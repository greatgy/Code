package com.genius.core.cache.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.cache.mock.testentity.User;
import com.genius.core.cache.rule.CountRule;
import com.genius.core.cache.rule.OneRule;
import com.genius.core.cache.rule.Rule;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class RedisUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.wbcom.global.utility.RedisUtil#Incr(com.wbcom.core.cache.Rule)}
	 * .
	 */
	@Test
	public void testIncr() {
		CountRule rule = new CountRule(GlobalUtil.getUUID());
		System.out.println(rule);
		assertEquals(1, RedisUtil.incr(rule));
		assertEquals(2, RedisUtil.incr(rule));
		assertEquals(2, RedisUtil.getIncr(rule));
		assertEquals(true, RedisUtil.delIncr(rule));
		assertEquals(-1, RedisUtil.getIncr(rule));
		assertEquals(1, RedisUtil.incr(rule));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.global.utility.RedisUtil#getStr(com.wbcom.core.cache.Rule)}
	 * .
	 */
	@Test
	public void testGetStr() {
		Rule rule = new OneRule(GlobalUtil.getUUID());
		String expected = "hi,it's me.";
		RedisUtil.set(rule, expected);
		assertEquals(expected, RedisUtil.getStr(rule));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.global.utility.RedisUtil#getLong(com.wbcom.core.cache.Rule)}
	 * .
	 */
	@Test
	public void testGetLong() {
		Rule rule = new OneRule(GlobalUtil.getUUID());
		long expected = 123999;
		RedisUtil.set(rule, expected);
		assertEquals(expected, RedisUtil.getLong(rule));
	}

	@Test
	public void testSortedSet() {
		Rule rule = new OneRule(GlobalUtil.getUUID());
		int expected = 9;
		String field = "x";
		RedisUtil.addSortedSet(rule, field, expected);
		int actual = (int) RedisUtil.getSortSetVal(rule, field);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetObject() throws IOException, ClassNotFoundException {
		String uid1 = "uid1";
		Rule rule = new OneRule(GlobalUtil.getUUID());
		User user = new User();
		user.setUid(uid1);
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		baos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(baos);
		oos.writeObject(user);
		byte[] bytes = baos.toByteArray();
		RedisUtil.set(rule, bytes);
		byte[] b = RedisUtil.getBytes(rule);
		ByteArrayInputStream bais = null;
		bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		user = (User) ois.readObject();
		System.out.println(rule.toString());
	}
}
