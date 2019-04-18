package com.supergenius.xo.sudokuapi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 版本更新
 * 
 * @CreateTime 2018年5月23日--下午6:48:17
 * @author LiuBin
 */
@Json(value = { "uid", "status", "createtime", "updatetime", "name", "code", "company", "path", "introduce" }, ignoreTypeStrategy = Json.appStrategy, strategy = Json.appStrategy)
@Maps(strategy = {Maps.dbStrategy})
public class Versions extends BaseEntity {

	private static final long serialVersionUID = 8780595218188911252L;

	private String name; // 和manifest中versionName一致
	private double code; // 和manifest中versionCode一致
	private String company; // 和app中包名一致"come.supergenius.sudokuapp.***"
	private String path; // 更新文件上传路径
	private String introduce; // 版本更新说明
	private boolean enforcement; //强制更新，默认为非强制

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCode() {
		return code;
	}

	public void setCode(double code) {
		this.code = code;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public boolean isEnforcement() {
		return enforcement;
	}

	public void setEnforcement(boolean enforcement) {
		this.enforcement = enforcement;
	}

}
