package com.supergenius.web.admin.career.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
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
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.core.rule.CareerArticleRlue;
import com.supergenius.core.rule.CareerExperienceRule;
import com.supergenius.core.rule.CareerLeftArticleRule;
import com.supergenius.core.rule.CareerTopArticleRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.career.util.ArticleRedisUtil;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.career.entity.Article;
import com.supergenius.xo.career.entity.Catalogue;
import com.supergenius.xo.career.enums.ECatalogue;
import com.supergenius.xo.career.enums.ETop;
import com.supergenius.xo.career.service.ArticleSO;
import com.supergenius.xo.career.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;

/**
 * 文章管理HP
 * 
 * @author 杨光
 * @date 2017年11月14日14:25:13
 */
public class CareerArticleHP extends BaseHP {
	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static SearchEngine engine;

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
			engine = (SearchEngine) spring.getBean("careerEngine");
		}
		return engine;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 杨光
	 */
	public static Map<String, Object> query(Map<String, Object> model, ECatalogue catalogue) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (Integer.valueOf(model.get(ViewKeyDict.status).toString()) == 10) {
				map.put(MapperDict.createtime_gt, new DateTime(DateTimeZone.forOffsetHours(8)));
			}else {
				map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} 
		if (catalogue == ECatalogue.guide) {
			Integer[] cids = new Integer[]{21,22,23};
			List<Integer> listcid = java.util.Arrays.asList(cids);
			map.put(MapperDict.cidlist, listcid);
		}else {
			map.put(MapperDict.cid, catalogue);
		}
		map.put(MapperDict.type, true);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.createtime_le, true);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		for (Article article:list) {
			article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
			article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日12:10:33
	 */
	public static List<Article> getNewList(List<Article> list) {
		List<Article> newList = new ArrayList<>();
		Boolean flag = false; // 定义标志位，用于判断是否存在冻结模块的文章
		for (Article article : list) {
			flag = false;
			List<Catalogue> cataloguelist = getStatusList(EStatus.disable);
			for (Catalogue catalogue : cataloguelist) {
				if (article.getCid() == catalogue.getCid()) { // 当文章在被冻结的模块中~
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
				Catalogue catalogue = getCatalogueSO().getOneByCid(article.getCid());
				article.setCataloguename(catalogue.getName());
				newList.add(article);
			}
		}
		return newList;
	}

	/**
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日14:13:52
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
	 * 获取编辑文章时显示的模块
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年11月14日16:44:00
	 */
	public static List<Catalogue> getCatalogueList() {
		List<Catalogue> list = new ArrayList<>();
		int[] cids={11,12,13,15,21,22,23};
		Catalogue catalogue;
		for (int i = 0; i < cids.length; i++) {
			String path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.Separator_Directory
					+ cids[i];
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				catalogue = SerialUtil.deserializeFromJson(path, Catalogue.class, Json.cacheStrategy);
				list.add(catalogue);
			}
		}
		return list;
	}
	
	/**
	 * 获取专题的模块
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年11月14日16:44:00
	 */
	public static List<Catalogue> getGuideList() {
		List<Catalogue> list = new ArrayList<>();
		int[] cids={21,22,23};
		Catalogue catalogue;
		for (int i = 0; i < cids.length; i++) {
			catalogue = getCatalogueSO().getOneByCid(cids[i]);
			if(catalogue.getStatus() == EStatus.enable){
				list.add(catalogue);
			}
		}
		return list;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日14:12:33
	 */
	public static int getTopArticleCount(ECatalogue catalogue) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, ETop.istop); // 表示置顶
		map.put(MapperDict.cid, catalogue); // 表示置顶
		List<Article> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 文章初始化全站索引
	 * 
	 * @author 杨光
	 * @createtime 2017年12月18日10:54:04
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.career.name());
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
				map.put(MapperSearchDict.table, ESite.career.name());
				maps.add(map);
			}
			if (maps.size() > 0)
				getEngine().addBatch(maps);
		}
	}
	
	/**
	 * 文章有变更时处理缓存
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年12月4日17:06:02
	 */
	public static void handleCache(int cid) {
		Rule rule = new CareerArticleRlue(String.valueOf(cid));// 清空相应cid下的文章缓存
		MemcacheUtil.remove(rule);
		Rule careerTopArticlerule = new CareerTopArticleRule(String.valueOf(cid));// 清空相应cid下的轮播文章缓存
		MemcacheUtil.remove(careerTopArticlerule);
		Rule careerLeftArticleRule = new CareerLeftArticleRule(String.valueOf(cid));//清空相应cid下的初次加载文章缓存
		MemcacheUtil.remove(careerLeftArticleRule);
		Rule careerExperienceRule = new CareerExperienceRule();//清空首页心得文章
		MemcacheUtil.remove(careerExperienceRule);
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
		map.put(MapperDict.createtime, new DateTime(DateTimeZone.forOffsetHours(8)));
		map.put(MapperDict.initstatus, EStatus.init);
		listmap.put(MapperDict.createtime_le, new DateTime(DateTimeZone.forOffsetHours(8)));
		listmap.put(MapperDict.initstatus, EStatus.init);
		List<Article> list = getSO().getList(listmap);
		if (getSO().updateFields(map)) {
			Map<String, Object> solrMap = null;
			for(Article article :list){
				handleCache(article.getCid());
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.career.name());
				getEngine().add(solrMap);
			}
			log.debug("careerArticle publish is true");
			return true;
		}
		log.debug("careerArticle publish is false");
		return false;
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
