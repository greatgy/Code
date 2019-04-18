package com.supergenius.xo.tpi.dao;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.supergenius.xo.tpi.entity.Project;

/**
 * 项目Dao
 * 
 * @author ShangJianguo
 */
public interface ProjectDao extends BaseMongoDao<Project> {
	
	/**
	 * 进行模糊查询
	 * @param name 项目名
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Project> search(String name, Pager pager);
}
