package com.genius.server.base.helper;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.SEO;
import com.google.common.collect.Lists;

/**
 * 处理SEO的常用读写等操作
 * 
 * @author architect.bian
 * 2014-6-26 下午12:32:15
 */
public class SEOHP extends BaseHP {
	
	private static Map<String, Map<String, SEO>> seoMap = new HashMap<String, Map<String, SEO>>();
	private static Map<String, SEO> seoCacheMap = new HashMap<String, SEO>();
	private static long lastModifiedTime = 0;

	/**
	 * 初始网页的SEO变量，可从缓存中获取
	 * @param requestURI
	 * @param model
	 * @author: Architect.bian
	 * 2014-6-24 下午2:01:37
	 */
	public static void initialize(String requestURI, Map<String, Object> model) {
		SEO seo = null;
		String site = "";
		if (model.get(BaseViewKeyDict.site) != null) {
			site= model.get(BaseViewKeyDict.site).toString();
		}
		if (seoCacheMap.containsKey(requestURI) && !isSEOUpdated(site)) {//TODO memcache存储 
			seo = seoCacheMap.get(requestURI);
		} else {
			Collection<SEO> seos = getSEOs(site).values();
			for (SEO item : seos) {
				if (item.isMatchUri(requestURI.toLowerCase())) {
					seo = createNewSEO(item, model, site);
					seoCacheMap.put(requestURI, seo);//放入缓存
					break;
				}
			}
		}
		if (seo != null) {
			model.put(BaseViewKeyDict.isSEOConfiged, true);
			model.put(BaseViewKeyDict.title, seo.getTitle());
			model.put(BaseViewKeyDict.keywords, seo.getKeywords());
			model.put(BaseViewKeyDict.description, seo.getDesc());
		}
	}
	
	/**
	 * 获取所有的seo列表
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-30 下午4:05:20
	 */
	public static List<SEO> getList(String... site) {
		return Lists.newArrayList(getSEOs(site).values());
	}

	/**
	 * 删除某个seo
	 * @param uid
	 * @return
	 * @author Architect.bian
	 * 2014-6-26 下午12:54:28
	 */
	public static boolean delete(String[] uids, String... site) {
		for (String uid : uids) {
			if (getSEOs(site).containsKey(uid)) {
				Map<String, SEO> map = deserializeMap(site);
				map.remove(uid);
				if (serializeMap(map, site)) {
					clearCache(site);
				}
			}
		}
		return true;
	}
	
	/**
	 * 添加一个seo
	 * @param seo
	 * @return
	 * @author Architect.bian
	 * 2014-6-26 下午12:58:32
	 */
	public static boolean add(SEO seo, String... site) {
		Map<String, SEO> map = deserializeMap(site);
		seo.setUri(seo.getUri().toLowerCase());
		map.put(seo.getUid(), seo);
		if (serializeMap(map, site)) {
			clearCache(site);
		}
		return true;
	}
	
	/**
	 * 更新一个seo，等同于重新添加
	 * @param seo
	 * @return
	 * @author Architect.bian
	 * 2014-6-26 下午1:00:11
	 */
	public static boolean update(SEO seo) {
		return add(seo);
	}
	
	/**
	 * 通过seo返回一个含有title keywords description的最终翻译后的SEO对象
	 * @param item
	 * @param model
	 * @return
	 * @author Architect.bian
	 * 2014-6-24 下午3:51:00
	 */
	private static SEO createNewSEO(SEO seo, Map<String, Object> model, String... site) {
		String title = processTmpl(BaseViewKeyDict.title, seo.getTitle(), model);
		String keywords = processTmpl(BaseViewKeyDict.keywords, seo.getKeywords(), model);
		String desc = processTmpl(BaseViewKeyDict.description, seo.getDesc(), model);
		if (seo.getParentuid() != null) {
			SEO parentSeo = getSEOs(site).get(seo.getParentuid());
			if (parentSeo != null) {
				model.put(BaseViewKeyDict.title, title);
				model.put(BaseViewKeyDict.keywords, keywords);
				model.put(BaseViewKeyDict.description, desc);
				return createNewSEO(parentSeo, model, site);//根据parentuid进行递归
			}
		}
		return new SEO(title, keywords, desc);
	}

	/**
	 * 格式化模板，若是freemarker格式则进行模板处理，否则仅进行追加主力
	 * @param key
	 * @param strtmpl
	 * @param model
	 * @return
	 * @author Architect.bian
	 * 2014-6-26 下午7:59:42
	 */
	private static String processTmpl(String key, String strtmpl, Map<String, Object> model) {
		if (strtmpl == null) {
			return null;
		}
		if (strtmpl.contains("${")) {
			return FreemarkerUtil.processStr(strtmpl, model);
		} else {
			return model.containsKey(key) ? model.get(key) + strtmpl : strtmpl;
		}
	}

	/**
	 * 从缓存或者序列化文件中返回序列化的map
	 * @return
	 * @author Architect.bian
	 * 2014-6-26 上午10:51:40
	 */
	private static Map<String, SEO> getSEOs(String... site) {
		if (site.length == 0) {
			return new HashMap<String, SEO>();
		}
		if(seoMap.get(site[0]) == null || seoMap.get(site[0]).size() == 0) {// seo缓存
			seoMap.put(site[0], deserializeMap(site));
		}
		return seoMap.get(site[0]);
	}

	/**
	 * 是否修改
	 * @return
	 */
	private static boolean isSEOUpdated(String... site) {
		File file = new File(getSerializePath(site));
		if (lastModifiedTime == file.lastModified()) {//没更改
			return false;
		} else {
			lastModifiedTime = file.lastModified();//已更改
			clearCache(site);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, SEO> deserializeMap(String... site) {
		Map<String, SEO> map = (Map<String, SEO>)SerialUtil.deserializeFromJson(getSerializePath(site), Map.class);
		return map == null ? new HashMap<String, SEO>() : map;
	}
	
	private static boolean serializeMap(Map<String, SEO> map, String... site) {
		return SerialUtil.serializeToJson(map, getSerializePath(site));
	}

	private static String getSerializePath(String... site) {
		if (site != null && site.length > 0 && StrUtil.isNotEmpty(site[0])) {
			return BaseSysConf.getSEOPath(site[0]);
		}
		return BaseSysConf.SerialSEOPath;
	}
	
	private static void clearCache(String... site) {
		seoMap.get(site[0]).clear();
		seoCacheMap.clear();
	}

}
