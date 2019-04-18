package com.supergenius.xo.startup.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.startup.entity.Inbox;

/**
 * 收件箱单元测试类
 * @author yangguang
 * @date 2017年8月29日15:59:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InboxDaoTest {

	@Autowired
	InboxDao dao;
	
	Inbox entity;
	
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
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Inbox();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid2);
		entity.setNewsuid(TestConst.uid3);
		entity.setIsread(false);
		entity.setType(EMsg.comment);
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
		entity = new Inbox();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid2);
		entity.setNewsuid(TestConst.uid3);
		entity.setIsread(false);
		entity.setType(EMsg.comment);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, TestConst.uid1);
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.userid, TestConst.uid2);
		dao.updateFields(map);
		assertEquals(TestConst.uid2, dao.get(TestConst.uid1).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdatebyUseruid() {
		entity = new Inbox();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid2);
		entity.setNewsuid(TestConst.uid3);
		entity.setIsread(false);
		entity.setType(EMsg.comment);
		entity.setStatus(EStatus.enable);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EStatus.disable);
		map.put(MapperDict.useruid, TestConst.uid2);
		dao.updateByUseruid(map);
		assertEquals(EStatus.disable, dao.get(TestConst.uid1).getStatus());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Inbox();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid2);
		entity.setNewsuid(TestConst.uid3);
		entity.setIsread(false);
		entity.setType(EMsg.comment);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
