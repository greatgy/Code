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

import com.supergenius.moral.moral.dao.ExamDao;
import com.supergenius.xo.moral.entity.Exam;
import com.supergenius.xo.moral.enums.EExam;

/**
 * 添加考试的初始化数据
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitExamDataTest {

	@Autowired
	ExamDao dao;

	@Ignore
	@Test
	public void insertInitData() {
		List<String> answerList = new ArrayList<>();
		List<String> correctanswerList = new ArrayList<>();
		List<String> questionList = new ArrayList<>();
		answerList.add("answeruid");
		answerList.add("answeruid");
		answerList.add("answeruid");
		correctanswerList.add("correctansweruid");
		correctanswerList.add("correctansweruid");
		correctanswerList.add("correctansweruid");
		questionList.add("questionuid");
		questionList.add("questionuid");
		questionList.add("questionuid");
		for (int i = 0; i <= 5; i++) {
			Exam exam = new Exam();
			exam.setBegintime(DateTime.now());
			exam.setFinishtime(DateTime.now());
			exam.setScore(60.00 + i);
			exam.setType(EExam.exam);
			exam.setUseruid("Useruid" + i);
			exam.setAnsweruids(answerList);
			exam.setCorrectansweruids(correctanswerList);
			exam.setQuestionuids(questionList);
			dao.insert(exam);
		}
	}

}
