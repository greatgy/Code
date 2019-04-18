package com.genius.core.base.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.mock.testentity.Role;
import com.genius.core.base.mock.testentity.User;
/**
 * @author architect.bian
 *
 */
public class JsonUtilTest {

	@Test
	public void testToJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		assertEquals("{\"k\":\"v\"}", JsonUtil.toJson(map));
		String hi = "hi";
		assertEquals("\"hi\"", JsonUtil.toJson(hi));
		User user = new User();
		user.setName("hello");
		String json = JsonUtil.toJson(user);
		System.out.println(json);//{"id":"e1a7a04f4f174ce4a235147c79aee8ad","uid":"e1a7a04f4f174ce4a235147c79aee8ad","status":"enable","userid":null,"groupuid":null,"account":0.0,"nickname":null,"pwd":null,"name":"hello","gender":null,"realname":null,"birthday":null,"email":null,"intro":null,"thumb":null,"avatar":null,"original":null,"signed":null,"qq":null,"msn":null,"phone":null,"mobile":null,"salary":0,"resetpwd":null,"logincount":0,"msgcount":0,"errorlasttime":null,"errorcount":0,"errorip":null,"lastlogintime":null,"lastloginip":"","registerip":"","childuids":null,"friends":null,"genderName":"","isInit":false,"isEnable":true,"isDisable":false,"isStart":false,"isEnd":false,"isPayuserfee":false,"createtimeStr":"","createDate":"2014-01-13"}
		assertTrue(json.contains("\"name\":\"hello\""));
		map = null;
		assertEquals("null", JsonUtil.toJson(map));
		assertEquals("", JsonUtil.fromJson("", String.class));
		assertEquals(null, JsonUtil.fromJson("", Map.class));
		assertEquals(null, JsonUtil.fromJson("null", Map.class));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMapToJson() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", "uid111111111");
		data.put("int", 50);
		data.put("string", "hi, world");
		data.put("hi 'h5'", "hi, <h1 class='bold'>html5</h1>");
		List<Object> childList = new ArrayList<Object>();
		childList.add("a");
		childList.add("b");
		childList.add("c");
		childList.add("d");
		childList.add(123);
		childList.add(456);
		childList.add(789);
		childList.add("e");
		data.put("childList", childList);
		Map<String, Object> childMap = new HashMap<String, Object>();
		childMap.put("key1", "key1value");
		childMap.put("key2", 222);
		childMap.put("nowtime", DateUtil.NowTime());
		data.put("childMap", childMap);
		data.put("createtime", DateUtil.NowTime());
		System.out.println(data);
		System.out.println("JsonUtil.toJson:");
		String strJson = JsonUtil.toJson(data);
		System.out.println(strJson);
		Map<String, Object> data2 = (Map<String, Object>)JsonUtil.fromJson(strJson, Map.class);
		System.out.println("data2:");
		System.out.println(data2);
		String strJson2 = JsonUtil.toJson(data2);
		System.out.println("strJson2:");
		System.out.println(strJson2);
		assertEquals(strJson, strJson2);
	}
	
	@Test
	public void testFromJson() {
		String json = "{\"id\":\"d21aef6727004b49a8f1379314324e7f\",\"uid\":\"d21aef6727004b49a8f1379314324e7f\",\"status\":\"enable\",\"userid\":null,\"groupuid\":null,\"account\":0.0,\"nickname\":null,\"pwd\":null,\"name\":\"hello\",\"gender\":null,\"realname\":null,\"birthday\":null,\"email\":null,\"intro\":null,\"thumb\":null,\"avatar\":null,\"original\":null,\"signed\":null,\"qq\":null,\"msn\":null,\"phone\":null,\"mobile\":null,\"salary\":0,\"resetpwd\":null,\"logincount\":0,\"msgcount\":0,\"errorlasttime\":null,\"errorcount\":0,\"errorip\":null,\"lastlogintime\":null,\"lastloginip\":\"\",\"registerip\":\"\",\"childuids\":null,\"friends\":null,\"createtimeStr\":\"\",\"createDate\":\"2014-01-13\"}";
		User user = JsonUtil.fromJson(json, User.class);
		assertEquals("hello", user.getName());
	}

	/**
	 * 测试Map<String, User>类型ToJson与FromJson
	 * 
	 * @author: Architect.bian
	 * 2014-6-15 上午1:43:16
	 * 
	 * {"bill":{"uid":"df6a39f8fe904058a975d9a51dfe9b0f","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"groupuid":null,"id":null,"createtime":1402767878462,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"bill","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null},"kim":{"uid":"5493b78debc4461baecdccf5262f134f","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"groupuid":null,"id":null,"createtime":1402767878494,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"kim","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null}}
	 * {bill=com.genius.core.base___.testentity.User@b5ee3a8a, kim=com.genius.core.base___.testentity.User@888e705b}
	 * com.genius.core.base___.testentity.User@b5ee3a8a
	 * bill
	 * com.genius.core.base___.testentity.User@888e705b
	 * kim
	 * 
	 */
	@Test
	public void testMapUserTOFromJson() {
		Map<String, User> map = new HashMap<String, User>();
		User bill = new User("bill");
		map.put("bill", bill);
		User kim = new User("kim");
		map.put("kim", kim);
		String json = JsonUtil.toJson(map);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertTrue(json.contains("\"name\":\"kim\""));
		
		@SuppressWarnings("unchecked")
		Map<String, User> mapFromJson = JsonUtil.fromJson(json, Map.class);
		System.out.println(mapFromJson);
		for (User v : mapFromJson.values()) {
			System.out.println(v);
			System.out.println(v.getName());
		}
	}
	
	/**
	 * 测试List<User>类型ToJson与FromJson
	 * 
	 * @author: Architect.bian
	 * 2014-6-15 上午1:43:56
	 * 
	 * [{"uid":"6a2fe39e28f5474b9432916b7df67cf6","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"groupuid":null,"id":null,"createtime":1402767849962,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"bill","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null},{"uid":"812c920f1af74c88849d4c85b6071a41","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"groupuid":null,"id":null,"createtime":1402767849993,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"kim","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null}]
	 * [com.genius.core.base___.testentity.User@2a867dd6, com.genius.core.base___.testentity.User@33c351a4]
	 * com.genius.core.base___.testentity.User@2a867dd6
	 * bill
	 * com.genius.core.base___.testentity.User@33c351a4
	 * kim
	 * 
	 */
	@Test
	public void testListUserTOFromJson() {
		List<User> list = new ArrayList<>();
		User bill = new User("bill");
		list.add(bill);
		User kim = new User("kim");
		list.add(kim);
		String json = JsonUtil.toJson(list);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertTrue(json.contains("\"name\":\"kim\""));
		
		@SuppressWarnings("unchecked")
		List<User> listFromJson = JsonUtil.fromJson(json, List.class);
		System.out.println(listFromJson);
		for (User item : listFromJson) {
			System.out.println(item);
			System.out.println(item.getName());
		}
	}
	
	/**
	 * 测试List<User> User.children = list 进行tojson与fromjson
	 * 
	 * @author: Architect.bian
	 * 2014-6-15 上午3:46:54
	 * 
	 * [{"uid":"d1f12589fcb244938046d82757d761ce","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"children":[{"uid":"8e070f193fbd457ebbd79d2996307068","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"children":[],"groupuid":null,"id":null,"createtime":1402775196950,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"bill_1","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null},{"uid":"49e3f448db6640348b6e6618772a93cc","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"children":[],"groupuid":null,"id":null,"createtime":1402775196950,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"bill_2","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null}],"groupuid":null,"id":null,"createtime":1402775196916,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"bill","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null},{"uid":"ee60c5b26cbe4924931b2148cc8c6916","birthday":null,"phone":null,"CLASS":"com.genius.core.base___.testentity.User","errorlasttime":null,"msgcount":0,"updatetime":null,"userid":null,"children":[],"groupuid":null,"id":null,"createtime":1402775196950,"friends":null,"lastlogintime":null,"oid":0,"resetpwd":null,"name":"kim","gender":null,"tid":null,"lastloginip":"","salary":0,"qq":null,"msn":null,"registerip":"","nickname":null,"status":null,"signed":null,"avatar":null,"errorip":null,"intro":null,"pwd":null,"email":null,"childuids":null,"original":null,"account":0.0,"errorcount":0,"realname":null,"logincount":0,"clickcount":-1,"thumb":null,"mobile":null}]
	 * [com.genius.core.base___.testentity.User@e9209e28, com.genius.core.base___.testentity.User@11c34634]
	 * com.genius.core.base___.testentity.User@e9209e28
	 * bill_1
	 * bill_2
	 * bill
	 * com.genius.core.base___.testentity.User@11c34634
	 * kim
	 * 
	 */
	@Test
	public void testListUserChildrenReadWriteJson() {
		List<User> list = new ArrayList<>();
		User bill = new User("bill");
		User bill_1 = new User("bill_1");
		User bill_2 = new User("bill_2");
		bill.getChildren().add(bill_1);
		bill.getChildren().add(bill_2);
		list.add(bill);
		User kim = new User("kim");
		list.add(kim);
		String json = JsonUtil.toJson(list);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertTrue(json.contains("\"name\":\"kim\""));
		
		@SuppressWarnings("unchecked")
		List<User> listFromJson = JsonUtil.fromJson(json, List.class);
		System.out.println("listFromJson:" + listFromJson);
		for (User item : listFromJson) {
			System.out.println(item);
			for (User u : item.getChildren()) {
				System.out.println(u.getName());
			}
			System.out.println(item.getName());
		}
		assertTrue(listFromJson.get(0).getChildren().size() == 2);
	}
	
	@Test
	public void testAllStrategy() {
		User user = new User("bill");
		String json = JsonUtil.toJson(user);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		User userFromJson = JsonUtil.fromJson(json, User.class);
		assertEquals(user.getName(), userFromJson.getName());
		json = JsonUtil.toJson(user, "forsave");
		System.out.println(json);
	}
	
	@Test
	public void testAllStrategyJsonOnMethod() {
		User user = new User("bill");
		String json = JsonUtil.toJson(user);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertTrue(json.contains("\"updatetime\":"));
		assertTrue(json.contains("\"statusName\":\"\""));
		assertTrue(json.contains("\"CLASS\":\"com.genius.core.base.mock.testentity.User\""));
		User userFromJson = JsonUtil.fromJson(json, User.class);
		assertEquals(user.getName(), userFromJson.getName());
		
		json = JsonUtil.toJson(user, "forsave");
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertFalse(json.contains("\"clickcount\":"));
		assertTrue(json.contains("\"statusName\":\"\""));
		assertFalse(json.contains("\"CLASS\":\"com.genius.core.base.mock.testentity.User\""));
	}
	
	@Test
	public void testDoubleStrategy() {
		Role role = new Role();
		role.getUid();
		role.setAdminid("adminid");
		role.setAuthority("Author");
		role.setAuthorityname("作者");
		String json = JsonUtil.toJson(role, Json.keyValueStrategy);
		System.out.println(json);//{"uid":"629bea3d5c2c494092d49230822eefb9","name":"作者"}
		assertTrue(json.contains("uid"));
		assertTrue(json.contains("name"));
		assertFalse(json.contains("statusName"));
		assertFalse(json.contains("createtimeStr"));
		json = JsonUtil.toJson(role, Json.webStrategy);
		System.out.println(json);//{"uid":"629bea3d5c2c494092d49230822eefb9","authority":"Author","desc":null,"updatetimeStr":null,"statusName":"正常","createtimeStr":"2014-07-21 17:48:51","name":"作者","adminid":"adminid"}
		assertTrue(json.contains("uid"));
		assertTrue(json.contains("name"));
		assertTrue(json.contains("statusName"));
		assertTrue(json.contains("createtimeStr"));
	}
}
