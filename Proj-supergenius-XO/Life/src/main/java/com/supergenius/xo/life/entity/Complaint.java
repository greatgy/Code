package com.supergenius.xo.life.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.EComplaint;

/**
 * 投诉举报实体类
 * 
 * @author YangGuang
 * @date 2018年5月9日16:39:36
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value = { "uid" })
public class Complaint extends BaseEntity {

	private static final long serialVersionUID = 2668822950623984585L;
	@Json(strategy = Json.webStrategy)
	private String fromuid;// 举报人uid
	@Json(strategy = Json.webStrategy)
	private long cid;// 模块id
	@Json(strategy = Json.webStrategy)
	private String fromuseruid;// 举报人uid
	@Json(strategy = Json.webStrategy)
	private String refurl;// 相关事件链接
	@Json(strategy = Json.webStrategy)
	private String refname;// 相关事件
	private EComplaint type;// 类型（受贿、偏袒、徇私舞弊）
	@Json(strategy = Json.webStrategy)
	private int kind;// 类型（0文章，1视频，2话题）
	@Json(strategy = Json.webStrategy)
	private String desc;// 详细说明
	@Json(strategy = Json.webStrategy)
	private String file;// 上传凭证
	@Json(strategy = Json.webStrategy)
	private String result;// 处理结果
	@Json(strategy = Json.webStrategy)
	private String cataloguename; // 板块名称
	// 一下不存入数据库
	private String fromusername; // 举报人

	public Complaint() {
		super();
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	@Json(strategy = Json.webStrategy)
	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

	public String getFromuseruid() {
		return fromuseruid;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}

	@Json(strategy = Json.webStrategy)
	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}

	public String getRefname() {
		return refname;
	}

	public void setRefname(String refname) {
		this.refname = refname;
	}

	public EComplaint getType() {
		return type;
	}

	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}

	public void setType(EComplaint type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}

}
