package com.supergenius.web.admin.moral.controller;

import java.util.HashMap;
import java.util.Locale;
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
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.admin.moral.helper.CommentHP;
import com.supergenius.web.admin.moral.helper.MessageHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.service.CommentSO;

/**
 * 评论管理
 * 
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralCommentAdminer extends BaseController {

	@Autowired
	CommentSO so;

	/**
	 * 打开评论管理
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/comment", method = RequestMethod.GET)
	public String comment(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.comment.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.comment, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		return "domoralcomment";
	}

	/**
	 * 组织数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/comment/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> comment_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CommentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/comment/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comment_delete(String ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.touid, ids);
		Comment comment = so.get(ids);
		if (so.delete(ids) && so.deleteByMap(map)) {
			MessageHP.sendNoticeMsg(BaseUserHP.get(comment.getFromuseruid()), comment.getContent(), null, EMsg.delcomment);
			return success();
		} else {
			return result(MsgKeyDict.deleteFailed);
		}
	}

	/**
	 * 修改状态
	 * 
	 * @param ids
	 * @param status
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/comment/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> comment_status(String[] ids, @PathVariable int status) {
		if (so.setStatus(ids, EStatus.get(status))) {
			// TODO 删除序列化文件
			return success();
		} else {
			return result(MsgKeyDict.editFailed);
		}
	}
}
