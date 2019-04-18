package com.supergenius.xo.user.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.UserInfoDao;
import com.supergenius.xo.user.entity.UserInfo;
import com.supergenius.xo.user.service.UserInfoSO;

/**
 * 用户信息SOImpl
 * 
 * @author LiuBin
 * @date 2016-3-23 下午6:44:37
 */
@Service
public class UserInfoSOImpl extends BaseSOImpl<UserInfo> implements UserInfoSO {

	@Autowired
	private UserInfoDao dao;

	@Autowired
	protected BaseDao<UserInfo> getDao() {
		return dao;
	}

	@Override
	public boolean updateAvatar(String uid, String[] paths) {
		if (paths != null && paths.length == 3) {
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, uid);
			map.put(MapperDict.avatarbig, paths[0]);
			map.put(MapperDict.avatar, paths[1]);
			map.put(MapperDict.avatarlittle, paths[2]);
			return dao.updateFields(map);
		} else {
			return false;
		}
	}

	@Override
	public boolean updateIdentityID(String uid, String identityid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.identityid, identityid);
		return dao.updateFields(map);
	}

	@Override
	public boolean getByIdentityID(String uid, String identityid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.identityid, identityid);
		List<UserInfo> userinfo = dao.getList(map);
		if (userinfo.size() == 0) {
			return false;
		}
		if (userinfo.size() == 1 && userinfo.get(0).getUid().equals(uid)) {
			return false;
		} else {
			return true;
		}
	}

}
