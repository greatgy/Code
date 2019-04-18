package com.supergenius.web.front.life.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.CommentsHP;
import com.supergenius.web.front.life.helper.ContentHP;
import com.supergenius.web.front.life.helper.CourseHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.web.front.life.helper.VideoHP;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.Photo;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.enums.ELabel;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.rule.LifeVideoClickCountRlue;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;

/**
 * 视频
 * 
 * @author ChenQi
 */
@Controller
public class VideoController extends BaseController {

	@Autowired
	VideoSO so;

	@Autowired
	UserSO userSO;
	
	/**
	 * 视频
	 * 
	 * @param model
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/video/{cid:\\d+}", method = RequestMethod.GET)
	public String getVideo(Map<String, Object> model, @PathVariable Long cid, Long pcid, HttpServletRequest request) {
		model.put(ViewKeyDict.videoList, VideoHP.getVideo(SysConf.VideoSize, 1, cid));
		model.put(ViewKeyDict.hotVideoList, VideoHP.getHotVideo(SysConf.VideoSize, 1, cid));
		model.put(ViewKeyDict.pagesize, SysConf.VideoSize);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.pcid, pcid);
		if (ECatalogue.get(cid) == ECatalogue.test) {
			Photo photo = ContentHP.getbannerList(ECatalogue.get(cid)).get(0);
			model.put(ViewKeyDict.photo, photo);
			return "inspection";
		}
		return "mystage";
	}

	/**
	 * 视频下拉加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/ajax/video/{cid:\\d+}", method = RequestMethod.GET)
	public String ajax_video(Map<String, Object> model,@PathVariable Long cid, int pagenum, HttpServletRequest request) {
		List<Video> list = VideoHP.getVideo(SysConf.VideoSize, pagenum, cid);
		model.put(ViewKeyDict.list, list);
		return "ajaxvideo";
	}
	
	/**
	 * 热门视频
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/ajax/hotvideo/{cid:\\d+}", method = RequestMethod.GET)
	public String hotVideo(Map<String, Object> model,@PathVariable Long cid, int pagenum, HttpServletRequest request) {
		List<Video> list = VideoHP.getHotVideo(SysConf.VideoSize, pagenum, cid);
		model.put(ViewKeyDict.list, list);
		return "ajaxvideo";
	}
	/**
	 * @author 雍雪振
	 * @time 2018年6月2日下午12:34:12
	 * @description: 获得效果检验会员专区视频
	 */
	@RequestMapping(value = "/ajax/video/membervideo", method = RequestMethod.GET)
	public String memberVideo(Map<String, Object> model, Long cid, int pagenum, HttpServletRequest request) {
		model.put(ViewKeyDict.memberVideoList, VideoHP.getMemberVideo(SysConf.VideoSize, pagenum, cid));
		return "ajaxmembervideo";
	}

	/**
	 * 视频详情页面
	 * 
	 * @param model
	 * @param uid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月12日14:10:12
	 */
	@RequestMapping(value = "/videoDetail/{cid:\\d+}/{uid:.{32}}", method = RequestMethod.GET)
	public String article_detial(Map<String, Object> model, @PathVariable Long cid, @PathVariable String uid, HttpServletRequest request, HttpServletResponse response) {
		if (StrUtil.isNotEmpty(uid)) {
			Video video = so.get(uid);
			Rule rule = new LifeVideoClickCountRlue(video.getUid());
			RedisUtil.incr(rule);
			VideoHP.organized(video);
			User user = BaseUserHP.getCurrUser(request);
			if (ECatalogue.get(cid) == ECatalogue.test && video.getIsmember() == 1 && video.getState() !=  EState.wait) {
				Comments major = CommentsHP.getMajor(video.getUid());
				List<Comments> secondlist = CommentsHP.getSecondMajor(video);
				CommentsHP.organized(secondlist);
				List<String> prizes = new ArrayList<>();
				if (user != null) {
					prizes = CommentsHP.getListPrize(user.getUid());
					for (String item : prizes) {
						if (major.getUid().equals(item)) {
							major.setIsprize(true);
							break;
						}
					}
					for (Comments answer : secondlist) {
						for (String item : prizes) {
							if (answer.getUid().equals(item)) {
								answer.setIsprize(true);
								break;
							}
							if (major.getUid().equals(item)) {
								major.setIsprize(true);
								break;
							}
						}
					}
				} else {// 从cookie中获取
					Visitor visitor = CommentsHP.getVisitor(request, response);
					for (Comments item : secondlist) {
						if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
							item.setIsprize(true);
						}
					}
					if (CommentsHP.isPrise(major.getUid(), visitor.getUid())) {
						major.setIsprize(true);
					}
				}
				model.put(ViewKeyDict.majorreply, major);
				model.put(ViewKeyDict.secondmajorreply, secondlist);
			}
			model.put(ViewKeyDict.bean, video);
			model.put(ViewKeyDict.relateVideoList, VideoHP.getRelateVideo(SysConf.RelateVieoSize, cid, video));
			model.put(ViewKeyDict.cid, cid);
			Catalogue catalogue = IndexHP.getOneCatalogue(cid);
			long pcid=catalogue.getPcid();
			model.put(ViewKeyDict.pcid, pcid);
			if (user != null) {
				model.put(ViewKeyDict.useruid, user.getUid());
			} else {
				model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
			}
		}
		return "inspectdetail";
	}

	/**
	 * 跳转到添加视频的页面
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年5月18日18:12:18
	 */
	@RequestMapping(value = "/my/addvideo/{cid:\\d+}", method = RequestMethod.GET)
	public String addvideo(Map<String, Object> model, @PathVariable Long cid, HttpServletRequest request) {
		Catalogue catalogue = IndexHP.getOneCatalogue(cid);
		long pcid=catalogue.getPcid();
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.cid, cid);
		if (ECatalogue.get(cid) == ECatalogue.test) {
			//进行获取课程
			List<Subject> list = CourseHP.getSubject(EGrade.six);
			List<Subject> sevenList = CourseHP.getSubject(EGrade.seven);
			List<Subject> eightList = CourseHP.getSubject(EGrade.eight);
			List<Subject> nineList = CourseHP.getSubject(EGrade.nine);
			model.put(ViewKeyDict.sixList, list);
			model.put(ViewKeyDict.sevenList, sevenList);
			model.put(ViewKeyDict.eightList, eightList);
			model.put(ViewKeyDict.nineList, nineList);
			return "publishinspection";
		}
		return "publishFruit";
	}
	
	/**
	 * 添加视频
	 * 
	 * @param imgdata
	 * @param content
	 * @param title
	 * @param summary
	 * @return
	 * @author ChenQi
	 * @date 2018年5月18日18:12:59
	 */
	@RequestMapping(value = "/my/video/add_{cid:\\d+}", method = RequestMethod.POST)
	public String article_add(Map<String, Object> model, @PathVariable Long cid, String content, String title, String contributeimg, Integer keywords, Integer grade, Integer subjectid, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		if (StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(contributeimg)) {
			Video video = new Video();
			video.setUseruid(user.getUid());
			video.setIsmember(user.getIsUser() == true ? 1 : 0);
			if (ECatalogue.get(cid) == ECatalogue.stage) {
				video.setState(EState.over);
			} else {
				video.setState(EState.wait);
			}
			video.setCid(cid);
			video.setContent(content);
			video.setTitle(title);
			String[] imgdata = contributeimg.split(BaseStrDict.comma);
			video.setImgoriginal(imgdata[0]);
			video.setImgbig(imgdata[1]);
			video.setImgmedium(imgdata[2]);
			video.setImglittle(imgdata[3]);
			if (keywords != null) {
				video.setKeywords(ELabel.get(keywords));
			} else {
				video.setGrade(EGrade.get(grade));
				video.setSid(subjectid);
				video.setKeywords(ELabel.none);
			}
			if (so.add(video)) {
				return redirectPrefix + "/videoDetail" + BaseStrDict.slash + video.getCid() + BaseStrDict.slash + video.getUid();
			}
		}
		return "failed";
	}
	
	/**
	 * 异步获取科目列表
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/life/ajax/lifecourse/subjects", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_course(int grade, HttpServletResponse response) {
		List<Subject> list = CourseHP.getSubject(EGrade.get(grade));
		response.setContentType("text/html;charset=UTF-8");
		return JsonUtil.toJson(list, Json.webStrategy);
	}
}