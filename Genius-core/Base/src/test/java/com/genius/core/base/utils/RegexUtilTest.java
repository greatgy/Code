package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author liushaomin
 *
 */
public class RegexUtilTest {
	
	/**
	 * 判断是否是邮箱
	 */
	@Test
	public void isEmail(){
		System.out.println(RegexUtil.isEmail("466435707@qq.com"));
		assertTrue(RegexUtil.isEmail("466435707@qq.com"));
	}
	
	@Test
	public void testReplace() {
		String str = "hello, world";
		String regex = "^hello,";
		String regex2 = "^(hello, |hi )";
		assertEquals("hi world", str.replaceFirst(regex, "hi"));
		System.out.println(str.replaceFirst(regex, "hi"));
		assertEquals(str.replaceFirst(regex, "hi").replaceFirst(regex2, ""), str.replaceFirst(regex2, ""));
	}

}
