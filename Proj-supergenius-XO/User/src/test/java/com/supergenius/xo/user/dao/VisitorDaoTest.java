package com.supergenius.xo.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.Visitor;

/**
 * Visitor测试
 * 
 * @author xuzhixiang
 * @date 2017年8月2日15:46:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class VisitorDaoTest {

	@Autowired
	private VisitorDao dao;

	private Visitor entity;

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
		entity = dao.getOne(map);
		assertNotNull(entity);
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
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
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Visitor();
		entity.setUid(TestConst.uid1);
		entity.setLoginip(TestConst.ip);
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
		entity = new Visitor();
		entity.setUid(TestConst.uid1);
		entity.setLoginip(TestConst.ip);
		entity.setCreatetime(new DateTime());
		dao.insert(entity);	
		String uid = entity.getUid();
		System.err.println(uid);
		assertNotNull(dao.get(uid));
		entity.setLoginip(TestConst.ip2);
		entity.setCreatetime(new DateTime());
		entity.setUseruid(TestConst.uid2);
		assertTrue(dao.update(entity));
		assertEquals(TestConst.ip2, dao.get(TestConst.uid1).getLoginip());
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Visitor();
		entity.setUid(TestConst.uid1);
		entity.setLoginip(TestConst.ip);
		entity.setCreatetime(new DateTime());
		dao.insert(entity);	
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

}