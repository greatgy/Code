package com.genius.xo.baseadmin.mock.testconstants;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author Architect.bian
 * 
 */
public class TestConst {

	public static final String name = "zhangsan";
	
	public static final String _123456 = "123456";

	public static final String pwd_123456 = DigestUtils.md5Hex(_123456);

	public static final String userId = "greathill";

	public static final String uid = "uid00000000000000000000000000000";

	public static final String uid1 = "uid11111111111111111111111111111";

	public static final String uid2 = "uid22222222222222222222222222222";

	public static final int oid = 1;

	/**
	 * 可测试常量之间关联是否正确
	 */
	@Test
	public void testUserConstant() {
		assertTrue(true);
	}

}
