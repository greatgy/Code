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
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.enums.EFollow;
import com.supergenius.xo.mock.testconstants.TestConst;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SubscribeDaoTest {

	@Autowired
	private SubscribeDao dao;
	
	private Subscribe entity;
	
	@Before
	public void before(){
		entity = new Subscribe();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid2);
		entity.setRefuseruid(TestConst.uid1);
		entity.setCreatetime(new DateTime());
		entity.setFollow(EFollow.draft);
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
		entity = dao.get(TestConst.uid);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setUseruid(TestConst.uid2);
		assertTrue(dao.update(entity));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = dao.get(TestConst.uid);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("refuseruid", TestConst.uid2);
		assertTrue(dao.updateFields(map));
	}
}


