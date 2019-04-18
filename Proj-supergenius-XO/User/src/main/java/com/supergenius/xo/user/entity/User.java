package com.supergenius.xo.user.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.MathUtil;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * User实体类
 * 
 * @author chenminchang
 * @date 2016-3-24 上午11:33:15
 */
@Json(value = {"uid", "oid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.allStrategy)
public class User extends UserInfo {

	private static final long serialVersionUID = 8059434763938508136L;
	@Json(strategy = Json.allStrategy)
	private String usersn;// 会员号
	@Json(strategy = Json.allStrategy)
	private String email;// 邮箱
	private String newemail;// 新邮箱
	private String validcode;// 邮箱验证码
	private String mobile;// 手机，页面用它
	private String phone;// 电话
	private String openid;// 第三方身份id
	private String password;// 密码
	private String paypwd;// 支付密码
	private String resetpwd;// 重置密码value
	@Json(strategy = Json.allStrategy)
	private double account;// 账户余额
	private double freezeaccount;// 冻结金额
	private double totalpay;// 累计支出
	private double totalincome;// 累计收入 
	@Json(strategy = Json.allStrategy)
	private int type;// 用户类别，如普通用户、会员、学员、裁判、专家等
	private List<EUser> listtype = new ArrayList<>();//此user的所有type
	@Json(strategy = Json.allStrategy)
	private EUserChannel channelfrom;// 来自渠道
	private int logincount;// 登录次数
	@Json(strategy = Json.allStrategy)
	private DateTime lastlogintime;// 最后登录时间
	@Json(strategy = Json.allStrategy)
	private String lastloginip;// 最后登录IP
	private Map<String, Object> data;// 保存用户状态(如保存发送重置密码邮件时间等)

	private UserInfo info = new UserInfo();

	private static final String REASON = "reason";
	private static final String RESETPWDTIME = "resetpwdtime";
	private static final String RESETPAYPWDTIME = "resetpaypwdtime";
	private static final String RESETPAYPWDCODE = "resetpaypwdcode";
	
	public UserInfo getUserInfo() {
		this.info.setUid(this.getUid());
		return info;
	}
	
	@Json(strategy = Json.allStrategy)
	public String getId() {
		if (StringUtils.isNotEmpty(id)) {
			return id;
		} else if (oid > 0) {
			return String.valueOf(oid);
		} else {
			return this.getUid();
		}
	}

	public void setUserInfo(UserInfo userInfo) {
		this.info = userInfo;
	}

	protected UserInfo getThis() {
		return this.info;
	}

	public String getUsersn() {
		return usersn;
	}

	public void setUsersn(String usersn) {
		this.usersn = usersn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewemail() {
		return newemail;
	}

	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}

	public String getValidcode() {
		return validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkPwd(String pwd) {
		if (StringUtils.isEmpty(pwd)) {
			return false;
		}
		return DigestUtils.md5Hex(this.uid + pwd).equals(this.getPassword());
	}

	public void initPassword(String pwd) {
		this.password = DigestUtils.md5Hex(this.getUid() + pwd);
	}

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}

	public void initPayPwd(String paypwd) {
		this.paypwd = DigestUtils.md5Hex(this.getUid() + paypwd);
	}

	public boolean checkPayPwd(String paypwd) {
		if (StringUtils.isEmpty(paypwd)) {
			return false;
		}
		return DigestUtils.md5Hex(this.uid + paypwd).equals(this.getPaypwd());
	}

	public String getResetpwd() {
		return resetpwd;
	}

	public void setResetpwd(String resetpwd) {
		this.resetpwd = resetpwd;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public void plusAccount(double account) {
		this.account = MathUtil.add(this.account, account);
	}
	
	public void subtractAccount(double account) {
		this.account = MathUtil.subtract(this.account, account);
	}
	
	public void plusFreezeAccount(double cost) {
		this.account = MathUtil.add(this.freezeaccount, cost);
	}
	
	public void subtractFreezeAccount(double cost) {
		this.account = MathUtil.subtract(this.freezeaccount, cost);
	}
	public double getFreezeaccount() {
		return freezeaccount;
	}

	public void setFreezeaccount(double freezeaccount) {
		this.freezeaccount = freezeaccount;
	}

	public double getTotalpay() {
		return totalpay;
	}

	public void setTotalpay(double totalpay) {
		this.totalpay = totalpay;
	}
	
	public void plusTotalpay(double totalpay) {
		this.totalpay = MathUtil.add(this.totalpay, totalpay);
	}

	public double getTotalincome() {
		return totalincome;
	}

	public void setTotalincome(double totalincome) {
		this.totalincome = totalincome;
	}
	
	public void plusTotalincome(double totalincome) {
		this.totalincome = MathUtil.add(this.totalincome, totalincome);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void minusType(EUser e) {
		this.type = this.type ^ Integer.valueOf(e.toString());
		this.listtype.remove(e);
	}

	public void plusType(EUser e) {
		if (e != null) {
			this.type = this.type | Integer.valueOf(e.toString());
			this.listtype.add(e);
		}
	}
	
	public List<EUser> getTypes() {
		if (listtype.size() == 0) {
			listtype = EUser.getMatch(this.type);
		}
		return listtype;
	}
	
	public boolean getIsAll() {
		return EUser.ismatch(type, EUser.all);
	}
	
	public boolean getIsUser() {
		return (EUser.ismatch(type, EUser.user) || EUser.ismatch(type, EUser.student) || EUser.ismatch(type, EUser.judgement) || EUser.ismatch(type, EUser.expert) || EUser.ismatch(type, EUser.studentMoral) || EUser.ismatch(type, EUser.enterpriser));
	}
	
	@Json(strategy = Json.allStrategy)
	public boolean getIsStudent() {
		return EUser.ismatch(type, EUser.student);
	}
	
	public boolean getIsJudgement() {
		return EUser.ismatch(type, EUser.judgement);
	}
	
	public boolean getIsExpert() {
		return EUser.ismatch(type, EUser.expert);
	}
	
	public boolean getIsStudentMoral() {
		return EUser.ismatch(type, EUser.studentMoral);
	}
	
	public boolean getIsEnterpriser() {
		return EUser.ismatch(type, EUser.enterpriser);
	}

	public EUserChannel getChannelfrom() {
		return channelfrom;
	}

	public void setChannelfrom(EUserChannel channelfrom) {
		this.channelfrom = channelfrom;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelfromName() {
		if(this.channelfrom == null) {
			return null;
		} else {
			return this.channelfrom.getName();
		}
	}
	
	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public DateTime getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(DateTime lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	@Json(strategy = Json.webStrategy)
	public String getLastlogintimeStr() {
		if(getLastlogintime() == null){
			return  null;
		} else {
			return getLastlogintime().toString(DateUtil.FORMAT_DATETIME_CHINA);
		}
	}
	
	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getData() {
		return JsonUtil.toJson(data);
	}

	@SuppressWarnings("unchecked")
	public void setData(String json) {
		this.data = JsonUtil.fromJson(json, Map.class);
	}

	/**
	 * 以map形式获取data中数据
	 * 
	 * @return
	 * @author XieMing
	 */
	public Map<String, Object> getDataMap() {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		return this.data;
	}

	/**
	 * 获取data中的resetpwd的值
	 * 
	 * @return
	 * @author XieMing
	 */
	public DateTime getDataMap_resetpwdtime() {
		if (this.getDataMap().containsKey(RESETPWDTIME)) {
			return new DateTime(this.getDataMap().get(RESETPWDTIME));
		}
		return null;
	}
	
	public DateTime getDataMap_resetpaypwdtime() {
		if (this.getDataMap().containsKey(RESETPAYPWDTIME)) {
			return new DateTime(this.getDataMap().get(RESETPAYPWDTIME));
		}
		return null;
	}
	
	public String getDataMap_resetpaypwdcode() {
		if (this.getDataMap().containsKey(RESETPAYPWDCODE)) {
			return this.getDataMap().get(RESETPAYPWDCODE).toString();
		}
		return null;
	}

	/**
	 * 设置发送重置密码邮件时间
	 * 
	 * @author XieMing
	 */
	public void setDataMap_resetpwdtime() {
		this.getDataMap().put(RESETPWDTIME, DateUtil.Now());
	}
	
	public void setDataMap_resetpaypwdtime(DateTime time) {
		this.getDataMap().put(RESETPAYPWDTIME, time);
	}
	
	public void setDataMap_resetpaypwdcode(String code) {
		this.getDataMap().put(RESETPAYPWDCODE, code);
	}
	
	/**
	 * 设置特批原因
	 * @param reason
	 * @author XieMing
	 * 2016-5-17 下午12:27:53
	 */
	public void setDataMap_reason(String reason) {
		this.getDataMap().put(REASON, reason);
	}

	@Json(strategy = Json.webStrategy, alias=REASON)
	public String getDataMap_reason() {
		if(this.getDataMap().get(REASON) == null){
			return "";
		}
		return this.getDataMap().get(REASON).toString();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}