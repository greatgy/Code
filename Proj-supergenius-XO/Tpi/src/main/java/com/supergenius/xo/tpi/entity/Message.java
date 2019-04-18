package com.supergenius.xo.tpi.entity;

import java.util.List;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EFromUserType;
import com.supergenius.xo.tpi.enums.EInboxState;
import com.supergenius.xo.tpi.enums.EMsg;
import com.supergenius.xo.tpi.enums.EMsgState;
import com.supergenius.xo.tpi.enums.EOutboxState;

/**
 * 消息
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Message extends BaseEntity {
	
	private static final long serialVersionUID = 6824226830089193503L;
	@Json(strategy = Json.webStrategy)
	private String fromuseruid;//发送人uid
	@Json(strategy = Json.webStrategy)
	private String fromusername;//发送人用户名
	@Json(strategy = Json.webStrategy)
	private String touseruid;//接收人uid,当系统群发消息时，touseruid为"all"或"目标的类型名"
	@Json(strategy = Json.webStrategy)
	private String tousername;//接收人用户名
	@Json(strategy = Json.webStrategy)
	private String title;//标题
	@Json(strategy = Json.webStrategy)
	private String content;//内容
	@Json(strategy = Json.webStrategy)
	private String href ;//链接
	@Json(strategy = Json.webStrategy)
	private String fromuseravatar;//发送人头像
	@Json(strategy = Json.webStrategy)
	private EMsgState state = EMsgState.init;//(未读、已读、已删除)
	@Json(strategy = Json.webStrategy)
	private EOutboxState outstate = EOutboxState.normal;//发件箱状态(0删除，1正常)
	@Json(strategy = Json.webStrategy)
	private EInboxState instate = EInboxState.normal;//收件箱状态(0删除，1正常)
	@Json(strategy = Json.webStrategy)
	private EMsg type;//类型（系统消息、系统通知、会员消息、创建团队时的消息、
	@Json(strategy = Json.webStrategy)
	private List<Link> files;
	@Json(strategy = Json.webStrategy)
	private EFromUserType usertype;
	
	public String getFromuseruid() {
		return fromuseruid;
	}
	
	public EFromUserType getUsertype() {
		return usertype;
	}

	public String getUsertyeName() {
		if (usertype != null) {
			return usertype.name();
		} else {
			return null;
		}
	}
	
	public EOutboxState getOutstate() {
		return outstate;
	}

	public void setOutstate(EOutboxState outstate) {
		this.outstate = outstate;
	}

	public EInboxState getInstate() {
		return instate;
	}

	public void setInstate(EInboxState instate) {
		this.instate = instate;
	}

	public void setUsertype(EFromUserType usertype) {
		this.usertype = usertype;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}
	
	public String getTouseruid() {
		return touseruid;
	}
	
	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
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
	
	public String getFromuseravatar() {
		return fromuseravatar;
	}

	public void setFromuseravatar(String fromuseravatar) {
		this.fromuseravatar = fromuseravatar;
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
	
	public List<Link> getFiles() {
		return files;
	}
	
	public void setFiles(List<Link> files) {
		this.files = files;
	}
	
	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.content != null) {
			this.content = WebUtil.clearXSS(this.content);
		}
	}
	
}
