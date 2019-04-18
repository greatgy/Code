package com.genius.xo.mongodb.mock.model;

import java.util.Map;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;

@Maps(strategy=Maps.dbStrategy)
public class Book extends BaseEntity {

	private static final long serialVersionUID = -1902671965374507669L;
	
	private String name;
	private Map<Integer, String> pages;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Integer, String> getPages() {
		return pages;
	}
	public void setPages(Map<Integer, String> pages) {
		this.pages = pages;
	}

}
