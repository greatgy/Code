package com.supergenius.xo.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.enums.ETrade;

/** 
 * 交易明细测试
 * @author chenminchang
 * @date 2016-3-24 上午10:10:32 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class TradeDetailDaoTest {

	@Autowired
	private TradeDetailDao dao;

	private TradeDetail entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		assertEquals(TestConst.uid,entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test GetSumMoney()
	 */
	@Test
	public void testGetSumMoney() {
		assertTrue(dao.getSumMoney(null) > 0);
	}

	/**
	 * Test GetSumMoneyBySite
	 */
	@Test
	public void testGetSumMoneyBySite() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.site, ESite.user);
		assertTrue(dao.getSumMoney(map) > 0);
	}
	
	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new TradeDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid("testuseruid");
		entity.setTradeeventuid("tradeeventuid");
		entity.setMoney(999.59);
		entity.setAccountcurr(888.99);
		entity.setSn("liushuisn123");
		entity.setOrderuid("orderuid123");
		entity.setName("作为裁判奖励");
		entity.setSite(ESite.user);
		entity.setSuccesstime(new DateTime());
		entity.setFailedtime(new DateTime());
		entity.setMemo("交易正常");
		entity.setType(ETrade.adminCancelTicket);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new TradeDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid("testuseruid");
		entity.setTradeeventuid("tradeeventuid");
		entity.setMoney(999.59);
		entity.setAccountcurr(888.99);
		entity.setSn("liushuisn123");
		entity.setOrderuid("orderuid123");
		entity.setName("作为裁判奖励");
		entity.setSuccesstime(new DateTime());
		entity.setFailedtime(new DateTime());
		entity.setMemo("交易正常");
		entity.setType(ETrade.adminCancelTicket);
		entity.setSite(ESite.user);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setAccountcurr(111.89);
		entity.setCreatetime(new DateTime());
		entity.setMemo("测试说明");
		String useruid = GlobalUtil.getUUID();
		entity.setUseruid(useruid);
		assertTrue(dao.update(entity));
		assertEquals(useruid, dao.get(uid).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}
	
	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new TradeDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid("testuseruid");
		entity.setTradeeventuid("tradeeventuid");
		entity.setMoney(999.59);
		entity.setAccountcurr(888.99);
		entity.setSn("liushuisn123");
		entity.setOrderuid("orderuid123");
		entity.setName("作为裁判奖励");
		entity.setSuccesstime(new DateTime());
		entity.setFailedtime(new DateTime());
		entity.setMemo("交易正常");
		entity.setSite(ESite.user);
		entity.setType(ETrade.adminCancelTicket);
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		String useruid = GlobalUtil.getUUID();
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		assertEquals(useruid, dao.get(uid).getUseruid());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new TradeDetail();
		entity.setUid(TestConst.uid1);
		entity.setUseruid("testuseruid");
		entity.setTradeeventuid("tradeeventuid");
		entity.setMoney(999.59);
		entity.setAccountcurr(888.99);
		entity.setSn("liushuisn123");
		entity.setOrderuid("orderuid123");
		entity.setName("作为裁判奖励");
		entity.setSuccesstime(new DateTime());
		entity.setFailedtime(new DateTime());
		entity.setMemo("交易正常");
		entity.setSite(ESite.user);
		entity.setType(ETrade.adminCancelTicket);
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}
}
