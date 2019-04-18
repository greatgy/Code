package com.supergenius.xo.moral.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.genius.core.base.constant.BaseStrDict;
import com.supergenius.moral.moral.dao.DocDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.entity.Doc;

/**
 * DocDao测试类
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class DocDaoTest {

	@Autowired
	DocDao dao;

	Doc entity = null;

	@Before
	public void before() {
		entity = new Doc();
		entity.setUid(TestConst.uid);
		entity.setName("你好");
		entity.setCountdl(2);
		entity.setSn("1");
		entity.setUpdatetime(DateTime.now());
		entity.setOid(2);

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
		List<Doc> docsList = dao.getList(map);
		assertEquals(1, docsList.size());
	}

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String name = entity.getName();
		entity.setName(TestConst.name1);
		assertTrue(dao.update(entity));
		Doc doc1 = dao.get(TestConst.uid);
		assertEquals(TestConst.name1, doc1.getName());
		entity.setName(name);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String nameString = entity.getName();
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, TestConst.name1);
		dao.updateFields(map);
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put(BaseMapperDict.name, nameString);
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String nameString = "adminuid";
		for (int i = 0; i < size; i++) {
			Doc doc = new Doc();
			doc.setName(nameString);
			dao.insert(doc);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, nameString);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}

}
