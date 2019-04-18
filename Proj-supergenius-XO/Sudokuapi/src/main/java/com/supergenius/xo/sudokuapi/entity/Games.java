package com.supergenius.xo.sudokuapi.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;

/**
 * 游戏实体
 * 
 * @author ChenQi
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = { Json.webStrategy, Json.appStrategy }, strategy = { Json.webStrategy, Json.appStrategy })
@Maps(strategy = Maps.dbStrategy)
public class Games implements Serializable {

	/*** 由于status需要使用String类型，所以不继承BaseEntity ***/
	protected String id;// 默认ID
	protected int oid;// 自增主键
	@Json(strategy = Json.allStrategy)
	protected String uid;// 唯一ID
	protected String tid;// 时间ID：120707235955
	@Json(strategy = Json.allStrategy)
	protected String status;
	@Json(strategy = Json.allStrategy)
	protected Date createtime;// 创建时间
	@Json(strategy = Json.allStrategy)
	protected Date updatetime;// 更新时间

	private static final long serialVersionUID = 3515832472780120134L;
	private String creator;
	private long index; //当前游戏个数
	private String parentid; //重玩时的第一次游戏的ID
	private int cost;
	private String playMode;
	private String startMode;
	private Integer waitCountdown;
	private Integer savecount; //棋盘保存次数，前台数据更新
	private Integer gameCountdown;
	private Integer delayCountdown;
	private int capacity;
	private int duration;
	private int remainingTime;
	private int waitTime;
	private int currentTime;
	private int errorCount;//单机，人机 错误的格子数
	private String level;
	private String manual;
	private boolean money_returned;
	private boolean delayed;
	private int real_wait_time;
	private String puzzle; //puzzle的ObjectUId
    private int roundtime; // 人机模式下的回合时间
	private int gametime; //人机或单击的游戏时间
	private ObjectId room;
	private Rules rule;
	private Map<String, Object> userCellValues;
	private Map<String, Object> cellValueOwners;
	private Map<String, Object> knownCellValues;
	private Map<String, Object> scores;
	private Map<String, Object> timeoutCounter;//记录account的人机对战的模式超时格子数
	private Map<String, Object> passCounter;//记录account的人机对战的模式pass格子数
	private Map<String, Map<String,Integer>>  userdprops;//记录此局游戏用户使用的道具及道具数量
	private Map<String, Object> optionsOnce;
	private Map<String, Object> glassesUsed;
	private Map<String, Object> optionsAlways;
	private Map<String, Object> optionsPrompt;
	private Map<String, Object> optionsSole;//存储是否使用唯余卡（对战时需要用到map类型）
	private Map<String, Object> changedScores;
	private Map<String, Object> playerIndex;
	private List<Object> messages;
	private List<Object> results;
	private List<Object> quitPlayers;
	private List<Object> mode;
	private List<String> joinRecords;
	private List<String> players;

	public Games() {
		this.uid = getUid();
	}

	/**
	 * @param uid
	 * @param createtime
	 * @param updatetime
	 */
	public Games(String uid, Date createtime, Date updatetime) {
		this.setUid(uid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 */
	public Games(int oid, Date createtime, Date updatetime) {
		this.setOid(oid);
		this.setCreatetime(createtime);
		this.setUpdatetime(updatetime);
	}

	/**
	 * @return the id
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getId() {
		if (StringUtils.isNotEmpty(id)) {
			return id;
		} else if (oid > 0) {
			return String.valueOf(oid);
		} else {
			return this.getUid();
		}
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the oid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public int getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setOid(int oid) {
		this.oid = oid;
	}

	/**
	 * @return the uid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUid() {
		if (StringUtils.isEmpty(this.uid)) {
			this.uid = new ObjectId().toString();
			return this.uid;
		}
		return this.uid;
	}

	/**
	 * @return the uid
	 */
	@Maps(strategy = Maps.allStrategy, alias = "_id", isRaw = true)
	public ObjectId getUidForDB() {
		if (StringUtils.isEmpty(this.uid)) {
			return new ObjectId();
		}
		return new ObjectId(this.uid);
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	@Maps(strategy = Maps.allStrategy, alias = "_id", isRaw = true)
	public void setUidForDB(ObjectId uid) {
		this.uid = uid.toString();
	}

	/**
	 * @return the tid
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getTid() {
		if (StringUtils.isEmpty(tid)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATE_TID);
			tid = sdf.format(new Date());
		}
		return tid;
	}

	public Map<String, Object> getPassCounter() {
		return passCounter;
	}

	public void setPassCounter(Map<String, Object> passCounter) {
		this.passCounter = passCounter;
	}

	public int getGametime() {
		return gametime;
	}

	public void setGametime(int gametime) {
		this.gametime = gametime;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setTid(String tid) {
		this.tid = tid;
	}

	@Maps(strategy = Maps.allStrategy)
	public String getStatus() {
		return status;
	}

	@Maps(strategy = Maps.allStrategy)
	public void setStatus(String status) {
		this.status = status;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getStatusName() {
		return EGameStatus.getName(EGameStatus.getByName(this.status), Locale.CHINA);
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsInit() {
		return EGameStatus.getByName(this.status) == EGameStatus.init;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsEnable() {
		return EGameStatus.getByName(this.status) == EGameStatus.enable;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsDisable() {
		return EGameStatus.getByName(this.status) == EGameStatus.disable;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsDeleted() {
		return EGameStatus.getByName(this.status) == EGameStatus.deleted;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsStart() {
		return EGameStatus.getByName(this.status) == EGameStatus.start;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsWait() {
		return EGameStatus.getByName(this.status) == EGameStatus.wait;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public boolean getIsEnd() {
		return EGameStatus.getByName(this.status) == EGameStatus.end;
	}

	/**
	 * @return the createtime
	 */
	@Maps(strategy = Maps.allStrategy)
	public Date getCreatetime() {
		return createtime;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getCreatetimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATETIME_CHINA);
		return sdf.format(getCreatetime());
	}

	@Maps(strategy = Maps.allStrategy)
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updateTime
	 */
	@Maps(strategy = Maps.allStrategy)
	public Date getUpdatetime() {
		return updatetime;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUpdatetimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_DATETIME_CHINA);
		return getUpdatetime() == null ? null : sdf.format(getUpdatetime());
	}

	@Maps(strategy = Maps.allStrategy)
	public void setUpdatetime(Date updateTime) {
		this.updatetime = updateTime;
	}

	public void clearForSitemap() {
		this.status = null;
		this.createtime = null;
	}

	public int getRoundtime() {
		return roundtime;
	}

	public void setRoundtime(int roundtime) {
		this.roundtime = roundtime;
	}

	@Override
	public int hashCode() {
		return StringUtils.isEmpty(uid) ? System.identityHashCode(this) : uid.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return this == null;
		} else if (this.hashCode() == o.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getStartMode() {
		return startMode;
	}

	public void setStartMode(String startMode) {
		this.startMode = startMode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public String getPlayMode() {
		return playMode;
	}

	public void setPlayMode(String playMode) {
		this.playMode = playMode;
	}

	public Integer getWaitCountdown() {
		return waitCountdown;
	}

	public void setWaitCountdown(Integer waitCountdown) {
		this.waitCountdown = waitCountdown;
	}

	public Integer getGameCountdown() {
		return gameCountdown;
	}

	public void setGameCountdown(Integer gameCountdown) {
		this.gameCountdown = gameCountdown;
	}

	public Integer getDelayCountdown() {
		return delayCountdown;
	}

	public void setDelayCountdown(Integer delayCountdown) {
		this.delayCountdown = delayCountdown;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public boolean getMoney_returned() {
		return money_returned;
	}

	public void setMoney_returned(boolean money_returned) {
		this.money_returned = money_returned;
	}

	public boolean getDelayed() {
		return delayed;
	}

	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}

	public int getReal_wait_time() {
		return real_wait_time;
	}

	public void setReal_wait_time(int real_wait_time) {
		this.real_wait_time = real_wait_time;
	}

	public String getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(String puzzle) {
		this.puzzle = puzzle;
	}

	public ObjectId getRoom() {
		return room;
	}

	public void setRoom(ObjectId room) {
		this.room = room;
	}

	public Rules getRule() {
		return rule;
	}

	public void setRule(Rules rule) {
		this.rule = rule;
	}

	public Map<String, Object> getUserCellValues() {
		return userCellValues;
	}

	public void setUserCellValues(Map<String, Object> userCellValues) {
		this.userCellValues = userCellValues;
	}

	public Map<String, Object> getCellValueOwners() {
		return cellValueOwners;
	}

	public void setCellValueOwners(Map<String, Object> cellValueOwners) {
		this.cellValueOwners = cellValueOwners;
	}

	public Map<String, Object> getScores() {
		return scores;
	}

	public void setScores(Map<String, Object> scores) {
		this.scores = scores;
	}

	public Map<String, Object> getTimeoutCounter() {
		return timeoutCounter;
	}

	public void setTimeoutCounter(Map<String, Object> timeoutCounter) {
		this.timeoutCounter = timeoutCounter;
	}

	public Map<String, Object> getOptionsOnce() {
		return optionsOnce;
	}

	public void setOptionsOnce(Map<String, Object> optionsOnce) {
		this.optionsOnce = optionsOnce;
	}

	public Map<String, Map<String, Integer>> getUserdprops() {
		return userdprops;
	}

	public void setUserdprops(Map<String, Map<String, Integer>> userdprops) {
		this.userdprops = userdprops;
	}

	public Map<String, Object> getGlassesUsed() {
		return glassesUsed;
	}

	public void setGlassesUsed(Map<String, Object> glassesUsed) {
		this.glassesUsed = glassesUsed;
	}

	public Map<String, Object> getOptionsAlways() {
		return optionsAlways;
	}

	public void setOptionsAlways(Map<String, Object> optionsAlways) {
		this.optionsAlways = optionsAlways;
	}
	
	public Map<String, Object> getOptionsPrompt() {
		return optionsPrompt;
	}

	public void setOptionsPrompt(Map<String, Object> optionsPrompt) {
		this.optionsPrompt = optionsPrompt;
	}
	
	public Map<String, Object> getOptionsSole() {
		return optionsSole;
	}

	public void setOptionsSole(Map<String, Object> optionsSole) {
		this.optionsSole = optionsSole;
	}

	public Map<String, Object> getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(Map<String, Object> playerIndex) {
		this.playerIndex = playerIndex;
	}

	public List<Object> getMessages() {
		return messages;
	}

	public void setMessages(List<Object> messages) {
		this.messages = messages;
	}

	public List<String> getJoinRecords() {
		return joinRecords;
	}

	public void setJoinRecords(List<String> joinRecords) {
		this.joinRecords = joinRecords;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	public Map<String, Object> getKnownCellValues() {
		return knownCellValues;
	}

	public void setKnownCellValues(Map<String, Object> knownCellValues) {
		this.knownCellValues = knownCellValues;
	}

	public Map<String, Object> getChangedScores() {
		return changedScores;
	}

	public void setChangedScores(Map<String, Object> changedScores) {
		this.changedScores = changedScores;
	}

	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}

	public List<Object> getQuitPlayers() {
		return quitPlayers;
	}

	public void setQuitPlayers(List<Object> quitPlayers) {
		this.quitPlayers = quitPlayers;
	}

	public List<Object> getMode() {
		return mode;
	}

	public void setMode(List<Object> mode) {
		this.mode = mode;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getSavecount() {
		return savecount;
	}

	public void setSavecount(Integer savecount) {
		this.savecount = savecount;
	}
}
