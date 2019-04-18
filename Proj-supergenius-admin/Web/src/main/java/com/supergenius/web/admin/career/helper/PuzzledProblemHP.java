package com.supergenius.web.admin.career.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.search.engine.SearchEngine;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.career.entity.Problem;
import com.supergenius.xo.career.enums.ETop;
import com.supergenius.xo.career.service.ProblemSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 文章管理HP
 * 
 * @author 杨光
 * @date 2017年11月14日14:25:13
 */
public class PuzzledProblemHP extends BaseHP {

	private static ProblemSO so;
	private static AdminSO adminSO;
	private static SearchEngine engine;

	private static ProblemSO getSO() {
		if (so == null) {
			so = (ProblemSO) spring.getBean(ProblemSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("careerEngine");
		}
		return engine;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 杨光
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Problem> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		return result;
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日12:10:33
	 */
	public static List<Problem> getNewList(List<Problem> list) {
		for (Problem article : list) {
			if (StrUtil.isNotEmpty(article.getAdminuid())) {
				Admin admin = getAdminSO().get(article.getAdminuid());
				if (admin != null) {
					article.setAdminname(admin.getName());
				}
			}
		}
		return list;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日14:12:33
	 */
	public static int getTopArticleCount() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, ETop.istop); // 表示置顶
		List<Problem> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

}
