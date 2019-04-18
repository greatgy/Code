package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;

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
public class FileUtilTest {

	private static String imgtest = StrUtil.trim(ClassLoader.getSystemResource("files/test.jpg").getFile(), "/");

	@Test
	public void toBase64Test() {
		String strEnc = FileUtil.toBase64(imgtest);
		System.out.println(strEnc);
		FileUtil.fromBase64(strEnc, imgtest);
		assertEquals(strEnc, FileUtil.toBase64(imgtest));
	}
}
