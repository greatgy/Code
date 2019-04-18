package com.supergenius.server.startup.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.startup.service.QuestionSO;
import com.supergenius.xo.startup.entity.Options;
import com.supergenius.xo.startup.entity.Question;

public class QuestionHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(QuestionHP.class);
	private static QuestionSO so;

	public static QuestionSO getSo() {
		if (so == null) {
			so = (QuestionSO) spring.getBean(QuestionSO.class);
		}
		return so;
	}
	
	public static String[] strings = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

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
	 * 返回封装options的默认条件questionList
	 * 
	 * @return
	 * @author ChenQi 2017年6月22日15:38:46
	 */
	public static List<Question> getQuestionList() {
		List<Question> questionList =  getSo().getList();// 原questionList默认条件
		List<Question> newQuestionList = getNewQuestionList(questionList);
		return newQuestionList;
	}
	
	/**
	 * 返回封装options的根据条件查询出的questionList
	 * 
	 * @return
	 * @author ChenQi 2017年7月5日12:15:46
	 */
	public static List<Question> getQuestionList(Map<String, Object> map) {
		List<Question> questionList =  getSo().getList(map);
		List<Question> newQuestionList = getNewQuestionList(questionList);
		return newQuestionList;
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
