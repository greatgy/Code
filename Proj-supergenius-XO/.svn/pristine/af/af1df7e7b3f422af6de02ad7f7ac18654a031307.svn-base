package com.supergenius.xo.moralnews.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.dao.AnnouncementDao;
import com.supergenius.xo.moralnews.entity.Announcement;
import com.supergenius.xo.moralnews.service.AnnouncementSO;

/**
 * 公告SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日10:00:31
 */
@Service("moralnewsAnnouncementSOImpl")
public class AnnouncementSOImpl extends BaseSOImpl<Announcement> implements AnnouncementSO {

	@Autowired
	private AnnouncementDao dao;

	@Override
	protected BaseDao<Announcement> getDao() {
		return dao;
	}

	@Override
	public boolean update(EStatus eStatus, String ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, eStatus);
		map.put(MapperDict.uid, ids);
		return getDao().updateFields(map);
	}

	@Override
	public boolean setTop(String id, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.istop, istop);
		map.put(BaseMapperDict.uid, id);
		return dao.updateFields(map);
	}
	
	@Override
	public boolean checkIsTop(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, true);
		map.put(BaseMapperDict.uid, id);
		if (0 == dao.getList(map).size()) {
			return true;
		} else {
			return false;
		}
	}
	
}
