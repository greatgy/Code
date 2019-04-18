package com.supergenius.xo.sudokuapi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 银行类型
 * @author liushaomin
 */
public enum EBank {

	icbc("0"),//工商银行
	cmbchina("1"),//招商银行
	boc("2"),//中国银行
	abchina("3"),//中国农业银行
	bankcomm("4"),//交通银行
	abchina_b2b("5"),//中国农业银行 企业b2b
	unionpay("6"),//银联
	special("7"),//特批增加
	alipay("11"),//支付宝
	tenpay("12"),//财付通
	wxpay("13"),//微信支付
	paypal("14"),//paypal
	payeezy("15"),//美洲银行
	apppay("16"),//手机app支付
	applepay("17");//苹果支付

	private final String value;

	private EBank(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EBank get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EBank get(String str) {
		for (EBank e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EBank.getName(EBank.get(value), Locale.CHINA);
	}

	public static String getName(EBank e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
