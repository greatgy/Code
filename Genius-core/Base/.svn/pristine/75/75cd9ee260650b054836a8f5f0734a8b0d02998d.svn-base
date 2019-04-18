package com.genius.core.base.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Architect.bian
 * 
 */
public class HttpUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * Get方式请求某个地址，返回输出的内容
	 * @param uri 可以不包含请求的协议，默认是http，支持https
	 * @return
	 * @author: LiuYongjian
	 */
	public static String get(String uri) {
		return get(uri, null);
	}
	
	/**
	 * 
	 * @param uri
	 * @return
	 * @author: LiuYongjian
	 */
	public static String get(URI uri) {
		HttpGet httpget = new HttpGet(uri);
		log.info("get request uri:" + uri);
		return execute(httpget, Consts.UTF_8);
	}

	/**
	 * 执行httpclient get方法
	 * 目前只支持http，
	 * url可以以http开头 如：https://www.baidu.com
	 * 也可以不带http 如：www.baidu.com
	 * @param url 
	 * @param map
	 * @return
	 * @author: LiuYongjian
	 */
	public static String get(String url, Map<String, String> params) {
		URIBuilder builder = createURIBuilder(url, params);
		try {
			return get(builder.build());
		} catch (URISyntaxException e) {
			logException(log, e);
		}
		return null;
	}

	/**
	 * Post方式请求某个地址，返回输出的内容
	 * @param uri url可以以http开头,也可以不带http 如：www.baidu.com
	 * @return
	 */
	public static String post(String uri) {
		return post(uri, null);
	}
	
	/**
	 * 以xml格式提交post请求
	 * @param uri
	 * @param xml
	 * @return
	 */
	public static String postXml(String uri, String xml) {
		HttpEntity entity = new StringEntity(xml, ContentType.create("application/xml", Consts.UTF_8));
		try {
			URI u = getUri(uri);
			return post(u, entity);
		} catch (URISyntaxException e) {
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 以json格式提交post请求
	 * @param uri
	 * @param json
	 * @return
	 */
	public static String postJson(String uri, String json) {
		HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
		try {
			URI u = getUri(uri);
			return post(u, entity);
		} catch (URISyntaxException e) {
			logException(log, e);
		}
		return null;
	}
		
	/**
	 * Post方式请求某个地址，返回输出的内容
	 * @param uri
	 * @return
	 */
	public static String post(URI uri) {
		return post(uri, null);
	}
	
	/**
	 * 执行httpclient Post方法
	 * @param url url可以以http开头,也可以不带http 如：www.baidu.com
	 * @param map 请求参数
	 * @return 结果
	 */
	public static String post(String url, Map<String, String> params) {
		EntityBuilder builder = createEntityBuilder(params);
		try {
			URI uri = getUri(url);
			return post(uri, builder.build());
		} catch (URISyntaxException e) {
			logException(log, e);
		}
		return null;
	}

	public static String post(URI url, HttpEntity entity) {
		HttpPost httpPost = new HttpPost(url);
		if (entity.getContentLength() > 0) {
			httpPost.setEntity(entity);
		}
		log.info("post request url :" + url);
		return execute(httpPost, Consts.UTF_8);
	}
	
	/**
	 * 执行httpget/httppost等
	 * @param request
	 * @param utf8 
	 * @return
	 */
	public static String execute(HttpUriRequest request, Charset charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(request);
			return EntityUtils.toString(response.getEntity(), charset);
//			return httpclient.execute(request, new BasicResponseHandler());
		} catch (IOException e) {
			logException(log, e);
			return null;
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logException(log, e);
			}
		}
	}

	/**
	 * 为get请求创建URIBuilder
	 * @param url
	 * @param map 需要传递的参数
	 * @return
	 */
	private static URIBuilder createURIBuilder(String url, Map<String, String> params) {
		URIBuilder builder = new URIBuilder();
		if (url.startsWith("http") || url.startsWith("https")) {
			builder.setPath(url);
		} else {
			builder.setScheme("http").setHost(url);
		}
		builder.setCharset(Consts.UTF_8);
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return builder;
	}

	/**
	 * 为post请求创建EntityBuilder，请求体为application/x-www-form-urlencoded
	 * @param params
	 * @return
	 */
	private static EntityBuilder createEntityBuilder(Map<String, String> params) {
		EntityBuilder builder = EntityBuilder.create();
		if (params != null && params.size() > 0) {
			List<NameValuePair> pairs = new ArrayList<>();
			for (Entry<String, String> item : params.entrySet()) {
				pairs.add(new BasicNameValuePair(item.getKey(), item.getValue()));
			}
			builder.setParameters(pairs);
		}
		builder.setContentType(ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));
		return builder;
	}

	/**
	 * 返回URI
	 * @param uri 可以不带http://协议
	 * @return
	 * @throws URISyntaxException
	 */
	private static URI getUri(String uri) throws URISyntaxException {
		URI u = null;
		if (uri.startsWith("http://") || uri.startsWith("https://")) {
			u  = new URIBuilder(uri).build();
		} else {
			u = new URIBuilder().setScheme("http").setPath(uri).build();
		}
		return u;
	}
	
}
