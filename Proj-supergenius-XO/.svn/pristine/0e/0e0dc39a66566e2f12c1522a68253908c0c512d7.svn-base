package com.supergenius.xo.gupage.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.dao.PhotoDao;
import com.supergenius.xo.gupage.entity.Photo;
import com.supergenius.xo.gupage.service.PhotoSO;

/**
 * 图片SO实现
 * 
 * @author yangguang
 * @date 2018年1月10日10:51:19
 */
@Service("gupagePhotoSOImpl")
public class PhotoSOImpl extends BaseSOImpl<Photo> implements PhotoSO {

	@Autowired
	private PhotoDao dao;

	@Override
	protected BaseDao<Photo> getDao() {
		return dao;
	}

	@Override
	public boolean setRecommend(String[] ids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}
	
	@Override
	public Map<String, Photo> getLastNext(Photo photo) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.createtimestartne, DateUtil.parseDateTime(photo.getCreatetime(), DateUtil.FORMAT_DATETIME_CHINA));
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime);
		Photo next = dao.getOne(map);
		map = getParamMap();
		map.put(MapperDict.createtimeendne, DateUtil.parseDateTime(photo.getCreatetime(), DateUtil.FORMAT_DATETIME_CHINA));
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.desc);
		Photo last = dao.getOne(map);
		Map<String, Photo> result = new HashMap<>();
		result.put(MapperDict.last, last);
		result.put(MapperDict.next, next);
		return result;
	}
}
