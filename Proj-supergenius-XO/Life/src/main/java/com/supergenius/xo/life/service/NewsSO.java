package com.supergenius.xo.life.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.News;

/**
 * 消息so
 * 
 * @author YangGuang
 * @date 2018年5月9日16:00:05
 */
public interface NewsSO extends BaseSO<News>{

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
	 * @author ChenQi
	 * @date 2018年1月4日15:29:33
	 */
	Boolean updateByUseruid(Map<String, Object> map);

	/**
	 * 获取指定useruid的指定消息组的未读消息数量
	 * @param useruid
	 * @return
	 */
	int getCountByUnRead(String uid);
}
