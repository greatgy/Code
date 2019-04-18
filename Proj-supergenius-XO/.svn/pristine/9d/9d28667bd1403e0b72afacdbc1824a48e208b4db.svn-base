package com.supergenius.xo.user.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.enums.EScore;

/**
 * 积分SO
 * 
 * @author liubin
 * @date 2016-7-18 下午2:33:01
 */
public interface ScoreSO extends BaseSO<Score> {

	/**
	 * 根据用户的uid和积分类型
	 * 
	 * @param useruid
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	Score getOne(String useruid, EScore type);

	/**
	 * 更新用户单个类型总积分
	 * 
	 * @param useruid
	 * @param score
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	boolean updateTotal(String useruid, int total, EScore type);

	/**
	 * 根据类型得到用户的总积分
	 * 
	 * @param useruid
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	int getTotalByTypes(String useruid, EScore... types);

	/**
	 * 获取用户总收入的积分（原创文章+转载文章获得的积分）
	 * 
	 * @param useruid
	 * @return
	 * @author chenminchang
	 */
	int getTotal(String useruid);
}
