package com.supergenius.xo.tpi.daoimpl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TpiUserDao;
import com.supergenius.xo.tpi.entity.TpiUser;

/**
 * 用户Dao实现
 * 
 * @author ShangJianguo
 */
@Component
public class TpiUserDaoImpl extends BaseMongoDaoImpl<TpiUser> implements TpiUserDao {

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.TpiUserDao#search(java.lang.String, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-8 下午4:52:05
	 */
	@Override
	public List<TpiUser> search(String username, Pager pager) {
		Pattern pattern = Pattern.compile(username, Pattern.CASE_INSENSITIVE);
        DBObject query = new BasicDBObject();
        query.put(MapperDict.username, pattern);
        return getList(query, pager);
	}

}
