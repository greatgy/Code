package com.supergenius.web.admin.life.helper;

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
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.ECatalogueType;
import com.supergenius.xo.life.rule.LifeFirstArticleRule;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 天才人生文章管理HP
 * 
 * @author JiaShitao
 * @date 2018年5月10日18:22:16
 */
public class LifeArticleHP extends BaseHP {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static UserSO userSO;
	private static SearchEngine engine;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
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

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("lifeEngine");
		}
		return engine;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return map
	 * @author JiaShitao
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(ViewKeyDict.cid));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
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
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月11日20:00:50
	 */
	public static int getTopArticleCount(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, 1); // 表示置顶
		map.put(MapperDict.cid, cid);
		List<Article> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月11日20:00:43
	 */
	public static List<Article> getNewList(List<Article> list) {
		List<Article> newList = new ArrayList<>();
		Boolean flag = false; // 定义标志位，用于判断是否存在冻结模块的文章
		for (Article article : list) {
			// isMemeber(article);
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
	 * @author JiaShitao
	 * @date 2018年5月11日20:00:47
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
	 * @author JiaShitao
	 * @date 2018年5月11日19:23:22
	 */
	public static List<Catalogue> getCatalogueList() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, ECatalogueType.article);
		List<Catalogue> list = getCatalogueSO().getList(map);
		return list;
	}

	/**
	 * 文章有变化时清空缓存
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月22日15:10:15
	 */

	public static void Cache(Long cid) {
		Rule indexRule = new LifeFirstArticleRule(String.valueOf(ECatalogue.index));
		MemcacheUtil.remove(indexRule);
		Rule firstRule = new LifeFirstArticleRule(String.valueOf(cid));
		MemcacheUtil.remove(firstRule);
		FileUtil.delete(SysConf.SerialBasePath + ViewKeyDict.article + cid + SysConf.SerialTravelPath);
	}
	

	/**
	 * 文章初始化全站索引
	 * 
	 * @author YangGuang
	 * @createtime 2018年6月6日15:33:23
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.life.name());
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
				map.put(MapperSearchDict.table, ESite.life.name());
				maps.add(map);
			}
			if (maps.size() > 0)
				getEngine().addBatch(maps);
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
