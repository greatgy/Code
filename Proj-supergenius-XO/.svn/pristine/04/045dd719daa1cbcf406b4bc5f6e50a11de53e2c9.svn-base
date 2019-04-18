package com.supergenius.xo.startup.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.startup.enums.EGender;

/**
 * 调查结果统计实体类
 * 
 * @author ChenQi
 * @date 2017年6月20日 11:52:22
 */
@Json(value = { "uid", "score", "ruleruid", "useruid", "data", "loginip", "name", "nickname", "gender", "email", "phone",
		"questionList", "selectedMap", "rejectcount", "contont", "advice" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Statistics extends BaseEntity {

	private static final long serialVersionUID = 1397259194367802584L;
	private int score;// 总得分
	private String ruleruid;// 得分结果反馈内容
	private String data;// 记录被调查者回答的题目uid及选项，json格式存储{"questionuid":"A",...}
	private String loginip;// 登录IP
	private String useruid;//用户uid
	private String name;// 姓名
	private String nickname;// 昵称
	private EGender gender;// 性别(0男1女)
	private String email;// 邮箱
	private String phone;// 手机号码
	private String address;// 地址
	private String advice; //意见反馈

	// 下面属性不存在数据库中，封装为了使用
	private List<Question> questionList = new ArrayList<Question>();// 将问题封装成Question对象并存储在list中
	private Map<Integer, Integer> selectedMap = new HashMap<Integer, Integer>();
	private int rejectcount;
	private String contont;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRuleruid() {
		return ruleruid;
	}

	public void setRuleruid(String ruleruid) {
		this.ruleruid = ruleruid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
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

	public int getRejectcount() {
		return rejectcount;
	}

	public void setRejectcount(int rejectcount) {
		this.rejectcount = rejectcount;
	}

	public String getContont() {
		return contont;
	}

	public void setContont(String contont) {
		this.contont = contont;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
}
