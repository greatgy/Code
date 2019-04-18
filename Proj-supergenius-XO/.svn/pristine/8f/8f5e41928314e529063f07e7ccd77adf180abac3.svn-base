package com.supergenius.xo.user.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.user.entity.UserInfo;

/**
 * 用户信息SO
 * 
 * @author LiuBin
 * @date 2016-3-23 下午6:19:47
 */
public interface UserInfoSO extends BaseSO<UserInfo> {

	/**
	 * 修改头像，大中小图
	 * 
	 * @param uid
	 * @param paths
	 * @return
	 */
	boolean updateAvatar(String uid, String[] paths);

	/**
	 * 更新身份证号
	 * 
	 * @param identityid
	 * @return
	 */
	boolean updateIdentityID(String uid, String identityid);

	/**
	 * 根据身份证号得到user
	 * 
	 * @param identutyid
	 * @return
	 */
	boolean getByIdentityID(String uid, String identityid);
	
}
