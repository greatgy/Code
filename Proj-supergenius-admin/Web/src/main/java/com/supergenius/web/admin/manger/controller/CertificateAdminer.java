package com.supergenius.web.admin.manger.controller;

import java.util.HashMap;
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
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.manager.helper.CertificateHP;
import com.supergenius.web.admin.manager.helper.EmailHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 证书管理
 * 
 * @author liubin
 * @createtime 2016-11-8下午4:29:07
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CertificateAdminer extends BaseController {

	@Autowired
	CertificateSO so;
	
	@Autowired
	UserSO userSO;
	
	/**
	 * 证书管理主页
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9上午11:22:58
	 * @return String
	 */
	@RequestMapping(value = "/manager/certificate", method = RequestMethod.GET)
	public String certificate(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.certificate.name());
		model.put(ViewKeyDict.channelname, EChannel.certificate.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(EStudentLevel.majordomo.name(), EStudentLevel.majordomo.getName());
		map.put(EStudentLevel.vicepresident.name(), EStudentLevel.vicepresident.getName());
		map.put(EStudentLevel.president.name(), EStudentLevel.president.getName());
		model.put(ViewKeyDict.level, map);
		model.put(ViewKeyDict.types, ECertificate.getCertificatesForSearch());
		return "docertificate";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9上午11:22:52
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = { "/manager/ajax/certificate/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> certificate_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CertificateHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 颁发证书
	 * @param uid
	 * @param imglittle
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午12:00:41
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = { "/manager/ajax/certificate/setcertificate" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setcertificate(String uid, String imglittle) {
		if (StrUtil.isNotEmpty(uid)) {
			Certificate certificate = so.get(uid);
			if (certificate != null) {
				if (StrUtil.isNotEmpty(imglittle)) {
					String[] img = imglittle.split(BaseMapperDict.comma);
					certificate.setImgoriginal(img[0]);
					certificate.setImgbig(img[1]);
					certificate.setImg(img[2]);
					certificate.setImglittle(img[3]);
					so.update(certificate);
					User user = userSO.get(certificate.getUseruid());
					if (user != null) {
						EmailHP.sendAwardCertificateSuccess(user.getShowname(), certificate.getMajor().getName(), certificate.getType().getName(), user.getEmail());
					}
					MsgHP.sendAwardCertificateMsg(AdminHP.getAdminUid(), certificate.getUseruid(), certificate.getMajor().getName(), certificate.getType().getName());
					return success();
				}
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 修改状态
	 * @param ids
	 * @param status
	 * @param adminuid
	 * @param dopwd
	 * @param desc
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午12:38:47
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/certificate/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> certificate_status(String[] ids, @PathVariable int status, String adminuid, String dopwd, String desc) {
		EStatus status2 = EStatus.get(status);
		if (AdminHP.isDopwd(dopwd)) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.certificate.toInt());
			adminLog.setOperation(EAdminLog.updateCertificateStatus.getName());
			adminLog.setData(EAdminLog.updateCertificateStatus.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			Certificate certificate = so.get(ids[0]);
			if (certificate != null) {
				certificate.setStatus(status2);
			}
			so.update(certificate, adminLog);
			return success();
		}
		return result(MsgKeyDict.dopwdIsWrong);
	}
}
