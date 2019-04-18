package com.supergenius.xo.startup.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Content;

/**
 * 评论Dao
 * 
 * @author ChenQi
 * @date 2017年8月23日11:16:10
 */
@Component("startupContentDao")
public interface ContentDao extends BaseDao<Content> {

}
