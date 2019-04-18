package com.supergenius.xo.mock.testconstants;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author liushaomin
 *
 */
public class TestConst {

	public static final String name = "zhangsan";
	
	public static final String _123456 = "123456";
	
	public static final String mima_123 = "mima.123";
	
	public static final String paypwd = "pay.123";

	public static final String userId = "greathill";

	public static final String uid = "uid00000000000000000000000000000";
	
	

	public static final String uid1 = "uid11111111111111111111111111111";

	public static final String uid2 = "uid22222222222222222222222222222";
	
	public static final String uid3 = "73e08d4a0bcf4da5a4f2a1ff8e7333c8";
	
	public static final int oid = 1;
	
	public static final Long count = 5L;
	
	public static final String usersn = "testusersn";
	
	public static final String email = "123456789@qq.com";
	
	public static final String mobile = "18338384380";

	/**
	 * 可测试常量之间关联是否正确
	 */
	@Test
	public void testUserConstant() {
		assertTrue(true);
	}
}
