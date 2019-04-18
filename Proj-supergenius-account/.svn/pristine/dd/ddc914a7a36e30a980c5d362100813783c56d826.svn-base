package com.supergenius.third.wxpay.qrcode.config;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class PayDataTest {

	@Test
	public void testSign() {
		String md5 = DigestUtils.md5Hex("appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA&key=192006250b4c09247ec02edce69f6a2d");
		assertEquals("9A0A8659F005D6984697E2CA0A9CF3B7", md5.toUpperCase());
		md5 = DigestUtils.md5Hex("appid=wx9c3fa5f18b28093e&body=充值&mch_id=1353784902&nonce_str=50aa613ec9ad41bebb8d9e8d2d006ca0&notify_url=http://account.supergenius.cn/api/frombank13/63a402e83d88402db30d8e7383d0996a&out_trade_no=C170104121432947&spbill_create_ip=127.0.0.1&total_fee=1&trade_type=NATIVE&key=08277a5eaf6546a69a7b8442d17e5e7e");
		assertEquals("7CCB698431AE80F62D13EE16E062C7F3", md5.toUpperCase());
	}

}
