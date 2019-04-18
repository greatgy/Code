package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * content枚举
 * @author liushaomin
 */
public enum EType {
	
	manager("0"),//职业经理人
	enterpriser("1"),//企业家
	wisdom("2"),//智慧产业
	joinus("4"),//加入我们
	aboutus("3"),//关于我们(公司简介)
	legal("5"),//法律声明
	contact("6"),//联系我们
	friendlink("7"),//友情连接
	indexsupergenius("8"),//首页显示的关于超天才
	mobilemanager("9"),//移动端职业经理人
	mobileenterpriser("10"),//移动端企业家
	mobilewisdom("11"),//移动端智慧产业
	mobileaboutus("12"),//移动端关于我们
	mobilecontact("13"),//移动端联系我们
	member("14");//会员通道

	private final String value;

	private EType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EType get(String str) {
		for (EType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public static String getName(EType e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
	
	public String getName() {
		return I18N.getEnumName(EType.get(value), Locale.CHINA);
	}
	
}
