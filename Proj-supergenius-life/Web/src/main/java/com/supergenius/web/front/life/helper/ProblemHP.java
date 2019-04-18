package com.supergenius.web.front.life.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.search.engine.SearchEngine;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.service.AnswerSO;
import com.supergenius.xo.life.service.ProblemSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 问题HP
 * 
 * @author ChenQi
 * @date 2018年5月16日15:35:25
 */
public class ProblemHP extends BaseHP {

	private static ProblemSO so;

	private static AnswerSO answerSO;

	private static UserSO userSO;

	private static VisitorSO visitorSO;

	private static SearchEngine engine;

	public static ProblemSO getSO() {
		if (so == null) {
			so = (ProblemSO) spring.getBean(ProblemSO.class);
		}
		return so;
	}

	public static AnswerSO getAnswerSO() {
		if (answerSO == null) {
			answerSO = (AnswerSO) spring.getBean(AnswerSO.class);
		}
		return answerSO;
	}

	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	private static VisitorSO getVisitorSO() {
		if (visitorSO == null) {
			visitorSO = (VisitorSO) spring.getBean(VisitorSO.class);
		}
		return visitorSO;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("lifeEngine");
		}
		return engine;
	}

	/**
	 * 通过oid获取问题
	 * 
	 * @param oid
	 * @return
	 * @author ChenQi
	 * @date 2017年12月7日15:09:40
	 */
	public static Problem getProblem(int oid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.oid, oid);
		return getSO().getOne(map);
	}

	/**
	 * 获得问题
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2017年12月6日18:32:05
	 */
	public static List<Problem> getProblemList(int pagerNum, int pagerSize, Long cid, int ismember) {
		Pager pager = Pager.getNewInstance(pagerNum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		if (ismember != 0) {
			map.put(MapperDict.ismember, ismember);
		}
		List<Problem> list = getSO().getList(map);
		organizedUser(list);
		return list;
	}

	/**
	 * 获得热门视频
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年5月17日12:22:12
	 * @return List<Article>
	 */
	public static List<Problem> getHotProblemList(int pagerNum, int pagerSize, Long cid, int ismember) {
		Pager pager = Pager.getNewInstance(pagerNum, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		if (ismember != 0) {
			map.put(MapperDict.ismember, ismember);
		}
		List<Problem> list = getSO().getList(map);
		organizedUser(list);
		list.sort(COMPARATOR);
		int maxHotsize = (pagerNum + 1) * pagerSize;
		if (maxHotsize > list.size()) {
			maxHotsize = list.size();
		}
		return list.subList(pagerNum * pagerSize, maxHotsize);
	}

	/**
	 * 获取游客
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	public static Visitor getNickVisitor(HttpServletRequest request, HttpServletResponse response) {
		String ip = NetUtil.getIPAddr(request);
		Visitor visitor = getRandomVisitor();
		if (StrUtil.isEmpty(visitor.getAvatar()) || StrUtil.isEmpty(visitor.getNickname())) {
			visitor = getRandomVisitor();
		}
		visitor.setLoginip(ip);
		visitor.setCreatetime(new DateTime());
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			visitor.setUseruid(user.getUid());
		}
		getVisitorSO().add(visitor);
		return visitor;
	}

	/**
	 * 获取随机昵称和头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	@SuppressWarnings("unchecked")
	public static Visitor getRandomVisitor() {
		String path = SysConf.SerialBasePath + SysConf.SerialUserVisitorPath;
		List<Visitor> list = new ArrayList<Visitor>();
		list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
		Visitor visitor = new Visitor();
		visitor = list.get((int) (Math.random() * list.size()));
		return visitor;
	}

	/**
	 * 获取随机昵称和头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	public static void organizedUser(Problem problem) {
		User user = getUserSO().get(problem.getUseruid());
		if (user != null) {
			problem.setFromuseruid(user.getUid());
		} else {
			Visitor visitor = getVisitorSO().get(problem.getUseruid());
			problem.setFromuseruid(visitor.getUseruid());
			problem.setUsername(visitor.getUsername());
		}
	}

	/**
	 * 获取随机昵称和头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	public static void organizedUser(List<Problem> list) {
		User user;
		Visitor visitor;
		for (Problem problem : list) {
			user = getUserSO().get(problem.getUseruid());
			if (user != null) {
				problem.setUser(user);
			} else {
				visitor = getVisitorSO().get(problem.getUseruid());
				problem.setVisitor(visitor);
			}
		}
	}

	/**
	 * 根据城市名称查询所在经纬度
	 * 
	 * @param addr
	 *            查询的地址
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> getCoordinate(String addr) throws IOException {
		String lng = null;// 经度
		String lat = null;// 纬度
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format("http://api.map.baidu.com/geocoder?address=%s&output=json", address);
		System.err.println(url);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String data = null;
				int count = 1;
				while ((data = br.readLine()) != null) {
					if (count == 5) {
						lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));// 经度
						count++;
					} else if (count == 6) {
						lat = data.substring(data.indexOf(":") + 1);// 纬度
						count++;
					} else {
						count++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		Map<String, Object> placemap = new HashMap<String, Object>();
		placemap.put("value", new String[] { lng, lat });
		return placemap;
	}

	private static final Comparator<Problem> COMPARATOR = new Comparator<Problem>() {
		public int compare(Problem o1, Problem o2) {
			return o1.compareTo(o2);// 运用Video类的compareTo方法比较两个对象
		}
	};
}