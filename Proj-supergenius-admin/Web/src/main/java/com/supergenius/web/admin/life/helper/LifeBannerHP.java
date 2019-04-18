package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.entity.Photo;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.service.ContentSO;

/** 
 * banner管理HP
 * @author ChenQi
 * @date 2018年5月11日15:37:55
 */
public class LifeBannerHP extends BaseHP {

	private static ContentSO so;
	private static List<Photo> lifelist = new ArrayList<Photo>();
	
	private static ContentSO getSO() {
		if (so == null) {
			so = spring.getBean(ContentSO.class);
		}
		return so;
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @return
	 * @author ChenQi
	 * @createtime 2018年5月11日15:37:55
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		lifelist = new ArrayList<Photo>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, 10);
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.type, EContent.banner);
		List<Content> content = getSO().getList(map);
		result.put(ViewKeyDict.rows, organized(content));
		return result;
	}
	
	/**
	 * 组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	@SuppressWarnings("unchecked")
	public static List<Photo> organized(List<Content> contentList) {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		Photo photo = null;
		List<Photo> result = new ArrayList<>();
		for (Content content : contentList) {
			map = JsonUtil.fromJson(content.getData(), Map.class);
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				Map<String, String> data = map.get(key);
				photo = createPhoto(key, data);
				photo.setUid(content.getUid());
				photo.setCid(content.getCid());
				photo.setStatus(content.getStatus());
				lifelist.add(photo);
				result.add(photo);
			}
		}
		return result;
	}

	/**
	 * 组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Photo createPhoto(String oid, Map<String, String> map) {
		Photo photo = new Photo();
		photo.setOid(Integer.parseInt(oid));
		photo.setTitle(map.get(ViewKeyDict.title));
		photo.setContent(map.get(ViewKeyDict.content));
		photo.setUrl(map.get(ViewKeyDict.url));
		return photo;
	}
	
	/**
	 * 更新图片
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Content update(String title, String content, String url, String oid, String cid) {
		Map<String, Object> contentMap = getParamMap();
		contentMap.put(MapperDict.type, EContent.banner);
		contentMap.put(MapperDict.cid, cid);
		Content entity = getSO().getOne(contentMap);
		Map<String, Map<String, String>> map = new HashMap<>();
		Map<String, String> photoMap;
		for (Photo photo : lifelist) {
			if (String.valueOf(photo.getCid()).equals(cid)) {
				if (String.valueOf(photo.getOid()).equals(oid)) {
					photoMap = new HashMap<>();
					photoMap.put(ViewKeyDict.title, title);
					photoMap.put(ViewKeyDict.content, content);
					photoMap.put(ViewKeyDict.url, url);
					map.put(oid, photoMap);
				} else {
					photoMap = new HashMap<>();
					photoMap.put(ViewKeyDict.title, photo.getTitle());
					photoMap.put(ViewKeyDict.content, photo.getContent());
					photoMap.put(ViewKeyDict.url, photo.getUrl());
					map.put(String.valueOf(photo.getOid()), photoMap);
				}
			}
		}
		entity.setData(JsonUtil.toJson(map));
		return entity;
	}
}