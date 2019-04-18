package com.supergenius.xo.life.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;
import com.supergenius.xo.life.enums.ECatalogue;

/**
 * 目录枚举类
 * 
 * @author YangGuang
 * @date 2018年5月14日17:48:03
 */
public enum ECatalogue {
	
	index(Long.parseLong("000000001", 2)),//首页1
	position(Long.parseLong("000000010", 2)),//人生定位2
	dreaming(Long.parseLong("000000100", 2)),//疯狂理想4
	middle(Long.parseLong("000001000", 2)),//梦之初心8
	high(Long.parseLong("000010000", 2)),//放飞梦想16
	college(Long.parseLong("000100000", 2)),//追梦时光32
	senior(Long.parseLong("001000000", 2)),//人生守望64
	answer(Long.parseLong("010000000", 2)),//人生问答128
	idol(Long.parseLong("100000000", 2)),//我的偶像256
	member(Long.parseLong("1000000000", 2)),//会员通道512
	purpose(Long.parseLong("10000000000", 2)),//人生宗旨1024
	Location(Long.parseLong("100000000000", 2)),//人生定位2048
	path(Long.parseLong("1000000000000", 2)),//人生路径4096
	duty(Long.parseLong("10000000000000", 2)),//家长责任8192
	course(Long.parseLong("100000000000000", 2)),//课程推荐16384
	test(Long.parseLong("1000000000000000", 2)),//效果检验32768
	ideal(Long.parseLong("10000000000000000", 2)),//我的理想65536
	abroadguide(Long.parseLong("100000000000000000", 2)),//留学指南131072
	communication(Long.parseLong("1000000000000000000", 2)),//交流对话262144
	difference(Long.parseLong("10000000000000000000", 2)),//别出心裁524288
	thinking(Long.parseLong("100000000000000000000", 2)),//思维拓展1048576
	perspectives(Long.parseLong("1000000000000000000000", 2)),//课程见解2097152
	material(Long.parseLong("10000000000000000000000", 2)),//资料交流4194304
	major(Long.parseLong("100000000000000000000000", 2)),//专业匹配8388608
	university(Long.parseLong("1000000000000000000000000", 2)),//大学优选16777216
	abroadtrend(Long.parseLong("10000000000000000000000000", 2)),//留学风向33554432
	insight(Long.parseLong("100000000000000000000000000", 2)),//火眼金睛67108864
	fraud(Long.parseLong("1000000000000000000000000000", 2)),//识别骗局134217728
	history(Long.parseLong("10000000000000000000000000000", 2)),//水煮历史268435456
	technology(Long.parseLong("100000000000000000000000000000", 2)),//去伪存真536870912
	design(Long.parseLong("1000000000000000000000000000000", 2)),//人生设计1073741824
	stage(Long.parseLong("10000000000000000000000000000000", 2)),//我的舞台2147483648
	abroadworld(Long.parseLong("100000000000000000000000000000000", 2)),//留学天地4294967296
	dream(Long.parseLong("1000000000000000000000000000000000", 2)),//梦想成真8589934592
	watching(Long.parseLong("10000000000000000000000000000000000", 2)),//守望初心17179869184
	changing(Long.parseLong("100000000000000000000000000000000000", 2)),//理想变迁34359738368
	partner(Long.parseLong("1000000000000000000000000000000000000", 2)),//携手前行68719476736
	recommendation(Long.parseLong("10000000000000000000000000000000000000", 2)),//人生推荐137438953472
	travel(Long.parseLong("100000000000000000000000000000000000000", 2)),//走万里路274877906944
	side(Long.parseLong("1000000000000000000000000000000000000000", 2)),//我身边的偶像549755813888
	worship(Long.parseLong("10000000000000000000000000000000000000000", 2)),//我崇拜的偶像1099511627776
	fantasy(Long.parseLong("100000000000000000000000000000000000000000", 2));//我幻想的偶像2199023255552
	
	private final long value;

	private ECatalogue(long v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ECatalogue get(long v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogue get(String str) {
		for (ECatalogue e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static String getName(ECatalogue e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	/**
	 * @param education2
	 * @return
	 */
	public long plus(ECatalogue e) {
		return value | Long.parseLong(e.toString());
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param int type
	 * @return
	 */
	public boolean ismatch(long type) {
		return value  == (value  & type);
	}
	/**
	 * @param education2
	 * @return
	 */
	public long minus(ECatalogue e) {
		return value  ^ Long.parseLong(e.toString());
	}

	/**
	 * 得到最先匹配的类型
	 * 
	 * @param type
	 */
	public static List<ECatalogue> getMatch(long type) {
		List<ECatalogue> list = new ArrayList<>();
		for (ECatalogue item : ECatalogue.values()) {
			if (item.ismatch(type)) {
				list.add(item);
			}
		}
		return list;
	}
}
