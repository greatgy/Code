package com.supergenius.xo.moral.entity;

import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EExam;
import com.supergenius.xo.moral.enums.EExamState;

/**
 * 考试(exam)实体类
 * 
 * @author LiJiacheng
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Exam extends BaseEntity {

	private static final long serialVersionUID = -7244309697497588895L;
	@Json(strategy = Json.webStrategy)
	private String useruid;// 会员uid
	@Json(strategy = Json.webStrategy)
	private String username;// 会员名字
	private EExam type;// 考试、模拟练习
	@Json(strategy = Json.webStrategy)
	private List<String> materialqstuid;// 练习时--材料题uid，材料的uid
	private EChapter chapter;// 章节
	private List<String> questionuids;// 题目的uids
	private List<String> answeruids;// 结果uids
	@Json(strategy = Json.webStrategy)
	private List<String> correctansweruids;// 正确答案uids
	@Json(strategy = Json.webStrategy)
	private Double score;// 分数
	@Json(strategy = Json.webStrategy)
	private DateTime begintime;// 开始时间
	@Json(strategy = Json.webStrategy)
	private DateTime finishtime;// 结束时间
	@Json(strategy = Json.webStrategy)
	private EExamState state;// 状态

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getUsername() {
		return username;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setUsername(String username) {
		this.username = username;
	}

	public EExam getType() {
		return type;
	}

	public void setType(EExam type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTypeName() {
		return EExam.getName(type, Locale.CHINA);
	}
	
	public List<String> getMaterialqstuid() {
		return materialqstuid;
	}

	public void setMaterialqstuid(List<String> materialqstuid) {
		this.materialqstuid = materialqstuid;
	}
	
	public EChapter getChapter() {
		return chapter;
	}

	public void setChapter(EChapter chapter) {
		this.chapter = chapter;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getChapterName() {
		return EChapter.getName(chapter, Locale.CHINA);
	}

	public List<String> getQuestionuids() {
		return questionuids;
	}

	public void setQuestionuids(List<String> questionuids) {
		this.questionuids = questionuids;
	}
	
	public List<String> getAnsweruids() {
		return answeruids;
	}

	public void setAnsweruids(List<String> answeruids) {
		this.answeruids = answeruids;
	}

	public List<String> getCorrectansweruids() {
		return correctansweruids;
	}

	public void setCorrectansweruids(List<String> correctansweruids) {
		this.correctansweruids = correctansweruids;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public DateTime getBegintime() {
		return begintime;
	}

	public void setBegintime(DateTime begintime) {
		this.begintime = begintime;
	}
	
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getBegintimeStr() {
		return getBegintime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}

	public DateTime getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(DateTime finishtime) {
		this.finishtime = finishtime;
	}
	
	public EExamState getState() {
		return state;
	}

	public void setState(EExamState state) {
		this.state = state;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getStateName() {
		return EExamState.getName(state, Locale.CHINA);
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getFinishtimeStr() {
		return getFinishtime() == null ? null : getFinishtime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
}
