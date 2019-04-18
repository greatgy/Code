package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;

import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.mock.testconstants.TestConst;
import com.genius.core.base.mock.testentity.User;

public class FreeMarkerUtilTest {

	private static String emailtemplspath = ClassLoader.getSystemResource("Files/emailtempls").getPath();

	public static final String emailtemplname = "test.data";
	
	/**
	 * 测试FreeMarkderUtilTest.process方法解析填充邮件内容
	 * 
	 * @throws IOException
	 * @author: LiuYongjian
	 */
	@Test
	public void freemarkerUtilTest() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		String uid = GlobalUtil.getUUID();
		user.setUid(uid);
		user.setEmail("aa@bb.com");
		user.setName("测试名");
		user.setPwd(TestConst.pwd_123456);
		map.put("judge", user);
		map.put("block", "content");
		DateTime datetime = new DateTime();
		String date = datetime.toString(DateUtil.FORMAT_DATETIME_CHINA);
		map.put(BaseViewKeyDict.date, date);
		String content = FreemarkerUtil.process(emailtemplspath, emailtemplname, map);
		System.out.println(content);
		map.put("block", "title");
		String title = FreemarkerUtil.process(emailtemplspath, emailtemplname, map);
		System.out.println(title);
	}
	
	@Test
	public void testProcessStr() {
		String tmpl = "I am ${name}";
		Map<String, Object> map = new HashMap<>();
		map.put("name", "tiger");
		assertEquals("I am tiger", FreemarkerUtil.processStr(tmpl, map));
		assertEquals("hi world", FreemarkerUtil.processStr("hi world", map));
	}

}
