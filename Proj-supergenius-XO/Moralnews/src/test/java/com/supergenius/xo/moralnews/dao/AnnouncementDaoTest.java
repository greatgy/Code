package com.supergenius.xo.moralnews.dao;

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
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moralnews.entity.Announcement;

/**
 * 公告测试类
 * 
 * @author JiaShitao
 * @date 2018年9月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AnnouncementDaoTest {

	@Autowired
	private AnnouncementDao dao;

	private Announcement entity;
	
	@Before
	public void before() {
		entity = new Announcement();
		entity.setUid(TestConst.uid);
		entity.setContent("文章");
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
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Announcement();
		entity.setUid(TestConst.uid2);
		entity.setContent("公告的测试内容");
		entity.setTitle("公告的测试标题");
		entity.setIstop(1);
		entity.setStatus(EStatus.enable);
		entity.setEndtime(new DateTime());	
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
		entity = new Announcement();
		entity.setUid(TestConst.uid2);
		entity.setContent("公告的测试内容");
		entity.setTitle("公告的测试标题");
		entity.setIstop(1);
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setContent("测试修改内容");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
