package com.supergenius.web.admin.ai.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
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
import com.supergenius.core.rule.AiArticleRlue;
import com.supergenius.core.rule.AiLeftArticleRule;
import com.supergenius.core.rule.AiTopArticleRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.ai.entity.Article;
import com.supergenius.xo.ai.entity.Catalogue;
import com.supergenius.xo.ai.entity.Label;
import com.supergenius.xo.ai.enums.ETop;
import com.supergenius.xo.ai.service.ArticleSO;
import com.supergenius.xo.ai.service.CatalogueSO;
import com.supergenius.xo.ai.service.LabelSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;

/**
 * 文章管理HP
 * 
 * @author 杨光
 * @date 2017年9月19日11:52:19
 */
public class AiArticleHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(AiArticleHP.class);

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static SearchEngine engine;
	private static LabelSO labelSO;

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
			engine = (SearchEngine) spring.getBean("aiEngine");
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
	 * @author 杨光
	 */
	public static Map<String, Object> query(Map<String, Object> model, EStatus catagory) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		map.put(MapperDict.catagory, catagory);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Article> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
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
				Catalogue catalogue = getCatalogueSO().get(article.getCid());
				article.setCataloguename(catalogue.getName());
				newList.add(article);
			}
		}
		return newList;
	}

	/**
	 * 编辑文章时处理缓存
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年10月17日15:59:15
	 */
	public static void handleEditCache(Article article, int oid) {
		Rule firstRule = new AiArticleRlue(SysConf.HomeCid);// 清空首页导航下的所有文章
		MemcacheUtil.remove(firstRule);
		Rule firstLeftRule = new AiLeftArticleRule(SysConf.HomeCid);// 清空首页导航下的左侧文章
		MemcacheUtil.remove(firstLeftRule);
		Rule rule = new AiArticleRlue(String.valueOf(article.getCid()));// 两个cid下的缓存进行处理
		List<Article> allArticleList = AiArticleHP.getArticleListFromMC(rule);
		if (allArticleList != null && allArticleList.size() > 0) {
			article.setContent("");
			allArticleList.add(article);
			MemcacheUtil.set(rule, allArticleList);
		}
		Rule rule1 = new AiArticleRlue(String.valueOf(oid));
		MemcacheUtil.remove(rule1);
		Rule leftArticleRule = new AiLeftArticleRule(String.valueOf(article.getCid()));// 改变的两个cid的左侧文章都需要清空缓存
		MemcacheUtil.remove(leftArticleRule);
		Rule OldLeftArticleRule = new AiLeftArticleRule(String.valueOf(oid));
		MemcacheUtil.remove(OldLeftArticleRule);
		Rule topArticleRule = new AiTopArticleRule(String.valueOf(article.getCid()));// 改变的两个cid的置顶文章都需要清空缓存
		MemcacheUtil.remove(topArticleRule);
		Rule OldtopArticleRule = new AiTopArticleRule(String.valueOf(oid));
		MemcacheUtil.remove(OldtopArticleRule);
		Rule topHomeRule = new AiTopArticleRule(String.valueOf(SysConf.HomeCid));// 清空首页置顶文章缓存
		MemcacheUtil.remove(topHomeRule);
	}

	/**
	 * 冻结和解冻文章时处理缓存
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年10月17日16:20:31
	 */
	public static void handleStatusCache(Article article, int status) {
		Rule rule = new AiArticleRlue(String.valueOf(article.getCid()));
		if (status == 0) {
			MemcacheUtil.remove(rule);
		}
		Rule leftArticleRule = new AiLeftArticleRule(String.valueOf(article.getCid()));// 清空左侧文章缓存
		MemcacheUtil.remove(leftArticleRule);
		Rule topArticleRule = new AiTopArticleRule(String.valueOf(article.getCid()));// 清空置顶文章缓存
		MemcacheUtil.remove(topArticleRule);
		Rule firstRule = new AiArticleRlue(SysConf.HomeCid);// 清空首页导航下的所有文章
		MemcacheUtil.remove(firstRule);
		Rule firstLeftRule = new AiLeftArticleRule(SysConf.HomeCid);// 清空首页导航下的左侧文章
		MemcacheUtil.remove(firstLeftRule);
		Rule topHomeRule = new AiTopArticleRule(String.valueOf(SysConf.HomeCid));// 清空首页置顶文章缓存
		MemcacheUtil.remove(topHomeRule);
	}

	/**
	 * 删除文章时处理缓存
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年10月17日16:20:31
	 */
	public static void handleDeleteCache(int cid) {
		Rule rule = new AiArticleRlue(String.valueOf(cid));// 清空相应cid下的文章缓存
		MemcacheUtil.remove(rule);
		Rule firstRule = new AiArticleRlue(SysConf.HomeCid);// 清空首页导航下的所有文章
		MemcacheUtil.remove(firstRule);
		Rule firstLeftRule = new AiLeftArticleRule(SysConf.HomeCid);// 清空首页导航下的左侧文章
		MemcacheUtil.remove(firstLeftRule);
		Rule leftArticleRule = new AiLeftArticleRule(String.valueOf(cid));// 清空左侧文章缓存
		MemcacheUtil.remove(leftArticleRule);
		Rule topArticleRule = new AiTopArticleRule(String.valueOf(cid));// 清空置顶文章缓存
		MemcacheUtil.remove(topArticleRule);
		Rule topHomeRule = new AiTopArticleRule(String.valueOf(SysConf.HomeCid));// 清空首页置顶文章缓存
		MemcacheUtil.remove(topHomeRule);
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
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日14:12:33
	 */
	public static int getTopArticleCount() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, ETop.istop); // 表示置顶
		List<Article> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 文章初始化全站索引
	 * 
	 * @author 杨光
	 * @createtime 2017年9月19日14:12:52
	 */
	public static void initializeSearchIndex() {
		Map<String, Object> delmap = new HashMap<>();
		delmap.put(MapperSearchDict.table, ESite.ai.name());
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
				map.put(MapperSearchDict.table, ESite.ai.name());
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
	 * @author 杨光
	 * @date 2017年9月19日14:14:33
	 */
	public static Boolean addTags(Article article) {
		Map<String, Object> map = getParamMap();
		List<Label> list = getLabelSO().getList(map); // 得到所有标签
		if (list != null) {
			String articleContent = article.getContent() + article.getTitle();
			String labelContent;
			for (Label label : list) {
				labelContent = label.getContent();
				if (StrUtil.isNotEmpty(labelContent) & StrUtil.isNotEmpty(articleContent) & articleContent.indexOf(label.getContent()) != -1) {
					label.setRefuid(label.getRefuid() + ViewKeyDict.comma + article.getUid());
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
