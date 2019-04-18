package com.supergenius.xo.manager.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.UserStaticsDao;
import com.supergenius.xo.manager.entity.UserStatics;
import com.supergenius.xo.manager.service.UserStaticsSO;

/** 
 * 用户统计Impl
 * @author guanshiqian
 * @date 2016-4-27 下午12:20:59 
 */
@Service
public class UserStaticsSOImpl extends BaseSOImpl<UserStatics> implements UserStaticsSO {

	@Autowired
	UserStaticsDao dao;
	
	@Override
	protected BaseDao<UserStatics> getDao() {
		return dao;
	}

	@Override
	public UserStatics getOne(String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		return dao.getOne(map);
	}

}
