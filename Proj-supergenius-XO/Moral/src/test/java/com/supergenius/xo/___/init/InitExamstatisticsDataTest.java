package com.supergenius.xo.___.init;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.model.base.enums.EStatus;
import com.supergenius.moral.moral.dao.ExamstatisticsDao;
import com.supergenius.xo.moral.entity.Examstatistics;

/**
 * 添加考试结果统计的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitExamstatisticsDataTest {

	@Autowired
	ExamstatisticsDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		List<String> erroruidsList = new ArrayList<>();
		List<String> correctuidsList = new ArrayList<>();
		erroruidsList.add("erroruid");
		erroruidsList.add("erroruid");
		erroruidsList.add("erroruid");
		correctuidsList.add("correctuid");
		correctuidsList.add("correctuid");
		correctuidsList.add("correctuid");
		for (int i = 0; i <= 5; i++) {
			Examstatistics examstatistics = new Examstatistics();
			examstatistics.setCorrectuids(correctuidsList);
			examstatistics.setCreatetime(DateTime.now());
			examstatistics.setErroruids(erroruidsList);
			examstatistics.setStatus(EStatus.enable);
			examstatistics.setUseruid("Useruid" + i);
			dao.insert(examstatistics);
		}
	}

}
