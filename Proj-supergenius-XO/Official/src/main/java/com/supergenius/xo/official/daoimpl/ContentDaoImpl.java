package com.supergenius.xo.official.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.official.dao.ContentDao;
import com.supergenius.xo.official.entity.Content;

/**
 * 网站内容Dao实现
 * 
 * @author Liuxiaoke
 */
@Component("officialContentDaoImpl")
public class ContentDaoImpl extends BaseMongoDaoImpl<Content> implements ContentDao {

}
