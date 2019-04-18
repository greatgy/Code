package com.genius.xo.mongodb.mock.model;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;

//@Mongo
//@Mongo(collection="library2")
@Maps(strategy = Maps.dbStrategy)
public class Library extends BaseEntity {

	private static final long serialVersionUID = 7970800668805729902L;
	
	private String name;
	private String nameEN;
	private Address address;
	private ELibrary type;
	private List<Book> books;
	private List<User> visitors;
	private List<Short> visitorsAge;
	private List<String> visitorsName;
	private int visitorCount;
	private DateTime foundTime;
	private LocalTime openTime;
	private LocalTime closeTime;
	private List<Map<String, String>> logs;
	private Map<String, Map<String, Object>> memos;
	
	@Maps(strategy= {Maps.dbStrategy, "vip"}, alias="_id")
	public String getUid() {
		return super.getUid();
	}
	/**
	 * @param uid the uid to set
	 */
	@Maps(strategy= {Maps.dbStrategy, "vip"}, alias="_id")
	public void setUid(String uid) {
		super.setUid(uid);
	}
	
	@Maps(strategy = {"vip", Maps.dbStrategy})
	public String getName() {
		return name;
	}
	@Maps(strategy = {"vip", Maps.dbStrategy})
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public ELibrary getType() {
		return type;
	}
	public void setType(ELibrary type) {
		this.type = type;
	}

	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<User> getVisitors() {
		return visitors;
	}
	public void setVisitors(List<User> visitors) {
		this.visitors = visitors;
	}

	public List<Short> getVisitorsAge() {
		return visitorsAge;
	}
	public void setVisitorsAge(List<Short> visitorsAge) {
		this.visitorsAge = visitorsAge;
	}
	public List<String> getVisitorsName() {
		return visitorsName;
	}
	public void setVisitorsName(List<String> visitorsName) {
		this.visitorsName = visitorsName;
	}
	
	public int getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}

	public DateTime getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(DateTime foundTime) {
		this.foundTime = foundTime;
	}

	public LocalTime getOpenTime() {
		return openTime;
	}
	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	public LocalTime getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}
	public List<Map<String, String>> getLogs() {
		return logs;
	}
	public void setLogs(List<Map<String, String>> logs) {
		this.logs = logs;
	}
	public Map<String, Map<String, Object>> getMemos() {
		return memos;
	}
	public void setMemos(Map<String, Map<String, Object>> memos) {
		this.memos = memos;
	}

}