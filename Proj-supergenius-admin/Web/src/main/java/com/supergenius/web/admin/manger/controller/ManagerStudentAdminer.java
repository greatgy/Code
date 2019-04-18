package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.manager.helper.StudentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.MajorSO;
import com.supergenius.xo.manager.service.StudentSO;
import com.supergenius.xo.manager.service.UserLevelSO;

/** 
 * 学员管理controller
 * @author chenminchang
 * @date 2016-10-27 下午6:06:21 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerStudentAdminer extends BaseController {
	
	@Autowired
	StudentSO so;
	@Autowired
	UserLevelSO userlevelSO;
	@Autowired
	MajorSO majorSO;
	
	/**
	 * 跳转学员管理
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27下午6:08:13
	 */
	@RequestMapping(value = "/manager/student", method = RequestMethod.GET)
	public String appstudent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.student.name());
		model.put(ViewKeyDict.channelname, EChannel.student.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.total, so.getCount());
		model.put(ViewKeyDict.special, userlevelSO.getCountByChannel(ELevelChannel.specialStudent));
		model.put(ViewKeyDict.invite, userlevelSO.getCountByChannel(ELevelChannel.inviteStudent));
		model.put(ViewKeyDict.student, StudentHP.getStudentCountByMajor());
		model.put(ViewKeyDict.majormap, EMajor.getChinaNames());
		model.put(ViewKeyDict.studentlevelmap, EStudentLevel.getEsudentsLevelMap());
		model.put(ViewKeyDict.levelmap, ECertificate.getAboutJudgeDegreeMap());
		return "domstudent";
	}
	

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/manager/ajax/student/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> student_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StudentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 更改学员等级
	 * @param request
	 * @param ids
	 * @param adminuid
	 * @param dopwd
	 * @param desc
	 * @param level
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1下午4:59:55
	 */
	@RequestMapping(value = { "/manager/ajax/student/setlevel" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> student_setlevel(HttpServletRequest request, String[] ids, String adminuid, String dopwd, String desc, int level) {
		if (AdminHP.isDopwd(dopwd)) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.student.toInt());
			adminLog.setOperation(EAdminLog.updateStudentLevel.getName());
			adminLog.setData(EAdminLog.updateStudentLevel.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			if (StudentHP.setLevel(ids[0], EStudentLevel.get(level), desc, adminLog))
				return success();
			return result(MsgKeyDict.doFailed);
		} 
		return result(MsgKeyDict.dopwdIsWrong);
	}
}
