package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.manager.enums.EStudentLevel;

/** 
 * 挑战的裁判表
 * @author chenminchang
 * @date 2016-7-18 下午2:19:22 
 */
public class PKJudgement extends BaseEntity {

	private static final long serialVersionUID = 6334106833139924216L;
	private String pkscheduleuid; //挑战表UID
	@Json(strategy=Json.webStrategy)
	private String useruid; //操作人UID
	private String judgementuid; //裁判对应的userUID
	private EStudentLevel pklevel; //挑战级别
	private String desc; //备注/原因
	private int sortorder; //排序从小到大
	private boolean isabsent; //是否缺席
	//stauts状态       init:初始 start:挑战已支付 wait:挑战时间确定，发出裁判邀请，等待裁判处理 end:已过期 disable:拒绝 enable:接受 delete:已失效 
	private String username;//操作人姓名
	
	public PKJudgement() {
		super();
	}
	
	public PKJudgement(String pkscheduleuid, String useruid, String judgementuid, EStudentLevel pklevel, EStatus status) {
		super();
		this.pkscheduleuid = pkscheduleuid;
		this.useruid = useruid;
		this.judgementuid = judgementuid;
		this.pklevel = pklevel;
		this.status = status;
	}
	
	@Json(strategy=Json.webStrategy)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPkscheduleuid() {
		return pkscheduleuid;
	}
	
	public void setPkscheduleuid(String pkscheduleuid) {
		this.pkscheduleuid = pkscheduleuid;
	}
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getJudgementuid() {
		return judgementuid;
	}
	
	public void setJudgementuid(String judgementuid) {
		this.judgementuid = judgementuid;
	}
	
	public EStudentLevel getPklevel() {
		return pklevel;
	}
	
	public void setPklevel(EStudentLevel pklevel) {
		this.pklevel = pklevel;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getSortorder() {
		return sortorder;
	}
	
	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}
	
	public boolean isIsabsent() {
		return isabsent;
	}
	
	public void setIsabsent(boolean isabsent) {
		this.isabsent = isabsent;
	}
}