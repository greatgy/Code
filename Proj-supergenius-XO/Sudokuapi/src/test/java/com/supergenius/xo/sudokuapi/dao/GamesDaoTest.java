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
import com.supergenius.xo.sudokuapi.entity.Games;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 用户数据单元测试
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class GamesDaoTest {

	@Autowired
	GamesDao gamesDao;

	Games entity = null;

	@Before
	public void before() {
		entity = new Games();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setCreator(TestConst.uid2);
		entity.setStatus(EGameStatus.enable.name());
		entity.setUpdatetime(new Date());
		boolean result = gamesDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = gamesDao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	@Test
	public void testGet() {
		assertNotNull(gamesDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.creator, TestConst.uid2);
		assertNotNull(gamesDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.creator, TestConst.uid2);
		assertEquals(1, gamesDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.creator, TestConst.uid2);
		List<Games> list = gamesDao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = gamesDao.get(TestConst.uid);
		entity.setCapacity(5);
		assertTrue(gamesDao.update(entity));
		Games games = gamesDao.get(TestConst.uid);
		assertEquals(5, games.getCapacity());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.creator, TestConst.uid2);
		map.put(BaseMapperDict.uid, entity.getUid());
		gamesDao.updateFields(map);
		assertEquals(TestConst.uid2, gamesDao.get(TestConst.uid).getCreator());
	}

}