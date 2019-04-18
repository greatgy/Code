package com.supergenius.web.admin.tpi.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.ArticleHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.enums.EMergeCaseType;
import com.supergenius.xo.tpi.enums.EMergeNewsType;
import com.supergenius.xo.tpi.service.ArticleSO;
import com.supergenius.xo.tpi.service.TpiCountSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 文章管理控制器
 * 
 * @author ShangJianguo
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ArticleAdminer extends BaseController {
	
	@Autowired
	ArticleSO so;
	
	@Autowired
	WishSO wishSO;	
	
	@Autowired
	TpiCountSO tpicountSO;	
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.article.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.article));
		Map<String, String> channelMap = new HashMap<>();
		for (EArticleChannel item : EArticleChannel.values()) {
			channelMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, channelMap);
		Map<String, String> typeMap1 = new HashMap<>();
		Map<String, String> typeMap2 = new HashMap<>();
		for (EMergeCaseType item : EMergeCaseType.values()) {
			typeMap1.put(item.name(), item.getName());
		}
		for (EMergeNewsType item : EMergeNewsType.values()) {
			typeMap2.put(item.name(), item.getName());
		}
		model.put(ViewKeyDict.map1, typeMap1);
		model.put(ViewKeyDict.map2, typeMap2);
		return "doarticle";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = {"ajax/article/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ArticleHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加文章
	 * @param article
	 * @param file
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(Article article, String file, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			article.setImgs(imgs);
		}
		if (article.getChannel() == null) {
			article.setChannel(EArticleChannel.mergecase);
			article.setCtype(EMergeCaseType.casestudies.name());
		}
		if (article.getCtype()==null && article.getNtype()==null) {
			article.setChannel(EArticleChannel.mergecase);
			article.setCtype(EMergeCaseType.casestudies.name());
		}
		article.setUpdatetime(DateTime.now());
		article.setCreatetime(DateTime.now());
		if (so.add(article)) {
			SerialFile(request);
			return result(MsgKeyDict.addSuccess);
		}else {
			return result(MsgKeyDict.addFailed);
		}
	}
	
	/**
	 * 保存编辑文章
	 * @param article
	 * @param file
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(Article article, String file) {
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
	 * 删除文章
	 * @param ids
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids, HttpServletRequest request) {
		if(so.delete(ids)){
			wishSO.deleteByTouid(ids);
			tpicountSO.deleteByRefuid(ids);
			SerialFile(request);
			return success();
		}else{
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
	/**
	 * 设置是否置顶
	 * @param ids
	 * @param istop
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/istop/{istop:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_settop(String[] ids, @PathVariable int istop) {
		if (so.setTop(ids, istop==0 ? false : true)){
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 设置是否公开
	 * @param ids
	 * @param ispublic
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/ispublic/{ispublic:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_setpublic(String[] ids, @PathVariable int ispublic) {
		if (so.setPublic(ids, ispublic==0 ? false : true)){
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 更新状态
	 * @param ids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/article/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> debate_status(String[] ids, @PathVariable int status, HttpServletRequest request) {
		if (so.setStatus(ids, EStatus.get(status))) {
			SerialFile(request);
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 重新生成文件
	 * @author liushaomin
	 */
	private void SerialFile(HttpServletRequest request){
		try {
			FileUtil.delete(SysConf.FileSiteBasePath + SysConf.FileArticlePath);
			List<Article> mergecase = so.getList(EArticleChannel.mergecase, new Pager(1, WebConf.OfficialIndexTpiarticleSize));
			List<Article> mergenews = so.getList(EArticleChannel.mergenews, new Pager(1, WebConf.OfficialIndexTpiarticleSize));
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			model.put(ViewKeyDict.list, mergecase);
			model.put(ViewKeyDict.list2, mergenews);
			model.put(ViewKeyDict.baseTpiPath, WebConf.baseTpiPath);
			model.put(ViewKeyDict.baseOfficialImg, WebConf.baseOfficialImg);
			File file = new File(SysConf.FileSiteBasePath + SysConf.FileArticlePath);
			FreemarkerUtil.process(SysConf.OfficialIndexTemplatePath, SysConf.HtmlArticleData, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
