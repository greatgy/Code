package com.supergenius.xo.tpi.enums;

/**
 * 团队进度枚举
 * @author liushaomin
 */
public enum ETeamStage {
	
	init("0"),//进行中
	success("1");//创建完成
	
	private final String value;

	private ETeamStage(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ETeamStage get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETeamStage get(String str) {
		for (ETeamStage e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
}
