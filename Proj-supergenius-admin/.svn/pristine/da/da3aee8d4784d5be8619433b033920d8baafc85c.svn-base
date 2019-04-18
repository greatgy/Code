package com.supergenius.web.admin.moral.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.admin.moral.helper.ExamHP;
import com.supergenius.web.admin.moral.helper.MessageHP;
import com.supergenius.web.admin.moral.helper.StudentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.moral.entity.Certificate;
import com.supergenius.xo.moral.entity.ScoreDetail;
import com.supergenius.xo.moral.entity.Student;
import com.supergenius.xo.moral.service.StudentSO;
import com.supergenius.xo.moral.service.UserStatisticsSO;

/**
 * 学员管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class StudentAdminer extends BaseController{

	@Autowired
	StudentSO so;
	
	@Autowired
	UserStatisticsSO statisticsSO;
	
	/**
	 * 打开学员管理
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/student", method = RequestMethod.GET)
	public String student(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.student.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.student, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		return "dostudent";
	}
	
	/**
	 * 查询组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/moral/ajax/student/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> student_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StudentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/student/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> student_delete(String[] ids) {
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
	@RequestMapping(value = { "/moral/ajax/student/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> student_status(String[] ids, @PathVariable int status) {
		if (so.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 修改积分
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = { "/moral/ajax/student/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> scortDetail_edit(ScoreDetail scoreDetail){
		int beginscore = -1;
		if (scoreDetail !=null && scoreDetail.getUseruid() != null) {
			beginscore = statisticsSO.getOneByUseruid(scoreDetail.getUseruid()).getScore();
		}
		if (StudentHP.modifyScore(beginscore, scoreDetail, AdminHP.getAdmin())) {
			return result(MsgKeyDict.editSuccess);
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 加载修改历史
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = {"/moral/ajax/scoredetail/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> scoredetail_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StudentHP.queryScoreDetail(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 颁发证书
	 * @param model
	 * @param uid
	 * @param file
	 * @param result
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/moral/ajax/student/certificate"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> student_certificate(Map<String, Object> model, String uid, String file, String result, HttpServletRequest request) {
		Student student = so.get(uid);
		if (result.equals("1")) {
			Certificate certificate = new Certificate();
			if (StringUtils.isNotEmpty(file)) {
				String[] imgs = file.split(BaseStrDict.comma);
				certificate.setImg(imgs[0]);
			}
			certificate.setCreatetime(DateTime.now());
			certificate.setSn(StudentHP.getCertificateSN());
			student.setCertificate(certificate);
			if (so.update(student)) {
				ExamHP.updateState(student.getUseruid(), true);
				String url = String.format(WebConf.baseMoralPath + WebConf.CertificateUrl, student.getUseruid());
				MessageHP.sendNoticeMsg(BaseUserHP.get(student.getUseruid()), url, EMsg.getcertificate);
				return success();
			}
		}else {
			if (ExamHP.updateState(student.getUseruid(), false)) {
				MessageHP.sendNoticeMsg(BaseUserHP.get(student.getUseruid()), null, EMsg.nocertificate);
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
}
