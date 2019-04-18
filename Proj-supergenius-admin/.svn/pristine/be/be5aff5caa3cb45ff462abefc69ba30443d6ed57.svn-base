package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Answer;
import com.supergenius.xo.life.enums.ETop;
import com.supergenius.xo.life.service.AnswerSO;

/**
 * 回答管理HP
 * 
 * @author JiaShitao
 * @date 2018年5月9日14:25:13
 */
public class LifeAnswerHP extends BaseHP {

	private static AnswerSO so;
	private static AdminSO adminSO;

	private static AnswerSO getSO() {
		if (so == null) {
			so = (AnswerSO) spring.getBean(AnswerSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author JiaShitao
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(ViewKeyDict.cid).toString().trim());
		}
		/*
		 * if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
		 * map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		 * System.out.println(model.get(ViewKeyDict.istop)); } else {
		 * map.put(MapperDict.istop, null);
		 * System.out.println(model.get(ViewKeyDict.istop)); }
		 */
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Answer> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		return result;
	}

	/**
	 * 对传入的List进行处理，添加管理员名字
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日12:10:33
	 */
	public static List<Answer> getNewList(List<Answer> list) {
		for (Answer article : list) {
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
		List<Answer> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author ChenQi
	 * @date 2018年1月31日19:23:22
	 */
	/*
	 * public static List<Catalogue> getCatalogueList() { Map<String, Object> map =
	 * getParamMap(); List<Integer> list = new ArrayList<Integer>(); list.add(7);
	 * list.add(32); list.add(43); list.add(44); list.add(45); list.add(55);
	 * map.put(MapperDict.cids, list); List<Catalogue> catelogueList =
	 * getCatalogueSO().getList(map); return catelogueList; }
	 */

}
