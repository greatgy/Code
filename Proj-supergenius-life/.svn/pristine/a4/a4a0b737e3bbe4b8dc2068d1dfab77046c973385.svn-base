package com.supergenius.web.front.life.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.ArticleHP;
import com.supergenius.web.front.life.helper.CollectHP;
import com.supergenius.web.front.life.helper.CommentsHP;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.ECollectType;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 首页controller
 * 
 * @author: ChenQi
 * @date 2017年11月14日10:24:34
 */
@Controller
public class ArticleDetailController extends BaseController {

	@Autowired
	CatalogueSO catalogueSO;
	/**
	 * 文章查看详情页面
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:41:09
	 */
	@RequestMapping(value = "/article/{cid:\\d+}/{oid:\\d+}", method = RequestMethod.GET)
	public String article_detial(Map<String, Object> model, String commentuid, @PathVariable long cid, @PathVariable int oid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		Article article = ArticleHP.getArticle(oid);
		ArticleHP.setSummary(article);
		if (user != null) {// 设置是否赞过
			if (CommentsHP.isPrise(article.getUid(), user.getUid())) {
				article.setIsprize(true);
			}
			article.setIscollect(CollectHP.isCollect(user.getUid(), article.getUid(), ECollectType.article));// 是否收藏过
		} else {
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (CommentsHP.isPrise(article.getUid(), visitor.getUid())) {
				article.setIsprize(true);
			}
		}
		ArticleHP.incrClickCount(request, response, article.getUid());// 增加计数
		article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
		article.setCollectcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.collectcount));
		article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
		article.setCommentscount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.commentscount));
		//Pager pager = Pager.getNewInstance(0, SysConf.HotArticleSize);
		if (ECatalogue.get(cid) == ECatalogue.travel) {
			List<Article> articleList =  ArticleHP.getRelatePlaceArticle(article);
			model.put(ViewKeyDict.relateArticleList, articleList);
		} else {
			List<Article> recommendList = ArticleHP.getRecommendArticle(article.getUid());
			//List<Article> articleList = so.getRelatecarticleList(pager, article.getCid(), article.getUseruid());
			if (recommendList.size() > 0) {
				model.put(ViewKeyDict.relateArticleList, recommendList);
			} else {
				model.put(ViewKeyDict.hotArticleList, ArticleHP.getHotArticles(SysConf.HotArticleSize, article.getCid()));// 获得热门文章
			}
		}
		model.put(ViewKeyDict.bean, article);
		model.put(ViewKeyDict.cid, cid);
		Catalogue catalogue = catalogueSO.getOneByCid(cid);
		model.put(ViewKeyDict.catalogue, catalogue);
		model.put(ViewKeyDict.pcid, catalogue.getPcid());
		model.put(ViewKeyDict.commentuid, commentuid);
		return "articledetail";
	}
	
}
