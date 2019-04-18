package com.supergenius.xo.enterpriser.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.enterpriser.entity.Catalogue;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 模块测试类
 * @author loupengyu
 * @date 2016-10-24 下午4:56:51
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
	@Before
	public void before() {
		entity = new Catalogue();
		entity.setContent("ceshi");
		entity.setCid(0);
		entity.setName("测试");
		entity.setStatus(EStatus.enable);
		entity.setAdminuid(TestConst.uid);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}
	
	@After
	public void After(){
		entity = null;
		boolean result = dao.delete(0);
		assertTrue(result);
	}
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(0);
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
		map.put(BaseMapperDict.uid,TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity.setContent("测试1");
		entity.setName("name1");
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		assertTrue(dao.update(entity));
	}
}
