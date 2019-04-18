package com.genius.core.cache.rule;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class CountRuleTest {

	@Test
	public void test() {
		Rule rule = new CountRule();
		System.out.println(rule.getConfKey());
		System.out.println(rule.toString());
		assertEquals("/count:,0", rule.toString());
	}

}
