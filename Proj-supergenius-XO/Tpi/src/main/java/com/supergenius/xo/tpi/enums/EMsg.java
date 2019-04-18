package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息类型
 * @author liushaomin
 */
public enum EMsg {

	sysmsg("0"),//系统消息
	usermsg("1"),//会员消息
	createteammsg("2"),//团队消息
	sysnotice("3");//系统通知
	
	private final String value;

	private EMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EMsg get(String str) {
		for (EMsg e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EMsg.getName(EMsg.get(value), Locale.CHINA);
	}
	
	public static String getName(EMsg e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
