package com.genius.core.search.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.MapBuilder;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.core.search.mock.TestConst;
import com.genius.core.search.mock.TestMapperDict;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class SolrSearchEngineTest {

//	private static String path = StrUtil.trim(ClassLoader.getSystemResource("files").getFile(), "/");
	
	@Autowired
	SearchEngine engine;
	
	private String strID = GlobalUtil.getUUID();
	
	@Before
	public void setUp() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(TestMapperDict.uid, strID);
		map.put(TestMapperDict.name, TestConst.name);
		boolean result = engine.add(map);
		assertTrue(result);
	}

	@org.junit.After
//	@Test
	public void clear() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("*", "*");
//		map.put(MapperDict.route, "shard1");
		boolean result = engine.delete(map);
		assertTrue(result);
	}
	
	@Test
	public void testSearch() {
		Map<String, Object> map = new HashMap<>();
//		map.put("*", "*");
		map.put(TestMapperDict.name, TestConst.name);
		map.put(MapperSearchDict.start, 0);
		map.put(MapperSearchDict.rows, 2);
		map.put(MapperSearchDict.sort, TestMapperDict.uid);
		List<?> result = engine.search(map);
		System.out.println(result);
		assertTrue(result.size() > 0);
		SolrDocument doc = (SolrDocument)result.get(0);;
		assertTrue(StrUtil.isNotEmpty(doc.get(TestMapperDict.uid)));
	}
	
	@Test
	public void testAdd() throws Exception {
		clear();
		int count = 9;
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(TestMapperDict.uid, GlobalUtil.getUUID());
			map.put(TestMapperDict.name, TestConst.name);
			boolean result = engine.add(map);
			System.out.println("testAdd: " + map.get(TestMapperDict.uid));
			assertTrue(result);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("*", "*");
		map.put(MapperSearchDict.rows, count);
		List<?> result = engine.search(map);
		assertEquals(count, result.size());
	}
	
	@Test
	public void testAddBatch() throws Exception {
		clear();
		int count = 90;
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(TestMapperDict.uid, GlobalUtil.getUUID());
			map.put(TestMapperDict.name, TestConst.name + i);
			list.add(map);
		}
		engine.addBatch(list);
		Map<String, Object> map = new HashMap<>();
		map.put("*", "*");
		map.put(MapperSearchDict.rows, count);
		List<?> result = engine.search(map);
		assertEquals(count, result.size());
	}

	/**
	 * 有错误！
	 * @throws InterruptedException
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午7:39:40
	 */
	@SuppressWarnings("unchecked")
	@Ignore
	public void testdeleteByID2() throws InterruptedException {
		int num = engine.search((Map<String, Object>)MapBuilder.start("*", "*").get()).size();
		String id = GlobalUtil.getUUID();
		System.out.println(id);
		Map<String, Object> map = new HashMap<>();
		map.put(TestMapperDict.uid, id);
		map.put(TestMapperDict.name, TestConst.name);
		boolean result = engine.add(map);
//		Thread.sleep(1000);
		int currNum = engine.search((Map<String, Object>)MapBuilder.start("*", "*").get()).size();
		assertEquals((num + 1), currNum);
		Thread.sleep(2000);
		assertTrue(result);
		result = engine.deleteByID(id);
		assertTrue(result);
		Thread.sleep(1000);
		currNum = engine.search((Map<String, Object>)MapBuilder.start("*", "*").get()).size();
		assertEquals(num, currNum);
//		engine.deleteByID("a0492ff5f6944d5299fcb829577de785", "dad790ea16bf400ea4c07c1065fe654e");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testdeleteByID() throws Exception {
		clear();
		int count = 3;
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(TestMapperDict.uid, GlobalUtil.getUUID());
			map.put(TestMapperDict.name, TestConst.name);
			engine.add(map);
		}
		List<?> result = engine.search((Map<String, Object>)MapBuilder.start("*", "*").add(MapperSearchDict.rows, count).get());
		int num = result.size();
//		for (Object item : result) {
//			SolrDocument doc = (SolrDocument)item;
//			engine.deleteByID(doc.get(TestMapperDict.id).toString());
//			System.out.println("delete: " + doc.get(TestMapperDict.id));
//			int currNum = engine.search((Map<String, Object>)MapBuilder.start("*", "*").add(MapperDict.rows, count).get()).size();
//			assertEquals(--num, currNum);
//		}
		String[] ids = new String[num];
		for (int i = 0; i < num; i++) {
			SolrDocument doc = (SolrDocument)result.get(i);
			ids[i] = doc.get(TestMapperDict.uid).toString();
		}
		engine.deleteByID(ids);
		int currNum = engine.search((Map<String, Object>)MapBuilder.start("*", "*").add(MapperSearchDict.rows, count).get()).size();
		assertEquals(0, currNum);
//		engine.deleteByID("15fc910e50604ca5b1c0bcd70d7f9904", "d32504263acd49a19a9d5ca43b1111e9");
	}
	
	@Test
	public void testDelete() {
		Map<String, Object> map = new HashMap<>();
		map.put(TestMapperDict.uid, GlobalUtil.getUUID());
		map.put(TestMapperDict.name, TestConst.name + TestConst.name2);
		boolean result = engine.add(map);
		assertTrue(result);
		map.clear();
		map.put(TestMapperDict.name, TestConst.name);
		map.put(TestMapperDict.name, TestConst.name2);
		result = engine.delete(map);
		assertTrue(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdate() throws InterruptedException {
		String id = GlobalUtil.getUUID();
		Map<String, Object> map = new HashMap<>();
		map.put(TestMapperDict.uid, id);
		map.put(TestMapperDict.name, TestConst.name);
		engine.add(map);
		System.out.println("add:" + id);
		SolrDocument doc = (SolrDocument) engine.search((Map<String, Object>)MapBuilder.start(TestMapperDict.uid, id).get()).get(0);
		assertEquals(doc.get(TestMapperDict.uid).toString(), id);
		assertEquals(doc.get(TestMapperDict.name).toString(), TestConst.name);
		System.out.println("_version_: " + doc.get("_version_"));
		map.put(TestMapperDict.name, TestConst.name2);
//		Thread.sleep(6000);
		engine.add(map);
		System.out.println("update: " + id);
		SolrDocument doc2 = (SolrDocument) engine.search((Map<String, Object>)MapBuilder.start(TestMapperDict.uid, id).get()).get(0);
		System.out.println("_version_: " + doc2.get("_version_"));
		assertEquals(doc2.get(TestMapperDict.uid).toString(), id);
		assertEquals(doc2.get(TestMapperDict.name).toString(), TestConst.name2);
	}
	
	@Test
	public void testUpdateCount() {
		String id = "0";
		Map<String, Object> map = new HashMap<>();
		map.put(TestMapperDict.uid, id);
		for (int i = 0; i < 10; i++) {
			map.put(TestMapperDict.name, i);
			engine.add(map);
		}
	}
	
	@Test
	@Ignore /* 需要cloud模式并有两个shard*/
	public void testRoute() throws Exception {
		clear();
		int count = 9;
		System.out.println("开始添加shard1...");
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(TestMapperDict.uid, GlobalUtil.getUUID());
			map.put(TestMapperDict.name, TestConst.name);
			map.put(MapperSearchDict.route, TestConst.shard1);
			engine.add(map);
			System.out.println(map.get(TestMapperDict.uid));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("*", "*");
		map.put(MapperSearchDict.rows, count);
		map.put(MapperSearchDict.route, TestConst.shard1);
		List<?> result = engine.search(map);
		assertEquals(count, result.size());//shard1数量是count
		map.put(MapperSearchDict.route, TestConst.shard2);
		List<?> result2 = engine.search(map);
		assertEquals(0, result2.size()); //shard2数量是0
		
		System.out.println("开始添加shard2...");
		for (int i = 0; i < count; i++) {
			Map<String, Object> m = new HashMap<>();
			m.put(TestMapperDict.uid, GlobalUtil.getUUID());
			m.put(TestMapperDict.name, TestConst.name);
			m.put(MapperSearchDict.route, TestConst.shard2);
			engine.add(m);
			System.out.println(m.get(TestMapperDict.uid));
		}
		map.put(MapperSearchDict.route, TestConst.shard1);
		result = engine.search(map);
		assertEquals(count, result.size());//shard1数量是count
		map.put(MapperSearchDict.route, TestConst.shard2);
		result2 = engine.search(map);
		assertEquals(count, result2.size()); //shard2数量是count
		
		System.out.println("开始删除shard1数据...");
		map.clear();
		map.put("*", "*");
		map.put(MapperSearchDict.route, TestConst.shard1);
		engine.delete(map);
		map.put(MapperSearchDict.route, TestConst.shard1);
		result = engine.search(map);
		assertEquals(0, result.size());//shard1数量是0
		map.put(MapperSearchDict.route, TestConst.shard2);
		result2 = engine.search(map);
		assertEquals(count, result2.size()); //shard2数量是count

		System.out.println("开始删除shard2数据...");
		map.clear();
		map.put("*", "*");
		map.put(MapperSearchDict.route, TestConst.shard2);
		engine.delete(map);
		map.put(MapperSearchDict.route, TestConst.shard1);
		result = engine.search(map);
		assertEquals(0, result.size());//shard1数量是0
		map.put(MapperSearchDict.route, TestConst.shard2);
		result2 = engine.search(map);
		assertEquals(0, result2.size()); //shard2数量是0
	}
}
