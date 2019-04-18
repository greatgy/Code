package com.supergenius.xo.tpi.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EPayType;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;

/**
 * 并购平台的注册账号
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class TpiUser extends BaseEntity {
	
	private static final long serialVersionUID = 2610781637875275793L;
	
	@Json(strategy = Json.webStrategy)
	private String usersn; // 会员号
	@Json(strategy = Json.webStrategy)
	private String username;// 会员名（ 机构名称）
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private ETpiUserType type;// 用户类型（投资机构，推荐机构，并购机构）
	@Json(strategy = Json.webStrategy)
	private String email;// 注册邮箱
	private String newemail; // 在更换邮箱的功能时使用
	private String passwordrecover;//在找回密码时使用
	private String password;
	@Json(strategy = Json.webStrategy)
	private String avatarorigin;
	@Json(strategy = Json.webStrategy)
	private String avatarbig;
	@Json(strategy = Json.webStrategy)
	private String avatarmidium;
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String avatarlittle;
	@Json(strategy = Json.webStrategy)
	private DateTime committime;// 提交时间
	@Json(strategy = Json.webStrategy)
	private ETpiuserState state = ETpiuserState.emailvalid;// 状态
	@Json(strategy = Json.webStrategy)
	private boolean istop;// 置顶 是、否
	@Json(strategy = Json.webStrategy)
	private boolean isindex;// 在首页
	@Json(strategy = Json.webStrategy)
	private ContactInfo contactinfo;// 联系信息
	@Json(strategy = Json.webStrategy)
	private InvestInfo investInfo;// 投资机构
	@Json(strategy = Json.webStrategy)
	private MergerInfo mergerInfo;// 并购机构
	@Json(strategy = Json.webStrategy)
	private RecommendInfo recommendInfo;// 推荐机构
	@Json(strategy = Json.webStrategy)
	private EPayType paytype;// 用户缴费方式（来源）
	private int wantpronum;// 目标项目数，不保存
	private int praisenum;// 支持数，不保存
	private DateTime lastlogintime;//最后一次登陆时间
	
	private List<Project> wantinvest = new ArrayList<>();

	public String getUsersn() {
		return usersn;
	}

	public void setUsersn(String usersn) {
		this.usersn = usersn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ETpiUserType getType() {
		return type;
	}

	public void setType(ETpiUserType type) {
		this.type = type;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarorigin() {
		return avatarorigin;
	}

	public void setAvatarorigin(String avatarorigin) {
		this.avatarorigin = avatarorigin;
	}

	public String getAvatarbig() {
		return avatarbig;
	}

	public void setAvatarbig(String avatarbig) {
		this.avatarbig = avatarbig;
	}

	public String getAvatarmidium() {
		return avatarmidium;
	}

	public void setAvatarmidium(String avatarmidium) {
		this.avatarmidium = avatarmidium;
	}

	public String getAvatarlittle() {
		return avatarlittle;
	}

	public void setAvatarlittle(String avatarlittle) {
		this.avatarlittle = avatarlittle;
	}

	public DateTime getCommittime() {
		return committime;
	}

	public void setCommittime(DateTime committime) {
		this.committime = committime;
	}

	public ContactInfo getContactinfo() {
		return contactinfo;
	}

	public void setContactinfo(ContactInfo contactinfo) {
		this.contactinfo = contactinfo;
	}

	public InvestInfo getInvestInfo() {
		return investInfo;
	}

	public void setInvestInfo(InvestInfo investInfo) {
		this.investInfo = investInfo;
	}

	public MergerInfo getMergerInfo() {
		return mergerInfo;
	}

	public void setMergerInfo(MergerInfo mergerInfo) {
		this.mergerInfo = mergerInfo;
	}

	public RecommendInfo getRecommendInfo() {
		return recommendInfo;
	}

	public void setRecommendInfo(RecommendInfo recommendInfo) {
		this.recommendInfo = recommendInfo;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getStateName() {
		return ETpiuserState.getName(state, Locale.CHINA);
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getPayTypeName() {
		return EPayType.getName(paytype, Locale.CHINA);
	}
	
	public ETpiuserState getState() {
		return state;
	}
	
	public void setState(ETpiuserState state) {
		this.state = state;
	}
	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public boolean isIsindex() {
		return isindex;
	}

	public void setIsindex(boolean isindex) {
		this.isindex = isindex;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getWantpronum() {
		return wantpronum;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWantpronum(int wantpronum) {
		this.wantpronum = wantpronum;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getPraisenum() {
		return praisenum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setPraisenum(int praisenum) {
		this.praisenum = praisenum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public List<Project> getWantinvest() {
		return wantinvest;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWantinvest(List<Project> wantinvest) {
		this.wantinvest = wantinvest;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTypeName() {
		return ETpiUserType.getName(type, Locale.CHINA);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void initPassword(String pwd) {
		this.password = DigestUtils.md5Hex(this.getUid() + pwd);
	}
	
	public void initPasswordRecover(String pwd) {
		this.passwordrecover = this.getUid() + DigestUtils.md5Hex(pwd);
	}

	public String getNewemail() {
		return newemail;
	}

	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	
	public String getPasswordrecover() {
		return passwordrecover;
	}

	public void setPasswordrecover(String passwordrecover) {
		this.passwordrecover = passwordrecover;
	}
	
	public DateTime getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(DateTime lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public EPayType getPaytype() {
		return paytype;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setPaytype(EPayType paytype) {
		this.paytype = paytype;
	}
	
}
