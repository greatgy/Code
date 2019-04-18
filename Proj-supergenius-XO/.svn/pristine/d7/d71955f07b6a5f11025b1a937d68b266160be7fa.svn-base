package com.supergenius.xo.manager.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 视频枚举
 * @author LiuBin
 */
public enum EVideoFrom { 

	pk("0"),//挑战视频
	reply("1"),//答辩视频
	open("2"),//开题视频
	expert("3"),//专家讲解视频
	example("4"),//范例视频
	other("5");//其它视频

	private final String value;

	private EVideoFrom(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EVideoFrom get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EVideoFrom get(String str) {
		for (EVideoFrom e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取所有视频类型的值与中文名称
	 * @return
	 * @author XieMing
	 * 2016-10-20 下午4:44:55
	 */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> videofrom = new HashMap<String, String>();
		for (EVideoFrom from : EVideoFrom.values()) {
			videofrom.put(from.toString(), from.getName());
		}
		return videofrom;
    }
}
