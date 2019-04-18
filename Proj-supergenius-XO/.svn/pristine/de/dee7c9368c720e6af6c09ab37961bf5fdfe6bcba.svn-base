package com.supergenius.xo.admin.service;

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
import com.supergenius.xo.admin.constant.TestConstant;
import com.supergenius.xo.admin.entity.EmailLog;

/**
 * 评论的单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class EmailLogServiceTest {

	@Autowired
	EmailLogSO so;
	
	EmailLog entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = so.get(TestConstant.uid);
		assertNotNull(entity);
		assertEquals(TestConstant.uid, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConstant.uid);
		assertNotNull(so.getOne(map));
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConstant.uid);
		assertTrue(so.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		Map<String, Object> map = new HashMap<>();
		map.put(TestConstant.uid, "好人");
		entity = new EmailLog();
		entity.setContent("测试");
		entity.setAdminid(TestConstant.uid2);
		entity.setUid(TestConstant.uid2);
		entity.setDataMap(map);
		entity.setSender("好人");
		so.add(entity);
		String uid = entity.getUid();
		assertNotNull(so.get(uid));
		so.delete(uid);
		assertNull(so.get(uid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = so.get(TestConstant.uid);
		entity.setContent("坏人");
		so.update(entity);
		assertEquals("坏人", so.get(TestConstant.uid).getContent());
	}

//	/**
//	 * Test UpdateFields()
//	 */
//	@Test
//	public void testUpdateFields() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("title", "nice");
//		map.put("content", "haoren");
//		so.updateFields(map);
//		assertEquals("haoren", so.get(TestConstant.uid).getContent());
//	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		Map<String, Object> map = new HashMap<>();
		map.put(TestConstant.uid, "好人");
		entity = new EmailLog();
		entity.setContent("测试");
		entity.setAdminid(TestConstant.uid2);
		entity.setUid(TestConstant.uid2);
		entity.setDataMap(map);
		entity.setSender("好人");
		so.add(entity);
		assertNotNull(so.get(TestConstant.uid2));
		so.delete(TestConstant.uid2);
		assertNull(so.get(TestConstant.uid2));
	}
	
}
