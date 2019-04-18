package com.supergenius.xo.tpi.enums;

/**
 * 发件箱消息状态
 * @author liuxiaoke
 *
 */
public enum EOutboxState {
	normal("1"),
	delete("0");
	
	private final String value;

	private EOutboxState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EOutboxState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EOutboxState get(String str) {
		for (EOutboxState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
