package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppReplyExpertDao;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.service.AppReplyExpertSO;

/** 
 * 答辩专家soImpl
 * @author chenminchang
 * @date 2016-7-17 下午12:14:04 
 */
@Service
public class AppReplyExpertSOImpl extends BaseSOImpl<AppReplyExpert> implements AppReplyExpertSO{

	@Autowired
	AppReplyExpertDao dao;
	
	@Override
	protected AppReplyExpertDao getDao() {
		return dao;
	}

	@Override
	public List<AppReplyExpert> getAppReplyExpertList(String appreplyuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.appreplyuid, appreplyuid);
		map.put(MapperDict.isabsent, 0);
		return dao.getList(map);
	}

	@Override
	public AppReplyExpert getOne(String appreplyuid, String expertuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.appreplyuid, appreplyuid);
		map.put(MapperDict.expertuid, expertuid);
		return dao.getOne(map);
	}

	@Override
	public boolean update(AppReplyExpert oldAppReplyExpert, AppReplyExpert appReplyExpert) {
		return dao.update(oldAppReplyExpert) && dao.insert(appReplyExpert);
	}

	@Override
	public List<AppReplyExpert> getList(String appreplyuid, boolean isabsent) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.appreplyuid, appreplyuid);
		map.put(MapperDict.isabsent, isabsent);
		return dao.getList(map);
	}
}
