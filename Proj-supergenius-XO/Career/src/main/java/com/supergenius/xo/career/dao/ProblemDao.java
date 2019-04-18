package com.supergenius.xo.career.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.career.entity.Problem;

/**
 * 问题Dao
 * @author ChenQi
 * @date 2017年11月13日17:05:27
 */
@Component("CareerProblemDao")
public interface ProblemDao extends BaseDao<Problem> {
	
}
