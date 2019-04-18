package com.genius.core.base.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class BarCodeUtilTest {

	private static String testPath = StrUtil.trim(ClassLoader.getSystemResource("files").getFile(), "/") + "/qrcode.png";
	private static String testText = "weixin：//wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D";

	@Test
	public void testCreateQRCodeToFile() {
		BarCodeUtil.createQRCodeToFile(testText, 500, 500, testPath);
	}

	@Test
	@Ignore
	public void testCreateQRCodeToStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseBarCodeFromStream() {
		String text;
		try {
			text = BarCodeUtil.parseBarCodeFromStream(ImageIO.read(new File(testPath)));
			assertEquals(text, testText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
