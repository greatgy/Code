package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.ProblemDao;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.service.ProblemSO;

/**
 * 内容SOImpl
 * @Author:JiaShitao
 * @Date:2018年5月7日下午6:08:45
 */
@Service("lifeProblemSOImpl")
public class ProblemSOImpl extends BaseSOImpl<Problem> implements ProblemSO {
	@Autowired
	ProblemDao dao;
	@Override
	protected BaseDao<Problem> getDao() {
		return dao;
	}

}
