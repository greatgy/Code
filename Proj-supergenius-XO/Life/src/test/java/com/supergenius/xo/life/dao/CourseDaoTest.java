package com.supergenius.xo.life.dao;

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

import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 内容测试类
 * 
 * @Author:JiaShitao
 * @Date:2018年5月7日下午7:07:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CourseDaoTest {
	@Autowired
	private CourseDao dao;
	private Course entity;
	
	@Before
	public void before() {
		entity = new Course();
		entity.setSid(2);
		entity.setUid(TestConst.uid);
		entity.setName(TestConst.name);
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setGrade(EGrade.eight);
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setData("data");
		entity.setPress("人教版");
		entity.setAdminuid(TestConst.uid1);
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
		entity = new Course();
		entity.setSid(3);
		entity.setUid("uid00000000000000000000000000001");
		entity.setName(TestConst.name);
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setGrade(EGrade.eight);
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setData("data");
		entity.setPress("人教版");
		entity.setAdminuid(TestConst.uid1);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setPress("sujiaoban巴拉");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
}