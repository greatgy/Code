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

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.startup.dao.StatisticsDao;
import com.supergenius.xo.startup.entity.Statistics;

/**
 * 规则测试类
 * @author ChenQi
 * @date 2017年6月20日15:32:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class StatisticsDaoTest {

	@Autowired
	private StatisticsDao dao;

	private Statistics entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertEquals(TestConst.uid2, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid2);
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
		map.put(BaseMapperDict.uid, TestConst.uid2);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Statistics();
		entity.setUid(TestConst.uid3);
		entity.setScore(280);
		entity.setRuleruid(TestConst.uid);
		entity.setData("{'uid11111111111111111111111111111':'C'}");
		entity.setLoginip("192.168.1.1");
		entity.setAdvice("这是一条测试的意见反馈");
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
		entity = new Statistics();
		entity.setUid(TestConst.uid3);
		entity.setScore(280);
		entity.setRuleruid(TestConst.uid);
		entity.setData("{'uid11111111111111111111111111111':'C'}");
		entity.setLoginip("192.168.1.1");
		entity.setAdvice("这是一条测试的意见反馈");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setScore(290);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Statistics();
		entity.setUid(TestConst.uid3);
		entity.setScore(280);
		entity.setRuleruid(TestConst.uid);
		entity.setData("{'uid11111111111111111111111111111':'C'}");
		entity.setLoginip("192.168.1.1");
		entity.setAdvice("这是一条测试的意见反馈");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("score", "290");
		map.put("loginip", "192.168.1.2");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Statistics();
		entity.setUid(TestConst.uid3);
		entity.setScore(280);
		entity.setRuleruid(TestConst.uid);
		entity.setData("{'uid11111111111111111111111111111':'C'}");
		entity.setLoginip("192.168.1.1");
		entity.setAdvice("这是一条测试的意见反馈");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid3));
		dao.delete(TestConst.uid3);
		assertNull(dao.get(TestConst.uid3));
	}

}
