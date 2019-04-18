package com.supergenius.xo.sudokuapi.entity;

import java.util.Date;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;

/**
 * 道具实体
 * 
 * @author yangguang
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = Maps.dbStrategy)
public class Joinrecords extends BaseEntity {

	private static final long serialVersionUID = -5220227612047913649L;
	private String account;// 账号
	private Date join_time;
	private Date quit_time;
	private ObjectId game;// 账号
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getJoin_time() {
		return join_time;
	}

	public void setJoin_time(Date join_time) {
		this.join_time = join_time;
	}

	public Date getQuit_time() {
		return quit_time;
	}

	public void setQuit_time(Date quit_time) {
		this.quit_time = quit_time;
	}

	public ObjectId getGame() {
		return game;
	}

	public void setGame(ObjectId game) {
		this.game = game;
	}
	
	public void setGameUid(String game) {
		this.game = new ObjectId(game);
	}
	
	public String getGameUid() {
		return game.toString();
	}
}
