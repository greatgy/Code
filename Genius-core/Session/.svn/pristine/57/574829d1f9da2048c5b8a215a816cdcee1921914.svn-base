package com.genius.core.session.interfaces.rule;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.cache.rule.Rule;
import com.genius.core.session.mock.testconstants.TestConst;
import com.genius.core.session.mock.testentity.CacheConf;
import com.genius.core.session.rule.SessionRule;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SessionRuleTest {

	@Test
	public void test() {
		Rule rule = new SessionRule(TestConst.uid, TestConst.userId);
		String expected = CacheConf.basePath + "/s/" + TestConst.uid + ":" + TestConst.userId;
		System.out.println(rule.toString());
		System.out.println(expected);
		assertEquals(expected, rule.getKey());
		assertEquals(expected + ",0", rule.toString());
	}

}
