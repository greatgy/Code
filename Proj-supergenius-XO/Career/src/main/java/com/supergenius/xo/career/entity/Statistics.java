package com.supergenius.xo.career.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 调查结果统计实体类
 * 
 * @author yangguang
 * @date 2017年11月13日15:57:53
 */
@Json(value = { "uid", "profession", "ruleruid", "servant", "data", "art", "marketing", "manager", "artisan", 
		"questionList", "selectedMap",  "contont" , "loginip", "order"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Statistics extends BaseEntity {

	private static final long serialVersionUID = 1397259194367802584L;
	private double servant;//服务人员偏差
	private double profession;//专业人士
	private double art;//文化艺术
	private double marketing;//营销
	private double manager;//企业管理
	private double artisan;//技术人员
	private String data;// 记录被调查者回答的题目uid及选项，json格式存储{"questionuid":"A",...}
	private String ruleruid;
	private String loginip;// 登录IP

	// 下面属性不存在数据库中，封装为了使用
	private List<Question> questionList = new ArrayList<Question>();// 将问题封装成Question对象并存储在list中
	private Map<Integer, Integer> selectedMap = new HashMap<Integer, Integer>();
	private String contont;
	private int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getRuleruid() {
		return ruleruid;
	}

	public void setRuleruid(String ruleruid) {
		this.ruleruid = ruleruid;
	}
	
	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Map<Integer, Integer> getSelectedMap() {
		return selectedMap;
	}

	public void setSelectedMap(Map<Integer, Integer> selectedMap) {
		this.selectedMap = selectedMap;
	}

	public String getContont() {
		return contont;
	}

	public void setContont(String contont) {
		this.contont = contont;
	}

	public double getServant() {
		return servant;
	}

	public void setServant(double servant) {
		this.servant = servant;
	}

	public double getProfession() {
		return profession;
	}

	public void setProfession(double profession) {
		this.profession = profession;
	}

	public double getArt() {
		return art;
	}

	public void setArt(double art) {
		this.art = art;
	}

	public double getMarketing() {
		return marketing;
	}

	public void setMarketing(double marketing) {
		this.marketing = marketing;
	}

	public double getManager() {
		return manager;
	}

	public void setManager(double manager) {
		this.manager = manager;
	}

	public double getArtisan() {
		return artisan;
	}

	public void setArtisan(double artisan) {
		this.artisan = artisan;
	}
}
