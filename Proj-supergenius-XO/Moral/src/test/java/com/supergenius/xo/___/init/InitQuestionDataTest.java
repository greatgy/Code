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
import com.supergenius.moral.moral.dao.QuestionDao;
import com.supergenius.xo.moral.entity.Options;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * 添加题库的初始化数据
 * 
 * @author LiJiacheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
public class InitQuestionDataTest {

	@Autowired
	QuestionDao dao;

	@Ignore
	@Test
	public void insertInitSingleData() {
		List<String> AnswerList = new ArrayList<>();
		List<Options> optionsList = new ArrayList<>();
		
		for (int i = 0; i <= 5; i++) {
			AnswerList.clear();
			optionsList.clear();
			for (int j = 0; j < 4; j++) {
				Options options = new Options();
				options.setUid("uid" + j);
				options.setName("optionName" + j);
				options.setStatus(EStatus.enable);
				options.setSortorder("-1");
				optionsList.add(options);
			}
			AnswerList.add("uid0");
			Question question = new Question();
			question.setChapter(EChapter.one);
			question.setAnalysis("Analysis" + i);
			question.setCreatetime(DateTime.now());
			question.setStatus(EStatus.enable);
			question.setTitle("第一章Title" + i);
			question.setType(EQst.single);
			question.setAnswer(AnswerList);
			question.setOptions(optionsList);
			dao.insert(question);
		}
	}
	
	@Ignore
	@Test
	public void insertInitMaterialData() {
		List<String> AnswerList = new ArrayList<>();
		List<Options> optionsList = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for (int i = 0; i <= 5; i++) {
			questions.clear();
			Question question = new Question();
			question.setChapter(EChapter.one);
			question.setCreatetime(DateTime.now());
			question.setDesc("我是材料，我是材料，我是材料，重要的事情说三遍" + i);
			question.setStatus(EStatus.enable);
			question.setType(EQst.material);
			for (int j = 0; j < 5; j++) {
				AnswerList.clear();
				optionsList.clear();
				for (int c = 0; c < 4; c++) {
					Options options = new Options();
					options.setUid("uid" + c);
					options.setName("materialoptionName" + c);
					options.setStatus(EStatus.enable);
					options.setSortorder("-1");
					optionsList.add(options);
				}
				AnswerList.add("uid0");
				Question itemqst = new Question();
				itemqst.setAnalysis("Analysis" + i);
				itemqst.setTitle("第一章MaterialTitle" + i);
				itemqst.setAnswer(AnswerList);
				itemqst.setOptions(optionsList);
				dao.insert(itemqst);
				questions.add(itemqst);
			}
			question.setQuestions(questions);
			dao.insert(question);
		}
	}

}
