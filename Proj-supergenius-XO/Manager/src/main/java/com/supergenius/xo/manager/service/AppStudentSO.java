package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppStudent;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.UserInfo;

/** 
 * 申请学员SO
 * @author chenminchang
 * @date 2016-3-21 下午5:01:57 
 */
public interface AppStudentSO extends BaseSO<AppStudent> {
	
	/**
	 * 根据useruid得到对象
	 * @param userUid
	 * @author liubin
	 * @createtime 2016-8-18下午3:40:47
	 * @return AppStudent
	 */
	AppStudent getOne(String userUid);

	/**
	 * 通过用户的uid获取申请表的uid
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-17 下午3:19:08
	 * @param major 
	 */
	AppStudent getOne(String uid, EMajor major);

	/**
	 * 申请学员成功插入记录
	 * @param appStudent
	 * @param student
	 * @return
	 * @author liubin
	 * @createtime 2016-9-1下午4:51:05
	 * @return boolean
	 */
	boolean add(AppStudent appStudent, Student student, UserInfo userInfo);
	
	/**
	 * 得到指定状态的AppStudent对象
	 * @param uid
	 * @param major
	 * @return
	 * @author liubin
	 * @createtime 2016-9-21下午5:26:58
	 * @return AppStudent
	 */
	AppStudent getOne(String userUid, EMajor major, EStatus status);
	
	/**
	 * 根据指定状态获取count
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午3:06:52
	 */
	int getCount(EStatus status);
	
	/**
	 * 根据搜索条件获得count
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午7:20:36
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据搜索条件获得List
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午7:21:19
	 */
	List<AppStudent> search(Map<String, Object> map);
	
	/**
	 * 后台更新申请表
	 * @param appStudent
	 * @param adminLog
	 * @return
	 * @author chenminchang
	 * @create 2016-10-24下午8:21:40
	 */
	boolean update(AppStudent appStudent, AdminLog adminLog);
	
	/**
	 * 同意学员申请，对appstudent和student还有user进行学员身份增加
	 * @param appStudent
	 * @param student
	 * @param user
	 * @param adminLog
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27下午4:34:06
	 */
	boolean updateStatusAndType(AppStudent appStudent, Student student, User user, UserLevel userlevel, AdminLog adminLog);
}
