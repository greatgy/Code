package com.supergenius.xo.enterpriser.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Lecture;

/** 
 * 讲座SO
 * @author chenminchang
 * @date 2016-10-24 下午4:59:38 
 */
public interface LectureSO extends BaseSO<Lecture> {

	/**
	 * 获取可以参加报名的一条记录
	 * @param enable
	 * @return
	 * @author XieMing
	 * 2016-10-24 下午8:28:04
	 */
	Lecture getOne(EStatus status);

	/**
	 * 更新报名的人数
	 * @param lecture
	 * @return
	 * @author XieMing
	 * 2016-10-24 下午8:36:21
	 */
	boolean updateRegistercount(Lecture lecture);

	/**
	 * 获取讲座数
	 * @return
	 * @author chenminchang
	 * @create 2016-10-25下午1:54:44
	 */
	int getLectureCount(Map<String, Object> map); 
	
	/**
	 * 根据uid更新状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26上午11:37:59
	 */
	boolean updateStatus(String uid, EStatus status);
	
	/**
	 * 获取同一讲座的不同期
	 * @param sn
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午12:17:47
	 */
	List<Lecture> getListBySn(String sn);
	
	/**
	 * 更新讲座时间
	 * @param uid
	 * @param time
	 * @param adminlog
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27上午9:27:11
	 */
	boolean updateTime(String uid, DateTime time);
	
	/**
	 * 更新讲座地址
	 * @param uid
	 * @param address
	 * @param adminlog
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27上午9:33:12
	 */
	boolean updateAddress(String uid, String address);
	
	/**
	 * 更新讲座名称
	 * @param uid
	 * @param name
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27上午9:49:14
	 */
	boolean updateName(String uid, String name);
	
	/**
	 * 更新讲座名称
	 * @param uid
	 * @param file
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27上午9:49:14
	 */
	boolean updateFile(String uid, String file);
}
