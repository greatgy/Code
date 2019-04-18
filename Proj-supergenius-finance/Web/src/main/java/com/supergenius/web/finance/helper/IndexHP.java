package com.supergenius.web.finance.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Catalogue;
import com.supergenius.xo.finance.entity.Content;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.enums.ECatalogue;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.rule.FinanceArticleRlue;
import com.supergenius.xo.finance.rule.FinanceFirstArticleRule;
import com.supergenius.xo.finance.rule.FinanceTopArticleRule;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CatalogueSO;
import com.supergenius.xo.finance.service.ContentSO;
import com.supergenius.xo.finance.service.LabelSO;

public class IndexHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(IndexHP.class);

	private static ArticleSO so;
	private static CatalogueSO catalogueso;
	private static ContentSO contentso;
	private static LabelSO labelso;
	private static DateTime cacheTime;

	public static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	public static CatalogueSO getCatalogueSO() {
		if (catalogueso == null) {
			catalogueso = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueso;
	}

	public static ContentSO getContentSO() {
		if (contentso == null) {
			contentso = (ContentSO) spring.getBean(ContentSO.class);
		}
		return contentso;
	}

	public static LabelSO getLabelSO() {
		if (labelso == null) {
			labelso = (LabelSO) spring.getBean(LabelSO.class);
		}
		return labelso;
	}

	public static int[] catalogues = { 2, 4, 8, 16, 64, 128 };

	/**
	 * 刷新缓存
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2017年10月19日10:32:21
	 * @return List<Article>
	 */
	public static void handleCache() {
		if (cacheTime == null) {
			cacheTime = getSO().getCacheTime();
		}
		if (cacheTime != null) {
			if (new DateTime(DateTimeZone.forOffsetHours(8)).getMillis() >= cacheTime.getMillis()) {
				removeCache();
				cacheTime = getSO().getCacheTime();
			}
		}
	}

	/**
	 * 清空缓存
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2017年10月19日10:32:21
	 * @return List<Article>
	 */
	public static void removeCache() {
		int[] Catalogues = { 1, 2, 4, 8, 16, 64, 128 };
		Rule bannerrule = null;
		Rule cidrule = null;
		for (int i = 0; i < Catalogues.length; i++) {
			bannerrule = new FinanceTopArticleRule(String.valueOf(Catalogues[i]));// 模块的轮播缓存
			MemcacheUtil.remove(bannerrule);
			cidrule = new FinanceArticleRlue(String.valueOf(Catalogues[i]));// 模块下的所有文章
			MemcacheUtil.remove(cidrule);
		}
		Rule Firstrule = new FinanceFirstArticleRule();// 首页左侧缓存
		MemcacheUtil.remove(Firstrule);
		Rule allrule = new FinanceArticleRlue(String.valueOf(0));// 所有文章的缓存
		MemcacheUtil.remove(allrule);
	}

	/**
	 * 得到广告位内容
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月28日15:04:02
	 * @return List<Content>
	 */
	public static List<Content> getADContent(EContent content) {
		List<Content> list = getContentSO().getADContentList(content);
		return list;
	}

	/**
	 * 得到首页的最新醒世金语
	 * 
	 * @return
	 * @author Yangguang
	 * @date 2018年1月2日16:49:04
	 * @return Article
	 */
	public static Article getGoldLanguage() {
		Map<String, Object> map = getParamMap();
		map.put(ViewKeyDict.cid, ECatalogue.gold);
		Article article = getSO().getOne(map);
		if (article != null) {
			setSummary(article);
			article.setContent("");
		}
		return article;
	}

	/**
	 * 得到轮播文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日17:00:20
	 * @return List<Article>
	 */
	public static List<Article> getBanner(int pagerSize, int cid) {
		Rule rule = new FinanceTopArticleRule(String.valueOf(cid));
		List<Article> recommendList = getArticleListFromMC(rule);
		if (recommendList != null && recommendList.size() > 0) {
			return recommendList;
		} else {
			Pager pager = Pager.getNewInstance(0, pagerSize);
			pager.setOrderBy(MapperDict.toptime);
			recommendList = getSO().getBannerList(pager, true, cid);
			if (recommendList.size() < pagerSize) {
				pager = Pager.getNewInstance(0, pagerSize - recommendList.size());
				List<Article> latestList = getSO().getBannerList(pager, false, cid);
				recommendList.addAll(latestList);
			}
			for (Article article : recommendList) {
				article.setContent("");
			}
			if (recommendList != null && recommendList.size() > 0) {
				MemcacheUtil.set(rule, recommendList);
			}
		}
		return recommendList;
	}

	/**
	 * 得到首页各版块推荐的文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日17:17:10
	 * @return List<Article>
	 */
	public static List<Article> getRecommendArticle() {
		List<Article> list = new ArrayList<>();
		List<Article> result = new ArrayList<>();
		Pager pager = Pager.getNewInstance(0, 1);
		for (int i = 0; i < catalogues.length; i++) {
			list = getSO().getRecommendList(pager, true, catalogues[i]);
			if (list.size() == 0) {
				list = getSO().getRecommendList(pager, false, catalogues[i]);
			}
			result.addAll(list);
		}
		return result;
	}

	/**
	 * 得到首页财经资讯推荐的文章
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月15日10:50:23
	 * @return List<Article>
	 */
	public static List<Article> getRecommendEconomicsArticle() {
		List<Article> list = new ArrayList<>();
		List<Article> result = new ArrayList<>();
		Pager pager = Pager.getNewInstance(0, 4);
		list = getSO().getRecommendList(pager, true, 1);
		if (list.size() < 4) {
			result.addAll(list);
			pager = Pager.getNewInstance(0, 4 - list.size());
			list = getSO().getRecommendList(pager, false, 1);
		}
		result.addAll(list);
		return result;
	}

	/**
	 * 得到首页左侧显示文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日18:02:40
	 * @return List<Article>
	 */
	public static List<Article> getIndexArticle(int pagerSize) {
		Rule rule = new FinanceFirstArticleRule();
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
	 * 得到除首页之外其他页面的第一次加载的文章
	 * 
	 * @author XueZhenYong
	 * @Datetime 2018年1月3日下午4:19:36
	 * @return List<Article> 10
	 */
	public static List<Article> getFirstPageArticles(int pagerSize, int cid) {
		Pager pager = new Pager(pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		initArticleList(list);
		return list;
	}

	/**
	 * 首页加载更多文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日18:52:39
	 * @return List<Article>
	 */
	public static List<Article> getFirstMore(int pagenum) {
		Pager pager = Pager.getNewInstance(pagenum, SysConf.FirstLoadSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
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
			article = IndexHP.setSummary(article);
		}
		initArticleList(list);
		return list;
	}

	/**
	 * 加载文章
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日18:52:39
	 * @return List<Article>
	 */
	public static List<Article> getMoreArticles(int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap();
		map.put(BaseViewKeyDict.startIndex, pagenum - 1);
		map.put(BaseMapperDict.pageSize, pagesize);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		initArticleList(list);
		return list;
	}

	/**
	 * 除首页的页面加载更多文章
	 * 
	 * @author XueZhenYong
	 * @Datetime 2018年1月3日下午6:35:15
	 */
	public static List<Article> getOtherPagesMore(int pagenum, int cid, int pagesize) {
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
	 * 得到指定区间的文章
	 * 
	 * @author YangGuang
	 * @Datetime 2018年2月23日13:48:44
	 */
	public static List<Article> getPagesMore(int pagenum, int cid, int pagesize) {
		Map<String, Object> map = getParamMap();
		map.put(BaseViewKeyDict.startIndex, pagenum - 1);
		map.put(BaseMapperDict.pageSize, pagesize);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		initArticleList(list);
		return list;
	}

	/**
	 * 得到热门文章
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月28日15:49:14
	 * @return List<Article>
	 */
	public static List<Article> getHotArticle(int pagerSize, int cid) {
		List<Article> allArticleList = getAllArticle(cid);
		List<Article> hotArticleList = new ArrayList<Article>();
		if (allArticleList.size() > pagerSize) {
			for (int i = 0; i < pagerSize; i++) {
				hotArticleList.add(allArticleList.get(i));
			}
		} else {
			hotArticleList.addAll(allArticleList);
		}
		return hotArticleList;
	}

	/**
	 * 得到其他页面的热门文章
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月28日15:49:14
	 * @return List<Article>
	 */
	public static List<Article> getHotArticles(int pagerSize, int cid) {
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
	 * @author XueZhenYong
	 * @Datetime 2018年1月22日下午2:24:36
	 */
	public static List<Article> getHotArticleByTime(int cid, int time) {
		Map<String, Object> map = getParamMap();
		if (cid > 0) {
			map.put(MapperDict.cid, cid);
		}
		map.put(MapperDict.time, time);
		map.put(MapperDict.type, EStatus.enable);
		return getSO().getList(map);
	}

	/**
	 * 根据cid得到该模块的文章， cid为空的时候，得到所有的
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2017年8月29日17:04:02
	 * @return List<Article>
	 */
	public static List<Article> getAllArticle(int cid) {
		Rule rule = new FinanceArticleRlue(String.valueOf(cid));
		List<Article> allArticleList = getArticleListFromMC(rule);
		if (allArticleList == null || allArticleList.size() == 0) {
			allArticleList = getSO().getListByCid(cid);
			for (Article article : allArticleList) {
				setSummary(article);
				article.setContent("");
			}
			if (allArticleList != null && allArticleList.size() > 0) {
				MemcacheUtil.set(rule, allArticleList);
			}
		}
		initArticleList(allArticleList);
		allArticleList.sort(COMPARATOR);
		return allArticleList;
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
	 * 得到热门标签
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年1月2日17:37:52
	 * @return List<Article>
	 */
	public static List<Label> getHotLabel(int pagerSize) {
		Pager pager = Pager.getNewInstance(0, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.count + MapperDict.desc);
		List<Label> hotLabelList = getLabelSO().getList(map);
		return hotLabelList;
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

	/**
	 * 获取二级导航模块
	 * 
	 * @author 许志翔
	 * @return List<Catalogue>
	 * @date 2017年12月4日11:35:08
	 */
	@SuppressWarnings("unchecked")
	public static List<Catalogue> getCatalogueList() {
		List<Catalogue> list = new LinkedList<>();
		Catalogue catalogue = null;
		// 从序列化文件中取得网站导航的目录(排序)
		String path = SysConf.SerialBasePath + SysConf.SerialFinanceCataloguePath + SysConf.FinanceCatalogueOrder;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
			for (String cid : index.get(MapperDict.informationList).split(MapperDict.comma)) {
				path = SysConf.SerialBasePath + SysConf.SerialFinanceCataloguePath + SysConf.Separator_Directory + cid;
				file = new File(path);
				if (file.exists() && file.isFile()) {
					catalogue = SerialUtil.deserializeFromJson(path, Catalogue.class, Json.cacheStrategy);
					list.add(catalogue);
				} else {
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.cid, cid);
					catalogue = getCatalogueSO().getOne(map);
					serializeCatalogue(catalogue);
				}
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.status, EStatus.enable);
			map.put(MapperDict.isspecial, 0);
			map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.desc);
			list = getCatalogueSO().getList(map);
			String informationList = "";
			for (Catalogue item : list) { // 将Catalogue序列化
				serializeCatalogue(item);
				informationList = informationList + item.getCid() + MapperDict.comma;
			}
			informationList = informationList.substring(0, informationList.length() - 1);
			Map<String, Object> map2 = new HashMap<>(); // 将模块排序进行序列化
			map2.put(MapperDict.informationList, informationList);
			SerialUtil.serializeToJson(map2, SysConf.SerialBasePath + SysConf.SerialFinanceCataloguePath + SysConf.FinanceCatalogueOrder);
		}
		return list;
	}

	/**
	 * 序列化模块
	 * 
	 * @param catalogue
	 */
	public static void serializeCatalogue(Catalogue catalogue) {
		String cataloguepath = SysConf.SerialBasePath + SysConf.SerialFinanceCataloguePath + SysConf.Separator_Directory + catalogue.getCid();
		File tempfile = new File(cataloguepath);
		if (!tempfile.getParentFile().exists()) {
			tempfile.getParentFile().mkdirs();
		}
		if (tempfile.exists() && tempfile.isFile()) {
			tempfile.delete();
		}
		try {
			tempfile.createNewFile();
			SerialUtil.serializeToJson(catalogue, cataloguepath, Json.cacheStrategy);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * 重新序列化官网首页天财评论的内容
	 * 
	 * @author YangGuang
	 * @Datetime 2018年2月7日16:22:27
	 */
	@SuppressWarnings("unchecked")
	public static void SerializeOfficial(Map<String, Object> map, HttpServletRequest request) {
		try {
			// 序列化最新文章
			FileUtil.delete(SysConf.FileSiteBasePath + SysConf.FileRecentPath);
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			List<Article> list = (List<Article>) map.get(ViewKeyDict.list);
			model.put(ViewKeyDict.list, list.subList(0, list.size() > SysConf.OfficialIndexFinanceSize ? SysConf.OfficialIndexFinanceSize : list.size()));
			model.put(ViewKeyDict.baseFinancePath, WebConf.baseRootPath);
			File file = new File(SysConf.FileSiteBasePath + SysConf.FileRecentPath);
			FreemarkerUtil.process(SysConf.OfficialIndexTemplatePath, SysConf.HtmlRecentData, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重新序列化会员中心AI的内容
	 * 
	 * @author ChenQi
	 * @Datetime 2018年3月27日18:36:34
	 */
	@SuppressWarnings("unchecked")
	public static void SerializeFinanceArticle(Map<String, Object> map, HttpServletRequest request) {
		try {
			// 序列化最新文章
			File file = new File(SysConf.FileSiteBasePath + SysConf.SerialUserFinanceArticlePath);
			if (file.exists()) {
				FileUtil.delete(SysConf.FileSiteBasePath + SysConf.SerialUserFinanceArticlePath);
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			List<Article> list = (List<Article>) map.get(ViewKeyDict.list);
			model.put(ViewKeyDict.list, list.subList(0, list.size() > SysConf.SerialUserFinanceArticleNum ? SysConf.SerialUserFinanceArticleNum : list.size()));
			model.put(ViewKeyDict.baseFinancePath, WebConf.baseRootPath);
			file = new File(SysConf.FileSiteBasePath + SysConf.SerialUserFinanceArticlePath);
			FreemarkerUtil.process(SysConf.UserIndexTemplatePath, SysConf.HtmlNewData, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}