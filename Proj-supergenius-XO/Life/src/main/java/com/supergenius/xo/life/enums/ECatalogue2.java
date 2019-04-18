package com.supergenius.xo.life.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;
import com.supergenius.xo.life.enums.ECatalogue2;

/**
 * 目录枚举类
 * 
 * @author YangGuang
 * @date 2018年5月14日17:48:03
 */
public enum ECatalogue2 {
	
	index(Integer.valueOf("000000001", 2)),//首页1
	position(Integer.valueOf("000000010", 2)),//人生定位2
	dreaming(Integer.valueOf("000000100", 2)),//疯狂理想4
	middle(Integer.valueOf("000001000", 2)),//梦之初心8
	high(Integer.valueOf("000010000", 2)),//放飞梦想16
	college(Integer.valueOf("000100000", 2)),//追梦时光32
	senior(Integer.valueOf("001000000", 2)),//人生守望64
	answer(Integer.valueOf("010000000", 2)),//人生问答128
	idol(Integer.valueOf("100000000", 2)),//我的偶像256
	member(Integer.valueOf("1000000000", 2)),//会员通道512
	purpose(Integer.valueOf("10000000000", 2)),//人生宗旨1024
	Location(Integer.valueOf("100000000000", 2)),//人生定位2048
	path(Integer.valueOf("1000000000000", 2)),//人生路径4096
	duty(Integer.valueOf("10000000000000", 2)),//家长责任8192
	course(Integer.valueOf("100000000000000", 2)),//课程推荐16384
	test(Integer.valueOf("1000000000000000", 2)),//效果检验32768
	ideal(Integer.valueOf("10000000000000000", 2)),//我的理想65536
	abroadguide(Integer.valueOf("100000000000000000", 2)),//留学指南131072
	communication(Integer.valueOf("1000000000000000000", 2)),//交流对话262144
	difference(Integer.valueOf("10000000000000000000", 2)),//别出心裁524288
	thinking(Integer.valueOf("100000000000000000000", 2)),//思维拓展1048576
	perspectives(Integer.valueOf("1000000000000000000000", 2)),//课程见解2097152
	material(Integer.valueOf("10000000000000000000000", 2)),//资料交流4194304
	major(Integer.valueOf("100000000000000000000000", 2)),//专业匹配8388608
	university(Integer.valueOf("1000000000000000000000000", 2)),//大学优选16777216
	abroadtrend(Integer.valueOf("10000000000000000000000000", 2)),//留学风向33554432
	insight(Integer.valueOf("100000000000000000000000000", 2)),//火眼金睛67108864
	fraud(Integer.valueOf("1000000000000000000000000000", 2)),//识别骗局134217728
	history(Integer.valueOf("10000000000000000000000000000", 2)),//水煮历史268435456
	technology(Integer.valueOf("100000000000000000000000000000", 2)),//去伪存真536870912
	design(Integer.valueOf("1000000000000000000000000000000", 2)),//人生设计1073741824
	stage(Integer.valueOf("10000000000000000000000000000000", 2)),//我的舞台2147483648
	abroadworld(Integer.valueOf("100000000000000000000000000000000", 2)),//留学天地4294967296
	dream(Integer.valueOf("1000000000000000000000000000000000", 2)),//梦想成真8589934592
	watching(Integer.valueOf("10000000000000000000000000000000000", 2)),//守望初心17179869184
	changing(Integer.valueOf("100000000000000000000000000000000000", 2)),//理想变迁34359738368
	partner(Integer.valueOf("1000000000000000000000000000000000000", 2)),//携手前行68719476736
	recommendation(Integer.valueOf("10000000000000000000000000000000000000", 2)),//人生推荐137438953472
	travel(Integer.valueOf("100000000000000000000000000000000000000", 2)),//走万里路274877906944
	side(Integer.valueOf("1000000000000000000000000000000000000000", 2)),//我身边的偶像549755813888
	worship(Integer.valueOf("10000000000000000000000000000000000000000", 2)),//我崇拜的偶像1099511627776
	fantasy(Integer.valueOf("100000000000000000000000000000000000000000", 2));//我幻想的偶像2199023255552
	
	private final int value;

	private ECatalogue2(Integer v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ECatalogue2 get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogue2 get(String str) {
		for (ECatalogue2 e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static String getName(ECatalogue2 e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	/**
	 * @param education2
	 * @return
	 */
	public Integer plus(ECatalogue2 e) {
		return value | Integer.valueOf(e.toString());
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param int type
	 * @return
	 */
	public boolean ismatch(int type) {
		return value  == (value  & type);
	}
	/**
	 * @param education2
	 * @return
	 */
	public int minus(ECatalogue2 e) {
		return value  ^ Integer.valueOf(e.toString());
	}

	/**
	 * 得到最先匹配的类型
	 * 
	 * @param type
	 */
	public static List<ECatalogue2> getMatch(int type) {
		List<ECatalogue2> list = new ArrayList<>();
		for (ECatalogue2 item : ECatalogue2.values()) {
			if (item.ismatch(type)) {
				list.add(item);
			}
		}
		return list;
	}
}
