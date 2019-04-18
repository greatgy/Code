package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.StudentSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 证书管理HP
 * @author liubin
 * @date 2016-11-8 下午4:33:08 
 */
public class CertificateHP extends BaseHP {

	private static CertificateSO so;
	private static UserSO userSO;
	private static StudentSO studentSO;
	
	private static CertificateSO getSO() {
		if (so == null) {
			so = spring.getBean(CertificateSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static StudentSO getStudentSO() {
		if (studentSO == null) {
			studentSO = spring.getBean(StudentSO.class);
		}
		return studentSO;
	}
	
	/**
	 * 根据查询条件得到数据
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午5:36:39
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.keyword, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		List<Certificate> list = getSO().searchList(map);
		for (Certificate certificate : list) {
			User user = getUserSO().get(certificate.getUseruid());
			Student student = getStudentSO().get(certificate.getUid());
			certificate.setUser(user);
			if (student != null) {
				certificate.setStudentsn(student.getSn());
			}
			if (user != null) {
				switch(certificate.getType()) {
				case RMBA:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.RMBA.getName()));
					break;
				case SMBA:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.SMBA.getName()));
					break;
				case TMBA:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.TMBA.getName()));
					break;
				case judge:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.judge.getName()));
					break;
				case expert:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.expert.getName()));
					break;
				case seniorExpert:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.seniorExpert.getName()));
					break;
				case specialExpert:
					certificate.setCertificatename(String.format(SysConf.CertificateName, user.getShowname(), certificate.getMajor().getName(), ECertificate.specialExpert.getName()));
					break;
				default:
					break;
				}
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
