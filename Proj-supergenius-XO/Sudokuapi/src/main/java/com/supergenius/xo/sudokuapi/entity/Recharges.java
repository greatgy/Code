package com.supergenius.xo.sudokuapi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;
import com.supergenius.xo.sudokuapi.enums.EBank;

/**
 * 充值记录实体
 * 
 * @author yangguang
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.appStrategy, strategy = Json.appStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Recharges extends BaseEntity {

	private static final long serialVersionUID = -2086414919713225362L;
	@Json(strategy = Json.appStrategy)
	private String from;	//购买者
	@Json(strategy = Json.appStrategy)
	private String targer;  //购买获得者
	@Json(strategy = Json.appStrategy)
	private String trans_code; //
	@Json(strategy = Json.appStrategy)
	private String payuid;
	@Json(strategy = Json.appStrategy)
	private int purchase;	//购买的天才币数量
	@Json(strategy = Json.appStrategy)
	private double cost;	//购买天才币所花的钱数
	@Json(strategy = Json.appStrategy)
	private boolean used;
	@Json(strategy = Json.appStrategy)
	private EBank bank;	//购买时通过的银行渠道
	@Json(strategy = Json.appStrategy)
	private String name;	//昵称
	@Json(strategy = Json.appStrategy)
	private String country;	//国家


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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTarger() {
		return targer;
	}

	public void setTarger(String targer) {
		this.targer = targer;
	}

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getPayuid() {
		return payuid;
	}

	public void setPayuid(String payuid) {
		this.payuid = payuid;
	}

	public int getPurchase() {
		return purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public EBank getBank() {
		return bank;
	}

	public void setBank(EBank bank) {
		this.bank = bank;
	}
	
}
