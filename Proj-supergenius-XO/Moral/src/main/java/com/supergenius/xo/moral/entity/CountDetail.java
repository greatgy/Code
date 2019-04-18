package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.ECountDetail;

/**
 * 点赞、收藏、点击明细表
 * 
 * @author LiJiacheng
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class CountDetail extends BaseEntity {

	private static final long serialVersionUID = -8930934010059988726L;
	@Json(strategy = Json.webStrategy)
	private String fromuseruid;// 点赞或收藏人的uid
	@Json(strategy = Json.webStrategy)
	private String articleuid;// 帖子的uid
	@Json(strategy = Json.webStrategy)
	private int count;// 是否点赞、收藏或点击过
	@Json(strategy = Json.webStrategy)
	private ECountDetail type;// （收藏、点赞）

	public String getFromuseruid() {
		return fromuseruid;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}

	public String getArticleuid() {
		return articleuid;
	}

	public void setArticleuid(String articleuid) {
		this.articleuid = articleuid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ECountDetail getType() {
		return type;
	}

	public void setType(ECountDetail type) {
		this.type = type;
	}

	/**
	 * @param fromuseruid
	 * @param articleuid
	 * @param count
	 * @param type
	 * @author: LiJiacheng 2015-9-1 下午12:10:15
	 */
	public CountDetail(String fromuseruid, String articleuid, int count, ECountDetail type) {
		super();
		this.fromuseruid = fromuseruid;
		this.articleuid = articleuid;
		this.count = count;
		this.type = type;
	}

	/**
	 * 
	 * @author: LiJiacheng 2015-9-1 下午12:20:14
	 */
	public CountDetail() {
		super();
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:20:14
	 */
	public CountDetail(int oid, DateTime createtime, DateTime updatetime) {
		super(oid, createtime, updatetime);
	}

	/**
	 * @param uid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:20:14
	 */
	public CountDetail(String uid, DateTime createtime, DateTime updatetime) {
		super(uid, createtime, updatetime);
	}

}
