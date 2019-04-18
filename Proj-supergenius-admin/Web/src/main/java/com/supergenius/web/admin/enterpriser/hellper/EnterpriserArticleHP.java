package com.supergenius.web.admin.enterpriser.hellper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
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
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Article;
import com.supergenius.xo.enterpriser.entity.Catalogue;
import com.supergenius.xo.enterpriser.rule.EnterpriserFirstArticleRule;
import com.supergenius.xo.enterpriser.rule.EnterpriserIndexArticleRule;
import com.supergenius.xo.enterpriser.service.ArticleSO;
import com.supergenius.xo.enterpriser.service.CatalogueSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 个人文章管理HP
 * 
 * @author loupengyu
 * @date 2018年1月11日19:22:16
 */
public class EnterpriserArticleHP extends BaseHP {
	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

	private static ArticleSO so;
	private static SearchEngine engine;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static UserSO userSO;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("enterpriserEngine");
		}
		return engine;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
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
	 * @return map
	 * @author loupengyu
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.searchcid))) {
			if (!model.get(ViewKeyDict.searchcid).equals("all")) {
				map.remove(MapperDict.cids);
				map.put(MapperDict.cid, Integer.valueOf((String) model.get(ViewKeyDict.searchcid)));
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, model.get(ViewKeyDict.isrecommend));
		} else {
			map.put(MapperDict.isrecommend, null);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.createtime_le, true);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		for (Article articles : list) {
			if (StrUtil.isNotEmpty(articles.getAdminuid())) {
				Admin admin = getAdminSO().get(articles.getAdminuid());
				if (admin != null) {
					articles.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(articles.getCid())) {
				Catalogue catalogue = getCatalogueSO().get(articles.getCid());
				if (catalogue != null) {
					articles.setCataloguename(catalogue.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}

	/**
	 * 根据channel获得cid
	 * 
	 * @param channel
	 * @return
	 * @author loupengyu
	 * @date 2018年1月11日19:23:21
	 */
	public static int getcid(String channel) {
		int cid = 10;
		switch (channel) {
		case "upmadeinchina":
			cid = 3;
			break;
		case "industryfund":
			cid = 6;
			break;
		case "crossmerger":
			cid = 4;
			break;
		case "internationalmerger":
			cid = 5;
			break;
		default:
			break;
		}
		return cid;
	}

	/**
	 * 文章初始化全站索引
	 * 
	 * @author YangGuang
	 * @createtime 2018年1月13日14:33:05
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.enterpriser.name());
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
				map.put(MapperSearchDict.table, ESite.enterpriser.name());
				maps.add(map);
			}
			if (maps.size() > 0)
				getEngine().addBatch(maps);
		}
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
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.enterpriser.name());
				getEngine().add(solrMap);
			}
			log.debug("enterpriserArticle publish is true");
			return true;
		}
		log.debug("enterpriserArticle publish is false");
		return false;
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
		map.put(MapperDict.cid, cid);
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
			isMemeber(article);
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
	 * 判断是否是会员
	 * 
	 * @param content
	 * @return
	 * @author ChenQi
	 */
	public static void isMemeber(Article article) {
		if (article.getAuthoruid() == article.getAdminuid()) {
			article.setIsmember(1);
		} else {
			User user = getUserSO().get(article.getAuthoruid());
			if (user == null) {
				article.setIsmember(0);
			} else {
				article.setIsmember(1);
			}
		}
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
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月31日19:23:22
	 */
	public static List<Catalogue> getCatalogueList() {
		Map<String, Object> map = getParamMap();
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(5);
		list.add(31);
		list.add(33);
		list.add(35);
		list.add(36);
		list.add(61);
		list.add(62);
		list.add(63);
		map.put(MapperDict.cids, list);
		List<Catalogue> catelogueList = getCatalogueSO().getList(map);
		return catelogueList;
	}

	/**
	 * 文章有变化时清空缓存
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日20:01:05
	 */
	public static void Cache(int cid) {
		Rule indexRule = new EnterpriserIndexArticleRule(String.valueOf(cid));
		Rule firstRule = new EnterpriserFirstArticleRule(String.valueOf(cid));
		MemcacheUtil.remove(firstRule);
		MemcacheUtil.remove(indexRule);
		if (cid >= 61 && cid <= 63) {
			indexRule = new EnterpriserIndexArticleRule(String.valueOf(6));
			MemcacheUtil.remove(indexRule);
		}
	}
	
	/**
	 * 处理上传的图片
	 * 
	 * @param fileimg
	 * @param string
	 * @param imgshowsizes
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
