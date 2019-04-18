package com.supergenius.xo.manager.dao;

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

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EJudgementLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * Judge测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class JudgeDaoTest {

	@Autowired
	private JudgeDao dao;

	private Judge entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getSn());
		assertNotNull(entity.getType());
		assertNotNull(entity.getMajor());
		assertNull(entity.getLevel());
		assertNotNull(entity.getCertificateuid());
		assertNotNull(entity.getJudgecount());
		assertNotNull(entity.getComplaintcount());
		assertNotNull(entity.getDesc());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetOne()
	 */
	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.uid, TestConst.uid2);
		entity = dao.getOne(map);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getSn());
		assertNotNull(entity.getType());
		assertNotNull(entity.getMajor());
		assertNull(entity.getLevel());
		assertNotNull(entity.getCertificateuid());
		assertNotNull(entity.getJudgecount());
		assertNotNull(entity.getComplaintcount());
		assertNotNull(entity.getDesc());
		assertNotNull(entity.getStatus());
		assertNotNull(entity.getCreatetime());
		assertNotNull(entity.getUpdatetime());
	}

	/**
	 * Test GetCount()
	 */
	@Test
	public void testGetCount() {
		assertTrue(dao.getCount(null) >= 1);
	}

	/**
	 * Test GetList()
	 */
	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.status, EStatus.enable);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new Judge();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setSn("judge123456");
		entity.setType(EJudge.specialInviteJudgement);
		entity.setMajor(EMajor.marketManage);
		entity.setLevel(EJudgementLevel.erji);
		entity.setCertificateuid("certificateuid12346789");
		entity.setJudgecount(60);
		entity.setComplaintcount(66);
		entity.setDesc("just a test !");
		entity.setStatus(EStatus.start);
		entity.setUpdatetime(new DateTime());
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
		entity = new Judge();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setSn("judge123456");
		entity.setType(EJudge.specialInviteJudgement);
		entity.setMajor(EMajor.marketManage);
		entity.setLevel(EJudgementLevel.erji);
		entity.setCertificateuid("certificateuid12346789");
		entity.setJudgecount(60);
		entity.setComplaintcount(66);
		entity.setDesc("just a test !");
		entity.setStatus(EStatus.start);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setSn("testusersn222");
		entity.setMajor(EMajor.prodManage);
		entity.setStatus(EStatus.end);
		assertTrue(dao.update(entity));
		assertTrue(entity.getStatus().equals(EStatus.end));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new Judge();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setSn("judge123456");
		entity.setType(EJudge.specialInviteJudgement);
		entity.setMajor(EMajor.marketManage);
		entity.setLevel(EJudgementLevel.erji);
		entity.setCertificateuid("certificateuid12346789");
		entity.setJudgecount(60);
		entity.setComplaintcount(66);
		entity.setDesc("just a test !");
		entity.setStatus(EStatus.start);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.sn, "judge222222");
		map.put(MapperDict.status, EStatus.end);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new Judge();
		entity.setUid(TestConst.uid1);
		entity.setUseruid(TestConst.uid3);
		entity.setSn("judge123456");
		entity.setType(EJudge.specialInviteJudgement);
		entity.setMajor(EMajor.marketManage);
		entity.setLevel(EJudgementLevel.erji);
		entity.setCertificateuid("certificateuid12346789");
		entity.setJudgecount(60);
		entity.setComplaintcount(66);
		entity.setDesc("just a test !");
		entity.setStatus(EStatus.start);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid1));
		dao.delete(TestConst.uid1);
		assertNull(dao.get(TestConst.uid1));
	}
}