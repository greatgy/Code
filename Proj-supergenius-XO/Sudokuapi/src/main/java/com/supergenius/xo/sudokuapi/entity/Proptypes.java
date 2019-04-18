package com.supergenius.xo.sudokuapi.entity;

import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 道具类型
 * 
 * @author liushaomin
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Proptypes extends BaseEntity {

	private static final long serialVersionUID = -5220227612047913649L;
	private String type;// 类型，TODO 有什么用？
	@Json(strategy = Json.webStrategy)
	private Map<String, Object> name;// 名称
	@Json(strategy = Json.webStrategy)
	private Map<String, Object> func;// 功能（介绍）
	@Json(strategy = Json.webStrategy)
	private String icon;// 图标
	@Json(strategy = Json.webStrategy)
	private int price;// 价格
	@Json(strategy = Json.webStrategy)
	private long sales;// 销量
	@Json(strategy = Json.webStrategy)
	private int order;// 顺序（用于上移、下移）

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Map<String, Object> getName() {
		return name;
	}

	public void setName(Map<String, Object> name) {
		this.name = name;
	}

	public Map<String, Object> getFunc() {
		return func;
	}

	public void setFunc(Map<String, Object> func) {
		this.func = func;
	}

}