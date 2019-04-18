package com.supergenius.web.admin.life.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Complaint;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.service.ComplaintSO;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 举报管理HP
 * @author YangGuang
 * @date 2018年5月10日11:54:58
 */
public class ComplaintHP extends BaseHP {

	private static ComplaintSO so;
	private static UserSO userSO;
	private static NewsSO newsSO;

	private static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}
	
	private static ComplaintSO getSO() {
		if (so == null) {
			so = spring.getBean(ComplaintSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	
	/**
	 * 加载数据
	 * @param model
	 * @return
	 * @author YangGuang
	 * @createtime 2018年5月10日11:54:48
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.createtime + MapperDict.desc);
		List<Complaint> list = getSO().getList(map);
		for(Complaint complaint : list){
			User user = getUserSO().get(complaint.getFromuseruid());
			complaint.setFromusername(user.getUsername());
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 审核举报之后发送消息
	 * 
	 * @param comment
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Complaint complaint, String adminuid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, complaint.getRefname());
		map.put(MapperDict.touid, complaint.getFromuseruid());
		map.put(MapperDict.content, complaint.getRefname());
		map.put(MapperDict.type, ELifeMsg.report);
		map.put(MapperDict.href, complaint.getRefurl());
		map.put(MapperDict.fromuseruid, adminuid);
		getNewsSO().add(map);
	}
}
