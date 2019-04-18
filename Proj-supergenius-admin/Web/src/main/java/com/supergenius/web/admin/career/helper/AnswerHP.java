package com.supergenius.web.admin.career.helper;

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
import com.supergenius.xo.career.entity.Answer;
import com.supergenius.xo.career.entity.Problem;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.career.service.AnswerSO;
import com.supergenius.xo.career.service.ProblemSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 评论管理HP
 * 
 * @author ChenQi
 * @date 2017年11月14日12:20:15
 */
public class AnswerHP extends BaseHP {

	private static AnswerSO so;
	private static ProblemSO problemso;
	private static AdminSO adminSO;

	private static AnswerSO getSO() {
		if (so == null) {
			so = (AnswerSO) spring.getBean(AnswerSO.class);
		}
		return so;
	}

	private static ProblemSO getProblemSO() {
		if (problemso == null) {
			problemso = spring.getBean(ProblemSO.class);
		}
		return problemso;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	/**
	 * 查询评论时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.search))) {
			map.put(ViewKeyDict.fromusername + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			map.put(BaseMapperDict.content + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			Problem problem = problemso.getOneByField(BaseMapperDict.title + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			if (problem != null) {
				map.put(BaseMapperDict.fromuid, problem.getUid());
			} else {
				map.put(BaseMapperDict.fromuid, "");
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String startTime = model.get(ViewKeyDict.createtimestart).toString().trim() + MapperDict.starttimeformat;
			map.put(MapperDict.createtimestart, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String endTime = model.get(ViewKeyDict.createtimeend).toString().trim() + MapperDict.endtimeformat;
			map.put(MapperDict.createtimeend, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.type + MapperDict.asc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.type, EComment.comment);
		List<Answer> list = getSO().search(map);
		for (Answer answer : list) {
			if (StrUtil.isNotEmpty(answer.getAdminuid())) {
				Admin admin = getAdminSO().get(answer.getAdminuid());
				if (admin != null) {
					answer.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(answer.getFromuid())) {
				Problem problem = getProblemSO().get(answer.getFromuid());
				if (problem != null) {
					answer.setTitle(problem.getTitle());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 获取参与人数的数量
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static String getUserCount() {
		int commentsCount = getSO().getCountByfromuseruid();
		return String.valueOf(commentsCount);
	}

}
