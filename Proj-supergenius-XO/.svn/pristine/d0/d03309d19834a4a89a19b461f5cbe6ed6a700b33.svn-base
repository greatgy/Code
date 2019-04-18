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
import com.supergenius.xo.manager.entity.AppStudent;
import com.supergenius.xo.manager.enums.EAppStudent;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.UserInfo;

/**
 * AppStudent测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AppStudentDaoTest {

	@Autowired
	private AppStudentDao dao;

	private AppStudent entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertNotNull(entity.getUsersn());
		assertNotNull(entity.getMoney());
		assertNotNull(entity.getSemester());
		assertNotNull(entity.getReason());
		assertNotNull(entity.getType());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, "uid22222222222222222222222222210");
		entity = dao.getOne(map);
		assertNotNull(entity);
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetUserCountByStudent()
	 */
	@Test
	public void testGetUserCountByStudent() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.userid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test GetUserListByStudent()
	 */
	@Test
	public void testGetUserListByStudent() {
		Map<String, Object> map = new HashMap<String, Object>();
		AppStudent appStudent = new AppStudent();
		appStudent.setUid(TestConst.uid1);
		appStudent.setUseruid(TestConst.uid);
		UserInfo userInfo = new UserInfo();
		userInfo.setUid(TestConst.uid);
		map.put(BaseMapperDict.userid, TestConst.uid);
		map.put("userinfo", userInfo);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new AppStudent();
		entity.setUid(TestConst.uid1);
		entity.setUsersn("testusersn1111");
		entity.setMajor(EMajor.marketManage);
		entity.setSemester("2");
		entity.setType(EAppStudent.entrepreneur);
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
		entity = new AppStudent();
		entity.setUid(TestConst.uid1);
		entity.setUsersn("testusersn1111");
		entity.setMajor(EMajor.marketManage);
		entity.setSemester("2");
		entity.setType(EAppStudent.entrepreneur);
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setUsersn("testusersn222");
		entity.setMajor(EMajor.prodManage);
		entity.setSemester("2");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new AppStudent();
		entity.setUid(TestConst.uid1);
		entity.setUsersn("testusersn1111");
		entity.setMajor(EMajor.marketManage);
		entity.setSemester("2");
		entity.setType(EAppStudent.entrepreneur);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.sn, "studentsn222222");
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
		entity = new AppStudent();
		entity.setUid(TestConst.uid1);
		entity.setUsersn("testusersn1111");
		entity.setMajor(EMajor.marketManage);
		entity.setSemester("2");
		entity.setType(EAppStudent.entrepreneur);
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

}