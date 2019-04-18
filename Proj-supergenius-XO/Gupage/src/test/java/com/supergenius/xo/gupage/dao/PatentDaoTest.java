package com.supergenius.xo.gupage.dao;

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

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.entity.Patent;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 专利测试类
 * 
 * @author loupengyu
 * @date 2018年1月10日12:13:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class PatentDaoTest {

	@Autowired
	private PatentDao dao;

	private Patent entity;
	
	@Before
	public void before() {
		entity = new Patent();
		entity.setUid(TestConst.uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setFilepath("a/b/c");
		entity.setImgbig("a/b/c");
		entity.setCreatetime(new DateTime());
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
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
		map.put(MapperDict.uid, TestConst.uid);
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
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Patent();
		entity.setUid(TestConst.uid2);
		entity.setAdminuid(TestConst.uid1);
		entity.setFilepath("a/b/c");
		entity.setImgbig("a/b/c");
		entity.setCreatetime(new DateTime());
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
