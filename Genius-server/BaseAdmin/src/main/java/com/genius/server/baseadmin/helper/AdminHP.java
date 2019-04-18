package com.genius.server.baseadmin.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;

/**
 * @author architect.bian
 *
 */
public class AdminHP extends BaseHP {

	private static AdminSO so;

	private static AdminSO getSO() {
		if (so == null) {
			so = (AdminSO) spring.getBean(AdminSO.class);
		}
		return so;
	}
	
	/**
	 * 获取当前登录管理员的ID
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-27 下午6:02:19
	 */
	public static String getAdminid() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth == null ? null : auth.getName(); 
	}

	/**
	 * 获取当前登录管理员
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-27 下午6:02:35
	 */
	public static Admin getAdmin() {
		return getSO().getByAdminid(AdminHP.getAdminid());
	}

	/**
	 * 获取当前登录管理员的uid
	 * @return adminuid
	 * @author ShangJianguo
	 * @createtime 2014-8-8 上午10:03:23
	 */
	public static String getAdminUid() {
		return getAdmin().getUid();
	}
	
	/**
	 * 验证是否是
	 * @param dopwd
	 * @author Architect.bian
	 * @createtime 2014-7-27 下午5:59:56
	 */
	public static boolean isDopwd(String dopwd) {
		return getAdmin().isEqualDopwd(dopwd);
	}

	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, BaseViewKeyDict.name, BaseViewKeyDict.adminid, BaseViewKeyDict.email, BaseViewKeyDict.mobile);
		if (StrUtil.isNotEmpty(model.get(BaseViewKeyDict.status))) {
			map.put(BaseMapperDict.status, model.get(BaseViewKeyDict.status).toString().trim());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseViewKeyDict.total, getSO().getCount(map));
		result.put(BaseViewKeyDict.rows, getSO().getList(map));
		return result;
	}
}
