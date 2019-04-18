package com.supergenius.xo.sudokuapi.entity;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 玩家反馈消息实体
 *
 * @author YongXueZhen
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = Maps.dbStrategy)
public class Feedbacks extends BaseEntity {

    private static final long serialVersionUID = -2086414919713225362L;
    @Json(strategy = Json.webStrategy)
    private ObjectId useruid;//玩家的uid
    @Json(strategy = Json.webStrategy)
    private String content;//反馈的内容
    @Json(strategy = Json.webStrategy)
    private int type;//反馈是否已处理[0未查看，1已查看未回复，2已回复]
    @Json(strategy = Json.webStrategy)
    private String email;//用户填入的email
    @Json(strategy = Json.webStrategy)
    private ObjectId messageid; //回复消息id
    
    public ObjectId getMessageid() {
		return messageid;
	}

	public void setMessageid(ObjectId messageid) {
		this.messageid = messageid;
	}

	public ObjectId getUseruid() {
        return useruid;
    }

    public void setUseruid(ObjectId useruid) {
        this.useruid = useruid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
