package com.supergenius.web.admin.enterpriser.hellper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.entity.Photo;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.service.ContentSO;

/** 
 * 引资购商图片管理HP
 * @author YangGuang
 * @date 2018年1月30日16:45:33
 */
public class PhotoHP extends BaseHP {

	private static ContentSO so;
	private static List<Photo> list = new ArrayList<Photo>();
	
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
	 * @author YangGuang
	 * @createtime 2018年1月30日16:47:40
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		list = new ArrayList<Photo>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, 6);
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EContent.photo);
		Content content = getSO().getOne(map);
		result.put(ViewKeyDict.rows, organized(content));
		return result;
	}
	
	/**
	 * 组织数据
	 * 
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Photo> organized(Content content) {
		Map<String, Map<String, String>> map = JsonUtil.fromJson(content.getData(), Map.class);
		Iterator<String> iter = map.keySet().iterator();
		Photo photo = null;
		List<Photo> result = new ArrayList<>();
		while (iter.hasNext()) {
			String key = iter.next();
			Map<String, String> data = map.get(key);
			photo = createPhoto(key, data);
			list.add(photo);
		}
		result.addAll(list);
		return result;
	}

	/**
	 * 组织数据
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static Photo createPhoto(String oid, Map<String, String> map) {
		Photo photo = new Photo();
		photo.setOid(Integer.parseInt(oid));
		photo.setTitle(map.get(ViewKeyDict.title));
		photo.setContent(map.get(ViewKeyDict.content));
		return photo;
	}
	
	/**
	 * 更新图片
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static Content update(String title, String content, String oid) {
		Map<String, Object> contentMap = getParamMap();
		contentMap.put(MapperDict.type, EContent.photo);
		Content entity = getSO().getOne(contentMap);
		Map<String, Map<String, String>> map = new HashMap<>();
		Map<String, String> photoMap;
		for(Photo photo : list){
			if (String.valueOf(photo.getOid()).equals(oid)) {
				photoMap = new HashMap<>();
				photoMap.put(ViewKeyDict.title, title);
				photoMap.put(ViewKeyDict.content, content);
				map.put(oid, photoMap);
			} else {
				photoMap = new HashMap<>();
				photoMap.put(ViewKeyDict.title, photo.getTitle());
				photoMap.put(ViewKeyDict.content, photo.getContent());
				map.put(String.valueOf(photo.getOid()), photoMap);
			}
		}
		entity.setData(JsonUtil.toJson(map));
		return entity;
	}
}
