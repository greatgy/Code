package com.supergenius.xo.account.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.common.enums.ESite;

/**
 * 退款表
 * 
 * @author YangGuang
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Refund extends BaseEntity {

	private static final long serialVersionUID = -472715697989788824L;
	private String accountuid;// 账户记录的Uid
	private String out_refund_no;// 商户退款单号
	private String transaction_id;// 微信、支付宝等订单号
	private String username;// 用户姓名
	private String useruid;// 用户id
	private String useremail;// 用户邮箱
	private ESite site;// 平台
	private EBank bank;// 金额来自的银行
	private String currency;// 货币类型
	private String adminuid;
	private double money;// 充值金额
	private double refundmoney;// 充值金额
	private String desc;// 描述
	private String data;// 备用信息
	private ERefundState state;// 退款状态os

	public String getAccountuid() {
		return accountuid;
	}

	public void setAccountuid(String accountuid) {
		this.accountuid = accountuid;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
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

	public EBank getBank() {
		return bank;
	}

	public void setBank(EBank bank) {
		this.bank = bank;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getRefundmoney() {
		return refundmoney;
	}

	public void setRefundmoney(double refundmoney) {
		this.refundmoney = refundmoney;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ERefundState getState() {
		return state;
	}

	public void setState(ERefundState state) {
		this.state = state;
	}

	@Json(strategy = Json.webStrategy)
	public String getBankName() {
		return EBank.getName(bank, Locale.CHINA);
	}
}