package com.genius.core.base.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.mock.testentity.Book;
import com.genius.core.base.mock.testentity.Msg;
import com.genius.core.base.mock.testentity.User;
import com.genius.core.base.mock.testenums.EGender;
import com.genius.core.base.mock.testenums.EStatus;

/**
 * @author Architect.bian
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class MapsUtilTest {
	
	private User testUser;
	
	@Before
	public void before(){
		String name = "zhangsan";
		int account = 100;
		String userid = "userid000000";
		testUser = new User(name);
		testUser.setAccount(account);
		testUser.setUserid(userid);
		testUser.setGender(EGender.gentleman);
		testUser.setBirthday(new LocalDate());
		testUser.setSalary(968777);
		testUser.setStatus(EStatus.start);
		testUser.setLastlogintime(new DateTime());
		testUser.setTags(new String[]{"baby", "hi", "world", "obama"});
		testUser.setBooks(getBooks());
		testUser.setChilduids(getListStr());
		testUser.setChildren(getChildren());
	}
	
	@Test
	public void testToMap() {
		String name = "zhangsan";
		int account = 100;
		String userid = "userid000000";
		User user = new User(name);
		user.setAccount(account);
		user.setUserid(userid);
		user.setGender(EGender.gentleman);
		user.setBirthday(new LocalDate());
		user.setSalary(968777);
		user.setStatus(EStatus.start);
		user.setLastlogintime(new DateTime());
		user.setTags(new String[]{"baby", "hi", "world", "obama"});
		user.setBooks(getBooks());
		user.setChilduids(getListStr());
		user.setChildren(getChildren());
		Map<String, Object> result = MapsUtil.toMap(user);
		System.out.println(result);
		assertNull(result.get("readBooks"));
		System.out.println("Json:");
		assertEquals(name, result.get("name"));
		System.out.println(JsonUtil.toJson(result));
	}
	
	@Test
	public void testToMapFromMapObj(){
		Map<String, Object> map = MapsUtil.toMap(getBooks());
		System.out.println(map);
		System.out.println(map.values());
		assertNotNull(map);
		assertTrue(map.size() > 0);
	}
	
	@Test
	@Ignore
	public void testToMapFromListObj(){
		Map<String, Object> map = MapsUtil.toMap(getBooks().values());
		System.out.println(map);
		assertNull(map);
	}
	
	@Test
	public void testIncludeType() {
		Map<String, Object> result = MapsUtil.toMap(testUser);
		assertNull(result.get("CLASS"));
		result = MapsUtil.toMap(testUser, Maps.cacheStrategy);
		assertEquals(testUser.getClass().getName(), result.get("CLASS"));
	}
	
	@Test
	public void testAnnotationOnMethod() {
		Map<String, Object> result = MapsUtil.toMap(testUser, Maps.cacheStrategy);
		System.out.println(result);
		assertEquals(testUser.getClass().getName(), result.get("CLASS"));
	}
	
	@Test
	public void testFromMap() {
		Map<String, Object> map = new HashMap<>();
		String uid = "uid000000";
		String name = "Obama";
		String groupuid = "groupuid";
		double account = 56.3;
		String nickname = "snapi";
		EGender gender = EGender.lady;
		String realname = "realname";
		LocalDate birthday = new LocalDate();
		int salary = 688875;
		EStatus status = EStatus.deleted;
		DateTime lastlogintime = new DateTime();
		String[] tags = new String[]{"a", "b", "c"};
		Map<String, Book> books = getBooks();
		List<String> friends = getListStr();
		List<User> children = getChildren();
		map.put("uid", uid);
		map.put("name", name);
		map.put("groupuid", groupuid);
		map.put("account", account);
		map.put("nickname", nickname);
		map.put("gender", gender);
		map.put("realname", realname);
		map.put("birthday", birthday);
		map.put("salary", salary);
		map.put("status", status);
		map.put("lastlogintime", lastlogintime);
		map.put("tags", tags);
		map.put("books", books);
		map.put("friends", getListStr());
		map.put("children", children);
		map.put("readBooks", MapsUtil.toMap(getBooks()).values());
		User user = MapsUtil.fromMap(map, User.class);
		assertEquals(name, user.getName());
		assertEquals(uid, user.getUid());
		assertEquals(groupuid, user.getGroupuid());
		assertTrue(account == user.getAccount());
		assertEquals(nickname, user.getNickname());
		assertEquals(gender, user.getGender());
		assertEquals(realname, user.getRealname());
		assertEquals(birthday, user.getBirthday());
		assertEquals(salary, user.getSalary());
		assertEquals(status, user.getStatus());
		assertEquals(lastlogintime.getMillis(), user.getLastlogintime().getMillis());
		assertArrayEquals(tags, user.getTags());
		assertEquals(books, user.getBooks());
		assertArrayEquals(friends.toArray(), user.getFriends().toArray());
		assertArrayEquals(children.toArray(), user.getChildren().toArray());
		assertNull(user.getReadBooks());
	}
	
	@Test
	public void testIsRaw() {
		String name = "张三";
		String userid = "userid000000";
		User user = new User(name);
		user.setUserid(userid);
		Msg msg = new Msg();
		msg.setFrom(user);
		msg.setCreatetime(new DateTime());
		msg.setUpdatetime(new DateTime());
		Map<String, Object> map = MapsUtil.toMap(msg);
		System.out.println(map);
		System.out.println(map.get("from"));
		assertEquals(map.get("from").getClass(), User.class);
		assertEquals(map.get("createtime").getClass(), DateTime.class);
		assertEquals(map.get("updatetime").getClass(), Long.class);
		Msg msg2 = MapsUtil.fromMap(map, Msg.class);
		assertEquals(msg.getCreatetime(), msg2.getCreatetime());
		assertEquals(msg.getFrom().getUserid(), msg2.getFrom().getUserid());
	}

	private List<User> getChildren() {
		List<User> users = new ArrayList<>();
		users.add(new User("peter"));
		users.add(new User("jack"));
		return users;
	}

	private List<String> getListStr() {
		List<String> list = new ArrayList<>();
		list.add("uid00000000000");
		list.add("uid111111111111111");
		list.add("uid22222222222222");
		list.add("uid3333333333333333");
		list.add("uid4444444444444");
		list.add("uid555555555555555");
		return list;
	}

	private Map<String, Book> getBooks() {
		Map<String, Book> map = new HashMap<String, Book>();
		map.put("first", new Book("id01", "first book", "obama", "good book", new DateTime()));
		map.put("second", new Book("id02", "second book", "hi", "good book", new DateTime()));
		map.put("third", new Book("id03", "third book", "world", "good book", new DateTime()));
		map.put("fourth", new Book("id04", "Go book", "Golang", "go book", new DateTime()));
		return map;
	}

 }
