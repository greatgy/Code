package com.genius.core.search.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;

/**
 * 搜索的配置
 * 
 * @author architect.bian
 * @createtime 2015-5-11 下午4:19:04
 */
@Component
public class SearchConf {
  
	public static Properties props;
	private static String defaultCloudKey = "solr.default.cloud";
	private static String solrCloud;
	private static String defaultHostKey = "solr.default.host";
	private static String solrHost;
	private static String solrCollection;
	private static String defaultUniqueKey = "solr.default.cloud";
	private static String uniqueKey;
	
	@SuppressWarnings("static-access")
	@Value("#{search}")
	public void set(Properties props) {
		this.props = props;
	}
	
	/**
	 * 获取主机地址
	 * @author liubin
	 * @date 2017年9月11日 下午4:52:35
	 * @return void
	 */
	public static String getHost(String... site) {
		if (StrUtil.isNotEmpty(site) && site.length > 0) {
			if (props.containsKey(String.format("solr.%s.host", site[0]))) {
				solrHost = props.getProperty(String.format("solr.%s.host", site[0]));
			} else if (props.containsKey(defaultHostKey)) {
				solrHost = props.getProperty(defaultHostKey);
			} else {
				solrHost = props.getProperty("solr.host");
			}
		}
		return StrUtil.isNotEmpty(solrHost) ? solrHost.split(BaseStrDict.slash)[0] : null;
	}
	
	/**
	 * 获取collection
	 * @author liubin
	 * @date 2017年9月11日 下午5:20:42
	 * @return String
	 */
	public static String getCollection(String... site) {
		if (StrUtil.isNotEmpty(site) && site.length > 0) {
			if (props.containsKey(String.format("solr.%s.host", site[0]))) {
				solrCollection = props.getProperty(String.format("solr.%s.host", site[0]));
			} else if (props.containsKey(defaultHostKey)) {
				solrCollection = props.getProperty(defaultHostKey);
			} else {
				solrCollection = props.getProperty("solr.host");
			}
		}
		return StrUtil.isNotEmpty(solrCollection) ? solrCollection.split(BaseStrDict.slash)[1] : null;
	}
	
	/**
	 * 判断是否启用cloud
	 * @author liubin
	 * @date 2017年9月11日 下午5:33:14
	 * @return boolean
	 */
	public static boolean isCloud(String... site) {
		if (StrUtil.isNotEmpty(site) && site.length > 0) {
			if (props.containsKey(String.format("solr.%s.cloud", site[0]))) {
				solrCloud = props.getProperty(String.format("solr.%s.cloud", site[0]));
			} else if (props.containsKey(defaultCloudKey)) {
				solrCloud = props.getProperty(defaultCloudKey);
			} else {
				solrCloud = props.getProperty("solr.cloud");
			}
		}
		return StrUtil.isNotEmpty(solrCloud) ? Boolean.parseBoolean(solrCloud) : true;
	}
	
	/**
	 * 得到uniqueKey，默认为"uid"
	 * @author liubin
	 * @date 2017年9月11日 下午5:56:39
	 * @return String
	 */
	public static String getUniqueKey(String... site) {
		if (StrUtil.isNotEmpty(site) && site.length > 0) {
			if (props.containsKey(String.format("solr.%s.uniqueKey", site[0]))) {
				uniqueKey =props.getProperty(String.format("solr.%s.uniqueKey", site[0]));
			} else if (props.containsKey(defaultUniqueKey)) {
				uniqueKey = props.getProperty(defaultUniqueKey);
			} else {
				uniqueKey = props.getProperty("solr.uniqueKey");
			}
		}
		return StrUtil.isNotEmpty(uniqueKey) ? uniqueKey : "uid";
	}
}
