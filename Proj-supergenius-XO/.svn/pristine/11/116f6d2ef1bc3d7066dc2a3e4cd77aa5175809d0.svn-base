package com.supergenius.xo.moral.entity;

import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * 题库(question)实体类
 * 
 * @author LiJiacheng
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Question extends BaseEntity {

	private static final long serialVersionUID = 7357460003796218746L;
	@Json(strategy = Json.webStrategy)
	private EChapter chapter;// 章节
	@Json(strategy = Json.webStrategy)
	private EQst type;// 题型（单选or材料）
	@Json(strategy = Json.webStrategy)
	private String title;// 题目
	@Json(strategy = Json.webStrategy)
	private String desc;// 阅读的材料
	@Json(strategy = Json.webStrategy)
	private List<Options> options;// 答案
	@Json(strategy = Json.webStrategy)
	private List<Question> questions;// 材料题中的题目
	@Json(strategy = Json.webStrategy)
	private String analysis;// 答案解析
	@Json(strategy = Json.webStrategy)
	private List<String> answer;// 正确答案

	public EChapter getChapter() {
		return chapter;
	}

	public void setChapter(EChapter chapter) {
		this.chapter = chapter;
	}

	@Json(strategy = Json.webStrategy)
	public String getChapterName() {
		return EChapter.getName(chapter, Locale.CHINA);
	}
	
	public EQst getType() {
		return type;
	}

	public void setType(EQst type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EQst.getName(type, Locale.CHINA);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
	
	/**
	 * set
	 * @param question
	 * @author liushaomin
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void set(Question question) {
		this.desc = question.getDesc();
		this.status = question.getStatus();
		this.chapter = question.getChapter();
	}

}
