package com.supergenius.xo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EGoods;

/**
 * 订单商品表
 * @author diaobisong
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class OrderGoods extends BaseEntity {
   
	private static final long serialVersionUID = 1829713819563496067L;
	private String orderuid;//订单ID
	@Json(strategy = Json.webStrategy)
	private String useruid;//用户唯一ID
	@Json(strategy = Json.webStrategy)
	private String sn;//商品编号(挑战编号|视频编号)
	private String refuid;//对应商品的uid
	@Json(strategy = Json.webStrategy)
	private String name;//商品名称
	private EGoods type;//商品类型（视频|门票|挑战）
	private int count;//商品个数
	private double unitprice;//单价
	@Json(strategy = Json.webStrategy)
	private double totalprice;//总价
	@Json(strategy = Json.webStrategy)
	private User user;//订单商品所属的用户
	
	public OrderGoods() {
		super();
	}
	
	/**
	 * 生成一个订单条目的方法
	 * @param orderuid
	 * @param sn
	 * @param name
	 * @param type
	 * @param count
	 * @param unitprice
	 * @param totalprice
	 * @author: XieMing
	 */
	public OrderGoods(String orderuid, String useruid, String sn, String name, EGoods type, int count, double unitprice, double totalprice, String refuid) {
		super();
		this.orderuid = orderuid;
		this.useruid = useruid;
		this.sn = sn;
		this.name = name;
		this.type = type;
		this.count = count;
		this.unitprice = unitprice;
		this.totalprice = totalprice;
		this.refuid = refuid;
	}
	
	public String getOrderuid() {
		return orderuid;
	}
	
	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getName() {
		return name;
	}
	
	public EGoods getType() {
		return type;
	}
	
	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}
	
	public void setType(EGoods type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getUnitprice() {
		return unitprice;
	}
	
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	
	public double getTotalprice() {
		return totalprice;
	}
	
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
