package com.supergenius.xo.startup.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 题库实体类
 * 
 * @author ChenQi
 * @date 2017年6月20日 11:48:39
 */
@Json(value = {"uid", "status", "createtime", "updatetime", "optionList", "optionsMap", "img"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Question extends BaseEntity {

	private static final long serialVersionUID = -630494910728576807L;
	private int type;// 题目类型(0一票否决|1创业加分)
	private String name;// 题目
	private String options;// 选项类别和选项及选项内容，以json的方式记录{"如果是男生，请选择":{"A":"",...}}
	private String optionscore;// 选项类别和选项及选项得分，以json的方式记录{"如果是男生，请选择":{"A":5,...}}
	private int order;// 显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示
	private String adminuid;// 操作人uid
	private String img; //题目的插图路径
	// 下面属性不存在数据库中，封装为了使用
	List<Options> optionList = new ArrayList<Options>();// 将选项及其内容和分数封装成Options对象并存储在list中
	Map<String, List<Options>> optionsMap = new HashMap<String, List<Options>>();// 将选项及其内容和分数封装成Options对象和选项类别并存储在map中
	private String adminname;

	public Map<String, List<Options>> getOptionsMap() {
		return optionsMap;
	}

	public void setOptionsMap(Map<String, List<Options>> optionsMap) {
		this.optionsMap = optionsMap;
	}

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
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	public String getOptionscore() {
		return optionscore;
	}

	public void setOptionscore(String optionscore) {
		this.optionscore = optionscore;
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
