package com.supergenius.web.admin.moralnews.helper;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.*;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.core.search.engine.SearchEngine;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.moralnews.entity.Article;
import com.supergenius.xo.moralnews.entity.Catalogue;
import com.supergenius.xo.moralnews.enums.ECatalogue;
import com.supergenius.xo.moralnews.rule.MoralnewsArticleRlue;
import com.supergenius.xo.moralnews.rule.MoralnewsFirstArticleRule;
import com.supergenius.xo.moralnews.rule.MoralnewsTopArticleRule;
import com.supergenius.xo.moralnews.service.ArticleSO;
import com.supergenius.xo.moralnews.service.CatalogueSO;
import com.supergenius.xo.user.service.UserSO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章管理HP
 * 
 * @author ChenQi
 * @date 2018年1月5日20:00:37
 */
public class MoralnewsArticleHP extends BaseHP {
	private static Logger log = LoggerFactory.getLogger(MoralnewsArticleHP.class);

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static SearchEngine engine;
	private static UserSO userSO;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("moralnewsEngine");
		}
		return engine;
	}

	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<String, Object> model, String channel) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (Integer.valueOf(model.get(ViewKeyDict.status).toString()) == 10) {
				map.put(MapperDict.createtime_gt, new DateTime(DateTimeZone.forOffsetHours(8)));
			} else {
				map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(ViewKeyDict.cid));
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		if (channel.equals("moralnewsarticle")){
			map.put(MapperDict.kind_article,true);
		}else if (channel.equals("moralnewssimplearticle")){
			map.put(MapperDict.kind_simplearticle,true);
		}
		map.put(MapperDict.createtime_le, true);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Article> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:00:43
	 */
	public static List<Article> getNewList(List<Article> list) {
		List<Article> newList = new ArrayList<>();
		Boolean flag = false; // 定义标志位，用于判断是否存在冻结模块的文章
		for (Article article : list) {
			flag = false;
			List<Catalogue> cataloguelist = getStatusList(EStatus.disable);
			for (Catalogue catalogue : cataloguelist) {
				if ((article.getCid() & catalogue.getCid()) == catalogue.getCid()) { // 当文章在被冻结的模块中~
					flag = true;
					break;
				}
			}
			if (!flag) { // 当文章在未被冻结的模块中~
				if (StrUtil.isNotEmpty(article.getAdminuid())) {
					Admin admin = getAdminSO().get(article.getAdminuid());
					if (admin != null) {
						article.setAdminname(admin.getName());
					}
				}
				newList.add(article);
			}
		}
		return newList;
	}

	/**
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:00:47
	 */
	public static List<Catalogue> getStatusList(EStatus status) {
		List<Catalogue> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		list = getCatalogueSO().getList(map);
		if (list != null) {
			return list;
		}
		return null;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:00:50
	 */
	public static int getTopArticleCount(int cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, 1); // 表示置顶
		map.put(MapperDict.cid, cid); // 表示置顶
		List<Article> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 获取推荐的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:00:54
	 */
	public static int getRecommendArticleCount(int cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.isrecommend, 1); // 表示推荐
		map.put(MapperDict.cid, cid);
		List<Article> list = getSO().getList(map);
		return getNewList(list).size();
	}

	/**
	 * 文章初始化全站索引
	 * 
	 * @author ChenQi
	 * @createtime 2018年1月5日20:00:57
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.moralnews.name());
		delmap.put("status", EStatus.enable);
		getEngine().delete(delmap);
		Map<String, Object> countMap = getParamMap();
		int count = getSO().getCount(countMap);
		int totalPage = (count - 1) / 100 + 1;
		for (int i = 1; i <= totalPage; i++) {
			countMap = getParamMap(Pager.getNewInstance(i, 100));
			List<Article> list = getSO().getList(countMap);
			List<Map<String, Object>> maps = new ArrayList<>();
			for (Article item : list) {
				item.setContent(WebUtil.clearHtmlTag(item.getContent()).toString());
				Map<String, Object> map = MapsUtil.toMap(item, Maps.searchStrategy);
				map.put(MapperSearchDict.table, ESite.moralnews.name());
				maps.add(map);
			}
			if (maps.size() > 0) {
				getEngine().addBatch(maps);
			}
		}
	}

	/**
	 * 添加或编辑或删除文章后清理缓存
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:01:05
	 */
	public static void Cache(Article article) {
		Rule indexrule = new MoralnewsFirstArticleRule(String.valueOf(ECatalogue.index));//清除首页文章缓存
		MemcacheUtil.remove(indexrule);
		Rule bannerrule = new MoralnewsTopArticleRule(String.valueOf(article.getCid()));// 模块的轮播缓存
		MemcacheUtil.remove(bannerrule);
		Rule Firstrule = new MoralnewsFirstArticleRule();// 首页左侧缓存
		MemcacheUtil.remove(Firstrule);
		Rule cidrule = new MoralnewsArticleRlue(String.valueOf(article.getCid()));// 模块下的所有文章
		MemcacheUtil.remove(cidrule);
		Rule allrule = new MoralnewsArticleRlue(String.valueOf(0));// 所有文章的缓存
		MemcacheUtil.remove(allrule);
	}

	/**
	 * 根据channel获得cid
	 * 
	 * @param channel
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:01:16
	 */
	public static int getcid(String channel) {
		int cid = 10;
		switch (channel) {
		case "economics":
			cid = 1;
			break;
		case "invest":
			cid = 2;
			break;
		case "merger":
			cid = 4;
			break;
		case "risk":
			cid = 8;
			break;
		case "technology":
			cid = 16;
			break;
		case "gold":
			cid = 32;
			break;
		case "entrepreneur":
			cid = 64;
			break;
		case "profession":
			cid = 128;
			break;
		case "nightwords":
			cid = 256;
			break;
		default:
			break;
		}
		return cid;
	}

	/**
	 * 发布文章
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static boolean publish() {
		Map<String, Object> map = getParamMap();
		Map<String, Object> listmap = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.createtime_le, new DateTime(DateTimeZone.forOffsetHours(8)));
		map.put(MapperDict.initstatus, EStatus.init);
		map.put(MapperDict.createtime, new DateTime(DateTimeZone.forOffsetHours(8)));
		listmap.put(MapperDict.createtime_le, new DateTime(DateTimeZone.forOffsetHours(8)));
		listmap.put(MapperDict.initstatus, EStatus.init);
		List<Article> list = getSO().getList(listmap);
		if (getSO().updateFields(map)) {
			Map<String, Object> solrMap = null;
			for (Article article : list) {
				Cache(article);
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.moralnews.name());
				getEngine().add(solrMap);
			}
			log.debug("moralnewsArticle publish is true");
			return true;
		}
		log.debug("moralnewsArticle publish is false");
		return false;
	}
	
	/**
	 * 处理上传的图片
	 * 
	 * @param fileimg
	 * @param imgUploadBasePath
	 * @author tf
	 * @return
	 */
	public static String resizeImage(MultipartFile fileimg, String path, int[][] imgsizes, String imgUploadBasePath) {
		String[] paths = FileUtil.resizeImage(fileimg, path, imgsizes, imgUploadBasePath);
		String data = null;
		if (path.length() != 0) {
			data = paths[0] + BaseStrDict.comma + paths[1] + BaseStrDict.comma + paths[2] + BaseStrDict.comma + paths[3];
		}
		return data;
	}
}
