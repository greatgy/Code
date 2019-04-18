package com.supergenius.web.front.life.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.MongoConf;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.common.helper.RecommendHP;
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Travel;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.rule.LifeFirstArticleRule;
import com.supergenius.xo.life.service.ArticleSO;

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
	 * @author YangGuang
	 * @date 2018年5月15日14:42:31
	 */
	public static Article getArticle(int id) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.oid, id);
		return getSO().getOne(map);
	}
	/**
	 * 通过uid获取文章
	 *
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:42:31
	 */
	public static Article getArticleOne(String id) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, id);
		return getSO().getOne(map);
	}
	/**
	 * 增加点击计数
	 * 
	 * @param articleuid
	 * @param user
	 * @return
	 * @author 2017年8月29日14:45:35
	 */
	public static void incrClickCount(HttpServletRequest request, HttpServletResponse response, String uid) {
		boolean isfirst = true;
		Cookie cookie = CookieUtil.getCookie(request, uid);
		if (cookie != null) {
			isfirst = false;
		}
		if (isfirst) {
			ArticleRedisUtil.incr(uid, ViewKeyDict.clickcount);
			CookieUtil.addCookieSess(response, uid, EChannel.finance.name());
		}
	}

	/**
	 * 获取页面首次加载文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:17:08
	 * @return List<Article>
	 */
	public static List<Article> getFirstArticles(int pagerSize) {
		Rule rule = new LifeFirstArticleRule(String.valueOf(ECatalogue.index));
		List<Article> list = getArticleListFromMC(rule);
		if (list != null && list.size() > 0) {
			initArticleList(list);
			return list;
		} else {
			Pager pager = Pager.getNewInstance(0, pagerSize);
			Map<String, Object> map = getParamMap(pager);
			map.put(MapperDict.type, EStatus.enable);
			list = getSO().getList(map);
			for (Article article : list) {
				setSummary(article);
				article.setContent("");
			}
			if (list != null && list.size() > 0) {
				MemcacheUtil.set(rule, list);
			}
		}
		initArticleList(list);
		return list;
	}
	
	/**
	 * 获取文章页面首次加载文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:17:08
	 * @return List<Article>
	 */
	public static List<Article> getOtherArticles(int pagerSize, Long cid) {
		Pager pager = Pager.getNewInstance(0, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			setSummary(article);
			article.setContent("");
		}
		initArticleList(list);
		return list;
	}
	
	/**
	 * 获取除首页的其他模块页面文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:17:08
	 * @return List<Article>
	 */
	public static List<Article> getArticleList(int pagerSize, Long cid) {
		Pager pager = Pager.getNewInstance(0, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			setSummary(article);
			article.setContent("");
		}
		initArticleList(list);
		return list;
	}
	
	/**
	 * 按原创获取文章
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author JiaShitao
	 */
	public static List<Article> getOriginalArticle(int pagenum, int pagesize) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.isoriginal, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			setSummary(article);
			article.setContent("");
		}
		initArticleList(list);
		return list;
	}

	/**
	 * 获取文章总数
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:17:08
	 * @return List<Article>
	 */
	public static int getArticleCount(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.type, EStatus.enable);
		return getSO().getCount(map);
	}

	/**
	 * 首页加载更多文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:18:12
	 * @return List<Article>
	 */
	public static List<Article> getFirstMore(int pagenum, int pagesize) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			setSummary(article);
			article.setContent("");
		}
		initArticleList(list);
		return list;
	}
	
	/**
	 * 得到官网首页文章
	 * 
	 * @author YangGuang
	 * @Datetime 2018年7月13日16:25:45
	 */
	public static List<Article> getOfficial() {
		Pager pager = Pager.getNewInstance(1, 5);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			article = setSummary(article);
		}
		initArticleList(list);
		return list;
	}

	/**
	 * 根据cid加载更多文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日12:20:56
	 * @return List<Article>
	 */
	public static List<Article> getMoreArticle(int pagenum, int pagesize, Long cid) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		initArticleList(list);
		return list;
	}

	/**
	 * 获得热门文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日11:45:54
	 * @return List<Article>
	 */
	public static List<Article> getHotArticles(int pagerSize, Long cid) {
		List<Article> hotArticleList = getHotArticleByTime(cid, 6);
		List<Article> list = new ArrayList<>();
		if (hotArticleList.size() < pagerSize) {
			hotArticleList = getHotArticleByTime(cid, 30);
		}
		initArticleList(hotArticleList);
		hotArticleList.sort(COMPARATOR);
		for (Article article : hotArticleList) {
			if (list.size() >= pagerSize) {
				break;
			}
			list.add(article);
		}
		return list;
	}

	/**
	 * 
	 * @author YangGuang
	 * @Datetime 2018年5月15日11:47:48
	 */
	public static List<Article> getHotArticleByTime(Long cid, int time) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.time, time);
		map.put(MapperDict.type, EStatus.enable);
		return getSO().getList(map);
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

	/**
	 * 为文章设置摘要
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年9月12日18:19:43
	 * @return Article
	 */
	public static Article setSummary(Article article) {
		if (StrUtil.isEmpty(article.getSummary()) && StrUtil.isNotEmpty(article.getContent())) {
			String content = StringEscapeUtils.unescapeHtml4(article.getContent());// 转义特殊字符
			StringBuffer clearHtmlContent = WebUtil.clearHtmlTag(content);
			if (!StrUtil.isEmpty(clearHtmlContent)) {
				String summary = clearHtmlContent.length() > SysConf.SummarySize ? clearHtmlContent.substring(0, SysConf.SummarySize) + "......"
						: clearHtmlContent.substring(0, clearHtmlContent.length() - 1);
				article.setSummary(StringEscapeUtils.unescapeHtml4(summary));
			}
		} else if (StrUtil.isNotEmpty(StringEscapeUtils.unescapeHtml4(article.getSummary())) && StringEscapeUtils.unescapeHtml4(article.getSummary()).length() > SysConf.SummarySize) {
			article.setSummary(StringEscapeUtils.unescapeHtml4(article.getSummary()).substring(0, SysConf.SummarySize) + "......");
		}
		return article;
	}

	/**
	 * 初始化所有文章
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月29日17:04:02
	 * @return boolean
	 */
	public static boolean initArticleList(List<Article> allArticleList) {
		boolean flag = true;
		Map<String, Object> map = new HashMap<String, Object>();
		for (Article article : allArticleList) {
			String uid = article.getUid();
			if (ArticleRedisUtil.isInit(uid)) {
				article.setClickcount(ArticleRedisUtil.getInt(uid, ViewKeyDict.clickcount));
				article.setCollectcount(ArticleRedisUtil.getInt(uid, ViewKeyDict.collectcount));
				article.setCommentscount(ArticleRedisUtil.getInt(uid, ViewKeyDict.commentscount));
				article.setPrizecount(ArticleRedisUtil.getInt(uid, ViewKeyDict.prizecount));
				article.setWeight(getWeight(uid));
			} else {
				map.clear();
				map.put(ViewKeyDict.clickcount, 0);
				map.put(ViewKeyDict.collectcount, 0);
				map.put(ViewKeyDict.commentscount, 0);
				map.put(ViewKeyDict.prizecount, 0);
				flag = ArticleRedisUtil.set(uid, map);
			}
			setSummary(article);
			article.setContent("");
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算权重值
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月29日17:25:25
	 * @return Float
	 */
	public static Float getWeight(String uid) {
		Map<String, Float> map = new HashMap<String, Float>();
		map.put(ViewKeyDict.clickcount, SysConf.ClickcountPercent);
		map.put(ViewKeyDict.collectcount, SysConf.CollectcountPercent);
		map.put(ViewKeyDict.commentscount, SysConf.CommentscountPercent);
		map.put(ViewKeyDict.prizecount, SysConf.PrizecountPercent);
		return ArticleRedisUtil.getWeight(uid, map);
	}

	private static final Comparator<Article> COMPARATOR = new Comparator<Article>() {
		public int compare(Article o1, Article o2) {
			return o1.compareTo(o2);// 运用Article类的compareTo方法比较两个对象
		}
	};

	/**
	 * 从缓存中获取文章
	 * 
	 * @param rule
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Article> getArticleListFromMC(Rule rule) {
		List<Article> list = null;
		try {
			list = (List<Article>) MemcacheUtil.get(rule);
		} catch (Exception e) {
			logException(log, e);
		}
		return list;
	}
	
	/**
	 * 获取行万里路的地图数据
	 * 
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void OrganizedCity(Map<String, Object> model,Long cid) {
		List<Map<String, Object>> chinaList = new ArrayList<>();
		List<Map<String, Object>> worldList = new ArrayList<>();
		List<Map<String, Object>> traList = new ArrayList<>();
		Map<String, Object> chinaMap = new HashMap<>();
		Map<String, Object> worldMap = new HashMap<>();
		Map<String, List<Travel>> travelMap = new HashMap<>();
		Map<String, Object> tempMap = null;
		int maxChina = 1;
		int maxWorld = 1;
		int chinaValue = 0;
		int worldValue = 0;
		String country = null;
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, ECatalogue.travel);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			if (article == null || StrUtil.isEmpty(article.getOrigin())) {
				continue;
			}
			tempMap = JsonUtil.fromJson(article.getOrigin(), Map.class);
			
			country = (String) tempMap.get("country");
			if (tempMap.containsKey("province")) {
				List<Travel> travelList = new ArrayList<>();
				String province = (String) tempMap.get("province");
				Travel travel = new Travel();
				travel.setCountry(country);
				travel.setProvince((String) tempMap.get("province"));
				travel.setCity((String) tempMap.get("name"));
				travel.setTitle(article.getTitle());
				travel.setCid(article.getCid());
				travel.setOid(article.getOid());
				if (travelMap.containsKey(province)) {
					travelList = travelMap.get(province);
				}
				travelList.add(travel);
				travelMap.put(province,travelList);
			}
			if (tempMap.containsKey("province")) {
				String province = (String) tempMap.get("province");
				if (chinaMap.containsKey(province)) {
					chinaValue = (int) chinaMap.get(province);
					chinaMap.put(province, chinaValue + 1);
					if (chinaValue + 1 > maxChina) {
						maxChina = chinaValue + 1;
					}
				} else {
					chinaMap.put(province, 1);
				}
			}
			if (worldMap.containsKey(country)) {
				worldValue = (int) worldMap.get(country);
				worldMap.put(country, worldValue + 1);
				if (worldValue + 1 > maxWorld) {
					maxWorld = worldValue + 1;
				}
			} else {
				worldMap.put(country, 1);
			}
		}
		if (travelMap != null) {
			Iterator it = travelMap.keySet().iterator();
			String key = null;
			Map<String, Object> china = null;
			while (it.hasNext()) {
				key = (String) it.next();
				china = new HashMap<>();
				china.put("name", key);
				china.put("value", travelMap.get(key));
				traList.add(china);
			}
		}
		if (chinaMap != null) {
			Iterator chinait = chinaMap.keySet().iterator();
			String chinaKey = null;
			Map<String, Object> china = null;
			while (chinait.hasNext()) {
				chinaKey = (String) chinait.next();
				china = new HashMap<>();
				china.put("name", chinaKey);
				china.put("value", chinaMap.get(chinaKey));
				chinaList.add(china);
			}
		}
		if (worldMap != null) {
			Iterator worldit = worldMap.keySet().iterator();
			String worldKey = null;
			Map<String, Object> world = null;
			while (worldit.hasNext()) {
				worldKey = (String) worldit.next();
				world = new HashMap<>();
				world.put("name", worldKey);
				world.put("value", worldMap.get(worldKey));
				worldList.add(world);
			}
		}
		
		//获得热门城市
		List<Article> placeList = ArticleHP.getArticleHot(1, SysConf.HotArticleSize, cid);
		List<String> hotplace = new ArrayList<>();
		for (Article article : placeList) {
			String json = article.getOrigin();
			map = JsonUtil.fromJson(json, Map.class);
			hotplace.add((String) map.get("name"));
		}
		model.put(ViewKeyDict.china, JsonUtil.toJson(chinaList));
		model.put(ViewKeyDict.world, JsonUtil.toJson(worldList));
		model.put(ViewKeyDict.travelList, JsonUtil.toJson(traList));
		model.put(ViewKeyDict.hotplace, hotplace);
		model.put(ViewKeyDict.maxChina, maxChina);
		model.put(ViewKeyDict.maxWorld, maxWorld);
	}
	

	/**
	 * 行万里路按热度获取
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Article> getArticleHot(int pagenum, int pagesize, Long cid) {
		String path = SysConf.SerialBasePath +ViewKeyDict.article + cid +SysConf.SerialTravelPath;
		List<Article> result = new ArrayList<>();
		List<Article> list = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
		}
		if (list.size() == 0) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.cid, cid);
			list = getSO().getList(map);
			List<Article> tempList = new ArrayList<>();
			tempList.addAll(list);
			SerialUtil.serializeToJson(tempList, path, Json.cacheStrategy);
		}
		initArticleList(list);
		list.sort(COMPARATOR);

		int maxHotsize = pagenum * pagesize;
		if (maxHotsize > list.size()) {
			maxHotsize = list.size();
		}
		if ((pagenum - 1) * pagesize < maxHotsize) {
			result = list.subList((pagenum - 1) * pagesize, maxHotsize);
		}
		return result;
	}
	
	/**
	 * 行万里路获取相同地点的文章
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author ChenQi
	 */
	@SuppressWarnings("unchecked")
	public static List<Article> getRelatePlaceArticle(Article item) {
		List<Article> relateList = new ArrayList<Article>();
		List<Article> result = new ArrayList<Article>();
		String place = (String)JsonUtil.fromJson(item.getOrigin(), Map.class).get("name");
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, ECatalogue.travel);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			String json = article.getOrigin();
			Map<String, Object> map1 = JsonUtil.fromJson(json, Map.class);
			article.setPlacename((String) map1.get("name"));
			if (((String) map1.get("name")).equals(place) && !article.getUid().equals(item.getUid())) {
				relateList.add(article);
			}
		}
		if (list.size() > 5) {
			if (relateList.isEmpty()) {
				result = list.subList(0, 5);
			} else if (relateList.size() > 5) {
				result = relateList.subList(0, 5);
			} else {
				result = relateList;
			}
		} else {
			result = list;
		}
		
		return result;
	}
	
	/**
	 * 行万里路根据省份进行获取
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author tf
	 */
	public static List<Article> getProvinceArticle(int pagenum, int pagesize, String province) {
		List<Article> result = new ArrayList<Article>();
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, ECatalogue.travel);
		map.put(MapperDict.origin_lk, province);
		result = getSO().getList(map);
		return result;
	}
	
}
