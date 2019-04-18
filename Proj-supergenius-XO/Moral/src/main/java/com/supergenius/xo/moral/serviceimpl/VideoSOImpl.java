package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.VideoDao;

import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Video;
import com.supergenius.xo.moral.service.VideoSO;

/**
 * 视频SO实现
 * 
 * @author LiJiacheng
 */
@Service("moralVideoSOImpl")
public class VideoSOImpl extends BaseSOImpl<Video> implements VideoSO {

	@Autowired
	VideoDao dao;

	@Override
	protected BaseDao<Video> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.VideoSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.VideoSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList()
	 */
	@Override
	public List<Video> getList(){
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.orderBy, MapperDict.chapter);
		return dao.getList(map);
	}

}
