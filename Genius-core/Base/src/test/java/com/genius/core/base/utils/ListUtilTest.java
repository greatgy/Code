package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author architect.bian
 *
 */
public class ListUtilTest {

	@Test
	public void testToString(){
		assertEquals("1,2,3", ListUtil.toString(",", "1", "2", "3"));
		String[] strs = {"1", "2", "3"};
		assertEquals("1,2,3", ListUtil.toString(",", strs));
	}
}
