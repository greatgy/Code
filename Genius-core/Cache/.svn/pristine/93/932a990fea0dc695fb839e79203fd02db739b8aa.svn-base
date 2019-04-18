package com.genius.core.cache.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.cache.mock.testconstants.TestConst;
import com.genius.core.cache.mock.testentity.User;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MemcacheEngineTest {

	@Autowired
	MemcacheEngine engine;

	@Autowired
	User user;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		engine.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		engine.stop();
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#set(java.lang.String, java.lang.Object)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetStringObject() throws Exception {
		String key = "/asdhj234f";
		user.setUid(TestConst.uid);
		Object value = user;
		engine.set(key, value);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEmptyKey() {
		// engine.set(null, "null");
		engine.set("", "empty");
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#set(java.lang.String, int, java.lang.Object)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetStringIntObject() throws Exception {
		String key = "/asdhj234f";
		user.setUid(TestConst.uid);
		Object value = user;
		engine.set(key, 1, value);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
		Thread.sleep(2000);
		assertNull(engine.get(key));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#get(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetString() throws Exception {
		String key = "/asdhj234f";
		user.setUid(TestConst.uid);
		Object value = user;
		engine.set(key, value);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#get(java.lang.String, int)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStringInt() throws Exception {
		String key = "/asdhj234f";
		user.setUid(TestConst.uid);
		Object value = user;
		engine.set(key, 1, value);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
		assertEquals(TestConst.uid, ((User) engine.get(key, 2)).getUid());
		Thread.sleep(1050);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
		Thread.sleep(2000);
		assertNull(engine.get(key));
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#gets(java.util.Collection)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGets() throws Exception {
		String key1 = "/key1";
		String key2 = "/key2";
		user.setUid(TestConst.uid);
		engine.set(key1, user);
		user.setUserid(TestConst.userId);
		engine.set(key2, user);
		Collection<String> keys = new HashSet<String>();
		keys.add(key1);
		keys.add(key2);
		assertEquals(2, engine.get(keys).size());
		assertEquals(TestConst.uid, ((User) engine.get(keys).get(key1)).getUid());
		assertEquals(TestConst.userId, ((User) engine.get(keys).get(key2)).getUserid());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#getKeys()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetKeys() throws Exception {
		assertTrue(engine.clearAll());
		String key1 = "/key1";
		String key2 = "/key2";
		user.setUid(TestConst.uid);
		engine.set(key1, user);
		user.setUserid(TestConst.userId);
		engine.set(key2, user);
		assertEquals(2, engine.getKeys().size());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#getValues()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValues() throws Exception {
		engine.clearAll();
		String key1 = "/key1";
		String key2 = "/key2";
		user.setUid(TestConst.uid);
		engine.set(key1, user);
		user.setUserid(TestConst.userId);
		engine.set(key2, user);
		assertEquals(2, engine.getValues().size());
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.MemcacheEngine#remove(java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRemove() throws Exception {
		String key = "/keylasdjf";
		user.setUid(TestConst.uid);
		engine.set(key, user);
		assertEquals(TestConst.uid, ((User) engine.get(key)).getUid());
		engine.remove(key);
		assertNull(engine.get(key));
	}

	/**
	 * Test method for {@link com.wbcom.core.cache.impl.MemcacheEngine#size()}.
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testSize() throws Exception {
		String key1 = "/key1";
		String key2 = "/key2";
		user.setUid(TestConst.uid);
		engine.set(key1, user);
		user.setUserid(TestConst.userId);
		engine.set(key2, user);
		assertEquals(0, engine.size());
	}

}
