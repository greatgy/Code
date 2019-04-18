/**
 * 
 * @author liubin
 * @date 2017年6月27日 下午6:17:01
 */
package com.supergenius.web.admin.startup.controller;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.QuestionHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.service.QuestionSO;
import com.supergenius.xo.startup.entity.Question;

/**
 * 问题管理
 * 
 * @author liubin
 * @date 2017年6月27日 下午6:17:01
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class QuestionAdminer extends BaseController {

	@Autowired
	private QuestionSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 显示问题管理页面
	 * 
	 * @author liubin
	 * @date 2017年6月27日 下午6:19:15
	 * @return String
	 */
	@RequestMapping(value = "/startup/question", method = RequestMethod.GET)
	public String question(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.question.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.question, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.startup.name());
		model.put(MapperDict.count, so.getCount(EStatus.enable));
		model.put(ViewKeyDict.photopath, SysConf.StartupPhotoPath);
		return "doquestion";
	}

	/**
	 * 显示列表
	 * 
	 * @author liubin
	 * @date 2017年6月27日 下午7:12:03
	 * @return String
	 */
	@RequestMapping(value = "/startup/ajax/question/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> question_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = QuestionHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加题目
	 * 
	 * @author liubin
	 * @date 2017年6月28日 下午6:42:03
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/startup/ajax/question/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_add(Map<String, Object> model, String order, String type, String name, String options, String optionscore, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(order) && StrUtil.isNotEmpty(type) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(options) && StrUtil.isNotEmpty(optionscore)) {
			Question question = new Question();
			question.setOrder(Integer.parseInt(order));
			question.setType(Integer.parseInt(type));
			question.setName(name);
			question.setOptions(options);
			question.setOptionscore(optionscore);
			question.setImg(imgdata[0]);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				question.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.question.toInt());
			adminLog.setDataid(question.getUid());
			adminLog.setDesc("");
			adminLog.setData(EAdminLog.addQuestion.getName());
			adminLog.setOperation(EAdminLog.addQuestion.getName());
			if (so.getOne(question.getOrder()) != null) {
				so.updateFieldsByOrderIncr(question.getOrder());// 如果时是插入到中间的题目则更新order顺序
			}
			so.add(question, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑题目
	 * 
	 * @author liubin
	 * @date 2017年7月2日 下午4:17:58
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/startup/ajax/question/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_edit(Map<String, Object> model, String uid, String order, String type, String name, String options, String optionscore, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(order) && StrUtil.isNotEmpty(type) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(options) && StrUtil.isNotEmpty(optionscore)) {
			Question question = so.get(uid);
			String questionOptions = QuestionHP.getUpperString(question.getOptions());
			String recvOptions = QuestionHP.getUpperString(options);
			Map<String, Map<String, String>> questionOptionsMap = JsonUtil.fromJson(questionOptions, Map.class);
			Map<String, Map<String, String>> recvOptionsMap = JsonUtil.fromJson(recvOptions, Map.class);
			Iterator<String> iter = questionOptionsMap.keySet().iterator();
			boolean temp = false;
			while (iter.hasNext()) {
				String key = iter.next();
				Map<String, String> questionOptionList = questionOptionsMap.get(key);
				Map<String, String> optionsList = recvOptionsMap.get(key);
				if (questionOptionList.size() != optionsList.size()) {
					temp = true;
				}
			}
			if (question != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.question.toInt());
				adminLog.setDataid(question.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.updateQuestion.getName());
				adminLog.setOperation(EAdminLog.updateQuestion.getName());
				if (temp) {
					Question question2 = new Question();// 生成新对象，修改前的对象也要保留，以后需要统计查看
					question2.setType(Integer.parseInt(type));
					question2.setName(name);
					question2.setOptions(options);
					question2.setOptionscore(optionscore);
					if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
						question2.setAdminuid(AdminHP.getAdminUid());
					}
					question.setStatus(EStatus.disable);// 将原来的问题对象删除掉
					question2.setOrder(Integer.parseInt(order));
					if (imgdata.length == 0 ) {
						question2.setImg(question.getImg());
					} else {
						question2.setImg(imgdata[0]);
					}
					so.add(question2);
				} else {
					question.setType(Integer.parseInt(type));
					question.setName(name);
					question.setOptions(options);
					question.setOptionscore(optionscore);
					question.setImg(imgdata[0]);
				}
				if (Integer.parseInt(order) != question.getOrder()) {
					so.updateFieldsByOrderDecr(question.getOrder());
					so.updateFieldsByOrderIncr(Integer.parseInt(order));
				}
				question.setOrder(Integer.parseInt(order));// 将修改前的题目需要也跟着改变，统计数据一致
				so.update(question, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 删除题目
	 * 
	 * @author liubin
	 * @date 2017年6月29日 下午5:51:31
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = { "/startup/ajax/question/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> question_delete(String[] ids) {
		Question question = so.get(ids[0]);
		if (question != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.question.toInt());
			adminLog.setOperation(EAdminLog.deleteQuestion.getName());
			adminLog.setData(EAdminLog.deleteQuestion.getName());
			adminLog.setDesc(EAdminLog.deleteQuestion.getName());
			adminLog.setDataid(ids[0]);
			so.updateFieldsByOrderDecr(question.getOrder());// 将所有大于该题号的数据全部减1
			so.delete(question.getUid());
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
}
