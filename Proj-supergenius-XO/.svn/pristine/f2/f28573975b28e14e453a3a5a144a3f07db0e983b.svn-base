package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.PkStateDetailDao;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.service.PkStateDetailSO;

/**
 * 挑战状态明细SO
 * @author XieMing
 * @date 2016-4-29 下午3:19:45
 */
@Service
public class PkStateDetailSOImpl extends BaseSOImpl<PKStateDetail> implements PkStateDetailSO {

	@Autowired
	PkStateDetailDao dao;

	@Override
	protected BaseDao<PKStateDetail> getDao() {
		return dao;
	}

	@Override
	public PKStateDetail getOneByToStage(String pkuid, EPKStage tostage) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pkscheduleuid, pkuid);
		map.put(MapperDict.tostage, tostage);
		return dao.getOne(map);
	}

	@Override
	public List<PKStateDetail> getList(String pkscheduleuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getList(map);
	}

	@Override
	public int getCount(String pkscheduleuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getCount(map);
	}

	@Override
	public List<PKStateDetail> getListByEnable(String pkscheduleuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getList(map);
	}
}
