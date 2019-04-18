package com.supergenius.xo.user.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EOrderState;

/**
 * 订单日志表
 * @author diaobisong
 */
public class OrderLog extends BaseEntity {

	private static final long serialVersionUID = 5731328526601798974L;
	private String logsn; //流水号
	private String orderuid;//订单uid
	private String ordersn;//订单编号
	private String useruid;//用户唯一id
	private String name;//订单日志名称
	private EOrderState statefrom;//原状态
	private EOrderState stateto;//现状态
	
	public OrderLog() {
		super();
	}
	
	public OrderLog(String logsn, String orderuid, String ordersn, String useruid, String name, EOrderState statefrom, EOrderState stateto) {
		super();
		this.logsn = logsn;
		this.orderuid = orderuid;
		this.ordersn = ordersn;
		this.useruid = useruid;
		this.name = name;
		this.statefrom = statefrom;
		this.stateto = stateto;
	}
	
	public String getLogsn() {
		return logsn;
	}
	
	public void setLogsn(String logsn) {
		this.logsn = logsn;
	}
	
	public String getOrderuid() {
		return orderuid;
	}
	
	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}
	
	public String getOrdersn() {
		return ordersn;
	}
	
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public EOrderState getStatefrom() {
		return statefrom;
	}
	
	public void setStatefrom(EOrderState statefrom) {
		this.statefrom = statefrom;
	}
	
	public EOrderState getStateto() {
		return stateto;
	}
	
	public void setStateto(EOrderState stateto) {
		this.stateto = stateto;
	}
	
}
