package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 支付状态枚举
 * @author diaobisong
 */
public enum EOrderState {

	init(-1),//初始化
	failed(0), //失败
	success(1),//成功
	unpay(2), //未付款
	payed(3),//已付款
	cancel(4),//取消订单
	preparepay(6),//预支付
	timeout(5),//已过期
	admincancel(14),//管理员退票
	delete(-2);// 已删除

	private final int value;

	private EOrderState(int v) {
		this.value = v;
	}

	public int getValue() {
		return value;
	}

	public static EOrderState get(int v) {
		for (EOrderState e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
	public static EOrderState get(long v) {
		for (EOrderState e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
 	public String toString() {
 		return String.valueOf(value);
 	}
	
	public boolean equals(EOrderState v){
	    return this.value == v.getValue();
	}
	
	public String getName(){
	    return I18N.getEnumName(this, Locale.CHINA);
	}
}
