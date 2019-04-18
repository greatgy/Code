package com.supergenius.xo.finance.service;

import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.finance.entity.News;
import com.supergenius.xo.finance.enums.EFinanceMsg;

/**
 * 消息so
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:55:19
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
	 * @param typegroup
	 * @return
	 */
	int getCountByUnRead(String useruid, EFinanceMsg typegroup);
}
