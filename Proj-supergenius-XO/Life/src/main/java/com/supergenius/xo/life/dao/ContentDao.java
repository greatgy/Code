package com.supergenius.xo.life.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Content;

/**
 * 内容Dao
 * 
 * @Author:JiaShitao
 * @Data:2018年5月7日下午
 */
@Component("lifeContentDao")
public interface ContentDao extends BaseDao<Content> {
	
}
