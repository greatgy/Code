package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.enums.EComplaint;
import com.supergenius.xo.user.entity.User;

/**
 * 投诉举报实体类
 * @author XieMing
 * @date 2016-7-17 下午12:03:10
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Complaint extends BaseEntity {

	private static final long serialVersionUID = 2668822950623984585L;
	@Json(strategy = Json.webStrategy)
	private String fromuseruid;//举报人uid
	@Json(strategy = Json.webStrategy)
	private String touseruid;//被举报人uid
	@Json(strategy = Json.webStrategy)
	private String refuid;//相关uid
	@Json(strategy = Json.webStrategy)
	private String refname;//相关事件名称
	private EComplaint type;//类型（受贿、偏袒、徇私舞弊）
	@Json(strategy = Json.webStrategy)
	private EUser tousertype;//被举报对象的类型
	@Json(strategy = Json.webStrategy)
	private String desc;//详细说明
	@Json(strategy = Json.webStrategy)
	private String file;//上传凭证
	@Json(strategy = Json.webStrategy)
	private String result;//处理结果
	private User fromuser; // 举报人
	private User touser; // 被举报人
	private String refurl;//当是pk时，需要链接到pk详情页，不存入数据库
	private String reflevelname;//学员级别
	private String reflevelname2;//被举报人级别
	private int refcount;//裁判或者专家的执教场数
	//status:12为待处理0非有效的举报1有效属实的举报
	
	public Complaint() {
		super();
	}
	
	/**
	 * 举报时生成一条举报记录的构造方法
	 * @param fromuseruid
	 * @param touseruid
	 * @param refuid
	 * @param refname
	 * @param type
	 * @param tousertype
	 * @param desc
	 * @param file
	 * @author: XieMing
	 */
	public Complaint(String fromuseruid, String touseruid, String refuid, String refname, EComplaint type, EUser tousertype, String desc, String file) {
		super();
		this.fromuseruid = fromuseruid;
		this.touseruid = touseruid;
		this.refuid = refuid;
		this.refname = refname;
		this.type = type;
		this.tousertype = tousertype;
		this.desc = desc;
		this.file = file;
	}
	
	@Json(strategy = Json.webStrategy)
	public int getRefcount() {
		return refcount;
	}

	public void setRefcount(int refcount) {
		this.refcount = refcount;
	}

	@Json(strategy = Json.webStrategy)
	public String getReflevelname2() {
		return reflevelname2;
	}

	public void setReflevelname2(String reflevelname2) {
		this.reflevelname2 = reflevelname2;
	}

	@Json(strategy = Json.webStrategy)
	public String getRoUserTypeName() {
		return getTousertype().getTypeName();
	}

	@Json(strategy = Json.webStrategy)
	public String getReflevelname() {
		return reflevelname;
	}

	public void setReflevelname(String reflevelname) {
		this.reflevelname = reflevelname;
	}

	public String getFromuseruid() {
		return fromuseruid;
	}
	
	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}
	
	@Json(strategy = Json.webStrategy)
	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User fromuser) {
		this.fromuser = fromuser;
	}
	
	public String getTouseruid() {
		return touseruid;
	}
	
	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
	}
	
	@Json(strategy = Json.webStrategy)
	public User getTouser() {
		return touser;
	}

	public void setTouser(User touser) {
		this.touser = touser;
	}

	public String getRefuid() {
		return refuid;
	}
	
	public void setRefuid(String refuid) {
		this.refuid = refuid;
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
	
	public EUser getTousertype() {
		return tousertype;
	}
	
	public void setTousertype(EUser tousertype) {
		this.tousertype = tousertype;
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
	
	@Json(strategy = Json.webStrategy)
	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	
}
