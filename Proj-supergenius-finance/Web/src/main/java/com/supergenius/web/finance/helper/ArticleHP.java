package com.supergenius.web.finance.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.CookieUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.MongoConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.common.helper.RecommendHP;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.service.ArticleSO;

/**
 * 文章HP
 * 
 * @author yangguang
 * @date 2017年8月29日14:43:02
 */
public class ArticleHP extends BaseHP {
	
	private static Logger log = LoggerFactory.getLogger(ArticleHP.class);

	private static ArticleSO so;

	public static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}
	
	/**
	 * 通过oid获取文章
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2017年12月1日16:58:59
	 */
	public static Article getArticle(int id) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.oid, id);
			return getSO().getOne(map);
	}

	/**
	 * 增加点击计数
	 * 
	 * @param articleuid
	 * @param user
	 *            点击人信息
	 * @return
	 * @author 2017年8月29日14:45:35
	 */
	public static void incrClickCount(HttpServletRequest request, HttpServletResponse response, Article article) {
		boolean isfirst = true;
		Cookie cookie = CookieUtil.getCookie(request, article.getUid());
		if (cookie != null) {
			isfirst = false;
		}
		if (isfirst) {
			ArticleRedisUtil.incr(article.getUid(), ViewKeyDict.clickcount);
			CookieUtil.addCookieSess(response, article.getUid(), EChannel.finance.name());
		}
	}
	
	/**
	 * 从缓存中获取文章
	 * @param rule
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Article> getArticleListFromMC(Rule rule){
		List<Article> list = null;
		try {
			list = (List<Article>)MemcacheUtil.get(rule);
		} catch (Exception e) {
			logException(log, e);
		}
		return list;
	}
	
	/**
	 * 获取他的主页文章
	 * 
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年9月20日18:07:12
	 */
	public static List<Article> getHisArticle(String uid, int pagenum, int pagesize, HttpServletRequest request) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.authoruid, uid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			if (article != null) {
				IndexHP.setSummary(article);
				article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
				article.setCollectcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.collectcount));
				article.setCommentscount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.commentscount));
				article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
			}
		}
		return list;
	}
	
	/**
	 * 他的文章数量
	 * 
	 * @param uid
	 * @return
	 * @author 杨光
	 */
	public static int getHisCount(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(ViewKeyDict.authoruid, uid);
		map.put(ViewKeyDict.type, EStatus.enable);
		return getSO().getCount(map);
	}
	
	/**
	 * 获得相关文章
	 * 
	 * @param articleuid
	 * @return
	 * @author ChenQi
	 * @date 2018年8月23日12:32:28
	 */
	public static List<Article> getRecommendArticle(String articleUid) {
		List<Article> recommendArticle = new ArrayList<Article>();
		Map<String, Object> map = getParamMap();
		List<String> uidList = RecommendHP.getUids(MongoConf.Host, MongoConf.Port, MongoConf.DBbase, MongoConf.Collection, articleUid);
		if (uidList != null && uidList.size() > 0) {
			map.put(MapperDict.uidlist, uidList);
			recommendArticle = so.getList(map);
		}
		return recommendArticle;
	}
}
