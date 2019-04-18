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

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 文章测试类
 * 
 * @author ChenQi
 * @date 2018年5月9日15:49:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class TopicDaoTest {

	@Autowired
	private TopicDao dao;

	private Topic entity;
	
	@Before
	public void before() {
		entity = new Topic();
		entity.setCid(TestConst.oid);
		entity.setUid(TestConst.uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setContent("文章");
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
		entity = new Topic();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
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
		entity = new Topic();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setAuthor("李四");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
