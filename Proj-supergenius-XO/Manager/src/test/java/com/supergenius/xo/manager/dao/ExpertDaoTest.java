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
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.mock.testconstants.TestConst;

/** 
 * 专家dao测试
 * @author chenminchang
 * @date 2016-7-18 下午2:43:43 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/application**.xml" })
public class ExpertDaoTest {
	
	@Autowired
	ExpertDao dao;
	
	Expert entity;
	
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
	 * Test Insdert()
	 */
	@Test
	public void testInsert() {
		entity = new Expert();
		entity.setUseruid(GlobalUtil.getUUID());
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setLevel(EExpertLevel.expert);
		entity.setType(EExpert.expert);
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
		entity = new Expert();
		entity.setUseruid(GlobalUtil.getUUID());
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setLevel(EExpertLevel.expert);
		entity.setType(EExpert.expert);
		dao.insert(entity);
		String uid = entity.getUid();
		entity = dao.get(uid);
		entity.setUseruid(GlobalUtil.getUUID());
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setLevel(EExpertLevel.expert);
		entity.setType(EExpert.expert);
		entity.setStatus(EStatus.disable);
		dao.update(entity);
		assertEquals(EStatus.disable, dao.get(uid).getStatus());
		dao.delete(uid);
		assertNull(dao.get(uid));
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
	
	/**
	 * Test getListByStatus()
	 * @author liubin
	 * @createtime 2016-8-28下午4:47:28
	 * @return void
	 */
	@Test
	public void testGetListByStatus() {
		EStatus [] status = {EStatus.enable, EStatus.end};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		map.put(MapperDict.useruid, TestConst.uid);
		map.put(MapperDict.major, EMajor.financeManage);
		entity = dao.getListByStatus(map).get(0);
		assertNotNull(entity);
	}
	
	/**
	 * Test getOneByStatus()
	 * @author liubin
	 * @createtime 2016-8-28下午6:12:42
	 * @return void
	 */
	@Test
	public void testGetOneByStatus() {
		EStatus [] status = {EStatus.enable, EStatus.end};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		map.put(MapperDict.useruid, TestConst.uid);
		map.put(MapperDict.major, EMajor.financeManage);
		entity = dao.getOneByStatus(map);
		assertNotNull(entity);
	}
}