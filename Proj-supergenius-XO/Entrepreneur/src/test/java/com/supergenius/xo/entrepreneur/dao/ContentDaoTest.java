package com.supergenius.xo.entrepreneur.dao;

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
import com.supergenius.xo.entrepreneur.entity.Content;
import com.supergenius.xo.entrepreneur.enums.EContent;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 内容测试类
 * 
 * @author tf
 * @date 2018年7月5日
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
		entity.setName(TestConst.name);
		entity.setType(EContent.ad);
		entity.setData("data");
		entity.setSummary("summary");
		entity.setOriginurl("originurl");
		entity.setAdminuid(TestConst.uid1);
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
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setContent("李四李四李四李四李四文章的测试内容");
		assertTrue(dao.update(entity));
	}
	
}
