package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.TpiCountDao;
import com.supergenius.xo.tpi.entity.TpiCount;

/**
 * CountDetailDaoImpl
 * @author liushaomin
 */
@Component
public class TpiCountDaoImpl extends BaseMongoDaoImpl<TpiCount> implements TpiCountDao{

}
