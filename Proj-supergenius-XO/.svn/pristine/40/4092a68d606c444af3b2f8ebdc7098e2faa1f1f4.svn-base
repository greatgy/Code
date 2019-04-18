package com.supergenius.xo.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.enums.EGoods;

/**
 * 订单商品测试类
 * @author diaobisong
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class OrderGoodsDaoTest {
	
	@Autowired
	private OrderGoodsDao dao;

	private OrderGoods entity;

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new OrderGoods();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		String uid = TestConst.uid1;
		entity = new OrderGoods();
		entity.setUid(uid);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = new OrderGoods();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		entity = new OrderGoods();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		entity = new OrderGoods();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		map.put(BaseMapperDict.type, EGoods.video.getValue());
		map.put(BaseMapperDict.fromuid, TestConst.uid1);
		assertTrue(dao.getCount(map) >= 1);
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		entity = new OrderGoods();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid1);
		assertTrue(dao.getList(map).size() > 0);
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		String uid = TestConst.uid1;
		entity = new OrderGoods();
		entity.setUid(uid);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		assertEquals(TestConst.uid1, dao.get(uid).getUseruid());
		entity = new OrderGoods();
		entity.setUid(uid);
		entity.setUseruid(TestConst.uid2);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.update(entity);
		assertNotNull(dao.get(uid));
		assertEquals(TestConst.uid2, dao.get(uid).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		String uid = TestConst.uid1;
		entity = new OrderGoods();
		entity.setUid(uid);
		entity.setUseruid(TestConst.uid1);
		entity.setName("商品名称");
		entity.setOrderuid("订单唯一id11111111");
		entity.setSn("订单流水号11111111");
		entity.setType(EGoods.video);
		entity.setUnitprice(1);
		entity.setCount(100);
		entity.setTotalprice(100);
		entity.setRefuid(TestConst.uid1);
		dao.insert(entity);
		assertNotNull(dao.get(uid));
		assertEquals(TestConst.uid1, dao.get(uid).getUseruid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, uid);
		map.put(MapperDict.useruid, TestConst.uid2);
		dao.updateFields(map);
		assertNotNull(dao.get(uid));
		assertEquals(TestConst.uid2, dao.get(uid).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

}
