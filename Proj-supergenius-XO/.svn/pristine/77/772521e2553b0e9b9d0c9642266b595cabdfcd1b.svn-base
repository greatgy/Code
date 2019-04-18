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

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.startup.entity.Catalogue;

/**
 * 目录模块测试类
 * @author 许志翔
 * @date 2017年8月9日15:03:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CatalogueDaoTest {
	

	@Autowired
	private CatalogueDao dao;

	private Catalogue entity;
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(0);
		assertNotNull(entity);
		assertEquals(0, entity.getCid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", 1);
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
		map.put("cid", 1);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Catalogue();
		entity.setCid(200);
		entity.setPcid(0);		
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
		entity.setPcid(0);		
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

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Catalogue();
		entity.setCid(200);
		entity.setPcid(0);		
		entity.setName("创业花絮");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(200));
		dao.delete(200);
		assertNull(dao.get(200));
	}
}
