package com.supergenius.web.admin.manger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.manager.helper.ComplaintHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Complaint;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ComplaintSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkScheduleSO;

/** 
 * 举报管理
 * @author liubin
 * @date 2016-11-6 下午6:23:39 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ComplaintAdminer extends BaseController {

	@Autowired
	ComplaintSO so;
	
	@Autowired
	JudgeSO judgeSO;
	
	@Autowired
	PkScheduleSO pkScheduleSO;
	
	@Autowired
	AppReplySO appReplySO;
	
	@Autowired
	ExpertSO expertSO;
	
	@Autowired
	CertificateSO certificateSO;
	
	/**
	 * 获得页面数据
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午6:28:50
	 * @return String
	 */
	@RequestMapping(value = "/manager/complaint", method = RequestMethod.GET)
	public String challenge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.complaint.name());
		model.put(ViewKeyDict.channelname, EChannel.complaint.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.managerBaseRootPath, WebConf.baseManagerPath);
		model.put(ViewKeyDict.total, so.getCount());
		model.put(ViewKeyDict.count, so.getCount(EUser.expert));
		model.put(ViewKeyDict.count2, so.getCount(EUser.judgement));
		model.put(ViewKeyDict.success, so.getEableCount());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		Map<String, String> map = new HashMap<String, String>();
		map.put(EUser.judgement.toString(), EUser.judgement.getTypeName());
		map.put(EUser.expert.toString(), EUser.expert.getTypeName());
		model.put(ViewKeyDict.types, map);
		return "docomplaint";
	}
	
	/**
	 * 查询得到数据
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午6:52:04
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/manager/ajax/complaint/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_complaint(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ComplaintHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 设置举报结果
	 * @param uid
	 * @param dopwd
	 * @param desc
	 * @param result
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7下午4:47:50
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/complaint/setresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setresult(String uid, String status, String result) {
		if (StrUtil.isNotEmpty(uid)) {
			Complaint complaint = so.get(uid);
			if (complaint != null) {
				complaint.setResult(result);
				complaint.setStatus(EStatus.get(status));
				if (complaint.getStatus() == EStatus.enable) {// 举报成功发送消息
					if (complaint.getTousertype() == EUser.judgement) {
						PKSchedule pkSchedule = pkScheduleSO.get(complaint.getRefuid());
						if (pkSchedule != null) {
							Judge judge = judgeSO.getOne(complaint.getTouseruid(), pkSchedule.getMajor());
							if (judge != null) {
								if (ComplaintHP.isGetPunishOfComplaint(complaint.getTouseruid(), pkSchedule.getMajor(), EUser.judgement)) {
									judge.setStatus(EStatus.disable);
									Certificate certificate = certificateSO.get(judge.getCertificateuid());
									if (certificate != null) {
										certificate.setStatus(EStatus.disable);
									}
									so.update(complaint, judge, certificate);
									//裁判得到惩罚，发送消息
									if (MsgHP.sendCancelJudgementQualification(AdminHP.getAdminUid(), complaint.getTouseruid()) && MsgHP.sendCompJudgeSuccess(AdminHP.getAdminUid(), complaint.getTouseruid(), complaint.getRefname())) {
										return success();
									}
								} else {
									judge.setComplaintcount(judge.getComplaintcount() + 1);// 没有惩罚则被举报次数加1
									so.update(complaint, judge, null);
									if (MsgHP.sendCompJudgeSuccessToJudge(AdminHP.getAdminUid(), complaint.getTouseruid(), complaint.getRefname()) && MsgHP.sendCompJudgeSuccess(AdminHP.getAdminUid(), complaint.getTouseruid(), complaint.getRefname())) {
										return success();
									}
								}
							}
						}
					} else if (complaint.getTousertype() == EUser.expert) {
						AppReply appReply = appReplySO.get(complaint.getRefuid());
						if (appReply != null) {
							ECertificate certificate = ComplaintHP.getDegree(appReply.getCertificated());
							Expert expert = null;
							if (certificate == ECertificate.RMBA) {
								expert = expertSO.getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.expert);
							} else if (certificate == ECertificate.SMBA) {
								expert = expertSO.getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.highExpert);
							} else {
								expert = expertSO.getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.specialExpert);
							}
							if (expert != null) {
								expert.setStatus(EStatus.disable);//取消裁判资格
								Certificate certificate2 = certificateSO.get(expert.getCertificateuid());
								if (certificate2 != null) {
									certificate2.setStatus(EStatus.disable);
								}
								so.update(complaint, expert, certificate2);
								if (MsgHP.sendComExpertSuccess(AdminHP.getAdminUid(), complaint.getFromuseruid(), complaint.getRefname(), expert.getLevel().getName()) && MsgHP.sendComExpertSuccessToExpert(AdminHP.getAdminUid(), complaint.getTouseruid(), complaint.getRefname(), expert.getLevel().getName())) {
									return success();
								}
							}
						}
					}
				} else {
					so.update(complaint);
					if (MsgHP.sendCompliantNoPass(AdminHP.getAdminUid(), complaint.getFromuseruid(), complaint.getRefname())) {
						return success();
					}
				}
			}
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 下载文件
	 * @param response
	 * @param request
	 * @param name
	 * @param suffix
	 * @param path
	 * @throws Exception
	 * @author liubin
	 * @createtime 2016-11-7下午5:16:07
	 * @return void
	 */
	@RequestMapping(value = "/manager/complaint/download", method = RequestMethod.GET)
	public void complaint_download(HttpServletResponse response, HttpServletRequest request, String name, String suffix, String path) throws Exception {
		FileUtil.download(name, path + name + suffix, request, response);
	}
}
