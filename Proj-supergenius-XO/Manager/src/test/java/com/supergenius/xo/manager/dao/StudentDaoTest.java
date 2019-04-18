package com.supergenius.xo.manager.dao;

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
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * Student测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class StudentDaoTest {

	@Autowired
	private StudentDao dao;

	private Student entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertNotNull(entity.getMajors());
		assertNotNull(entity.getSn());
		assertNotNull(entity.getSaturdaytime());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.useruid, TestConst.uid);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getMajors());
		assertNotNull(entity.getSn());
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
		entity = new Student();
		entity.setUid(TestConst.uid1);
		entity.setSn("sn123456");
		entity.setMajors(Integer.valueOf(EMajor.marketManage.toString()));
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
		entity = new Student();
		entity.setUid(TestConst.uid1);
		entity.setSn("sn123456");
		entity.setMajors(Integer.valueOf(EMajor.marketManage.toString()));
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setSn("sn12345689");
		entity.setMajors(Integer.valueOf(EMajor.marketManage.toString()));
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Student();
		entity.setUid(TestConst.uid1);
		entity.setSn("sn123456");
		entity.setMajors(Integer.valueOf(EMajor.marketManage.toString()));
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.sn, "studentsn11111");
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
		entity = new Student();
		entity.setUid(TestConst.uid1);
		entity.setSn("sn123456");
		entity.setMajors(Integer.valueOf(EMajor.marketManage.toString()));
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

}