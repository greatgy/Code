package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 积分明细枚举类型
 * @author YuYingJie
 */
public enum EScoreDetail {

	sign("0"),//签到
	article("1"),//发帖
	comment("2"),//回复帖子
	praise("3"),//帖子或留言被赞
	upload("4"),//上传资料
	download("5"),//资料被下载
	istop("6"),//帖子置顶
	delArticle("7"),//帖子删除
	delComment("8"),//留言删除
	passExam("9"),//通过考试
	adminModify("10"),//管理员修改
	userdocrmcase("11");//上传资料移入案例库
	
	private final String value;
	
	private EScoreDetail(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}
	
	public static EScoreDetail get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EScoreDetail get(String str) {
		for (EScoreDetail e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EScoreDetail.getName(EScoreDetail.get(value), Locale.CHINA);
	}

	public static String getName(EScoreDetail e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
