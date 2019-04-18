package com.supergenius.xo.manager.entity;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EConfer;

/**
 * 会议室实体类
 * @author XieMing
 * @date 2016-7-17 下午12:05:12
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Conference extends BaseEntity {

	private static final long serialVersionUID = 1871959766493872864L;
	@Json(strategy = Json.webStrategy)
	private String sn;//会议室编号
	private String typeuid;//ref(挑战,答辩等)uid
	@Json(strategy = Json.webStrategy)
	private String typename;//ref(挑战,答辩等)名称
	private String cid;//会议室ID
	@Json(strategy = Json.webStrategy)
	private String name;//会议室名称
	@Json(strategy = Json.webStrategy)
	private String password;//会议室密码
	@Json(strategy = Json.webStrategy)
	private String passwordadmin;//会议室管理密码
	@Json(strategy = Json.webStrategy)
	private DateTime begintime;//会议室开始时间
	@Json(strategy = Json.webStrategy)
	private DateTime endtime;//会议室结束时间
	private String grouptype;//集群类型
	@Json(strategy = Json.webStrategy)
	private int maxcount;//最大数量
	@Json(strategy = Json.webStrategy)
	private int maxcountuser;//最大与会人数
	@Json(strategy = Json.webStrategy)
	private int maxcounttourist;//最大观众人数
	@Json(strategy = Json.webStrategy)
	private int maxcountspokesman;//最大主席人数
	@Json(strategy = Json.webStrategy)
	private int expectjoincount;//预期与会人数
	@Json(strategy = Json.webStrategy)
	private int realjoincount;//实际与会人数
	private boolean isopen;//是否公开,允许任何人参加（1是，0否）,可不填，默认为1
	private boolean islock;//锁定会议室（1是，0否）,可不填，默认为0
	private boolean isautoclear;//自动清空会议数据（1是，0否），可不填，默认为1
	private boolean isallvisible;//所有人可见（0是，1否），可不填，默认为1
	private String args;//会议室参数，回车分割
	@Json(strategy = Json.webStrategy)
	private String description;//会议描述
	@Json(strategy = Json.webStrategy)
	private EConfer type;//会议室类型
	private boolean istop;//是否置顶
	//status:init 未开始  enable 进行中  end 已关闭     disable 已删除 
	// 点击会议室管理，遍历数据库中所有的会议室，拿当前时间和会议室的开始、结束时间比较，就可以获得会议室的状态,然后进行修改状态
	
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getTypeuid() {
		return typeuid;
	}
	
	public void setTypeuid(String typeuid) {
		this.typeuid = typeuid;
	}
	
	public String getTypename() {
		return typename;
	}
	
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public String getCid() {
		return cid;
	}
	
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordadmin() {
		return passwordadmin;
	}
	
	public void setPasswordadmin(String passwordadmin) {
		this.passwordadmin = passwordadmin;
	}
	
	public DateTime getBegintime() {
		if (begintime != null)
			return begintime;
		return null;
	}

	public void setBegintime(DateTime begintime) {
		this.begintime = begintime;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getBegintimeStr() {
		if (begintime != null)
			return begintime.toString(DateUtil.FORMAT_DATETIME_CHINA);
		return null;
	}
	
	public void setBegintimeStr(String time) {
		this.begintime = DateUtil.parse(time);//因页面显示begintimeStr时间，故这样设置
	}

	public DateTime getEndtime() {
		return endtime;
	}

	@Json(strategy = Json.webStrategy)
	public String getEndtimeStr() {
		return endtime.toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	public void setEndtimeStr(String time) {
		this.endtime = DateUtil.parse(time);//因页面显示endtimeStr时间，故这样设置
	}
	
	public void setEndtime(DateTime endtime) {
		this.endtime = endtime;
	}

	public int getExpectJoinCount() {
		return expectjoincount;
	}

	public void setExpectJoinCount(int expectjoincount) {
		this.expectjoincount = expectjoincount;
	}

	public int getRealJoinCount() {
		return realjoincount;
	}

	public void setRealJoinCount(int realjoincount) {
		this.realjoincount = realjoincount;
	}
	
	public String getGrouptype() {
		return grouptype;
	}
	
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	
	public int getMaxcount() {
		return maxcount;
	}
	
	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}
	
	public int getMaxcountuser() {
		return maxcountuser;
	}
	
	public void setMaxcountuser(int maxcountuser) {
		this.maxcountuser = maxcountuser;
	}
	
	public int getMaxcounttourist() {
		return maxcounttourist;
	}
	
	public void setMaxcounttourist(int maxcounttourist) {
		this.maxcounttourist = maxcounttourist;
	}
	public int getMaxcountspokesman() {
		return maxcountspokesman;
	}
	
	public void setMaxcountspokesman(int maxcountspokesman) {
		this.maxcountspokesman = maxcountspokesman;
	}
	
	public boolean isIsopen() {
		return isopen;
	}
	
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
	
	public boolean isIslock() {
		return islock;
	}
	
	public void setIslock(boolean islock) {
		this.islock = islock;
	}
	
	public boolean isIsautoclear() {
		return isautoclear;
	}
	
	public void setIsautoclear(boolean isautoclear) {
		this.isautoclear = isautoclear;
	}
	
	public boolean isIsallvisible() {
		return isallvisible;
	}
	
	public void setIsallvisible(boolean isallvisible) {
		this.isallvisible = isallvisible;
	}
	
	public String getArgs() {
		return args;
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public EConfer getType() {
		return type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return type.getName();
	}
	
	public void setType(EConfer type) {
		this.type = type;
	}
	
	public boolean getIstop() {
		return istop;
	}
	
	public void setIstop(boolean istop) {
		this.istop = istop;
	}

}
