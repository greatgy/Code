package com.supergenius.web.admin.career.helper;

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
import com.supergenius.server.career.helper.CareerQuestionHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.career.entity.Question;
import com.supergenius.xo.career.entity.Ruler;
import com.supergenius.xo.career.entity.Statistics;
import com.supergenius.xo.career.service.RulerSO;
import com.supergenius.xo.career.service.StatisticsSO;
import com.supergenius.xo.common.constants.MapperDict;

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
		Map<String, Object> paramMap = getParamMap();
		List<Question> allQuestionList = CareerQuestionHP.getQuestions(paramMap);
		Map<String, Question> allQuestionMap = new HashMap<String, Question>();
		for (Question question : allQuestionList) {
			allQuestionMap.put(question.getUid(), question);
		}
		int number = 0;
		for (Statistics statistic : statistics) {
			number++;
			List<Question> questionList = new ArrayList<Question>();
			// 将json格式Data转换为Map：("order":"选项序号")的形式
			String data = statistic.getData();
			Map<String, String> dataMap = JsonUtil.fromJson(data, Map.class);
			Map<Integer, Integer> selectedMap = new HashMap<Integer, Integer>();
			Iterator<String> iter = dataMap.keySet().iterator();
			int order = 0;
			while (iter.hasNext()) {
				order++;
				String key = iter.next();
				int index = getCharacters().indexOf(dataMap.get(key)) + 1;
				if (allQuestionMap.containsKey(key)) {
					Question question = allQuestionMap.get(key);
					selectedMap.put(order, index);
					questionList.add(question);
				}
			}
			if (statistic.getRuleruid() != null) {
				Ruler ruler = getrulerSO().get(statistic.getRuleruid());
				if (ruler != null) {
					statistic.setContont(ruler.getName());
				}
			}
			statistic.setOrder(number);
			statistic.setSelectedMap(selectedMap);
			statistic.setQuestionList(questionList);
		}
		return statistics;
	}

	/**
	 * 获得选项常量list
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日14:58:46
	 */
	public static List<String> getCharacters() {
		List<String> characters = Arrays.asList(CareerQuestionHP.strings);
		return characters;
	}

}
