package com.supergenius.web.admin.manager.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Major;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.MajorSO;
import com.supergenius.xo.manager.service.StudentSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 学员管理的后台hp
 * @author chenminchang
 * @date 2016-10-31 上午9:55:43 
 */
public class StudentHP extends BaseHP {
	
	@Autowired
	private static StudentSO so;
	@Autowired
	private static MajorSO majorSO;
	@Autowired
	private static UserLevelSO userLevelSO;
	@Autowired
	private static UserSO userSO;
	@Autowired
	private static CertificateSO certificateSO;
	
	private static StudentSO getSO() {
		if (so == null) {
			so = spring.getBean(StudentSO.class);
		}
		return so;
	}
	
	private static MajorSO getMajorSO() {
		if (majorSO == null) {
			majorSO = spring.getBean(MajorSO.class);
		}
		return majorSO;
	}
	
	private static UserLevelSO getUserLevelSO() {
		if (userLevelSO == null) {
			userLevelSO = spring.getBean(UserLevelSO.class);
		}
		return userLevelSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static CertificateSO getCertificateSO() {
		if (certificateSO == null) {
			certificateSO = spring.getBean(CertificateSO.class);
		}
		return certificateSO;
	}
	
	/**
	 * 
	 * 获取每个专业的学员人数
	 * @author chenminchang
	 * @create 2016-10-31上午10:00:34
	 */
	public static Map<String, Object> getStudentCountByMajor() {
		Map<String, Object> map = new HashMap<>();
		List<Major> majorList = getMajorSO().getList();
		for (Major major : majorList) {
			map.put(major.getTypeName(), getUserLevelSO().getCount(major.getType(), EUser.student));
		}
		return map;
	}
	
	
	/**
	 * 获取查询结果
	 * @param model
	 * @return
	 * @author chenminchang
	 * @create 2016年12月7日下午6:47:20
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, EMajor.getByName(model.get(ViewKeyDict.major).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keywords))) {
			map.put(MapperDict.studentsn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, ELevelChannel.getByName(model.get(ViewKeyDict.channel).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.level))) {
			map.put(MapperDict.levelto, EStudentLevel.get(model.get(ViewKeyDict.level).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.certificate))) {
			map.put(MapperDict.certificate, ECertificate.get(model.get(ViewKeyDict.certificate).toString()));
		}
		map.put(MapperDict.userleveltype, EUser.student);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getUserLevelSO().searchCount(map));
		List<UserLevel> list = getUserLevelSO().search(map);
		for (UserLevel userLevel : list) {
			Student student = getSO().get(userLevel.getUseruid());
			if (student != null)
				userLevel.setStudetsn(student.getSn());
			User user = getUserSO().get(userLevel.getUseruid());
			userLevel.setUsername(user.getName());
			userLevel.setUsersn(user.getUsersn());
			userLevel.setLastlogintime(user.getLastlogintimeStr());
			userLevel.setLastloginip(user.getLastloginip());
			userLevel.setMajorName(userLevel.getMajor().getName());
			userLevel.setLevel(userLevel.getLevelto());
			if (StrUtil.isNotEmpty(userLevel.getCertificateuid())) {
				Certificate certificate = getCertificateSO().get(userLevel.getCertificateuid());
				userLevel.setCertificatename(certificate.getType().getName());
				userLevel.setCertificatesrc(certificate.getImglittle());
			}
			//获取他成为学员的时间，因为student表是申请时插入，所以只能在userlevel中获取
			if (!ELevelChannel.isBeComeStudentType(userLevel.getChannel())) {//如果当前的userlevel不是申请学员
				//根据createtime升序拿到第一个成为学员的userlevel，不分专业
				UserLevel beforeUserLevel = getUserLevelSO().getList(userLevel.getUseruid(), EUser.student, ELevelChannel.getBeComeStudentType()).get(0);
				if (userLevel.getCreatetime().isAfter(beforeUserLevel.getCreatetime()))//如果查询出来的那条userlevel在当前的userlevel的createtime之前
					userLevel.setCreatetime(beforeUserLevel.getCreatetime());//将时间设置给他，供给前台显示，不插入数据库
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 修改学员等级
	 * @param string
	 * @param eStudentLevel
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1下午4:28:14
	 */
	public static boolean setLevel(String uid, EStudentLevel level, String desc, AdminLog adminLog) {
		UserLevel oldUserLevel = getUserLevelSO().get(uid);
		if (oldUserLevel != null && level.toInt() != oldUserLevel.getLevelto()) {//不允许同级设置
			UserLevel newUserLevel = createUserLevel(oldUserLevel, level, desc);
			if (newUserLevel == null)
				return true;
			List<UserLevel> addUserLevelList = new ArrayList<>();//将所有的UserLeve放到这里
			List<Certificate> addCertificateList = new ArrayList<>();//将所有的需要添加的证书放到这里
			List<Certificate> removeCertificateList = new ArrayList<>();//将所有的需要删除的证书放到这里
			if (EStudentLevel.vicepresident.equals(level) || EStudentLevel.president.equals(level)) {//副总裁或总裁
				if (getCertificateSO().getOne(newUserLevel.getMajor(), newUserLevel.getUseruid(), ECertificate.RMBA) == null) {//判断原来是否有RMBA证书
					addCertificateList.add(createCertificate(newUserLevel, ECertificate.RMBA));
				}
				if (getCertificateSO().getOne(newUserLevel.getMajor(), newUserLevel.getUseruid(), ECertificate.SMBA) == null) {//判断原来是否有SMBA证书
					Certificate smba = createCertificate(newUserLevel, ECertificate.SMBA);
					addCertificateList.add(smba);
					newUserLevel.setCertificateuid(smba.getUid());
				}
				if (EStudentLevel.president.equals(level)) {//总裁
					List<UserLevel> userLevelList = getUserLevelSO().getList(newUserLevel.getUseruid(), EUser.student);//所有专业
					if (userLevelList != null && userLevelList.size() > 1) { //有多个专业
						for (UserLevel userlevel : userLevelList) {
							if (!userlevel.getMajor().equals(newUserLevel.getMajor())) {//将其他专业中的某一个专业等级设置为总裁
								if (!EStudentLevel.president.equals(userlevel.getLevelto())) {//他专业不是总裁等级
									if (getCertificateSO().getOne(userlevel.getMajor(), userlevel.getUseruid(), ECertificate.SMBA) == null) { //判断其他专业是否有SMBA证书
										addCertificateList.add(createCertificate(userlevel, ECertificate.SMBA));
									}
									if (getCertificateSO().getOne(userlevel.getMajor(), userlevel.getUseruid(), ECertificate.RMBA) == null) { //判断其他专业是否有RMBA证书
										addCertificateList.add(createCertificate(userlevel, ECertificate.RMBA));
									}
									Certificate tmba1 = createCertificate(userlevel, ECertificate.TMBA);
									UserLevel userLevel1 = createUserLevel(userlevel, level, desc);
									userLevel1.setCertificateuid(tmba1.getUid());//将tmba证书uid存入userlevel
									addCertificateList.add(tmba1);
									addUserLevelList.add(userLevel1);
									break;
								} else { //判断其他专业是总裁等级，换下一个专业
									continue;
								}
								
							}
						}
					} 
					Certificate tmba2 = createCertificate(newUserLevel, ECertificate.TMBA);//添加当前专业的tmba证书
					newUserLevel.setCertificateuid(tmba2.getUid());
					addCertificateList.add(tmba2);
				} else { //判断是不是从总裁降到副总裁的
					if (EStudentLevel.president.equals(oldUserLevel.getLevelto())) {
						removeCertificateList.add(getCertificateSO().getOne(newUserLevel.getMajor(), newUserLevel.getUseruid(), ECertificate.TMBA));
						newUserLevel.setCertificateuid(getCertificateSO().getOne(newUserLevel.getMajor(), newUserLevel.getUseruid(), ECertificate.SMBA).getUid());
					}
				}
			} else {
				if (StrUtil.isNotEmpty(newUserLevel.getCertificateuid())) {
					newUserLevel.setCertificateuid(null);
				}
				removeCertificateList.addAll(getCertificateSO().getList(newUserLevel.getUseruid(), newUserLevel.getMajor(), ECertificate.getAboutJudgeDegree()));
			}
			addUserLevelList.add(newUserLevel);
			return getUserLevelSO().addAndDisablether(addUserLevelList, addCertificateList, removeCertificateList, adminLog);
		}
		return false;
	}
	
	/**
	 * 生成新的证书
	 * @param newUserLevel
	 * @param ecertificate
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2上午10:18:56
	 */
	public static Certificate createCertificate(UserLevel userLevel, ECertificate ecertificate) {
		Certificate smba = new Certificate();
		smba.setUseruid(userLevel.getUseruid());
		smba.setSn(AutoIncrHP.getCertificatesn());
		smba.setRefuid(userLevel.getUid());
		smba.setMajor(userLevel.getMajor());
		smba.setType(ecertificate);
		smba.setDesc(userLevel.getChannel().getName());
		return smba;
	}
	
	/**
	 * 生成新的userleve
	 * @param userlevel
	 * @param level
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2上午10:48:47
	 */
	public static UserLevel createUserLevel(UserLevel oldUserLevel, EStudentLevel level, String desc) {
		if (oldUserLevel.getLevelto() == Integer.valueOf(level.toString()))
			return null;
		UserLevel newUserLevel = new UserLevel();
		newUserLevel.setUseruid(oldUserLevel.getUseruid());
		newUserLevel.setType(oldUserLevel.getType());
		newUserLevel.setMajor(oldUserLevel.getMajor());
		newUserLevel.setLevelfrom(oldUserLevel.getLevelto());
		newUserLevel.setLevelto(Integer.valueOf(level.toString()));
		newUserLevel.setCertificateuid(oldUserLevel.getCertificateuid());
		newUserLevel.setDesc(desc);
		newUserLevel.setChannel(ELevelChannel.specialLevel);
		return newUserLevel;
	}
}
