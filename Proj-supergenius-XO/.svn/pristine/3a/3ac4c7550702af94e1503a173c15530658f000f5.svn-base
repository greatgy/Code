package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 专家申请枚举类
 * @author guanshiqian
 * @date 2016-4-29 上午9:51:39 
 */
public enum EAppExpert {

	userApply("0"),//用户申请成为专家
	expertUpGrade("1"),//专家晋级
	adminApply("2"),// 管理员申请成为专家
	specialExpert("3"),//特批专家申请
	inviteExpert("4");//特聘专家申请	

    private final String value;

    private EAppExpert(String v) {
        this.value = v;
    }

    public String toString() {
        return this.value;
    }

    public static EAppExpert get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EAppExpert get(String str) {
		for (EAppExpert e : values()) {
		   if (e.value.equals(str)) {
			return e;
		   }
		}
		return null;
	}
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
