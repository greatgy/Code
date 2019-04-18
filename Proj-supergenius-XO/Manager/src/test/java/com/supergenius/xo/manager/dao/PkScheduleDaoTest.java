package com.supergenius.xo.manager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 挑战测试
 * @author chenminchang
 * @date 2016-7-18 下午2:44:40 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/application**.xml" })
public class PkScheduleDaoTest {

	@Autowired
	PkScheduleDao dao;
	
	PKSchedule entity;
	
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
		assertTrue(dao.getList(map).size() > 0);
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
		entity = new PKSchedule();
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setPkuseruid(GlobalUtil.getUUID());
		entity.setPkuseruid2(GlobalUtil.getUUID());
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
		entity = new PKSchedule();
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setPkuseruid(GlobalUtil.getUUID());
		entity.setPkuseruid2(GlobalUtil.getUUID());
		dao.insert(entity);
		String uid = entity.getUid();
		entity = dao.get(uid);
		entity.setStatus(EStatus.disable);
		entity.setSn(GlobalUtil.getUUID());
		entity.setMajor(EMajor.hrManage);
		entity.setPkuseruid(GlobalUtil.getUUID());
		entity.setPkuseruid2(GlobalUtil.getUUID());
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
	
	@Test
	@Ignore
	public void testGetCountByOrderGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid);
		map.put(MapperDict.major, EMajor.hrManage);
		assertTrue(dao.getCountByOrderGoods(map) > 0);
	}
	
	@Test
	@Ignore
	public void testGetListByOrderGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid);
		map.put(MapperDict.major, EMajor.hrManage);
		assertTrue(dao.getListByOrderGoods(map).size() > 0);
		System.out.println(dao.getListByOrderGoods(map).size());
	}
	
	@Autowired
	PkScheduleSO so;
	
	@Test
	public void testGetListBySearch() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.search, "张");
	    map.put(MapperDict.major, EMajor.hrManage);
		System.err.println(so.getListBySearch(map));
//		System.out.println(dao.getListBySearch(map).size());
	}
	
	@Test
	public void testGetCountBySearch() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.search, "00");
		System.out.println(dao.getCountBySearch(map));
	}
}