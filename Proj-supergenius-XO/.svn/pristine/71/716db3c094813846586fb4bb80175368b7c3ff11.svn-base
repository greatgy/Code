package com.supergenius.xo.sudokuapi.entity;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 加入积分实体
 * 
 * @author ChenQi
 * @date 2017年12月27日15:32:41
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = Maps.dbStrategy)
public class Pointsrecords extends BaseEntity {

	private static final long serialVersionUID = -8674302972376509193L;
	private String account;// 账号
	private int gain;// 获得的积分
	private int total;// 用户之前的积分
	private ObjectId game;// 游戏id
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getGain() {
		return gain;
	}
	public void setGain(int gain) {
		this.gain = gain;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ObjectId getGame() {
		return game;
	}
	public void setGame(ObjectId game) {
		this.game = game;
	}
	
}
