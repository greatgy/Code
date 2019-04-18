package com.genius.server.baseadmin.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.RoleSO;

public class RoleHP extends BaseHP {

//	private static Logger log = LoggerFactory.getLogger(RoleHP.class);
	
	private static RoleSO so;

	private static RoleSO getSO() {
		if (so == null) {
			so = (RoleSO) spring.getBean(RoleSO.class);
		}
		return so;
	}

	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, BaseViewKeyDict.name);
		if (StrUtil.isNotEmpty(model.get(BaseViewKeyDict.status))) {
			map.put(BaseMapperDict.status, model.get(BaseViewKeyDict.status).toString().trim());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseViewKeyDict.total, getSO().getCount(map));
		result.put(BaseViewKeyDict.rows, getSO().getList(map));
		return result;
	}

}
