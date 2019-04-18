package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.RulesDao;
import com.supergenius.xo.sudokuapi.entity.Rules;
import com.supergenius.xo.sudokuapi.service.RulesSO;

/**
 * 游戏规则so实现
 * 
 * @author liushaomin
 */
@Service
public class RulesSOImpl extends BaseSOImpl<Rules> implements RulesSO {

	@Autowired
	RulesDao rulesDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Rules> getDao() {
		return rulesDao;
	}
}
