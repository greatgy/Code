package com.genius.core.base.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 身份证工具类
 * @author ChenQi
 * @date 2017年12月1日14:54:46
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class IDCardUtilTest {

	private static String testIDcard = "130431198003157583";//注:该身份证号码仅供测试使用
	
	@Test
	public void testIsIdcard() {
		 System.out.println("身份证号码是否合法："+IDCardUtil.isIdcard(testIDcard));
		 System.out.println("年龄："+IDCardUtil.getAgeByIdCard(testIDcard));
		 System.out.println("生日："+IDCardUtil.getBirthByIdCard(testIDcard));
		 System.out.println("性别："+IDCardUtil.getGenderByIdCard(testIDcard));
		 System.out.println("省份："+IDCardUtil.getProvinceByIdCard(testIDcard));
	}
}
