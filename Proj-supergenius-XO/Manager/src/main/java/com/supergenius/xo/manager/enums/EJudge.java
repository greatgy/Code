package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 裁判类别枚举
 * @author XieMing
 * @date 2016-5-3 下午12:22:34
 */
public enum EJudge {

	judgment(Integer.valueOf("1000000000000000000000000000000", 2)), //普通裁判1073741824
	//fulltimeJudgement(Integer.valueOf("0100000000000000000000000000000", 2)),//专职裁判536870912
	specialJudgement(Integer.valueOf("0010000000000000000000000000000", 2)),//特批裁判268435456
	specialInviteJudgement(Integer.valueOf("0001000000000000000000000000000", 2));//特聘裁判134217718
	
	private final int value;

    private EJudge(int v) {
        this.value = v;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static EJudge get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EJudge get(String str) {
		for (EJudge e : values()) {
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
    public static EJudge getByValues(String name) {
		for (EJudge e : values()) {
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
		for (EJudge e : values()) {
		    int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e.name();
			}
		}
		return null;
	}
    
    public String getTypeName() {
		return EJudge.getName(EJudge.get(value), Locale.CHINA);
	}

    public static String getName(EJudge e, Locale locale) {
        return I18N.getEnumName(e, locale);
    }
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
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
     * 判断此类型是否与值匹配
     * @param type
     * @return
     */
    public static boolean ismatch(int v, EJudge type) {
        return v == (v | Integer.valueOf(type.toString()));
    }
    /**
     * @param education2
     * @return
     */
    public Integer plus(EJudge e) {
        return value | Integer.valueOf(e.toString());
    }

    /**
     * @param education2
     * @return
     */
    public int minus(EJudge e) {
        return value ^ Integer.valueOf(e.toString());
    }

    /**
     * 得到最先匹配的类型
     * @param type
     */
    public static List<EJudge> getMatch(int type) {
        List<EJudge> list = new ArrayList<>();
        if (EJudge.judgment.ismatch(type)) {
            list.add(EJudge.judgment);
        }
        /*if (EJudge.fulltimeJudgement.ismatch(type)) {
            list.add(EJudge.fulltimeJudgement);
        }*/
        if (EJudge.specialJudgement.ismatch(type)) {
        	list.add(EJudge.specialJudgement);
        }
        return list;
    }
    
    /**
     * 获取所有裁判类型的值与中文名称
     * @return
     * @author XieMing
     * 2016-10-21 下午1:47:59
     */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> types = new HashMap<String, String>();
		for (EJudge type : EJudge.values()) {
			types.put(type.toString(), type.getName());
		}
		return types;
    }
}
