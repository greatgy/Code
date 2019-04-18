package com.supergenius.xo.life.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.life.entity.News;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 消息测试类
 * 
 * @author YangGuang
 * @date 2018年5月9日17:02:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class NewsDaoTest {
	
	@Autowired
	NewsDao dao;
	
	News entity;
	
	/**
	 * Test Get()
	 */
	@Before
	public void before() {
		entity = new News();
		entity.setUid(TestConst.uid1);
		entity.setFromuid(TestConst.uid2);
		entity.setTouid(TestConst.uid);
		entity.setType(ELifeMsg.commentsarticle);
		entity.setTitle("111");
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid1);
		assertTrue(result);
	}
	
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}
	
	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 0);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid1);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid1).getStatus());
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.status, EStatus.enable);
		dao.updateFields(map);
		assertEquals(EStatus.enable, dao.get(TestConst.uid1).getStatus());
	}

}
