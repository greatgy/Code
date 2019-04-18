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
import com.supergenius.xo.gupage.entity.Content;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 内容测试类
 * 
 * @author loupengyu
 * @date 2018年1月10日12:14:09
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
		entity.setAdminuid(TestConst.uid1);
		entity.setCreatetime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setContent("sdfasd");
		entity.setData("sdfaf");
		entity.setOid(TestConst.oid);
		entity.setName("wanglian");
		entity.setOriginurl("a/c/v");
		entity.setSummary("zhaiyao");
		entity.setType(1);
		entity.setUpdatetime(new DateTime());
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
		entity = new Content();
		entity.setUid(TestConst.uid2);
		entity.setAdminuid(TestConst.uid1);
		entity.setCreatetime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setType(1);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
