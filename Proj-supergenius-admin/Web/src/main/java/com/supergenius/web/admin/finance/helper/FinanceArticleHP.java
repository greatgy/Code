package com.supergenius.web.admin.finance.helper;

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
import com.genius.core.base.utils.JsonUtil;
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
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.helper.BaseHP;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Catalogue;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.rule.FinanceArticleRlue;
import com.supergenius.xo.finance.rule.FinanceFirstArticleRule;
import com.supergenius.xo.finance.rule.FinanceTopArticleRule;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CatalogueSO;
import com.supergenius.xo.finance.service.LabelSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 文章管理HP
 * 
 * @author ChenQi
 * @date 2018年1月5日20:00:37
 */
public class FinanceArticleHP extends BaseHP {
	private static Logger log = LoggerFactory.getLogger(FinanceArticleHP.class);

	private static ArticleSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static SearchEngine engine;
	private static LabelSO labelSO;
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
			engine = (SearchEngine) spring.getBean("financeEngine");
		}
		return engine;
	}

	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
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
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<String, Object> model, String channel) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (!channel.equals(ViewKeyDict.financearticle)) {
			map.put(MapperDict.cid, getcid(channel));
		}
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, model.get(ViewKeyDict.isrecommend));
		} else {
			map.put(MapperDict.isrecommend, null);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
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
	@SuppressWarnings("unchecked")
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
				if (StrUtil.isNotEmpty(article.getData())) {
					Map<String, Integer> data = JsonUtil.fromJson(article.getData(), Map.class);
					article.setScore(data.get("score"));
				} else {
					article.setScore(0);
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
		delmap.put(MapperSearchDict.table, ESite.finance.name());
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
				map.put(MapperSearchDict.table, ESite.finance.name());
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
	 * @author ChenQi
	 * @date 2018年1月5日20:01:01
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
	 * @author ChenQi
	 * @date 2018年1月5日20:01:05
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
				label.setCount(label.getCount() + 2);
				getLabelSO().update(label);
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
		Rule bannerrule = new FinanceTopArticleRule(String.valueOf(article.getCid()));// 模块的轮播缓存
		MemcacheUtil.remove(bannerrule);
		Rule Firstrule = new FinanceFirstArticleRule();// 首页左侧缓存
		MemcacheUtil.remove(Firstrule);
		Rule cidrule = new FinanceArticleRlue(String.valueOf(article.getCid()));// 模块下的所有文章
		MemcacheUtil.remove(cidrule);
		Rule allrule = new FinanceArticleRlue(String.valueOf(0));// 所有文章的缓存
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
	 * 添加标签
	 * 
	 * @param content
	 * @param uid
	 * @return
	 * @author yangguang
	 */
	public static void add(String uid, String content) {
		String[] labels = content.split("\\s+");
		for (String labeli : labels) {
			if (StrUtil.isEmpty(labeli)) {
				continue;
			}
			Label labeltmp = isExist(labeli);
			if (labeltmp != null) {
				labeltmp.setRefuid(labeltmp.getRefuid() + ViewKeyDict.comma + uid);
				labeltmp.setCount(labeltmp.getCount() + 2);
				getLabelSO().update(labeltmp);
				continue;
			}
			Label label = new Label();
			label.setContent(labeli);
			label.setCount(2);
			label.setRefuid(uid);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				label.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.label.toInt());
			adminLog.setDataid(label.getUid());
			adminLog.setDesc("");
			adminLog.setData(EAdminLog.addLabel.getName());
			adminLog.setOperation(EAdminLog.addLabel.getName());
			getLabelSO().add(label, adminLog);
			addTags(label);
		}
	}

	/**
	 * 判断标签是否已经存在
	 * 
	 * @param content
	 * @return
	 * @author yangguang
	 */
	public static Label isExist(String content) {
		List<Label> list = getLabelSO().getList();
		if (StrUtil.isEmpty(content)) {
			return null;
		}
		for (Label item : list) {
			if (content.equals(item.getContent())) {
				return item;
			}
		}
		return null;
	}

	/**
	 * 判断是否是会员
	 * 
	 * @param content
	 * @return
	 * @author ChenQi
	 */
	public static void isMemeber(Article article) {
		User user = getUserSO().get(article.getAuthoruid());
		if(user != null) {
			article.setPublisher(user.getUsername());
			if (user.getIsUser()) {
				article.setIsmember(1);
			} else {
				article.setIsmember(0);
			} 
		}else {
			article.setIsmember(2);
			Admin admin = getAdminSO().get(article.getAuthoruid());
			if (admin != null) {
				article.setPublisher(admin.getName());
			}
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
				Cache(article);
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.finance.name());
				getEngine().add(solrMap);
			}
			log.debug("financeArticle publish is true");
			return true;
		}
		log.debug("financeArticle publish is false");
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