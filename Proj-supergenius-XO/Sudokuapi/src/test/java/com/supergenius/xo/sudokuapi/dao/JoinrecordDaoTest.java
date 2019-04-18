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

import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.sudokuapi.entity.Joinrecords;

/**
 * 登录历史单元测试
 * @author YuYingJie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class JoinrecordDaoTest {

	@Autowired
	JoinrecordsDao dao;
	
	Joinrecords entity = null;

	
	/**
	 * 在执行所有单元测试方法之前插入
	 */
	@Before
	public void before() {
		entity = new Joinrecords();
		entity.setUid(TestConst.uid3);
		entity.setAccount("lili");
		entity.setGameUid(TestConst.uid1);
		entity.setUpdatetime(new Date());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid3);
		assertTrue(result);
	}
	
	@Test
	public void testGetOne() {
		Map<String,Object> map = new HashMap<>();
		map.put(MapperDict.account, "lili");
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, TestConst.uid3);
		assertNotNull(dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, TestConst.uid3);
		List<Joinrecords> list = dao.getList(map);
		assertNotNull(list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid3);
		entity.setAccount("wangli");
		assertTrue(dao.update(entity));
		Joinrecords loginhistories = dao.get(TestConst.uid3);
		assertEquals("wangli", loginhistories.getAccount());
	}

}
