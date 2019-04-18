package com.supergenius.xo.moral.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EScoreDetail;

/**
 *  积分明细
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class ScoreDetail extends BaseEntity{

	private static final long serialVersionUID = 1894442601398609454L;
	private String useruid;//会员uid
	private String adminid;//管理员id
	@Json(strategy = Json.webStrategy)
	private String adminname;//管理员name
	@Json(strategy = Json.webStrategy)
	private int beginscore;//变更前
	@Json(strategy = Json.webStrategy)
	private int finishscore;//变更后
	private EScoreDetail type;//积分项
	@Json(strategy = Json.webStrategy)
	private String desc;//修改理由
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	
	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
	public int getBeginscore() {
		return beginscore;
	}
	
	public void setBeginscore(int beginscore) {
		this.beginscore = beginscore;
	}
	
	public int getFinishscore() {
		return finishscore;
	}
	
	public void setFinishscore(int finishscore) {
		this.finishscore = finishscore;
	}

	public EScoreDetail getType() {
		return type;
	}

	public void setType(EScoreDetail type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
