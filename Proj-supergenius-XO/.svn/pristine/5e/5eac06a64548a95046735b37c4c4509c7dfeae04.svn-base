package com.supergenius.xo.career.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 规则实体类
 * 
 * @author yangguang
 * @date 2017年11月13日15:51:19
 */
@Json(value = { "uid", "research", "name", "convention", "art", "enterprise", "sociology", "reality",
		"adminuid", "adminname", "cid", "content"}, ignoreTypeStrategy = Json.webStrategy, strategy = {Json.webStrategy, Json.cacheStrategy})
public class Ruler extends BaseEntity {

	private static final long serialVersionUID = 5613847105728617725L;
	private String name;// 职业名称
	private int cid;//职业id
	private double research;//研究型
	private double convention;//常规型
	private double art;//艺术型
	private double enterprise;//企业型
	private double sociology;//社会型
	private double reality;//现实型
	private String adminuid;// 操作人uid
	private String content;// 内容
	
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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
	
	public double getResearch() {
		return research;
	}

	public void setResearch(double research) {
		this.research = research;
	}

	public double getConvention() {
		return convention;
	}

	public void setConvention(double convention) {
		this.convention = convention;
	}

	public double getArt() {
		return art;
	}

	public void setArt(double art) {
		this.art = art;
	}

	public double getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(double enterprise) {
		this.enterprise = enterprise;
	}

	public double getSociology() {
		return sociology;
	}

	public void setSociology(double sociology) {
		this.sociology = sociology;
	}

	public double getReality() {
		return reality;
	}

	public void setReality(double reality) {
		this.reality = reality;
	}

	/**
	 * 针对性更新
	 * 
	 * @param t
	 */
	public void set(Ruler t) {
		this.research = t.getResearch();
		this.convention = t.getConvention();
		this.art = t.getArt();
		this.enterprise = t.getEnterprise();
		this.sociology = t.getSociology();
		this.reality = t.getReality();
		this.adminuid = t.getAdminuid();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
