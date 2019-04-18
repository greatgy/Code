package com.supergenius.xo.manager.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.UserStatics;

/** 
 * 用户统计SO
 * @author guanshiqian
 * @date 2016-4-27 下午12:18:55 
 */
public interface UserStaticsSO extends BaseSO<UserStatics> {
	
	/**
	 * 根据useruid得到UserStatics对象
	 * @param userUid
	 * @author liubin
	 * @createtime 2016-8-23下午5:54:01
	 * @return UserStatics
	 */
	UserStatics getOne(String userUid);
}
