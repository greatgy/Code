package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.MongoDict;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.entity.Article;

/**
 * 测试类 测试Or
 * 
 * @author Liuxiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ArticleDaoTest {

	@Autowired
	ArticleDao dao;

	Article entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Article();
		entity.setUid(TestConst.uid);
		entity.setTitle(TestConst.name);
		entity.setAuthor(TestConst.uid1);
		entity.setUpdatetime(DateTime.now());
		
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.Author, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.Author, TestConst.uid1);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.Author, TestConst.uid1);
		List<Article> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String Title = entity.getTitle();
		entity.setTitle(TestConst.name1);
		assertTrue(dao.update(entity));
		Article tempadArticle = dao.get(TestConst.uid);
		assertEquals(TestConst.name1, tempadArticle.getTitle());
		entity.setTitle(Title);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String Author = entity.getAuthor();
		map.put(MapperDict.Author, TestConst.uid2);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		map.put(MapperDict.Author, Author);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String Author = "Author";
		for (int i = 0; i < size; i++) {
			Article entity = new Article();
			entity.setAuthor(Author);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.Author, Author);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	@SuppressWarnings("unused")
	private List<Article> searchOr(String str, Pager pager) {
		Map<?, ?> clause1 = MapBuilder.start(MapperDict.ctype, str).get();
		Map<?, ?> clause2 = MapBuilder.start(MapperDict.ntype, str).get();
		
		List<Object> or = new ArrayList<>();
		or.add(clause1);
		or.add(clause2);
		
		DBObject querry = new BasicDBObject(MongoDict.$or, or);
		return dao.getList(querry, pager);
	}

}
