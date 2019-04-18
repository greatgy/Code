package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EPKdate;

/** 
 * 
 * @author chenminchang
 * @date 2016-7-18 上午9:15:54 
 */
public class PKDate extends BaseEntity {

	private static final long serialVersionUID = 8710476630095867136L;
	private int year; //年
	private int toyear; //到哪年
	private int month; //月
	private int tomonth; //到哪月
	private int day; //日
	private int today; //到哪日
	private int fromhour; //从几时
	private int tohour; //到几时
	private int fromminute; //从几分
	private int tominute; //到几分
	private String datetime; //挑战日期
	private int count; //场次
	private int leftcount; //剩余场次
	private EPKdate type; //pk日期类型
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getToyear() {
		return toyear;
	}
	
	public void setToyear(int toyear) {
		this.toyear = toyear;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getTomonth() {
		return tomonth;
	}
	
	public void setTomonth(int tomonth) {
		this.tomonth = tomonth;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getToday() {
		return today;
	}
	
	public void setToday(int today) {
		this.today = today;
	}
	
	public int getFromhour() {
		return fromhour;
	}
	
	public void setFromhour(int fromhour) {
		this.fromhour = fromhour;
	}
	
	public int getTohour() {
		return tohour;
	}
	
	public void setTohour(int tohour) {
		this.tohour = tohour;
	}
	
	public int getFromminute() {
		return fromminute;
	}
	
	public void setFromminute(int fromminute) {
		this.fromminute = fromminute;
	}
	
	public int getTominute() {
		return tominute;
	}
	
	public void setTominute(int tominute) {
		this.tominute = tominute;
	}
	
	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getLeftcount() {
		return leftcount;
	}
	
	public void setLeftcount(int leftcount) {
		this.leftcount = leftcount;
	}
	
	public EPKdate getType() {
		return type;
	}
	
	public void setType(EPKdate type) {
		this.type = type;
	}
}