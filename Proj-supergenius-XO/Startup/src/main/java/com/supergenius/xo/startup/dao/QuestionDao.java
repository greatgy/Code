package com.supergenius.xo.startup.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Question;

/**
 * 题库Dao
 * @author ChenQi
 * @date 2017年6月20日15:12:22
 */
@Component
public interface QuestionDao extends BaseDao<Question> {
	
	/**
	 * 根据某个字段修改字段值
	 * @author liubin
	 * @date 2017年6月29日 下午4:13:20
	 * @return boolean
	 */
	boolean updateByFields(Map<String, Object> map);
}
