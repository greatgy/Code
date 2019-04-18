package com.supergenius.xo.enterpriser.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.BaseEntity;

/** 
 * 讲座实体
 * @author chenminchang
 * @date 2016-10-24 下午4:23:52 
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Lecture extends BaseEntity {
	
	private static final long serialVersionUID = 8756115658398762603L;
	@Json(strategy = Json.webStrategy)
	private String sn;//讲座编号
	@Json(strategy = Json.webStrategy)
	private String name;//讲座名称
	@Json(strategy = Json.webStrategy)
	private String username;//演讲人名称
	@Json(strategy = Json.webStrategy)
	private DateTime time;//讲座时间
	@Json(strategy = Json.webStrategy)
	private String address;//讲座地点
	@Json(strategy = Json.webStrategy)
	private String notice;//报名须知
	@Json(strategy = Json.webStrategy)
	private double fee;//报名费用
	@Json(strategy = Json.webStrategy)
	private int maxcount;//最大报名人数
	@Json(strategy = Json.webStrategy)
	private int registercount;//已报名人数
	@Json(strategy = Json.webStrategy)
	private int semester;//第几学期（一期一个表）
	@Json(strategy = Json.webStrategy)
	private String data;//以json的方式记录
	@Json(strategy = Json.webStrategy)
	private String file;//讲座文件
	//status状态(init 未开始报名 enable 可报名 end 报名结束 wait 时间确定已发送邮件)
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public DateTime getTime() {
		return time;
	}
	
	public void setTime(DateTime time) {
		this.time = time;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTimeStr() {
		return this.time.toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNotice() {
		return notice;
	}
	
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public double getFee() {
		return fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public int getMaxcount() {
		return maxcount;
	}
	
	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}
	
	public int getRegistercount() {
		return registercount;
	}
	
	public void setRegistercount(int registercount) {
		this.registercount = registercount;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFilename() {
		if (StrUtil.isNotEmpty(file)) {
			return file.substring(file.lastIndexOf(BaseStrDict.slash) + 1, file.lastIndexOf(BaseStrDict.dot));
		}
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFilenameFix() {
		if (StrUtil.isNotEmpty(file)) {
			return file.substring(file.lastIndexOf(BaseStrDict.slash) + 1);
		}
		return null;
	}

}
