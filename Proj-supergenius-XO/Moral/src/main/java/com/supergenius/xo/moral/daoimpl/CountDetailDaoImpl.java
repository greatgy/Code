package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.CountDetailDao;
import com.supergenius.xo.moral.entity.CountDetail;

/**
 * 点赞、收藏明细Dao实现
 * 
 * @author LiJiacheng
 */
@Component
public class CountDetailDaoImpl extends BaseMongoDaoImpl<CountDetail> implements CountDetailDao {

}
