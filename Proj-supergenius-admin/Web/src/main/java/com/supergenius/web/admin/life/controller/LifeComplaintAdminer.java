package com.supergenius.web.admin.life.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.ComplaintHP;
import com.supergenius.web.admin.life.helper.LifeArticleHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Complaint;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.ComplaintSO;
import com.supergenius.xo.life.service.ProblemSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.life.service.VideoSO;

/**
 * 举报管理
 * 
 * @author YangGuang
 * @date 2018年5月10日11:46:14
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeComplaintAdminer extends BaseController {

	@Autowired
	ComplaintSO so;
	@Autowired
	ArticleSO articleSO;
	@Autowired
	VideoSO videoSO;
	@Autowired
	TopicSO topicSO;
	@Autowired
	ProblemSO problemSO;

	/**
	 * 获得页面数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 * @createtime 2018年5月10日11:46:29
	 * @return String
	 */
	@RequestMapping(value = "/life/complaint", method = RequestMethod.GET)
	public String challenge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.complaint.name());
		model.put(ViewKeyDict.channelname, EChannel.complaint.getName());
		model.put(ViewKeyDict.total, so.getCount());
		model.put(ViewKeyDict.site, ESite.life.name());
		return "dolifecomplaint";
	}

	/**
	 * 查询得到数据
	 * 
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 * @author YangGuang
	 * @createtime 2018年5月10日11:48:37
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/life/ajax/complaint/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_complaint(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ComplaintHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 審核举报
	 * 
	 * @param uid
	 * @param dopwd
	 * @param desc
	 * @param result
	 * @return
	 * @author YangGuang
	 * @createtime 2018年5月10日11:49:50
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/complaint/setresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setresult(String uid, String status, String result) {
		if (StrUtil.isNotEmpty(uid)) {
			Complaint complaint = so.get(uid);
			if (complaint != null) {
				complaint.setResult(result);
				complaint.setStatus(EStatus.get(status));
				so.update(complaint);
				if (complaint.getStatus() == EStatus.enable) {// 举报成功发送消息
					if (complaint.getKind() == 0) {
						Article article = articleSO.get(complaint.getFromuid());
						article.setStatus(EStatus.disable);
						articleSO.update(article);
						Map<String, Object> map = new HashMap<>();
						map.put(MapperDict.uid, article.getUid());
						LifeArticleHP.getEngine().delete(map);
						LifeArticleHP.Cache(article.getCid());
					} else if (complaint.getKind() == 1) {
						Video video = videoSO.get(complaint.getFromuid());
						video.setStatus(EStatus.disable);
						videoSO.update(video);
					} else if (complaint.getKind() == 2) {
						Topic topic = topicSO.get(complaint.getFromuid());
						topic.setStatus(EStatus.disable);
						topicSO.update(topic);
					}else if (complaint.getKind() == 3) {
						Problem problem = problemSO.get(complaint.getFromuid());
						problem.setStatus(EStatus.disable);
						problemSO.update(problem);
						Map<String, Object> map = new HashMap<>();
						map.put(MapperDict.uid, problem.getUid());
						LifeArticleHP.getEngine().delete(map);
					}
					ComplaintHP.sendMsg(complaint, AdminHP.getAdminUid());
					return result(MsgKeyDict.editSuccess);
				}
			}
		}
		return result(MsgKeyDict.editFailed);
	}

}