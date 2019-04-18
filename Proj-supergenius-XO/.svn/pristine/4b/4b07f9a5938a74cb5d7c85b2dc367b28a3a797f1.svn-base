package com.supergenius.xo.tpi.entity;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.EUserType;

/**
 * 评分详情表
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class ScoreDetail implements Serializable {

	private static final long serialVersionUID = -2675650687601790704L;
	private String uid;
	private String fromuid;
	private EUserType fromusertype;
	private String touid;
	private EChannel tochannel;
	private int score;
	private DateTime createtime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}

	public EUserType getFromusertype() {
		return fromusertype;
	}

	public void setFromusertype(EUserType fromusertype) {
		this.fromusertype = fromusertype;
	}

	public String getTouid() {
		return touid;
	}

	public void setTouid(String touid) {
		this.touid = touid;
	}

	public EChannel getTochannel() {
		return tochannel;
	}

	public void setTochannel(EChannel tochannel) {
		this.tochannel = tochannel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public DateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}
}
