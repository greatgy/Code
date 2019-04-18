package com.supergenius.xo.sudokuapi.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.sudokuapi.entity.Users;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 用户SO
 * 
 * @author LiJiacheng
 */
public interface UsersSO extends BaseSO<Users> {

	/**
	 * 冻结、解冻用户
	 * 
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean update(EGameStatus eStatus, String ids);

	/**
	 * 更新积分和天才币
	 * 
	 * @param where
	 * @param fields
	 * @return
	 * @author LiJiacheng
	 */
	boolean updatePoints(Map<String, Object> where, Map<String, Object> fields);

	/**
	 * 修改用户积分的时候，对应更新当前用户的段位
	 * 
	 * @param newPoint
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateGrades(Map<String, Object> where, int newPoint, List<Map<String, Object>> allMaps);
	
	/**
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 * @author ChenQi
	 */
	boolean isExistEmail(String email);

	/**
	 * 验证用户名是否存在
	 * 
	 * @param account
	 * @return
	 * @author ChenQi
	 */
	boolean isExistAccount(String account);

	/**
	 * 验证用户名是否存在
	 *
	 * @param account
	 * @return
	 * @author ChenQi
	 */
	boolean isExistAccountName(String account);
	/**
	 * 验证昵称是否存在
	 * 
	 * @param nickname
	 * @return
	 * @author ChenQi
	 */
	boolean isExistName(String name);
	
	/**
	 * 根据用户名获得用户
	 * 
	 * @param account
	 * @return
	 * @author ChenQi
	 */
	Users getByAccount(String account);
	
	/**
	 * 修改用户名
	 * @param oldAccount
	 * @param newAccount
	 * @return
	 * @CreateTime  2018年5月21日--下午3:48:49
	 * @Author  LiuBin
	 */
	boolean update(String oldAccount, String newAccount);
	
	/**
	 * 根据分数得到数量
	 * @param score
	 * @return
	 * @CreateTime  2018年6月20日--上午10:16:44
	 * @Author  LiuBin
	 */
	long getCount(int score);
}
