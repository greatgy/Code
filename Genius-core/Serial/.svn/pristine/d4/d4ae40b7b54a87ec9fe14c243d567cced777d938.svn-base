package com.genius.core.serial.mock.testentity;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.serial.mock.testenums.EStatus;

/**
 * @author Architect.bian
 *
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;//默认ID
	private int oid;//自增主键
	private String uid;//唯一ID
	private String tid;//时间ID：120707235955
	@Json(strategy = Json.allStrategy)
	private EStatus status = EStatus.enable;
	private DateTime createtime;//创建时间
	private DateTime updatetime;//更新时间
	
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
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the oid
	 */
	public int getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(int oid) {
		this.oid = oid;
	}
	/**
	 * @return the uid
	 */
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
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the tid
	 */
	public String getTid() {
		if (StringUtils.isEmpty(tid)) {
			tid = (new DateTime()).toString(DateUtil.FORMAT_DATE_TID);
		}
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	public EStatus getStatus() {
		return status;
	}
	public void setStatus(EStatus status) {
		this.status = status;
	}
	@Json(strategy = Json.allStrategy)
	public String getStatusName() {
		return EStatus.getName(this.status, Locale.CHINA);
	}
	public boolean getIsInit(){
		return this.status == EStatus.init;
	}
	public boolean getIsEnable(){
		return this.status == EStatus.enable;
	}
	public boolean getIsDisable(){
		return this.status == EStatus.disable;
	}
	public boolean getIsDeleted(){
		return this.status == EStatus.deleted;
	}
	public boolean getIsStart(){
		return this.status == EStatus.start;
	}
	public boolean getIsWait(){
		return this.status == EStatus.wait;
	}
	public boolean getIsEnd(){
		return this.status == EStatus.end;
	}
	
	/**
	 * @return the createtime
	 */
	public DateTime getCreatetime() {
		if (createtime == null) {
			createtime = new DateTime(DateTimeZone.forOffsetHours(8));
		}
		return createtime.toDateTime(DateTimeZone.forOffsetHours(8));
	}
	@Json(strategy = Json.allStrategy)
	public String getCreatetimeStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	public String getCreateDateStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATE_CHINA);
	}

	/**
	 * @param createtime the createtime to set
	 */
	@DateTimeFormat(iso=ISO.DATE)
	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updateTime
	 */
	public DateTime getUpdatetime() {
		if (updatetime != null) {
			return updatetime.withZone(DateTimeZone.forOffsetHours(8));
		}
		return updatetime;
	}
	@Json(strategy = Json.allStrategy)
	public String getUpdatetimeStr() {
		return getUpdatetime() == null ? null : getUpdatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	/**
	 * @param updateTime the updateTime to set
	 */
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
