package com.supergenius.xo.sudokuapi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

import java.util.Map;

/**
 * 道具购买记录实体，即天才币的消费记录
 * 
 * @author yangguang
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.appStrategy, strategy = Json.appStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Purchaserecords extends BaseEntity {

	private static final long serialVersionUID = -2086414919713225362L;
	@Json(strategy = Json.appStrategy)
	private String account;// 账户
	@Json(strategy = Json.appStrategy)
	private String prop_type;// 道具类型
	@Json(strategy = Json.appStrategy)
	private int cost;// 花费的天才币数量
	@Json(strategy = Json.appStrategy)
	private int count;// 购买的道具数量
	@Json(strategy = Json.appStrategy)
	private Map<String,Integer> itemmap; //记录批量购买的道具
	@Json(strategy = Json.appStrategy)
	private String trans_code; //订单号
	@Json(strategy = Json.appStrategy)
	private String name;// 昵称
	@Json(strategy = Json.appStrategy)
	private String country;// 国家

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public Map<String, Integer> getItemmap() {
		return itemmap;
	}

	public void setItemmap(Map<String, Integer> itemmap) {
		this.itemmap = itemmap;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProp_type() {
		return prop_type;
	}

	public void setProp_type(String prop_type) {
		this.prop_type = prop_type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
