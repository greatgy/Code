package com.genius.core.base.mock.testentity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Maps;

@Maps
public class Book {
	
	private String id;
	private String name;
	private String author;
	private String content;
	private DateTime publishTime;
	
	public Book() {	}
	
	public Book(String id, String name, String author, String content, DateTime publishTime) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.content = content;
		this.publishTime = publishTime;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DateTime getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(DateTime publishTime) {
		this.publishTime = publishTime;
	}

	
}
