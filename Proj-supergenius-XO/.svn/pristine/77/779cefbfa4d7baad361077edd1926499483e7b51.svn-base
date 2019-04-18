package com.supergenius.xo.user.entity;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.enums.ETrade;

/** 
 * 交易明细实体
 * @author chenminchang
 * @date 2016-3-24 上午9:23:11 
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class TradeDetail extends BaseEntity {

	private static final long serialVersionUID = -7452853372543931607L;
	@Json(strategy = Json.webStrategy)
	private String useruid;//用户uid
	private String tradeeventuid;//交易事件uid
	@Json(strategy = Json.webStrategy)
	private double money;//金额
	@Json(strategy = Json.webStrategy)
	private double accountcurr;//余额
	@Json(strategy = Json.webStrategy)
	private String sn;//流水号
	private String orderuid;//订单uid
	@Json(strategy = Json.webStrategy)
	private ETrade type;//交易类型交易类型(充值、视频收入、扣除挑战费、作为裁判奖励、购买视频、购买门票、购买道具、取消门票、管理员退票、申请答辩、冻结挑战费用、解冻挑战费用、挑战获胜奖励、特批增加金额、特批减少金额、积分晋级)',
	private String name;//(充值、视频收入、扣除挑战费、作为裁判奖励、购买视频、购买门票、购买道具、取消门票、管理员退票、申请答辩、冻结挑战费用、解冻挑战费用、挑战获胜奖励、特批增加金额、特批减少金额)',
	@Json(strategy = Json.webStrategy)
	private ESite site;//来源平台
	private DateTime successtime;//成功时间
	private DateTime failedtime;//失败时间 
	private String memo;//备注
	@Json(strategy = Json.webStrategy)
	private User user;//交易所属用户

	public TradeDetail() {
		super();
	}

	/**
	 * 购买门票或者视频生成对应的记录的构造方法
	 * @param useruid
	 * @param tradeeventuid
	 * @param money
	 * @param accountcurr
	 * @param sn
	 * @param orderuid
	 * @param type
	 * @param name
	 * @param site
	 * @author: XieMing
	 */
	public TradeDetail(String useruid, String tradeeventuid, double money, double accountcurr, String sn, String orderuid, ETrade type, String name, ESite site) {
		super();
		this.useruid = useruid;
		this.tradeeventuid = tradeeventuid;
		this.money = money;
		this.accountcurr = accountcurr;
		this.sn = sn;
		this.orderuid = orderuid;
		this.type = type;
		this.name = name;
		this.site = site;
	}
	
	/**
	 * 判断是否为收入
	 * @return
	 */
	public boolean isIsincome() {
		return ETrade.isIncome(this.type);
	}
	
	public ETrade getType() {
		return type;
	}

	public void setType(ETrade type) {
		this.type = type;
	}

	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}
	
	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getTradeeventuid() {
		return tradeeventuid;
	}

	public void setTradeeventuid(String tradeeventuid) {
		this.tradeeventuid = tradeeventuid;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}

	public double getAccountcurr() {
		return accountcurr;
	}

	public void setAccountcurr(double accountcurr) {
		this.accountcurr = accountcurr;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getOrderuid() {
		return orderuid;
	}

	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ESite getSite() {
		return site;
	}

	public void setSite(ESite site) {
		this.site = site;
	}

	@Json(strategy = Json.webStrategy)
	public String getSiteName() {
		return this.site.getName();
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserName() {
		return this.user.getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUsersn() {
		return this.user.getUsersn();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getEmail() {
		return this.user.getEmail();
	}
}
