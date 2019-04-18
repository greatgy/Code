package com.supergenius.xo.finance.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.finance.dao.LabelDao;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.service.LabelSO;

/**
 * 标签so实现
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:55:19
 */
@Service("financeLabelSOImpl")
public class LabelSOImpl extends BaseSOImpl<Label> implements LabelSO{

	@Autowired
	private LabelDao dao;
	
	@Autowired
	private AdminLogSO adminlogSO ;
	
	@Override
	protected BaseDao<Label> getDao() {
		return dao;
	}
	
	@Override
    public boolean add(Label label, AdminLog adminLog) {
      return dao.insert(label) && adminlogSO.add(adminLog);
    }
	
	@Override
	public boolean update(Label label, AdminLog adminLog) {
		return dao.update(label) && adminlogSO.add(adminLog);
	}
}
