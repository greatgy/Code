package com.supergenius.web.admin.gupage.helper;

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
import com.supergenius.xo.gupage.entity.Article;
import com.supergenius.xo.gupage.service.ArticleSO;

/**
 * 个人文章管理HP
 * 
 * @author loupengyu
 * @date 2018年1月11日19:22:16
 */
public class GupageArticleHP extends BaseHP {
	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

	private static ArticleSO so;
	private static SearchEngine engine;
	private static AdminSO adminSO;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("gupageEngine");
		}
		return engine;
	}
	
	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return map
	 * @author loupengyu
	 */
	public static Map<String, Object> query(Map<String, Object> model , String channel) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		map.put(MapperDict.cid, getcid(channel));
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
				case "gupagearticle":
					cid = 10;
					break;
				case "gupagenews":
					cid = 11;
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
		delmap.put(MapperSearchDict.table, ESite.gupage.name());
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
				map.put(MapperSearchDict.table, ESite.gupage.name());
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
			for(Article article :list){
				article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
				solrMap = MapsUtil.toMap(article, Maps.searchStrategy);// 增加索引
				solrMap.put(MapperSearchDict.table, ESite.gupage.name());
				getEngine().add(solrMap);
			}
			log.debug("gupageArticle publish is true");
			return true;
		}
		log.debug("gupageArticle publish is false");
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