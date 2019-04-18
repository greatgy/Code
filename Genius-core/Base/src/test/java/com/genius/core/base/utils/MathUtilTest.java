package com.genius.core.base.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author architect.bian
 *
 */
public class MathUtilTest {

	@Test
	public void testScale() {
		double d1 = 1.123456;
		double expect = 1.12;
		assertTrue(expect == MathUtil.scale(d1, 2));
		d1 = 1.1256789;
		assertTrue(expect == MathUtil.scale(d1, 2));
		double d2 = 1.66;
		double actual = MathUtil.scale(d2, 2);
		System.out.println(actual);
		assertTrue(1.66 != actual);
	}
	
	@Test
	public void testScale_Halfup() {
		double d1 = 1.123456;
		double expect = 1.12;
		assertTrue(expect == MathUtil.scale_Halfup(d1, 2));
		d1 = 1.1256789;
		expect = 1.13;
		assertTrue(expect == MathUtil.scale_Halfup(d1, 2));
		double d2 = 1.66;
		double actual = MathUtil.scale_Halfup(d2, 2);
		System.out.println(actual);
		assertTrue(1.66 == actual);
	}
	
	@Test
	public void testMuti() {
		double d1 = 3.1415926;
		double d2 = 3.146789;
		assertEquals((int) MathUtil.muti(d1, 100), 314);
		assertEquals((int) MathUtil.muti(d2, 100), 314);
	}
}
