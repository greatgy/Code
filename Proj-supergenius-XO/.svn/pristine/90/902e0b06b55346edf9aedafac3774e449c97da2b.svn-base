package com.supergenius.xo.tpi.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.MongoDict;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.dao.ArticleDao;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;

/**
 * 用户Dao实现
 * 
 * @author ShangJianguo
 */
@Component
public class ArticleDaoImpl extends BaseMongoDaoImpl<Article> implements ArticleDao {

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.ArticleDao#search(java.lang.String, com.genius.model.base.entity.Pager)
	 * @author: ShangJianguo
	 * 2015-2-8 下午6:19:16
	 */
	@Override
	public List<Article> search(String keyword, Pager pager, EArticleChannel channel) {
		Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        DBObject qtitle = new BasicDBObject();
        qtitle.put(MapperDict.title, pattern);
        
        DBObject qcontent = new BasicDBObject();
        qcontent.put(MapperDict.content, pattern);
        
        BasicDBList dbList = new BasicDBList();
        dbList.add(qtitle);
        dbList.add(qcontent);
        
        DBObject query = new BasicDBObject();
        query.put(MongoDict.$or, dbList);
        
        DBObject qchannel = new BasicDBObject(MapperDict.channel, channel.toString());
        BasicDBList dbList2 = new BasicDBList();
        dbList2.add(query);
        dbList2.add(qchannel);
        
        return getList(new BasicDBObject(MongoDict.$and, dbList2), pager);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.ArticleDao#getList(java.lang.String, com.genius.model.base.entity.Pager)
	 * @author Liuxiaoke
	 */
	@Override
	public List<Article> getList(String typename, Pager pager) {
		Map<?, ?> clause1 = MapBuilder.start(MapperDict.ctype, typename).get();
		Map<?, ?> clause2 = MapBuilder.start(MapperDict.ntype, typename).get();
		
		List<Object> or = new ArrayList<>();
		or.add(clause1);
		or.add(clause2);
		
		DBObject query = new BasicDBObject(MongoDict.$or, or);
		return getList(query, pager);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.tpi.dao.ArticleDao#getCount(java.lang.String)
	 * @author Liuxiaoke
	 */
	@Override
	public int getCount(String typename) {
		Map<?, ?> clause1 = MapBuilder.start(MapperDict.ctype, typename).get();
		Map<?, ?> clause2 = MapBuilder.start(MapperDict.ntype, typename).get();
		
		List<Object> or = new ArrayList<>();
		or.add(clause1);
		or.add(clause2);
		
		DBObject query = new BasicDBObject(MongoDict.$or, or);
		return getCount(query);
	}
}
