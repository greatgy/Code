package com.supergenius.web.front.life.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.EssayHP;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.service.EssaySO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 动态controller
 * 
 * @author: ChenQi
 * @date 2018年5月18日10:37:16
 */
@Controller
@RequestMapping(value = BaseUriConf.baseMobilePath)
public class EssayMobile extends BaseController {
	
	@Autowired
	private VisitorSO visitorso;
	
	@Autowired
	private EssaySO essayso;
	
	
	/**
	 * 添加动态
	 * 
	 * @param fromuid
	 * @param request
	 * @param response
	 * @author ChenQi
	 * @date 2018年5月18日10:54:51
	 */
	@ResponseBody
	@RequestMapping(value = { "/ajax/addessay/{cid:\\d+}" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> essay_add(Essay essay, String isnick, @PathVariable Long cid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		Visitor visitor = null;
		if (StrUtil.isEmpty(isnick)) {
			essay.setFromUser(user);
			essay.setFromuseruid(user.getUid());
			essay.setFromuseroid(user.getOid());
			essay.setFromusername(user.getUsername());
		} else {
			visitor = EssayHP.getNickVisitor(request, response);
			essay.setFromuseruid(visitor.getUid());
			essay.setFromuseroid(visitor.getOid());
			essay.setFromusername(user.getUsername());
			essay.setFromVisitorAvatar(visitor.getAvatar());
			essay.setFromVisitorName(visitor.getNickname());
		}
		if (StrUtil.isNotEmpty(essay.getTouseruid())) {
			User toUser = BaseUserHP.get(essay.getTouseruid());
			if (toUser != null) {
				essay.setTousername(toUser.getUsername());
				essay.setTouseroid(toUser.getOid());
			} else {
				Visitor tovisitor = visitorso.get(essay.getTouseruid());	
				if (tovisitor != null) {
					essay.setTousername(tovisitor.getUsername());
				}
				essay.setTouseroid(tovisitor.getOid());
			}
		}
		essay.setType(EComment.comment);
		essay.setCid(cid);
		essay.setChannel(ELifeChannel.essay);
		essay.setContent(WebUtil.clearXSS(essay.getContent()));
		String topuid = request.getParameter(ViewKeyDict.topuid);
		if (StringUtils.isEmpty(topuid)) {
			topuid = request.getParameter(ViewKeyDict.touid);
		}
		if (StrUtil.isNotEmpty(topuid)) {
			essay.setData(topuid);
		}
		if (StrUtil.isEmpty(essay.getTouid())) {
			essay.setTouid(null);
		}
		String imgpath = request.getParameter(ViewKeyDict.imgpath);
		if (StrUtil.isNotEmpty(request.getParameter(ViewKeyDict.imgpath))) {
			String[] data = imgpath.split("@");
			String imglittle = new String();
			String imgmedium = new String();
			String imgbig = new String();
			String imgoriginal = new String();
			for (int i = 0; i < data.length ; i++) {
				imgoriginal += data[i].split(",")[0] + ",";
				imgbig += data[i].split(",")[1] + ",";
				imgmedium += data[i].split(",")[2] + ",";
				imglittle += data[i].split(",")[3] + ",";
			}
			essay.setImgoriginal(imgoriginal);
			essay.setImglittle(imglittle);
			essay.setImgmedium(imgmedium);
			essay.setImgbig(imgbig);
		}
		boolean addresult = EssayHP.add(essay, request);
		FileUtil.delete(SysConf.SerialBasePath + "/life/"+ cid + SysConf.SerialEssayPath);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.success, addresult);
		result.put(ViewKeyDict.bean, essay);
		return json(result, Json.webStrategy);
	}
	
	/**
	 * 获取一级评论
	 * 
	 * @param model
	 * @param fromuid文章的uid
	 * @param num当前页数
	 * @author ChenQi
	 * @date 2017年12月7日17:30:43
	 */
	@RequestMapping(value = ("/ajax/essay/{cid:\\d+}_{num:\\d+}"), method = RequestMethod.GET)
	public String essay_list(Map<String, Object> model, @PathVariable Long cid, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		if (num == null || num <= 0) {
			num = 1;
		}
		User user = BaseUserHP.getCurrUser(request);
		List<Essay> list = essayso.getCommentList(EComment.comment, SysConf.EssaySize, num, cid);
		EssayHP.organized(list);
		EssayHP.setprise(list, user, request, response);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.list, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		}else{
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		return "majaxessay";
	}
	/**
	 * 获取动态热点
	 * 
	 * @param model
	 * @param fromuid文章的uid
	 * @param nu当前页数
	 * @author ChenQi
	 * @date 2018年5月18日11:20:45
	 */
	@RequestMapping(value = ("/ajax/essay/hot/{cid:\\d+}_{nu:\\d+}"), method = RequestMethod.GET)
	public String essay_list_hot(Map<String, Object> model, @PathVariable Long cid, @PathVariable Integer nu, HttpServletRequest request,
			HttpServletResponse response) {
		if (nu == null || nu <= 0) {
			nu = 1;
		}
		User user = BaseUserHP.getCurrUser(request);
		List<Essay> list = EssayHP.getEssayHot(nu, SysConf.EssaySize, cid);
		EssayHP.organized(list);
		EssayHP.setprise(list, user, request, response);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.list, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		}else{
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		return "majaxessay";
	}
	/**
	 * 获取二级评论
	 * 
	 * @param model
	 * @param firstuid一级评论的uid
	 * @param pagenum当前页数
	 * @author ChenQi
	 * @date 2018年5月18日11:22:26
	 */
	@RequestMapping(value = ("/ajax/essay/{firstuid:.{32}}_{cid:\\d+}_{pagenum:\\d+}"), method = RequestMethod.GET)
	public String second_list(Map<String, Object> model, @PathVariable String firstuid, @PathVariable Long cid, @PathVariable Integer pagenum, HttpServletRequest request,
			HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (pagenum == null || pagenum <= 0) {
			pagenum = 1;
		}
		List<Essay> list = essayso.getSecondList(EComment.comment, firstuid, SysConf.EssaySize, pagenum);
		EssayHP.organized(list);
		EssayHP.setprise(list, user, request, response);
		model.put(ViewKeyDict.firstuid, firstuid);
		model.put(ViewKeyDict.secondList, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		}else{
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		model.put(ViewKeyDict.cid, cid);
		return "majaxsecondessay";
	}
	
	/**
	 * 删除评论
	 * 
	 * @param model
	 * @param useruid
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ajax/essay/delete/{uid:.{32}}/{fromuseruid:.{32}}/{useruid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean essay_delete(Map<String, Object> model, @PathVariable String uid, @PathVariable String fromuseruid, @PathVariable String useruid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			if (!user.getUid().equals(fromuseruid) && !user.getUid().equals(useruid)) {
				return false;
			}
		}else {
			String visitorUid = CookieUtil.get(request, ViewKeyDict.visitors);
			if (StrUtil.isNotEmpty(visitorUid) && !visitorUid.equals(fromuseruid)) {
				return false;
			}
		}
		EssayHP.deleteEssay(uid, useruid);
		return true;
	}
	
	/**
	 * 动态详情页
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author ChenQi
	 * @date 2018年5月18日11:55:13
	 */
	@RequestMapping(value = "/life/essaydetail/{uid:.{32}}", method = RequestMethod.GET)
	public String problemDetail(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request, HttpServletResponse response) {
		Essay essay = essayso.get(uid);
		User user = BaseUserHP.getCurrUser(request);
		if (essay == null) {
			return response404(response);
		}
		EssayHP.organized(essay);
		List<Essay> list = essayso.getSecondList(EComment.comment, essay.getUid(), SysConf.EssaySize, 1);
		EssayHP.organized(list);
		if (user != null) {// 设置是否赞过
			if (EssayHP.isPrise(essay.getUid(), user.getUid())) {
				essay.setIsprize(true);
			}
			for (Essay item : list) {
				if (EssayHP.isPrise(item.getUid(), user.getUid())) {
					item.setIsprize(true);
				}
			}
		} else {
			Visitor visitor = EssayHP.getVisitor(request, response);
			if (EssayHP.isPrise(essay.getUid(), visitor.getUid())) {
				essay.setIsprize(true);
			}
			for (Essay item : list) {
				if (EssayHP.isPrise(item.getUid(), visitor.getUid())) {
					item.setIsprize(true);
				}
			}
		}
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		}else{
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		model.put(ViewKeyDict.firstuid, uid);
		model.put(ViewKeyDict.item, essay);
		model.put(ViewKeyDict.secondessay, list);
		return "messaydetail";
	}
	
	/**
	 * 上传图片处理
	 * 
	 * @param model
	 * @param type
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/essay/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String uploadEssayimg(Map<String, Object> model, @RequestParam MultipartFile file, HttpServletRequest request) {
		String data = EssayHP.resizeImage(file, SysConf.UploadImgPath, SysConf.ImgShowSizes, SysConf.ImgUploadBasePath);
		String[] imgpath = data.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		if (imgpath.length > 0) {
			map.put("path", imgpath);
			map.put("state", 1);
		} else {
			map.put("state", 0);
			map.put("errmsg", "error img");
		}
		return JsonUtil.toJson(map);
	}
	
	/**
	 * 点赞的处理
	 * 
	 * @param model
	 * @param channel
	 * @param oid
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ajax/prize/btnprizeessay_{channel:[a-z]+}_{oid:\\d+}_{cid:\\d+}_{uid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean answer_prize(Map<String, Object> model, @PathVariable String channel, @PathVariable int oid, @PathVariable Long cid, @PathVariable String uid,
			HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		boolean bool = false;
		if (ELifeChannel.get(channel) == ELifeChannel.essay) {
			if (user != null) {
				if (!essayso.isNotPrized(uid, user.getUid(), ELifeChannel.get(channel))) {// 取消赞
					EssayHP.cancelPrize(user.getUid(), uid, channel);
					bool = false;
				} else {// 加赞
					Essay essay = new Essay();
					essay.setTouid(uid);
					essay.setFromuseruid(user.getUid());
					essay.setFromuseroid(user.getOid());
					essay.setFromusername(user.getShowname());
					essay.setContent("");
					essay.setType(EComment.praise);
					essay.setChannel(ELifeChannel.get(channel));
					bool = EssayHP.add(essay, request);
				}
			} else {// 非会员
				Visitor visitor = EssayHP.getVisitor(request, response);
				if (!essayso.isNotPrized(uid, visitor.getUid(), ELifeChannel.get(channel))) {// 已经点过赞，再次点击取消赞
					EssayHP.cancelPrize(visitor.getUid(), uid, channel);
					bool = false;
				} else {
					Essay essay = new Essay();
					essay.setTouid(uid);
					essay.setFromuseruid(visitor.getUid());
					essay.setFromuseroid(0);
					essay.setFromusername(WebConf.DefaultAnonymousName);// WebConf.defaultAnonymousName
					essay.setContent("");
					essay.setType(EComment.praise);
					essay.setChannel(ELifeChannel.get(channel));
					bool = EssayHP.add(essay, request);
				}
			}
		}
		return bool;
	}
}