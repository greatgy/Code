package com.supergenius.xo.finance.dao;

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

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.finance.entity.Collect;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 收藏测试类
 * 
 * @author 娄鹏宇
 * @date 2017年9月19日10:26:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CollectDaoTest {

	@Autowired
	private CollectDao dao;

	private Collect entity;

	@Before
	public void before() {
		entity = new Collect();
		entity.setUid(TestConst.uid1);
		entity.setCreatetime(new DateTime());
		entity.setUseruid(TestConst.uid2);
		entity.setRefuid(TestConst.uid1);
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid1);
		assertTrue(result);
	}

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
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
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid1);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setRefuid(TestConst.uid);
		assertTrue(dao.update(entity));
	}
}