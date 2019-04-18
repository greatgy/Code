package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;

/**
 * 用户统计实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午12:39:39
 */
public class UserStatics extends BaseEntity {

	private static final long serialVersionUID = -6234937779922322264L;
	private String useruid;// 用户uid
	private int pkcount;// 我的挑战数
	private int watchpkcount;// 我的观战数
	private int replycount;// 我的答辩数
	private int judgecount;// 作为裁判数
	private int expertcount;// 作为专家数
	private int visitorcount;// 个人主页被多少人看过

	public UserStatics() {
		super();
	}
	
	public UserStatics(String useruid, int pkcount, int watchpkcount, int replycount, int judgecount, int expertcount, int visitorcount) {
		super();
		this.useruid = useruid;
		this.pkcount = pkcount;
		this.watchpkcount = watchpkcount;
		this.replycount = replycount;
		this.judgecount = judgecount;
		this.expertcount = expertcount;
		this.visitorcount = visitorcount;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getPkcount() {
		return pkcount;
	}

	public void setPkcount(int pkcount) {
		this.pkcount = pkcount;
	}

	public int getWatchpkcount() {
		return watchpkcount;
	}

	public void setWatchpkcount(int watchpkcount) {
		this.watchpkcount = watchpkcount;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public int getJudgecount() {
		return judgecount;
	}

	public void setJudgecount(int judgecount) {
		this.judgecount = judgecount;
	}

	public int getExpertcount() {
		return expertcount;
	}

	public void setExpertcount(int expertcount) {
		this.expertcount = expertcount;
	}

	public int getVisitorcount() {
		return visitorcount;
	}

	public void setVisitorcount(int visitorcount) {
		this.visitorcount = visitorcount;
	}
}
