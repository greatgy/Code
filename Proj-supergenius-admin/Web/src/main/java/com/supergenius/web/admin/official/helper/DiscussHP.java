package com.supergenius.web.admin.official.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.official.entity.Discuss;
import com.supergenius.xo.official.enums.EDiscuss;
import com.supergenius.xo.official.service.DiscussSO;

/**
 * 评论互动HP
 * @author liushaomin
 */
public class DiscussHP extends BaseHP{

	private static DiscussSO so;

	private static DiscussSO getSO() {
		if (so == null) {
			so = (DiscussSO) spring.getBean(DiscussSO.class);
		}
		return so;
	}
	
	/**
	 * 查询组织语句
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, model.get(ViewKeyDict.channel).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * @param replycontent
	 * @return
	 * @author liushaomin
	 */
	public static List<Discuss> getReplys(String replycontent) {
		List<Discuss> replys = new ArrayList<>();
		Discuss reply = new Discuss();
		reply.setFromname(AdminHP.getAdmin().getName());
		reply.setContent(replycontent);
		reply.setType(EDiscuss.admin);
		reply.setFromavatar(SysConf.TpiSysMsgImg);
		replys.add(reply);
		return replys;
	}
}
