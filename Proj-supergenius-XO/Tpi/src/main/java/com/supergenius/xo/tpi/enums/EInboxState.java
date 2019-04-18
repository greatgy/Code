package com.supergenius.xo.tpi.enums;

/**
 * 收件箱状态
 * @author liuxiaoke
 *
 */
public enum EInboxState {
	normal("1"),
	delete("0");
	
	private final String value;

	private EInboxState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EInboxState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EInboxState get(String str) {
		for (EInboxState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
