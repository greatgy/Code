package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.ContentDao;
import com.supergenius.xo.tpi.entity.Content;


/**
 * ContentDaoImpl
 * @author liushaomin
 */
@Component
public class ContentDaoImpl extends BaseMongoDaoImpl<Content> implements ContentDao{

}
