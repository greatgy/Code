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
import com.supergenius.xo.sudokuapi.entity.Recharges;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 充值记录单元测试
 * 
 * @author YangGuang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RechargesDaoTest {

	@Autowired
	RechargesDao rechargesDao;

	Recharges entity = null;

	@Before
	public void before() {
		entity = new Recharges();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setStatus(EGameStatus.enable);
		entity.setUpdatetime(new Date());
		entity.setCost(TestConst.oid);
		entity.setFrom(TestConst.name);
		entity.setTarger(TestConst.name1);
		entity.setPayuid(TestConst.uid1);
		boolean result = rechargesDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = rechargesDao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	@Test
	public void testGet() {
		assertNotNull(rechargesDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.payuid, TestConst.uid1);
		assertNotNull(rechargesDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.payuid, TestConst.uid1);
		assertEquals(1, rechargesDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.payuid, TestConst.uid1);
		List<Recharges> list = rechargesDao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = rechargesDao.get(TestConst.uid);
		entity.setFrom(TestConst.name1);
		assertTrue(rechargesDao.update(entity));
		Recharges recharges = rechargesDao.get(TestConst.uid);
		assertEquals(TestConst.name1, recharges.getFrom());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.payuid, TestConst.uid2);
		map.put(BaseMapperDict.uid, entity.getUid());
		rechargesDao.updateFields(map);
		assertEquals(TestConst.uid2, rechargesDao.get(TestConst.uid).getPayuid());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Recharges recharges = new Recharges();
			recharges.setPayuid(TestConst.uid3);
			rechargesDao.insert(recharges);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.payuid, TestConst.uid3);
		assertEquals(3, rechargesDao.getList(map).size());
		rechargesDao.deleteByMap(map);
		assertTrue(rechargesDao.getList(map).size() == 0);
	}

}
