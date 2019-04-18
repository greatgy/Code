package com.supergenius.xo.common.entity;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.JsonUtil;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.enums.ESite;

/**
 * 消息(message)实体类
 * 
 * @author YuYingJie
 */
public class Message extends BaseEntity {

	private static final long serialVersionUID = -848946697731721736L;

	private String fromuseruid;// 来自谁uid
	private int fromuseroid;// 来自谁oid
	@Json(strategy = Json.webStrategy)
	private String fromusername;// 来自谁name
	private String touseruid;// 发给谁uid
	private int touseroid;// 发给谁oid
	private String tousername;// 发给谁name
	private String useravatar;// 会员头像or网站头像
	@Json(strategy = Json.webStrategy)
	private String title;// 标题
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	private String href;// 链接
	private EMsgState state;// 已读、未读
	private EMsg type;// 消息类型
	private ESite site;// 来自哪个项目
	@Json(strategy = Json.webStrategy)
	private String sn;// 消息编号
	private Map<String, String> data = new HashMap<String, String>();// 保留,usertype,评论uid

	public String getFromuseruid() {
		return fromuseruid;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}

	public int getFromuseroid() {
		return fromuseroid;
	}

	public void setFromuseroid(int fromuseroid) {
		this.fromuseroid = fromuseroid;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTouseruid() {
		return touseruid;
	}

	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
	}

	public int getTouseroid() {
		return touseroid;
	}

	public void setTouseroid(int touseroid) {
		this.touseroid = touseroid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getUseravatar() {
		return useravatar;
	}

	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public EMsgState getState() {
		return state;
	}

	public void setState(EMsgState state) {
		this.state = state;
	}

	public EMsg getType() {
		return type;
	}

	public void setType(EMsg type) {
		this.type = type;
	}

	public ESite getSite() {
		return site;
	}

	public void setSite(ESite site) {
		this.site = site;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getData() {
		return JsonUtil.toJson(data);
	}

	public Map<String, String> getDataMap() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(String data) {
		this.data = (Map<String, String>) JsonUtil.fromJson(data, Map.class);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getMsgType() {
		return type.name();
	}
	
	/**
	 * @param fromuseruid
	 * @param fromuseroid
	 * @param fromusername
	 * @param touseruid
	 * @param touseroid
	 * @param tousername
	 * @param useravatar
	 * @param title
	 * @param content
	 * @param href
	 * @param state
	 * @param type
	 * @param site
	 * @param sn
	 * @param data
	 * @author: LiJiacheng 2015-8-28 下午3:00:36
	 */
	public Message(String fromuseruid, int fromuseroid, String fromusername, String touseruid, int touseroid, String tousername, String useravatar, String title, String content, String href,
			EMsgState state, EMsg type, ESite site, String sn, Map<String, String> data) {
		super();
		this.fromuseruid = fromuseruid;
		this.fromuseroid = fromuseroid;
		this.fromusername = fromusername;
		this.touseruid = touseruid;
		this.touseroid = touseroid;
		this.tousername = tousername;
		this.useravatar = useravatar;
		this.title = title;
		this.content = content;
		this.href = href;
		this.state = state;
		this.type = type;
		this.site = site;
		this.sn = sn;
		this.data = data;
	}

	/**
	 * 
	 * @author: LiJiacheng 2015-8-28 下午3:02:02
	 */
	public Message() {
		super();
	}

}
