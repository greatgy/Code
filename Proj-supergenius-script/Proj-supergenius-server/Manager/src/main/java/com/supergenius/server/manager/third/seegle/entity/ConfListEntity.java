package com.supergenius.server.manager.third.seegle.entity;

import java.io.Serializable;

/**
 * 会议室列表
 * @author chenminchang
 * @createtime 2016-9-9上午10:46:44
 */
public class ConfListEntity implements Serializable {

	private static final long serialVersionUID = 2835579970626784903L;
	private String confname;
	private String cid;
	private String beginTime;
	private String endTime;
	private String maxconfuser;

	public String getConfname() {
		return confname;
	}

	public void setConfname(String confname) {
		this.confname = confname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMaxconfuser() {
		return maxconfuser;
	}

	public void setMaxconfuser(String maxconfuser) {
		this.maxconfuser = maxconfuser;
	}

	@Override
	public String toString() {
		return "ConfListEntity [confname=" + confname + ", cid=" + cid + ", beginTime=" + beginTime + ", endTime=" + endTime + ", maxconfuser=" + maxconfuser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((confname == null) ? 0 : confname.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((maxconfuser == null) ? 0 : maxconfuser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfListEntity other = (ConfListEntity) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (confname == null) {
			if (other.confname != null)
				return false;
		} else if (!confname.equals(other.confname))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (maxconfuser == null) {
			if (other.maxconfuser != null)
				return false;
		} else if (!maxconfuser.equals(other.maxconfuser))
			return false;
		return true;
	}

}
