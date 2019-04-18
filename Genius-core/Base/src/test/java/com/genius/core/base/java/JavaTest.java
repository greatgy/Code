package com.genius.core.base.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.mock.testentity.User;
import com.genius.core.base.mock.testenums.EGender;
import com.google.common.collect.Lists;

/**
 * Java的基本语法测试
 *
 * @author architect.bian
 * 2014-6-24 上午11:34:53
 */
public class JavaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSplit() {
		String str = "aaa\nbbbb";
		assertEquals(2, str.split("\n").length);
		String str2 = "a   b";
		assertEquals(4, str2.split(" ").length);
		String str3 = "a";
		assertEquals(1, str3.split(" ").length);
	}

	@Test
	public void testTrim() {
		String str = "\r  a  \n";
		assertEquals("a", str.trim());
	}

	@Test
	public void testSplitConvert() {
		String strweight = "1 3 5";
		String[] strs = StringUtils.split(strweight);
		int[] weights = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			weights[i] = Integer.parseInt(strs[i]);
		}
		assertArrayEquals(weights, new int[] { 1, 3, 5 });
	}

	@Test
	public void testStringFormat() {
		String str = String.format("/imgs/webimgs/pic%1d.gif", 1);
		assertEquals("/imgs/webimgs/pic1.gif", str);
		String str2 = String.format("{\"id\": \"%1s\",\"text\": \"%2s | %3s\"}", "uid", "name1", "name2");
		assertEquals("{\"id\": \"uid\",\"text\": \"name1 | name2\"}", str2);
		String str3 = String.format("%d_%d_%d_%d", 1, 2, 3, 4);
		assertEquals("1_2_3_4", str3);
		String str4 = String.format("%s_%s_%s_%s", 0.1, 0.12, 0.123, 0.1234);
		assertEquals("0.1_0.12_0.123_0.1234", str4);
		double d = 12.0;
		System.out.println(d);
		assertEquals("12.0", String.valueOf(d));
		assertEquals("12", new DecimalFormat("#").format(d));
		String str5 = String.format("%s=%%s", 1);
		assertEquals("1=1", String.format(str5, 1));
	}
	
	@Test
	public void testString() {
		String str = "test";
		assertEquals("testnull", str + null);
		assertEquals("nulltest", null + str);
		String str0 = "hiworld";
		String str1 = "hiworld";
		String str2 = new String("hiworld");
		assertTrue(str0 == str1);
		assertTrue(str0 != str2);
		assertTrue(str0 == str2.intern());
		assertTrue(str2 != str2.intern());
		String str3 = new StringBuilder("hi").append("world").toString();
		String str4 = new StringBuilder("xxxxxuid").append("world").toString();
		String str5= new StringBuilder("你好").append("world").toString();
		assertTrue(str3.intern() != str3);
		assertTrue(str4.intern() == str4);
		assertTrue(str5.intern() == str5);
	}

	@Test
	public void testReplace() {
		String str = "aba";
		assertEquals("bbb", str.replace("a", "b"));
	}

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testForeachNull() {
		List<String> list = null;
		for (String str : list) {
			System.out.println(str);
		}
	}

	@Test
	public void testArrayList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		assertEquals(new Integer(2), list.get(2));
		list.remove(2);
		list.add(0, -1);
		assertEquals(new Integer(1), list.get(2));
	}

	@Test
	public void testListRemove() {
		List<String> list = new ArrayList<>();
		list.add(null);
		list.add("");
		list.add("ssss");
		list.add(null);
		if (list.contains(null)) {
			Collection<Object> c = new ArrayList<Object>();
			c.add(null);
			c.add("");
			list.removeAll(c);
		}
		assertTrue(list.size() == 1);
	}

	@Test(expected = NumberFormatException.class)
	public void testStringToDouble() {
		Double.valueOf("1,111");
	}

	@Test
	public void testDateTime() {
		DateTime d = new DateTime("2006-01-26T13:30:00-06:00");
		System.out.println(d);
		DateTime datetime = ISODateTimeFormat.dateTimeParser().parseDateTime("2012-11-20T12:35:57");
		assertNotNull(datetime);
	}

	@Test
	public void testCompareDateTime() {
		DateTime dateTime = new DateTime();
		dateTime = dateTime.minusSeconds(10);
		// System.out.println(dateTime);
		// System.out.println(dateTime.minusSeconds(10));
		// System.out.println(dateTime);
		assertTrue(dateTime.plusSeconds(9).isBeforeNow());
		dateTime = dateTime.minusMinutes(1);
		assertTrue(dateTime.plusMinutes(1).isBeforeNow());
		dateTime = dateTime.minusMinutes(10);
		assertTrue(dateTime.plusMinutes(10).isBeforeNow());
		dateTime = dateTime.minusMinutes(30);
		assertTrue(dateTime.plusMinutes(30).isBeforeNow());
		dateTime = dateTime.minusHours(1);
		assertTrue(dateTime.plusHours(1).isBeforeNow());
		dateTime = dateTime.minusHours(2);
		assertTrue(dateTime.plusHours(2).isBeforeNow());
		dateTime = dateTime.minusHours(3);
		assertTrue(dateTime.plusHours(3).isBeforeNow());
		dateTime = dateTime.minusDays(1);
		assertTrue(dateTime.plusDays(1).isBeforeNow());
		dateTime = dateTime.minusWeeks(1);
		assertTrue(dateTime.plusWeeks(1).isBeforeNow());
	}

	@Test
	public void testRegex() {
		Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
		while (m.find())
			System.out.println(m.group());
		int i = 0;
		while (m.find(i)) {
			System.out.print(m.group() + " ");
			i++;
		}
		System.out.println();
		Matcher murl = Pattern.compile("/myhome/(.{32})").matcher("http://xxx.projs.com:8080/myhome/86883b04795f38d0a70b1f9004c1f999/sdkjfikd");
		if (murl.find()) {
			System.out.println(murl.group());
			System.out.println(murl.group(1));
		}
		System.out.println(Pattern.matches(".*/myhome/(.{32})\\S*", "http://xxx.projs.com:8080/myhome/86883b04795f38d0a70b1f9004c1f999/klsjdfke"));
	}

	@Test
	public void testInteger() {
		System.out.println(Integer.MAX_VALUE);
		int i = Integer.valueOf("1111111111111111111111111111111", 2);
		System.out.println(i);
		i = Integer.valueOf("-1111111111111111111111111111111", 2);
		System.out.println(i);
		i = Integer.valueOf("0000000000000000000000000000011", 2);
		System.out.println(i);
		i = Integer.valueOf("11", 2);
		System.out.println(i);
		i = Integer.valueOf("11", 10);
		System.out.println(i);
		i = Integer.valueOf("11", 16);
		System.out.println(i);
		i = 18;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toOctalString(i));
		System.out.println(Integer.toHexString(i));

		int type = Integer.valueOf("0100000000000000000000000000000", 2);
		int flag = Integer.valueOf("1100000000000000000000000000000", 2);
		int flag2 = Integer.valueOf("1000000000000000000000000000000", 2);
		System.out.println("type:" + type);
		System.out.println("flag:" + flag);
		assertEquals(type, flag & type);
		System.out.println(flag & type);
		assertTrue(type != (flag2 & type));
		System.out.println(flag2 & type);
		flag2 = type | flag2;
		assertEquals(type, flag2 & type);
		System.out.println(flag2 & type);
	}

	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "123");
		assertEquals(map.get(null), "123");
		map.put(null, "456");
		assertEquals(map.get(null), "456");
	}

	@Test
	public void testCharacter() {
		System.out.println(Character.getNumericValue('你'));// -1
		System.out.println(Character.getNumericValue('a'));// 10
		// System.out.println(Integer.valueOf("a"));//java.lang.NumberFormatException
		System.out.println((int) 'a');// 97
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testLinkedHashMap() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("a", "avalue");
		map.put("c", "cvalue");
		map.put("b", "bvalue");
		map.put("1", "1value");
		map.put("0", "0value");
		Object obj = map;
		map = (Map<String, Object>) obj;
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			System.out.println(key + "=" + map.get(key));
		}
		Collection<Object> values = map.values();
		for (Object v : values) {
			System.out.println(v);
		}
	}

	@Test
	public void testSubstr() {
		String oldname = "aaa.txt";
		String ext = oldname.lastIndexOf(BaseStrDict.dot) != -1 ? oldname.substring(oldname.lastIndexOf(BaseStrDict.dot) + 1).toLowerCase() : "";
		System.out.println(ext);
	}

	@Test
	public void testSet() {
		Set<String> set = new HashSet<String>();
		set.add("");
		set.add("a");
		System.out.println(set.toString());
	}

	@Test
	public void testBeep() throws InterruptedException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		kit.beep();
	}
	
	/**
	 * 测试数据读取       +  加密算法:
	 */
	@Test
	public void initpaypwd() {
		System.out.println(DigestUtils.md5Hex("uid00000000000000000000000000000" + "123456"));
		System.out.println(DigestUtils.md5Hex("123456"));
	}
	@Test
	public void testArray(){
		String[] strs = {"1", "2", "3"};
		assertEquals("{1,2,3}", ArrayUtils.toString(strs));
//		System.out.println(Arrays.toString(strs));
		assertEquals("[1, 2, 3]", Arrays.toString(strs));
		assertEquals("class [Ljava.lang.String;", strs.getClass().toString());
		assertTrue(strs.getClass().isArray());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException() {
		String[] strs = {"0"};
		System.out.println(strs[0]);
		System.out.println(strs[1]);
	}
	
	@Test
	public void testBitSet() {
		String names[] = { "Java", "Source", "and", "Support", "hi"};
	    BitSet bits = new BitSet();
	    for (int i = 0, n = names.length; i < n; i++) {
	      if ((names[i].length() % 2) == 0) {
	    	  bits.set(i);
	      }
	    }
	    System.out.println(bits);
	    System.out.println("Size : " + bits.size());
	    System.out.println("Length: " + bits.length());
	    for (int i = 0, n = names.length; i < n; i++) {
	      if (!bits.get(i)) {
	        System.out.println(names[i] + " is odd");
	      }
	    }
	    BitSet bites = new BitSet();
	    bites.set(0);
	    bites.set(1);
	    bites.set(2);
	    bites.set(3);
	    bites.andNot(bits);
	    System.out.println(bites);
	}

	/**
	 * 类注解
	 * */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface MyAnnotationClass {
		public String msg();
	}

	/**
	 * 方法注解
	 * */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface MyAnnotationMethod {
		public String common();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface MyAnnotationField {
		boolean request();
	}

	@MyAnnotationClass(msg = "这个是一个类注解")
	public class MyAnnotationDemo {

		public MyAnnotationDemo() {}

		public MyAnnotationDemo(String hello) {
			this.hello = hello;
		}

		@MyAnnotationMethod(common = "这个是一个方法注解")
		public void method() {
			System.out.println(hello);
		}

		@MyAnnotationField(request = true)
		private String hello;
	}
	
	@Test
	public void testAnnotation() throws IllegalArgumentException, IllegalAccessException{
		MyAnnotationDemo demo = new MyAnnotationDemo("hello rollen");
		MyAnnotationClass annotationClass = demo.getClass().getAnnotation(MyAnnotationClass.class);
		System.out.println(annotationClass.msg());

		Method method = null;
		try {
			method = demo.getClass().getMethod("method", new Class[0]);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		MyAnnotationMethod annotationMethod = method.getAnnotation(MyAnnotationMethod.class);
		System.out.println(annotationMethod.common());

		Field field = null;
		try {
			field = demo.getClass().getDeclaredField("hello");
			System.out.println(field.getName());
			if (field.isAnnotationPresent(MyAnnotationField.class)) {
				field.setAccessible(true);
//			ReflectionUtils.makeAccessible(field);
				System.out.println(field.get(demo));
				field.set(demo, "hello world");
				System.out.println(field.get(demo));
				field.setAccessible(false);
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		MyAnnotationField annotationField = field.getAnnotation(MyAnnotationField.class);
		System.out.println(annotationField.request());
	}
	
	@Test
	public void testTypeNewInstance() {
		try {
			Object demo = User.class.newInstance();
			System.out.println(demo.getClass());
			//class com.geniusvideo.test.entity.User
			System.out.println(demo.getClass().getName());
			//com.geniusvideo.test.entity.User
			System.out.println(demo.getClass().getCanonicalName());
			//com.geniusvideo.test.entity.User
			System.out.println(demo.getClass().isLocalClass());
			//false
			System.out.println(demo.getClass().getPackage().getName());
			//com.geniusvideo.test.entity
			System.out.println();
			System.out.println(demo.getClass().getSuperclass());
			//class com.geniusvideo.web.base.entity.BaseEntity
			System.out.println(demo.getClass().getSuperclass().getName());
			//com.geniusvideo.web.base.entity.BaseEntity
			System.out.println(demo.getClass().getSuperclass().getDeclaredFields());
			//[Ljava.lang.reflect.Field;@a0a36
			System.out.println(demo.getClass().getSuperclass().getSuperclass());
			//class com.grandgenius.web.base.entity.BaseEntity
			System.out.println(demo.getClass().getSuperclass().getSuperclass().getName());
			//com.grandgenius.web.base.entity.BaseEntity
			//System.out.println(demo.getClass().getSuperclass().getSuperclass().getSuperclass().getName());
			//java.lang.Object
			System.out.println(Object.class.getName());
			//java.lang.Object
			System.out.println(demo.getClass().getSuperclass().getSuperclass().getSuperclass() == Object.class);
			//true
			System.out.println("-----------------------------------------------------");
			Field field = null;
			try {
				field = demo.getClass().getDeclaredField("serialVersionUID");
				System.out.println(field.getModifiers());
				//26
				System.out.println(Modifier.isFinal(field.getModifiers()));
				//true
				field = demo.getClass().getSuperclass().getDeclaredField("uid");
//				field = demo.getClass().getSuperclass().getSuperclass().getDeclaredField("uid");
				System.out.println(field.getName());
				//uid
				field.setAccessible(true);
				System.out.println(field.get(demo));
				//null
				field.set(demo, "great-looooooooooooong-uid");
				System.out.println(field.get(demo));
				//great-looooooooooooong-uid
				field = demo.getClass().getDeclaredField("userid");
				System.out.println(field.getName());
				//userid
				field = demo.getClass().getDeclaredField("gender");
				System.out.println(field.get(demo));
				//null
				System.out.println(field.getDeclaringClass());
				//class com.geniusvideo.test.entity.User
				System.out.println(field.getDeclaringClass());
				//class com.geniusvideo.test.entity.User
				System.out.println(field.getGenericType());
				//class com.grandgenius.web.base.entity.EGender
				System.out.println("isPrimitive():" + String.class.isPrimitive());
				System.out.println(field.getGenericType().getClass().isPrimitive());
				//false
				System.out.println(field.toGenericString());
				//public com.grandgenius.web.base.entity.EGender com.geniusvideo.test.entity.User.gender
				System.out.println(field.getModifiers());
				//1
				System.out.println(field.isEnumConstant());
				//false
				System.out.println(field.isSynthetic());
				//false
				System.out.println(field.getType());
				//class com.grandgenius.web.base.entity.EGender
				System.out.println(field.getClass());
				//class java.lang.reflect.Field
				System.out.println(EGender.class);
				//class com.grandgenius.web.base.entity.EGender
				System.out.println(EGender.class.getName());
				//com.grandgenius.web.base.entity.EGender
				System.out.println(field.getType().isEnum());
				//true
				if (field.getType().isEnum()) {
					Method method = null;
					method = field.getType().getMethod("get", int.class);
					Object e = method.invoke(field.getType(), 0);
					System.out.println(e);
					//0
					System.out.println(e.getClass());
					//class com.grandgenius.web.base.entity.EGender
					System.out.println(e.toString());
					//0
					method = field.getType().getMethod("name");
					//Lady
					System.out.println(method.invoke(e));
					field.set(demo, e);
					System.out.println(field.get(demo));
					//0
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMapListType() {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		Map<String, User> mapUser = new HashMap<String, User>();
		System.out.println(mapObj.getClass());
		System.out.println(mapUser.getClass());
		System.out.println(mapObj.getClass().isArray());
		System.out.println(Arrays.toString(mapObj.getClass().getInterfaces()));
		System.out.println(Arrays.toString(mapUser.getClass().getInterfaces()));
		assertTrue(Lists.newArrayList(mapObj.getClass().getInterfaces()).contains(Map.class));
		assertTrue(Lists.newArrayList(mapUser.getClass().getInterfaces()).contains(Map.class));
		System.out.println(Map.class);
		
		List<Object> listObj = new ArrayList<>();
		List<User> listUser = new ArrayList<>();
		System.out.println(listObj.getClass());
		System.out.println(listUser.getClass());
		System.out.println(listObj.getClass().isArray());
		System.out.println(Arrays.toString(listObj.getClass().getInterfaces()));
		System.out.println(Arrays.toString(listUser.getClass().getInterfaces()));
		assertTrue(Lists.newArrayList(listObj.getClass().getInterfaces()).contains(List.class));
		assertTrue(Lists.newArrayList(listUser.getClass().getInterfaces()).contains(List.class));
		System.out.println(Map.class);
	}
	
	@Test
	public void testClassForName() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(new JavaTest().getClass().getName());//com.mygenius.___.init.JavaTest
		System.out.println(this.getClass().getName());//com.mygenius.___.init.JavaTest
		Object obj = Class.forName(this.getClass().getName()).newInstance();
		System.out.println(obj);//com.mygenius.___.init.JavaTest@1c5cd7
	}
	
	/**
	 * 在一次 Java 应用的执行中，对于同一个对象，hashCode 方法必须始终返回相同的整数，但这整数不反映对象是否被修改（equals 比较）的信息。同一个应用的不同执行，该整数不必保持一致。
	 * 
	 */
	@Test
	public void testHashCode() {
		assertEquals("".hashCode(), 0);
		assertEquals("0".hashCode(), 48);
		assertEquals("00".hashCode(), 1536);
		assertEquals("000".hashCode(), 47664);
		assertEquals("1".hashCode(), 49);
		assertEquals("2".hashCode(), 50);
		assertEquals("10".hashCode(), 1567);
		assertEquals("100".hashCode(), 48625);
		assertEquals("a".hashCode(), 97);
		assertEquals("A".hashCode(), 65);
		assertEquals("b".hashCode(), 98);
		assertEquals("sdfghjkl;weyuiop234790-".hashCode(), 64599096);
		assertEquals("sdfghjkl;weyuiop234790-sdfghjkl;weyuiop234790-".hashCode(), -190004992);
		assertEquals("sdfghjkl;wey443uiop12323432df790-4sdfgs h213jkl12weyui3f 43as'[df\\gre567z5w][231p234790-".hashCode(), -680581046);
		assertEquals("hashCode是jdk根据对象的地址或者字符串或者数字算出来的int类型的数值 详细了解请 参考 [1] public int hashCode()返回该对象的哈希码值。支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能如果根据equals(java.lang.Object)方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。".hashCode(), -1934396428);
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		assertEquals("Aa".hashCode(), "BB".hashCode());
	}
	
	@Test
	public void testUri() throws URISyntaxException {
		URI uri = new URI("mongodb://root:123456@localhost:27017/test?arg=1");
		assertEquals("mongodb", uri.getScheme());
		assertEquals("root:123456", uri.getRawUserInfo());
		assertEquals("localhost", uri.getHost());
		assertEquals(27017, uri.getPort());
//		assertEquals("", uri.getAuthority());
		assertEquals("/test", uri.getPath());
		assertEquals("arg=1", uri.getQuery());
		System.out.println(uri.getAuthority());
		System.out.println(uri.getFragment());
		System.out.println(uri.getRawSchemeSpecificPart());
		System.out.println(uri.getSchemeSpecificPart());
		System.out.println(uri.getUserInfo());
	}

	private static int stack(int n) {
		if (n == 1)
			return 1;
		return stack(n - 1) + 1;
	}
	
	@Test(expected=StackOverflowError.class)
	public void testStack() {
		assertTrue(stack(20) == 20);
		stack(65536);
	}

	@Test
	public void testArrayListandLinkedList() {
		long start = System.currentTimeMillis();
		ArrayList<Object> list = new ArrayList<Object>();
		Object obj = new Object();
		for (int i = 0; i < 5000000; i++) {
			list.add(obj);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		LinkedList<Object> list1 = new LinkedList<Object>();
		Object obj1 = new Object();
		for (int i = 0; i < 5000000; i++) {
			list1.add(obj1);
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		Object obj2 = new Object();
		for (int i = 0; i < 1000; i++) {
			list.add(0, obj2);
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			list1.add(obj1);
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		list.remove(0);
		end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		list1.remove(250000);
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	@Test
	@Ignore
	public void testWeakHashMap() {
		String a = new String("a");
		String b = new String("b");
		Map<String, String> weakmap = new WeakHashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put(a, "aaa");
		map.put(b, "bbb");
		weakmap.put(a, "aaa");
		weakmap.put(b, "bbb");
		map.remove(a);
		a = null;
		b = null;
		assertEquals(weakmap.size(), 2);
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc();
		System.gc(); //执行多次，以避免没有完全垃圾回收
		assertEquals(weakmap.size(), 1);
		Iterator<?> i = map.entrySet().iterator();
		while (i.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> en = (Map.Entry<String, String>) i.next();
			System.out.println("map:" + en.getKey() + ":" + en.getValue());
		}
		Iterator<?> j = weakmap.entrySet().iterator();
		while (j.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> en = (Map.Entry<String, String>) j.next();
			System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());
		}
	}

	/**
	 * 配置vm运行参数-verbose:gc查看垃圾回收情况
	 * java -XX:+PrintFlagsFinal -version 输出默认参数
	 * uintx MaxHeapSize  := 268435456   {product}
	 * 所以i为250+左右抛异常
	 * 使用了标志-XX:+HeapDumpOnOutOfMemoryError，JVM会在遇到OutOfMemoryError时拍摄一个“堆转储快照”，并将其保存在一个文件中。
	 */
	@Test(expected=OutOfMemoryError.class)
	public void testOutMemory() {
		System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
		List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
//		System.gc();
		for (int i = 0; i < 1000; i++) {
			WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
			d.put(new byte[1000][1000], new byte[1000][1000]);
			maps.add(d);
//			System.gc();
			System.err.println(i);
		}
		System.gc();
	}
	
	/**
	 * 部分电脑执行慢，设置为ignore
	 */
	@Test
	@Ignore
	public void testOutMemory2() {
		System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
		List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
		System.gc();
		for (int i = 0; i < 1000; i++) {
			WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
			d.put(new byte[1000][1000], new byte[1000][1000]);
			maps.add(d);
			System.gc();
			System.err.println(i);
			for (int j = 0; j < i; j++) {
				System.err.println(j + " size" + maps.get(j).size()); // Key 在 GC 的时候被清除，Value 在 Key 清除后访问 WeakHashMap 被清除
			}
		}
	}
	
	/**
	 * -XX:+PrintGCDetails 查看gc日志
	 */
	@Test
	@Ignore
	public void testByteBufferDirect() {
		for (int i = 0; i < 1024; i++) {
			ByteBuffer.allocateDirect(128 * 1024 * 1024);
			System.out.println(i);
//			System.gc();
		}
	}

	@Test
	public void testComparator() {
		TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 - o2 > 0) {
					return 1;
				} else if (o1 - o2 < 0) {
					return -1;
				}
				return 0;
			}
		});
		String actual = "3b";
		treeMap.put(5, "5a");
		treeMap.put(3, "3a");
		treeMap.put(3, actual);
		treeMap.put(7, "7a");
		System.out.println(treeMap.firstEntry().getValue());
		assertEquals(treeMap.firstEntry().getValue(), actual);
	}
	
	@Test
	@Ignore
	public void testReadFileFromJar() {
		System.out.println(this.getClass().getResourceAsStream("xxx"));
	}
	
	@Test
	@Ignore
	public void testListFileFromJar() {
		URL resource = this.getClass().getResource("xxx");
    	String path = resource.getPath();
    	if (path.contains("!")) {
			path = path.substring(0, path.indexOf("!")).replaceAll("\\\\", "/").replace("file:/", "");
		} else {
			path = path.replaceAll("\\\\", "/").replace("file:/", "");
		}
    	if (path.endsWith(".jar")) {
    		try {
    			JarFile jar = new JarFile(path);
    			Enumeration<JarEntry> entries = jar.entries();
    			while(entries.hasMoreElements()) {
    				String name = "/" + entries.nextElement().getName();
    				if (name.startsWith("xxx") && name.endsWith(".jpg")) {
    					System.out.println(name);
    				}
    			}
    			jar.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}
}