package com.supergenius.web.admin.startup.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.service.QuestionSO;
import com.supergenius.xo.startup.service.StatisticsSO;
import com.supergenius.xo.startup.entity.Options;
import com.supergenius.xo.startup.entity.Question;
import com.supergenius.xo.startup.entity.Statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题管理HP
 * 
 * @author liubin
 * @date 2017年6月28日 上午10:32:47
 */
public class QuestionHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(QuestionHP.class);
	private static QuestionSO so;
	private static AdminSO adminSO;
	private static StatisticsSO statisticsSO;

	private static QuestionSO getSO() {
		if (so == null) {
			so = spring.getBean(QuestionSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	private static StatisticsSO getStatisticsSO() {
		if (statisticsSO == null) {
			statisticsSO = spring.getBean(StatisticsSO.class);
		}
		return statisticsSO;
	}

	public static String[] strings = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * 查询列表数据
	 * 
	 * @author liubin
	 * @date 2017年6月28日 上午10:33:45
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.order))) {
			map.put(MapperDict.order, model.get(ViewKeyDict.order).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (model.get(ViewKeyDict.status).toString().equals("1")) {
				map.put(MapperDict.status, EStatus.enable);
			}
			if (model.get(ViewKeyDict.status).toString().equals("0")) {
				map.put(MapperDict.status, EStatus.disable);
			}
		} else {
			map.put(MapperDict.status, EStatus.enable);// 默认只查出来正常的数据
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.sql_order + MapperDict.asc);// 按照order正排序
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Question> list = getSO().getList(map);
		querys(list);
		for (Question question : list) {
			if (StrUtil.isNotEmpty(question.getAdminuid())) {
				Admin admin = getAdminSO().get(question.getAdminuid());
				if (admin != null) {
					question.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 对question中的选项增加点击数
	 * 
	 * @author yangguang
	 * @date 2017年7月14日 下午18:13:45
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void querys(List<Question> questionslist) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.gender + MapperDict.suffix_notEqual_key, true);
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<Statistics> list = getStatisticsSO().getList(map);
		for (Statistics statistics : list) {
			Map<String, String> data = JsonUtil.fromJson(statistics.getData(), Map.class);
			String temp = "";
			for (String key : data.keySet()) {
				temp = key + data.get(key) + statistics.getGender();
				if (!result.containsKey(temp)) {
					result.put(temp, 1);
				} else {
					result.put(temp, result.get(temp) + 1);
				}
			}
		}
		List<Question> allQuestionList = QuestionHP.getNewQuestionList(questionslist);
		for (String key : result.keySet()) {
			Question questionTemp = null;
			for (Question question : allQuestionList) {
				if (key.substring(0, 32).equals(question.getUid())) {
					questionTemp = question;
					break;
				}
			}
			if (questionTemp != null) {
				Map<String, Map<String, String>> optionsMap = JsonUtil.fromJson(questionTemp.getOptions(), Map.class);
				List<Options> options = new ArrayList<Options>();
				if (optionsMap.containsKey("")) {
					options = questionTemp.getOptionList();
				} else {
					options = questionTemp.getOptionsMap().get(key.substring(key.length() - 1, key.length()));
				}
				for (Options options2 : options) {
					if (options2.getOption().equals(key.substring(32, 33))) {
						options2.setCount(result.get(key));
					}
				}
			}
		}
	}

	/**
	 * 将传递过来的questionlist封装options
	 * 
	 * @return
	 * @author ChenQi 2017年6月22日15:38:46
	 */
	@SuppressWarnings("unchecked")
	public static List<Question> getNewQuestionList(List<Question> questionList) {
		List<Question> newQuestionList = new ArrayList<Question>();// 封装options的questionList
		for (Question question : questionList) {
			String options = getUpperString(question.getOptions());
			String optionScore = getUpperString(question.getOptionscore());
			Map<String, Map<String, String>> optionsMap = JsonUtil.fromJson(options, Map.class);
			Map<String, Map<String, Integer>> optionscoreMap = JsonUtil.fromJson(optionScore, Map.class);
			if (optionsMap.containsKey("")) {// 无选项类型的question将选项存储在optionList中
				Map<String, String> optionsList = optionsMap.get("");
				Map<String, Integer> optionscoreList = optionscoreMap.get("");
				question.setOptionList(QuestionHP.getOptionsList(optionsList, optionscoreList));
			} else {// 有选项类型的question将选项存储在optionMap中
				Map<String, List<Options>> optionMap = new HashMap<String, List<Options>>();
				Iterator<String> iter = optionsMap.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Map<String, String> optionsList = optionsMap.get(key);
					Map<String, Integer> optionscoreList = optionscoreMap.get(key);
					optionMap.put(key, QuestionHP.getOptionsList(optionsList, optionscoreList));
				}
				question.setOptionsMap(optionMap);
			}
			newQuestionList.add(question);
		}
		return newQuestionList;
	}

	/**
	 * 获取选项及其内容
	 * 
	 * @return
	 * @author ChenQi 2017年6月21日16:32:28
	 */
	public static List<Options> getOptionsList(Map<String, String> optionsMap, Map<String, Integer> optionscoreMap) {
		List<Options> optionList = new ArrayList<Options>();
		for (int i = 0; i < optionsMap.size(); i++) {
			Options options = new Options();
			options.setOption(strings[i]);
			if (optionsMap.containsKey(strings[i])) {
				options.setData(optionsMap.get(strings[i]));
			} else {
				log.debug("the option is error! maybe the option is null, please go to databases look for reason!!!");
			}
			if (optionscoreMap.containsKey(strings[i])) {
				options.setScore(optionscoreMap.get(strings[i]));
			} else {
				log.error("the score is error! the option is null, please go to databases look for reason!!!");
			}
			optionList.add(options);
		}
		return optionList;
	}

	/**
	 * 将小写字母转换为大写字母
	 * 
	 * @return
	 * @author ChenQi 2017年7月11日16:27:42
	 */
	public static String getUpperString(String str) {
		StringBuffer UpperSB = new StringBuffer();
		char c[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if (c[i] >= 97) {
				UpperSB.append((c[i] + "").toUpperCase());
			} else {
				UpperSB.append(c[i] + "");
			}
		}
		String UpperStr = UpperSB.toString();
		return UpperStr;
	}
}
