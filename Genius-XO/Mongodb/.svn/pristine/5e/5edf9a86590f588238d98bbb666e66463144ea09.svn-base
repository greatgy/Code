package com.genius.xo.mongodb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.MongoDict;
import com.genius.xo.mongodb.mock.dao.LibraryDao;
import com.genius.xo.mongodb.mock.model.Address;
import com.genius.xo.mongodb.mock.model.Book;
import com.genius.xo.mongodb.mock.model.Library;
import com.genius.xo.mongodb.mock.testconstants.MapperDict;
import com.genius.xo.mongodb.mock.testconstants.TestConst;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class LibraryDaoTest {

	@Autowired
	LibraryDao dao;

	Library entity = null;
	
	/**
	 * 在执行所有单元测试方法之前插入library
	 */
	@Before
	public void before() {
		entity = new Library();
		entity.setName(TestConst.name2);
		entity.setFoundTime(DateTime.now().minusDays(2));
		
		Library data = new Library();
		data.setUid(TestConst.uid);
		data.setName(TestConst.name);
		data.setVisitorCount(TestConst.count);
		data.setFoundTime(DateTime.now().minusDays(2));
		assertTrue(dao.insert(data));

	}

	/**
	 * 在执行完所有单元测试之后删除library
	 */
	@After
	public void after() {
//		dao.delete(TestConst.uid);
//		dao.delete(entity.getUid());
//		assertNull(dao.get(TestConst.uid));
		entity = null;
		dao.drop();
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testIn(){
		Map<String, Object> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add(TestConst.uid);
		map.put(BaseMapperDict.ids, list);
		List<Library> libraries = dao.getList(map);
		assertEquals(1, libraries.size());
		
		dao.insert(entity);
		map = new HashMap<>();
		list = new ArrayList<>();
		list.add(entity.getName());
		list.add("nametest");
		map.put(BaseMapperDict.name + BaseMapperDict.suffix_in_key, list);
		List<Library> libraries1 = dao.getList(map);
		assertEquals(1, libraries1.size());
		
	}
	
	/**
	 * 测试insert
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:16
	 */
	@Test
	public void testInsert() {
		boolean result = dao.insert(entity);
		assertTrue(result);
		assertTrue(dao.delete(entity.getId()));
	}
	
	/**
	 * 测试insert指定strategy
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-11 下午5:48:16
	 */
	@Test
	public void testInsertWithStrategy() {
		String name = "nameWithStrategy";
		entity.setName(name);
		entity.setNameEN("nameEN");
		boolean result = dao.insert(entity, "vip");
		assertTrue(result);
		entity = dao.get(entity.getId(), "vip");
		assertTrue(entity.getNameEN() == null);
		assertEquals(name, entity.getName());
		assertTrue(dao.delete(entity.getId()));
	}

	/**
	 * 测试insert失败
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:26
	 */
	@Test
	public void testInsertFail() {
		dao.insert(entity);
		boolean result = dao.insert(entity);
		assertFalse(result);
		assertTrue(dao.delete(entity.getId()));
	}

	/**
	 * 测试插入原生数据类型数组
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:47
	 */
	@Test
	public void testInsertWithArray() {
		List<String> names = Lists.newArrayList("ab", "c", "de", "f", "g");
		entity.setVisitorsName(names);
		dao.insert(entity);
		Library library = dao.get(entity.getId());
		assertTrue(names.get(2).equals(library.getVisitorsName().get(2)));
		assertTrue(dao.delete(entity.getId()));
	}
	
	/**
	 * 测试插入原生数据类型Map嵌套
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:47
	 */
	@Test
	public void testInsertWithMaps() {
		Map<String, Map<String, Object>> memos = new HashMap<>();
		Map<String, Object> memo = new HashMap<>();
		memo.put("k1", "v1");
		memo.put("k2", "v2");
		memos.put("lili", memo);
		memos.put("beibei", memo);
		entity.setMemos(memos);
		dao.insert(entity);
		Library library = dao.get(entity.getId());
		assertTrue(library.getMemos().get("lili").get("k1").equals("v1"));
		assertTrue(dao.delete(entity.getId()));
	}
	
	/**
	 * 测试插入原生数据类型数组，读取返回时short变int，所以操作mongodb时不能使用short类型
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:47
	 */
	@Test(expected=ClassCastException.class)
	public void testInsertWithArrayWithConvertException() {
		List<Short> ages = Lists.newArrayList((short)1, (short)2, (short)3, (short)4, (short)5);
		entity.setVisitorsAge(ages);
		dao.insert(entity);
		Library library = dao.get(entity.getId());
		System.out.println("ages.get(2).getClass():" + ages.get(2).getClass());
		System.out.println("library.getVisitorsAge().get(2):" + library.getVisitorsAge().get(2));
		System.out.println("library.getVisitorsAge().get(2).getClass():" + library.getVisitorsAge().get(2).getClass());
	}

	/**
	 * 测试插入引用的对象
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:38:57
	 */
	@Test
	public void testInsertRefObj() {
		Address addr = new Address("china", "beijing", "haidian", "shangdi", "park");
		entity.setAddress(addr);
		boolean result = dao.insert(entity);
		assertTrue(result);
		Library library = dao.get(entity.getUid());
		assertEquals(addr, library.getAddress());
		assertTrue(dao.delete(entity.getId()));
	}

	/**
	 * 测试插入有对象列表（List）的数据
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-9 上午10:39:09
	 */
	@Test
	public void testInsertWithObjList() {
		List<Book> list = new ArrayList<>();
		Map<Integer, String> pages = new HashMap<>();
		pages.put(1, "first page");
		pages.put(2, "second page");
		pages.put(3, "third page");
		Book book = null;
		for (int i = 0; i < 3; i++) {
			book = new Book();
			book.setName(TestConst.nameBook + i);
			book.setPages(pages);
			list.add(book);
		}
		entity.setBooks(list);
		assertTrue(dao.insert(entity));
		Library fromDB = dao.get(entity.getId());
		assertEquals(list.size(), fromDB.getBooks().size());
		assertEquals(list, fromDB.getBooks());
		assertEquals(list.get(0), fromDB.getBooks().get(0));
		assertEquals(list.get(0).getUid(), fromDB.getBooks().get(0).getUid());
		assertTrue(dao.delete(entity.getId()));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		Library t = dao.getOne(map);
		assertNotNull(t);
		assertEquals(t.getName(), TestConst.name);
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name, TestConst.name);
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	/**
	 * 测试大于等于条件成功
	 * 
	 * @author ShangJianguo
	 */
	@Test 
	public void testGetListGTE(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_greaterOrEqual_key, TestConst.count);
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_greaterOrEqual_key, TestConst.count + 1);
		list = dao.getList(map);
		assertEquals(0, list.size());
	}
	
	/**
	 * 测试大于等于条件
	 * 
	 * @author ShangJianguo
	 */
	@Test
	public void testGetListGt(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_greater_key, TestConst.count);
		List<Library> list = dao.getList(map);
		assertEquals(0, list.size());
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_greater_key, TestConst.count - 1);
		list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testGetListLTE(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_lessOrEqual_key, TestConst.count);
		List<Library> list = dao.getList(map);
		System.out.println(list);
		assertEquals(1, list.size());
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_lessOrEqual_key, TestConst.count - 1);
		list = dao.getList(map);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetListLT(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.foundTime + BaseMapperDict.suffix_lessOrEqual_key, DateTime.now());
		map.put(MapperDict.name + BaseMapperDict.suffix_like_key, TestConst.name);
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testGetListLike(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.name + BaseMapperDict.suffix_like_key, "zhan");
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
		
		map.put(MapperDict.name + BaseMapperDict.suffix_like_key, "hangs");
		list = dao.getList(map);
		assertEquals(1, list.size());
		
		map.put(TestConst.name + BaseMapperDict.suffix_like_key, "hangsann");
		list = dao.getList(map);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetListMutilFix(){
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.visitorCount + BaseMapperDict.suffix_greater_key, TestConst.count - 1);
		map.put(MapperDict.foundTime + BaseMapperDict.suffix_lessOrEqual_key, DateTime.now());
		map.put(MapperDict.name + BaseMapperDict.suffix_like_key, TestConst.name);
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
	}

	@Test
	public void testGetListWhere(){
		String name = "乔布斯";
		entity.setName(name);
		entity.setNameEN("Jobs");
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		
		Map<String, Object> map = new HashMap<>();
		map.put(MongoDict.$where, String.format("this.name.indexOf('%s') >= 0", TestConst.name));
		List<Library> list = dao.getList(map);
		assertEquals(1, list.size());
		System.out.println(list.get(0).getName());
		assertTrue(list.get(0).getName().contains(TestConst.name));
		
		map.put(MongoDict.$where, String.format("this.name.indexOf('%s') == -1", TestConst.name));
		list = dao.getList(map);
		assertEquals(1, list.size());
		System.out.println(list.get(0).getName());
		assertTrue(!list.get(0).getName().contains(TestConst.name));
	}
	
	/**
	 * 随机取某条记录，将%s改为相应的获取比例并增加limit，即可按比例随机取某几条记录
	 * 比例：1-limit/count，为避免取的记录数不够，则换成算法为：1-limit/count * 2 相当于取两次，但还是应该有结果判断逻辑，数量不够时重新取
	 * 
	 * @author Architect.bian
	 * @createtime 2015-7-13 下午2:13:31
	 */
	@Test
	public void testGetListWhereRandom(){
		String name = "乔布斯";
		entity.setName(name);
		entity.setNameEN("Jobs");
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		
		Map<String, Object> map = new HashMap<>();
		map.put(MongoDict.$where, String.format("function(){if(Math.random() > %s ) {return true;} else {return false;}}", 0));
		List<Library> list = dao.getList(map);
		assertTrue(list.size() > 1);
		
		map.put(MongoDict.$where, String.format("function(){if(Math.random() > %s ) {return true;} else {return false;}}", 1));
		list = dao.getList(map);
		assertEquals(0, list.size());
	}

	/**
	 * 返回结果：[{total=2, name=乔布斯, nameEN=Jobs, foundTime=1422436727098}, {total=1, name=乔布斯, nameEN=Jobs, foundTime=1422263926987}, {total=1, name=zhangsan, nameEN=null, foundTime=1422263927010}]
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:19:04
	 */
	@Test
	public void testGroup() {
		String name = "乔布斯";
		entity.setVisitorCount(60);
		entity.setName(name);
		entity.setNameEN("Jobs");
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		entity.setUid(GlobalUtil.getUUID());
		String name2 = "it's me";
		entity.setName(name2);
		entity.setVisitorCount(50);
		entity.setFoundTime(DateTime.now());
		dao.insert(entity);
		entity.setUid(GlobalUtil.getUUID());
		entity.setVisitorCount(70);
		dao.insert(entity);
		
		Map<String, Object> where = new HashMap<>();
		where.put(MapperDict.foundTime + BaseMapperDict.suffix_lessOrEqual_key, DateTime.now());
		
		Map<String, Object> group = new HashMap<>();
		group.put(MapperDict.total, MapBuilder.start(MongoDict.$sum, 1).get());
//		group.put(MapperDict.total, new BasicDBObject(MongoDict.$sum, 1));
		group.put("avgVisitorCount", MapBuilder.start(MongoDict.$avg, "$" + MapperDict.visitorCount).get());
		
		List<String> fields = new ArrayList<>();
		fields.add(MapperDict.name);
		fields.add(MapperDict.nameEN);
		fields.add("foundTime");
		
		Map<String, Object> sort = new HashMap<>();
		sort.put(MapperDict.total, -1);
		
		List<Map<String, Object>> list = dao.group(where, fields, group, sort, new Pager(1, 5));
		System.out.println(list);
		assertTrue(list.size() == 3);
		assertTrue(Integer.valueOf(list.get(0).get(MapperDict.total).toString()) == 2);
		assertEquals(name2, list.get(0).get(MapperDict.name));
		assertTrue(Double.valueOf(list.get(0).get("avgVisitorCount").toString()) == 60);
	}
	
	/**
	 * [{total=2, name=乔布斯, nameEN=Jobs, foundTime=1422439072555}, {total=1, name=乔布斯, nameEN=Jobs, foundTime=1422266272445}, {total=1, name=zhangsan, nameEN=null, foundTime=1422266272469}]
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:57:57
	 */
	@Test
	public void testAggregate(){
		String name = "乔布斯";
		entity.setName(name);
		entity.setNameEN("Jobs");
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		entity.setFoundTime(DateTime.now());
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		
		DBObject where = new BasicDBObject();
		where.put(MapperDict.foundTime, new BasicDBObject(MongoDict.$lte, DateTime.now().getMillis()));
		
		
		DBObject group = new BasicDBObject();
//		group.put("_id", "$name");
		group.put(MongoDict.id, MapBuilder.start(MapperDict.name, "$name").add(MapperDict.nameEN, "$nameEN").add(MapperDict.foundTime, "$foundTime").get());
		group.put(MapperDict.total, new BasicDBObject(MongoDict.$sum, 1));
		
		DBObject sort = new BasicDBObject();
		sort.put(MapperDict.total, -1);
		
		DBObject fields = new BasicDBObject();
		fields.put(MongoDict.id, 0);
		fields.put(MapperDict.name, "$_id.name");
		fields.put(MapperDict.nameEN, "$_id.nameEN");
		fields.put(MapperDict.foundTime, "$_id.foundTime");
		fields.put(MapperDict.total, 1);
		
//		DBObject limit = new BasicDBObject(MongoDict.$limit, 5);
//		DBObject skip = new BasicDBObject(MongoDict.$skip, 0);
		
		List<DBObject> pipeline = new ArrayList<>();
		pipeline.add(new BasicDBObject(MongoDict.$match, where));
		pipeline.add(new BasicDBObject(MongoDict.$group, group));
		pipeline.add(new BasicDBObject(MongoDict.$sort, sort));
		pipeline.add(new BasicDBObject(MongoDict.$project, fields));
//		pipeline.add(limit);
//		pipeline.add(skip);
		List<Map<String, Object>> list = dao.aggregate(pipeline, null, new Pager(1, 5));
		System.out.println(list);
		assertTrue(list.size() == 3);
		assertTrue(Integer.valueOf(list.get(0).get(MapperDict.total).toString()) == 2);
		assertEquals(name, list.get(0).get(MapperDict.name));
		assertNotNull(list.get(0).get(MapperDict.nameEN));
	}
	
	/**
	 * [{_id=乔布斯, total=3}, {_id=zhangsan, total=1}]
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-28 下午5:57:27
	 */
	@Test
	public void testAggregate2(){
		String name = "乔布斯";
		entity.setName(name);
		entity.setNameEN("Jobs");
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		entity.setFoundTime(DateTime.now());
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		entity.setUid(GlobalUtil.getUUID());
		dao.insert(entity);
		
		DBObject where = new BasicDBObject();
		where.put(MapperDict.foundTime, new BasicDBObject(MongoDict.$lte, DateTime.now().getMillis()));
		
		
		DBObject group = new BasicDBObject();
		group.put("_id", "$name");
		group.put(MapperDict.total, new BasicDBObject(MongoDict.$sum, 1));
		
		DBObject sort = new BasicDBObject();
		sort.put(MapperDict.total, -1);
		
		DBObject fields = new BasicDBObject();
		fields.put(MongoDict.id, 1);
		fields.put(MapperDict.total, 1);
		
//		DBObject limit = new BasicDBObject(MongoDict.$limit, 5);
//		DBObject skip = new BasicDBObject(MongoDict.$skip, 0);
		
		List<DBObject> pipeline = new ArrayList<>();
		pipeline.add(new BasicDBObject(MongoDict.$match, where));
		pipeline.add(new BasicDBObject(MongoDict.$group, group));
		pipeline.add(new BasicDBObject(MongoDict.$sort, sort));
		pipeline.add(new BasicDBObject(MongoDict.$project, fields));
//		pipeline.add(limit);
//		pipeline.add(skip);
		List<Map<String, Object>> list = dao.aggregate(pipeline, null, new Pager(1, 5));
		System.out.println(list);
		assertTrue(list.size() == 2);
		assertTrue(Integer.valueOf(list.get(0).get(MapperDict.total).toString()) == 3);
		assertEquals(name, list.get(0).get("_id"));
		assertTrue(list.get(1).get(MapperDict.nameEN) == null);
	}
	
	@Test
	public void testUpdate() {
		Library library = new Library();
		dao.insert(library);
		library.setVisitorCount(1);
		dao.update(library);
		assertEquals(1, dao.get(library.getUid()).getVisitorCount());
		assertTrue(dao.delete(library.getUid()));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateFields() {
		boolean result = dao.insert(entity);
		assertTrue(result);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.name, TestConst.name1);
		map.put(MongoDict._id, entity.getId());
		dao.updateFields(map);
		List<Library> list = dao.getList((Map<String, Object>) MapBuilder.start().get());
		for (Library library : list) {
			if (library.getId().equals(entity.getId())) {
				assertEquals(library.getName(), TestConst.name1);
			} else {
				assertFalse(library.getName().equals(TestConst.name1));
			}
		}
		map.remove(MongoDict._id);
		dao.updateFields(map);
		list = dao.getList((Map<String, Object>) MapBuilder.start().get());
		for (Library library : list) {
			assertEquals(library.getName(), TestConst.name1);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateWhere(){
		entity.setName(TestConst.name1);
		boolean result = dao.insert(entity);
		assertTrue(result);
		Map<String, Object> where = new HashMap<String, Object>();
		where.put(MapperDict.name + BaseMapperDict.suffix_like_key, TestConst.name1);
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(MapperDict.name, TestConst.name2);
		dao.update(where, fields);
		List<Library> list = dao.getList((Map<String, Object>) MapBuilder.start().get());
		for (Library library : list) {
			if (library.getName().indexOf(TestConst.name2) != -1) {
				assertEquals(library.getName(), TestConst.name2);
			} else {
				assertFalse(library.getName().equals(TestConst.name2));
			}
		}
	}

	/**
	 * 测试boolean update(DBObject where, DBObject dbObj);的用法
	 * 
	 * @author Architect.bian
	 * @createtime 2015-7-24 下午1:29:52
	 */
	@Test
	public void testUpdateDBObj(){
		entity.setName(TestConst.name1);
		boolean result = dao.insert(entity);
		assertTrue(result);
		QueryBuilder where = QueryBuilder.start();
		where.put(MapperDict.name).notEquals(TestConst.name1);
		DBObject fields = new BasicDBObject();
		String enName = "english name";
		fields.put(MongoDict.$set, MapBuilder.start(MapperDict.name, TestConst.name1).add(MapperDict.nameEN, enName).get());
		fields.put(MongoDict.$inc, MapBuilder.start(MapperDict.visitorCount, 1).get());
		dao.update(where.get(), fields);
		List<Library> list = dao.getList(new HashMap<String, Object>());
		for (Library library : list) {
			assertEquals(TestConst.name1, library.getName());
			if (enName.equals(library.getNameEN())) {
				assertTrue(library.getVisitorCount() >= 1);
			}
		}
	}

	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Library entity = new Library();
			entity.setUid(GlobalUtil.getUUID());
			entity.setName(name);
			entity.setVisitorCount(100);
			entity.setFoundTime(DateTime.now());
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.name, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	@Test
	public void testIncrease(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid);
		map.put(MapperDict.age, 1);
		dao.increase(map);
	}
	
	/**
	 * true/false的值应该起作用，影响exist的值
	 * 
	 * @author Architect.bian
	 * @createtime 2015-1-19 下午9:01:00
	 */
	@Test
	public void testExist(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.name + BaseMapperDict.suffix_exist_key, false);
		List<Library> list = dao.getList(map);
		assertTrue(list.size() == 0);
		map.clear();
		map.put(MapperDict.name + BaseMapperDict.suffix_exist_key, true);
		list = dao.getList(map);
		assertTrue(list.size() == 1);
		map.clear();
		map.put(MapperDict.name + BaseMapperDict.suffix_nexist_key, false);
		list = dao.getList(map);
		assertTrue(list.size() == 1);
	}
}
