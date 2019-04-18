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
import com.supergenius.xo.official.entity.Banner;

/**
 * Banner单元测试
 * @title LiuXiaoke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class BannerDaoTest {
	
	@Autowired
	BannerDao dao;
	
	Banner entity = null;
	
	@Before
	public void before() {
		Banner banner = new Banner();
		banner.setUid(TestConst.uid);
		banner.setTitle(TestConst.name);
		assertTrue(dao.insert(banner));
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
		map.put(MapperDict.title, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, TestConst.name);
		List<Banner> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setTitle(TestConst.newname);
		dao.update(entity);
		assertEquals(TestConst.newname, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, TestConst.name);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Banner entity = new Banner();
			entity.setTitle(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.title, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
}
