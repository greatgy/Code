package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.PropsDao;
import com.supergenius.xo.sudokuapi.entity.Props;
import com.supergenius.xo.sudokuapi.service.PropsSO;

/**
 * 用户SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class PropsSOImpl extends BaseSOImpl<Props> implements PropsSO {

	@Autowired
	PropsDao propsDao;

	@Override
	protected BaseDao<Props> getDao() {
		return propsDao;
	}

}
