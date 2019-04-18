package com.supergenius.web.admin.startup.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.startup.helper.QuestionHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.entity.Options;
import com.supergenius.xo.startup.entity.Question;
import com.supergenius.xo.startup.entity.Ruler;
import com.supergenius.xo.startup.entity.Statistics;
import com.supergenius.xo.startup.service.RulerSO;
import com.supergenius.xo.startup.service.StatisticsSO;

/**
 * 问卷调查统计信息HP（管理后台）
 * 
 * @author ChenQi
 */
public class StatisticsHP extends BaseHP {

	private static StatisticsSO so;
	private static RulerSO rulerSO;

	private static StatisticsSO getSO() {
		if (so == null) {
			so = (StatisticsSO) spring.getBean(StatisticsSO.class);
		}
		return so;
	}

	private static RulerSO getrulerSO() {
		if (rulerSO == null) {
			rulerSO = (RulerSO) spring.getBean(RulerSO.class);
		}
		return rulerSO;
	}

	/**
	 * 查询测试数据时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
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
		List<Statistics> statistics = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getNewStatistics(statistics));
		return result;
	}

	/**
	 * 将statistcs封装questionlist和selectedMap
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日15:40:28
	 */
	@SuppressWarnings("unchecked")
	public static List<Statistics> getNewStatistics(List<Statistics> statistics) {
		Map<String, Object> paramMap = new HashMap<>();
		List<Question> allQuestionList = QuestionHP.getQuestionList(paramMap);
		Map<String, Question> allQuestionMap = new HashMap<String, Question>();
		for (Question question : allQuestionList) {
			allQuestionMap.put(question.getUid(), question);
		}
		for (Statistics statistic : statistics) {
			List<Question> questionList = new ArrayList<Question>();
			// 将json格式Data转换为Map：("order":"选项序号")的形式
			String data = statistic.getData();
			Map<String, String> dataMap = JsonUtil.fromJson(data, Map.class);
			Map<Integer, Integer> selectedMap = new HashMap<Integer, Integer>();
			Iterator<String> iter = dataMap.keySet().iterator();
			int order = 0;
			int rejectcount = 0;
			while (iter.hasNext()) {
				order++;
				String key = iter.next();
				int index = getCharacters().indexOf(dataMap.get(key)) + 1;
				if (allQuestionMap.containsKey(key)) {
					Question question = allQuestionMap.get(key);
					if (question.getType() == 0) {
						List<Options> optionsList = question.getOptionList();
						for (Options options : optionsList) {
							if (options.getOption().equals(dataMap.get(key)) && options.getScore() == 0) {
								rejectcount++;
							}
						}
					}
					selectedMap.put(order, index);
					questionList.add(question);
				}
			}
			if (statistic.getRuleruid() != null) {
				Ruler ruler = getrulerSO().get(statistic.getRuleruid());
				if (ruler != null) {
					statistic.setContont(ruler.getContent());
				}
			}
			statistic.setRejectcount(rejectcount);
			statistic.setSelectedMap(selectedMap);
			statistic.setQuestionList(questionList);
		}
		return statistics;
	}

	/**
	 * 对question中的选项增加点击数
	 * 
	 * @author yangguang
	 * @date 2017年7月14日 下午18:13:45
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void querys(List<Question> allQuestionList) {
		Map<String, Object> map = getParamMap();
		Map<String, Integer> result = new HashMap<String, Integer>();
		map.put(MapperDict.gender + MapperDict.suffix_notEqual_key, true);
		List<Statistics> list = getSO().getList(map);
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
	 * 获得选项常量list
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日14:58:46
	 */
	public static List<String> getCharacters() {
		List<String> characters = Arrays.asList(QuestionHP.strings);
		return characters;
	}

}
