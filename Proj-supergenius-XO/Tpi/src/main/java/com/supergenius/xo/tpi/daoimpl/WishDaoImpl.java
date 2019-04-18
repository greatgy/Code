package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.WishDao;
import com.supergenius.xo.tpi.entity.Wish;

/**
 * WishDetailDaoImpl
 * @author liushaomin
 */
@Component
public class WishDaoImpl extends BaseMongoDaoImpl<Wish> implements WishDao{

}
