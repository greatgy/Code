package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.supergenius.xo.life.entity.News;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.enums.ETop;
import com.supergenius.xo.life.service.AnswerSO;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.life.service.ProblemSO;

/**
 * 问题管理HP
 * 
 * @author JiaShitao
 * @date 2018年5月9日11:50:19
 * @return String
 */
public class LifeProblemHP extends BaseHP {

	private static ProblemSO so;
	private static AdminSO adminSO;
	private static AnswerSO answerSO;
	private static NewsSO newsSO;

	private static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}

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

	private static AnswerSO getAnswerSO() {
		if (answerSO == null) {
			answerSO = (AnswerSO) spring.getBean(AnswerSO.class);
		}
		return answerSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @author JiaShitao
	 * @date 2018年5月9日11:59:19
	 * @return map
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
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.state + MapperDict.asc + MapperDict.comma + MapperDict.createtime + MapperDict.desc
				+ MapperDict.comma + MapperDict.status + MapperDict.desc);
		List<Problem> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewList(list));
		return result;
	}

	/**
	 * 对传入的List进行处理，添加管理员名字
	 * 
	 * @return List<Problem>
	 * @author JiaShitao
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
	 * @author JiaShitao
	 * @date 2018年5月10日14:12:33
	 */
	public static int getTopArticleCount() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, ETop.istop); // 表示置顶
		List<Problem> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 获得专家点评二级列表
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static List<Answer> getSecondMajor(Problem problem) {
		List<Answer> list = new ArrayList<Answer>();
		Map<String, Object> majormap = getParamMap();
		majormap.put(MapperDict.type, EComment.major);
		majormap.put(MapperDict.fromuseruid, ViewKeyDict.defaultuid);
		majormap.put(MapperDict.fromuid, problem.getUid());
		majormap.put(MapperDict.touseruid, problem.getUseruid());
		List<Answer> majorlist = getAnswerSO().getList(majormap);
		Map<String, Object> usermap = getParamMap();
		usermap.put(MapperDict.type, EComment.major);
		usermap.put(MapperDict.fromuseruid, problem.getUseruid());
		usermap.put(MapperDict.fromuid, problem.getUid());
		usermap.put(MapperDict.touseruid, ViewKeyDict.defaultuid);
		usermap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Answer> userlist = getAnswerSO().getList(usermap);
		list.addAll(majorlist);
		list.addAll(userlist);
		for (Answer answer : list) {
			answer.setIsmajor(1);
		}
		list.sort(COMPARATOR);
		return list;
	};

	private static final Comparator<Answer> COMPARATOR = new Comparator<Answer>() {
		public int compare(Answer o1, Answer o2) {
			int i = o1.compareTo(o2);
			return i;//
		}
	};

	/**
	 * 点评之后发送消息
	 * 
	 * @param video
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Problem problem) {
		News news = new News();
		news.setTitle(problem.getTitle());
		news.setFromuid(ViewKeyDict.defaultuid);
		news.setTouid(problem.getUseruid());
		news.setType(ELifeMsg.evaluateproblem);
		news.setContent("/problem/detail/" + problem.getCid() + "/" + problem.getOid());
		getNewsSO().add(news);
	}
}