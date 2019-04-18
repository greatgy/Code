package com.supergenius.xo.manager.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Major;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * Major测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MajorDaoTest {

	@Autowired
	private MajorDao dao;

	private Major entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertNotNull(entity.getSummary());
		assertNotNull(entity.getContent());
		assertNotNull(entity.getType());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getSummary());
		assertNotNull(entity.getContent());
		assertNotNull(entity.getType());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Major();
		entity.setUid(TestConst.uid3);
		entity.setSummary("i will help you !");
		entity.setContent("Thank you !");
		entity.setType(EMajor.hrManage);
		entity.setStatus(EStatus.disable);
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
		entity = new Major();
		entity.setUid(TestConst.uid3);
		entity.setSummary("i will help you !");
		entity.setType(EMajor.hrManage);
		entity.setContent("Thank you !");
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setSummary(null);
		entity.setContent("Thank you !");
		assertTrue(dao.update(entity));
		assertNull(entity.getSummary());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Major();
		entity.setUid(TestConst.uid3);
		entity.setSummary("i will help you !");
		entity.setContent("Thank you !");
		entity.setType(EMajor.hrManage);
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Major();
		entity.setUid(TestConst.uid3);
		entity.setType(EMajor.hrManage);
		entity.setSummary("i will help you !");
		entity.setContent("Thank you !");
		entity.setStatus(EStatus.disable);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid3));
		dao.delete(TestConst.uid3);
		assertNull(dao.get(TestConst.uid3));
	}
}