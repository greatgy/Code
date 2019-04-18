package com.supergenius.server.career.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.career.entity.Options;
import com.supergenius.xo.career.entity.Question;
import com.supergenius.xo.career.entity.Statistics;
import com.supergenius.xo.career.enums.EType;
import com.supergenius.xo.career.service.QuestionSO;
import com.supergenius.xo.career.service.StatisticsSO;


public class CareerQuestionHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(CareerQuestionHP.class);
	private static QuestionSO so;
	private static StatisticsSO statisticsSO;

	public static QuestionSO getSo() {
		if (so == null) {
			so = (QuestionSO) spring.getBean(QuestionSO.class);
		}
		return so;
	}
	
	private static StatisticsSO getStatisticsSO() {
		if (statisticsSO == null) {
			statisticsSO = spring.getBean(StatisticsSO.class);
		}
		return statisticsSO;
	}
	
	public static String[] strings = { "A", "B", "C", "D", "E", "F"};
	
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
		Map<String, Integer> result = new HashMap<String, Integer>();//questionuid+option:count
		List<Statistics> list = getStatisticsSO().getList(map);
		for (Statistics statistics : list) {
			Map<String, String> data = JsonUtil.fromJson(statistics.getData(), Map.class);
			String temp = "";
			for (String key : data.keySet()) {
				temp = key + data.get(key);
				if (!result.containsKey(temp)) {
					result.put(temp, 1);
				} else {
					result.put(temp, result.get(temp) + 1);
				}
			}
		}
		List<Question> allQuestionList = CareerQuestionHP.getNewQuestionList(questionslist);
		for (String key : result.keySet()) {
			Question questionTemp = null;
			for (Question question : allQuestionList) {
				if (key.substring(0, 32).equals(question.getUid())) {
					questionTemp = question;
					break;
				}
			}
			if (questionTemp != null) {
				List<Options> options = questionTemp.getOptionList();
				for (Options options2 : options) {
					if (options2.getOption().equals(key.substring(32, 33))) {
						options2.setCount(result.get(key));
					}
				}
			}
		}
	}
	
	/**
	 * 返回封装options的根据条件查询出的questionList
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日12:15:46
	 */
	public static List<Question> getQuestionList(Map<String, Object> map) {
		List<Question> questionList =  getSo().getList(map);
		querys(questionList);
		return questionList;
	}
	
	/**
	 * 返回封装options的根据条件查询出的questionList
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日12:15:46
	 */
	public static List<Question> getQuestions(Map<String, Object> map) {
		List<Question> questionList =  getSo().getList(map);
		List<Question> list = getNewQuestionList(questionList);
		return list;
	}

	/**
	 * 获取选项及其内容
	 * 
	 * @return
	 * @author yangguang 2017年11月15日14:37:00
	 */
	public static List<Options> getOptionsList(Map<String, String> optionsMap, Map<String, Integer> optiontypeMap) {
		List<Options> optionList = new ArrayList<Options>();
		for (int i = 0; i < optionsMap.size(); i++) {
			Options options = new Options();
			options.setOption(strings[i]);
			if (optionsMap.containsKey(strings[i])) {
				options.setData(optionsMap.get(strings[i]));
			} else {
				log.debug("the option is error! maybe the option is null, please go to databases look for reason!!!");
			}
			if (optiontypeMap.containsKey(strings[i])) {
				int type = optiontypeMap.get(strings[i]);
				options.setTypename(EType.getName(EType.get(type), Locale.CHINA));
				options.setType(type);
			} else {
				log.error("the score is error! the option is null, please go to databases look for reason!!!");
			}
			optionList.add(options);
		}
		return optionList;
	}

	/**
	 * 将传递过来的questionlist封装options
	 * 
	 * @return
	 * @author yangguang 2017年11月15日14:24:13
	 */
	@SuppressWarnings("unchecked")
	public static List<Question> getNewQuestionList(List<Question> questionList) {
		List<Question> newQuestionList = new ArrayList<Question>();// 封装options的questionList
		for (Question question : questionList) {
			String options = getUpperString(question.getOptions());
			String optionType = getUpperString(question.getOptiontype());
			Map<String, String> optionsMap = JsonUtil.fromJson(options, Map.class);
			Map<String, Integer> optiontypeMap = JsonUtil.fromJson(optionType, Map.class);
			question.setOptionList(CareerQuestionHP.getOptionsList(optionsMap, optiontypeMap));
			newQuestionList.add(question);
		}
		return newQuestionList;
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
        for(int i = 0; i<str.length(); i++){
            if(c[i]>=97){
            	UpperSB.append((c[i]+"").toUpperCase());
            }
            else{
            	UpperSB.append(c[i]+"");
            }
        }
        String UpperStr = UpperSB.toString();
        return UpperStr;
	}
}
