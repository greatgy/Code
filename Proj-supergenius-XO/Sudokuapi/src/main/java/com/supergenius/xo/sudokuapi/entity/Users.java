package com.supergenius.xo.sudokuapi.entity;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.base.BaseEntity;
import com.supergenius.xo.sudokuapi.enums.EGrade;
import com.supergenius.xo.sudokuapi.enums.ERecharge;

/**
 * 用户实体类
 * 
 * @author LiJiacheng
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = Maps.dbStrategy)
public class Users extends BaseEntity {

	private static final long serialVersionUID = 7033387149933261607L;
	@Json(strategy = Json.appStrategy)
	private String account;// 账号
	@Json(strategy = Json.appStrategy)
	private String accountname;//
	@Json(strategy = Json.appStrategy, alias="nickname")
	private String name;// 用户名
	@Json(strategy = Json.appStrategy)
	private String password;// 密码
	@Json(strategy = Json.appStrategy)
	private int sex; //性别0女1男
	@Json(strategy = Json.appStrategy)
	private int age;//年龄
	@Json(strategy = Json.appStrategy)
	private boolean haveinfo;//是否填写个人信息：性别，年龄
	@Json(strategy = Json.appStrategy)
	private boolean  agreement;//是否同意隐私协议
	@Json(strategy = Json.appStrategy)
	private String macaddress;//机器序列号
	@Json(strategy = Json.appStrategy)
	private String lastloginaddress;//最後一次登录设备号
	@Json(strategy = Json.appStrategy)
	private String username;
	@Json(strategy = Json.appStrategy)
	private String token;
	@Json(strategy = Json.appStrategy)
	private String channelid;
	@Json(strategy = Json.appStrategy)
	private boolean loginaddressdiff;
	@Json(strategy = Json.appStrategy)
	private String channeluseruid;
	@Json(strategy = Json.appStrategy)
	private String email;// 邮箱
	@Json(strategy = Json.appStrategy)
	private String create_ip;// 创建IP
	@Json(strategy = Json.appStrategy)
	private Date logintime;// 上次登录时间
	@Json(strategy = Json.appStrategy)
	private String login_ip;// 上次登录IP
	@Json(strategy = Json.appStrategy)
	private String icon;// 头像
	@Json(strategy = Json.appStrategy)
	private int defaulticon; //
	@Json(strategy = Json.appStrategy, alias="grade")
	private EGrade grade;// 段位级别
	@Json(strategy = Json.appStrategy)
	private boolean recharge;// 是否充值
	@Json(strategy = Json.appStrategy, alias="credit")
	private int points;// 积分
	@Json(strategy = Json.appStrategy)
	private int rounds;// 总局数
	@Json(strategy = Json.appStrategy)
	private int wintimes;// 赢的局数
	@Json(strategy = Json.appStrategy)
	private Map<String,Map<String,Double>> userdata;  //此用户的游戏数据，单机和人机的游戏数量，准确率，单局最高得分，单局最高积分
	@Json(strategy = Json.appStrategy, alias="coin")
	private int money;// 天才币
	@Json(strategy = Json.appStrategy)
	private int isaccount;// 账户名是否可以修改(0不可以1可以)
	@Json(strategy = Json.appStrategy)
	private double cost;// 账户的消费金额
	@Json(strategy = Json.appStrategy)
	private Map<String, Integer> propcount; // 用户购买过的所有道具
	@Json(strategy = Json.appStrategy)
	private String country;// 所处国家或地区
	@Json(strategy = Json.appStrategy)
	private String language;// 使用的语言
	@Json(strategy = Json.appStrategy)
	private String region_code;// 区域代码
	
	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPropcount(Map<String, Integer> propcount) {
		this.propcount = propcount;
	}
	
	public Map<String, Integer> getPropcount() {
		return propcount;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Map<String, Map<String, Double>> getUserdata() {
		return userdata;
	}

	public boolean isLoginaddressdiff() {
		return loginaddressdiff;
	}

	public void setLoginaddressdiff(boolean loginaddressdiff) {
		this.loginaddressdiff = loginaddressdiff;
	}

	public void setUserdata(Map<String, Map<String, Double>> userdata) {
		this.userdata = userdata;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastloginaddress() {
		return lastloginaddress;
	}

	public void setLastloginaddress(String lastloginaddress) {
		this.lastloginaddress = lastloginaddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void initPassword(String pwd) {
		this.password = DigestUtils.md5Hex(pwd);
	}

	public int getSex() {
		return sex;
	}

	public int getDefaulticon() {
		return defaulticon;
	}

	public void setDefaulticon(int defaulticon) {
		this.defaulticon = defaulticon;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreate_ip() {
		return create_ip;
	}

	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public EGrade getGrade() {
		return grade;
	}

	public void setGrade(EGrade grade) {
		this.grade = grade;
	}
	
	public boolean checkPwd(String pwd) {
		if (StringUtils.isEmpty(pwd)) {
			return false;
		}
		return DigestUtils.md5Hex(pwd).equals(this.getPassword());
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	@Json(strategy = Json.webStrategy)
	public String getGradeName() {
		return EGrade.getName(grade, Locale.CHINA);
	}

	public boolean isRecharge() {
		return recharge;
	}

	public void setRecharge(boolean recharge) {
		this.recharge = recharge;
	}

	public boolean isAgreement() {
		return agreement;
	}

	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}

	public boolean isHaveinfo() {
		return haveinfo;
	}

	public void setHaveinfo(boolean haveinfo) {
		this.haveinfo = haveinfo;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	@Json(strategy = Json.webStrategy)
	public String getRechargeName() {
		if (recharge) {
			return ERecharge.getName(ERecharge.is, Locale.CHINA);
		} else {
			return ERecharge.getName(ERecharge.no, Locale.CHINA);
		}
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public int getWintimes() {
		return wintimes;
	}

	public void setWintimes(int wintimes) {
		this.wintimes = wintimes;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getChanneluseruid() {
		return channeluseruid;
	}

	public void setChanneluseruid(String channeluseruid) {
		this.channeluseruid = channeluseruid;
	}

	public int getIsaccount() {
		return isaccount;
	}

	public void setIsaccount(int isaccount) {
		this.isaccount = isaccount;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
}
