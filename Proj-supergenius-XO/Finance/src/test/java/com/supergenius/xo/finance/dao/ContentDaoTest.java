package com.supergenius.xo.finance.dao;

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

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.finance.entity.Content;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * @author XueZhenYong
 * @date 2018年1月2日上午11:33:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ContentDaoTest {

	@Autowired
	private ContentDao dao;

	private Content entity;

	@Before
	public void before() {
		entity = new Content();
		entity.setUid(TestConst.uid);
		entity.setName("广告");
		entity.setData("广告广告");
		entity.setSummary("广告简介");
		entity.setContent("我是一条广告");
		entity.setType(EContent.economics);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
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
		map.put(BaseMapperDict.uid, TestConst.uid1);
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
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Content();
		entity.setUid(TestConst.uid2);
		entity.setName("广告");
		entity.setData("广告广告");
		entity.setSummary("广告简介");
		entity.setContent("我是一条广告");
		entity.setType(EContent.economics);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
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
		entity = new Content();
		entity.setUid(TestConst.uid2);
		entity.setName("广告");
		entity.setData("广告广告");
		entity.setSummary("广告简介");
		entity.setContent("我是一条广告");
		entity.setType(EContent.economics);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setName("11111");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Content();
		entity.setUid(TestConst.uid2);
		entity.setName("广告");
		entity.setData("广告广告");
		entity.setSummary("广告简介");
		entity.setContent("我是一条广告");
		entity.setType(EContent.economics);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("name", "广告位");
		map.put("summary", "简介简介");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Content();
		entity.setUid(TestConst.uid2);
		entity.setName("广告");
		entity.setData("广告广告");
		entity.setSummary("广告简介");
		entity.setContent("我是一条广告");
		entity.setType(EContent.economics);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
}
