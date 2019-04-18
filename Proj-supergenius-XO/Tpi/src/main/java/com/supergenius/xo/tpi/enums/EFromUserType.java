package com.supergenius.xo.tpi.enums;

/**
 * 发件人类型
 * @author LiuXiaoke
 */
public enum EFromUserType {

	genius("0"),//超天才
	merger("1");//并购会员消息
	
	private final String value;

	private EFromUserType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EFromUserType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EFromUserType get(String str) {
		for (EFromUserType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
