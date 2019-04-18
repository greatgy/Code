package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.TypeDao;
import com.supergenius.xo.tpi.entity.Type;

/**
 * type的实现
 * @author liushaomin
 */
@Component
public class TypeDaoImpl extends BaseMongoDaoImpl<Type> implements TypeDao{

}
