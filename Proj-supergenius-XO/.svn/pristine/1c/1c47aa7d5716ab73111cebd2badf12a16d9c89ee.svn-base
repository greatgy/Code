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
import com.supergenius.xo.sudokuapi.entity.Rules;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 游戏规则单元测试
 * 
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RulesDaoTest {

	@Autowired
	RulesDao rulesDao;

	Rules entity = null;

	@Before
	public void before() {
		entity = new Rules();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setStatus(EGameStatus.init);
		entity.setUpdatetime(new Date());
		boolean result = rulesDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = rulesDao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(rulesDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EGameStatus.init);
		assertNotNull(rulesDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EGameStatus.init);
		assertEquals(1, rulesDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EGameStatus.init);
		List<Rules> list = rulesDao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = rulesDao.get(TestConst.uid);
		entity.setStatus(EGameStatus.init);
		assertTrue(rulesDao.update(entity));
		Rules rules = rulesDao.get(TestConst.uid);
		assertEquals(EGameStatus.init, rules.getStatus());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.init);
		map.put(BaseMapperDict.uid, entity.getUid());
		rulesDao.updateFields(map);
		assertEquals(EGameStatus.init, rulesDao.get(TestConst.uid).getStatus());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Rules rules = new Rules();
			rules.setStatus(EGameStatus.start);
			rulesDao.insert(rules);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, EGameStatus.start);
		assertEquals(3, rulesDao.getList(map).size());
		rulesDao.deleteByMap(map);
		assertTrue(rulesDao.getList(map).size() == 0);
	}

}
