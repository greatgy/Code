package com.supergenius.xo.enterpriser.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.AppCooperationDao;
import com.supergenius.xo.enterpriser.entity.AppCooperation;
import com.supergenius.xo.enterpriser.service.AppCooperationSO;

/** 
* 
* @author chenminchang
* @date 2016年12月5日 下午12:27:08 
*/
@Service
public class AppCooperationSOImpl extends BaseSOImpl<AppCooperation> implements AppCooperationSO {

	@Autowired
	AppCooperationDao dao;
	
	@Override
	protected BaseDao<AppCooperation> getDao() {
		return dao;
	}

	@Override
	public AppCooperation getOneByEmail(String email) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.email, email);
		return dao.getOne(map);
	}

}
