package com.supergenius.xo.moral.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.UserdocDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Userdoc;

/**
 * UserdocDao测试类
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class UserdocDaoTest {

	@Autowired
	UserdocDao dao;

	Userdoc entity = null;

	@Before
	public void before() {
		entity = new Userdoc();
		entity.setUid(TestConst.uid);
		entity.setName(TestConst.name);
		entity.setCountdl(33);
		entity.setId("agajrhbgkg");
		entity.setMd5sum("a;ujhgakhgbkaerghaukrygagbaryhfgaurfgvbayh");
		entity.setCreatetime(DateTime.now());

		assertTrue(dao.insert(entity));
	}

	@After
	public void after() {
		entity = null;
		assertTrue(dao.delete(TestConst.uid));
	}

	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseStrDict.uid, TestConst.uid);
		assertNotNull(dao.getOne(map));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseStrDict.uid, TestConst.uid);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseStrDict.uid, TestConst.uid);
		List<Userdoc> userdocs = dao.getList(map);
		assertEquals(1, userdocs.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String name = entity.getName();
		entity.setName(TestConst.name1);
		assertTrue(dao.update(entity));
		Userdoc userdoc = dao.get(TestConst.uid);
		assertEquals(TestConst.name1, userdoc.getName());
		entity.setName(name);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = entity.getName();
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, TestConst.name1);
		dao.updateFields(map);
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, name);
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String name = "krvaarvhalek";
		for (int i = 0; i < size; i++) {
			Userdoc userdoc = new Userdoc();
			userdoc.setName(name);
			dao.insert(userdoc);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, name);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

	@Test
	@Ignore
	public void testGroup() {
		Map<String, Object> where = new HashMap<>();
		where.put(MapperDict.createtime + BaseMapperDict.suffix_less_key, DateTime.now());
		List<String> fields = new ArrayList<>();
		fields.add(MapperDict._id);
		fields.add(MapperDict.total);
		Map<String, Object> group = new HashMap<>();
		group.put(MapperDict._id, MongoDict.$ + MapperDict.useruid);
		group.put(MapperDict.total, MapBuilder.start(MongoDict.$sum, MongoDict.$ + MapperDict.countdl).get());
		Map<String, Object> sort = new HashMap<>();
		sort.put(MapperDict.total, -1);
		List<Map<String, Object>> list = dao.group(where, fields, group, sort, new Pager(1, 5));
		for (Map<String, Object> map : list) {
			System.out.println(map.get(MapperDict._id) + " : " + map.get(MapperDict.total));
		}
		assertNotNull(list);
	}

}
