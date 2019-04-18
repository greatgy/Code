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
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moralnews.entity.Catalogue;

/**
 * 目录模块测试类
 * 
 * @author JIaShitao
 * @date 2018年9月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CatalogueDaoTest {

	@Autowired
	private CatalogueDao dao;

	private Catalogue entity;

	@Before
	public void before() {
		entity = new Catalogue();
		entity.setCid(TestConst.oid);
		entity.setCreatetime(new DateTime());
		entity.setName("花絮");
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.oid);
		assertTrue(result);
	}

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.oid);
		assertNotNull(entity);
		assertEquals(TestConst.oid, entity.getCid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", TestConst.oid);
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
		map.put("cid", TestConst.oid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Catalogue();
		entity.setCid(200);
		entity.setName("创业花絮");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		int cid = entity.getCid();
		assertNotNull(dao.get(cid));
		dao.delete(cid);
		assertNull(dao.get(cid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Catalogue();
		entity.setCid(200);
		entity.setName("创业花絮");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		int cid = entity.getCid();
		assertNotNull(dao.get(cid));
		entity.setName("创业轶事");
		assertTrue(dao.update(entity));
		dao.delete(cid);
		assertNull(dao.get(cid));
	}

}
