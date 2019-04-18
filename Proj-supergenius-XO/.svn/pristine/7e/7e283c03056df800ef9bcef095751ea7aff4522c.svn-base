package com.supergenius.xo.manager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.mock.testconstants.TestConst;

/** 
 * 申请答辩专家测试
 * @author chenminchang
 * @date 2016-7-17 下午12:32:21 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AppReplyExpertDaoTest {

	@Autowired
	AppReplyExpertDao dao;
	
	AppReplyExpert entity;
	
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
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}
	
	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new AppReplyExpert();
		entity.setExpertuid(GlobalUtil.getUUID());
		entity.setAppreplyuid(GlobalUtil.getUUID());
		entity.setIsabsent(true);
		entity.setDesc("aaaa");
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
		entity = dao.get(TestConst.uid);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(TestConst.uid).getStatus());
	}
	
	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		map.put(BaseMapperDict.status, EStatus.enable);
		dao.updateFields(map);
		assertEquals(EStatus.enable, dao.get(TestConst.uid).getStatus());
	}
	
}