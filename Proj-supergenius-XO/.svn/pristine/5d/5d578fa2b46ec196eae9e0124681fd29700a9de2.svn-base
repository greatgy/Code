package com.supergenius.xo.sudokuapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.entity.Users;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 用户数据单元测试
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UsersDaoTest {

	@Autowired
	UsersDao usersDao;

	Users entity = null;

	@Before
	public void before() {
		entity = new Users();
		entity.setUid(TestConst.uid);
		entity.setAccount(TestConst._123456);
		entity.setName(TestConst.name);
		entity.setPassword(TestConst.pwd_123456);
		entity.setEmail("test@test.com");
		entity.setCreate_ip("TestCreate_IP");
		entity.setLogintime(new Date());
		entity.setLogin_ip("TestLogin_IP");
		entity.setIcon(TestConst.username);
		entity.setGrade(null);
		entity.setPoints(20000);
		entity.setRounds(100);
		entity.setWintimes(40);
		entity.setMoney(1000);
		entity.setCreatetime(new Date());
		entity.setStatus(EGameStatus.enable);
		entity.setUpdatetime(new Date());
		boolean result = usersDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = usersDao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(usersDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		assertNotNull(usersDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		assertNotNull(usersDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		List<Users> list = usersDao.getList(map);
		assertNotNull(list.size());
	}

	@Test
	public void testUpdate() {
		entity = usersDao.get(TestConst.uid);
		entity.setName(TestConst.name1);
		assertTrue(usersDao.update(entity));
		Users users = usersDao.get(TestConst.uid);
		assertEquals(TestConst.name1, users.getName());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.name, TestConst.name1);
		map.put(BaseMapperDict.uid, entity.getUid());
		usersDao.updateFields(map);
		assertEquals(TestConst.name1, usersDao.get(TestConst.uid).getName());
	}

	@Test
	public void testDeleteByMap() {
		String name = "nametest";
		for (int i = 0; i < 3; i++) {
			Users users = new Users();
			users.setName(name);
			usersDao.insert(users);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.name, name);
		assertEquals(3, usersDao.getList(map).size());
		usersDao.deleteByMap(map);
		assertTrue(usersDao.getList(map).size() == 0);
	}

}
