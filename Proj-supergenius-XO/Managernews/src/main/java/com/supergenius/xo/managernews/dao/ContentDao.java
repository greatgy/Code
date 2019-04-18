package com.supergenius.xo.managernews.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.managernews.entity.Content;

/**
 * 内容Dao
 * 
 * @author tf
 * @date 2018年7月5日
 */
@Component("managernewsContentDao")
public interface ContentDao extends BaseDao<Content> {

}
