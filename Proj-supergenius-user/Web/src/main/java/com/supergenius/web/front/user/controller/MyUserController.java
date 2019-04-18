package com.supergenius.web.front.user.controller;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.common.utils.ValidUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.user.helper.SmsHP;
import com.supergenius.web.front.user.helper.UserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 账号管理Controller
 * 
 * @author chenminchang
 * @date 2016-3-25 下午1:52:17
 */
@Controller
public class MyUserController extends BaseController {

	@Autowired
	private UserSO userSO;
	@Autowired
	private UserInfoSO userInfoSO;

	/**
	 * 账号管理-个人信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/info", "/my/user" }, method = RequestMethod.GET)
	public String userinfo(Map<String, Object> model, HttpServletRequest request) {
		return "userinfo";
	}

	/**
	 * 账号管理-个人信息-打开修改个人信息页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/editinfo" }, method = RequestMethod.GET)
	public String editinfo(Map<String, Object> model, HttpServletRequest request) {
		return "editinfo";
	}

	/**
	 * 账号管理-个人信息-提交修改个人信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @author chenminchang
	 */
	@RequestMapping(value = "/my/user/editinfo", method = RequestMethod.POST)
	public String editInfo(User user, HttpServletRequest request) {
		User oldUser = BaseUserHP.getCurrUser(request);
		if (oldUser != null) {
			oldUser.setNickname (user.getUserInfo().getNickname());
			oldUser.setCompany(user.getCompany().trim());
			oldUser.setDepartment(user.getDepartment().trim());
			oldUser.setJob(user.getJob().trim());
			oldUser.setSummary(user.getSummary());
			boolean result1 = userInfoSO.update(oldUser.getUserInfo());
			boolean result2 = userSO.update(oldUser);
			if (result1 && result2) {
				BaseUserHP.freshSessUser(request, oldUser);
			}
		}
		return "userinfo";
	}

	/**
	 * 账号管理-个人信息-修改头像
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setavatar" }, method = RequestMethod.GET)
	public String setavatar(Map<String, Object> model, HttpServletRequest request) {
		return "setavatar";
	}

	/**
	 * 账号管理-个人信息-修改头像-提交
	 * 
	 * @param model
	 * @param avatar
	 * @param x
	 * @param y
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/user/setavatar", method = RequestMethod.POST)
	public String modifyavatar(Map<String, Object> model, String avatar, String x, String y, String x2, String y2, @RequestParam(required = false) MultipartFile file, HttpServletRequest request) {
		User me = BaseUserHP.getCurrUser(request);
		if (file != null && file.getSize() > 0) {
			String oldname = file.getOriginalFilename();
			String ext = FileUtil.getExtName(oldname);
			if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".gif")) {
				String[] strs = FileUtil.resizeImage(file, SysConf.ImgUserDataDir_Slash + me.getOid() + ViewKeyDict.slash + ViewKeyDict.avatar, 700, 900);
				if (strs != null && strs.length == 2) {
					model.put(ViewKeyDict.avatar, strs[1]);
				}
			} else {
				model.put(ViewKeyDict.err, true);
				return "setavatar";
			}
		} else if (StringUtils.isNotEmpty(avatar) && StringUtils.isNotEmpty(x) && StringUtils.isNotEmpty(y)) {
			String cropimg = FileUtil.cropImage(avatar, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(x2), Integer.valueOf(y2));// 截取的图片
			String[] paths = FileUtil.resizeImage(cropimg, SysConf.AvatarSizes);
			if (userInfoSO.updateAvatar(me.getUid(), paths)) {
				String cropimgpath = cropimg.replace(BaseSysConf.ImgSiteBasePath, "");
				FileUtil.deleteImg(avatar);// 删除原图
				FileUtil.deleteImg(cropimgpath);// 删除截取图
				FileUtil.deleteImg(me.getAvatarbig(), me.getAvatar(), me.getAvatarlittle());// 删除旧图
				me.setAvatarbig(paths[0]);
				me.setAvatar(paths[1]);
				me.setAvatarlittle(paths[2]);
				BaseUserHP.freshSessUser(request, me);
				return redirectPrefix + "/my/user/info";
			}
		}
		return "setavatar";
	}

	/**
	 * 账号管理--修改登录密码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setpwd" }, method = RequestMethod.GET)
	public String setpwd(Map<String, Object> model, HttpServletRequest request) {
		return "setpwd";
	}

	/**
	 * 账号管理--修改登录密码-ajax判断输入密码是否正确
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ajax/my/user/verifypwd" }, method = RequestMethod.GET)
	@ResponseBody
	public String verifypwd(Map<String, Object> model, @RequestParam("v") String currpwd, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		String flag = "0";
		if (user.checkPwd(currpwd)) {
			return ViewKeyDict.real;
		}
		return flag;
	}

	/**
	 * 账号管理--修改登录密码-提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setpwd" }, method = RequestMethod.POST)
	public String setpwd(Map<String, Object> model, String currpwd, String newpwd2, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		if (user.checkPwd(currpwd)) {
			user.initPassword(newpwd2);
			if (userSO.updatePwd(user)) {
				BaseUserHP.freshSessUser(request, user);
				model.put(MsgKeyDict.success, true);
				return "setpwd";
			}
		}
		model.put(MsgKeyDict.failed, true);
		return "setpwd";
	}

	/**
	 * 账号管理--支付密码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setpaypwd" }, method = RequestMethod.GET)
	public String setpaycode(Map<String, Object> model, HttpServletRequest request, String find) {// 找回密码链接到这带find参数
		if (find != null) {
			model.put(ViewKeyDict.find, true);
		}
		return "setpaypwd";
	}

	/**
	 * 设置支付密码（忘记支付密码）
	 * 
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 * @author XieMing 2016年12月1日 下午7:53:08
	 */
	@RequestMapping(value = { "/my/email_findpaypwd/{uid:.{32}}/{validcode}" }, method = RequestMethod.GET)
	public String reset_paypwd(Map<String, Object> model, @PathVariable String uid, @PathVariable String validcode, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		user = userSO.get(user.getUid());
		if (user.getUid().equals(uid)) {
			DateTime date1 = user.getDataMap_resetpaypwdtime();
			String code = user.getDataMap_resetpaypwdcode();
			if (date1 != null && date1.plusDays(SysConf.ResetPwdDays).isAfterNow()) {
				if (validcode.equals(code)) {
					Map<String, Object> map = user.getDataMap();
					map.remove(MapperDict.resetpaypwdtime);
					user.setData(JsonUtil.toJson(map));
					userSO.updateData(user.getUid(), user.getData());
					model.put(MsgKeyDict.failed, "1");
					return "resetpaypwd";
				} else {
					return "404";
				}
			} else {
				model.put(MsgKeyDict.not_exist, true);
				return "resetpaypwd";
			}
		} else {
			return "404";
		}
	}

	/**
	 * 设置支付密码
	 * 
	 * @param model
	 * @param newpaypwd
	 * @param newpaypwd2
	 * @param request
	 * @return
	 * @author XieMing 2016年12月1日 下午7:52:51
	 */
	@RequestMapping(value = { "/my/user/resetpaypwd" }, method = RequestMethod.POST)
	public String resetpaypwd(Map<String, Object> model, String newpaypwd, String newpaypwd2, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		if (StrUtil.isNotEmpty(newpaypwd) && newpaypwd.equals(newpaypwd2)) {
			user.initPayPwd(newpaypwd);
			if (userSO.updatePayPwd(user)) {
				BaseUserHP.freshSessUser(request, user);
				model.put(MsgKeyDict.success, true);
				model.put(MsgKeyDict.failed, "1");
				return "resetpaypwd";
			}
		}
		model.put(MsgKeyDict.failed, "0");
		return "resetpaypwd";

	}

	/**
	 * 账号管理--支付密码-ajax判断输入支付密码是否正确
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ajax/my/user/verifypaypwd" }, method = RequestMethod.GET)
	@ResponseBody
	public String verifypaypwd(Map<String, Object> model, @RequestParam("v") String currpaypwd, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		String flag = "0";
		if (user.checkPayPwd(currpaypwd)) {
			return ViewKeyDict.real;
		}
		return flag;
	}

	/**
	 * 账号管理--支付密码-提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setpaypwd" }, method = RequestMethod.POST)
	public String setpaypwd(Map<String, Object> model, String currpaypwd, String newpaypwd2, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		if (user.checkPayPwd(currpaypwd)) {
			user.initPayPwd(newpaypwd2);
			if (userSO.updatePayPwd(user)) {
				BaseUserHP.freshSessUser(request, user);
				model.put(MsgKeyDict.success, true);
				return "setpaypwd";
			}
		}
		model.put(MsgKeyDict.failed, true);
		return "setpaypwd";
	}

	

	/**
	 * 账号管理--修改绑定邮箱
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setemail" }, method = RequestMethod.GET)
	public String setemail(Map<String, Object> model, HttpServletRequest request, String change) {
		model.put(ViewKeyDict.abc, change);
		return "setemail";
	}

	/**
	 * 会员注册--实名认证-会员中心-ajax判断输入身份证是否存在
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ajax/my/user/verifyidcard" }, method = RequestMethod.GET)
	@ResponseBody
	public String verifyidcard(Map<String, Object> model, @RequestParam("v") String idcard, HttpServletRequest request) {
		User user=UserHP.getUserByIdentityid(idcard);
		if (user == null) {
			return ViewKeyDict.real;
		}
		return "0";
	}

	/**
	 * 账号管理--修改绑定邮箱-提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/setemail" }, method = RequestMethod.POST)
	public String setemail(Map<String, Object> model, String currpwd, String idcard, String newemail, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		if (idcard.trim().equals(user.getIdentityid())) {
			if (user.checkPwd(currpwd)) {
				user.setValidcode(StrUtil.getRandomString(6));
				user.setNewemail(newemail);
				if (userSO.updateNewemail(user)) {
					if (UserHP.sendRebindingEmail(user)) {
						BaseUserHP.freshSessUser(request, user);
						model.put(ViewKeyDict.url, UserHP.getEmailLoginUrl(user.getNewemail()));
						model.put(MsgKeyDict.success, true);
						return "setemail";
					}
				}
			}
		}
		model.put(MsgKeyDict.failed, true);
		return "setemail";
	}

	/**
	 * 账号管理--修改绑定邮箱
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/changeemail" }, method = RequestMethod.GET)
	public String changeemail(Map<String, Object> model, String change, String email, String code, HttpServletRequest request) {
		if (SmsHP.validateSmsCode(email, code)) {
			SmsHP.removeSmsCode(email);
			model.put(ViewKeyDict.status, change);
			return "setemail";
		}
		model.put(ViewKeyDict.err, true);
		model.put(ViewKeyDict.abc, change);
		return "setemail";
	}

	/**
	 * 账号管理--修改绑定邮箱2
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/changeemail/stp2" }, method = RequestMethod.GET)
	public String changeemail2(Map<String, Object> model, String uid, String status, String email, String code, HttpServletRequest request, HttpServletResponse response) {
		if (SmsHP.validateSmsCode(email, code)) {
			SmsHP.removeSmsCode(email);
			Map<String, Object> map = new HashMap<String, Object>();
			if (ValidUtil.isEmail(email)) {
				map.put(ViewKeyDict.uid, uid);
				map.put(ViewKeyDict.email, email);
				userSO.updateFields(map);
			}
			if (ValidUtil.isMobile(email)) {
				map.put(ViewKeyDict.uid, uid);
				map.put(ViewKeyDict.mobile, email);
				userSO.updateFields(map);
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put(MapperDict.uid, uid);
			map1.put(MapperDict.showname, email);
			userInfoSO.updateFields(map1);
			User user = userSO.get(uid);
			request.setAttribute(ViewKeyDict.me, user);
			BaseUserHP.freshSessUser(request, user);
			model.put(ViewKeyDict.success, status);
			return "setemail";
		}
		model.put(ViewKeyDict.addemail, status);
		model.put(ViewKeyDict.err, true);
		return "setemail";
	}

	/**
	 * 解绑
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/my/unbind" }, method = RequestMethod.GET)
	public String unbind(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, String param) {
		User user = BaseUserHP.getCurrUser(request);
		HashMap<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, user.getUid());
		switch (param) {
		case "1":
			if (StrUtil.isEmpty(user.getQq()) && StrUtil.isEmpty(user.getMobile()) && StrUtil.isEmpty(user.getWx()) && StrUtil.isEmpty(user.getSina())) {
				model.put(ViewKeyDict.errmsg, true);
			} else {
				map.put(MapperDict.email, "");
				userSO.updateFields(map);
			}
			break;
		case "2":
			if (StrUtil.isEmpty(user.getQq()) && StrUtil.isEmpty(user.getEmail()) && StrUtil.isEmpty(user.getWx()) && StrUtil.isEmpty(user.getSina())) {
				model.put(ViewKeyDict.errmsg, true);
			} else {
				map.put(MapperDict.mobile, "");
				userSO.updateFields(map);
			}
			break;
		case "3":
			if (StrUtil.isEmpty(user.getQq()) && StrUtil.isEmpty(user.getMobile()) && StrUtil.isEmpty(user.getEmail()) && StrUtil.isEmpty(user.getSina())) {
				model.put(ViewKeyDict.errmsg, true);
			} else {
				map.put(MapperDict.wx, "");
				userInfoSO.updateFields(map);
			}
			break;
		case "4":
			if (StrUtil.isEmpty(user.getQq()) && StrUtil.isEmpty(user.getMobile()) && StrUtil.isEmpty(user.getEmail()) && StrUtil.isEmpty(user.getWx())) {
				model.put(ViewKeyDict.errmsg, true);
			} else {
				map.put(MapperDict.sina, "");
				userInfoSO.updateFields(map);
			}
			break;
		case "5":
			if (StrUtil.isEmpty(user.getSina()) && StrUtil.isEmpty(user.getMobile()) && StrUtil.isEmpty(user.getEmail()) && StrUtil.isEmpty(user.getWx())) {
				model.put(ViewKeyDict.errmsg, true);
			} else {
				map.put(MapperDict.qq, "");
				userInfoSO.updateFields(map);
			}
			break;
		default:
			break;
		}
		User user2 = userSO.get(user.getUid());
		request.setAttribute(ViewKeyDict.me, user2);
		BaseUserHP.freshSessUser(request, user2);
		return "safeqa";
	}

	/**
	 * 绑定email或者mobile
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/my/bind" }, method = RequestMethod.GET)
	public String bind(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, String param) {
		Integer integer = Integer.valueOf(param);
		if (integer == 1) {
			model.put(ViewKeyDict.addemail, ViewKeyDict.email);
			return "setemail";
		}
		model.put(ViewKeyDict.addemail, ViewKeyDict.mobile);
		return "setemail";
	}


	/**
	 * 账号管理--账号保护
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/safeqa" }, method = RequestMethod.GET)
	public String safeqa(Map<String, Object> model, HttpServletRequest request) {
		return "safeqa";
	}
	/**
	 * 账号管理--我的消息
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/user/news" }, method = RequestMethod.GET)
	public String news(Map<String, Object> model, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		model.put(ViewKeyDict.newsList,UserHP.getMsgs(user.getUid(), 1, SysConf.MyMsgPageSize));
		return "news";
	}
	/**
	 * 账号管理--获取更多消息
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ajax/my/user/morenews" }, method = RequestMethod.GET)
	public String ajax_news(Map<String, Object> model, HttpServletRequest request,int pagenum ) {
		User user = BaseUserHP.getCurrUser(request);
		model.put(ViewKeyDict.list,UserHP.getMsgs(user.getUid(),pagenum, SysConf.MyMsgPageSize));
		return "ajaxnews";
	}

	/**
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/user/newdetail/{uid}", method = RequestMethod.GET)
	public String newdetail(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request) {
		model.put(ViewKeyDict.msg,UserHP.getOneNew(uid));
		return "newdetail";
	}

	/**
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/user/newdelete/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public String newdelete(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request) {
		if(UserHP.deleteOneNew(uid)){
			return "true";
		}
		return "false";
	}
}