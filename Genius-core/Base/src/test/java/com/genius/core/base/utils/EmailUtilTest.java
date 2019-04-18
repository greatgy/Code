package com.genius.core.base.utils;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;

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
public class EmailUtilTest {

	@Test
	@Ignore
	public void testSend() {
		 assertTrue(EmailUtil.send("service@supergenius.cn", "你好", "测试成功！"));
	}
	
	@Test
	public void mimeUtilityTest() throws UnsupportedEncodingException, ParseException {
		String str = "hi, 你好！";
		String txt = MimeUtility.encodeText(str);
		System.out.println(txt);//=?UTF-8?Q?hi,_=E4=BD=A0=E5=A5=BD=EF=BC=81?=
//		System.out.println(MimeUtility.decodeText(txt));
//		System.out.println(MimeUtility.decodeWord(txt));
		System.out.println(str.equals(MimeUtility.decodeText(txt)));
		System.out.println(str.equals(MimeUtility.decodeWord(txt)));
		String word = MimeUtility.encodeWord(str);
		System.out.println(word);//=?UTF-8?Q?hi=2C_=E4=BD=A0=E5=A5=BD=EF=BC=81?=
//		System.out.println(MimeUtility.decodeText(word));
//		System.out.println(MimeUtility.decodeWord(word));
		System.out.println(str.equals(MimeUtility.decodeText(word)));
		System.out.println(str.equals(MimeUtility.decodeWord(word)));
	}
}
