package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.core.rule.CommentCountPkRule;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.manager.helper.CommentsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Comments;
import com.supergenius.xo.manager.service.CommentsSO;
import com.supergenius.xo.user.enums.EComment;

/** 
 * 评论后台管理
 * @author chenminchang
 * @date 2016-11-3 下午12:01:37 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerCommentsAdminer extends BaseController {
	
	@Autowired
	CommentsSO so;
	@Autowired
	AdminLogSO adminLogSO;
	
	/**
	 * 进入评论管理
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-3下午12:18:18
	 */
	@RequestMapping( value = "/manager/comments", method = RequestMethod.GET)
	public String manager_comments(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.comments.name());
		model.put(ViewKeyDict.channelname, EChannel.comments.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.commentcount, so.getCount(EComment.comment));
		model.put(ViewKeyDict.count, so.getSpeakCount(EComment.comment));
		model.put(ViewKeyDict.praisecount, so.getCount(EComment.praise));
		model.put(ViewKeyDict.count2, so.getSpeakCount(EComment.praise));
		model.put(ViewKeyDict.total, so.getSpeakCount());
		return "domcomments";
	}
	
	/**
	 * 查询评论数据
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-4下午3:22:56
	 */
	@RequestMapping(value = { "/manager/ajax/comments/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> comments_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CommentsHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除评论
	 * @param ids
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8上午10:51:02
	 */
	@RequestMapping(value = { "/manager/ajax/comments/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> manager_delete(String[] ids) {
		Comments comments = so.get(ids[0]);
		if (comments != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.comment.toInt());
			adminLog.setOperation(EAdminLog.deleteManagerComment.getName());
			adminLog.setData(EAdminLog.deleteManagerComment.getName());
			adminLog.setDesc(EAdminLog.deleteManagerComment.getName());
			adminLog.setDataid(ids[0]);
			if (so.delete(comments.getUid()) && adminLogSO.add(adminLog)) {
				String directory = comments.getChannel().name();
				CommentsHP.deleteSerialFile(directory, comments.getFromuid());;//序列化及缓存操作
				if (StrUtil.isEmpty(comments.getTouid())) {//一级评论
					Rule rule = new CommentCountPkRule(comments.getUid());
					RedisUtil.delete(rule);//删除redis中的计数
					so.deleteByTouid(comments.getUid());//删除下面的二级评论
				} else {
					CommentsHP.decrCommentCount(comments);//评论数减1
				}
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 解冻冻结评论
	 * @param ids
	 * @param status
	 * @param adminuid
	 * @param dopwd
	 * @param desc
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午5:52:02
	 */
	@RequestMapping(value = { "/manager/ajax/comments/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> comments_status(String[] ids, @PathVariable int status, String adminuid, String dopwd, String desc) {
		Comments comments = so.get(ids[0]);
		if (AdminHP.isDopwd(dopwd) && comments != null) {
			if (StrUtil.isNotEmpty(comments.getTouid())) {//二级评论
				if (EStatus.disable.equals(so.get(comments.getTouid()).getStatus()))
					return result(MsgKeyDict.doFailed);//一级评论为冻结时，二级解冻失败
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.comment.toInt());
			adminLog.setOperation(EAdminLog.updateManagerCommentStatus.getName());
			adminLog.setData(EAdminLog.updateManagerCommentStatus.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			if (so.updateStatus(comments.getUid(), EStatus.get(status), adminLog)) {
				if (StrUtil.isEmpty(comments.getTouid())) {//一级评论
					so.updateStatusByTouid(comments.getUid(), EStatus.get(status));//更新二级评论
				}
				CommentsHP.updateStatus(comments, EStatus.get(status));//序列化及缓存操作
				return success();
			}
			return result(MsgKeyDict.doFailed);
		} 
		return result(MsgKeyDict.dopwdIsWrong);
	}
	
	/**
	 * 二级评论冻结了是否可以解冻
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-9下午2:42:51
	 */
	@RequestMapping(value = { "/manager/ajax/comments/canenable" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comments_canenable(String uid) {
		Comments comments = so.get(uid);
		if (comments != null) {
			if (EStatus.enable.equals(so.get(comments.getTouid()).getStatus()))
				return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
}
