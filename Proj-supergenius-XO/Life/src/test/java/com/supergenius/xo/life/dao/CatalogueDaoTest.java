package com.supergenius.xo.life.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 目录模块测试类
 * @author ChenQi
 * @date 2018年5月9日18:39:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CatalogueDaoTest {
	

	@Autowired
	private CatalogueDao dao;

	private Catalogue entity;
	
	@Ignore
	public void before() {
		entity = new Catalogue();
		entity.setCid(0);
		entity.setName("测试模块");
		entity.setAdminuid(TestConst.uid1);
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@Ignore
	public void after() {
		entity = null;
		boolean result = dao.delete(0);
		assertTrue(result);
	}
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(1);
		assertNotNull(entity);
		assertEquals(1, entity.getCid());
	}
	@Test
	public void testGet1() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.pcid, 0);
		List<Catalogue> list = dao.getList(map);
		System.err.println(list.size());
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
	@Ignore
	public void testCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Ignore
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", 12138);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Ignore
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
		long cid = entity.getCid();
		assertNotNull(dao.get(cid));
		dao.delete(cid);
		assertNull(dao.get(cid));
	}

	/**
	 * Test Update()
	 */
	@Ignore
	public void testUpdate() {
		entity = new Catalogue();
		entity.setCid(3);
		entity.setPcid(0);		
		entity.setName("创业花絮");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		long cid = entity.getCid();
		assertNotNull(dao.get(cid));
		entity.setName("创业轶事");
		assertTrue(dao.update(entity));
		dao.delete(cid);
		assertNull(dao.get(cid));
	}

	/**
	 * Test Delete()
	 */
	@Ignore
	public void testDelete() {
		entity = new Catalogue();
		entity.setCid(3);
		entity.setPcid(0);		
		entity.setName("创业花絮");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(3));
		dao.delete(200);
		assertNull(dao.get(3));
	}
}
