package com.supergenius.___.init;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
import org.junit.Test;

import com.genius.core.base.utils.NumUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.supergenius.server.common.constants.ViewKeyDict;

/**
 * @author Architect.bian
 * 
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

	@Test
	public void testEnumToString() {
		EStatus[] arr = new EStatus[3];
		arr[0] = EStatus.disable;
		arr[1] = EStatus.enable;
		arr[2] = EStatus.init;
		StringBuffer sb = new StringBuffer();
		for (EStatus status : arr) {
			sb.append(status.toString());
			sb.append(",");
		}
		System.out.println(StrUtil.trim(sb.toString(), ","));
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
	public void testRandom() {
		Random random = new Random();
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt(9));
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(random.nextInt());
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
		System.out.println(NumUtil.random(3));
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
		String ext = oldname.lastIndexOf(ViewKeyDict.dot) != -1 ? oldname.substring(oldname.lastIndexOf(ViewKeyDict.dot) + 1).toLowerCase() : "";
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
		System.out.println(DigestUtils.md5Hex("93483b04795f48d0a70b1f9004c1f98f" + "123456"));
		
	}
	@Test
	public void testArray(){
		String[] strs = {"1", "2", "3"};
		assertEquals("{1,2,3}", ArrayUtils.toString(strs));
//		System.out.println(Arrays.toString(strs));
		assertEquals("[1, 2, 3]", Arrays.toString(strs));
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
}
