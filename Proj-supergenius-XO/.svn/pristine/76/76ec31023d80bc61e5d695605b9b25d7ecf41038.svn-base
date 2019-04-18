package com.supergenius.xo.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * User测试
 * 
 * @author chenminchang
 * @date 2016-3-24 下午1:33:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UserDaoTest {

	@Autowired
	private UserDao dao;

	private User entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid);
		assertNotNull(entity);
		System.out.println(entity.getAccount());
		assertNotNull(entity.getUserInfo());
		assertNotNull(entity.getUsersn());
		assertNotNull(entity.getEmail());
		assertEquals(TestConst.uid, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getEmail());
		assertNotNull(entity.getUserInfo());
		assertNotNull(entity.getUsersn());
	}

	/**
	 * Test GetOneByUsersn()
	 */
	@Test
	public void testGetOneByUsersn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.usersn, TestConst.usersn);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getEmail());
		assertNotNull(entity.getUserInfo());
		assertNotNull(entity.getUsersn());
	}

	/**
	 * Test GetOneByEmail()
	 */
	@Test
	public void testGetOneByEmail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.email, TestConst.email);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getEmail());
		assertNotNull(entity.getUserInfo());
		assertNotNull(entity.getUsersn());
	}

	/**
	 * Test GetOneByMobile()
	 */
	@Test
	public void testGetOneByMobile() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.mobile, TestConst.mobile);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getEmail());
		assertNotNull(entity.getUserInfo());
		assertNotNull(entity.getUsersn());
	}

	/**
	 * Test isExitEmail()
	 */
	@Test
	public void testIsExistEmail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.prefix_no_key + MapperDict.status, EStatus.disable + "," + EStatus.deleted);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getUsersn());
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
		//map.put(BaseMapperDict.uid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new User();
		entity.setUid(TestConst.uid1);
		entity.setPassword("123");
		entity.setPaypwd("123456");
		entity.setResetpwd("123");
		entity.setAccount(999.99);
		entity.setFreezeaccount(888.88);
		entity.setTotalpay(88888.66);
		entity.setTotalincome(66666.22);
		entity.setEmail("1112332@qq.com");
		entity.setType(Integer.valueOf(EUser.judgement.toString()));
		entity.setChannelfrom(EUserChannel.inviteuser);
		entity.setLogincount(888);
		entity.setLastlogintime(new DateTime());
		entity.setLastloginip("127.0.0.1");
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
		entity = new User();
		entity.setUid(TestConst.uid1);
		entity.setPassword("123");
		entity.setPaypwd("123456");
		entity.setResetpwd("123");
		entity.setAccount(999.99);
		entity.setFreezeaccount(888.88);
		entity.setTotalpay(88888.66);
		entity.setTotalincome(66666.22);
		entity.setEmail("1112332@qq.com");
		entity.setType(Integer.valueOf(EUser.judgement.toString()));
		entity.setChannelfrom(EUserChannel.userfee);
		entity.setLogincount(888);
		entity.setLastlogintime(new DateTime());
		entity.setLastloginip("127.0.0.1");
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		String password = "123987";
		entity.setPassword(password);
		entity.setAccount(966624.2);
		entity.setCreatetime(new DateTime());
		entity.setType(Integer.valueOf(EUser.expert.toString()));
		entity.setChannelfrom(EUserChannel.inviteuser);
		assertTrue(dao.update(entity));
		assertEquals(password, dao.get(uid).getPassword());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new User();
		entity.setUid(TestConst.uid1);
		entity.setPassword("123");
		entity.setPaypwd("123456");
		entity.setResetpwd("123");
		entity.setAccount(999.99);
		entity.setFreezeaccount(888.88);
		entity.setTotalpay(88888.66);
		entity.setEmail("1112332@qq.com");
		entity.setTotalincome(66666.22);
		entity.setType(Integer.valueOf(EUser.judgement.toString()));
		entity.setChannelfrom(EUserChannel.inviteuser);
		entity.setLogincount(888);
		entity.setLastlogintime(new DateTime());
		entity.setLastloginip("127.0.0.1");
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		String password = "963852";
		map.put(MapperDict.password, password);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		assertEquals(password, dao.get(uid).getPassword());
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new User();
		entity.setUid(TestConst.uid1);
		entity.setPassword("123");
		entity.setPaypwd("123456");
		entity.setResetpwd("123");
		entity.setAccount(999.99);
		entity.setFreezeaccount(888.88);
		entity.setTotalpay(88888.66);
		entity.setEmail("1112332@qq.com");
		entity.setTotalincome(66666.22);
		entity.setType(Integer.valueOf(EUser.judgement.toString()));
		entity.setChannelfrom(EUserChannel.inviteuser);
		entity.setLogincount(888);
		entity.setLastlogintime(new DateTime());
		entity.setLastloginip("127.0.0.1");
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}

	/**
	 * 输出加密后的密码
	 */
	@Test
	@Ignore
	public void testInitPwd() {
		entity = new User();
		entity.setUid("2b3e16d2c57d4f14a8244924dc4ae39f");
		entity.initPayPwd(TestConst.paypwd);
		entity.initPassword(TestConst.mima_123);
		System.out.println("password:" + entity.getPassword());
		System.out.println("paypassword:" + entity.getPaypwd());
	}
	
	/*
	 * 修改所有用户的密码为：mima.123 
	 * 邮箱为：1113449881@qq.com
	 */
	@Test
	@Ignore
	public void testUpdateAllUserPwd() {
		List<User> list = dao.getList(null);
		for (User user : list) {
			user.initPayPwd(TestConst.mima_123);
			user.initPassword(TestConst.mima_123);
			user.setAccount(10000000);
			user.setEmail("1113449881@qq.com");
			dao.update(user);
		}
	}
	
	/**
	 * 测试用户的类型
	 */
	@Test
	@Ignore
	public void testEUser() {
		int type = 1610612737;
		assertTrue(EUser.student.ismatch(type));
	}
	
	//获得一个UUID
	@Test
	public void getUUID() {
		System.out.println(GlobalUtil.getUUID());
	}

}