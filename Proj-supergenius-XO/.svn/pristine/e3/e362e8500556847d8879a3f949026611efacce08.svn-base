package com.supergenius.xo.account.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * 充值单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AccountDaoTest {
	
	@Autowired
	AccountDao dao;
	
	@Before
	public void before() {
		Account account = new Account();
		account.setUid(TestConst.uid);
		account.setAccountsn("accountsn");
		account.setUseremail("jasonhung_shao@163.com");
		account.setPayuid(TestConst.uid2);
		account.setUseruid(TestConst.uid1);
		account.setUsername(TestConst.name);
		account.setUserip("userip");
		account.setResulturl("resulturl");
		account.setNotifyurl("notifyurl");
		account.setSite(ESite.supergenius);
		account.setMoney(0.1);
		account.setBank(EBank.abchina);
		account.setState(EState.init);
		account.setSuccesstime(DateTime.now());
		account.setFailedtime(DateTime.now());
		account.setDesc("desc");
		assertTrue(dao.insert(account));
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
		map.put(MapperDict.payuid, TestConst.uid2);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.payuid, TestConst.uid2);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, TestConst.uid1);
		List<Account> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Account account = dao.get(TestConst.uid);
		account.setState(EState.pay);
		dao.update(account);
		assertEquals(EState.pay, dao.get(TestConst.uid).getState());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.state, EState.success);
		map.put(MapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(EState.success, dao.get(TestConst.uid).getState());
	}
	
	@Test
	public void testGetSumMoney(){
		Map<String, Object> map = new HashMap<>();
		assertTrue(dao.getSumMoney(map) >= 0.1);
	}
	
	@Test
	public void testGetList2(){
		Map<String, Object> map = new HashMap<>();
		List<String> list1 = new ArrayList<>();
		list1.add("accountsn");
		map.put("sns", list1);
		List<Account> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	@Ignore
	public void testESite() {
		System.out.println(ESite.get(0)); 
		System.out.println(ESite.get("0")); 
		for (int i = 0; i < 30; i++) {
			System.out.println(GlobalUtil.getUUID());
		}
	}
	
}
