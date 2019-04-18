package com.supergenius.xo.manager.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 专家等级枚举类
 * @author guanshiqian
 * @date 2016-4-28 下午5:58:15 
 */
public enum EExpertLevel {
	
	expert("0"), // 专家
	highExpert("1"), // 高级专家 
	specialExpert("2"); // 特级专家

    private final String value;

    private EExpertLevel(String v) {
        this.value = v;
    }

    public String toString() {
        return this.value;
    }

    public static EExpertLevel get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EExpertLevel get(String str) {
		for (EExpertLevel e : values()) {
		   if (e.value.equals(str)) {
			return e;
		   }
		}
		return null;
	}
    
    /**
     * 根据name得到values
     * @param name
     * @return
     */
    public static EExpertLevel getByName(String name) {
		for (EExpertLevel e : values()) {
			if (e.name().equalsIgnoreCase(name)) {
				return e;
			}
		}
		return null;
	}
    
    public static String getName(EExpertLevel e, Locale locale) {
        return I18N.getEnumName(e, locale);
    }
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

    /**
     * 获取所有专家级别的值与中文名称
     * @return
     * @author XieMing
     * 2016-7-27 下午3:00:34
     */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> levels = new HashMap<String, String>();
		for (EExpertLevel level : EExpertLevel.values()) {
			levels.put(level.toString(), level.getName());
		}
		return levels;
    }
}
