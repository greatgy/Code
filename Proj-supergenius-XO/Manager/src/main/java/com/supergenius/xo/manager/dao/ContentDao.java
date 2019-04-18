package com.supergenius.xo.manager.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Content;

/**
 * 网站内容Dao
 * @author chenzhixing
 */
@Component("managerContentDao")
public interface ContentDao extends BaseDao<Content> {

}
