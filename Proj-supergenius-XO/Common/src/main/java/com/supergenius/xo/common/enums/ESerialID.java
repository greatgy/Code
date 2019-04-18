package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 序列化枚举种类
 * 
 * @author liubin
 * @createtime 2016-4-15 下午7:18:55
 */
public enum ESerialID {

	usersn("0"), studentsn("1"), judgementsn("2"), ordersn("3"), videosn("4"), challengesn("5"), accountsn("6"), accountlog("7"), workordersn("8"), appreplysn("9"), conferencesn("10"),
	askleavesn("11"), appjudgement("12"), 
	lecturesn("13"),//讲座sn
	certificatesn("14");//证书sn

	private final String value;

	private ESerialID(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ESerialID get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ESerialID get(String str) {
		for (ESerialID e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ESerialID e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
