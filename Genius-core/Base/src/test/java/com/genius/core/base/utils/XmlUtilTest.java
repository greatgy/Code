package com.genius.core.base.utils;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class XmlUtilTest {

	@Test
	public void testParseXmlToMap() {
		String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		Map<String, String> map = XmlUtil.parseXmlToMap(xml);
		assertEquals("SUCCESS", map.get("return_code").toString());
		System.out.println(map);
	}

}
