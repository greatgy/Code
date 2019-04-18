package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMajor;

/**
 * student实体类
 * 
 * @author liubin
 * @date 2016-7-17 上午11:54:43
 */
public class Student extends BaseEntity {

	private static final long serialVersionUID = 8341974358855832191L;
	private String sn;// 学号
	private int majors;// 所有专业
	private String workdaytime;// 工作日禁止联系时间,回车分割
	private String saturdaytime;// 周末禁止联系时间,回车分割
	private String sundaytime;// 暂时没用

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getMajors() {
		return majors;
	}

	public void setMajors(int majors) {
		this.majors = majors;
	}

	public void minusMajor(EMajor e) {
		this.majors = this.majors ^ Integer.valueOf(e.toString());
	}

	public void plusMajor(EMajor e) {
		if (e != null) {
			this.majors = this.majors | Integer.valueOf(e.toString());
		}
	}
	
	public String getWorkdaytime() {
		return workdaytime;
	}

	public void setWorkdaytime(String workdaytime) {
		this.workdaytime = workdaytime;
	}

	public String getSaturdaytime() {
		return saturdaytime;
	}

	public void setSaturdaytime(String saturdaytime) {
		this.saturdaytime = saturdaytime;
	}

	public String getSundaytime() {
		return sundaytime;
	}

	public void setSundaytime(String sundaytime) {
		this.sundaytime = sundaytime;
	}
}
