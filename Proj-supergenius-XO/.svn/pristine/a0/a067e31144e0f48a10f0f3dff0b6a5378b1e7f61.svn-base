package com.supergenius.xo.sudokuapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
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
import com.supergenius.xo.sudokuapi.entity.Loginhistories;

/**
 * 登录历史单元测试
 * @author YuYingJie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class LoginhistoriesDaoTest {

	@Autowired
	LoginhistoriesDao dao;
	
	Loginhistories entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Loginhistories();
		entity.setUid(TestConst.uid);
		entity.setUserid(TestConst.uid1);
		entity.setIp("-192.169.0.1");
		entity.setUpdatetime(new Date());
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
		map.put(MapperDict.ip, "-192.169.0.1");
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.ip, "-192.169.0.1");
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.ip, "-192.169.0.1");
		List<Loginhistories> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setIp("127.0.0.1");
		assertTrue(dao.update(entity));
		Loginhistories loginhistories = dao.get(TestConst.uid);
		assertEquals("127.0.0.1", loginhistories.getIp());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.user, new ObjectId(TestConst.uid1));
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(TestConst.uid1, dao.get(TestConst.uid).getUserid());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Loginhistories entity = new Loginhistories();
			entity.setUserid(TestConst.uid2);
			entity.setIp("-000.000.000.000");
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.ip, "-000.000.000.000");
		assertEquals(3, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
