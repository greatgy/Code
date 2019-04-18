package com.supergenius.xo.enterpriser.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 置顶枚举类
 * 
 * @author 杨光
 * @date 2017年9月26日17:58:56
 */
public enum ECatalogue {
	
	index("1"),//首页
	purchase("2"),//引资购商
	made("3"),//中国制造提升
	crossmerger("4"),//跨境并购
	nationmerger("5"),//国际并购
	fund("6"),//并购产业基金
	cooperation("7"),//互助合作平台
	enterpriser("8"),//企业家培训
	book("9"),//商城
	member("10"),//会员通道
	purchaseconcept("21"),//引资购商概念
	purchaseFurm("22"),//引资购商论坛
	purchaseLecture("23"),//引资购商讲座
	madedilemma("31"),//中国制造面临困境
	madeforum("32"),//中国制造论坛
	madedown("33"),//引资购商走向没落
	madepurchaseforum("34"),//招商引资论坛
	madehelp("35"),//引资购商助力中国制造提升
	madesupersede("36"),//引资购商替代招商引资
	information("61"),//资讯
	example("62"),//典型案例
	statute("63");//法规
	
	private final String value;

	private ECatalogue(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECatalogue get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogue get(String str) {
		for (ECatalogue e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
