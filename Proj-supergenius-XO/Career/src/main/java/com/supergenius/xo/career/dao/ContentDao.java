package com.supergenius.xo.career.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.career.entity.Content;

/**
 * 评论Dao
 * 
 * @author ChenQi
 * @date 2017年8月23日11:16:10
 */
@Component("careerContentDao")
public interface ContentDao extends BaseDao<Content> {

}
