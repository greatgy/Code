package com.supergenius.xo.admin.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author architect.bian
 * 2014-6-24 上午11:55:24
 */
public enum EAdminLog {

	addTopic("0"),
	editTopic("1"),
	delTopic("2"),
	updateTopicStatus("3"),
	updateTopicStatusSucceed("4"),
	updateFinanceStatus("5"),
	updateFinanceStatusSucceed("6"),
	updateCommentsStatus("7"),
	updateCommentsStatusSucceed("8"),
	updateDebateStatus("9"),
	updateDebateStatusSucceed("10"),
	updateAdminerStatus("11"),
	updateAdminerStatusSucceed("12"),
	updateRoleStatus("13"),
	updateRoleStatusSucceed("14"),
	updateAppStudentStatus("15"),
	updateLectureStatus("16"),
	addLectureSemester("17"),
	updateManagerVideoStatus("18"),
	updateManagerVideoRecommend("19"),
	updateStudentLevel("20"),
	updateAppJudgeStage("21"),
	updateExpertStatus("22"),
	updateJudgeStatus("23"),
	updateAppExpertStage("24"),
	updateConferencePassword("25"),
	updateTicket("26"),
	updatePKScheduleStatus("27"),
	updateComplaintResult("28"),
	addInviteJudge("29"),
	addInviteExpert("30"),
	updateCertificateStatus("31"),
	updateManagerCommentStatus("32"),
	updateReplyStatus("33"),
	changeExpert("34"),
	updateParticepate("35"),
	updateAppCooperation("36"),
	deleteManagerComment("37"),
	updateManagerContent("38"),
	addQuestion("39"),
	deleteQuestion("40"),
	updateQuestion("41"),
	addRuler("39"),
	deleteRuler("40"),
	updateRuler("41"),
	addStatistics("39"),
	deleteStatistics("40"),
	updateStatistics("41"),
	addMusic("42"),
	deleteMusic("43"),
	updateMusic("44"),
	addStartupContent("45"),
	deleteStartupContent("46"),
	updateStartupContent("47"),
	addLabel("48"),
	updateLabelStatus("49"),
	updateLabel("50"),
	deleteStartupArticle("51"),
	addStartupArticle("52"),
	updateStartupArticle("53"),
	deleteStartupCatalogue("54"),
	addStartupCatalogue("55"),
	updateStartupCatalogue("56"),
	addAiContent("57"),
	deleteAiContent("58"),
	updateAiContent("59"),
	deleteAiComments("60"),
	deleteAiArticle("61"),
	updateAiArticle("62"),
	updateAiCatalogue("63"),
	addSolrContent("64"),
	deleteCareerComments("65"),
	deleteCareerTease("66"),
	deleteCareerAnswer("67"),
	updateCareerCatalogue("68"),
	updateCareerRuler("69"),
	addCareerArticle("70"),
	updateCareerArticle("71"),
	deleteCareerArticle("72"),
	addCareerProblem("73"),
	updateCareerProblem("74"),
	deleteCareerProblem("75"),
	addCareerContent("76"),
	deleteCareerContent("77"),
	updateCareerContent("78"),
	updateCareertease("79"),
	updateCareercomplainarea("80"),
	deleteFinanceComments("81"),
	addFinanceContent("82"),
	updateFinanceContent("83"),
	updateFinanceCatalogue("84"),
	deleteFinanceArticle("85"),
	updateFinanceArticle("85"),
	
	//顾雏军专栏
	deleteGupagePhoto("86"),
	addGupagePhoto("87"),
	updateGupagePhoto("88"),
	updateGupageVideo("89"),
	addGupageVideo("90"),
	deleteGupageVideo("91"),

	updateGupageCatalogue("92"),
	updateGupageArticle("95"), 
	updateGupageDebate("96"), 
	deleteGupageArticle("97"), 
	deleteGupageDebate("98"),
	deleteGupageComments("93"),
	addGupageContent("94"),
	updateGupageContent("95"),
	deleteGupagePager("96"),
	updateGupagePager("97"),
	//引资购商
	updateEnterpriserVideo("98"),
	addEnterpriserVideo("99"),
	deleteEnterpriserVideo("100"),
	updateEnterpriserCatalogue("101"), 
	deleteEnterpriserForum("102"),
	updateEnterpriserForum("103"),
	updateEnterpriserPhoto("104"),
	deleteEnterpriserComments("104"),
	addEnterpriserContent("105"), 
	updateEnterpriserContent("106"),
	updateEnterpriserArticle("107"),
	deleteEnterpriserArticle("108"),
	
	//天才人生
	addLifeAd("110"),
	updateLifeAd("111"),
	deleteLifeComments("112"),
	updateLifeProblem("113"),
	deleteLifeArticle("114"),
	updateLifeArticle("115"), 
	updateLifeAnswer("116"), 
	deleteLifeTopic("117"), 
	updateLifeTopic("118"),
	deleteLifeEssay("119"),
	deleteLifeVideo("120"),
	updateLifeCatalogue("121"),	
	deleteLifeBanner("122"),

	//职业经理人培训
	updateManagerNewsCatalogue("123"), 
	updateManagerNewsContent("124"),
	addManagerNewsContent("125"),
	deleteManagernewsArticle("126"),
	updateManagernewsArticle("127"),
	deleteManagernewsComments("128"),
	addManagerNewsAd("129"),
	updateManagerNewsAd("130"),
	//企业家培训
	deleteEntrepreneurArticle("131"),
	updateEntrepreneurArticle("132"),
	addEntrepreneurAd("133"),
	updateEntrepreneurCatalogue("134"),
	updateEntrepreneurAd("135"),

	//职业道德培训文章
	deleteMoralnewsArticle("136"),
	updateMoralnewsArticle("137"),

	deleteMoralnewsComments("138"),
	
	addMoralnewsAd("139"),
	updateMoralnewsAd("140"),
	deleteMoralnewsAd("141"),
	addMoralnewsAnnouncement("142"),
	updateMoralnewsAnnouncement("143"),
	deleteMoralnewsAnnouncement("144"),
	addMoralnewsCatalogue("145"),
	updateMoralnewsCatalogue("146");
	

	private final String value;

	private EAdminLog(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EAdminLog get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAdminLog get(String name) {
		for (EAdminLog e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
 		return EAdminLog.getName(this, Locale.CHINA);
	}
	
	public static String getName(EAdminLog e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
