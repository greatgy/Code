package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.JoinrecordsDao;
import com.supergenius.xo.sudokuapi.entity.Joinrecords;
import com.supergenius.xo.sudokuapi.service.JoinrecordsSO;

/**
 * 用户SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class JoinrecordsSOImpl extends BaseSOImpl<Joinrecords> implements JoinrecordsSO {

	@Autowired
	JoinrecordsDao joinrecordDao;

	@Override
	protected BaseDao<Joinrecords> getDao() {
		return joinrecordDao;
	}

}
