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
import com.supergenius.xo.sudokuapi.entity.Proptypes;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 游戏道具类型单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ProptypesDaoTest {

	@Autowired
	ProptypesDao proptypesDao;

	Proptypes entity = null;

	@Before
	public void before() {
		Map<String, Object> map = new HashMap<>();
		map.put("cn", TestConst.name);
		map.put("en", TestConst.title);
		entity = new Proptypes();
		entity.setUid(TestConst.uid);
		entity.setName(map);
		entity.setFunc(map);
		entity.setType(TestConst.optionsalways);
		entity.setPrice(10);
		entity.setCreatetime(new Date());
		entity.setStatus(EGameStatus.enable);
		entity.setUpdatetime(new Date());
		boolean result = proptypesDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = proptypesDao.delete(TestConst.uid);
		assertTrue(result);
	}

	@Test
	public void testGet() {
		assertNotNull(proptypesDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, TestConst.optionsalways);
		assertNotNull(proptypesDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, TestConst.optionsalways);
		assertEquals(1, proptypesDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, TestConst.optionsalways);
		List<Proptypes> list = proptypesDao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = proptypesDao.get(TestConst.uid);
		entity.setType(TestConst.optionsonce);
		assertTrue(proptypesDao.update(entity));
		Proptypes proptypes = proptypesDao.get(TestConst.uid);
		assertEquals(TestConst.optionsonce, proptypes.getType());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, TestConst.optionsonce);
		map.put(BaseMapperDict.uid, entity.getUid());
		proptypesDao.updateFields(map);
		assertEquals(TestConst.optionsonce, proptypesDao.get(TestConst.uid).getType());
	}

	@Test
	public void testDeleteByMap() {
		String prop = "propt";
		for (int i = 0; i < 3; i++) {
			Proptypes proptypes = new Proptypes();
			proptypes.setType(prop);
			proptypesDao.insert(proptypes);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, prop);
		assertEquals(3, proptypesDao.getList(map).size());
		proptypesDao.deleteByMap(map);
		assertTrue(proptypesDao.getList(map).size() == 0);
	}
}
