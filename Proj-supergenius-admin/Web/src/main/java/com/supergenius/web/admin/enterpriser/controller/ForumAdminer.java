package com.supergenius.web.admin.enterpriser.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserForumHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Forum;
import com.supergenius.xo.enterpriser.service.ForumSO;

/**
 * 论坛管理
 * 
 * @author YangGuang
 * @date 2018年1月30日11:50:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ForumAdminer extends BaseController {

	@Autowired
	private ForumSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 论坛管理页面
	 * 
	 * @author YangGuang
	 * @date 2018年1月30日14:33:40
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/enterpriserforum", method = RequestMethod.GET)
	public String forum(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterpriserforum.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.enterpriserforum, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.enterpriser.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.EnterpriserPhotoPath);
		return "doenterpriserforum";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:52:43
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> forum_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EnterpriserForumHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除论坛
	 * 
	 * @param ids
	 * @return
	 * @author YangGuang
	 * @data 2018年1月30日14:42:54
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> forum_delete(String[] ids) {
		String adminuid = AdminHP.getAdminUid();
		for (String id : ids) {
			Forum forum = so.get(id);
			if (forum != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.enterpriserforum.toInt());
				adminLog.setOperation(EAdminLog.deleteEnterpriserForum.getName());
				adminLog.setData(EAdminLog.deleteEnterpriserForum.getName());
				adminLog.setDesc(EAdminLog.deleteEnterpriserForum.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				forum.setStatus(EStatus.deleted);
				forum.setAdminuid(adminuid);
				if (so.update(forum)) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 冻结解冻
	 * 
	 * @param uid
	 * @return
	 * @author YangGuang
	 * @data 2018年1月30日14:47:06
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forum_delete(String uid, @PathVariable int status) {
		Forum forum = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (forum != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.enterpriserforum.toInt());
			adminLog.setOperation(EAdminLog.updateEnterpriserForum.getName());
			adminLog.setData(EAdminLog.updateEnterpriserForum.getName());
			adminLog.setDesc(EAdminLog.updateEnterpriserForum.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			forum.setStatus(EStatus.get(status));
			forum.setAdminuid(adminuid);
			if (so.update(forum)) {
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 论坛编辑
	 * 
	 * @param uid
	 * @param title
	 * @param content
	 * @param summary
	 * @return
	 * @author YangGuang
	 * @date 2018年1月30日14:54:14
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forum_edit(String content, String title, String uid, String author, String summary, String keywords) {
		Forum forum = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isEmpty(title) || StrUtil.isEmpty(content)) {
			return result(MsgKeyDict.updateFailed);
		}
		if (forum != null) {
			if (StrUtil.isNotEmpty(summary)) {
				forum.setSummary(summary);
			}
			if (StrUtil.isNotEmpty(keywords)) {
				forum.setKeywords(keywords);
			}
			forum.setContent(content);

			forum.setTitle(title);
			forum.setAuthor(author);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminUid);
			adminLog.setChannel(EChannel.enterpriserforum.toInt());
			adminLog.setDataid(forum.getUid());
			adminLog.setDesc(EAdminLog.updateEnterpriserForum.getName());
			adminLog.setData(EAdminLog.updateEnterpriserForum.getName());
			adminLog.setOperation(EAdminLog.updateEnterpriserForum.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				forum.setAdminuid(adminUid);
			}
			if (so.update(forum)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 推荐到首页
	 * 
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年1月30日15:13:44
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> forum_recommend(String[] ids) {
		if (so.setRecommend(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 置顶
	 * 
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年1月30日15:13:44
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/toptrue", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> forum_top(String[] ids) {
		if (so.setTop(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消推荐到首页
	 * 
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年1月30日15:14:24
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> forum_unrecommend(String[] ids) {
		if (so.setRecommend(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年1月30日15:14:24
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserforum/topfalse", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> forum_untop(String[] ids) {
		if (so.setTop(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 获取置顶的个数
	 * 
	 * @author ChenQi
	 * @date 2018年1月5日15:25:56
	 */
	@RequestMapping(value = "/enterpriser/ajax/{channel:[a-z]+}/topcount/{cid:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, @PathVariable int cid, @PathVariable String channel, HttpServletRequest request) {
		model.put(MapperDict.count, EnterpriserForumHP.getTopForumCount(cid));
		return json(model, Json.webStrategy);
	}

}