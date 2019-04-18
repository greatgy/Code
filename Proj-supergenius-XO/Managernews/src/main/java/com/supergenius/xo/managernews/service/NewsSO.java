package com.supergenius.xo.managernews.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.managernews.entity.News;

/**
 * 消息SO
 * 
 * @author tf
 * @date 2018年7月5日
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

}
