package com.supergenius.xo.manager.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 专家类型
 * @author chenminchang
 * @date 2016-7-18 上午10:22:17 
 */
public enum EExpert {
	
	expert("0"),//普通专家（学员晋级）
	//professionalExpert("1"),// 专职专家
	specialExpert("2"),//特批专家
	inviteExpert("3");//特邀专家

    private final String value;

    private EExpert(String v) {
        this.value = v;
    }

    public String toString() {
        return this.value;
    }

    public static EExpert get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EExpert get(String str) {
		for (EExpert e : values()) {
		   if (e.value.equals(str)) {
			return e;
		   }
		}
		return null;
	}
    
    public static String getName(EExpert e, Locale locale) {
        return I18N.getEnumName(e, locale);
    }
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

    /**
     * 获取所有专家类型的值与中文名称
     * @return
     * @author XieMing
     * 2016-7-27 下午3:00:34
     */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> types = new HashMap<String, String>();
		for (EExpert type : EExpert.values()) {
			types.put(type.toString(), type.getName());
		}
		return types;
    }
}
