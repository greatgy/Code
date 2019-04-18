package com.supergenius.xo.moralnews.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moralnews.entity.News;

/**
 * 消息SO
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
public interface NewsSO extends BaseSO<News> {

	/**
	 * 通过useruid获取news
	 * 
	 * @param useruid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月4日15:29:33
	 */
	Boolean updateByUseruid(Map<String, Object> map);
	
	/**
	 * 增加消息
	 * 
	 * @param map
	 * @return
	 * @author tf
	 */
	News add(Map<String, Object> map);

}
