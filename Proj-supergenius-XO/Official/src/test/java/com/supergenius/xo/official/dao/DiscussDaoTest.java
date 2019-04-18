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
import com.supergenius.xo.official.entity.Discuss;

/**
 * 评论互动单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class DiscussDaoTest {
	
	@Autowired
	DiscussDao dao;
	
	Discuss entity = null;
	
	@Before
	public void before() {
		Discuss discuss = new Discuss();
		discuss.setUid(TestConst.uid);
		discuss.setFromname(TestConst.name);
		assertTrue(dao.insert(discuss));
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
		map.put(MapperDict.fromname, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromname, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromname, TestConst.name);
		List<Discuss> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setFromname(TestConst.newname);
		dao.update(entity);
		assertEquals(TestConst.newname, dao.get(TestConst.uid).getFromname());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.fromname, TestConst.name);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getFromname());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Discuss entity = new Discuss();
			entity.setFromname(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.fromname, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
