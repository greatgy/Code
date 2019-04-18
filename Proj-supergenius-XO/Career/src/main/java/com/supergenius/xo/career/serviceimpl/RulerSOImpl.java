package com.supergenius.xo.career.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.career.dao.RulerDao;
import com.supergenius.xo.career.entity.Ruler;
import com.supergenius.xo.career.service.RulerSO;

/**
 * 
 * @author ChenQi
 * @date 2017年11月13日16:17:42
 */
@Service("careerRulerSOImpl")
public class RulerSOImpl extends BaseSOImpl<Ruler> implements RulerSO {

	@Autowired
	private RulerDao dao;

	@Autowired
	private AdminLogSO adminlogSO;

	@Override
	protected BaseDao<Ruler> getDao() {
		return dao;
	}

	@Override
	public boolean update(Ruler ruler, AdminLog adminLog) {
		return dao.update(ruler) && adminlogSO.add(adminLog);
	}
}
