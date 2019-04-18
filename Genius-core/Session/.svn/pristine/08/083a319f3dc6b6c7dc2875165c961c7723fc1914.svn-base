package com.genius.core.session.interfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.session.mock.testconstants.TestConst;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SessionTest {

	@Autowired
	HttpSession session;

	@Autowired
	HttpSession session2;

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.SessionImpl#getCreationTime()}.
	 */
	@Test
	public void testGetCreationTime() {
		long time1 = session.getCreationTime();
		long time2 = session2.getCreationTime();
		assertEquals(time1, time2);
	}

	/**
	 * Test method for
	 * {@link com.wbcom.core.cache.impl.SessionImpl#getAttributeNames()}.
	 */
	@Test
	public void testAttribute() {
		session.setAttribute(TestConst.uid, TestConst.userId);
		assertEquals(session.getAttribute(TestConst.uid), TestConst.userId);
		assertEquals(session2.getAttribute(TestConst.uid), TestConst.userId);
		session2.removeAttribute(TestConst.uid);
		assertNull(session.getAttribute(TestConst.uid));
	}

}
