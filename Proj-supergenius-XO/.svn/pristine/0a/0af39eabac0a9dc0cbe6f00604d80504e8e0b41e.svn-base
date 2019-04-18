package com.supergenius.xo.moral.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.UserStatistics;

/**
 * 用户统计so
 * 
 * @author liushaomin
 */
public interface UserStatisticsSO extends BaseSO<UserStatistics> {

	/**
	 * 更新文件下载量
	 * 
	 * @param student
	 * @return
	 * @author YuYingJie
	 */
	boolean updateCountdl(String useruid, int countdl);

	/**
	 * 更新上传量
	 * 
	 * @param useruid
	 * @param countupload
	 * @return
	 * @author YuYingJie
	 */

	boolean updateCountupload(String useruid, int countupload);

	/**
	 * 根据useruid获取
	 * 
	 * @param useruid
	 * @return
	 * @author liushaomin
	 */
	UserStatistics getOneByUseruid(String useruid);

	/**
	 * 更新积分
	 * 
	 * @param useruid
	 * @param score
	 * @return
	 * @author YuYingJie
	 */
	boolean updateScore(String useruid, int score);

	/**
	 * 获取积分前10名的会员
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<UserStatistics> getTopTenScore(Map<String, Object> map);

	/**
	 * 添加连续签到的天数
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateCountDays(String useruid);

	/**
	 * 将连续签到的天数置为1
	 * 
	 * @param useruid
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateDayOne(String useruid);

	/**
	 * 更新剩余考试时间
	 * 
	 * @param useruid
	 * @param updatatime
	 * @return
	 * @author liushaomin
	 */
	boolean updateExamTime(String useruid, DateTime updatatime);

	/**
	 * 将连续签到的天数更新成为0
	 * 
	 * @param map
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateSignZero(String uid);

}
