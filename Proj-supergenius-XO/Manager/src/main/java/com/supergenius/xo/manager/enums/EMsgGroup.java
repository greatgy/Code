package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 消息组类型，将所有消息类型归为这三组
 * @author chenminchang
 * @date 2016-7-28 下午2:23:09 
 */
public enum EMsgGroup {
	
	system(1),//系统消息
	comment(2),//评论
	notify(3);//通知
     
    private final int value;

 	private EMsgGroup(int v) {
 		this.value = v;
 	}

 	public static EMsgGroup get(int v) {
 		for(EMsgGroup e:values()) {
 			if(e.getValue()==v) {
 				return e;
 			}
 		}
 		return null;
 	}
 	
 	public static EMsgGroup get(String str) {
		for (EMsgGroup e : values()) {
			if (e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}
 	
 	public String toString() {
 		return String.valueOf(value);
 	}
 	
 	public int getValue() {
		return value;
	}
 	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
