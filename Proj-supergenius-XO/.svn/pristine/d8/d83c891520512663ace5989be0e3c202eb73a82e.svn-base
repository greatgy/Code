package com.supergenius.xo.common.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EQuotes;

/**
 * 名人名言实体类，此类不存数据库，保存序列化文件
 * 
 * @author LiJiacheng
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Quotes extends BaseEntity implements Comparable<Quotes>{

	private static final long serialVersionUID = 8379591553331664977L;

	@Json(strategy = { Json.webStrategy, Json.cacheStrategy })
	private String author;// 作者
	@Json(strategy = { Json.webStrategy, Json.cacheStrategy })
	private String content;// 内容
	@Json(strategy = { Json.webStrategy, Json.cacheStrategy })
	private EQuotes type;// 类型global:全站名言  finance:天财评论名言
	private int site;// 标记位，反序列化成为List时，存储List下标

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EQuotes getType() {
		if (type == null )
			type = EQuotes.global;
		return type;
	}

	public String getTypeName() {
		return getType().getName();
	}
	
	public void setType(EQuotes type) {
		this.type = type;
	}

	public int getSite() {
		return site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Quotes [author=" + author + ", content=" + content + ", id=" + id + ", oid=" + oid + ", uid=" + uid + ", tid=" + tid + ", status=" + status + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}

	@Override
	public int compareTo(Quotes o) {
		if (this.updatetime != o.updatetime) {
			return o.updatetime.compareTo(this.updatetime);
		} 
		return o.createtime.compareTo(this.createtime);
	}

}
