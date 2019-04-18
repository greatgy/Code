package com.supergenius.xo.life.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.EGrade;

/**
 * 科目实体类
 * 
 * @author YangGuang
 * @date 2018年5月9日11:45:50
 */
@Json(value = { "status", "createtime", "updatetime", "sid", "grade", "name", "data", "adminname" }, ignoreTypeStrategy = Json.webStrategy, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Subject extends BaseEntity {

	private static final long serialVersionUID = -5657738991329325473L;
	private int sid; // 科目的id
	private int grade; // 年级
	private String name; // 科目名称
	private String data; // 相关的其他json数据
	private String adminuid; // 操作人UID
	private List<EGrade> listgrade = new ArrayList<>();

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
	public List<EGrade> getGrades() {
		if (listgrade.size() == 0) {
			listgrade = EGrade.getMatch(this.grade);
		}
		return listgrade;
	}
	
	/**
	 * 显示所属的所有年级
	 * 
	 * @return
	 */
	@Json(strategy = {Json.webStrategy})
	public String getGradeName() {
		StringBuffer gradename = new StringBuffer();
		for (EGrade item : getGrades()) {
			gradename.append(EGrade.getName(EGrade.get(item.toString()), Locale.CHINA).toString()).append(" ");
		}
		return gradename.toString();
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIsSix() {
		return EGrade.ismatch(grade, EGrade.six);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsSeven() {
		return EGrade.ismatch(grade, EGrade.seven);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsEight() {
		return EGrade.ismatch(grade, EGrade.eight);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsNine() {
		return EGrade.ismatch(grade, EGrade.nine);
	}
}
