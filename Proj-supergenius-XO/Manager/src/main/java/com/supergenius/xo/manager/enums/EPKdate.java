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
 * Create at: 2013-9-10 上午10:21:13
 * ============================================================================
 */
package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * pk日期类型
 * @author chenminchang
 * @createtime 2016-7-18上午9:33:20
 */
public enum EPKdate {
	
	all("0");
	
	private final String value;

	private EPKdate(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EPKdate get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EPKdate get(String str) {
		for (EPKdate e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

}
