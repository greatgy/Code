package com.supergenius.xo.account.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;

/**
 * 充值日志
 * @author liushaomin
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class AccountLog extends BaseEntity{

	private static final long serialVersionUID = -7709608995914550903L;
	
	private String accountuid;//账户记录的Uid
	private String accountsn;//账户记录的sn
	private String useruid;//用户uid
	private String username;//用户姓名
	private String useremail;//用户邮箱
	private EBank bank;//操作的银行
	private EState accountstatefrom;//改变前状态
	private EState accountstateto;//改变后状态
	private double money;//操作金额
	private String desc;//描述
	
	/**
	 * @param account
	 */
	public AccountLog(Account account) {
		this.setAccountuid(account.getUid());
		this.setAccountsn(account.getAccountsn());
		this.setUseruid(account.getUseruid());
		this.setUsername(account.getUsername());
		this.setUseremail(account.getUseremail());
		this.setBank(account.getBank());
		this.setMoney(account.getMoney());//操作金额
		this.setAccountstateto(account.getState());
	}

	public String getAccountuid() {
		return accountuid;
	}
	
	public void setAccountuid(String accountuid) {
		this.accountuid = accountuid;
	}
	
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
	
	public EBank getBank() {
		return bank;
	}
	
	public void setBank(EBank bank) {
		this.bank = bank;
	}
	
	public EState getAccountstatefrom() {
		return accountstatefrom;
	}
	
	public void setAccountstatefrom(EState accountstatefrom) {
		this.accountstatefrom = accountstatefrom;
	}
	
	public EState getAccountstateto() {
		return accountstateto;
	}
	
	public void setAccountstateto(EState accountstateto) {
		this.accountstateto = accountstateto;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
