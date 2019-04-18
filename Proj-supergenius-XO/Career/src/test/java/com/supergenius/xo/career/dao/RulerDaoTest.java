package com.supergenius.xo.career.dao;

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
import com.supergenius.xo.career.entity.Ruler;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 规则测试类
 * @author ChenQi
 * @date 2017年6月20日15:10:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RulerDaoTest {

	@Autowired
	private RulerDao dao;

	private Ruler entity;
	
	@Before
	public void before() {
		entity = new Ruler();
		entity.setUid(TestConst.uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setName("在保证精神状态良好的情况下，你每天需要多少睡眠时间");
		entity.setArt(0.25F);
		entity.setCid(1);
		entity.setConvention(0.25F);
		entity.setEnterprise(0.25F);
		entity.setReality(0.25F);
		entity.setResearch(0.25F);
		entity.setSociology(0.25F);
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
		entity.setName("适合创业");
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
		map.put("art", 0.32F);
		assertTrue(dao.updateFields(map));
	}
}
