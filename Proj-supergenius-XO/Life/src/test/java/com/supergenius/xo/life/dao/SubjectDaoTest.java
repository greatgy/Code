package com.supergenius.xo.life.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 科目测试类
 * 
 * @Author:JiaShitao
 * @Date:2018年5月7日下午7:07:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SubjectDaoTest {
	@Autowired
	private SubjectDao dao;
	private Subject entity;
	
	@Before
	public void before() {
		entity = new Subject();
		entity.setName(TestConst.name);
		entity.setData("data");
		entity.setSid(123);
		entity.setGrade(8);
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid1);
		entity.setCreatetime(new DateTime());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(123);
		assertTrue(result);
	}
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(123);
		assertNotNull(entity);
		assertEquals(123, entity.getSid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.cid, 123);
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
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		int sid = entity.getSid();
		assertNotNull(dao.get(sid));
		entity.setName("李四李四李四李四李四文章的测试内容");
		assertTrue(dao.update(entity));
	}
	
}
