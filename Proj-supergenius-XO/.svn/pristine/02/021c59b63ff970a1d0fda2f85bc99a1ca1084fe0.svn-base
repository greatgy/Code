package com.supergenius.moral.moral.dao;

import java.util.List;

import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.supergenius.xo.moral.entity.Exam;
import com.supergenius.xo.moral.enums.EExam;

/**
 * 考试DAO
 * 
 * @author LiJiacheng
 */
public interface ExamDao extends BaseMongoDao<Exam> {

	/**
	 * 去重复获取考试通过的人数
	 * 
	 * @param type
	 * @param score
	 * @return
	 * @author LiJiacheng
	 */
	List<?> getDistinct(EExam type, int score);

}
