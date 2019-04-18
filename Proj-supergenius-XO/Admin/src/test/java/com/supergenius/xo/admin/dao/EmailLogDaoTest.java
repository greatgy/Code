package com.supergenius.xo.admin.dao;

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
public class EmailLogDaoTest {

	@Autowired
	EmailLogDao dao;
	
	EmailLog entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConstant.uid);
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
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConstant.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new EmailLog();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recever1", "haoren1");
		map.put("recever2", "haoren2");
		map.put("recever3", "haoren3");
		map.put("recever4", "haoren4");
		entity.setContent("测试");
		entity.setAdminid(TestConstant.uid2);
		entity.setUid(TestConstant.uid2);
//		entity.setDataMap("好人");
		entity.setDataMap(map);
		entity.setSender("好人");
		dao.insert(entity);
		System.out.println(entity.getReceiver());
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
		entity = dao.get(TestConstant.uid);
		entity.setContent("坏人");
		dao.update(entity);
		assertEquals("坏人", dao.get(TestConstant.uid).getContent());
	}

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
		dao.insert(entity);
		assertNotNull(dao.get(TestConstant.uid2));
		dao.delete(TestConstant.uid2);
		assertNull(dao.get(TestConstant.uid2));
	}
}
