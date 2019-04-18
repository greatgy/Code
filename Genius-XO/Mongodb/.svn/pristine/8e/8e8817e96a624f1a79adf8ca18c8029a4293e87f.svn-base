package com.genius.xo.mongodb;


/**
 * 数据库连接属性
 * 
 * @author architect.bian
 * @createtime 2015-1-7 下午4:19:56
 */
public class DBConnection {
	
	private static final String prefix_url = "mongodb://";
	
	private String url;
	private String username;
	private String password;
	private boolean safe;
	private String basePackage = "";//有两个或以上dbconn时，每个dbconn的basepack都必须是不一样的，起了id的作用
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	/**
	 * 构建成可以连接的uri
	 * mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database[.collection]][?options]]
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-7 下午6:43:53
	 */
	public String toUri() {
		String auth = "";
		if (this.username != null && this.username.length() > 0) {
			auth += this.username;
			if (this.password != null && this.password.length() > 0) {
				auth += ":" + this.password;
			}
			auth += "@";
		}
		String option = "";
		if (safe) {
			option += "safe=true";
		} else {
			option += "safe=false";
		}
		return prefix_url + auth + url.replace(prefix_url, "") + "?" + option;
	}
}
