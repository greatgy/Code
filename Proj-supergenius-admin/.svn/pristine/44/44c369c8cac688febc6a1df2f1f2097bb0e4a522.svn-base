/**
 * 
 * @author liubin
 * @date 2017年6月27日 下午6:17:01
 */
package com.supergenius.web.admin.career.controller;

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
import com.supergenius.web.admin.career.helper.QuestionHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Question;
import com.supergenius.xo.career.service.QuestionSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 问题管理
 * 
 * @author yangguang
 * @date 2017年11月15日12:25:46
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerQuestionAdminer extends BaseController {

	@Autowired
	private QuestionSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 显示问题管理页面
	 * 
	 * @author yangguang
	 * @date 2017年11月15日12:25:57
	 * @return String
	 */
	@RequestMapping(value = "/career/careerquestion", method = RequestMethod.GET)
	public String question(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerquestion.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerquestion, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(MapperDict.count, so.getCount(EStatus.enable));
		model.put(ViewKeyDict.photopath, SysConf.CareerPhotoPath);
		return "docareerquestion";
	}

	/**
	 * 显示列表
	 * 
	 * @author liubin
	 * @date 2017年6月27日 下午7:12:03
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careerquestion/list", method = RequestMethod.GET)
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
	@RequestMapping(value = "/career/ajax/careerquestion/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_add(Map<String, Object> model, String order, String type, String name, String options, String optiontype, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(order)&& StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(options) && StrUtil.isNotEmpty(optiontype)) {
			Question question = new Question();
			question.setOrder(Integer.parseInt(order));
			question.setName(name);
			question.setOptions(options);
			question.setOptiontype(optiontype);
			if (StrUtil.isNotEmpty(imgdata)) {
				question.setImg(imgdata[0]);
			}
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				question.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careerquestion.toInt());
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
	@RequestMapping(value = "/career/ajax/careerquestion/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_edit(Map<String, Object> model, String uid, String order, String type, String name, String options, String optiontype, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(order)&& StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(options) && StrUtil.isNotEmpty(optiontype)) {
			Question question = so.get(uid);
			if (question != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.question.toInt());
				adminLog.setDataid(question.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.updateQuestion.getName());
				adminLog.setOperation(EAdminLog.updateQuestion.getName());
				question.setName(name);
				question.setOptions(options);
				question.setOptiontype(optiontype);
				if (StrUtil.isNotEmpty(imgdata)) {
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
	@RequestMapping(value = { "/career/ajax/careerquestion/delete" }, method = RequestMethod.GET)
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
