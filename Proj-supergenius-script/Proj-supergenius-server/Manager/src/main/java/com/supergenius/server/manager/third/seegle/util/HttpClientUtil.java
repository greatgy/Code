package com.supergenius.server.manager.third.seegle.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	public static CloseableHttpClient httpclient = HttpClients.createDefault();
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String get(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			log.info(String.format("(url:%s)", url));
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				HttpEntity entity1 = response1.getEntity();
				String result = EntityUtils.toString(entity1);
				log.info(String.format("(result:%s)", result));
				return result;
			} catch (Exception e) {
				// TODO log
				e.printStackTrace();
				return "";
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
			return "";
		}

	}

	public static String post(String url) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		try {
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} finally {
			response2.close();
		}

		return "";
	}

}
