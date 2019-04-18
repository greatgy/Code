package com.supergenius.xo.account.entity;

import java.util.Locale;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;

/**
 * 充值表
 * @author liushaomin
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Account extends BaseEntity{

	private static final long serialVersionUID = -472715697989788824L;
	@Json(strategy = Json.webStrategy)
	private String accountsn;//流水号
	private String useruid;//用户Uid
	@Json(strategy = Json.webStrategy)
	private String username;//用户姓名
	@Json(strategy = Json.webStrategy)
	private String useremail;//用户邮箱
	private String userip;//用户ip
	private String resulturl;//充值结果页的url
	private String notifyurl;//回调url
	private ESite site;//平台
	private String payuid;//支付uid
	private ECharge type;//类型,0:充值 1会员费
	@Json(strategy = Json.webStrategy)
	private double money;//充值金额
	private double available;//可退款金额
	private EBank bank;//金额来自的银行
	private String desc;//描述
	private DateTime successtime;//成功时间
	private DateTime failedtime;//失败时间
	private EState state;//支付状态
	
	public String getAccountsn() {
		return accountsn;
	}
	
	public void setAccountsn(String accountsn) {
		this.accountsn = accountsn;
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
	
	public String getUseremail() {
		return useremail;
	}
	
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	public String getUserip() {
		return userip;
	}
	
	public void setUserip(String userip) {
		this.userip = userip;
	}
	
	public String getResulturl() {
		return resulturl;
	}
	
	public void setResulturl(String resulturl) {
		this.resulturl = resulturl;
	}
	
	public String getNotifyurl() {
		return notifyurl;
	}
	
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
	
	public ESite getSite() {
		return site;
	}
	
	public void setSite(ESite site) {
		this.site = site;
	}

	@Json(strategy = Json.webStrategy)
	public String getSiteName() {
		return ESite.getName(site, Locale.CHINA);
	}
	
	public String getPayuid() {
		return payuid;
	}
	
	public void setPayuid(String payuid) {
		this.payuid = payuid;
	}
	
	public ECharge getType() {
		return type;
	}
	
	public void setType(ECharge type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return ECharge.getName(type, Locale.CHINA);
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public EBank getBank() {
		return bank;
	}

	public void setBank(EBank bank) {
		this.bank = bank;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getBankName() {
		return EBank.getName(bank, Locale.CHINA);
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public DateTime getSuccesstime() {
		return successtime;
	}
	
	public void setSuccesstime(DateTime successtime) {
		this.successtime = successtime;
	}
	
	public DateTime getFailedtime() {
		return failedtime;
	}
	
	public void setFailedtime(DateTime failedtime) {
		this.failedtime = failedtime;
	}
	
	public EState getState() {
		return state;
	}
	
	public void setState(EState state) {
		this.state = state;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getStateName() {
		return EState.getName(state, Locale.CHINA);
	}

	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}
	
}