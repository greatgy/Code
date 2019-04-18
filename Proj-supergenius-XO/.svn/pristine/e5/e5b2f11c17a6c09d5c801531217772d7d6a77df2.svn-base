package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.LoginhistoriesDao;
import com.supergenius.xo.sudokuapi.entity.Loginhistories;
import com.supergenius.xo.sudokuapi.service.LoginhistoriesSO;

/**
 * 登录历史 SO 实现
 * @author YuYingJie
 */
@Service
public class LoginhistoriesSOImpl extends BaseSOImpl<Loginhistories> implements LoginhistoriesSO {

	@Autowired
	LoginhistoriesDao dao;
	
	@Override
	protected BaseDao<Loginhistories> getDao() {
		return dao;
	}

}
