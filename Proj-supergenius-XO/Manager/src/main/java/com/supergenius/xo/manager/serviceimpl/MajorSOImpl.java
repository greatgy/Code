package com.supergenius.xo.manager.serviceimpl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.MajorDao;
import com.supergenius.xo.manager.entity.Major;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.MajorSO;

/** 
 * 专业SOImpl  	
 * @author chenminchang
 * @date 2016-3-18 下午4:50:30 
 */
@Service
public class MajorSOImpl extends BaseSOImpl<Major> implements MajorSO {

	@Autowired
	MajorDao dao;

	@Override
	protected MajorDao getDao() {
		return dao;
	}

	@Override
	public Major getOneByType(EMajor type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}
	
	@Override
	public boolean updateContent(String uid, String content, String summary) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.content, content);
		map.put(MapperDict.summary, summary);
		return dao.updateFields(map);
	}
}
