package com.supergenius.web.finance.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.finance.helper.IndexHP;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Content;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.service.CatalogueSO;

/**
 * 目录controller
 * 
 * @author XueZhenYong
 * @date 2018年1月2日下午3:26:56
 */
@Controller
public class CatalogueController {

	@Autowired
	CatalogueSO so;

	/**
	 * 跳转到相应的页面并对相应的页面展示相应的数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XueZhenYong
	 * @Datetime 2018年1月2日下午4:01:47
	 */
	@RequestMapping(value = "/catalogue/{cid:\\w+}", method = RequestMethod.GET)
	private String index(Map<String, Object> model, @PathVariable int cid, HttpServletRequest request) {
		IndexHP.handleCache();
		model.put(ViewKeyDict.list, IndexHP.getFirstPageArticles(SysConf.FirstArticleSize, cid));
		model.put(ViewKeyDict.lablelist, IndexHP.getHotLabel(SysConf.HotLabelSize));
		model.put(ViewKeyDict.HotArticleList, IndexHP.getHotArticles(SysConf.HotArticleSize, cid));
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.pagesize, SysConf.FirstLoadSize);
		model.put(ViewKeyDict.FirstLoadSize, SysConf.FirstArticleSize);
		model.put(ViewKeyDict.catalogue, so.get(cid));
		List<Content> ADContent = IndexHP.getADContent(EContent.get(cid));
		model.put(ViewKeyDict.ADContent, ADContent);
		return "economic";
	}

	/**
	 * 除首页的页面的下拉加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @param cid
	 * @return
	 * @author 雍雪振
	 */
	@RequestMapping(value = "/ajax/otherpage/catalogue/{cid:\\w+}", method = RequestMethod.GET)
	public String ajax_firstArticle(Map<String, Object> model, int pagenum, @PathVariable int cid, HttpServletRequest request) {
		List<Article> list = IndexHP.getOtherPagesMore(pagenum, cid, SysConf.FirstLoadSize);
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "ajaxarticle";
	}
	
	/**
	 * 除首页的页面的加载文章
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @param cid
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/article/catalogue/{cid:\\w+}", method = RequestMethod.GET)
	public String ajax_Article(Map<String, Object> model, int pagesize, int pagenum, @PathVariable int cid, HttpServletRequest request) {
		List<Article> list = IndexHP.getPagesMore(pagenum, cid, pagesize);
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "ajaxarticle";
	}

}
