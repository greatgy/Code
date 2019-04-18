package com.supergenius.xo.career.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.News;

/**
 * 消息SO
 * 
 * @author ChenQi
 * @date 2017年11月13日16:49:59
 */
public interface NewsSO extends BaseSO<News> {

	/**
	 * 增加消息
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 */
	News add(Map<String, Object> map);
	
	/**
	 * 通过useruid获取news
	 * @param useruid
	 * @return
	 * @author 杨光
	 * @date 2017年9月20日09:49:02
	 */
	Boolean updateByUseruid(Map<String, Object> map);

	/**
	 * 获取指定useruid的指定消息组的未读消息数量
	 * @param useruid
	 * @return
	 */
	int getCountByUnRead(String uid);
}
