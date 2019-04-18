package com.supergenius.xo.sudokuapi.entity;

import java.util.Map;

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
public class Props extends BaseEntity {

	private static final long serialVersionUID = -5220227612047913649L;
	private String account;// 账号
	@Json(strategy = Json.appStrategy, alias="magnifyingGlassNum")
	private int magnifier; //放大镜
	@Json(strategy = Json.appStrategy, alias="impunityCardNum")
	private int impunity;//免罚卡  现已弃用
	@Json(strategy = Json.appStrategy, alias="timeDelayCardNum")
	private int delay; //延时卡   现已弃用
	@Json(strategy = Json.appStrategy, alias="smartGlassesNum")
	private int glasses;//智能眼镜   现已弃用
	@Json(strategy = Json.appStrategy, alias="candidateCardNum")
	private int optionsonce;//单次候选卡
	@Json(strategy = Json.appStrategy, alias="allCandidateCardNum")
	private int optionsalways;//全局候选卡
	@Json(strategy = Json.appStrategy, alias="promptNum")
	private int prompt;//提示
	@Json(strategy = Json.appStrategy)
	private int sole; // 唯余卡道具
	private int scope_instrument;
	private int nerve_gas_instrument;
	private int asphyxiant_gas_instrument;
	private int irritant_gas_instrument;
	private int scope_paper;
	private int nerve_gas_paper;
	private int asphyxiant_gas_paper;
	private int irritant_gas_paper;
	private int cough_syrup;
	private int sober_potion;
	private int invincible_bomb;
	private Map<String, Object> purchases;//购买的

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getMagnifier() {
		return magnifier;
	}

	public void setMagnifier(int magnifier) {
		this.magnifier = magnifier;
	}

	public int getImpunity() {
		return impunity;
	}

	public void setImpunity(int impunity) {
		this.impunity = impunity;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getGlasses() {
		return glasses;
	}

	public void setGlasses(int glasses) {
		this.glasses = glasses;
	}

	public int getOptionsonce() {
		return optionsonce;
	}

	public void setOptionsonce(int optionsonce) {
		this.optionsonce = optionsonce;
	}

	public int getOptionsalways() {
		return optionsalways;
	}

	public void setOptionsalways(int optionsalways) {
		this.optionsalways = optionsalways;
	}

	public Map<String, Object> getPurchases() {
		return purchases;
	}

	public void setPurchases(Map<String, Object> purchases) {
		this.purchases = purchases;
	}

	public int getScope_instrument() {
		return scope_instrument;
	}

	public void setScope_instrument(int scope_instrument) {
		this.scope_instrument = scope_instrument;
	}

	public int getNerve_gas_instrument() {
		return nerve_gas_instrument;
	}

	public void setNerve_gas_instrument(int nerve_gas_instrument) {
		this.nerve_gas_instrument = nerve_gas_instrument;
	}

	public int getAsphyxiant_gas_instrument() {
		return asphyxiant_gas_instrument;
	}

	public void setAsphyxiant_gas_instrument(int asphyxiant_gas_instrument) {
		this.asphyxiant_gas_instrument = asphyxiant_gas_instrument;
	}

	public int getIrritant_gas_instrument() {
		return irritant_gas_instrument;
	}

	public void setIrritant_gas_instrument(int irritant_gas_instrument) {
		this.irritant_gas_instrument = irritant_gas_instrument;
	}

	public int getScope_paper() {
		return scope_paper;
	}

	public void setScope_paper(int scope_paper) {
		this.scope_paper = scope_paper;
	}

	public int getNerve_gas_paper() {
		return nerve_gas_paper;
	}

	public void setNerve_gas_paper(int nerve_gas_paper) {
		this.nerve_gas_paper = nerve_gas_paper;
	}

	public int getAsphyxiant_gas_paper() {
		return asphyxiant_gas_paper;
	}

	public void setAsphyxiant_gas_paper(int asphyxiant_gas_paper) {
		this.asphyxiant_gas_paper = asphyxiant_gas_paper;
	}

	public int getIrritant_gas_paper() {
		return irritant_gas_paper;
	}

	public void setIrritant_gas_paper(int irritant_gas_paper) {
		this.irritant_gas_paper = irritant_gas_paper;
	}

	public int getCough_syrup() {
		return cough_syrup;
	}

	public void setCough_syrup(int cough_syrup) {
		this.cough_syrup = cough_syrup;
	}

	public int getSober_potion() {
		return sober_potion;
	}

	public void setSober_potion(int sober_potion) {
		this.sober_potion = sober_potion;
	}

	public int getInvincible_bomb() {
		return invincible_bomb;
	}

	public void setInvincible_bomb(int invincible_bomb) {
		this.invincible_bomb = invincible_bomb;
	}

	public int getPrompt() {
		return prompt;
	}

	public void setPrompt(int prompt) {
		this.prompt = prompt;
	}
	
	public int getSole() {
		return sole;
	}

	public void setSole(int sole) {
		this.sole = sole;
	}

}