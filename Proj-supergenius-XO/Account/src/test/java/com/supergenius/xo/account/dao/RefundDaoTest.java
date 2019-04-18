package com.supergenius.xo.account.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 退款单元测试
 * 
 * @author YangGuang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RefundDaoTest {

	@Autowired
	RefundDao dao;

	@Before
	public void before() {
		Refund refund = new Refund();
		refund.setUid(TestConst.uid);
		refund.setUseremail("jasonhung_shao@163.com");
		refund.setUseruid(TestConst.uid1);
		refund.setUsername(TestConst.name);
		refund.setSite(ESite.supergenius);
		refund.setAccountuid(TestConst.uid1);
		refund.setMoney(0.1);
		refund.setRefundmoney(0.2);
		refund.setBank(EBank.abchina);
		refund.setState(ERefundState.init);
		refund.setDesc("desc");
		assertTrue(dao.insert(refund));
	}

	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
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
		map.put(MapperDict.useruid, TestConst.uid1);
		List<Refund> list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testUpdate() {
		Refund refund = dao.get(TestConst.uid);
		refund.setState(ERefundState.agree);
		dao.update(refund);
		assertEquals(ERefundState.agree, dao.get(TestConst.uid).getState());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.state, ERefundState.success);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(ERefundState.success, dao.get(TestConst.uid).getState());
	}

}
