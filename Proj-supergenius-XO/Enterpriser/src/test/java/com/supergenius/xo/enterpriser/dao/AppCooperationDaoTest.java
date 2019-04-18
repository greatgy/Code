package com.supergenius.xo.enterpriser.dao;

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

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.AppCooperation;
import com.supergenius.xo.mock.testconstants.TestConst;

/** 
* 互助合作dao测试
* @author chenminchang
* @date 2016年12月5日 下午12:41:40 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AppCooperationDaoTest {
	
	@Autowired
	private AppCooperationDao dao;

	private AppCooperation entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid,entity.getUid());
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
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new AppCooperation();
		entity.setUid(TestConst.uid1);
		entity.setName("张三");
		entity.setSemester(1);;
		entity.setMobile(TestConst.mobile);;
		entity.setCompany("supergenius");;
		entity.setJob("java");;
		entity.setEmail(TestConst.email);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
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
		entity = new AppCooperation();
		entity.setUid(TestConst.uid1);
		entity.setName("张三");
		entity.setSemester(1);;
		entity.setMobile(TestConst.mobile);;
		entity.setCompany("supergenius");;
		entity.setJob("java");;
		entity.setEmail(TestConst.email);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity = dao.get(uid);
		entity.setName("张三85");
		entity.setSemester(2);;
		entity.setMobile(TestConst.mobile);;
		entity.setCompany("supergenius99");;
		entity.setJob("java99");;
		entity.setEmail(TestConst.email);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFieds() {
		entity = new AppCooperation();
		entity.setUid(TestConst.uid1);
		entity.setName("张三");
		entity.setSemester(1);;
		entity.setMobile(TestConst.mobile);;
		entity.setCompany("supergenius");;
		entity.setJob("java");;
		entity.setEmail(TestConst.email);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid,uid);
		map.put(MapperDict.name,"李四");
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new AppCooperation();
		entity.setUid(TestConst.uid1);
		entity.setName("张三");
		entity.setSemester(1);;
		entity.setMobile(TestConst.mobile);;
		entity.setCompany("supergenius");;
		entity.setJob("java");;
		entity.setEmail(TestConst.email);
		entity.setCreatetime(new DateTime());
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
}
