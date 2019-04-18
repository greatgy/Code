package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 专业类型枚举类
 * @author chenminchang
 * @date 2016-3-18 下午5:52:01 
 */
public enum EMajor {
	
	strategicManage(Integer.valueOf("1000000000000000000000000000000", 2)), //战略企划管理1073741824
	prodManage(Integer.valueOf("0100000000000000000000000000000", 2)),      //生产和物流管理536870912
	hrManage(Integer.valueOf("0010000000000000000000000000000", 2)),        //人力资源管理268435456
	marketManage(Integer.valueOf("0001000000000000000000000000000", 2)),    //市场营销管理134217728
	mediaManage(Integer.valueOf("0000100000000000000000000000000", 2)),     //整合传播管理67108864
	financeManage(Integer.valueOf("0000010000000000000000000000000", 2));   //财务管理33554432

    private final int value;

    private EMajor(int v) {
        this.value = v;
    }

    public int getValue() {
		return value;
	}
    
    public String toString() {
        return String.valueOf(value);
    }

    public static EMajor get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EMajor get(String str) {
		for (EMajor e : values()) {
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
    public static EMajor getByName(String name) {
		for (EMajor e : values()) {
			if (e.name().equalsIgnoreCase(name)) {
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
    public static String getByValue(String str) {
		for (EMajor e : values()) {
		    int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e.name();
			}
		}
		return null;
	}

    public String getSpecialtyName() {
		return EMajor.getName(EMajor.get(value), Locale.CHINA);
	}

    public static String getName(EMajor e, Locale locale) {
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
    public Integer plus(EMajor e) {
        return value | Integer.valueOf(e.toString());
    }

    /**
     * @param education2
     * @return
     */
    public int minus(EMajor e) {
        return value ^ Integer.valueOf(e.toString());
    }

    /**
     * 得到最先匹配的类型
     * @param type
     */
    public static List<EMajor> getMatch(int type) {
        List<EMajor> list = new ArrayList<>();
        if (EMajor.strategicManage.ismatch(type)) {
            list.add(EMajor.strategicManage);
        }
        if (EMajor.prodManage.ismatch(type)) {
            list.add(EMajor.prodManage);
        }
        if (EMajor.hrManage.ismatch(type)) {
            list.add(EMajor.hrManage);
        }
        if (EMajor.marketManage.ismatch(type)) {
            list.add(EMajor.marketManage);
        }
        if (EMajor.mediaManage.ismatch(type)) {
            list.add(EMajor.mediaManage);
        }
        if (EMajor.financeManage.ismatch(type)) {
            list.add(EMajor.financeManage);
        }
        return list;
    }

    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
    
    /**
     * 获取所有专业的中文名
     * @return
     */
    public static Map<String, Object> getChinaNames() {
    	Map<String, Object> map = new HashMap<>();
    	for (EMajor item : EMajor.values()) {
    		map.put(item.name(), item.getName());
    	}
    	return map;
    }
    
    /**
     * 获取所有专业的值与中文名称
     * @return
     * @author XieMing
     * 2016-7-27 下午3:00:34
     */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> major = new HashMap<String, String>();
		for (EMajor eMajor : EMajor.values()) {
			major.put(eMajor.toString(), eMajor.getName());
		}
		return major;
    }

	/**
	 * 返回student所有已申请专业
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-10 下午7:08:30
	 */
	public static Map<String, String> getMyMajor(int majors) {
		Map<String, String> map = new HashMap<String, String>();
		for (EMajor major : EMajor.values()) {
			if((major.value & majors) > 0) {
				map.put(major.toString(), major.getName());
			}
		}
		return map;
	}
}
