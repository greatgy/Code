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
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.AppJudgement;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EJudgementLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.mock.testconstants.TestConst;

/**
 * Appjudgement测试类
 * @author liubin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class AppJudgementDaoTest {

	@Autowired
	private AppJudgementDao dao;

	private AppJudgement entity;

	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		entity = dao.get(TestConst.uid2);
		assertNotNull(entity);
		assertNotNull(entity.getUseruid());
		assertNotNull(entity.getUsername());
		assertNotNull(entity.getType());
		assertNotNull(entity.getMajorlevel());
		assertNotNull(entity.getUserlevel());
		assertNotNull(entity.getJudgelevel());
		assertNotNull(entity.getAppstudentuid());
		assertNotNull(entity.getDesc());
		assertNotNull(entity.getFile());
		assertNotNull(entity.getFile2());
		assertNotNull(entity.getTopiccount());
		assertNotNull(entity.getDescto());
		assertNotNull(entity.isIsonline());
		assertNotNull(entity.getProvidetimes());
		assertNotNull(entity.getProvidetime());
		assertNotNull(entity.getLevelfrom());
		assertNotNull(entity.getLevelto());
		assertNotNull(entity.getStage());
		assertNull(entity.getConfuid());
		assertNotNull(entity.getArgs());
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
		assertNotNull(entity.getUsername());
		assertNotNull(entity.getType());
		assertNotNull(entity.getMajorlevel());
		assertNotNull(entity.getUserlevel());
		assertNotNull(entity.getJudgelevel());
		assertNotNull(entity.getAppstudentuid());
		assertNotNull(entity.getDesc());
		assertNotNull(entity.getFile());
		assertNotNull(entity.getFile2());
		assertNotNull(entity.getTopiccount());
		assertNotNull(entity.getDescto());
		assertNotNull(entity.isIsonline());
		assertNotNull(entity.getProvidetimes());
		assertNotNull(entity.getProvidetime());
		assertNotNull(entity.getLevelfrom());
		assertNotNull(entity.getLevelto());
		assertNotNull(entity.getStage());
		assertNull(entity.getConfuid());
		assertNotNull(entity.getArgs());
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
		map.put(BaseMapperDict.userid, TestConst.uid);
		assertTrue(dao.getList(map).size() > 0);
	}

	/**
	 * Test Insert()
	 */
	@Test
	public void testInsert() {
		entity = new AppJudgement();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid3);
		entity.setUsername("kebi");
		entity.setMajor(EMajor.marketManage);
		entity.setType(EJudge.judgment);
		entity.setMajorlevel(EStudentLevel.basic);
		entity.setUserlevel(EUser.expert);
		entity.setJudgelevel(EJudgementLevel.erji );
		entity.setAppstudentuid(TestConst.uid2);
		entity.setDesc("just a test !");
		entity.setFile("up");
		entity.setFile2("down");
		entity.setTopiccount(120);
		entity.setDescto("back");
		entity.setIsonline(true);
		entity.setProvidetime("12:00-15:00");
		entity.setProvidetimes("11:00-12:00\n13:00-14:00");
		entity.setLevelfrom(EJudgementLevel.gaoji);
		entity.setLevelto(EJudgementLevel.teji);
		entity.setStage(EAppJudgementStage.interview);
		entity.setConfuid("confuid123456");
		entity.setArgs("something");
		entity.setStatus(EStatus.deleted);
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
		entity = new AppJudgement();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid3);
		entity.setUsername("kebi");
		entity.setMajor(EMajor.marketManage);
		entity.setType(EJudge.judgment);
		entity.setMajorlevel(EStudentLevel.basic);
		entity.setUserlevel(EUser.expert);
		entity.setJudgelevel(EJudgementLevel.erji );
		entity.setAppstudentuid(TestConst.uid2);
		entity.setDesc("just a test !");
		entity.setFile("up");
		entity.setFile2("down");
		entity.setTopiccount(120);
		entity.setDescto("back");
		entity.setIsonline(true);
		entity.setProvidetime("12:00-15:00");
		entity.setProvidetimes("11:00-12:00\n13:00-14:00");
		entity.setLevelfrom(EJudgementLevel.gaoji);
		entity.setLevelto(EJudgementLevel.teji);
		entity.setStage(EAppJudgementStage.interview);
		entity.setConfuid("confuid123456");
		entity.setArgs("something");
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		entity.setMajor(EMajor.prodManage);
		entity.setStatus(EStatus.enable);
		assertTrue(dao.update(entity));
		assertTrue(entity.getStatus().equals(EStatus.enable));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test UpdateFields()
	 */
	@Test
	public void testUpdateFields() {
		entity = new AppJudgement();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid3);
		entity.setUsername("kebi");
		entity.setMajor(EMajor.marketManage);
		entity.setType(EJudge.judgment);
		entity.setMajorlevel(EStudentLevel.basic);
		entity.setUserlevel(EUser.expert);
		entity.setJudgelevel(EJudgementLevel.erji );
		entity.setAppstudentuid(TestConst.uid2);
		entity.setDesc("just a test !");
		entity.setFile("up");
		entity.setFile2("down");
		entity.setTopiccount(120);
		entity.setDescto("back");
		entity.setIsonline(true);
		entity.setProvidetime("12:00-15:00");
		entity.setProvidetimes("11:00-12:00\n13:00-14:00");
		entity.setLevelfrom(EJudgementLevel.gaoji);
		entity.setLevelto(EJudgementLevel.teji);
		entity.setStage(EAppJudgementStage.interview);
		entity.setConfuid("confuid123456");
		entity.setArgs("something");
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		String uid = entity.getUid();
		assertNotNull(dao.get(uid));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, EStatus.deleted);
		assertTrue(dao.updateFields(map));
		dao.delete(uid);
		assertNull(dao.get(uid));
	}

	/**
	 * Test Delete()
	 */
	@Test
	public void testDelete() {
		entity = new AppJudgement();
		entity.setUid(TestConst.uid);
		entity.setUseruid(TestConst.uid3);
		entity.setUsername("kebi");
		entity.setMajor(EMajor.marketManage);
		entity.setType(EJudge.judgment);
		entity.setMajorlevel(EStudentLevel.basic);
		entity.setUserlevel(EUser.expert);
		entity.setJudgelevel(EJudgementLevel.erji );
		entity.setAppstudentuid(TestConst.uid2);
		entity.setDesc("just a test !");
		entity.setFile("up");
		entity.setFile2("down");
		entity.setTopiccount(120);
		entity.setDescto("back");
		entity.setIsonline(true);
		entity.setProvidetime("12:00-15:00");
		entity.setProvidetimes("11:00-12:00\n13:00-14:00");
		entity.setLevelfrom(EJudgementLevel.gaoji);
		entity.setLevelto(EJudgementLevel.teji);
		entity.setStage(EAppJudgementStage.interview);
		entity.setConfuid("confuid123456");
		entity.setArgs("something");
		entity.setStatus(EStatus.deleted);
		entity.setUpdatetime(new DateTime());
		dao.insert(entity);
		assertNotNull(dao.get(TestConst.uid));
		dao.delete(TestConst.uid);
		assertNull(dao.get(TestConst.uid));
	}

}