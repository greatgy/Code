package com.supergenius.xo.user.entity;

import java.util.List;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EOrderState;

/**
 * 订单表
 * @author diaobisong
 */
public class Order extends BaseEntity {

    private static final long serialVersionUID = 5253634093268148177L;
    private String useruid;//用户唯一ID
    private String sn;//订单编号
    private String name;//订单名称
    private String memo;//备忘录
    private double money;//订单总金额
    private EOrderState state;//订单状态（初始化|失败|成功|未付款|已付款|取消订单|已过期|管理员退票|已删除）
    private double totalprice;//总价
    private List<OrderGoods> OrderGoods;//一个订单中的多个商品
    private OrderLog orderLog;
	
    public Order() {
    	super();
    }
    
    /**
     * 生成一条订单的方法
     * @param useruid
     * @param sn
     * @param name
     * @param money
     * @param state
     * @param totalprice
     * @param orderGoods
     * @author: XieMing
     */
    public Order(String useruid, String sn, String name, double money, EOrderState state, double totalprice) {
    	super();
    	this.useruid = useruid;
    	this.sn = sn;
    	this.name = name;
    	this.money = money;
    	this.state = state;
    	this.totalprice = totalprice;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public EOrderState getState() {
		return state;
	}
	
	public String getStateName() {
		return this.state.getName();
	}
	
	public void setState(EOrderState state) {
		this.state = state;
	}
	
	public double getTotalprice() {
		return totalprice;
	}
	
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	public List<OrderGoods> getOrderGoods() {
		return OrderGoods;
	}
	
	public void setOrderGoods(List<OrderGoods> orderGoods) {
		OrderGoods = orderGoods;
	}

	public OrderLog getOrderLog() {
		return orderLog;
	}

	public void setOrderLog(OrderLog orderLog) {
		this.orderLog = orderLog;
	}
	
}
