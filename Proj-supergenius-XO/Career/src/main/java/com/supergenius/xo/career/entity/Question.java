package com.supergenius.xo.career.entity;

import java.util.ArrayList;
import java.util.List;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 题库实体类
 * 
 * @author yangguang
 * @date 2017年11月13日15:47:05
 */
@Json(value = {"uid", "status", "createtime", "updatetime", "optionList", "optionsMap", "img"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Question extends BaseEntity {

	private static final long serialVersionUID = -630494910728576807L;
	private String name;// 题目
	private String options;// 选项类别和选项及选项内容，以json的方式记录{"如果是男生，请选择":{"A":"",...}}
	private String optiontype;// 选项类别和选项及选项得分，以json的方式记录{"如果是男生，请选择":{"A":5,...}}
	private int order;// 显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示
	private String adminuid;// 操作人uid
	private String img; //题目的插图路径
	// 下面属性不存在数据库中，封装为了使用
	List<Options> optionList = new ArrayList<Options>();// 将选项及其内容和分数封装成Options对象并存储在list中
	private String adminname;

	public List<Options> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Options> optionList) {
		this.optionList = optionList;
	}

	@Json(strategy = Json.webStrategy)
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Json(strategy = Json.webStrategy)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Json(strategy = Json.webStrategy)
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Json(strategy = Json.webStrategy)
	public String getOptiontype() {
		return optiontype;
	}

	public void setOptiontype(String optiontype) {
		this.optiontype = optiontype;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
