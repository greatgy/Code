package com.genius.core.cache.rule;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.cache.mock.testconstants.TestConst;
import com.genius.core.cache.mock.testentity.CacheConf;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UserRuleTest {

	@Test
	public void testUserRule() {
		Rule rule = new UserRule();
		rule.setKey(TestConst.uid);
		String cacheId = "/users";
		String path = CacheConf.basePath + "/users";
		String expected = CacheConf.basePath + "/users" + ":" + TestConst.uid;
		assertEquals(cacheId, rule.getConfKey());
		assertEquals(path, rule.getPath());
		assertEquals(expected, rule.getKey());
		assertEquals(expected + ",0", rule.toString());
		System.out.println(rule.getKey());
		System.out.println(rule.toString());
	}

	@Test
	public void testUserRule1Args() {
		Rule rule = new UserRule(TestConst.uid);
		String cacheId = "/users";
		String path = CacheConf.basePath + "/users";
		String expected = CacheConf.basePath + "/users" + ":" + TestConst.uid;
		assertEquals(cacheId, rule.getConfKey());
		assertEquals(path, rule.getPath());
		assertEquals(expected, rule.getKey());
		assertEquals(expected + ",0", rule.toString());
	}

	@Test
	public void testUserRule2Args() {
		Rule rule = new UserRule(TestConst.uid, 1);
		String cacheId = "/users";
		String path = CacheConf.basePath + "/users";
		String expected = path + ":" + TestConst.uid;
		assertEquals(cacheId, rule.getConfKey());
		assertEquals(path, rule.getPath());
		assertEquals(expected, rule.getKey());
		assertEquals(expected, rule.getKeys().toArray()[0]);
		assertEquals(expected + ",1", rule.toString());
		assertEquals(1, rule.getExpire());
		String key2 = "key222222";
		String expected2 = path + ":" + key2;
		rule.addKey(key2);
		assertEquals(2, rule.getKeys().size());
		assertTrue(rule.getKeys().contains(expected2));
	}

}
