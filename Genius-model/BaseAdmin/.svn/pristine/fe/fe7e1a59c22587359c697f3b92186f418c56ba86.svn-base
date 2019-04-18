package com.genius.model.baseadmin.entity;

import com.genius.model.base.entity.BaseEntity;
import com.genius.model.base.enums.EStatus;

/**
 * 状态明细
 * 
 * @author architect.bian
 * @createtime 2014-7-27 下午4:15:04
 */
public class StatusLog extends BaseEntity {
	
	private static final long serialVersionUID = 7614957505747199951L;
	
	private String fromuid;
	private String adminuid;
	private String name;
	private String desc;
	private String channel;
	private int statusfrom;
	private int statusto;
	
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * @return the fromuid
	 */
	public String getFromuid() {
		return fromuid;
	}
	/**
	 * @param fromuid the fromuid to set
	 */
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
	/**
	 * @return the adminuid
	 */
	public String getAdminuid() {
		return adminuid;
	}
	/**
	 * @param adminuid the adminuid to set
	 */
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the statusfrom
	 */
	public int getStatusfrom() {
		return statusfrom;
	}
	/**
	 * @param statusfrom the statusfrom to set
	 */
	public void setStatusfrom(int statusfrom) {
		this.statusfrom = statusfrom;
	}
	/**
	 * @return the statusto
	 */
	public int getStatusto() {
		return statusto;
	}
	/**
	 * @param statusto the statusto to set
	 */
	public void setStatusto(int statusto) {
		this.statusto = statusto;
	}

	public EStatus getStatusFrom(){
		return EStatus.get(this.statusfrom);
	}
	
	public EStatus getStatusTo(){
		return EStatus.get(this.statusto);
	}
}
