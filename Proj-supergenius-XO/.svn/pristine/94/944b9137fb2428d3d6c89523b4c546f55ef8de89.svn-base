package com.supergenius.xo.startup.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.News;

/**
 * 消息SO
 * 
 * @author yangguang
 * @date 2017年8月29日10:09:28
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
	 * @author 许志翔
	 * @date 2017年9月8日15:08:39
	 */
	List<News> getListByUseruid(String useruid, Pager pager);
}
