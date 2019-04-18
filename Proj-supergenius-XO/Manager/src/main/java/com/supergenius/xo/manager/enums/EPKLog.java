/**
 * 
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 GrandGeniusGroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: liushaomin
 * ----------------------------------------------------------------------------
 * Create at: 2013-7-23 下午1:01:06
 * ============================================================================
 */
package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author wuhongyan
 * 
 */
public enum EPKLog {
	
	userAction("0"),//用户操作
	sysAction("1");//系统操作

	private final String value;

	private EPKLog(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EPKLog get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EPKLog get(String str) {
		for (EPKLog e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EPKLog e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
