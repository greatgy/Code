package com.supergenius.xo.tpi.daoimpl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.dao.ProjectDao;
import com.supergenius.xo.tpi.entity.Project;


/**
 * 项目Dao实现
 * 
 * @author ShangJianguo
 */
@Component
public class ProjectDaoImpl extends BaseMongoDaoImpl<Project> implements ProjectDao{

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.ProjectDao#search(java.lang.String)
	 * @author: ShangJianguo
	 * 2015-2-8 下午3:28:38
	 */
	@Override
	public List<Project> search(String name, Pager pager) {
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        DBObject query = new BasicDBObject();
        query.put(MapperDict.name, pattern);
        return getList(query, pager);
	}

}
