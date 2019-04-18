package com.supergenius.xo.tpi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.tpi.entity.Link;
import com.supergenius.xo.tpi.entity.MergerIndicator;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.EProjectState;

/**
 * 测试类
 * 
 * @author ShangJianguo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class ProjectDaoTest {

	@Autowired
	ProjectDao dao;

	Project entity = null;

	/**
	 * 在执行所有单元测试方法之前插入user
	 */
	@Before
	public void before() {
		entity = new Project();
		entity.setUid(TestConst.uid);
		entity.setName(TestConst.name);
		entity.setUpdatetime(DateTime.now());
		boolean result = dao.insert(entity);
		assertTrue(result);
	}
	
	@After
	public void after() {
		entity = null;
		boolean result = dao.delete(TestConst.uid);
		assertTrue(result);
	}
	
	@Test
	public void testGet() {
		assertNotNull(dao.get(TestConst.uid));
	}

	@Test
	public void testGetOne() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertNotNull(dao.getOne(map));
	}
	
	@Test
	public void testGetCount() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		assertEquals(1, dao.getCount(map));
	}

	@Test
	public void testGetList() {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.name, TestConst.name);
		List<Project> list = dao.getList(map);
		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		entity.setName(TestConst.name1);
		dao.update(entity);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getName());
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, TestConst.name1);
		map.put(BaseMapperDict.uid, entity.getUid());
		dao.updateFields(map);
		assertEquals(TestConst.name1, dao.get(TestConst.uid).getName());
	}

	@Test
	public void testDeleteByMap() {
		String name = "testname";
		for (int i = 0; i < 3; i++) {
			Project entity = new Project();
			entity.setName(name);
			dao.insert(entity);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseMapperDict.name, name);
		assertTrue(dao.getList(map).size() == 3);
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
	
	/**
	 * 添加测试数据
	 * typeuid = testuid4 or testuid4 
	 */
	@Test
	@Ignore
	public void test() {
		Project pro = new Project();
		String uid = GlobalUtil.getUUID();
		pro.setUid(uid);
//		pro.setUid(TestConst.uid2);
		pro.setName("企鹅帝国 ");
		pro.setTypeuid("eaa11e37a6894b2f92a75bb6491cf83d");
		pro.setChannel(EProjectChannel.agency);
		pro.setState(EProjectState.payed);
		pro.setNumber("10088");
		pro.setIscherished(true);
		pro.setIspublic(true);
		pro.setIsrecommend(true);
		pro.setIstop(true);
		pro.setRatiohigh(10);
		pro.setRatiomedium(20);
		pro.setMinchangenum(10);
		pro.setMabackground("背景");
		pro.setRecommondreason("推荐理由");
		pro.setSuitcountry("中国");
		pro.setFromname("JasonLiu");
		pro.setFromuid(TestConst.uid);
		pro.setUpdatetime(DateTime.now());
		MergerIndicator mergerIndicator = new MergerIndicator();
		initMergerIndicator(mergerIndicator);
		pro.setMergerindicator(mergerIndicator);
		Link link = new Link();
		initLink(link);
		pro.setReport(link);
		dao.insert(pro);
	}
	
	/**
	 * 初始化并购指标
	 */
	private void initMergerIndicator(MergerIndicator mergerIndicator) {
		//股权可转让性
		mergerIndicator.setAssignwill(3);
		mergerIndicator.setOthersharewill(5);
		mergerIndicator.setAssignfree(10);
		//行业层面
		mergerIndicator.setIndustrygrowing(5);
		mergerIndicator.setIndustryperiod(6);
		mergerIndicator.setIndustrybarrier(4);
		mergerIndicator.setIndustrylaw(6);
		mergerIndicator.setIndustryconcentration(6);
		mergerIndicator.setIndustrychain(6);
		//企业层面
		mergerIndicator.setFirmstrategy(5);
		mergerIndicator.setFirmservice(6);
		mergerIndicator.setFirmoperations(7);
		mergerIndicator.setFirmsglobalkill(7);
		mergerIndicator.setFirmschinakill(7);
		mergerIndicator.setFirmbrand(6);
		mergerIndicator.setFirmcoverage(8);
		mergerIndicator.setFirmgrowth(6);
		mergerIndicator.setFirmfinance(6);
		mergerIndicator.setFirmsustainable(6);
		mergerIndicator.setFirmsshare(6);
		mergerIndicator.setFirmdomestic(5);		
		//并购机会与并购风险
		mergerIndicator.setRisk(5);
		mergerIndicator.setOpportunity(5);
		//并购能力和经验方面的要求
		mergerIndicator.setPeermaindex(3);
		mergerIndicator.setInvestmaindex(4);
		mergerIndicator.setAmbitionmaindex(5);
	}
	
	/**
	 * 初始化链接
	 */
	private void initLink(Link link) {
		link.setTitle("TestTille");
		link.setOrigin("TestOrigin");
		link.setDesc("TestDesc");
		link.setHref("www.supergenius.cn");
	}
	
	@Test
	public void testSearch() {
		List<Project> list = dao.search(TestConst.name, null);
		assertEquals(1, list.size());
	}
	
}
