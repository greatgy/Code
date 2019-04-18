package com.genius.model.base.entity;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;

/**
 * @author Architect.bian
 *
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String id;//默认ID
	protected int oid;//自增主键
	@Json(strategy = Json.allStrategy)
	protected String uid;//唯一ID
	protected String tid;//时间ID：120707235955
	@Json(strategy = Json.allStrategy)
	protected EStatus status = EStatus.enable;
	@Json(strategy = Json.allStrategy)
	protected DateTime createtime;//创建时间
	@Json(strategy = Json.allStrategy)
	protected DateTime updatetime;//更新时间
	
	public BaseEntity(){}
	
	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 */
	public BaseEntity(String uid, DateTime createtime, DateTime updatetime) {
		this.setUid(uid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}
	
	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 */
	public BaseEntity(int oid, DateTime createtime, DateTime updatetime) {
		this.setOid(oid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}
	
	/**
	 * @return the id
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getId() {
		if (StringUtils.isNotEmpty(id)) {
			return id;
		} else if (oid > 0) {
			return String.valueOf(oid);
		} else {
			return this.getUid();
		}
	}
	/**
	 * @param id the id to set
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the oid
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setOid(int oid) {
		this.oid = oid;
	}
	/**
	 * @return the uid
	 */
	@Maps(strategy=Maps.dbStrategy, alias="_id")
	public String getUid() {
		if (StringUtils.isEmpty(this.uid)) {
			this.uid = GlobalUtil.getUUID();
			return this.uid;
		}
		return this.uid;
	}
	/**
	 * @param uid the uid to set
	 */
	@Maps(strategy=Maps.dbStrategy, alias="_id")
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the tid
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTid() {
		if (StringUtils.isEmpty(tid)) {
			tid = (new DateTime()).toString(DateUtil.FORMAT_DATE_TID);
		}
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	@Maps(strategy=Maps.allStrategy)
	public EStatus getStatus() {
		return status;
	}
	@Maps(strategy=Maps.allStrategy)
	public void setStatus(EStatus status) {
		this.status = status;
	}
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getStatusName() {
		return EStatus.getName(this.status, Locale.CHINA);
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsInit(){
		return this.status == EStatus.init;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsEnable(){
		return this.status == EStatus.enable;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsDisable(){
		return this.status == EStatus.disable;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsDeleted(){
		return this.status == EStatus.deleted;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsStart(){
		return this.status == EStatus.start;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsWait(){
		return this.status == EStatus.wait;
	}
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsEnd(){
		return this.status == EStatus.end;
	}
	
	/**
	 * @return the createtime
	 */
	@Maps(strategy=Maps.allStrategy)
	public DateTime getCreatetime() {
		if (createtime == null) {
			createtime = new DateTime(DateTimeZone.forOffsetHours(8));
		}
		return createtime.toDateTime(DateTimeZone.forOffsetHours(8));
	}
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getCreatetimeStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getCreateDateStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATE_CHINA);
	}

	@Maps(strategy=Maps.allStrategy)
	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updateTime
	 */
	@Maps(strategy=Maps.allStrategy)
	public DateTime getUpdatetime() {
		if (updatetime != null) {
			return updatetime.withZone(DateTimeZone.forOffsetHours(8));
		}
		return updatetime;
	}
	
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getUpdatetimeStr() {
		return getUpdatetime() == null ? null : getUpdatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	@Maps(strategy=Maps.allStrategy)
	public void setUpdatetime(DateTime updateTime) {
		this.updatetime = updateTime;
	}
	
	public void clearForSitemap() {
		this.status = null;
		this.createtime = null;
	}
	
	@Override
	public int hashCode() {
		return StringUtils.isEmpty(uid) ? System.identityHashCode(this) : uid.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return this == null;
		} else if (this.hashCode() == o.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

}
