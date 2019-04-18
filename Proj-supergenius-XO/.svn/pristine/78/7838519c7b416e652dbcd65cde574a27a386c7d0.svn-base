package com.supergenius.xo.tpi.entity;

import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.ECurrency;
import com.supergenius.xo.tpi.enums.EMoneyUnit;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.EProjectState;

/**
 * 并购项目
 * 
 * @author ShangJianguo
 */
@Json(value = {"uid"}, ignoreTypeStrategy = Json.defaultStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Project extends BaseEntity {

	private static final long serialVersionUID = 7402704740846380326L;

	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String name;// 项目名称
	@Json(strategy = {Json.webStrategy, Json.defaultStrategy})
	private String img;// 配图(存储配图地址)
	@Json(strategy = Json.webStrategy)
	private double minmergefund;// 最小并购资金
	@Json(strategy = Json.webStrategy)
	private EMoneyUnit moneyunit;// 单位
	@Json(strategy = Json.webStrategy)
	private ECurrency currency;// 币种
	@Json(strategy = Json.webStrategy)
	private String number;// 编号
	@Json(strategy = Json.webStrategy)
	private EProjectChannel channel;//推荐种类
	@Json(strategy = Json.webStrategy)
	private String fromname;//推荐人姓名
	private String fromuid;//推荐人uid
	@Json(strategy = Json.webStrategy)
	private String typeuid;// 并购类型
	@Json(strategy = Json.webStrategy)
	private int level = 0;// 推荐级别
	@Json(strategy = Json.webStrategy)
	private int ratiohigh;// 管理层（高层）更新建议比例（0-100之间）
	@Json(strategy = Json.webStrategy)
	private int ratiomedium;// 管理层（中层）更新建议比例（0-100之间）
	@Json(strategy = Json.webStrategy)
	private int minchangenum;// 变更团队最低进入人数
	@Json(strategy = Json.webStrategy)
	private String suitcountry;// 适合收购的并购企业国籍
	@Json(strategy = Json.webStrategy)
	private String mabackground;// 并购背景
	@Json(strategy = Json.webStrategy)
	private String recommondreason;// 推荐理由
	@Json(strategy = Json.webStrategy)
	private EProjectState state;// 进度
	@Json(strategy = Json.webStrategy)
	private boolean istop;//是否置顶
	@Json(strategy = Json.webStrategy)
	private boolean isrecommend;//是否在首页显示
	@Json(strategy = Json.webStrategy)
	private boolean ispublic;// 是否公开
	@Json(strategy = Json.webStrategy)
	private boolean ischerished;// 是否魂牵梦绕
	@Json(strategy = Json.webStrategy)
	private MergerIndicator mergerindicator = new MergerIndicator();// 并购指标
	@Json(strategy = Json.webStrategy)
	private Link report = new Link();//研究报告
	@Json(strategy = Json.webStrategy)
	private List<Link> relatedarticles;//相关文章
	@Json(strategy = Json.webStrategy)
	private String typeName;//类别的名字，在type类中获取，不保存
	private int wantmnum;//想并购
	private int wantinum;//想投资
	private int countnum;//支持者

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getMinmergefund() {
		return minmergefund;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getUnitcurrency() {
		return getMoneyunitName() + getCurrencyName();
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getMoneyunitName() {
		return EMoneyUnit.getName(moneyunit, Locale.CHINA);
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getCurrencyName() {
		return ECurrency.getName(currency, Locale.CHINA);
	}
	
	public void setMinmergefund(double minmergefund) {
		this.minmergefund = minmergefund;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public EProjectChannel getChannel() {
		return channel;
	}

	public void setChannel(EProjectChannel channel) {
		this.channel = channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return EProjectChannel.getName(channel, Locale.CHINA);
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsSuperGenius(){
		return this.channel == EProjectChannel.supergenius;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsAgency(){
		return this.channel == EProjectChannel.agency;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsPersonal(){
		return this.channel == EProjectChannel.personal;
	}
	
	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}

	public String getTypeuid() {
		return typeuid;
	}

	public void setTypeuid(String typeuid) {
		this.typeuid = typeuid;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRatiohigh() {
		return ratiohigh;
	}

	public void setRatiohigh(int ratiohigh) {
		this.ratiohigh = ratiohigh;
	}

	public int getRatiomedium() {
		return ratiomedium;
	}

	public void setRatiomedium(int ratiomedium) {
		this.ratiomedium = ratiomedium;
	}

	public int getMinchangenum() {
		return minchangenum;
	}

	public void setMinchangenum(int minchangenum) {
		this.minchangenum = minchangenum;
	}

	public String getSuitcountry() {
		return suitcountry;
	}

	public void setSuitcountry(String suitcountry) {
		this.suitcountry = suitcountry;
	}
	
	public String getMabackground() {
		return mabackground;
	}

	public void setMabackground(String mabackground) {
		this.mabackground = mabackground;
	}

	public String getRecommondreason() {
		return recommondreason;
	}

	public void setRecommondreason(String recommondreason) {
		this.recommondreason = recommondreason;
	}

	public EProjectState getState() {
		return state;
	}

	public void setState(EProjectState state) {
		this.state = state;
	}

	@Json(strategy = Json.webStrategy)
	public String getStateName() {
		return EProjectState.getName(state, Locale.CHINA);
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

	public boolean isIspublic() {
		return ispublic;
	}

	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
	
	public boolean isIscherished() {
		return ischerished;
	}

	public void setIscherished(boolean ischerished) {
		this.ischerished = ischerished;
	}
	
	public MergerIndicator getMergerindicator() {
		return mergerindicator;
	}

	public void setMergerindicator(MergerIndicator mergerindicator) {
		this.mergerindicator = mergerindicator;
	}
	
	public Link getReport() {
		return report;
	}

	public void setReport(Link report) {
		this.report = report;
	}

	public List<Link> getRelatedarticles() {
		return relatedarticles;
	}

	public void setRelatedarticles(List<Link> relatedarticles) {
		this.relatedarticles = relatedarticles;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return typeName;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getWantmnum() {
		return wantmnum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWantmnum(int wantmnum) {
		this.wantmnum = wantmnum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getWantinum() {
		return wantinum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setWantinum(int wantinum) {
		this.wantinum = wantinum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public int getCountnum() {
		return countnum;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}
	

	/**
	 * set
	 * @param pro
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void set(Project pro) {
		this.channel = pro.getChannel();
		this.state = pro.getState();
		this.istop = pro.istop;
		this.ispublic = pro.ispublic;
		this.ischerished = pro.ischerished;
		this.isrecommend = pro.isrecommend;
		this.number = pro.getNumber();
		this.status = pro.getStatus();
	}
	
	public EMoneyUnit getMoneyunit() {
		return moneyunit;
	}

	public void setMoneyunit(EMoneyUnit moneyunit) {
		this.moneyunit = moneyunit;
	}

	public ECurrency getCurrency() {
		return currency;
	}

	public void setCurrency(ECurrency currency) {
		this.currency = currency;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.name != null) {
			this.name = WebUtil.clearXSS(this.name);
		}
		if (this.suitcountry != null) {
			this.suitcountry = WebUtil.clearXSS(this.suitcountry);
		}
		if (this.mabackground != null) {
			this.mabackground = WebUtil.clearXSS(this.mabackground);
		}
	}
	
}
