package com.supergenius.xo.manager.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.entity.AppLeave;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 请假记录单元测试
 * @author XieMing
 * @date 2016-7-19 下午12:08:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AppLeaveDaoTest {

	@Autowired
	AppLeaveDao dao;
	
	AppLeave entity;
	
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
		entity = new AppLeave();
		entity.setUid(TestConst.uid1);
		dao.insert(entity);
		entity.setUsertype(EUser.enterpriser);
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
		entity = dao.get(TestConst.uid);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid).getStatus());
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new AppLeave();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid2);
		dao.insert(entity);
		entity.setUsertype(EUser.enterpriser);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.useruid, TestConst.uid3);
		dao.updateFields(map);
		assertEquals(TestConst.uid3, dao.get(TestConst.uid1).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new AppLeave();
		entity.setUid(TestConst.uid1);
		dao.insert(entity);
		entity.setUsertype(EUser.enterpriser);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
