package com.supergenius.xo.manager.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.BaseEntity;
import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.enums.ETicket;

/** 
 * pk日程表
 * @author chenminchang
 * @date 2016-7-17 下午5:03:59 
 */
public class PKSchedule extends BaseEntity {

	private static final long serialVersionUID = 372246697454228387L;
	@Json(strategy = Json.webStrategy)
	private String sn; //挑战编号
	@Json(strategy = Json.webStrategy)
	private String confeuid; //会议室UID
	private String confesn; //会议室编号
	@Json(strategy = Json.webStrategy)
	private String name; //挑战名称
	@Json(strategy = Json.webStrategy)
	private EMajor major; //挑战专业
	private EStudentLevel level; //挑战级别
	private boolean isonline; //是否在线挑战
	@Json(strategy = Json.webStrategy)
	private String pkuseruid; //挑战者UID
	private EStudentLevel pkuserlevel; //挑战者级别
	@Json(strategy = Json.webStrategy)
	private String judgementuid; //挑战者裁判useruid
	@Json(strategy = Json.webStrategy)
	private String judgementsn; //挑战者裁判编号
	private String pkdates; //挑战者推荐的时间(10个时间用,分隔)
	private double judgmentfee; //挑战者裁判最终获得的费用
	@Json(strategy = Json.webStrategy)
	private boolean isallowpkuser; //挑战者是否加入视频点播系统(0否1是)
	private double userscore; //挑战者分数
	private boolean isallowjudgement; //挑战者裁判是否加入视频点播系统(0否1是)
	@Json(strategy = Json.webStrategy)
	private String pkuseruid2; //被挑战者UID
	private EStudentLevel pkuserlevel2; //被挑战者级别
	@Json(strategy = Json.webStrategy)
	private String judgementuid2; //被挑战者裁判useruid
	@Json(strategy = Json.webStrategy)
	private String judgementsn2; //被挑战者裁判编号
	private String pkdates2; //被挑战者推荐的时间(10个时间用,分隔)
	private double judgmentfee2; //被挑战者裁判最终获得的费用
	@Json(strategy = Json.webStrategy)
	private double userscore2; //被挑战者分数
	private boolean isallowpkuser2; //被挑战者是否加入视频点播系统(0否1是)
	private boolean isallowjudgement2; //被挑战者裁判是否加入视频点播系统(0否1是)
	@Json(strategy = Json.webStrategy)
	private String judgementchairuid; //主裁判useruid
	@Json(strategy = Json.webStrategy)
	private String judgementchairsn; //主裁判编号
	private DateTime pktime; //挑战开始时间
	private DateTime pktimeend; //挑战结束时间
	@Json(strategy = Json.webStrategy)
	private double pkprice; //挑战费用
	private double judgementchairfee; //主裁判最终获得的费用
	@Json(strategy = Json.webStrategy)
	private double ticketprice; //门票价格
	@Json(strategy = Json.webStrategy)
	private String file; //挑战题目
	private boolean istop; //是否置顶
	@Json(strategy = Json.webStrategy)
	private EPKStage stage; //挑战详细状态
	@Json(strategy = Json.webStrategy)
	private ETicket ticketstatus; //门票状态
	private int ticketcount; //门票总数
	@Json(strategy = Json.webStrategy)
	private int ticketsalecount; //已出售门票数量
	private String desc;//挑战详情描述
	@Json(strategy = Json.webStrategy)
	private String qqgroup;//挑战视频qq群
	private String data;//存放一些数据
	//后台显示不存入数据库
	private String pkusername; //挑战者姓名
	private String pkusername2; //被挑战者姓名
	private String judgementname; //挑战者裁判姓名
	private String judgementname2; //被挑战者裁判姓名
	private String judgementchairname; //主挑战者裁判姓名
	private String pkstagename; //挑战进度
	private String pkresult; // 挑战结果
	private String conferencename;//会议室名称
	@Json(strategy = Json.webStrategy)
	private List<PKJudgement> pkjudgements;//挑战者和被挑战者的裁判

	public PKSchedule() {
		super();
	}
	
	public PKSchedule(String sn, String name, EMajor major, EStudentLevel level, boolean isonline,
			String pkuseruid, EStudentLevel pkuserlevel, String pkdates, String pkuseruid2,
			EStudentLevel pkuserlevel2, double pkprice, double ticketprice, EPKStage stage, EStatus status) {
		super();
		this.sn = sn;
		this.name = name;
		this.major = major;
		this.level = level;
		this.isonline = isonline;
		this.pkuseruid = pkuseruid;
		this.pkuserlevel = pkuserlevel;
		this.pkdates = pkdates;
		this.pkuseruid2 = pkuseruid2;
		this.pkuserlevel2 = pkuserlevel2;
		this.pkprice = pkprice;
		this.ticketprice = ticketprice;
		this.stage = stage;
		this.status = status;
	}
	
	public List<PKJudgement> getPkjudgements() {
		return pkjudgements;
	}

	public void setPkjudgements(List<PKJudgement> pkjudgements) {
		this.pkjudgements = pkjudgements;
	}

	@Json(strategy = Json.webStrategy)
	public String getConferencename() {
		return conferencename;
	}

	public void setConferencename(String conferencename) {
		this.conferencename = conferencename;
	}

	@Json(strategy = Json.webStrategy)
	public String getPkresult() {
		return pkresult;
	}

	public void setPkresult(String pkresult) {
		this.pkresult = pkresult;
	}

	@Json(strategy = Json.webStrategy)
	public String getPkstagename() {
		return pkstagename;
	}

	public void setPkstagename(String pkstagename) {
		this.pkstagename = pkstagename;
	}

	@Json(strategy = Json.webStrategy)
	public String getJudgementname() {
		return judgementname;
	}

	public void setJudgementname(String judgementname) {
		this.judgementname = judgementname;
	}

	@Json(strategy = Json.webStrategy)
	public String getJudgementname2() {
		return judgementname2;
	}

	public void setJudgementname2(String judgementname2) {
		this.judgementname2 = judgementname2;
	}

	@Json(strategy = Json.webStrategy)
	public String getJudgementchairname() {
		return judgementchairname;
	}

	public void setJudgementchairname(String judgementchairname) {
		this.judgementchairname = judgementchairname;
	}

	@Json(strategy = Json.webStrategy)
	public String getPkusername() {
		return pkusername;
	}

	public void setPkusername(String pkusername) {
		this.pkusername = pkusername;
	}

	@Json(strategy = Json.webStrategy)
	public String getPkusername2() {
		return pkusername2;
	}

	public void setPkusername2(String pkusername2) {
		this.pkusername2 = pkusername2;
	}

	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getConfeuid() {
		return confeuid;
	}
	
	public void setConfeuid(String confeuid) {
		this.confeuid = confeuid;
	}
	
	public String getConfesn() {
		return confesn;
	}
	
	public void setConfesn(String confesn) {
		this.confesn = confesn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public EMajor getMajor() {
		return major;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getMajorname() {
		return this.major.getName();
	}
	
	public void setMajor(EMajor major) {
		this.major = major;
	}
	
	public EStudentLevel getLevel() {
		return level;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getLevelname() {
		return this.level.getName();
	}
	
	public void setLevel(EStudentLevel level) {
		this.level = level;
	}
	
	public boolean isIsonline() {
		return isonline;
	}
	
	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}
	
	public String getPkuseruid() {
		return pkuseruid;
	}
	
	public void setPkuseruid(String pkuseruid) {
		this.pkuseruid = pkuseruid;
	}
	
	public EStudentLevel getPkuserlevel() {
		return pkuserlevel;
	}
	
	public String getPkuserlevelname() {
		return this.pkuserlevel.getName();
	}
	
	public void setPkuserlevel(EStudentLevel pkuserlevel) {
		this.pkuserlevel = pkuserlevel;
	}
	
	public String getJudgementuid() {
		return judgementuid;
	}
	
	public void setJudgementuid(String judgementuid) {
		this.judgementuid = judgementuid;
	}
	
	public String getJudgementsn() {
		return judgementsn;
	}
	
	public void setJudgementsn(String judgementsn) {
		this.judgementsn = judgementsn;
	}
	
	public String getPkdates() {
		return pkdates;
	}
	
	public void setPkdates(String pkdates) {
		this.pkdates = pkdates;
	}
	
	public double getJudgmentfee() {
		return judgmentfee;
	}
	
	public void setJudgmentfee(double judgmentfee) {
		this.judgmentfee = judgmentfee;
	}
	
	public double getUserscore() {
		return userscore;
	}
	
	public void setUserscore(double userscore) {
		this.userscore = userscore;
	}
	
	public boolean isIsallowpkuser() {
		return isallowpkuser;
	}
	
	public void setIsallowpkuser(boolean isallowpkuser) {
		this.isallowpkuser = isallowpkuser;
	}
	
	public boolean isIsallowjudgement() {
		return isallowjudgement;
	}
	
	public void setIsallowjudgement(boolean isallowjudgement) {
		this.isallowjudgement = isallowjudgement;
	}
	
	public String getPkuseruid2() {
		return pkuseruid2;
	}
	
	public void setPkuseruid2(String pkuseruid2) {
		this.pkuseruid2 = pkuseruid2;
	}
	
	public EStudentLevel getPkuserlevel2() {
		return pkuserlevel2;
	}
	
	public String getPkuserlevel2name() {
		return this.pkuserlevel2.getName();
	}
	
	public void setPkuserlevel2(EStudentLevel pkuserlevel2) {
		this.pkuserlevel2 = pkuserlevel2;
	}
	
	public String getJudgementuid2() {
		return judgementuid2;
	}
	
	public void setJudgementuid2(String judgementuid2) {
		this.judgementuid2 = judgementuid2;
	}
	
	public String getJudgementsn2() {
		return judgementsn2;
	}
	
	public void setJudgementsn2(String judgementsn2) {
		this.judgementsn2 = judgementsn2;
	}
	
	public String getPkdates2() {
		return pkdates2;
	}
	
	public void setPkdates2(String pkdates2) {
		this.pkdates2 = pkdates2;
	}
	
	public double getJudgmentfee2() {
		return judgmentfee2;
	}
	
	public void setJudgmentfee2(double judgmentfee2) {
		this.judgmentfee2 = judgmentfee2;
	}
	
	public double getUserscore2() {
		return userscore2;
	}
	
	public void setUserscore2(double userscore2) {
		this.userscore2 = userscore2;
	}
	
	public boolean isIsallowpkuser2() {
		return isallowpkuser2;
	}
	
	public void setIsallowpkuser2(boolean isallowpkuser2) {
		this.isallowpkuser2 = isallowpkuser2;
	}
	
	public boolean isIsallowjudgement2() {
		return isallowjudgement2;
	}
	
	public void setIsallowjudgement2(boolean isallowjudgement2) {
		this.isallowjudgement2 = isallowjudgement2;
	}
	
	public String getJudgementchairuid() {
		return judgementchairuid;
	}

	public void setJudgementchairuid(String judgementchairuid) {
		this.judgementchairuid = judgementchairuid;
	}

	public String getJudgementchairsn() {
		return judgementchairsn;
	}
	
	public void setJudgementchairsn(String judgementchairsn) {
		this.judgementchairsn = judgementchairsn;
	}
	
	public DateTime getPktime() {
		return pktime;
	}
	
	public void setPktime(DateTime pktime) {
		this.pktime = pktime;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getPktimeStr() {
		if (StrUtil.isNotEmpty(getPktime())) {
			return getPktime().toString(DateUtil.FORMAT_DATETIME_CHINA);
		}
		return "";
	}
	
	public DateTime getPktimeend() {
		return pktimeend;
	}
	
	public void setPktimeend(DateTime pktimeend) {
		this.pktimeend = pktimeend;
	}
	
	public double getPkprice() {
		return pkprice;
	}
	
	public void setPkprice(double pkprice) {
		this.pkprice = pkprice;
	}
	
	public double getJudgementchairfee() {
		return judgementchairfee;
	}
	
	public void setJudgementchairfee(double judgementchairfee) {
		this.judgementchairfee = judgementchairfee;
	}
	
	public double getTicketprice() {
		return ticketprice;
	}
	
	public void setTicketprice(double ticketprice) {
		this.ticketprice = ticketprice;
	}
	
	public String getQqgroup() {
		return qqgroup;
	}

	public void setQqgroup(String qqgroup) {
		this.qqgroup = qqgroup;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFilename() {
		if (StrUtil.isNotEmpty(file)) {
			return file.substring(file.lastIndexOf(BaseStrDict.slash) + 1, file.lastIndexOf(BaseStrDict.dot));
		}
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFilenameFix() {
		if (StrUtil.isNotEmpty(file)) {
			return file.substring(file.lastIndexOf(BaseStrDict.slash) + 1);
		}
		return null;
	}
	
	public boolean isIstop() {
		return istop;
	}
	
	public void setIstop(boolean istop) {
		this.istop = istop;
	}
	
	public EPKStage getStage() {
		return stage;
	}
	
	public void setStage(EPKStage stage) {
		this.stage = stage;
	}
	
	public String getStagename() {
		return this.stage.getName();
	}
	
	//stage是不是申请中
	public boolean isApping() {
		return EPKStage.isApping(this.stage);
	}
	
	//是不是申请失败
	public boolean isAppFailed() {
		return EPKStage.isAppFailed(this.stage);
	}
	
	//是不是已取消
	public boolean isCancel() {
		return EPKStage.isCancel(this.stage);
	}

	// 是否需要加载购票和预测
	public boolean isNeedLoadMore() {
		if (!isApping() && !isAppFailed())
			return true;
		return false;
	}
	
	//当被挑战者没有操作时，他的头像是模糊的
	public boolean isAvatarBlurry() {
		return EPKStage.isNotHandleFromPKUser2(this.stage);
	}
	
	//当前状态是不是挑战双方确认
	public boolean isPkUsersAccept() {
		return EPKStage.isPKUsersAccept(this.stage);
	}
	
	//当前状态是不是双方裁判确认
	public boolean isPkJudgesAccept() {
		return EPKStage.isPKJudgesAccept(this.stage);
	}
	
	//当前状态是不是挑战者申请
	public boolean isAppingFromPKUser1() {
		return EPKStage.isAppingFromPKUser1(this.stage);
	}
	
	//当前状态是不是已结束
	public boolean isPkOver() {
		return EPKStage.isPKOver(this.stage);
	}
	
	public ETicket getETicket() {
		return ticketstatus;
	}
	
	public void setETicket(ETicket eTicket) {
		ticketstatus = eTicket;
	}
	
	public int getTicketcount() {
		return ticketcount;
	}
	
	public void setTicketcount(int ticketcount) {
		this.ticketcount = ticketcount;
	}
	
	public int getTicketsalecount() {
		return ticketsalecount;
	}
	
	public void setTicketsalecount(int ticketsalecount) {
		this.ticketsalecount = ticketsalecount;
	}
	
	/**
	 * 判断当前用户是不是挑战者或被挑战者
	 * @param useruid
	 * @return
	 * @author chenminchang
	 * @create 2016-8-28下午1:10:09
	 */
	public boolean isPKUser(String useruid) {
		if (this.pkuseruid.equals(useruid) || this.pkuseruid2.equals(useruid))
			return true;
		return false;
	}
	
	/**
	 * 判断当前用户是不是挑战者
	 * @param useruid
	 * @return
	 * @author chenminchang
	 * @create 2016-8-28下午1:09:17
	 */
	public boolean isPKUser1(String useruid) {
		if (this.pkuseruid.equals(useruid))
			return true;
		return false;
	}
	
	/**
	 * 判断当前用户是不是被挑战者
	 * @param useruid
	 * @return
	 * @author chenminchang
	 * @create 2016-8-28下午1:08:45
	 */
	public boolean isPKUser2(String useruid) {
		if (this.pkuseruid2.equals(useruid))
			return true;
		return false;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}