package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;

/**
 * 签到
 * 
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Sign extends BaseEntity {

	private static final long serialVersionUID = -536811186201923890L;
	@Json(strategy = Json.webStrategy)
	private String useruid;// 会员uid

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	/**
	 * @param useruid
	 * @author: LiJiacheng 2015-9-1 下午12:25:55
	 */
	public Sign(String useruid) {
		super();
		this.useruid = useruid;
	}

	/**
	 * 
	 * @author: LiJiacheng 2015-9-1 下午12:26:07
	 */
	public Sign() {
		super();
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:26:07
	 */
	public Sign(int oid, DateTime createtime, DateTime updatetime) {
		super(oid, createtime, updatetime);
	}

	/**
	 * @param uid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:26:07
	 */
	public Sign(String uid, DateTime createtime, DateTime updatetime) {
		super(uid, createtime, updatetime);
	}

}
