package com.genius.core.base.utils;

import java.util.Random;

import org.junit.Test;

/**
 * @author Architect.bian
 * 
 */
public class NumUtilTest {

	@Test
	public void testRandom() {
		Random random = new Random();
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
	}

}
