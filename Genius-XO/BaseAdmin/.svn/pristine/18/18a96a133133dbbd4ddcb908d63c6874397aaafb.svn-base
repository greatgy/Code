package com.genius.xo.baseadmin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.xo.baseadmin.mock.testconstants.TestConst;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AdminDaoTest {

	@Autowired
	AdminDao dao;

	Admin entity;

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getOne(java.util.Map)}.
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, "admin");
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#getList(java.util.Map)}.
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, "admin");
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		entity = new Admin();
		String uid = GlobalUtil.getUUID();
		entity.setUid(uid);
		entity.setAdminid(GlobalUtil.getUUID());
		entity.setEmail("asd.@asd.com");
		entity.setPwd(TestConst.pwd_123456);
		entity.setDopwd("123123");
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#update(java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid1);
		entity.setPwd(TestConst.pwd_123456);
		dao.update(entity);
		assertEquals(TestConst.pwd_123456, dao.get(TestConst.uid1).getPwd());
	}

	/**
	 * Test method for
	 * {@link com.mygenius.web.base.dao.BaseDao#updateFields(java.util.Map)}.
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.pwd, TestConst.pwd_123456);
		dao.updateFields(map);
		assertEquals(TestConst.pwd_123456, dao.get(TestConst.uid1).getPwd());
	}
	
	@Test
	public void testGetOneByAdminid() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminid","admin");
		assertNotNull(dao.getOne(map));
	}


}
