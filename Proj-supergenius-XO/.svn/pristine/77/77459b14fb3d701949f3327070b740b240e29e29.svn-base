package com.supergenius.xo.enterpriser.dao;

import static org.junit.Assert.assertEquals;
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

import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Video;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 视频测试类
 * 
 * @author 杨光
 * @date 2018年1月10日12:26:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class VideoDaoTest {

	@Autowired
	private VideoDao dao;

	private Video entity;
	
	@Before
	public void before() {
		entity = new Video();
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
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity.setContent("李四123456");
		entity.setIstop(1);
		assertTrue(dao.update(entity));
		assertEquals(1, dao.get(TestConst.uid).getIstop());
	}

}
