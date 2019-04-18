package com.supergenius.xo.tpi.dao;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.tpi.entity.Team;

/**
 * TeamDao
 * @author liushaomin
 * @modifier ShangJianguo
 */
public interface TeamDao extends BaseDao<Team>{

	/**
	 * 进行模糊查询
	 * @param team 团队名称
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Team> search(String name, Pager pager);
}
