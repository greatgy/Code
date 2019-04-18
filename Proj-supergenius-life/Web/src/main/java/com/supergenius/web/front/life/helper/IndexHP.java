package com.supergenius.web.front.life.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.ProblemSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.life.service.VideoSO;

public class IndexHP extends BaseHP {

	private static ArticleSO so;
	private static ProblemSO problemSO;
	private static TopicSO topicSO;
	private static VideoSO videoSO;
	private static CatalogueSO catalogueSO;
	private static DateTime cacheTime;

	public static ArticleSO getSO() {
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

	private static ProblemSO getProblemSO() {
		if (problemSO == null) {
			problemSO = (ProblemSO) spring.getBean(ProblemSO.class);
		}
		return problemSO;
	}

	private static TopicSO getTopicSO() {
		if (topicSO == null) {
			topicSO = (TopicSO) spring.getBean(TopicSO.class);
		}
		return topicSO;
	}

	private static VideoSO getVideoSO() {
		if (videoSO == null) {
			videoSO = (VideoSO) spring.getBean(VideoSO.class);
		}
		return videoSO;
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
	public static void handleCache(Long cid) {
		if (cacheTime == null) {
			cacheTime = getSO().getCacheTime();
		}
		if (cacheTime != null && new DateTime(DateTimeZone.forOffsetHours(8)).getMillis() >= cacheTime.getMillis()) {
			ContributeHP.Cache(cid);
			cacheTime = getSO().getCacheTime();
		}
	}

	/**
	 * 获得首页问答
	 * 
	 * @return
	 * @author yangguang
	 * @date 2018年5月14日16:23:28
	 * @return List<Problem>
	 */
	public static List<Problem> getIndexProblem(int pagenum, int pagerSize) {
		Pager pager = Pager.getNewInstance(pagenum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		return getProblemSO().getList(map);
	}

	/**
	 * 获得首页话题
	 * 
	 * @return
	 * @author yangguang
	 * @date 2018年5月14日16:23:28
	 * @return List<Problem>
	 */
	public static List<Topic> getIndexTopic(int pagenum, int pagerSize) {
		Pager pager = Pager.getNewInstance(pagenum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.examine, true);
		return getTopicSO().getList(map);
	}
	
	/**
	 * @author 雍雪振
	 * @time 2018年5月31日下午1:35:16
	 * @description:根据cid获取模块
	 */
	public static Catalogue getOneCatalogue(Long cid) {
		Catalogue catalogue = getCatalogueSO().getOneByCid(cid);
		return catalogue;
	}
	
	/**
	 * 获得首页视频
	 * 
	 * @return
	 * @author yangguang
	 * @date 2018年5月14日16:23:28
	 * @return List<Problem>
	 */
	public static List<Video> getIndexVideo(int pagenum, int pagerSize) {
		Pager pager = Pager.getNewInstance(pagenum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		List<Video> list = getVideoSO().getList(map);
		VideoHP.organized(list);
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
		ArticleHP.initArticleList(list);
		return list;
	}

	/**
	 * 获取目录
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日18:53:35
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getCatalogueList() {
		List<Map<String, Object>> list = new ArrayList<>();
		String path = SysConf.SerialBasePath + SysConf.SerialLifeCataloguePath;
		File file = new File(path);
		if (file.exists()) {
			list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.webStrategy);
		}
		if (list.size() == 0) {
			List<Catalogue> catalogues = getCatalogueSO().getFirstCatalogues();
			Map<String, Object> map = null;
			for (Catalogue catalogue : catalogues) {
				map = new HashMap<>();
				map.put(ViewKeyDict.first, catalogue);
				map.put(ViewKeyDict.second, getCatalogueSO().getListByPcid(catalogue.getCid()));
				list.add(map);
			}
			SerialUtil.serializeToJson(list, path, Json.webStrategy);
		}
		return list;
	}

	/**
	 * 得到指定区间的文章
	 * 
	 * @author YangGuang
	 * @Datetime 2018年5月22日18:08:40
	 */
	public static List<Article> getPagesMore(int pagenum, Long cid, int pagesize) {
		Map<String, Object> map = getParamMap();
		map.put(BaseViewKeyDict.startIndex, pagenum - 1);
		map.put(BaseMapperDict.pageSize, pagesize);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Article> list = getSO().getList(map);
		ArticleHP.initArticleList(list);
		return list;
	}
	
	/**
	 * 重新序列化内容
	 * 
	 * @author ChenQi
	 * @Datetime 2018年6月22日14:53:09
	 */
	@SuppressWarnings("unchecked")
	public static void SerializeArticle(Map<String, Object> map, HttpServletRequest request) {
		try {
			// 序列化最新文章
			File file = new File(SysConf.FileSiteBasePath + SysConf.SerialOfficialLifeArticlePath);
			if (file.exists()) {
				FileUtil.delete(SysConf.FileSiteBasePath + SysConf.SerialOfficialLifeArticlePath);
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			List<Article> list = (List<Article>) map.get(ViewKeyDict.list);
			model.put(ViewKeyDict.officiallist, list.subList(0, list.size() > SysConf.SerialOfficialArticleNum ? SysConf.SerialOfficialArticleNum : list.size()));
			model.put(ViewKeyDict.baseLifePath, WebConf.baseRootPath);
			file = new File(SysConf.FileSiteBasePath + SysConf.SerialOfficialLifeArticlePath);
			FreemarkerUtil.process(SysConf.OfficialIndexTemplatePath, SysConf.HtmlOfficialRecentData, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 序列化会员中心内容
	 * 
	 * @author YongXuezhen
	 * @Datetime 2018年7月02日11:10:20
	 */
	@SuppressWarnings("unchecked")
	public static void SerializeLifeArticle(Map<String, Object> map, HttpServletRequest request) {
		try {
			// 序列化最新文章
			File file = new File(SysConf.FileSiteBasePath + SysConf.SerialUserLifeArticlePath);
			if (file.exists()) {
				FileUtil.delete(SysConf.FileSiteBasePath + SysConf.SerialUserLifeArticlePath);
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			List<Article> list = (List<Article>) map.get(ViewKeyDict.list);
			model.put(ViewKeyDict.list, list.subList(0, list.size() > SysConf.SerialUserLifeArticleNum ? SysConf.SerialUserLifeArticleNum : list.size()));
			model.put(ViewKeyDict.baseLifePath, WebConf.baseRootPath);
			file = new File(SysConf.FileSiteBasePath + SysConf.SerialUserLifeArticlePath);
			FreemarkerUtil.process(SysConf.UserIndexTemplatePath, SysConf.HtmlNewData, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}