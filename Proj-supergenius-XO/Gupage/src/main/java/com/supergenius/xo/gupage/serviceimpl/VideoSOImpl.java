package com.supergenius.xo.gupage.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.dao.VideoDao;
import com.supergenius.xo.gupage.entity.Video;
import com.supergenius.xo.gupage.service.VideoSO;

/**
 * 视频SO实现
 * 
 * @author yangguang
 * @date 2018年1月10日10:52:37
 */
@Service("gupageVideoSOImpl")
public class VideoSOImpl extends BaseSOImpl<Video> implements VideoSO {

	@Autowired
	private VideoDao dao;

	@Override
	protected BaseDao<Video> getDao() {
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
}
