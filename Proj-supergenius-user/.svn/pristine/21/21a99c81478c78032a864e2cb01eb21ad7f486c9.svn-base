package com.supergenius.web.front.user.helper;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.security.Security;
import java.util.List;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxy;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.codehaus.xfire.transport.http.EasySSLProtocolSocketFactory;
import org.codehaus.xfire.util.dom.DOMOutHandler;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * 
 * 调用全国身份认证系统
 * 
 * @author thomasong
 * 
 */
public class NciicUtil {

	private static Logger log = LoggerFactory.getLogger(NciicUtil.class);

	public static final String SERVICE_URL = "https://ws.nciic.org.cn/nciic_ws/services/";
	public static final String VALID = "一致";
	public static String licensecode = FilePathMgr.getLicene();

	public NciicUtil() {
	}

	/**
	 * 核查身份
	 * 
	 * @param idcardNum
	 * @param username
	 * @return
	 * @throws MalformedURLException
	 */
	public static boolean checkIdCard(String idcardNum, String username) throws MalformedURLException {
		log.debug(String.format("begin to invoke checkIdCard(idcardNum:%s, username:%s)", idcardNum, username));
		String condition = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<ROWS><INFO><SBM>ctcjctcj44219</SBM></INFO><ROW><GMSFHM>公民身份号码"
				+ "</GMSFHM><XM>姓名</XM></ROW><ROW FSD=\"北京\" YWLX=\"贷款\" ><GMSFHM>${userIdCard}</GMSFHM>" + "<XM>${username}</XM></ROW>" + "</ROWS>";
		condition = condition.replace("${userIdCard}", idcardNum).replace("${username}", username);
		String result = new NciicUtil().executeClient("NciicServices", licensecode, condition);
		log.debug("xmlResult:" + result);
		NciicResult returncode = parseResultXML(result);
		if(returncode != null && returncode.getCode() == 1) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("all")
	public String executeClient(String serviceName, String license, String condition) throws MalformedURLException {
		ProtocolSocketFactory easy = new EasySSLProtocolSocketFactory();
		Protocol protocol = new Protocol("https", easy, 443);
		Protocol.registerProtocol("https", protocol);
		// 调用keystore文件
		System.setProperty("javax.net.ssl.trustStore", "nciic.keystore");
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Service serviceModel = new ObjectServiceFactory().create(INciicService.class, serviceName, null, null);
		INciicService service = (INciicService) new XFireProxyFactory().create(serviceModel, SERVICE_URL + serviceName);
		Client client = ((XFireProxy) Proxy.getInvocationHandler(service)).getClient();
		client.addOutHandler(new DOMOutHandler());
		// 压缩传输
		client.setProperty(CommonsHttpMessageSender.GZIP_ENABLED, Boolean.TRUE);
		// 忽略超时
		client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "1");
		client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
		String result = null;
		try {
			result = service.nciicCheck(licensecode, condition);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析XML
	 * 
	 * @param result
	 * @return
	 */
	public static NciicResult parseResultXML(String xmlResult) {
		// 创建一个新的字符串
		StringReader xmlString = new StringReader(xmlResult);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(xmlString);
		// 创建一个新的SAXBuilder
		SAXBuilder saxb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			Document doc = saxb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();
			String rootname = root.getName();
			if(rootname.equalsIgnoreCase("response")) {
				int errorcode = root.getAttribute("errorcode").getIntValue();
				Element rows = root.getChild("ROWS");
				Element row = rows.getChild("ROW");
				String errorMsg = row.getChildText("ErrorMsg");
				return new NciicResult(errorcode, errorMsg);
			} else {
				Element row = root.getChild("ROW");
				Element output = row.getChild("OUTPUT");
				List<?> itemList = output.getChildren("ITEM");

				String name_result = "";
				String idcard_result = "";
				for(int i = 0; i < 2; i++) {
					Element item = (Element) itemList.get(i);
					if(item.getChild("errormesage") != null) {
						String msg = item.getChildText("errormesage");
						return new NciicResult(-1, msg);
					}
					if(item.getChild("result_gmsfhm") != null) {
						name_result = item.getChildText("result_gmsfhm");
					}
					if(item.getChild("result_xm") != null) {
						idcard_result = item.getChildText("result_xm");
					}
				}
				if(idcard_result.equals(VALID) && name_result.equals(VALID)) {
					return new NciicResult(1, VALID);
				} else {
					return new NciicResult(0, xmlResult);
				}
			}

		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
