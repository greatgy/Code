package com.genius.core.base.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @author architect.bian
 *
 */
public class XmlUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(XmlUtil.class);
	
	private static XmlMapper mapper = new XmlMapper();

/**
 * Why StAX?
 * http://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.6/tutorial/doc/SJSXP2.html
 * */

	/**
	 * 将xml转为map，仅支持一级的xml格式
	 * If you need speed, using a StAX parser like Woodstox is the right way.
	 * @param xml
	 * @param map
	 * @return 成功与否
	 */
	public static Map<String, String> parseXmlToMap(String xml) {
		Map<String, String> map = new HashMap<>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			 // iterate through child elements of root
	        for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
	            Element element = (Element) i.next();
	            map.put(element.getName(), element.getStringValue());
	        }
			return map;
		} catch (DocumentException e) {
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * com.fasterxml.jackson.dataformat.xml.XmlMapper
	 * @param object
	 * @return
	 */
	public static String toXml(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		}
		return "";
	}
	
	public static <T> T fromXml(String xml, Class<T> valueType) {
			try {
				return mapper.readValue(xml, valueType);
			} catch (JsonParseException e) {
				e.printStackTrace();
				logException(log, e);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				logException(log, e);
			} catch (IOException e) {
				e.printStackTrace();
				logException(log, e);
			}
		return null;
	}
}