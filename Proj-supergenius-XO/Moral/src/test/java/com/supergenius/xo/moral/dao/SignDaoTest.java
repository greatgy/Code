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

import com.supergenius.moral.moral.dao.SignDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Sign;

/**
 *  签到单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SignDaoTest {

	
	@Autowired
	SignDao dao;

	Sign entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Sign();
		entity.setUid(TestConst.uid);
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
		map.put(MapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, TestConst.uid);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, TestConst.uid);
		List<Sign> list = dao.getList(map);
		assertEquals(1, list.size());
	}
}
