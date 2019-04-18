package com.supergenius.xo.base.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 专业枚举类
 *
 * @author ShangJianguo
 */
public enum ESpecialty {
	// TODO 重新考虑字段
	Strategicmanage(Integer.valueOf("1000000000000000000000000000000", 2)), //战略企划管理
	Prodmanage(Integer.valueOf("0100000000000000000000000000000", 2)),      //生产和物流管理
	HRManage(Integer.valueOf("0010000000000000000000000000000", 2)),        //人力资源管理
	Marketmanage(Integer.valueOf("0001000000000000000000000000000", 2)),    //市场营销管理
	MediaManage(Integer.valueOf("0000100000000000000000000000000", 2));     //整合传播管理

    private final int value;

    private ESpecialty(int v) {
        this.value = v;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static ESpecialty get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static ESpecialty get(String str) {
		for (ESpecialty e : values()) {
		    int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
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
    public static ESpecialty getByValues(String name) {
		for (ESpecialty e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
    
    /**
     * 根据values得到name
     * @param str
     * @return
     */
    public static String getByName(String str) {
		for (ESpecialty e : values()) {
		    int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e.name();
			}
		}
		return null;
	}
    
    public String getSpecialtyName() {
		return ESpecialty.getName(ESpecialty.get(value), Locale.CHINA);
	}

    public static String getName(ESpecialty e, Locale locale) {
        return I18N.getEnumName(e, locale);
    }

    /**
     * 判断此类型是否与值匹配
     * @param type
     * @return
     */
    public boolean ismatch(int type) {
        return value == (value & type);
    }

    /**
     * @param education2
     * @return
     */
    public Integer plus(ESpecialty e) {
        return value | Integer.valueOf(e.toString());
    }

    /**
     * @param education2
     * @return
     */
    public int minus(ESpecialty e) {
        return value ^ Integer.valueOf(e.toString());
    }

    /**
     * 得到最先匹配的类型
     * @param type
     */
    public static List<ESpecialty> getMatch(int type) {
        List<ESpecialty> list = new ArrayList<>();
        if (ESpecialty.Strategicmanage.ismatch(type)) {
            list.add(ESpecialty.Strategicmanage);
        }
        if (ESpecialty.Prodmanage.ismatch(type)) {
            list.add(ESpecialty.Prodmanage);
        }
        if (ESpecialty.HRManage.ismatch(type)) {
            list.add(ESpecialty.HRManage);
        }
        if (ESpecialty.Marketmanage.ismatch(type)) {
            list.add(ESpecialty.Marketmanage);
        }
        if (ESpecialty.MediaManage.ismatch(type)) {
            list.add(ESpecialty.MediaManage);
        }
        return list;
    }
	
}
