package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.JoinrecordsDao;
import com.supergenius.xo.sudokuapi.dao.PointsrecordsDao;
import com.supergenius.xo.sudokuapi.dao.PropsDao;
import com.supergenius.xo.sudokuapi.dao.PurchaserecordsDao;
import com.supergenius.xo.sudokuapi.dao.UsersDao;
import com.supergenius.xo.sudokuapi.entity.Users;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;
import com.supergenius.xo.sudokuapi.service.UsersSO;

/**
 * 用户SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class UsersSOImpl extends BaseSOImpl<Users> implements UsersSO {

	@Autowired
	UsersDao usersDao;
	@Autowired
	JoinrecordsDao joinrecordsDao;
	@Autowired
	PointsrecordsDao pointsrecordsDao;
	@Autowired
	PurchaserecordsDao purchaserecordsDao;
	@Autowired
	PropsDao propsDao;
	
	public final static String ISACCOUNT = "isaccount";

	@Override
	protected BaseDao<Users> getDao() {
		return usersDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.UsersSO#update(com.genius.model.base.enums.EGameStatus, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-23 下午12:10:00
	 */
	@Override
	public boolean update(EGameStatus eStatus, String ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, eStatus);
		map.put(MapperDict.uid, ids);
		return usersDao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.UsersSO#updatePoints(java.util.Map, java.util.Map)
	 * 
	 * @author: LiJiacheng 2015-9-23 下午4:02:59
	 */
	@Override
	public boolean updatePoints(Map<String, Object> where, Map<String, Object> fields) {
		return usersDao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.service.UsersSO#updateGrades(int)
	 * 
	 * @author: LiJiacheng 2015-11-12 上午10:23:04
	 */
	@Override
	public boolean updateGrades(Map<String, Object> where, int newPoint, List<Map<String, Object>> allMaps) {
		Map<String, Object> gradeMap = new HashMap<>();
		for (Map<String, Object> map : allMaps) {
			if (newPoint < Float.parseFloat(map.get(MapperDict.floor).toString())) {
				gradeMap.put(MapperDict.grade, Integer.parseInt(map.get(MapperDict.code).toString()) - 1);
				break;
			} else if (allMaps.size() - 1 == allMaps.indexOf(map)) {
				gradeMap.put(MapperDict.grade, Integer.parseInt(map.get(MapperDict.code).toString()));
			}
		}
		return usersDao.update(where, gradeMap);
	}
	
	/**
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 * @author ChenQi
	 */
	@Override
	public boolean isExistEmail(String email) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.email, email);
		map.put(MapperDict.status + MapperDict.suffix_notEqual_key, EGameStatus.disable);
		if(usersDao.getOne(map) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 验证邮箱是否存在
	 *
	 * @param email
	 * @return
	 * @author ChenQi
	 */
	@Override
	public boolean isExistAccount(String account) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.account, account);
		map.put(MapperDict.status + MapperDict.suffix_notEqual_key, EGameStatus.disable);
		if(usersDao.getOne(map) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 验证邮箱是否存在
	 *
	 * @param email
	 * @return
	 * @author ChenQi
	 */
	@Override
	public boolean isExistAccountName(String accountName) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.accountname, accountName);
		map.put(MapperDict.status + MapperDict.suffix_notEqual_key, EGameStatus.disable);
		if(usersDao.getOne(map) == null) {
			return false;
		}
		return true;
	}
	/**
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 * @author ChenQi
	 */
	@Override
	public boolean isExistName(String name) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.name, name);
		map.put(MapperDict.status + MapperDict.suffix_notEqual_key, EGameStatus.disable);
		if(usersDao.getOne(map) == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 根据用户名获得用户
	 * 
	 * @param account
	 * @return
	 * @author ChenQi
	 */
	@Override
	public Users getByAccount(String account) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.account, account);
		map.put(MapperDict.status + MapperDict.suffix_notEqual_key, EGameStatus.disable);
		return usersDao.getOne(map);
	}

	@Override
	public boolean update(String oldAccount, String newAccount) {
		Map<String, Object> where = getParamMap(true);
		Map<String, Object> fields = new HashMap<>();
		Map<String, Object> fields2 = new HashMap<>();
		where.put(MapperDict.account, oldAccount);
		fields.put(MapperDict.account, newAccount);
		fields2.put(MapperDict.account, newAccount);
		fields.put(ISACCOUNT, 0);
		return usersDao.update(where, fields) && propsDao.update(where, fields2) && joinrecordsDao.update(where, fields2) && pointsrecordsDao.update(where, fields2) && purchaserecordsDao.update(where, fields2);
	}

	@Override
	public long getCount(int score) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.points + BaseMapperDict.suffix_greater_key, score);
		return usersDao.getCount(map);
	}

}