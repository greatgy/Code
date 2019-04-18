package com.supergenius.web.admin.user.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.admin.entity.EmailLog;
import com.supergenius.xo.admin.service.EmailLogSO;

/**
 * 群发邮件
 * 
 * @author XieMing
 * @date 2016-5-23 上午11:18:27
 */
public class EmailLogHP extends BaseHP {

	private static EmailLogSO so;

	private static EmailLogSO getSO() {
		if (so == null) {
			so = spring.getBean(EmailLogSO.class);
		}
		return so;
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @return
	 * @author XieMing 2016-5-27 下午3:29:04
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getList(map).size());
		List<EmailLog> list = getSO().getList(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
