package com.genius.core.search.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.search.conf.SearchConf;
import com.genius.core.search.constant.MapperSearchDict;

/**
 * Solr的搜索引擎实现
 * 
 * search/add/delete操作支持设定route，route为空时则随机往所有shard写数据
 * @author architect.bian
 * @createtime 2015-12-28 下午4:31:45
 */
public class SolrSearchEngine extends BaseSearchEngine implements SearchEngine {

	private static Logger log = LoggerFactory.getLogger(SolrSearchEngine.class);
	
	private SolrClient client;

	public SolrSearchEngine(String site) {
		getClient(site);
	}
	
	/**
	 * 获取client，在配置文件search.properties中solr.cloud配置是否是cloud
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-28 下午5:03:17
	 */
	protected SolrClient getClient(String... site) {
		if (client == null) {
			if (SearchConf.isCloud(site)) {
				client = createCloudClient(site);
			} else {
				client = new HttpSolrClient(SearchConf.getHost(site));
			}
//		client.setParser(new XMLResponseParser());
		}
		return client;
	}

	private static CloudSolrClient createCloudClient(String... site) {
		CloudSolrClient cloudClient = new CloudSolrClient(SearchConf.getHost(site));
		cloudClient.setDefaultCollection(SearchConf.getCollection(site));
//		cloudClient.setParser(new XMLResponseParser());
		return cloudClient;
	}

	/**
	 * 搜索，支持route
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午2:01:04
	 */
	public List<?> search(Map<String, Object> map) {
		QueryResponse response;
		try {
			response = client.query(getSolrQuery(map));
			return response.getResults();
		} catch (SolrServerException | IOException e) {
			logException(log, e);
		}
		return null;
	}

	/**
	 * 创建索引或更新索引
	 */
	public boolean add(Map<String, Object> map) {
		UpdateResponse response;
		try {
			response = getUpdateRequest(map).add(getSolrInputDocument(map)).process(client);
//			response = getClient().add(getSolrInputDocument(map));
			client.commit();
			return isSuccess(response);
		} catch (SolrServerException e) {
			logException(log, e);
		} catch (IOException e) {
			logException(log, e);
		}
		return false;
	}

	/**
	 * 批量创建索引，忽略中间错误
	 * 不支持设定route
	 */
	public boolean addBatch(List<Map<String, Object>> maps) {
		UpdateResponse response;
		try {
			List<SolrInputDocument> docs = new ArrayList<>();
			for (Map<String, Object> m : maps) {
				docs.add(getSolrInputDocument(m));
			}
			response = client.add(docs);
			client.commit();
			return isSuccess(response);
		} catch (SolrServerException e) {
			logException(log, e);
		} catch (IOException e) {
			logException(log, e);
		}
		return false;
	}
	
	/**
	 * 删除索引，支持设置route
	 */
	public boolean delete(Map<String, Object> map) {
		UpdateResponse response;
		try {
			response = getUpdateRequest(map).deleteByQuery(getQuery(map)).process(client);
//			response = getClient().deleteByQuery(getQuery(map));
			client.commit();
			return isSuccess(response);
		} catch (SolrServerException e) {
			logException(log, e);
		} catch (IOException e) {
			logException(log, e);
		}
		return false;
	}

	/**
	 * 通过id删除索引，不支持route
	 */
	public boolean deleteByID(String... ids) {
		UpdateResponse response;
		try {
			StringBuilder builder = new StringBuilder();
			for (String id : ids) {
				builder.append(MapperSearchDict.operOR).append(SearchConf.getUniqueKey()).append(":").append(id);
			}
			response = client.deleteByQuery(builder.substring(MapperSearchDict.operOR.length()).toString());
			client.commit();
			return isSuccess(response);
		} catch (SolrServerException e) {
			logException(log, e);
		} catch (IOException e) {
			logException(log, e);
		}
		return false;
	}


	/**
	 * 根据map中的_route_返回一个updaterequest
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午9:54:22
	 */
	private UpdateRequest getUpdateRequest(Map<String, Object> map) {
		UpdateRequest request = new UpdateRequest();
		if (map.containsKey(MapperSearchDict.route)) {
			request.setParam(MapperSearchDict.route, map.get(MapperSearchDict.route).toString());
			map.remove(MapperSearchDict.route);
		}
		return request;
	}
	
	/**
	 * 返回solr的查询参数
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午2:14:46
	 */
	private SolrParams getSolrQuery(Map<String, Object> map) {
		SolrQuery query = new SolrQuery();
//		for (String key : map.keySet()) {
//			query.set(key,  map.get(key).toString());
//		}
		if (map.containsKey(MapperSearchDict.start)) {
			query.setStart(Integer.parseInt(map.get(MapperSearchDict.start).toString()));
			map.remove(MapperSearchDict.start);
		}
		if (map.containsKey(MapperSearchDict.rows)) {
			query.setRows(Integer.parseInt(map.get(MapperSearchDict.rows).toString()));
			map.remove(MapperSearchDict.rows);
		}
		if (map.containsKey(MapperSearchDict.sort)) {
			String[] sorts = map.get(MapperSearchDict.sort).toString().split(BaseStrDict.comma); //name, age desc, weight asc
			for (String str : sorts) {
				String[] arr = str.split(" ");
				if (str.endsWith(" " + MapperSearchDict.desc)) { //后缀不为" desc"时为升序
					query.addSort(new SolrQuery.SortClause(arr[0], SolrQuery.ORDER.desc));
				} else {
					query.addSort(new SolrQuery.SortClause(arr[0], SolrQuery.ORDER.asc));
				}
			}
			map.remove(MapperSearchDict.sort);
		}
		if (map.containsKey(MapperSearchDict.debug)) {
			query.setParam(MapperSearchDict.debug, map.get(MapperSearchDict.debug).toString());
			map.remove(MapperSearchDict.debug);
		}
		if (map.containsKey(MapperSearchDict.route)) {
			query.setParam(MapperSearchDict.route, map.get(MapperSearchDict.route).toString());
			map.remove(MapperSearchDict.route);
		}
		query.setQuery(getQuery(map));
		return query;
	}
		/**
	 * 将map转为solrinputdocument
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-28 下午5:28:07
	 */
	private SolrInputDocument getSolrInputDocument(Map<String, Object> map) {
		SolrInputDocument document = new SolrInputDocument();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			document.addField(key, map.get(key));
		}
		return document;
	}

	/**
	 * 通过map返回一个query
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 上午11:54:40
	 */
	private String getQuery(Map<String, Object> map) {
		if (map.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
//		String oper = "AND";
		for (String key : map.keySet()) {
			builder.append(MapperSearchDict.operOR).append(key).append(":").append(map.get(key).toString());//ClientUtils.escapeQueryChars
		}
		return builder.substring(MapperSearchDict.operOR.length()).toString();
	}

	/**
	 * 是否成功
	 * @param response
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-12-30 下午12:28:01
	 */
	private boolean isSuccess(UpdateResponse response) {
		return response.getStatus() == 0;
	}
}
