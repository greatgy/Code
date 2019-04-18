package com.supergenius.xo.tpi.entity;

import java.util.ArrayList;
import java.util.List;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.ECommentType;

/**
 * 评论
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Comment extends BaseEntity{

	private static final long serialVersionUID = 8428494509593074891L;
	@Json(strategy = Json.webStrategy)
	private String fromuid;//所评论的UID
	@Json(strategy = Json.webStrategy)
	private String touid;//回复comment的uid，对应此表的uid
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String fromuseruid;//评论人uid
	@Json(strategy = Json.webStrategy)
	private int fromuseroid;//评论人oid
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String fromusername;//评论人name
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String touseruid;//评论给谁的uid
	@Json(strategy = Json.webStrategy)
	private int touseroid;//评论给谁的oid
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String tousername;//评论给谁的name
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String content;
	@Json(strategy = Json.webStrategy)
	private ECommentType type;//类型，评论
	@Json(strategy = Json.webStrategy)
	private EChannel channel;//频道
	@Json(strategy = Json.webStrategy)
	private String fromtitle;
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String data;
	//SESSION_TYPE_USER = "user";
	//SESSION_TYPE_TPIUSER = "tpiuser";
	private String fromusertype;
	
	private Object fromUser;
	private Object toUser;
	private List<Comment> replys;
	private int prizenum;//赞数

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getFromtitle(){
		return fromtitle;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromtitle(String fromtitle){
		this.fromtitle = fromtitle;
	}
	
	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
	
	public String getTouid() {
		return touid;
	}
	
	public void setTouid(String touid) {
		this.touid = touid;
	}
	
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public ECommentType getType() {
		return type;
	}
	
	public void setType(ECommentType type) {
		this.type = type;
	}
	
	public EChannel getChannel() {
		return channel;
	}
	
	public void setChannel(EChannel channel) {
		this.channel = channel;
	}
	
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getChannelName(){
		return EChannel.getName(channel);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	@MapsIgnore(strategy = Maps.dbStrategy)
	public Object getFromUser() {
		return fromUser;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromUser(Object fromUser) {
		this.fromUser = fromUser;
	}

	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	@MapsIgnore(strategy = Maps.dbStrategy)
	public Object getToUser() {
		return toUser;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setToUser(Object toUser) {
		this.toUser = toUser;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public List<Comment> getReplys() {
		return replys == null ? new ArrayList<Comment>() : replys;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setReplys(List<Comment> replys) {
		this.replys = replys;
	}

	public String getFromusertype() {
		return fromusertype;
	}

	public void setFromusertype(String fromusertype) {
		this.fromusertype = fromusertype;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getPrizenum() {
		return prizenum;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setPrizenum(int prizenum) {
		this.prizenum = prizenum;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.content != null) {
			this.content = WebUtil.clearXSS(this.content);
		}
		if (this.data != null) {
			this.data = WebUtil.clearXSS(this.data);
		}
	}
	
}
