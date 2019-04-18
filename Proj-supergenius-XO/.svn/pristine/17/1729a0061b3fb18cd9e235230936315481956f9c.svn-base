package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.user.entity.User;

/**
 * 用户级别实体类
 * 此枚举的使用：每个type（学员|专家|裁判）和Major都只有一个状态为enable，更新的时候将原来type和type都相同的最新条目状态改:disable
 * 这样就保证每个type，major只有一个最新的为enable的
 * 当有冻结用户或裁判专家退出操作，需将对应数据的状态改为disable，避免查询到
 * @author liubin
 * @date 2016-7-17 下午12:47:37
 */
@Json(value = {"uid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class UserLevel extends BaseEntity {

	private static final long serialVersionUID = -3975686806070593748L;
	private String useruid;// 用户uid
	@Json(strategy = Json.webStrategy)
	private EMajor major;// 专业
	private int levelfrom;// 之前的级别
	@Json(strategy = Json.webStrategy)
	private int levelto;// 之后（晋级后）级别
	private EUser type;// 学员or专家or裁判   : 学员晋级 为学员 ，申请专家裁判为专家裁判 ，专家晋级
	private String certificateuid;// 证书uid
	private String arguments;// 相关记录参数
	private String desc;// 备注
	private ELevelChannel channel;// 申请学员or挑战晋级or积分晋级or答辩晋级or累计失败5场降级or特批学员级别|申请专家or专家晋级or特批专家or特聘专家or专家降级（处罚）or专职专家or退出专家|申请裁判or专职裁判or特聘裁判or被举报取消裁判资格or特批裁判or退出裁判
	//临时字段 不存入数据库
	private User user;// 关联user
	@Json(strategy = Json.webStrategy)
	private String username;// 关联username
	@Json(strategy = Json.webStrategy)
	private String usersn;// 关联usersn
	@Json(strategy = Json.webStrategy)
	private String lastlogintime;// 关联usersn
	@Json(strategy = Json.webStrategy)
	private String lastloginip;// 关联usersn
	@Json(strategy = Json.webStrategy)
	private String level;// 获得学员级别
	@Json(strategy = Json.webStrategy)
	private String majorname;// 获得专业名字
	@Json(strategy = Json.webStrategy)
	private String certificatename;// 证书名称
	@Json(strategy = Json.webStrategy)
	private String certificatesrc;// 证书地址
	@Json(strategy = Json.webStrategy)
	private String studentsn;// 学号

	public UserLevel() {
		super();
	}

	public UserLevel(String useruid, EMajor major, int levelfrom, int levelto, EUser type, ELevelChannel channel) {
		super();
		this.useruid = useruid;
		this.major = major;
		this.levelfrom = levelfrom;
		this.levelto = levelto;
		this.type = type;
		this.channel = channel;
	}

	public UserLevel(String useruid, EMajor major, Integer levelto, EUser type, ELevelChannel channel) {
		super();
		this.useruid = useruid;
		this.major = major;
		this.levelto = levelto.intValue();
		this.type = type;
		this.channel = channel;
	}

	public String getMajorName() {
		return majorname;
	}

	public void setMajorName(String majorName) {
		this.majorname = majorName;
	}

	public User getUser() {
		return user;
	}
	
	public String getUsername() {
		return user.getName();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsersn() {
		return user.getUsersn();
	}
	
	public void setUsersn(String usersn) {
		this.usersn= usersn;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = EStudentLevel.get(level).getName();
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public EMajor getMajor() {
		return major;
	}

	public void setMajor(EMajor major) {
		this.major = major;
	}

	public int getLevelfrom() {
		return levelfrom;
	}

	public void setLevelfrom(int levelfrom) {
		this.levelfrom = levelfrom;
	}

	public int getLevelto() {
		return levelto;
	}

	public void setLevelto(int levelto) {
		this.levelto = levelto;
	}

	public EUser getType() {
		return type;
	}

	public void setType(EUser type) {
		this.type = type;
	}

	public String getCertificateuid() {
		return certificateuid;
	}

	public void setCertificateuid(String certificateuid) {
		this.certificateuid = certificateuid;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ELevelChannel getChannel() {
		return channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelname() {
		return channel.getName();
	}

	public void setChannel(ELevelChannel channel) {
		this.channel = channel;
	}
	
	public String getCertificatename() {
		return certificatename;
	}

	public void setCertificatename(String certificatename) {
		this.certificatename = certificatename;
	}
	
	public String getCertificatesrc() {
		return certificatesrc;
	}

	public void setCertificatesrc(String certificatesrc) {
		this.certificatesrc = certificatesrc;
	}

	public String getStudetsn() {
		return studentsn;
	}

	public void setStudetsn(String studentsn) {
		this.studentsn = studentsn;
	}
}
