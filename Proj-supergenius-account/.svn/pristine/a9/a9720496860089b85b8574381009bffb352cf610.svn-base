package com.supergenius.web.account.api;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

public class DemoControllerTest {

	@Test
	@Ignore
	public void testMD5Verify() {
		String APIUID = "uid11111111111111111111111111111";
		String payuid = "c6b1c7d3181c43489edf2db8245344ac";
		String banktype = "11";
		String money = "0.01";
		String useruid = "uid11111111111111111111111111111";
		String pwdmd5 = "31fcc458f3fd9304f1c0b5fd7f83ae15";
		StringBuffer strb = new StringBuffer();
		strb.append(APIUID).append(payuid).append(banktype).append(money).append(useruid);
		String pwdString = DigestUtils.md5Hex(strb.toString());
		System.out.println(pwdString);
		assertEquals(pwdmd5, pwdString);
		System.out.println((new DateTime()).toString("yyyy-MM-dd hh-mm-ss"));
	}
	
	@Test
	public void testDateTime() {
		System.out.println((new DateTime()).toString("yyyy-MM-dd HH:mm:ss"));
	}

}
