package com.supergenius.web.admin.official.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.official.helper.OfficialArticleHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.official.entity.Article;
import com.supergenius.xo.official.enums.EArticleType;
import com.supergenius.xo.official.service.ArticleSO;

/**
 * 官网视频管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class VideoAdminer extends BaseController{
	
	@Autowired
	ArticleSO so;
	
	/**
	 * 打开页面
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/official/video"}, method = RequestMethod.GET)
	public String video(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.video.name());
		model.put(ViewKeyDict.site, EChannel.official.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.video, Locale.CHINA));
		Map<String, String> typeMap = new HashMap<String, String>();
		for (EArticleType item : EArticleType.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		typeMap.remove(EArticleType.news.toString());
		typeMap.remove(EArticleType.article.toString());
		model.put(ViewKeyDict.map, typeMap);
		return "dovideo";
	}
	
	/**
	 * 查询时组织数据	
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/official/ajax/video/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> video_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = OfficialArticleHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加文章
	 * @param article
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/video/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_add(Article article, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			article.setImgs(imgs);
		}
		if (so.add(article)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 编辑
	 * @param article
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/video/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_edit(Article article, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			article.setImgs(imgs);
		}
		if (so.update(article)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 修改状态
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/video/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/video/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> video_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

}
