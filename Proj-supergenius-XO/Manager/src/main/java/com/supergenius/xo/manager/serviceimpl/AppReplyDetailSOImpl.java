package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppReplyDetailDao;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.service.AppReplyDetailSO;

/**
 * 答辩状态明细Impl
 * @author XieMing
 * @date 2016-7-18 下午2:30:11
 */
@Service
public class AppReplyDetailSOImpl extends BaseSOImpl<AppReplyDetail> implements AppReplyDetailSO {

	@Autowired
	AppReplyDetailDao dao;
	
	@Override
	protected BaseDao<AppReplyDetail> getDao() {
		return dao;
	}

	@Override
	public int getCount(EReplyStage replystagefrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystagefrom, replystagefrom);
		return dao.getCount(map);
	}

	@Override
	public int getCountByStage(EReplyStage replystageto) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystageto, replystageto);
		return dao.getCount(map);
	}

	@Override
	public int getCount(EReplyStage replystagefrom, EReplyStage replystageto) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystagefrom, replystagefrom);
		map.put(MapperDict.replystageto, replystageto);
		return dao.getCount(map);
	}

	@Override
	public int getCount(List<EReplyStage> replystagefrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.replystagefrom + MapperDict.suffix_in_key, replystagefrom);
		return dao.getCount(map);
	}

	@Override
	public List<AppReplyDetail> getList(String appreplyuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.appreplyuid, appreplyuid);
		return dao.getList(map);
	}

	@Override
	public AppReplyDetail getOne(String appreplyuid, EReplyStage replystagefrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.appreplyuid, appreplyuid);
		map.put(MapperDict.replystagefrom, replystagefrom);
		return dao.getOne(map);
	}
	
}
