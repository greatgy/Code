package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.UserStatisticsDao;
import com.supergenius.xo.moral.entity.UserStatistics;

/**
 * 用户统计dao的实现
 * 
 * @author liushaomin
 */
@Component
public class UserStatisticsDaoImpl extends BaseMongoDaoImpl<UserStatistics> implements UserStatisticsDao {

}
