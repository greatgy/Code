package com.supergenius.xo.life.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.enums.ELabel;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.user.entity.User;

/**
 * 视频实体类
 * 
 * @author yangguang
 * @date 2018年5月9日12:19:15
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "imglittle", "imgmedium", "imgbig", "imgoriginal", "adminuid", "title", "summary", "cid", "content", "data", "istop", "useruid", "type",
		"state", "adminname", "subjectname", "ismember", "username"}, ignoreTypeStrategy = { Json.webStrategy }, strategy = { Json.webStrategy })
public class Video extends BaseEntity implements Comparable<Video> {

	private static final long serialVersionUID = 7021656777578046662L;

	private long cid; // 所属模块
	private EGrade grade; // 年级
	private int sid; // 科目id
	private String title; // 标题
	private String content; // 视频代码
	private String summary; // 视频描述
	private ELabel keywords;// 视频标签
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String data; // 备用
	private String useruid;
	private int istop; // 置顶(设为焦点文章)
	private int type; 
	private EState state; // 状态(保存待点评、待回复等状态)
	private String adminuid;
	private int ismember; // 是否会员(0用户1会员)

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String subjectname; // 科目名称
	private User user; // 科目名称
	private Long clickCount; // 科目名称
	private String keywordsname; // 管理员姓名
	private String username;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ELabel getKeywords() {
		return keywords;
	}

	public void setKeywords(ELabel keywords) {
		this.keywords = keywords;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public EGrade getGrade() {
		return grade;
	}

	public void setGrade(EGrade grade) {
		this.grade = grade;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsmember() {
		return ismember;
	}

	public void setIsmember(int ismember) {
		this.ismember = ismember;
	}

	public EState getState() {
		return state;
	}

	public void setState(EState state) {
		this.state = state;
	}

	@Json(strategy = { Json.webStrategy })
	public String getStateName() {
		return this.state.getName();
	}

	/**
	 * 显示所属的年级
	 * 
	 * @return
	 */
	@Json(strategy = { Json.webStrategy })
	public String getGradeName() {
		return EGrade.getName(this.grade, Locale.CHINA);
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
	public String getLabelName() {
		return ELabel.getName(this.keywords, Locale.CHINA);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	@Override
	public int compareTo(Video o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Video) {
			Video video = (Video) o;
			if (clickCount > video.getClickCount()) {
				return -1;
			} else if (clickCount == video.getClickCount()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}

	public String getKeywordsname() {
		if (this.keywords != ELabel.none) {
			this.keywordsname = this.keywords.getName();
		}
		return this.keywordsname;
	}

	public void setKeywordsname(String keywordsname) {
		this.keywordsname = keywordsname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
