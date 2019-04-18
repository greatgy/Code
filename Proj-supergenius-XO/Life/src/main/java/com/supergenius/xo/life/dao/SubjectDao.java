package com.supergenius.xo.life.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Subject;

/**
 * 科目Dao
 *@Author:JiaShitao
 *@Date:2018年5月7日下午5:54:10
 */
@Component("lifeSubjectDao")
public interface SubjectDao extends BaseDao<Subject> {
	
	/**
	 * 通過sid獲取
	 * @param cid
	 * @return
	 */
	Subject get(int sid);
	
	/**
	 * 通過sid刪除
	 * @param cid
	 * @return
	 */
	boolean delete(int sid);
}
