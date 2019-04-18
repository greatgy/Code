package com.genius.model.portal.entity;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.model.base.entity.BaseEntity;

/**
 * Html缓存调用
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:09:23
 */
public class Fresher extends BaseEntity {

	private static final long serialVersionUID = 1810587010115218562L;

	private Map<String, String> replaces = new HashMap<String, String>();
	
	private String script;
	
	private long token;
	
	public Fresher(){
		this.token = new DateTime().getMillis();
	}

	public Map<String, String> getReplaces() {
		return replaces;
	}

	public void setReplaces(Map<String, String> replaces) {
		this.replaces = replaces;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public long getToken() {
		return token;
	}

	public void setToken(long token) {
		this.token = token;
	}
}
