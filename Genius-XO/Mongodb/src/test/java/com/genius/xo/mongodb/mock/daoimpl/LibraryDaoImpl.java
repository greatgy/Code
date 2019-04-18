package com.genius.xo.mongodb.mock.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.genius.xo.mongodb.mock.dao.LibraryDao;
import com.genius.xo.mongodb.mock.model.Library;

/**
 * librarydao实现类
 * 
 * @author architect.bian
 * @createtime 2015-1-9 上午10:08:22
 */
@Component
public class LibraryDaoImpl extends BaseMongoDaoImpl<Library> implements LibraryDao {

	@Override
	public void drop() {
		getColl().drop();
	}

}
