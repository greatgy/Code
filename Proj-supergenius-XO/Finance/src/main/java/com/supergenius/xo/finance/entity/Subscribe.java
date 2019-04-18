package com.supergenius.xo.finance.entity;

import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.finance.enums.EFollow;
import com.supergenius.xo.user.entity.User;

/**
 * 订阅实体类
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:41:43
 */
public class Subscribe extends BaseEntity {
	
	private static final long serialVersionUID = -6184355713161576884L;
	private String useruid; // 订阅者id
	private EFollow follow; // 订阅状态
	private String refuseruid;

	private User refuser;
	private int articlecount;
	
	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public EFollow getFollow() {
		return follow;
	}

	public void setFollow(EFollow follow) {
		this.follow = follow;
	}

	public String getRefuseruid() {
		return refuseruid;
	}

	public void setRefuseruid(String refuseruid) {
		this.refuseruid = refuseruid;
	}

	public User getRefuser() {
		return refuser;
	}

	public void setRefuser(User refuser) {
		this.refuser = refuser;
	}

	public int getArticlecount() {
		return articlecount;
	}

	public void setArticlecount(int articlecount) {
		this.articlecount = articlecount;
	}

}
