package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.QuestionDao;

import com.supergenius.xo.moral.entity.Question;

/**
 * 题库DAO实现类
 * 
 * @author LiJiacheng
 */
@Component
public class QuestionDaoImpl extends BaseMongoDaoImpl<Question> implements QuestionDao {

}
