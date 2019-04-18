package com.supergenius.xo.tpi.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EType;

/**
 * 类别表，存储一些可编辑的类别
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class Type extends BaseEntity {

	private static final long serialVersionUID = 3077346754034641297L;
	
	@Json(strategy = Json.webStrategy)
	private String name; // 中文名字
	@Json(strategy = Json.webStrategy)
	private EType type;// 类型：团队类别、项目类别
	@Json(strategy = Json.webStrategy)
	private String summary;// 简介
	@Json(strategy = Json.webStrategy)
	private String typecode;// 分类代码
	@Json(strategy = Json.webStrategy)
	private String img;// 表示图
	private String adminuid;// 管理员uid
	@Json(strategy = Json.webStrategy)
	private int membernum; // 团队类别使用，该类团队至少需要的人数 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EType getType() {
		return type;
	}

	public void setType(EType type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EType.getName(type, Locale.CHINA);
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public int getMembernum() {
		return membernum;
	}

	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}

}
