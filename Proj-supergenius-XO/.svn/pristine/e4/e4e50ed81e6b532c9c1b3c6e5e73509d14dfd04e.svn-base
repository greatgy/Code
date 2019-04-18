package com.supergenius.xo.official.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.entity.Article;

/**
 * 文章（新闻）单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ArticleDaoTest {
	
	@Autowired
	ArticleDao dao;
	
	Article entity = null;
	
	@Before
	public void before() {
		Article article = new Article();
		article.setUid(TestConst.uid);
		article.setAuthor(TestConst.name);
		assertTrue(dao.insert(article));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.author, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.author, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.author, TestConst.name);
		List<Article> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setAuthor(TestConst.newname);
		dao.update(entity);
		assertEquals(TestConst.newname, dao.get(TestConst.uid).getAuthor());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.author, TestConst.name);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getAuthor());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "nametest";
		for (int i = 0; i < 3; i++) {
			Article entity = new Article();
			entity.setAuthor(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.author, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
