package com.supergenius.xo.enterpriser.dao;

import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.enterpriser.entity.Lecture;

/** 
 *  讲座Dao
 * @author chenminchang
 * @date 2016-10-24 下午4:23:17 
 */
public interface LectureDao extends BaseDao<Lecture> {

	/**
	 * 根据sn获取讲座数，group by sn
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-25下午1:53:26
	 */
	int getLectureCount(Map<String, Object> map);
}
