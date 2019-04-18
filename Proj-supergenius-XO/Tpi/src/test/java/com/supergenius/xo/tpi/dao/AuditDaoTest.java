package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
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
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Audit;

/**
 * 测试类
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AuditDaoTest {

	@Autowired
	AuditDao dao;

	Audit entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Audit();
		entity.setUid(TestConst.uid);
		entity.setAdminname(TestConst.name);
		entity.setAdminuid(TestConst.uid1);
		entity.setUpdatetime(DateTime.now());
		
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.adminuid, TestConst.uid1);
		List<Audit> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String adminname = entity.getAdminname();
		entity.setAdminname(TestConst.name1);
		assertTrue(dao.update(entity));
		Audit tempadAudit = dao.get(TestConst.uid);
		assertEquals(TestConst.name1, tempadAudit.getAdminname());
		entity.setAdminname(adminname);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String adminuid = entity.getAdminuid();
		map.put(BaseMapperDict.adminuid, TestConst.uid2);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		map.put(BaseMapperDict.adminuid, adminuid);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String adminuid = "adminuid";
		for (int i = 0; i < size; i++) {
			Audit entity = new Audit();
			entity.setAdminuid(adminuid);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.adminuid, adminuid);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
}
