package com.genius.core.search.conf;

import java.util.Properties;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;

/**
 * Solr的配置文件
 * 
 * @author architect.bian
 * @createtime 2015-5-11 下午4:59:21
 */
@Deprecated
public class SolrConf {
	
	private boolean cloud;
	private String host;
	private String collection;
	private String uniqueKey = "id";

	public SolrConf() { }

	/**
	 * 使用prop创建一个SolrConf示例
	 * @param prop
	 * @author Architect.bian
	 * @createtime 2015-5-11 下午5:01:48
	 */
	public SolrConf(Properties prop) {
		String cloud = prop.getProperty("solr.cloud");
		if (StrUtil.isNotEmpty(cloud)) {
			this.setCloud(Boolean.parseBoolean(cloud));
		}
		
		String[] hostColl = prop.getProperty("solr.host").split(BaseStrDict.slash);
		this.setHost(hostColl[0]);
		this.setCollection(hostColl[1]);
		String uniqueKey = prop.getProperty("solr.uniqueKey");
		if (StrUtil.isNotEmpty(uniqueKey)) {
			this.setUniqueKey(uniqueKey);
		}
	}

	public boolean isCloud() {
		return cloud;
	}

	public void setCloud(boolean cloud) {
		this.cloud = cloud;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCollection() {
		return this.collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

}
