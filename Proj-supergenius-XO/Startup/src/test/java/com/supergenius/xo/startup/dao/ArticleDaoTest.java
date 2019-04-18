package com.supergenius.xo.startup.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.startup.entity.Article;
import com.supergenius.xo.startup.entity.Collect;

/**
 * 文章测试类
 * @author 许志翔
 * @date 2017年8月23日14:47:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ArticleDaoTest {

	@Autowired
	private ArticleDao dao;
	
	@Autowired
	private CollectDao collectDao;

	private Article entity;
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid1);
		assertNotNull(entity);
		assertEquals(TestConst.uid1, entity.getUid());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, TestConst.uid1);
		assertNotNull(dao.getOne(map));
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		assertTrue(dao.getList(map).size() > 0);
	}
	
	/**
	 * Test GetList()
	 */
	@Test
	public void testGetListByUseruid() {
		Collect collect = new Collect();
		collect.setUid(TestConst.uid1);
		collect.setCreatetime(new DateTime());
		collect.setUseruid(TestConst.uid2);
		collect.setRefuid(TestConst.uid3);
		assertTrue(collectDao.insert(collect));
		entity = new Article();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthoruid("uid22222222222222222222222222224");
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.useruid, TestConst.uid2);
		assertTrue(dao.getListByUseruid(map).size() > 0);
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
		collectDao.delete(TestConst.uid1);
		assertNull(collectDao.get(TestConst.uid1));
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Article();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthoruid("uid22222222222222222222222222224");
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setIsoriginal(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Update()
	 */
	@Test
	public void testUpdate() {
		entity = new Article();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthoruid("uid22222222222222222222222222224");
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		entity.setAuthor("李四");
		assertTrue(dao.update(entity));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Article();
		entity.setUid(TestConst.uid2);
		entity.setCid(2);
		entity.setAdminuid(TestConst.uid3);
		entity.setAuthoruid("uid22222222222222222222222222224");
		entity.setAuthor("张三");
		entity.setContent("文章的测试内容");
		entity.setTitle("文章的测试标题");
		entity.setImglittle("/imglittle");
		entity.setImgmedium("/Imgmedium");
		entity.setImgbig("/Imgbig");
		entity.setImgoriginal("/Imgoriginal");
		entity.setOrigin("这是来源的测试");
		entity.setSummary("这个摘要的测试");
		entity.setIstop(1);
		entity.setToptime(new DateTime());
		entity.setStatus(EStatus.enable);
		entity.setCreatetime(new DateTime());	
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid2));
		dao.delete(TestConst.uid2);
		assertNull(dao.get(TestConst.uid2));
	}
	
}
