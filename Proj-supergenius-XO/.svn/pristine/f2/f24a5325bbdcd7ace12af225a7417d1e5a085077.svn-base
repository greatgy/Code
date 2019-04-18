package com.supergenius.xo.official.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.model.base.enums.EStatus;

/**
 * 公共基类
 * 
 * @author LiuXiaoke
 *
 */
@Deprecated
public abstract class BaseEntity implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Json(strategy = Json.allStrategy)
	protected String uid;// 唯一ID
	@Json(strategy = Json.allStrategy)
	protected EStatus status = EStatus.enable;
	@Json(strategy = Json.allStrategy)
	protected DateTime createtime;// 创建时间
	protected DateTime updatetime;// 更新时间

	public BaseEntity() {
	}

	public BaseEntity(String uid, DateTime createtime, DateTime updatetime) {
		this.setUid(uid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}

	@ObjectId
	@JsonProperty("_id")
	public String getUid() {
		if (StringUtils.isEmpty(this.uid)) {
			this.uid = GlobalUtil.getUUID();
		}
		return this.uid;
	}
	
	@ObjectId
	@JsonProperty("_id")
	public void setUid(String uid) {
		this.uid = uid;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}
/*
	@Json(strategy = Json.allStrategy)
	public String getStatusName() {
		return EStatus.getName(this.status, Locale.CHINA);
	}

	public boolean getIsInit() {
		return this.status == EStatus.init;
	}

	public boolean getIsEnable() {
		return this.status == EStatus.enable;
	}

	public boolean getIsDisable() {
		return this.status == EStatus.disable;
	}

	public boolean getIsDeleted() {
		return this.status == EStatus.deleted;
	}

	public boolean getIsStart() {
		return this.status == EStatus.start;
	}

	public boolean getIsWait() {
		return this.status == EStatus.wait;
	}

	public boolean getIsEnd() {
		return this.status == EStatus.end;
	}
*/
	public DateTime getCreatetime() {
		if (createtime == null) {
			createtime = new DateTime(DateTimeZone.forOffsetHours(8));
		}
		return createtime.toDateTime(DateTimeZone.forOffsetHours(8));
	}
/*
	@Json(strategy = Json.allStrategy)
	public String getCreatetimeStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}

	public String getCreateDateStr() {
		return getCreatetime().toString(DateUtil.FORMAT_DATE_CHINA);
	}*/

	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}

	public DateTime getUpdatetime() {
		if (updatetime != null) {
			return updatetime.withZone(DateTimeZone.forOffsetHours(8));
		}
		return updatetime;
	}
/*
	@Json(strategy = Json.allStrategy)
	public String getUpdatetimeStr() {
		return getUpdatetime() == null ? null : getUpdatetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
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
