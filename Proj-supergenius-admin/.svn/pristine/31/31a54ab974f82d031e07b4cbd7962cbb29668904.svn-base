package com.supergenius.web.admin.manager.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.AppStudent;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.AppStudentSO;
import com.supergenius.xo.manager.service.StudentSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 申请学员管理hp
 * @author chenminchang
 * @date 2016-10-20 下午5:16:02 
 */
public class AppStudentHP extends BaseHP {

	private static AppStudentSO so;
	private static StudentSO studentSO;
	private static UserSO userSO;

	private static AppStudentSO getSO() {
		if (so == null) {
			so = spring.getBean(AppStudentSO.class);
		}
		return so;
	}
	
	private static StudentSO getStudentSO() {
		if (studentSO == null) {
			studentSO = spring.getBean(StudentSO.class);
		}
		return studentSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, EMajor.getByName(model.get(ViewKeyDict.major).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		} else {
			List<EStatus> statusList = new ArrayList<>();
			statusList.add(EStatus.init);
			statusList.add(EStatus.disable);
			map.put(MapperDict.status + MapperDict.suffix_in_key, statusList);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keywords))) {
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		List<AppStudent> list = getSO().search(map);
		for (AppStudent appStudent : list) {
			appStudent.setUser(getUserSO().get(appStudent.getUseruid()));
			Student student = getStudentSO().get(appStudent.getUseruid());
			if (StrUtil.isNotEmpty(student.getWorkdaytime()))
				appStudent.setWorkdaytime(student.getWorkdaytime().replace("\n", "\t\t"));
			if (StrUtil.isNotEmpty(student.getSaturdaytime()))
				appStudent.setSaturdaytime(student.getSaturdaytime().replace("\n", "\t\t"));
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 处理appstudent
	 * @param uid
	 * @param status
	 * @param year
	 * @param month
	 * @param adminLog
	 * @author chenminchang
	 * @create 2016-10-24下午1:53:55
	 */
	public static boolean updateStatus(String uid, EStatus status, String year, String month, AdminLog adminLog, String desc) {
		AppStudent appStudent = getSO().get(uid);
		if (appStudent != null) {
			appStudent.setStatus(status);
			appStudent.setReason(desc);
			if (EStatus.enable.equals(status)) { //同意
				appStudent.setSemester(year + month);
				if (addStudent(appStudent, adminLog)) {
					MsgHP.sendAppStudentMsg(EMsgGroup.notify, EMsg.appStudent, WebConf.AppStudentMsgTitle, WebConf.baseManagerPath + SysConf.LaunchPKPath, appStudent.getUseruid(), adminLog.getAdminuid(), appStudent.getMajor());
					return true;
				}
			} else {//拒绝
				MsgHP.sendAppStudentMsg(EMsgGroup.notify, EMsg.appStudentFailed, WebConf.AppStudentFailedMsgTitle,  WebConf.baseManagerPath + SysConf.MajorPath, appStudent.getUseruid(), adminLog.getAdminuid(), appStudent.getMajor());
				return getSO().update(appStudent, adminLog);
			}
		}
		return false;
	}
	
	/**
	 * 增加学员身份
	 * 
	 * @author chenminchang
	 * @create 2016-10-25上午10:14:00
	 */
	public static boolean addStudent(AppStudent appStudent, AdminLog adminLog) {
		String useruid = appStudent.getUseruid();
		User user = getUserSO().get(useruid);
		Student student = getStudentSO().get(useruid);
		if (!EUser.student.ismatch(user.getType())) {
			user.plusType(EUser.student);
		}
		if (EStatus.init.equals(student.getStatus())) {
			student.setStatus(EStatus.enable);
		}
		student.plusMajor(appStudent.getMajor());
		UserLevel userlevel = new UserLevel(user.getUid(), appStudent.getMajor(), Integer.valueOf(EStudentLevel.basic.toString()), EUser.student, ELevelChannel.appStudent);
		return getSO().updateStatusAndType(appStudent, student, user, userlevel, adminLog);
	}
}
