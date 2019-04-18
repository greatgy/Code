package com.supergenius.web.admin.moral.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Options;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;
import com.supergenius.xo.moral.service.QuestionSO;

/**
 * 题库hp
 * 
 * @author liushaomin
 */
public class QstHP extends BaseHP {

	private static QuestionSO so;

	private static QuestionSO getSO() {
		if (so == null) {
			so = (QuestionSO) spring.getBean(QuestionSO.class);
		}
		return so;
	}

	/**
	 * 得到EChapter
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, String> getEChapterMap() {
		Map<String, String> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String k1, String k2) {
				return k1.compareTo(k2);
			}
		});
		for (EChapter item : EChapter.values()) {
			map.put(item.toString(), item.getName());
		}
		return map;
	}

	/**
	 * 得到abcd
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, String> getABCDMap() {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		Map<String, String> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String k1, String k2) {
				return k1.compareTo(k2);
			}
		});
		for (int i = 0; i < list.size(); i++) {
			map.put(String.valueOf(i), list.get(i));
		}
		return map;
	}

	/**
	 * 组织查询
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, ViewKeyDict.title);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.chapter))) {
			map.put(ViewKeyDict.chapter, model.get(ViewKeyDict.chapter).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString() + MapperDict.starttimeformat;
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		String[] etype = new String[] {EQst.single.toString(), EQst.material.toString()};
		map.put(MapperDict.type + MapperDict.suffix_in_key, etype);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * 设置材料题中的选项
	 * 
	 * @param str
	 * @param qstredio
	 * @return
	 * @author liushaomin
	 */
	public static List<Question> getQuestion(String[] str, String[] qstredio) {
		List<Question> questions = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			Question question = new Question();
			question.setUid(GlobalUtil.getUUID());
			String[] qst = str[i].split("liusvo");
			question.setTitle(qst[0]);
			String[] singleqst = {qst[1], qst[2], qst[3], qst[4]};
			String[] singleqstredio = qstredio[i].split(ViewKeyDict.slash);
			question.setOptions(getOptions(singleqst, singleqstredio));
			if(qst.length == 6){
				question.setAnalysis(qst[5]);
			}
			List<String> answer = new ArrayList<>();
			answer.add(String.valueOf(0));
			questions.add(question);
		}
		return questions;
	}

	/**
	 * 设置答案list
	 * 
	 * @param articles
	 * @return
	 * @author liushaomin
	 */
	public static List<Options> getOptions(String[] str, String[] optionsradio) {
		List<Options> options = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			Options option = new Options();
			option.setUid(String.valueOf(i));
			option.setName(str[i]);
			options.add(option);
			option.setSortorder(optionsradio[i]);
		}
		return options;
	}

}
