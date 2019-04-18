package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.TpiContentDao;
import com.supergenius.xo.tpi.entity.TpiContent;


/**
 * ContentDaoImpl
 * @author liushaomin
 */
@Component
public class TpiContentDaoImpl extends BaseMongoDaoImpl<TpiContent> implements TpiContentDao{

}
