package com.supergenius.xo.sudokuapi.entity;

import java.util.List;
import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 游戏规则
 * 
 * @author liushaomin
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Rules extends BaseEntity {

	private static final long serialVersionUID = -8801767551070584583L;
	private List<Object> grade;// 段位设置
	private Map<String, Object> score;// 得分设置,减分设置
	private Map<String, Object> ui;// 页面比例
	private Map<String, Object> exchange;

	public List<Object> getGrade() {
		return grade;
	}

	public void setGrade(List<Object> grade) {
		this.grade = grade;
	}

	public Map<String, Object> getScore() {
		return score;
	}

	public void setScore(Map<String, Object> score) {
		this.score = score;
	}

	public Map<String, Object> getUi() {
		return ui;
	}

	public void setUi(Map<String, Object> ui) {
		this.ui = ui;
	}

	public Map<String, Object> getExchange() {
		return exchange;
	}

	public void setExchange(Map<String, Object> exchange) {
		this.exchange = exchange;
	}

}
