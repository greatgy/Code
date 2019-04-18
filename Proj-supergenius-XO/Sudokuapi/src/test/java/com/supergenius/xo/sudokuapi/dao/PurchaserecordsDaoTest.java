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
import com.supergenius.xo.sudokuapi.entity.Purchaserecords;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 购买记录单元测试
 * 
 * @author YangGuang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class PurchaserecordsDaoTest {

	@Autowired
	PurchaserecordsDao purchaserecordsDao;

	Purchaserecords entity = null;

	@Before
	public void before() {
		entity = new Purchaserecords();
		entity.setUid(TestConst.uid);
		entity.setCreatetime(new Date());
		entity.setStatus(EGameStatus.enable);
		entity.setAccount(TestConst.uid2);
		entity.setProp_type(TestConst.title);
		entity.setUpdatetime(new Date());
		boolean result = purchaserecordsDao.insert(entity);
		assertTrue(result);
	}

	@After
	public void after() {
		entity = null;
		boolean result = purchaserecordsDao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	@Test
	public void testGet() {
		assertNotNull(purchaserecordsDao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.account, TestConst.uid2);
		assertNotNull(purchaserecordsDao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.account, TestConst.uid2);
		assertEquals(1, purchaserecordsDao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.account, TestConst.uid2);
		List<Purchaserecords> list = purchaserecordsDao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		entity = purchaserecordsDao.get(TestConst.uid);
		entity.setCost(5);
		assertTrue(purchaserecordsDao.update(entity));
		Purchaserecords purchaserecords = purchaserecordsDao.get(TestConst.uid);
		assertEquals(5, purchaserecords.getCost());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.account, TestConst.uid3);
		map.put(BaseMapperDict.uid, entity.getUid());
		purchaserecordsDao.updateFields(map);
		assertEquals(TestConst.uid3, purchaserecordsDao.get(TestConst.uid).getAccount());
	}

	@Test
	public void testDeleteByMap() {
		for (int i = 0; i < 3; i++) {
			Purchaserecords purchaserecords = new Purchaserecords();
			purchaserecords.setAccount(TestConst.name);
			purchaserecordsDao.insert(purchaserecords);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.account, TestConst.name);
		assertEquals(3, purchaserecordsDao.getList(map).size());
		purchaserecordsDao.deleteByMap(map);
		assertTrue(purchaserecordsDao.getList(map).size() == 0);
	}

}
