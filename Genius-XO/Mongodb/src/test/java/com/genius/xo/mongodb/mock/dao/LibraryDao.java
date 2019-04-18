package com.genius.xo.mongodb.mock.dao;

import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.genius.xo.mongodb.mock.model.Library;

/**
 * librarydao模拟测试dao接口
 * 
 * @author architect.bian
 * @createtime 2015-1-9 上午10:08:37
 */
public interface LibraryDao extends BaseMongoDao<Library> {

	void drop();
	
}
