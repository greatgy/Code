package com.genius.core.cache.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.cache.mock.testconstants.TestConst;
import com.genius.core.cache.mock.testentity.User;
import com.genius.core.cache.rule.SysRule;
import com.genius.core.cache.rule.UserOnlineRule;
import com.genius.core.cache.rule.UserRule;
import com.genius.core.cache.utils.MemcacheUtil;

/**
 * @author GreatHost
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MemcacheUtilTest {

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#set(com.wbcom.core.cache.Rule, java.lang.Object)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testSet() throws Exception {
		// MemcacheUtil.flushAll(new UserRule());
		// MemcacheUtil.flushAll(new UserOnlineRule());
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		assertNotNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		MemcacheUtil.set(new UserOnlineRule(TestConst.userId), TestConst.uid);
		assertNotNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(TestConst.uid, ((User) MemcacheUtil.get(new UserRule(TestConst.userId))).getUid());
		assertEquals(TestConst.userId, ((User) MemcacheUtil.get(new UserRule(TestConst.userId))).getUserid());
		assertEquals(TestConst.uid, MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#get(com.wbcom.core.cache.Rule)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testGet() throws Exception {
		assertNull(MemcacheUtil.get(new UserRule("")));
		assertNull(MemcacheUtil.get(new UserOnlineRule("")));
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		MemcacheUtil.set(new UserOnlineRule(TestConst.userId), TestConst.uid);
		assertEquals(TestConst.uid, ((User) MemcacheUtil.get(new UserRule(TestConst.userId))).getUid());
		assertEquals(TestConst.userId, ((User) MemcacheUtil.get(new UserRule(TestConst.userId))).getUserid());
		assertEquals(TestConst.uid, MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
	}

	@Test
	public final void testExpire() throws Exception {
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId, 1), user);
		assertNotNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		Thread.sleep(1500);
		assertNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		MemcacheUtil.set(new UserOnlineRule(TestConst.userId, 2), TestConst.uid);
		assertNotNull(MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
		Thread.sleep(1000);
		assertNotNull(MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
		Thread.sleep(1500);
		assertNull(MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
	}

	@Test
	public final void testIncrDecr() throws Exception {
		MemcacheUtil.remove(new UserRule(TestConst.userId));
		assertNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(1, MemcacheUtil.incr(new UserRule(TestConst.userId), 1));// 若不存在
																					// 初始值为1
		assertNotNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(2, MemcacheUtil.incr(new UserRule(TestConst.userId), 1));
		assertEquals("2", MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(1000, MemcacheUtil.incr(new UserRule(TestConst.userId), 998));
		Thread.sleep(1500);
		assertEquals(1001, MemcacheUtil.incr(new UserRule(TestConst.userId), 1));
		assertEquals("1001", MemcacheUtil.get(new UserRule(TestConst.userId)));
		Thread.sleep(1500);
		assertEquals(1000, MemcacheUtil.decr(new UserRule(TestConst.userId), 1));
		assertEquals("1000", MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(1, MemcacheUtil.decr(new UserRule(TestConst.userId), 999));
		// System.out.println(MemcacheUtil.decr(new
		// UserRule(TestConstants.userId), 2));
		// System.out.println(MemcacheUtil.decr(new
		// UserRule(TestConstants.userId), 2));
		// System.out.println(MemcacheUtil.decr(new
		// UserRule(TestConstants.userId), 2));
		// System.out.println(MemcacheUtil.decr(new
		// UserRule(TestConstants.userId), 2));
		assertEquals(0, MemcacheUtil.decr(new UserRule(TestConst.userId), 2));
		// assertEquals("0",MemcacheUtil.get(new
		// UserRule(TestConstants.userId)));//负数get不正确
		MemcacheUtil.remove(new UserRule(TestConst.userId));
		assertNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(0, MemcacheUtil.decr(new UserRule(TestConst.userId), 1));// 若不存在
																					// 初始值为0
		assertEquals(0, MemcacheUtil.decr(new UserRule(TestConst.userId), 1));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#getKeys(com.wbcom.core.cache.Rule)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testGetKeys() throws Exception {
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		System.out.println(MemcacheUtil.getKeys(new UserRule(TestConst.userId)));
		assertTrue(MemcacheUtil.getKeys(new UserRule(TestConst.userId)).contains(new UserRule(TestConst.userId)));
		assertFalse(MemcacheUtil.getKeys(new UserRule(TestConst.userId)).contains(TestConst.uid));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#getValues(com.wbcom.core.cache.Rule)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testGetValues() throws Exception {
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		MemcacheUtil.set(new UserRule(TestConst.uid), user);
		assertTrue(MemcacheUtil.getValues(new UserRule(TestConst.userId)).contains(user));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#remove(com.wbcom.core.cache.Rule)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testRemove() throws Exception {
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.uid), user);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		MemcacheUtil.remove(new UserRule(TestConst.userId));
		assertNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertEquals(TestConst.userId, ((User) MemcacheUtil.get(new UserRule(TestConst.uid))).getUserid());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheUtil#flushAll(com.wbcom.core.cache.Rule)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testFlushAll() throws Exception {
		User user = new User();
		user.setUid(TestConst.uid);
		user.setUserid(TestConst.userId);
		MemcacheUtil.set(new UserRule(TestConst.userId), user);
		MemcacheUtil.set(new UserOnlineRule(TestConst.userId), TestConst.uid);
		MemcacheUtil.flushAll(new UserRule());
		MemcacheUtil.flushAll(new UserOnlineRule());
		assertNull(MemcacheUtil.get(new UserRule(TestConst.userId)));
		assertNull(MemcacheUtil.get(new UserOnlineRule(TestConst.userId)));
	}

	@Test
	public void testGetAllKeys() throws Exception {
		Set<String> set = MemcacheUtil.getKeys(new SysRule());
		System.out.println(set);
	}

	@Test
	public void testGetAllValues() throws Exception {
		Collection<Object> set = MemcacheUtil.getValues(new SysRule());
		System.out.println(set);
	}
}
