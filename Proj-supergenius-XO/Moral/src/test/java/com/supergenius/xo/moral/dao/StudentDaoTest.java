package com.supergenius.xo.moral.dao;

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
import com.supergenius.moral.moral.dao.StudentDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Student;

/**
 * 学员单元测试 
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class StudentDaoTest {
	
	@Autowired
	StudentDao dao;

	Student entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Student();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid1);
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
		map.put(MapperDict.useruid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, TestConst.uid1);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, TestConst.uid1);
		List<Student> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setUseruid(TestConst.uid2);
		assertTrue(dao.update(entity));
		Student student = dao.get(TestConst.uid);
		assertEquals(TestConst.uid2, student.getUseruid());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.useruid, TestConst.uid1);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(TestConst.uid1, dao.get(TestConst.uid).getUseruid());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Student entity = new Student();
			entity.setUseruid(TestConst.uid2);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.useruid, TestConst.uid2);
		assertEquals(3, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}