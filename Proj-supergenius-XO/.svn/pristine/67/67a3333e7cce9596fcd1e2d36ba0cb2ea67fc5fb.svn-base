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
import com.supergenius.xo.startup.dao.RulerDao;
import com.supergenius.xo.startup.entity.Ruler;
import com.supergenius.xo.startup.enums.ERuler;

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
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Ruler();
		entity.setUid(TestConst.uid1);
		entity.setName("不适合创业");
		entity.setMinscore(0);
		entity.setMaxscore(256);
		entity.setRejectmincount(2);
		entity.setRejectmaxcount(4);
		entity.setType(ERuler.entrepreneurshiptest);
		entity.setContent("不适合创业");
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
		entity = new Ruler();
		entity.setUid(TestConst.uid1);
		entity.setName("不适合创业");
		entity.setMinscore(0);
		entity.setMaxscore(256);
		entity.setRejectmincount(2);
		entity.setRejectmaxcount(4);
		entity.setType(ERuler.entrepreneurshiptest);
		entity.setContent("不适合创业");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setMinscore(21);
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Ruler();
		entity.setUid(TestConst.uid1);
		entity.setName("不适合创业");
		entity.setMinscore(0);
		entity.setMaxscore(256);
		entity.setRejectmincount(2);
		entity.setRejectmaxcount(4);
		entity.setType(ERuler.entrepreneurshiptest);
		entity.setContent("不适合创业");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("type", "21");
		map.put("rejectcount", "3");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Ruler();
		entity.setUid(TestConst.uid1);
		entity.setName("不适合创业");
		entity.setMinscore(0);
		entity.setMaxscore(256);
		entity.setType(ERuler.entrepreneurshiptest);
		entity.setContent("不适合创业");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

}
