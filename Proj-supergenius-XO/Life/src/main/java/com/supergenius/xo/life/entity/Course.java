package com.supergenius.xo.life.entity;

import java.util.Locale;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.EGrade;

/**
 * 课程实体类
 * 
 * @author YangGuang
 * @date 2018年5月9日11:45:50
 */
@Json(value = { "status", "createtime", "updatetime", "sid", "subjectname", "grade", "name", "press", "publishtime", "useadress", "imglittle", "imgmedium", "imgbig", "imgoriginal", "data", "adminname" }, ignoreTypeStrategy = Json.webStrategy, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Course extends BaseEntity {

	private static final long serialVersionUID = -5657738991329325473L;
	private int sid; // 科目的id
	private EGrade grade; // 年级
	private String name; // 课程名称
	private String press; // 出版社名称
	private DateTime publishtime; // 出版时间
	private String useadress; // 应用地点
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String data; // 相关的其他json数据
	private String adminuid; // 操作人UID

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String subjectname; // 管理员姓名

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public EGrade getGrade() {
		return grade;
	}

	public void setGrade(EGrade grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	@Json(strategy = Json.allStrategy)
	public String getPublishtimeStr() {
		return getPublishtime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	public DateTime getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(DateTime publishtime) {
		this.publishtime = publishtime;
	}

	public String getUseadress() {
		return useadress;
	}

	public void setUseadress(String useadress) {
		this.useadress = useadress;
	}

	public String getImglittle() {
		return imglittle;
	}

	public void setImglittle(String imglittle) {
		this.imglittle = imglittle;
	}

	public String getImgmedium() {
		return imgmedium;
	}

	public void setImgmedium(String imgmedium) {
		this.imgmedium = imgmedium;
	}

	public String getImgbig() {
		return imgbig;
	}

	public void setImgbig(String imgbig) {
		this.imgbig = imgbig;
	}

	public String getImgoriginal() {
		return imgoriginal;
	}

	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	/**
	 * 显示所属的年级
	 * 
	 * @return
	 */
	@Json(strategy = {Json.webStrategy})
	public String getGradeName() {
		return EGrade.getName(this.grade, Locale.CHINA);
	}
}
