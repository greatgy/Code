package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.PointsrecordsDao;
import com.supergenius.xo.sudokuapi.entity.Pointsrecords;
import com.supergenius.xo.sudokuapi.service.PointsrecordsSO;

/**
 * 加入积分SO实现
 * 
 * @author ChenQi
 */
@Service
public class PointsrecordsSOImpl extends BaseSOImpl<Pointsrecords> implements PointsrecordsSO {

	@Autowired
	PointsrecordsDao pointsrecordsDao;

	@Override
	protected BaseDao<Pointsrecords> getDao() {
		return pointsrecordsDao;
	}

}
