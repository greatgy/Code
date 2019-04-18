package com.supergenius.xo.tpi.daoimpl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.entity.Team;

/**
 * TeamDaoImpl
 * @author liushaomin
 */
@Component
public class TeamDaoImpl extends BaseMongoDaoImpl<Team> implements TeamDao {
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.TeamDao#search(java.lang.String, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-8 下午6:06:51
	 */
	@Override
	public List<Team> search(String name, Pager pager) {
		Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        DBObject query = new BasicDBObject();
        query.put(MapperDict.name, pattern);
        return getList(query, pager);
	}
}
