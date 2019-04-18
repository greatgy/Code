package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.utils.MathUtil;
import com.genius.model.base.entity.BaseEntity;

/**
 * 用户统计
 * 
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class UserStatistics extends BaseEntity {

	private static final long serialVersionUID = -476239380592139584L;
	@Json(strategy = Json.webStrategy)
	private String useruid;// 会员uid
	private int countupload;// 上传文档数量
	private int countdl;// 下载数量
	private int countarticle;// 发帖数量
	private int countfavorarticle;// 收藏帖数量
	private int countcorrectqst;// 答对的题目数量
	private int counterrorqst;// 答错的题目数量
	@Json(strategy = Json.webStrategy)
	private int score;// 学员积分
	@Json(strategy = Json.webStrategy)
	private int countdaysign;// 连续签到的天数
	private int signdays;// 连续签到的天数
	@Json(strategy = Json.webStrategy)
	private DateTime examtime;// 剩余考试时间

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getCountupload() {
		return countupload;
	}

	public void setCountupload(int countupload) {
		this.countupload = countupload;
	}

	public int getCountdl() {
		return countdl;
	}

	public void setCountdl(int countdl) {
		this.countdl = countdl;
	}

	public int getCountarticle() {
		return countarticle;
	}

	public void setCountarticle(int countarticle) {
		this.countarticle = countarticle;
	}

	public int getCountfavorarticle() {
		return countfavorarticle;
	}

	public void setCountfavorarticle(int countfavorarticle) {
		this.countfavorarticle = countfavorarticle;
	}

	public int getCountcorrectqst() {
		return countcorrectqst;
	}

	public void setCountcorrectqst(int countcorrectqst) {
		this.countcorrectqst = (int) MathUtil.add(this.countcorrectqst, countcorrectqst);
	}

	public int getCounterrorqst() {
		return counterrorqst;
	}

	public void setCounterrorqst(int counterrorqst) {
		this.counterrorqst = (int) MathUtil.add(this.counterrorqst, counterrorqst);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCountdaysign() {
		return countdaysign;
	}

	public void setCountdaysign(int countdaysign) {
		this.countdaysign = countdaysign;
	}

	public int getSigndays() {
		return signdays;
	}

	public void setSigndays(int signdays) {
		this.signdays = signdays;
	}

	public DateTime getExamtime() {
		return examtime;
	}

	public void setExamtime(DateTime examtime) {
		this.examtime = examtime;
	}

}
