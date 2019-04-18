package com.genius.core.base.utils;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class HttpUtilTest {

	@Test
	public void testGet() {
		String result = HttpUtil.get("https://www.baidu.com");
//		System.out.println(result);
		assertTrue(result.contains("<html>"));
		assertTrue(result.contains("百度"));
	}
	
	@Test
	public void testGetParams() {
		Map<String, String> params = new HashMap<>();
		params.put("wd", "google");
		String result = HttpUtil.get("http://www.baidu.com/s", params);
//		System.out.println(result);
		assertTrue(result.contains("google"));
	}
	
	@Test
	public void testPost() {
		String result = HttpUtil.post("https://passport.baidu.com/v2/api/?login");
//		System.out.println(result);
		assertTrue(result.contains("<html>"));
	}
	
	@Test
	@Ignore
	public void testPostParams() {
		Map<String, String> params = new HashMap<>();
		params.put("name", "admin");
		params.put("pwd", "admin");
		String result = HttpUtil.post("http://127.0.0.1:9011/book/admin/login", params);
		System.out.println(result);
		assertTrue(result.contains("</html>"));
	}
	
	/**
	 * 
	@RequestMapping(value = { "/api/demopost" }, method = RequestMethod.POST, consumes= {"application/json", "application/xml"})
	public @ResponseBody String demopost(Map<String, Object> model, HttpServletRequest request, @RequestBody String body) {
		return String.format("Resonse: %s", body);
	}
	 */
	@Test
	@Ignore
	public void testPostXml() {
		String xml = "<root><name>admin</name><pwd>admin</pwd></root>";
		String result = HttpUtil.postXml("http://localhost:9085/proj-supergenius-account/api/demopost", xml);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void testPostJson() {
		String json = "{name: 'admin', pwd: 'admin'}";
		String result = HttpUtil.postJson("http://localhost:9085/proj-supergenius-account/api/demopost", json);
		System.out.println(result);
	}

}
