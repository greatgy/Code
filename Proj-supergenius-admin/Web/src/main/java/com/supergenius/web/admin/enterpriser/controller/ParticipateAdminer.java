package com.supergenius.web.admin.enterpriser.controller;

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
import com.supergenius.server.enterpriser.helper.EmailHP;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.enterpriser.hellper.ParticipateHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.enterpriser.service.LectureSO;
import com.supergenius.xo.enterpriser.service.ParticipateSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 申请讲座控制器
 * @author liubin
 * @date 2016-10-25 下午4:47:45 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ParticipateAdminer extends BaseController {

	@Autowired
	private ParticipateSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;
	
	@Autowired
	private LectureSO lectureSO;
	
	@Autowired
	private UserSO userSO;
	
	/**
	 * 讲座报名后台管理首页
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午5:17:56
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/participate", method = RequestMethod.GET)
	public String participate(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.participate.name());
		model.put(ViewKeyDict.channelname, EChannel.participate.getName());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.apptotal, so.getCount());
		model.put(ViewKeyDict.passcount, so.getCount(EStatus.enable, EStatus.init));
		model.put(ViewKeyDict.nopasstotal, so.getCount(EStatus.disable));
		return "doparticipate";
	}
	
	/**
	 * 获取查询数据
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26上午11:07:50
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/enterpriser/ajax/participate/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> participate_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ParticipateHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加讲座报名人员
	 * @param name
	 * @param mobile
	 * @param email
	 * @param address
	 * @param gift
	 * @return
	 * @author liubin
	 * @createtime 2016年12月14日上午11:09:08
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/participate/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> participate_add(String name, String mobile, String email, String address, String gift) {
		Lecture lecture = lectureSO.getOne(EStatus.enable);
		Participate participate = new Participate(null, null, lecture.getUid(), lecture.getName(), lecture.getSemester(), name, mobile, email, address);
		User user = userSO.getByEmail(participate.getEmail());
		String initPwd = AutoIncrHP.getUserInitPwd();
		if(user != null) {
			participate.setUseruid(user.getUid());
			participate.setUsersn(user.getUsersn());
			so.add(participate);
			if (EmailHP.sendParticipateSuccessNoUser(participate.getLecturename(), participate.getName(), participate.getEmail())) {
				 return success();
			}
		} else {
			if ("1".equals(gift)) {//同意赠送会员
				user = new User();
				user.setUsersn(AutoIncrHP.getVONUsersn());
				user.setEmail(participate.getEmail());
				user.setName(participate.getName());
				user.setMobile(participate.getMobile());
				user.setAddress(participate.getAddress());
				user.initPassword(initPwd);
				participate.setUseruid(user.getUid());
				participate.setUsersn(user.getUsersn());
				so.update(user, participate, null, null, null, null);
				if (EmailHP.sendParticipateSuccess(participate.getLecturename(), participate.getName(), participate.getEmail(), initPwd, participate.getEmail())) {
				    return success();
				}
			}
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 修改学期
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26上午11:12:00
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/ajax/participate/setsemester", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> participate_edit_semester(String uid, String semester) {
		Participate participate = so.get(uid);
		if (StrUtil.isNotEmpty(semester) && participate != null) {
			participate.setSemester(Integer.parseInt(semester));
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminid());
			adminLog.setDataid(participate.getUid());
			adminLog.setData(SysConf.ChangerSemester);
			adminLog.setChannel(EChannel.participate.toInt());
			adminLog.setOperation(SysConf.ChangerSemester);
			adminLog.setDesc(SysConf.ChangerSemester);
			if (so.update(participate) && adminLogSO.add(adminLog)) {
			    return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 修改发送赠品状态
	 * @param uid
	 * @param issendgift
	 * @return
	 * @author liubin
	 * @createtime 2016-10-26上午11:41:38
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/ajax/participate/setissendgift", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> participate_edit_issendgift(String uid, String sn) {
		Participate participate = so.get(uid);
		if (participate != null && participate.getStatus() == EStatus.enable) {
			participate.setStatus(EStatus.init);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminid());
			adminLog.setData(EContent.email_sendgift.getName());
			adminLog.setDataid(participate.getUid());
			adminLog.setChannel(EChannel.participate.toInt());
			adminLog.setOperation(EContent.email_sendgift.getName());
			adminLog.setDesc(EContent.email_sendgift.getName());
			if (so.update(participate) && adminLogSO.add(adminLog)) {
				EmailHP.sendGift(participate.getLecturename(), participate.getName(), sn, participate.getEmail());
			    return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 编辑报名信息，邮寄地址
	 * @param uid
	 * @param address
	 * @return
	 * @author XieMing
	 * 2016年12月5日 下午4:15:54
	 */
	@RequestMapping(value = "/enterpriser/ajax/participate/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> participate_edit(String uid, String address) {
		Participate participate = so.get(uid);
		if(participate != null) {
			participate.setAddress(address);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.participate.toInt());
			adminLog.setDataid(participate.getUid());
			adminLog.setData(EAdminLog.updateParticepate.getName());
			adminLog.setOperation(EAdminLog.updateParticepate.getName());
			adminLog.setDesc(EAdminLog.updateParticepate.getName());
			so.update(participate);
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

}
