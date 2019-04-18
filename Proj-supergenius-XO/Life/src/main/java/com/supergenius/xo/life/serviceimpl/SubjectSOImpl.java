package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.SubjectDao;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.service.SubjectSO;

/**
 * 科目SOImpl
 * 
 * @Author:JiaShitao
 * @Date:2018年5月7日下午6:08:45
 */
@Service("lifeSubjectSOImpl")
public class SubjectSOImpl extends BaseSOImpl<Subject> implements SubjectSO {
	@Autowired
	SubjectDao dao;

	@Override
	protected BaseDao<Subject> getDao() {
		return dao;
	}

	@Override
	public Subject get(int sid) {
		// TODO Auto-generated method stub
		return dao.get(sid);
	}

	@Override
	public boolean updateStatusBySid(int sid) {
		Subject subject = dao.get(sid);
		if (subject.getStatus() == EStatus.disable) {
			subject.setStatus(EStatus.enable);
		} else {
			subject.setStatus(EStatus.disable);
		}
		return dao.update(subject);
	}
}
