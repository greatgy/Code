package com.supergenius.xo.life.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 问题测试类
 * 
 * @Author:JiaShitao
 * @Date:2018年5月7日下午7:07:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ProblemDaoTest {
	@Autowired
	private ProblemDao dao;
	private Problem entity;
	
	@Before
	public void before() {
		entity = new Problem();
		entity.setUid(TestConst.uid);
		entity.setCid(TestConst.oid);
		entity.setCataloguename(TestConst.name);
		entity.setUseruid(TestConst.uid);
		entity.setUsername(TestConst.name);
		entity.setContent("content内容");
		entity.setTitle("title标题");
		entity.setPlace("place行万里路模块目的地");
		entity.setAdminuid(TestConst.uid1);
		entity.setToptime(new DateTime());
		entity.setCreatetime(new DateTime());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		assertTrue(dao.getList(map).size() > 0);
	}
	

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Problem();
		entity.setUid(TestConst.uid1);
		entity.setCid(TestConst.oid);
		entity.setCataloguename(TestConst.name);
		entity.setUseruid(TestConst.uid);
		entity.setUsername(TestConst.name);
		entity.setContent("content内容");
		entity.setTitle("title标题");
		entity.setPlace("place行万里路模块目的地");
		entity.setAdminuid(TestConst.uid1);
		entity.setToptime(new DateTime());
		entity.setCreatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Problem();
		entity.setUid(TestConst.uid1);
		entity.setCid(TestConst.oid);
		entity.setCataloguename(TestConst.name);
		entity.setUseruid(TestConst.uid);
		entity.setUsername(TestConst.name);
		entity.setContent("content内容");
		entity.setTitle("title标题");
		entity.setPlace("place行万里路模块目的地");
		entity.setAdminuid(TestConst.uid1);
		entity.setToptime(new DateTime());
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setContent("李四李四李四李四李四文章的测试内容");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
}
