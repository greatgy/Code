package com.supergenius.xo.tpi.enums;

/**
 * 消息状态枚举
 * @author liushaomin
 */
public enum EMsgState {
	
	success("0"),//已读
	init("1"),//未读
	delete("2"),//已删除
	//创建团队消息
	refusal("3"),//已拒绝
	accept("4");//已接受
	
	
	private final String value;

	private EMsgState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMsgState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EMsgState get(String str) {
		for (EMsgState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
