package com.supergenius.web.front.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.server.base.controller.BaseController;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.common.utils.ValidUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.user.helper.SmsHP;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 找回密码controller
 * 
 * @author XieMing
 * @createtime 2016-4-21 下午16:18:26
 */
@Controller
public class ResetPwdController extends BaseController {

	@Autowired
	UserSO so;
	@Autowired
	UserInfoSO userInfoSO;

	/**
	 * 找回密码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetpwd/usersn")
	public String usersn(Map<String, Object> model, String method, HttpServletRequest request) {
		model.put(ViewKeyDict.origin, method);
		return "resetpwdstep01";
	}

	/**
	 * 找回密码第一步
	 * 
	 * @param model
	 * @param email
	 * @param usersn
	 * @param request
	 * @return
	 * @author XieMing
	 */
	@RequestMapping(value = { "/resetpwd/step2" }, method = RequestMethod.POST)
	public String step02(Map<String, Object> model, String method, String token, String checkCode, HttpServletRequest request, HttpServletResponse response) {
		model.put(ViewKeyDict.origin, method);
		User user = null;
		if (ValidUtil.isEmail(token)) {
			user = so.getByEmail(token);
		} else if (ValidUtil.isMobile(token)) {
			user = so.getByMobile(token);
		}
		if (user == null) {
			model.put(MsgKeyDict.not_exist, true);
			return "resetpwdstep01";
		}
		if (!checkCode.trim().equals(BaseHP.getCaptcha(request))) {
			model.put(MsgKeyDict.err_user_password_no_match_usersn, true);
			return "resetpwdstep01";
		}
		model.put(ViewKeyDict.token, token);
		model.put(ViewKeyDict.user, user);
		// 发送短信验证码的操作
		SmsHP.sendSms(token);
		return "resetpwdstep02";
	}

	/**
	 * 找回密码第二步
	 * 
	 * @param model
	 * @param email
	 * @param usersn
	 * @param request
	 * @return
	 * @author XieMing
	 */
	@RequestMapping(value = { "/resetpwd/ste3" }, method = RequestMethod.POST)
	public String st3(Map<String, Object> model, String method, String useruid, String code, String token, HttpServletRequest request, HttpServletResponse response) {
		model.put(ViewKeyDict.origin, method);
		User user = so.get(useruid);
		boolean flag = SmsHP.validateSmsCode(token, code);
		model.put(ViewKeyDict.token, token);
		SmsHP.removeSmsCode(token);
		model.put(ViewKeyDict.user, user);
		if (!flag) {
			return "resetpwdstep02"; 
		}
		return "resetpwdstep04";
	}

	/**
	 * 重置密码
	 * 
	 * @param model
	 * @param pwd
	 * @param repwd
	 * @param email
	 * @param request
	 * @param response
	 * @return
	 * @author XieMing
	 */
	@RequestMapping(value = { "/resetpwd/step5" }, method = RequestMethod.POST)
	public String step5(Map<String, Object> model, String method, String pwd, String repwd, String token, HttpServletRequest request, HttpServletResponse response) {
		model.put(ViewKeyDict.origin, method);
		System.out.println("method is *************:" + method);
		System.out.println("token is *************:" + token);
		User user = null;
		if (ValidUtil.isEmail(token)) {
			user = so.getByEmail(token);
		} else if (ValidUtil.isMobile(token)) {
			user = so.getByMobile(token);
		}
		if ((pwd != null) && ValidUtil.isPassword(pwd) && user != null) {
			if (pwd.equals(repwd)) {
				if ("paywd".equals(method)) {
					if (BaseUserHP.getCurrUser(request) == null) {
						model.put(MsgKeyDict.is_Enemy, true);
						return "resetpwdstep04";
					}
					user.initPayPwd(pwd);
					so.updatePayPwd(user);
				} else {
					user.initPassword(pwd);
					so.updatePwd(user);
				}
				BaseUserHP.freshSessUser(request, user);
				return "resetpwdstep05";
			} else {
				model.put(MsgKeyDict.err_pwd2_equal, user.getEmail());
				model.put(ViewKeyDict.token, token);
				return "resetpwdstep04";
			}
		} else {
			model.put(MsgKeyDict.err_pwd, true);
			return "resetpwdstep04";
		}
	}

	/**
	 * ajax判断该会员号的用户是否存在
	 * 
	 * @param model
	 * @param usersn
	 * @return
	 * @author XieMing 2016年12月5日 下午5:57:44
	 */
	@RequestMapping(value = "/ajax/user/usersnvalid")
	@ResponseBody
	public String emailvalid(Map<String, Object> model, @RequestParam("v") String usersn) {
		String flag = "1";
		User user = so.getByEmail(usersn);
		if (user != null) {
			return ViewKeyDict.real;
		} else {
			flag = "0";
		}
		return flag;
	}
}
