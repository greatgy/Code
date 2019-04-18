package com.supergenius.xo.tpi.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.ETeamStage;
import com.supergenius.xo.user.entity.User;

/**
 * 团队实体
 * @author liushaomin
 * @modifier ShangJianguo
 */
@Json(value = {"uid"}, ignoreTypeStrategy = Json.defaultStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Team extends BaseEntity{
	
	private static final long serialVersionUID = -4928835733687971236L;
	@Json(strategy = Json.webStrategy)
	private String sn;// 团队编号
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String name;// 团队名称
	private String useruid;//创建人uid
	@Json(strategy = Json.webStrategy)
	private String username;// 创建人姓名
	@Json(strategy = Json.webStrategy)
	private int teamlevel;// 团队评分
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String imglittle;//小图
	private String imgmedium;//中图
	private String imgbig;//大图
	private String imgoriginal;//原图
	private String summary;//团队介绍
	private String declaration;//团队宣言
	private List<String> memberuids;//成员uid的集合
	@Json(strategy = Json.webStrategy)
	private boolean istop;//是否置顶    是、否
	@Json(strategy = Json.webStrategy)
	private boolean isrecommend;//是否推荐 是、否
	@Json(strategy = Json.webStrategy)
	private ETeamStage stage;//团队进度
	@Json(strategy = Json.webStrategy)
	private String typeuid; // 类型（专业并购、带资个人、智慧型野心、实干型野心）
	private ContactInfo contactinfo;// 联系信息
	private List<Link> achievements; //团队成就列表
	private String fundneed; // 整体资金需求
	private List<Notice> fundneeds;//资金需求列表
	private List<String> initmemuids;// 存储刚刚邀请，但是还没有同意的用户uid
	private DateTime rectime; // 推荐时间
	
	private String typeName;//类别的名字，在type类中获取，不保存
	private int articleNum; // 团队成员文章的数量
	private int wishprojNum;// 团队想要并购的项目数量
	private int wishmeNum;// 喜欢本团队的数目
	private int attentionNum;// 团队支持数
	private List<User> memberList;// 成员对象
	private List<Project> projectList;// 有意愿的项目
	private User user;// 创建人对象
	private List<User> initmemList;// 邀请的但未同意的

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
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getTeamlevel() {
		return teamlevel;
	}
	
	public void setTeamlevel(int teamlevel) {
		this.teamlevel = teamlevel;
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
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDeclaration() {
		return declaration;
	}
	
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	
	public List<String> getMemberuids() {
		return memberuids;
	}
	
	public void setMemberuids(List<String> memberuids) {
		this.memberuids = memberuids;
	}
	
	public boolean isIstop() {
		return istop;
	}
	
	public void setIstop(boolean istop) {
		this.istop = istop;
	}
	
	public boolean isIsrecommend() {
		return isrecommend;
	}
	
	public void setIsrecommend(boolean isrecommend) {
		this.isrecommend = isrecommend;
	}
	
	public ETeamStage getStage() {
		return stage;
	}

	public void setStage(ETeamStage stage) {
		this.stage = stage;
	}
	
	public String getTypeuid() {
		return typeuid;
	}

	public void setTypeuid(String typeuid) {
		this.typeuid = typeuid;
	}
	
	public ContactInfo getContactinfo() {
		return contactinfo;
	}
	
	public void setContactinfo(ContactInfo contactinfo) {
		this.contactinfo = contactinfo;
	}
	
	public List<Link> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Link> achievements) {
		this.achievements = achievements;
	}

	public String getFundneed() {
		return fundneed;
	}

	public void setFundneed(String fundneed) {
		this.fundneed = fundneed;
	}

	public List<Notice> getFundneeds() {
		return fundneeds;
	}

	public void setFundneeds(List<Notice> fundneeds) {
		this.fundneeds = fundneeds;
	}

	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTypeName() {
		return typeName;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getArticleNum() {
		return articleNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getWishprojNum() {
		return wishprojNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWishprojNum(int wishprojNum) {
		this.wishprojNum = wishprojNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public List<User> getMemberList() {
		return memberList;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setMemberList(List<User> memberList) {
		this.memberList = memberList;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public List<Project> getProjectList() {
		return projectList;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getWishmeNum() {
		return wishmeNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWishmeNum(int wishmeNum) {
		this.wishmeNum = wishmeNum;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getAttentionNum() {
		return attentionNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public User getUser() {
		return user;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getInitmemuids() {
		return initmemuids;
	}

	public void setInitmemuids(List<String> initmemuids) {
		this.initmemuids = initmemuids;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public List<User> getInitmemList() {
		return initmemList;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setInitmemList(List<User> initmemList) {
		this.initmemList = initmemList;
	}

	public DateTime getRectime() {
		return rectime;
	}

	public void setRectime(DateTime rectime) {
		this.rectime = rectime;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.name != null) {
			this.name = WebUtil.clearXSS(this.name);
		}
		if (this.summary != null) {
			this.summary = WebUtil.clearXSS(this.summary);
		}
		if (this. declaration!= null) {
			this.declaration = WebUtil.clearXSS(declaration);
		}
		if (this.fundneed != null) {
			this.fundneed = WebUtil.clearXSS(fundneed);
		}
	} 
	
}
