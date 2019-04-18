package com.supergenius.xo.admin.entity;

import java.util.Map;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.JsonUtil;
import com.genius.model.base.entity.BaseEntity;

/**
 * 群发邮件实体类
 * @author XieMing
 * @date 2016-5-23 下午12:08:50
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class EmailLog extends BaseEntity {

	private static final long serialVersionUID = -7173788165105152346L;
	private String adminid;// 管理员ID
	@Json(strategy = Json.allStrategy)
	private String title;// 标题
	@Json(strategy = Json.allStrategy)
	private String content;// 内容
	@Json(strategy = Json.allStrategy)
	private Map<String, Object> receiver;// 接收人（以uid:name形式用json存储）
	@Json(strategy = Json.allStrategy)
	private String sender;// 发送人
	
	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
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

	@Json(strategy = Json.webStrategy)
	public String getReceiver() {
		return JsonUtil.toJson(receiver);
	}

	@SuppressWarnings("unchecked")
	public void setReceiver(String json) {
		this.receiver = JsonUtil.fromJson(json, Map.class);
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * 接收Map类型的参数
	 * @param receiver
	 */
	public void setDataMap(Map<String, Object> receiver) {
		this.receiver = receiver;
	}
	
}
