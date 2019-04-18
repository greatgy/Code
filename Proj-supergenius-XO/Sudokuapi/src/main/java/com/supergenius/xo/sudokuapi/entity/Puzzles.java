package com.supergenius.xo.sudokuapi.entity;

import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 题目信息
 * 
 * @author yangguang
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = { Json.webStrategy, Json.appStrategy }, strategy = { Json.webStrategy, Json.appStrategy })
@Maps(strategy = Maps.dbStrategy)
public class Puzzles extends BaseEntity {

	private static final long serialVersionUID = -5220227612047913649L;
	private String level;
	private String mode;
	private String source;
	private Map<String, Object> question;
	private Map<String, Object> answer;
    //puzzles.getAnswer().size() - puzzles.getQuestion().size();   //需要填入的格子数量
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<String, Object> getQuestion() {
		return question;
	}

	public void setQuestion(Map<String, Object> question) {
		this.question = question;
	}

	public Map<String, Object> getAnswer() {
		return answer;
	}

	public void setAnswer(Map<String, Object> answer) {
		this.answer = answer;
	}

}
