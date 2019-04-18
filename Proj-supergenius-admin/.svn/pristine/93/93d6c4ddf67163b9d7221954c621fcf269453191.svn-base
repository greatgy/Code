package com.supergenius.web.admin.official.controller;

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
 * 新闻管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class NewsAdminer extends BaseController{
	
	@Autowired
	ArticleSO so;
	
	/**
	 * 进入新闻管理
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/official/news"}, method = RequestMethod.GET)
	public String news(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.news.name());
		model.put(ViewKeyDict.site, EChannel.official.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.news, Locale.CHINA));
		return "donews";
	}
	
	/**
	 * 查询时组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/official/ajax/news/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> news_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		model.put(ViewKeyDict.type, EArticleType.news);
		Map<String, Object> searchMap = OfficialArticleHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加新闻
	 * @param article
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/news/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> news_add(Article article, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			article.setImgs(imgs);
		}
		article.setType(EArticleType.news);
		if (so.add(article)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 保存编辑新闻
	 * @param article
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/news/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> news_edit(Article article, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			article.setImgs(imgs);
		}
		article.setType(EArticleType.news);
		if (so.update(article)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 设置置顶
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/official/ajax/news/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> news_top(String[] ids) {
		if (so.setTop(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消置顶
	 * @param ids
	 * @return 
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/news/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> news_untop(String[] ids) {
		if (so.setTop(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 修改新闻状态
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 */
	@RequestMapping(value = "/official/ajax/news/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> news_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 删除新闻
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/news/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> news_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
}
