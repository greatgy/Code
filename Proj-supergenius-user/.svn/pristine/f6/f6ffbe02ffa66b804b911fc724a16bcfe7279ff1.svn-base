package com.supergenius.web.api.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
* 人间仙境接口
* @author liubin
* @date 2017年4月27日 下午8:37:34 
*/
@Controller
public class UserInterfacer extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(UserInterfacer.class);

	@Autowired
	private UserSO userSO;

	@RequestMapping(value = "/api/user/getone", method = RequestMethod.POST)
	public @ResponseBody String user(Map<String, Object> model, String email, Integer id, String usersn, String apipartner, HttpServletRequest request) {
		log.info(String.format("begin to invoke user (id:%s, email:%s, usersn:%s)", id, email, usersn));
		User user1 = BaseUserHP.getCurrUser(request);
		if (user1 != null) {
			log.info("current user.uid:" + user1.getUid());
		} else {
			log.info("no current user");
		}
		if (StrUtil.isEmpty(email) && StrUtil.isEmpty(usersn) && id == null) {
			return JsonUtil.toJson(null);
		} else if (id != null && id <= 0) {
			return JsonUtil.toJson(null);
		}
		Map<String, Object> map = new HashMap<>();
		if (!StrUtil.isEmpty(email)) {
			map.put(MapperDict.email, email);
		}
		if (!StrUtil.isEmpty(usersn)) {
			map.put(MapperDict.usersn, usersn);
		}
		if (id != null) {
			map.put(ViewKeyDict.oid, id);
		}
		map.put(MapperDict.no + MapperDict.status, EStatus.disable + "," + EStatus.deleted);
		User user = userSO.getOne(map);
		log.info(String.format("return result ：%s", JsonUtil.toJson(user, Json.allStrategy)));
		return JsonUtil.toJson(user, Json.allStrategy);
	}
	
	@RequestMapping(value = "/api/user/loginstatus", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> Islogin(Map<String, Object> model, String ids, String apipartner, HttpServletRequest request, HttpServletResponse response) {
		log.info(String.format("begin to invoke Islogin (ids:%s)", ids));
		String status = "0";
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			log.info("current user.uid:" + user.getUid());
		} else {
			log.info("no current user");
		}
		Map<String, Object> map = new HashMap<>();
		if (!StrUtil.isNotEmpty(ids)) {
			map.put(ViewKeyDict.err, "ids为空");
			return map;
		}
		User user2 = BaseUserHP.getCurrUser(request);
		if (user2 != null) {
			status = "1";
		} else {
			status = "0";
		}
		map.put(ViewKeyDict.status, status);
		return map;
	}
	
	@RequestMapping(value = "/api/user/getcachelist", method = RequestMethod.POST)
	public @ResponseBody List<User> getcachelist(Map<String, Object> model, String ids, String apipartner, HttpServletRequest request, HttpServletResponse response) {
		log.info(String.format("begin to invoke users (ids:%s)", ids));
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			log.info("current user.uid:" + user.getUid());
		} else {
			log.info("no current user");
		}
		if (StrUtil.isEmpty(ids)) {
			List<User> users = new ArrayList<>();
			return users;
		}
		List<User> users = new ArrayList<>();
		String[] oids = ids.split(BaseStrDict.comma);
		int[] oidsInt = new int[oids.length];
		for (int i = 0; i < oids.length; i++) {
			oidsInt[i] = Integer.parseInt(oids[i]);
		}
		users = userSO.getList(oidsInt);
		log.info(String.format("return result ：%s", JsonUtil.toJson(users))) ;
		return users;

	}
	
	/**
	 * 搜索找人接口
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2017年5月26日上午11:20:19
	 * @return String
	 */
	@RequestMapping(value = {"/api/user/search/json"}, method = RequestMethod.POST)
	@ResponseBody
	public String humanSearch(Map<String, Object> model, HttpServletRequest request, String usersn, String showname, Integer type, Integer specialty, Integer userlevel,
			Integer judgelevel, String certificate, @RequestParam(defaultValue = "1") String num, @RequestParam(defaultValue = "-1") int pageSize) {
		log.info(String.format("begin to invoke search human for json,args: (usersn:%s,  showname:%s, type:%s, specialty:%s, userlevel:%s,judgelevel:%s, certificate:%s", usersn, showname, type,
				specialty, userlevel, judgelevel, certificate));
		// 开始判断参数是否存在，确定查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(usersn)) {// 会员号
			map.put(MapperDict.usersn, usersn);
		}
		if (StringUtils.isNotEmpty(showname)) {// 会员名
			map.put(MapperDict.showname + MapperDict.suffix_like_key, showname);
		}
		if (type != null) {// 类型
			map.put(MapperDict.type, type);
			if (userlevel != null) {
			    map.put(MapperDict.userlevel, userlevel);
			}
		}
		if (specialty != null) {// 专业
			map.put(MapperDict.major, specialty);
		}

		if (StrUtil.isNotEmpty(certificate)) {// 证书
			map.put(MapperDict.certificate, certificate);
		}
		Map<String, Object> returnmap = new HashMap<String, Object>();
		map.put(MapperDict.status, EStatus.enable);
		int pagesize = (pageSize == -1 ? Integer.parseInt(WebConf.SearchPageSize) : pageSize);
		Pager pager = Pager.getNewInstance(num, pagesize);
		List<User> userList = new ArrayList<User>();
		userList = userSO.searchCustomer(map, pager);
		int total = userSO.searchCount(map);
		returnmap.put(ViewKeyDict.datas, userList);
		returnmap.put(ViewKeyDict.total, total);
		String result = JsonUtil.toJson(returnmap, Json.allStrategy);
		log.info(String.format("the human search result : %s", result));
		return result;
	}
	
}
