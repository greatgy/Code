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
import com.supergenius.xo.official.entity.Recruit;

/**
 * 招聘单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RecruitDaoTest {
	
	@Autowired
	RecruitDao dao;
	
	Recruit entity = null;
	
	@Before
	public void before() {
		Recruit recruit = new Recruit();
		recruit.setUid(TestConst.uid);
		recruit.setTitle(TestConst.title);
		assertTrue(dao.insert(recruit));
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
		map.put(MapperDict.title, TestConst.title);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.title);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.title);
		List<Recruit> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setTitle(TestConst.name);
		dao.update(entity);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, TestConst.title);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.title, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testDeleteByMap() {
		String title = "title";
		for (int i = 0; i < 3; i++) {
			Recruit entity = new Recruit();
			entity.setTitle(title);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, title);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
