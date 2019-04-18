package com.supergenius.xo.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 游戏项目专用的com.sudoku.xo.base.BaseEntity, 与com.genius.model.base.entity.BaseEntity的区别是: 本BaseEntity的createtime和updatetime的类型为java.util.Date类型。
 * 
 * @author LiJiacheng
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;// 默认ID
	protected int oid;// 自增主键
	@Json(strategy = Json.allStrategy)
	protected String uid;// 唯一ID
	protected String tid;// 时间ID：120707235955
	@Json(strategy = Json.allStrategy)
	protected EGameStatus status = EGameStatus.enable;
	@Json(strategy = Json.allStrategy)
	protected Date createtime;// 创建时间
	@Json(strategy = Json.allStrategy)
	protected Date updatetime;// 更新时间

	public BaseEntity() {
		this.uid = new ObjectId().toString();
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 */
	public BaseEntity(String uid, Date createtime, Date updatetime) {
		this.setUid(uid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 */
	public BaseEntity(int oid, Date createtime, Date updatetime) {
		this.setOid(oid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}

	/**
	 * @return the id
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
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
	 * @param id
	 *            the id to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the oid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public int getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setOid(int oid) {
		this.oid = oid;
	}

	/**
	 * @return the uid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUid() {
		if (StringUtils.isEmpty(this.uid)) {
			this.uid = GlobalUtil.getUUID();
			return this.uid;
		}
		return this.uid;
	}

	/**
	 * @return the uid
	 */
	@Maps(strategy = Maps.allStrategy, alias = "_id", isRaw = true)
	public ObjectId getUidForDB() {
		if (StringUtils.isEmpty(this.uid)) {
			return new ObjectId();
		}
		return new ObjectId(this.uid);
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@Maps(strategy = Maps.allStrategy, alias = "_id", isRaw = true)
	public void setUidForDB(ObjectId uid) {
		this.uid = uid.toString();
	}

	/**
	 * @return the tid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getTid() {
		if (StringUtils.isEmpty(tid)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATE_TID);
			tid = sdf.format(new Date());
		}
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setTid(String tid) {
		this.tid = tid;
	}

	@Maps(strategy = Maps.allStrategy)
	public EGameStatus getStatus() {
		return status;
	}

	@Maps(strategy = Maps.allStrategy)
	public void setStatus(EGameStatus status) {
		this.status = status;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getStatusName() {
		return EGameStatus.getName(this.status, Locale.CHINA);
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsInit() {
		return this.status == EGameStatus.init;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsEnable() {
		return this.status == EGameStatus.enable;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsDisable() {
		return this.status == EGameStatus.disable;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsDeleted() {
		return this.status == EGameStatus.deleted;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsStart() {
		return this.status == EGameStatus.start;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsWait() {
		return this.status == EGameStatus.wait;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsEnd() {
		return this.status == EGameStatus.end;
	}

	/**
	 * @return the createtime
	 */
	@Maps(strategy = Maps.allStrategy)
	public Date getCreatetime() {
		return createtime == null ? new Date() : createtime;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getCreatetimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATETIME_CHINA);
		return sdf.format(getCreatetime());
	}

	@Maps(strategy = Maps.allStrategy)
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updateTime
	 */
	@Maps(strategy = Maps.allStrategy)
	public Date getUpdatetime() {
		return updatetime == null ? new Date() : updatetime;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUpdatetimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATETIME_CHINA);
		return getUpdatetime() == null ? null : sdf.format(getUpdatetime());
	}

	@Maps(strategy = Maps.allStrategy)
	public void setUpdatetime(Date updateTime) {
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
