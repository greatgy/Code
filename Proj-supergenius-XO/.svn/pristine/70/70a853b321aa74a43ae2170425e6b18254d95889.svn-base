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

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 标签测试类
 * 
 * @author loupengyu
 * @date 2017年12月29日 16:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class LabelDaoTest {

	@Autowired
	private LabelDao dao;

	private Label entity;
	
	@Before
	public void before() {
		entity = new Label();
		entity.setUid(TestConst.uid);
		entity.setAdminuid(TestConst.uid1);
		entity.setContent("文章");
		entity.setRefuid(TestConst.uid2);
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
		entity = new Label();
		entity.setUid(TestConst.uid2);
		entity.setAdminuid(TestConst.uid2);
		entity.setContent("文章的测试内容");
		entity.setCount(121);
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
		entity = new Label();
		entity.setUid(TestConst.uid2);
		entity.setAdminuid(TestConst.uid1);
		entity.setContent("文章的测试内容");
		entity.setStatus(EStatus.enable);
		entity.setCount(121);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		System.out.println(uid);
		assertNotNull(dao.get(uid));
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
