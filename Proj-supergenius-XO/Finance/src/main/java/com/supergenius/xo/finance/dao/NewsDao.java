package com.supergenius.xo.finance.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.finance.entity.News;

/**
 * 消息Dao
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:50:20
 */
@Component("financeNewsDao")
public interface NewsDao extends BaseDao<News>{

	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
