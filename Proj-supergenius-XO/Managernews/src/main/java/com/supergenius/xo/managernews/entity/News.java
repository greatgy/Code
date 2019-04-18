package com.supergenius.xo.managernews.entity;

import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.managernews.enums.EManagernewsMsg;

/**
 * @author tf
 * @date 2018年7月4日
 */
public class News extends BaseEntity {

	private static final long serialVersionUID = -2926114826183176479L;

	private String fromuid;// 发送者id
	private String touid;// 接收者id
	private String title;// 标题
	private String content;// 内容
	private String href;// 链接地址
	private EManagernewsMsg type;// 消息类型
	private boolean isread;// 消息状态(0未读1已读)

	// 下面属性不存在数据库中，封装为了使用
	private String fromusername; // 发送人姓名

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

	public EManagernewsMsg getType() {
		return type;
	}

	public void setType(EManagernewsMsg type) {
		this.type = type;
	}

	public boolean isIsread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}
	

}