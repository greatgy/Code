package com.genius.core.base.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
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
public class StrUtilTest {
	
	@Test
	public void testSplitToIntArray() {
		String str = "6 1 3 5";
		int[] expecteds = new int[] { 6, 1, 3, 5 };
		assertArrayEquals(expecteds, StrUtil.splitToIntArray(str));
		str = "6 1 3 5 ";
		assertArrayEquals(expecteds, StrUtil.splitToIntArray(str));
		str = "  6   1   3   5         ";
		assertArrayEquals(expecteds, StrUtil.splitToIntArray(str));
	}

	@Test
	public void testIsMatchAny() {
		String regex = ".*noavatar\\.png|.*noavatar180x180\\.png";
		assertTrue(StrUtil.isMatchAny("asdffd/asd/noavatar.png", regex));
		assertTrue(StrUtil.isMatchAny("asdffd/asd/noavatar180x180.png", regex));
		assertTrue(Pattern.compile("[\u2E80-\u9FFF]", Pattern.CASE_INSENSITIVE).matcher("你好").find());
		assertTrue(Pattern.compile("[a-zA-Z\u2E80-\u9FFF]", Pattern.CASE_INSENSITIVE).matcher("你好").find());
		assertTrue(Pattern.compile("[a-zA-Z\u2E80-\u9FFF]", Pattern.CASE_INSENSITIVE).matcher("abc").find());
		assertTrue(Pattern.compile("[a-zA-Z\u2E80-\u9FFF ]", Pattern.CASE_INSENSITIVE).matcher("  	").find());
	}

	@Test
	public void testRemoveSpace() {
		String str = "abcde     \nf \r  g ";
		String expected = "abcdefg";
		String actual = StrUtil.removeSpace(str);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFormat() {
		assertEquals("003", StrUtil.format("000", 3));
		assertEquals("3.00", StrUtil.format("#.00", 3));
	}
	
	@Test
	@Ignore
	public void testGetByCharset() throws UnsupportedEncodingException {
		String hi = StrUtil.get("你好", "UTF-8");
		System.out.println(hi);
		String hello = StrUtil.get("浣犲ソ", "GBK", "UTF-8");
		System.out.println(hello);
		assertEquals(hi, hello);
	}
	
	@Test
	public void testGetByCharset2() throws UnsupportedEncodingException {
		String hi = StrUtil.get("天才交易款","UTF-8", "GBK");
		System.out.println(hi);
		System.out.println(StrUtil.get(hi,"UTF-8", "GBK"));
		hi = StrUtil.get(hi,"GBK", "UTF-8");
		System.out.println(hi);
		hi = StrUtil.get(hi,"UTF-8", "GBK");
		System.out.println(hi);
		hi = StrUtil.get("天才交易款", "GBK", "GBK");
		hi = StrUtil.get(hi, "GBK", "UTF-8");
		System.out.println(hi);
		hi = StrUtil.get(hi, "UTF-8", "GBK");
		System.out.println(hi);
		hi = StrUtil.get(hi, "GBK", "UTF-8");
		System.out.println(hi);
	}
	
	@Test
	public void testBase64enc() throws IOException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><B2CReq><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.11</interfaceVersion><orderInfo><orderDate>20131215152745</orderDate><curType>001</curType><merID>0200EC23940823</merID><subOrderInfoList><subOrderInfo><orderid>1312151527453250004</orderid><amount>1</amount><installmentTimes>1</installmentTimes><merAcct>0200095719200060423</merAcct><goodsID></goodsID><goodsName>你好</goodsName><goodsNum></goodsNum><carriageAmt></carriageAmt></subOrderInfo></subOrderInfoList></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><Language>ZH_CN</Language></custom><message><creditType>2</creditType><notifyType>HS</notifyType><resultType>0</resultType><merReference>www.supergenius.cn</merReference><merCustomIp>118.194.132.89</merCustomIp><goodsType>1</goodsType><merCustomID></merCustomID><merCustomPhone></merCustomPhone><goodsAddress></goodsAddress><merOrderRemark></merOrderRemark><merHint></merHint><remark1></remark1><remark2></remark2><merURL>http://www.supergenius.cn/api/frombank0/637e56b3cd3c4cc08a53aa6bb4a24ff2</merURL><merVAR></merVAR></message></B2CReq>";
		xml = StrUtil.get(xml, "GBK");
		byte[] bytes = xml.getBytes("GBK");
		String str1 = StrUtil.base64Enc(bytes);
		xml = StrUtil.get(xml, "UTF-8");
		bytes = xml.getBytes("UTF-8");
		String str2 = StrUtil.base64Enc(bytes);
		System.out.println(str1);
		System.out.println(str2);
		assertTrue(!str1.equals(str2));
	}
	
	@Test
	public void testBase64Enc() throws IOException {
		String todecode = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PEIyQ1JlcT48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjExPC9pbnRlcmZhY2VWZXJzaW9uPjxvcmRlckluZm8+PG9yZGVyRGF0ZT4yMDEzMTIxNTE1Mjc0NTwvb3JkZXJEYXRlPjxjdXJUeXBlPjAwMTwvY3VyVHlwZT48bWVySUQ+MDIwMEVDMjM5NDA4MjM8L21lcklEPjxzdWJPcmRlckluZm9MaXN0PjxzdWJPcmRlckluZm8+PG9yZGVyaWQ+MTMxMjE1MTUyNzQ1MzI1MDAwNDwvb3JkZXJpZD48YW1vdW50PjE8L2Ftb3VudD48aW5zdGFsbG1lbnRUaW1lcz4xPC9pbnN0YWxsbWVudFRpbWVzPjxtZXJBY2N0PjAyMDAwOTU3MTkyMDAwNjA0MjM8L21lckFjY3Q+PGdvb2RzSUQ+PC9nb29kc0lEPjxnb29kc05hbWU+5L2g5aW9PC9nb29kc05hbWU+PGdvb2RzTnVtPjwvZ29vZHNOdW0+PGNhcnJpYWdlQW10PjwvY2FycmlhZ2VBbXQ+PC9zdWJPcmRlckluZm8+PC9zdWJPcmRlckluZm9MaXN0Pjwvb3JkZXJJbmZvPjxjdXN0b20+PHZlcmlmeUpvaW5GbGFnPjA8L3ZlcmlmeUpvaW5GbGFnPjxMYW5ndWFnZT5aSF9DTjwvTGFuZ3VhZ2U+PC9jdXN0b20+PG1lc3NhZ2U+PGNyZWRpdFR5cGU+MjwvY3JlZGl0VHlwZT48bm90aWZ5VHlwZT5IUzwvbm90aWZ5VHlwZT48cmVzdWx0VHlwZT4wPC9yZXN1bHRUeXBlPjxtZXJSZWZlcmVuY2U+d3d3LnN1cGVyZ2VuaXVzLmNuPC9tZXJSZWZlcmVuY2U+PG1lckN1c3RvbUlwPjExOC4xOTQuMTMyLjg5PC9tZXJDdXN0b21JcD48Z29vZHNUeXBlPjE8L2dvb2RzVHlwZT48bWVyQ3VzdG9tSUQ+PC9tZXJDdXN0b21JRD48bWVyQ3VzdG9tUGhvbmU+PC9tZXJDdXN0b21QaG9uZT48Z29vZHNBZGRyZXNzPjwvZ29vZHNBZGRyZXNzPjxtZXJPcmRlclJlbWFyaz48L21lck9yZGVyUmVtYXJrPjxtZXJIaW50PjwvbWVySGludD48cmVtYXJrMT48L3JlbWFyazE+PHJlbWFyazI+PC9yZW1hcmsyPjxtZXJVUkw+aHR0cDovL3d3dy5zdXBlcmdlbml1cy5jbi9hcGkvZnJvbWJhbmswLzYzN2U1NmIzY2QzYzRjYzA4YTUzYWE2YmI0YTI0ZmYyPC9tZXJVUkw+PG1lclZBUj48L21lclZBUj48L21lc3NhZ2U+PC9CMkNSZXE+";
		byte[] bytesdecode = Base64.decodeBase64(todecode);
		String strdecode = new String(bytesdecode, "GBK");
		System.out.println(strdecode);
		String strencode = StrUtil.base64Enc(strdecode.getBytes("GBK"));
		System.out.println(strencode);
		assertEquals(todecode, strencode);
	}
 }
