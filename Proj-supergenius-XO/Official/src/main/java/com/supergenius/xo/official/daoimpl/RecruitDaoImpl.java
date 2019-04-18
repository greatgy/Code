package com.supergenius.xo.official.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.official.dao.RecruitDao;
import com.supergenius.xo.official.entity.Recruit;

/**
 * 招聘dao实现
 * 
 * @author liushaomin
 */
@Component
public class RecruitDaoImpl extends BaseMongoDaoImpl<Recruit> implements RecruitDao {

}
