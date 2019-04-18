package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EAccountDetailType;
import com.supergenius.xo.tpi.enums.EAccountState;

/**
 * 用户交易明细
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class AccountDetail extends BaseEntity {

	private static final long serialVersionUID = -9180082008289731224L;

	private String useruid;
	private double money; // 交易金额
	private EAccountDetailType type;// 交易类别（账户注册，推荐项目等）
	private EAccountState state; //支付状态
	private String formuid; //充值对象uid
	private String payuid; //支付uid
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public EAccountDetailType getType() {
		return type;
	}

	public void setType(EAccountDetailType type) {
		this.type = type;
	}

	public EAccountState getState() {
		return state;
	}

	public void setState(EAccountState state) {
		this.state = state;
	}

	public String getFromuid() {
		return formuid;
	}

	public void setFromuid(String formuid) {
		this.formuid = formuid;
	}

	public String getPayuid() {
		return payuid;
	}

	public void setPayuid(String payuid) {
		this.payuid = payuid;
	}
}
