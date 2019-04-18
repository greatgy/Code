package com.supergenius.xo.tpi.enums;

/**
 * 点击明细的枚举类型
 * @author liushaomin
 */
public enum ECountType {
	
	read("0"),//阅读(点击)
	comment("1");//评论

	private final String value;

	private ECountType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECountType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECountType get(String str) {
		for (ECountType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
