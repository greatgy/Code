package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Notice;
import com.supergenius.xo.tpi.enums.ENoticeType;
import com.supergenius.xo.tpi.enums.ENoticeChannel;

/**
 * Notice单元测试
 * @author liushaomin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class NoticeDaoTest {
	
	@Autowired
	NoticeDao dao;
	
	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		Notice notice = new Notice();
		notice.setUid(TestConst.uid);
		notice.setTitle(TestConst.title);
		assertTrue(dao.insert(notice));
	}
	
	@After
	public void after() {
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}
	
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		assertEquals(1, dao.getCount(map));
	}
	
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.title, TestConst.title);
		List<Notice> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testUpdate() {
		Notice entity  = dao.get(TestConst.uid);
		entity.setTitle(TestConst.title1);
		dao.update(entity);
		assertEquals(TestConst.title1, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, TestConst.title);
		map.put(BaseMapperDict.uid, TestConst.uid);
		dao.updateFields(map);
		assertEquals(TestConst.title, dao.get(TestConst.uid).getTitle());
	}
	
	@Test
	public void testDeleteByMap() {
		String title = "testtitle";
		for (int i = 0; i < 3; i++) {
			Notice entity = new Notice();
			entity.setTitle(title);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.title, title);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	/**
	 * 插入测试数据
	 */
	@Test
	@Ignore
	public void testNotice() {
		Notice notice = new Notice();
		notice.setUid(GlobalUtil.getUUID());
		notice.setTitle("testtitle1");
		notice.setChannel(ENoticeChannel.recruitment);
		notice.setFromuid(GlobalUtil.getUUID());
		notice.setFromname("testfromname");
		notice.setType(ENoticeType.team);
		notice.setContent("testcontent");
		dao.insert(notice);
	}

}
