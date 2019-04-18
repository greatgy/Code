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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.QuestionDao;
import com.supergenius.xo.mock.testconstants.TestConst;
import com.supergenius.xo.moral.entity.Options;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * QuestionDao测试类
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class QuestionDaoTest {

	@Autowired
	QuestionDao dao;

	Question entity = null;

	@Before
	public void before() {
		entity = new Question();
		List<Question> qurList = new ArrayList<>();
		qurList.add(new Question());
		qurList.add(new Question());
		qurList.add(new Question());
		qurList.add(new Question());
		List<Options> opsList = new ArrayList<>();
		Options options = new Options();
		for (int i = 1; i <= 4; i++) {
			options.setUid("optionuid" + i);
			options.setName("optionname" + i);
			options.setStatus(EStatus.get(1));
			opsList.add(options);
		}
		List<String> ansList = new ArrayList<>();
		ansList.add("answer1");
		ansList.add("answer2");
		ansList.add("answer3");
		entity.setUid(TestConst.uid);
		entity.setQuestions(qurList);
		entity.setOptions(opsList);
		entity.setAnswer(ansList);
		entity.setAnalysis("analysis-analysis-analysis-analysis");
		entity.setChapter(EChapter.get("1"));
		entity.setDesc("desc-desc-desc-desc-desc-desc-desc");
		entity.setCreatetime(DateTime.now());
		entity.setTitle(TestConst.title);
		entity.setType(EQst.get("2"));
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
		List<Question> questions = dao.getList(map);
		assertEquals(1, questions.size());
	}

	/**
	 * 以下为循环5次之后的结果 
	 * 总数据量为1000条，本次访问数据库用时为:310 
	 * 总数据量为1000条，本次随机获取到的数据为:90条 
	 * 总数据量为1000条，本次访问数据库用时为:193 
	 * 总数据量为1000条，本次随机获取到的数据为:98条 
	 * 总数据量为1000条，本次访问数据库用时为:208 
	 * 总数据量为1000条，本次随机获取到的数据为:95条
	 * 总数据量为1000条，本次访问数据库用时为:219 
	 * 总数据量为1000条，本次随机获取到的数据为:100条 
	 * 总数据量为1000条，本次访问数据库用时为:187 
	 * 总数据量为1000条，本次随机获取到的数据为:102条
	 * 
	 * 连续进行几次循环之后发现，第一次的访问时间相比其他次访问要长一些， 
	 * 第一次的平均时间在300ms左右， 剩余次的平均时间在200ms左右。
	 * 
	 * @author LiJiacheng
	 */
	@Test
	public void testRandomGet() {
		entity = dao.get(TestConst.uid);
		for (int i = 1; i <= 1000; i++) {
			entity.setUid("uid" + i);
			assertTrue(dao.insert(entity));
		}
		Map<String, Object> map = new HashMap<>();
		map.put(MongoDict.$where, "function(){if(Math.random()>0.9){return true} else{return false}}");
		// for (int i = 0; i < 10; i++) {
		long timestart = System.currentTimeMillis();
		List<Question> list = dao.getList(map);
		long timeend = System.currentTimeMillis();
		System.out.println("总数据量为1000条，本次访问数据库用时为:" + (timeend - timestart));
		System.out.println("总数据量为1000条，本次随机获取到的数据为:" + list.size() + "条");
		// }
		for (int i = 1; i <= 1000; i++) {
			assertTrue(dao.delete("uid" + i));
		}
	}

	// 随机取值的相关测试结果，请参考文档--MongoDB性能测试（项目驱动）

	@Test
	public void testUpdate() {
		entity = dao.get(TestConst.uid);
		String analy = entity.getAnalysis();
		entity.setAnalysis(TestConst.username);
		assertTrue(dao.update(entity));
		Question question = dao.get(TestConst.uid);
		assertEquals(TestConst.username, question.getAnalysis());
		entity.setAnalysis(analy);
		dao.update(entity);
	}

	@Test
	public void testUpdateFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		String analy = entity.getAnalysis();
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put("analysis", "liujnralrnavja");
		dao.updateFields(map);
		map.put(BaseMapperDict.uid, entity.getUid());
		map.put("analysis", analy);
		dao.updateFields(map);
	}

	@Test
	public void testDeleteByMap() {
		int size = 3;
		String analy = "auaekjhbraelvgbahjhab";
		for (int i = 0; i < size; i++) {
			Question question = new Question();
			question.setAnalysis(analy);
			dao.insert(question);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("analysis", analy);
		assertEquals(size, dao.getList(map).size());
		dao.deleteByMap(map);
		assertTrue(dao.getList(map).size() == 0);
	}
}
