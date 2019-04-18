package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.EType;

/**
 * type单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class TypeDaoTest {

	@Autowired
	TypeDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Type type = new Type();
		type.setUid(TestConst.uid);
		type.setName(TestConst.name);
		type.setSummary("hello world");
		type.setTypecode("CODE");
		type.setType(EType.project);
		assertTrue(dao.insert(type));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		List<Type> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Type entity = dao.get(TestConst.uid);
		entity.setName(TestConst.name1);
		dao.update(entity);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getName());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, TestConst.name);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.name, dao.get(TestConst.uid).getName());
	}
	
	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Type entity = new Type();
			entity.setName(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
