package com.supergenius.xo.finance.entity;

import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 收藏实体类
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:41:43
 */
public class Collect extends BaseEntity {

	private static final long serialVersionUID = -3074816787819005109L;
	private String useruid; // 用户uid
	private String refuid; // 相对应文章uid
	private int cid; // 模块id

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String reduid) {
		this.refuid = reduid;
	}
}
