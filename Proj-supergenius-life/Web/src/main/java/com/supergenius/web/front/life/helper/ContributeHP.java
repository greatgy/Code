package com.supergenius.web.front.life.helper;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.search.engine.SearchEngine;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.ECatalogueType;
import com.supergenius.xo.life.rule.LifeFirstArticleRule;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CatalogueSO;

/**
 * 投稿HP
 * 
 * @author liushaomin
 */
public class ContributeHP extends BaseHP {

	private static ArticleSO so;
	
	private static CatalogueSO catalogueSO;

	private static SearchEngine engine;
	
	public static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}
	
	public static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("lifeEngine");
		}
		return engine;
	}

	/**
	 * 处理上传的图片
	 * 
	 * @param fileimg
	 * @param string
	 * @param imgshowsizes
	 * @param imgUploadBasePath
	 * @author liushaomin
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

	
	/**
	 * 得到投稿时显示的目录
	 * 
	 * @author yangguang
	 * @return
	 */
	public static List<Catalogue> getCatalogues() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, ECatalogueType.article);
		List<Catalogue> list = getCatalogueSO().getList(map);
		return list;
	}

	/**
	 * 发布文章时处理缓存
	 * 
	 * @param article
	 * @author yangguang
	 * @return
	 */
	public static void Cache(Long cid) {
		Rule indexRule = new LifeFirstArticleRule(String.valueOf(ECatalogue.index));
		MemcacheUtil.remove(indexRule);
		Rule firstRule = new LifeFirstArticleRule(String.valueOf(cid));
		MemcacheUtil.remove(firstRule);
		FileUtil.delete(SysConf.SerialBasePath + ViewKeyDict.article + cid + SysConf.SerialTravelPath);
	}
	
}