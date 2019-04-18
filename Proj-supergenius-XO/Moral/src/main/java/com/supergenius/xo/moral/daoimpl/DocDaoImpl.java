package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.DocDao;

import com.supergenius.xo.moral.entity.Doc;

/**
 * 讲义DAO实现
 * 
 * @author LiJiacheng
 */
@Component
public class DocDaoImpl extends BaseMongoDaoImpl<Doc> implements DocDao {

}
