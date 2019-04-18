package com.supergenius.web.admin.moral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.QstHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Question;
import com.supergenius.xo.moral.enums.EQst;
import com.supergenius.xo.moral.service.QuestionSO;

/**
 * 题库
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class QstAdminer extends BaseController{
	
	@Autowired
	QuestionSO so;
	
	/**
	 * 打开题库
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/qst", method = RequestMethod.GET)
	public String qst(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.qst.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.qst, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		model.put(ViewKeyDict.map, QstHP.getEChapterMap());
		model.put(ViewKeyDict.map1, QstHP.getABCDMap());
		return "doqst";
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/moral/ajax/qst/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> qst_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = QstHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加考试题
	 * @param qst
	 * @param rightoption
	 * @param erroption1
	 * @param erroption2
	 * @param erroption3
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/qst/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> qst_add(Question question, String singleqst, String [] optionsradio, String[] qst, String[] qstredio) {
		if (question.getType().equals(EQst.single)) {
			String [] singleqsts = singleqst.split("liusvo");
			question.setOptions(QstHP.getOptions(singleqsts, optionsradio));
			List<String> answer = new ArrayList<>();
			answer.add(String.valueOf(0));
			question.setAnswer(answer);
		}else {
			question.setQuestions(QstHP.getQuestion(qst, qstredio));
			if (question.getDesc().length() < 50) {
				question.setTitle(question.getDesc());
			}else {
				question.setTitle(question.getDesc().substring(0, 50));
			}
		}
		if (so.add(question)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 编辑考试题
	 * @param newqst
	 * @param rightoption
	 * @param erroption1
	 * @param erroption2
	 * @param erroption3
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/qst/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> qst_edit(Question newqst, String singleqst, String[] optionsradio){
		Question question = so.get(newqst.getUid());
		if (question.getType().equals(EQst.material)) {
			if (newqst.getDesc().length() < 50) {
				question.setTitle(newqst.getDesc());
			}else {
				question.setTitle(newqst.getDesc().substring(0, 50));
			}
		}else {
			String [] singleqsts = singleqst.split("liusvo");
			question.setOptions(QstHP.getOptions(singleqsts, optionsradio));
			List<String> answer = new ArrayList<>();
			answer.add(String.valueOf(0));
			question.setAnswer(answer);
		}
		question.set(newqst);
		if (so.update(question)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 编辑材料题时，添加材料题中的选项
	 * @param uid
	 * @param questions[题目, 正确答案, 错误答案, 错误答案, 错误答案, 正确答案的sortorder, 错误答案的sortorder, 错误答案的sortorder, 错误答案的sortorder, 答案解析]
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/materialqst/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> materialqst_add(String uid, String[] questions){
		Question question = so.get(uid);
		Question newqst = new Question();
		newqst.setTitle(questions[0]);
		String[] singleqst = {questions[1], questions[2], questions[3], questions[4]};
		String[] singleqstredio = {questions[5], questions[6], questions[7], questions[8]};
		newqst.setOptions(QstHP.getOptions(singleqst, singleqstredio));
		newqst.setAnalysis(questions[9]);
		List<String> answer = new ArrayList<>();
		answer.add(String.valueOf(0));
		newqst.setAnswer(answer);
		question.getQuestions().add(newqst);
		if (so.update(question)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 编辑材料题时，删除材料题中的选项(更新材料题)
	 * @param uid
	 * @param title
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/materialqst/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> debatearticle_delete(String uid, String title) {
		Question question = so.get(uid);
		List<Question> questions = question.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getTitle().equals(title)) {
				questions.remove(i);
				break;
			}
		}
		question.setQuestions(questions);
		if(so.update(question)){
			return success();
		}else {
			return result(MsgKeyDict.addFailed);
		}
	}
	
	/**
	 * 删除题目
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/qst/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> qst_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 修改状态
	 * @param ids
	 * @param status
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/qst/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> qst_status(String[] ids, @PathVariable int status) {
		if (so.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
}
