package com.supergenius.web.admin.startup.helper;

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
import com.supergenius.core.rule.StartupArticleListRule;
import com.supergenius.core.rule.StartupLatestArticleRule;
import com.supergenius.core.rule.StartupRecoomendListRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.entity.Article;
import com.supergenius.xo.startup.entity.Catalogue;
import com.supergenius.xo.startup.entity.Label;
import com.supergenius.xo.startup.service.ArticleSO;
import com.supergenius.xo.startup.service.CatalogueSO;
import com.supergenius.xo.startup.service.LabelSO;

/**
 * 文章管理HP
 * 
 * @author 许志翔
 * @date 2017年8月23日19:11:38
 */
public class StartupArticleHP extends BaseHP {

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static SearchEngine engine;
	private static LabelSO labelSO;

	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

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
			engine = (SearchEngine) spring.getBean("startupEngine");
		}
		return engine;
	}

	public static LabelSO getLabelSO() {
		if (labelSO == null) {
			labelSO = (LabelSO) spring.getBean(LabelSO.class);
		}
		return labelSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 许志翔
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.createtime_le, true);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Article> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		result.put(ViewKeyDict.time, new DateTime(DateTimeZone.forOffsetHours(8)).toString(DateUtil.FORMAT_DATETIME_CHINA));
		return result;
	}

	/**
	 * 获取catalogue所有子模块
	 * 
	 * @return
	 * @author 许志翔
	 * @date 2017年8月24日11:49:41
	 */
	public static List<Catalogue> getCatelogueList() {
		List<Catalogue> list = new ArrayList<>();
		List<Catalogue> result = new ArrayList<>();
		list = getCatalogueSO().getList();
		if (list != null) {
			for (Catalogue catalogue : list) {
				if (catalogue.getPcid() != 0) {// 判断该模块是否是子模块
					result.add(catalogue);
				}
			}
		}
		return result;
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author 许志翔
	 * @date 2017年8月28日11:55:39
	 */
	public static List<Article> getNewList(List<Article> list) {
		List<Article> newList = new ArrayList<>();
		Boolean flag = null; // 定义标志位，用于判断是否存在冻结模块的文章
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
	 * @author 许志翔
	 * @date 2017年8月25日17:28:31
	 */
	public static List<Catalogue> getStatusList(EStatus status) {
		List<Catalogue> list = new ArrayList<>();
		List<Catalogue> result = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		list = getCatalogueSO().getList(map);
		if (list != null) {
			for (Catalogue catalogue : list) {
				if (catalogue.getPcid() != 0) {// 判断该模块是否是子模块
					result.add(catalogue);
				}
			}
		}
		return result;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author 许志翔
	 * @date 2017年8月28日11:47:41
	 */
	public static int getTopArticleCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, 1); // 表示置顶
		map.put(MapperDict.status, EStatus.enable);
		List<Article> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 文章初始化全站索引
	 * 
	 * @author 许志翔
	 * @createtime 2017年8月30日19:04:43
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.startup.name());
		getEngine().delete(delmap);
		Map<String, Object> countMap = new HashMap<>();
		countMap.put(MapperDict.status, EStatus.enable);
		int count = getSO().getCount(countMap);
		int totalPage = (count - 1) / 100 + 1;
		for (int i = 1; i <= totalPage; i++) {
			countMap = getParamMap(Pager.getNewInstance(i, 100));
			countMap.put(MapperDict.status, EStatus.enable);
			List<Article> list = getSO().getList(countMap);
			List<Map<String, Object>> maps = new ArrayList<>();
			for (Article item : list) {
				item.setContent(WebUtil.clearHtmlTag(item.getContent()).toString());
				Map<String, Object> map = MapsUtil.toMap(item, Maps.searchStrategy);
				map.put(MapperSearchDict.table, ESite.startup.name());
				maps.add(map);
			}
			if (maps.size() > 0)
				getEngine().addBatch(maps);
		}
	}

	/**
	 * 给文章添加标签
	 * 
	 * @param article
	 * @return
	 * @author 许志翔
	 * @date 2017年8月31日17:56:23
	 */
	public static Boolean addTags(Article article) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		List<Label> list = getLabelSO().getList(map); // 得到所有标签
		if (list != null) {
			String articleContent = article.getContent() + article.getTitle();
			String labelContent;
			for (Label label : list) {
				labelContent = label.getContent();
				if (StrUtil.isNotEmpty(labelContent) & StrUtil.isNotEmpty(articleContent) & articleContent.indexOf(label.getContent()) != -1) {
					label.setRefuid(label.getRefuid() + ViewKeyDict.comma + article.getUid());
					label.setCount(label.getCount() + SysConf.RelateArticle);
					if (!getLabelSO().update(label)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 添加或解冻标签时对已有文章进行匹配
	 * 
	 * @param label
	 * @return
	 * @author yangguang
	 * @date 2017年9月6日11:26:59
	 */
	public static void addTags(Label label) {
		Map<String, Object> map = getParamMap();
		List<Article> list = getSO().getList(map);
		String labelContent;
		for (Article item : list) {
			String articleContent = item.getContent() + item.getTitle();
			if (articleContent.indexOf(label.getContent()) != -1) {
				labelContent = label.getContent();
				if (StrUtil.isNotEmpty(label.getRefuid())) {
					if (StrUtil.isNotEmpty(labelContent) & StrUtil.isNotEmpty(articleContent) & label.getRefuid().indexOf(item.getUid()) == -1) {
						label.setRefuid(label.getRefuid() + ViewKeyDict.comma + item.getUid());
					}
				} else {
					label.setRefuid(label.getRefuid() + ViewKeyDict.comma + item.getUid());
				}
				label.setCount(label.getCount() + SysConf.RelateArticle);
				getLabelSO().update(label);
			}
		}
	}

	/**
	 * 从缓存中获取文章
	 * 
	 * @param rule
	 * @return
	 * @author ShangJianguo
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
			Catalogue catalougue = null;
			Rule rule = new StartupArticleListRule(ViewKeyDict.all);
			Rule allArticleRule = new StartupArticleListRule(ViewKeyDict.all);// 清空左侧文章缓存
			MemcacheUtil.remove(allArticleRule);
			Rule firstCatalogueRule = null;
			Rule secondCatalogueRule = null;
			Rule recommendRule = new StartupRecoomendListRule();
			Rule latestrule = new StartupLatestArticleRule();// 清空快讯文章
			MemcacheUtil.remove(latestrule);
			// 清空置顶文章缓存
			MemcacheUtil.remove(recommendRule);
			List<Article> allArticleList = new ArrayList<>();
			for (Article article : list) {
				catalougue = catalogueSO.get(String.valueOf(article.getCid()));
				allArticleList = StartupArticleHP.getArticleListFromMC(rule);
				if (allArticleList != null) {
					article.setContent("");
					allArticleList.add(article);
					MemcacheUtil.set(rule, allArticleList);
				}
				firstCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getPcid()));// 清空一级目录文章缓存
				MemcacheUtil.remove(firstCatalogueRule);
				secondCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getCid()));// 清空二级目录文章缓存
				MemcacheUtil.remove(secondCatalogueRule);
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.startup.name());
				getEngine().add(solrMap);
			}
			String path = SysConf.SerialBasePath + SysConf.SerialArticlePath + SysConf.Separator_Directory;
			File file = new File(path);
			if (file.exists()) {
				File f = new File(path);
				String[] files = f.list();
				for (int a = 0; a < files.length; a++) {
					files[a] = path + files[a];
				}
				FileUtil.delete(files);
			} else {
				file.mkdirs();
			}
			log.debug("startupArticle publish is true");
			return true;
		}
		log.debug("startupArticle publish is false");
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
