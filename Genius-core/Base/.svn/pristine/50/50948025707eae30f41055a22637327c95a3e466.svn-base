package com.genius.core.base.java;

import org.junit.Test;

/**
 * -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author admin
 *
 */
public class GCTest {
	
	public Object instance = null;
	private static int MB1 = 1024 * 1024;
	@SuppressWarnings("unused")
	private byte[] bigSize = new byte[2 * MB1];
	
	@Test
	public void SystemGCTest() throws InterruptedException {
		GCTest objA = new GCTest();
		GCTest objB = new GCTest();
		
		objA.instance = objB;
		objB.instance = objA;
		
		objA = null;
		objB = null;
		
//		Thread.sleep(60 * 1000);
		System.gc();
	}
}
