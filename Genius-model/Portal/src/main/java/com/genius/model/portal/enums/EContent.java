package com.genius.model.portal.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 网站内容枚举类型，广告 关于我门等等
 *
 * @author architect.bian
 * 2014-6-24 上午11:25:03
 */
public enum EContent {

	ad("0"), notice("1"), friendLink_txt("2"), friendLink_img("3"), search("4"), hotsearch("5"), banner("6"), logo("7"),
	aboutus("20"), contact("21"), service("22"), hr("23"), legal("24"), help("25"), sitemap("26"), rule_register("27"), rule_user("28"), product("29"),
	advantage("30"), news("31"), articles("32"), hot("33"), team("34"), jobs("35"), introduce("36"), videos("37"), imgs("38"), index("39"),
	login("40"), order("41"), sales("42"), support("43"), recommend("44"), activity("45"), demo("46"),
	email_pwdreset("100"), email_validate("101"), email_rebinding("103"), email_registsuccess("104");

	private final String value;
	
	private EContent(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EContent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EContent get(String str) {
		for (EContent e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EContent e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
