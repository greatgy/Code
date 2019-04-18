package com.supergenius.xo.mock.testconstants;

import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * 测试常量类
 * 
 * @author LiJiacheng
 */
public class TestConst {

	public static final String name = "zhangsan";
	
	public static final String optionsalways = "optionsalways";
	
	public static final String optionsonce = "optionsonce";
	
	public static final String name1 = "zhangsan1";

	public static final String username = "zhangsan";

	public static final String _123456 = "123456";

	public static final String pwd_123456 = DigestUtils.md5Hex(_123456);

	public static final String userId = "greathill";

	public static final String uid = "000000000000000000000000";

	public static final String uid1 = "000111111111111111111111";

	public static final String uid2 = "000222222222222222222222";
	
	public static final String uid3 = "000333333333333333333333";

	public static final int oid = 1;

	public static final String title = "title";

	public static final String title1 = "title1";
	
	public static final String version = "version";

	/**
	 * 可测试常量之间关联是否正确
	 */
	@Test
	public void testUserConstant() {
		assertTrue(true);
	}

}
