package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 证书类型枚举类
 * @author XieMing
 * @date 2016-7-17 下午2:15:58
 */
public enum ECertificate {
	
	judge("0"),//普通裁判
	specialJudge("1"),//特批裁判
	inviteJudge("2"),//特聘裁判
	specificJudge("3"),//专职裁判
	expert("4"),//专家
	specialExpert("5"),//特批专家
	inviteExpert("6"),//特聘专家
	specificExpert("7"),//专职专家
	seniorExpert("8"),//高级专家
	superExpert("9"),//特级专家
	RMBA("10"),//RMBA
	SMBA("11"),//SMBA
	TMBA("12");//TMBA

	private final String value;

	private ECertificate(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECertificate get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECertificate get(String str) {
		for (ECertificate e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static ECertificate getByName(String str) {
		for (ECertificate e : values()) {
			if (e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 得到有关裁判的职位
	 * @return
	 * @author liubin
	 * @createtime 2016-9-13上午11:47:08
	 * @return List<ECertificate>
	 */
	public static List<ECertificate> getAboutJudgeDegree() {
		List<ECertificate> list = new ArrayList<ECertificate>();
		list.add(ECertificate.RMBA);
		list.add(ECertificate.TMBA);
		list.add(ECertificate.SMBA);
		return list;
	}
	
	/**
	 * 得到和裁判相关的职位map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:24:29
	 * @return Map<String,String>
	 */
	public static Map<String, String> getAboutJudgeDegreeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ECertificate.RMBA.toString(), ECertificate.RMBA.getName());
		map.put(ECertificate.TMBA.toString(), ECertificate.TMBA.getName());
		map.put(ECertificate.SMBA.toString(), ECertificate.SMBA.getName());
		return map;
	}
	
	/**
	 * 得到关于后台查询相关的证书类型
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午4:55:29
	 * @return Map<String,String>
	 */
	public static Map<String, String> getCertificatesForSearch() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ECertificate.judge.toString(), ECertificate.judge.getName());
		map.put(ECertificate.expert.toString(), ECertificate.expert.getName());
		map.put(ECertificate.seniorExpert.toString(), ECertificate.seniorExpert.getName());
		map.put(ECertificate.specialExpert.toString(), ECertificate.specialExpert.getName());
		map.put(ECertificate.RMBA.toString(), ECertificate.RMBA.getName());
		map.put(ECertificate.TMBA.toString(), ECertificate.TMBA.getName());
		map.put(ECertificate.SMBA.toString(), ECertificate.SMBA.getName());
		return map;
	}
}
