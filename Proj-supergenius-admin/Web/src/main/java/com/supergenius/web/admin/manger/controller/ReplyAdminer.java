package com.supergenius.web.admin.manger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.manager.helper.BaseConferenceHP;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.manager.helper.ComplaintHP;
import com.supergenius.web.admin.manager.helper.EmailHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.web.admin.manager.helper.ReplyHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.AppReplyDetailSO;
import com.supergenius.xo.manager.service.AppReplyExpertSO;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ConfeMemberSO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.UserSO;

/**
 * 答辩控制器
 * 
 * @author liubin
 * @date 2016-11-9 下午3:04:16
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ReplyAdminer extends BaseController {

	@Autowired
	AppReplySO so;

	@Autowired
	AppReplyDetailSO appReplyDetailSO;

	@Autowired
	AppReplyExpertSO appReplyExpertSO;

	@Autowired
	UserSO userSO;

	@Autowired
	ExpertSO expertSO;

	@Autowired
	CertificateSO certificateSO;
	
	@Autowired
	ConferenceSO conferenceSO;
	
	@Autowired
	ConfeMemberSO confeMemberSO;

	/**
	 * 答辩主页
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午5:52:35
	 * @return String
	 */
	@RequestMapping(value = "/manager/reply", method = RequestMethod.GET)
	public String challenge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.reply.name());
		model.put(ViewKeyDict.channelname, EChannel.reply.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.totalreplycount, so.getCount());
		model.put(ViewKeyDict.totalsecondreplycount, appReplyDetailSO.getCount(EReplyStage.failReply));
		model.put(ViewKeyDict.rmbareplyasuccesscount, ReplyHP.getAppDegreeSuccessCount(ECertificate.RMBA));
		model.put(ViewKeyDict.smbareplyasuccesscount, ReplyHP.getAppDegreeSuccessCount(ECertificate.SMBA));
		model.put(ViewKeyDict.tmbareplyasuccesscount, ReplyHP.getAppDegreeSuccessCount(ECertificate.TMBA));
		model.put(ViewKeyDict.totalreplyfailedcount, appReplyDetailSO.getCount(EReplyStage.waitRepay, EReplyStage.passReply));
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.levels, EStudentLevel.getEsudentsLevelMap());
		model.put(ViewKeyDict.types, ECertificate.getAboutJudgeDegreeMap());
		return "doreply";
	}

	/**
	 * 查询得到数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25上午11:30:47
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/manager/ajax/reply/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_challenge(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ReplyHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param request
	 * @param name
	 * @param suffix
	 * @param path
	 * @throws Exception
	 * @author liubin
	 * @createtime 2016-11-10下午5:44:59
	 * @return void
	 */
	@RequestMapping(value = "/manager/reply/download", method = RequestMethod.GET)
	public void replyfile_download(HttpServletResponse response, HttpServletRequest request, String name, String suffix, String path) throws Exception {
		FileUtil.download(name, path + name + suffix, request, response);
	}

	/**
	 * 审核开题申请
	 * 
	 * @param response
	 * @param request
	 * @param name
	 * @param suffix
	 * @param path
	 * @throws Exception
	 * @author liubin
	 * @createtime 2016-11-10下午6:33:37
	 * @return void
	 */
	@RequestMapping(value = "/manager/ajax/reply/setcheckreply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setcheckreply(String uid, String reason, String checkresult) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (appReply != null) {
				if (StrUtil.isEmpty(checkresult)) {
					return result(MsgKeyDict.doFailed);
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.reply.toInt());
				adminLog.setDataid(appReply.getUid());
				adminLog.setDesc(reason);
				adminLog.setData(EAdminLog.updateReplyStatus.getName());
				adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
				if ("0".equals(checkresult)) {
					appReply.setReplystage(EReplyStage.failApply);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppOpenreplyNoPassTitle);
					appReplyDetail.setDesc(reason);
					appReplyDetail.setReplystagefrom(EReplyStage.applying);
					appReplyDetail.setReplystageto(EReplyStage.failApply);
					so.update(appReply, appReplyDetail, adminLog);
					if (MsgHP.sendCheckreplyNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				} else {
					appReply.setReplystage(EReplyStage.passApply);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppOpenreplyPassTitle);
					appReplyDetail.setDesc(reason);
					appReplyDetail.setReplystagefrom(EReplyStage.applying);
					appReplyDetail.setReplystageto(EReplyStage.passApply);
					so.update(appReply, appReplyDetail, adminLog);
					if (MsgHP.sendCheckreplyPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 审核开题材料 
	 * 
	 * @param uid
	 * @param reason
	 * @param checkresult
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午7:01:25
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setcheckopenreplyfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setcheckopenreplyfile(String uid, String opentimeok, String openaddress, String specialexpert, String checkopenreplyfile, @RequestParam MultipartFile filedata) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (appReply != null) {
				if (StrUtil.isEmpty(checkopenreplyfile)) {
					return result(MsgKeyDict.doFailed);
				}
				String path = null;
				if (filedata.getSize() != 0) {
					path = FileUtil.uploadFile(filedata, SysConf.AppReplyFilePath);
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.reply.toInt());
				adminLog.setDataid(appReply.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.updateReplyStatus.getName());
				adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
				appReply.setFile2(path);
				if ("0".equals(checkopenreplyfile)) {
					appReply.setReplystage(EReplyStage.failData);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppOpenreplyNoPassTitle);
					appReplyDetail.setReplystagefrom(EReplyStage.checkData);
					appReplyDetail.setReplystageto(EReplyStage.failData);
					so.update(appReply, appReplyDetail, adminLog);
					if (MsgHP.sendCheckreplyFileNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				} else {
					String[] experts = specialexpert.split(BaseMapperDict.comma);
					appReply.setReplystage(EReplyStage.passData);
					appReply.setOpentimeok(opentimeok);
					appReply.setOpenaddress(openaddress);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppOpenreplyPassTitle);
					appReplyDetail.setReplystagefrom(EReplyStage.checkData);
					appReplyDetail.setReplystageto(EReplyStage.passData);
					if (experts.length != 3) {
						return result(MsgKeyDict.doFailed);
					}
					List<AppReplyExpert> list = new ArrayList<AppReplyExpert>();
					AppReplyExpert appReplyExpert = new AppReplyExpert(appReply.getUid(), experts[0]);
					AppReplyExpert appReplyExpert2 = new AppReplyExpert(appReply.getUid(), experts[1]);
					AppReplyExpert appReplyExpert3 = new AppReplyExpert(appReply.getUid(), experts[2]);
					list.add(appReplyExpert);
					list.add(appReplyExpert2);
					list.add(appReplyExpert3);
					so.update(appReply, list, appReplyDetail, adminLog);
					if (appReply.isIsvideotopic()) {//如果不是视频，则进行线下答辩，否则需要去创建视频会议室
						User user = userSO.get(appReply.getUseruid());
						boolean flag = true;
						for (String expertuid : experts) {
							Expert expert = expertSO.get(expertuid);
							if (expert != null) {
								User user2 = userSO.get(expert.getUseruid());
								if (user2 != null) {
									if (!EmailHP.sendSpecifyReplyExpertSuccess(user2.getShowname(), appReply.getName(), appReply.getOpentimeok(), openaddress, user2.getEmail())) {
										flag = false;
									}
								}
							}
						}
					if (user != null) {
						if (flag && EmailHP.sendCheckReplyFileAboutSuccess(user.getShowname(), appReply.getOpentimeok(), WebConf.baseManagerPath + SysConf.MyReplyUrl, user.getEmail(), EContent.email_checkopenreplyfilesuccess)
							&& MsgHP.sendCheckreplyFilePassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), appReply.getOpentimeok(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
							return success();
						}
					}
					} else {
						return success();
					}
//					ConfEntity confEntity = new ConfEntity();
//					confEntity.setConfname(appReply.getName());
//					confEntity.setBegintime(appReply.getOpentimeok().substring(0, appReply.getOpentimeok().indexOf(" ")));
//					confEntity.setConf_password_md5(DigestUtils.md5Hex(SysConf.password));
//					confEntity.setManage_password_md5(DigestUtils.md5Hex(SysConf.password));
//					confEntity.setGrouptype(SeegleConstant.DEfAULT_GROUP);
//					confEntity.setMax_conf_user(SysConf.Max_conf_user);
//					confEntity.setMax_conf_spokesman(SysConf.Max_conf_spokesman);
//					confEntity.setMax_conf_tourist(SysConf.Max_conf_tourist);
//					if (SeegleHP.creatOpenRoom(appReply, confEntity)) {
//						if (user != null) {
//							if (flag && flag2 && flag3 && EmailHP.sendCheckReplyFileAboutSuccess(user.getShowname(), appReply.getOpentimeok(), WebConf.baseManagerPath + SysConf.MyReplyUrl, user.getEmail(), EContent.email_checkopenreplyfilesuccess)
//								&& MsgHP.sendCheckreplyFilePassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
//								return success();
//							}
//						}
//					}
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 创建qq群会议室
	 * @param uid
	 * @param qq
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午6:33:20
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/addqqgroupmetting", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addqqgroupmetting(String uid, String reason) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			User user = userSO.get(appReply.getUseruid());
			if (appReply != null) {
				appReply.setQqgroup(reason);
				Conference conference = new Conference();
		    	conference.setSn(AutoIncrHP.getConferencesn());
		    	conference.setName(appReply.getName());
		    	conference.setTypeuid(appReply.getUid());
		    	conference.setTypename(appReply.getName());
		    	conference.setCid("");//使用qq，此字段为空
		    	conference.setType(EConfer.challenge);
		    	conference.setStatus(EStatus.init);
		    	so.update(appReply, conference);
		    	List<User> list = ReplyHP.getReplyExperts(appReply.getUid());
		    	for (int i = 0; i < list.size(); i++) {
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(list.get(i).getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setPkuid(appReply.getUid());
					confemember.setPkname(appReply.getName());
					confemember.setUsertype(EConfemember.appReply);
					confemember.setUserid(list.get(i).getUid());
					confemember.setUsername(list.get(i).getShowname());
					confemember.setUseralias(list.get(i).getShowname());
					confemember.setEmail(list.get(i).getEmail());
					confemember.setPhone(list.get(i).getPhone());
					confemember.setType(EConfer.reply);
					confemember.setUsertype(EConfemember.appReply);
					confeMemberSO.add(confemember);
				}
		    	ConfeMember confemember = new ConfeMember();
				confemember.setUseruid(user.getUid());
				confemember.setConfuid(conference.getUid());
				confemember.setConfsn(conference.getSn());
				confemember.setCid(conference.getCid());
				confemember.setPkuid(appReply.getUid());
				confemember.setPkname(appReply.getName());
				confemember.setUsertype(EConfemember.appReply);
				confemember.setUserid(user.getUid());
				confemember.setUsername(user.getShowname());
				confemember.setUseralias(user.getShowname());
				confemember.setEmail(user.getEmail());
				confemember.setPhone(user.getPhone());
				confemember.setType(EConfer.reply);
				confemember.setUsertype(EConfemember.student);
				confeMemberSO.add(confemember);
				boolean flag = true;
				for (User user2 : list) {
					if (user2 != null) {
						if (!EmailHP.sendCreatReplyConferenceSuccess(user2.getShowname(), appReply.getName(), user2.getEmail(), reason, WebConf.baseManagerPath + SysConf.MyReplyUrl)) {
							flag = false;
						}
					}
				}
				if (user != null) {
					if (flag && EmailHP.sendCreatReplyConferenceSuccess(user.getShowname(), appReply.getName(), user.getEmail(), reason, WebConf.baseManagerPath + SysConf.MyReplyUrl)
						&& MsgHP.sendCreatQQGroupmettingMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), reason, ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				}
			}
		}
		return null;
	}
		
	/**
	 * 论证会结束，设置论证会结果
	 * 
	 * @param uid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午3:41:00
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setopenreplyresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setopenreplyresult(String uid, String checkresult, String reason) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (StrUtil.isEmpty(checkresult)) {
				return result(MsgKeyDict.doFailed);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setDataid(appReply.getUid());
			adminLog.setDesc(reason);
			adminLog.setData(EAdminLog.updateReplyStatus.getName());
			adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
			if ("0".equals(checkresult)) {
				appReply.setReplystage(EReplyStage.failOpen);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.CheckAppPrereplyFileNoPassTitle);
				appReplyDetail.setDesc(reason);
				appReplyDetail.setReplystagefrom(EReplyStage.endOpen);
				appReplyDetail.setReplystageto(EReplyStage.failOpen);
				so.update(appReply, appReplyDetail, adminLog);
				if (MsgHP.sendOpenReplyNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
					return success();
				}
			} else {
				appReply.setReplystage(EReplyStage.passOpen);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.CheckAppPrereplyFilePassTitle);
				appReplyDetail.setDesc(reason);
				appReplyDetail.setReplystagefrom(EReplyStage.endOpen);
				appReplyDetail.setReplystageto(EReplyStage.passOpen);
				so.update(appReply, appReplyDetail, adminLog);
				if (MsgHP.sendOpenReplyPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 审核预答辩材料
	 * 
	 * @param uid
	 * @param reason
	 * @param checkreplyfile
	 * @param filedata
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午7:51:48
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setcheckprereply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setcheckprereply(String uid, String reason, String checkreplyfile, @RequestParam MultipartFile filedata) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isEmpty(checkreplyfile)) {
			AppReply appReply = so.get(uid);
			String path = null;
			if (filedata.getSize() != 0) {
				path = FileUtil.uploadFile(filedata, SysConf.AppReplyFilePath);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setDataid(appReply.getUid());
			adminLog.setDesc(reason);
			adminLog.setData(EAdminLog.updateReplyStatus.getName());
			adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
			appReply.setFile2(path);
			if ("0".equals(checkreplyfile)) {
				appReply.setReplystage(EReplyStage.failPredata);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.CheckAppPrereplyFileNoPassTitle);
				appReplyDetail.setDesc(reason);
				appReplyDetail.setReplystagefrom(EReplyStage.checkPredata);
				appReplyDetail.setReplystageto(EReplyStage.failPredata);
				so.update(appReply, appReplyDetail, adminLog);
				if (MsgHP.sendCheckAppPrereplyFileNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
					return success();
				}
			} else {
				appReply.setReplystage(EReplyStage.passPredata);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.CheckAppPrereplyFilePassTitle);
				appReplyDetail.setDesc(reason);
				appReplyDetail.setReplystagefrom(EReplyStage.checkPredata);
				appReplyDetail.setReplystageto(EReplyStage.passPredata);
				so.update(appReply, appReplyDetail, adminLog);
				if (MsgHP.sendCheckAppPrereplyFilePassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 答辩材料审核
	 * 
	 * @param uid
	 * @param replytimes
	 * @param specialexpert
	 * @param checkappreply
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午2:36:30
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setreplyfileresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setreplyfileresult(String uid, String replytimeok, String replyaddress, String specialexpert, String checkappreply, @RequestParam MultipartFile filedata) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (appReply != null) {
				if (StrUtil.isEmpty(checkappreply)) {
					return result(MsgKeyDict.doFailed);
				}
				String path = null;
				if (filedata.getSize() != 0) {
					path = FileUtil.uploadFile(filedata, SysConf.AppReplyFilePath);
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.reply.toInt());
				adminLog.setDataid(appReply.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.updateReplyStatus.getName());
				adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
				appReply.setFile2(path);
				if ("0".equals(checkappreply)) {
					appReply.setReplystage(EReplyStage.failReplyData);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppreplyNoPassTitle);
					appReplyDetail.setReplystagefrom(EReplyStage.checkReplyData);
					appReplyDetail.setReplystageto(EReplyStage.failReplyData);
					so.update(appReply, appReplyDetail, adminLog);
					if (MsgHP.sendCheckAppreplyNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				} else {
					appReply.setReplystage(EReplyStage.passReplyData);
					appReply.setReplytimeok(replytimeok);
					appReply.setReplyaddress(replyaddress);
					AppReplyDetail appReplyDetail = new AppReplyDetail();
					appReplyDetail.setAdminuid(AdminHP.getAdminUid());
					appReplyDetail.setAppreplyuid(appReply.getUid());
					appReplyDetail.setUseruid(appReply.getUseruid());
					appReplyDetail.setName(SysConf.CheckAppreplyPassTitle);
					appReplyDetail.setReplystagefrom(EReplyStage.checkReplyData);
					appReplyDetail.setReplystageto(EReplyStage.passReplyData);
					so.update(appReply, appReplyDetail, adminLog);
					if (MsgHP.sendCheckAppreplyPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(),  replytimeok, ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 控件专家的查询条件
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午12:36:14
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/expert/search_condition", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getExpertSearch(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		model.put(ViewKeyDict.levels, EExpertLevel.getValueAndChinese());
		model.put(ViewKeyDict.major, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.type, EExpert.getValueAndChinese());
		return model;
	}
	
	/**
	 * 控件专家列表查询
	 * @param model
	 * @param level
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午12:33:03
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/manager/ajax/expert/searchlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_challenge_expert(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ReplyHP.queryExpert(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 更换专家
	 * 
	 * @param uid
	 * @param expertuid
	 * @param expert2uid
	 * @param expert3uid
	 * @param specialexpert1
	 * @param specialexpert2
	 * @param specialexpert3
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午6:05:52
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setchangeexpert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setchangeexpert(String uid, String expertuid, String expert2uid, String expert3uid, String specialexpert1, String specialexpert2, String specialexpert3) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setDataid(appReply.getUid());
			adminLog.setDesc(EAdminLog.changeExpert.getName());
			adminLog.setData(EAdminLog.changeExpert.getName());
			adminLog.setOperation(EAdminLog.changeExpert.getName());
			boolean flag = true;
			String time = "";
			String address = "";
			if (EReplyStage.passData == appReply.getReplystage()) {
				time = appReply.getOpentimeok();
				address = appReply.getOpenaddress();
			} else {
				time = appReply.getReplytimeok();
				address = appReply.getReplyaddress();
			}
			Conference conference = conferenceSO.getByTypeuid(appReply.getUid());
			if (StrUtil.isNotEmpty(specialexpert1) && !specialexpert1.equals(expertuid)) {
				AppReplyExpert oldAppReplyExpert = appReplyExpertSO.getOne(appReply.getUid(), expertuid);
				if (oldAppReplyExpert != null) {
					oldAppReplyExpert.setStatus(EStatus.disable);// 将被替换掉的申请专家表状态设置为disable;
				}
				Expert oldExpert = expertSO.get(expertuid);
				Expert expert = expertSO.get(specialexpert1);
				if (expert != null && oldExpert != null) {
					AppReplyExpert appReplyExpert = new AppReplyExpert(appReply.getUid(), specialexpert1);
					appReplyExpertSO.update(oldAppReplyExpert, appReplyExpert);
					User oldUser = userSO.get(oldExpert.getUseruid());
					User user = userSO.get(expert.getUseruid());
					if (user != null && oldUser != null) {
						ConfeMember confeMember = confeMemberSO.getByUseruidConfuid(oldUser.getUid(), conference.getUid());
						if (confeMember != null) {
							confeMember.setStatus(EStatus.disable);
							confeMemberSO.update(confeMember);
							BaseConferenceHP.decrExpectJoinCount(conference);//预期参加会议人数-1
						}
						if (!ReplyHP.addConfUser(user, conference, EConfemember.appReply) || !EmailHP.sendChangeSpecifyReplyExpert(oldUser.getShowname(), appReply.getName(), oldUser.getEmail())
								|| !EmailHP.sendSpecifyReplyExpertSuccess(user.getShowname(), appReply.getName(), time, address, user.getEmail())) {
							flag = false;
						}
					}
				}
			}
			if (StrUtil.isNotEmpty(specialexpert2) && !specialexpert2.equals(expert2uid)) {
				AppReplyExpert oldAppReplyExpert = appReplyExpertSO.getOne(appReply.getUid(), expert2uid);
				if (oldAppReplyExpert != null) {
					oldAppReplyExpert.setStatus(EStatus.disable);
				}
				Expert oldExpert = expertSO.get(expert2uid);
				Expert expert = expertSO.get(specialexpert2);
				if (expert != null && oldExpert != null) {
					AppReplyExpert appReplyExpert = new AppReplyExpert(appReply.getUid(), specialexpert2);
					appReplyExpertSO.update(oldAppReplyExpert, appReplyExpert);
					User oldUser = userSO.get(oldExpert.getUseruid());
					User user = userSO.get(expert.getUseruid());
					if (user != null && oldUser != null) {
						ConfeMember confeMember = confeMemberSO.getByUseruidConfuid(oldUser.getUid(), conference.getUid());
						if (confeMember != null) {
							confeMember.setStatus(EStatus.disable);
							confeMemberSO.update(confeMember);
							BaseConferenceHP.decrExpectJoinCount(conference);//预期参加会议人数-1
						}
						if (!ReplyHP.addConfUser(user, conference, EConfemember.appReply) || !EmailHP.sendChangeSpecifyReplyExpert(oldUser.getShowname(), appReply.getName(), oldUser.getEmail())
								|| !EmailHP.sendSpecifyReplyExpertSuccess(user.getShowname(), appReply.getName(), time, address, user.getEmail())) {
							flag = false;
						}
					}
				}
			}
			if (StrUtil.isNotEmpty(specialexpert3) && !specialexpert3.equals(expert3uid)) {
				AppReplyExpert oldAppReplyExpert = appReplyExpertSO.getOne(appReply.getUid(), expert3uid);
				if (oldAppReplyExpert != null) {
					oldAppReplyExpert.setStatus(EStatus.disable);// 将被替换掉的专家状态设置为disable;
				}
				Expert oldExpert = expertSO.get(expert3uid);
				Expert expert = expertSO.get(specialexpert3);
				if (expert != null && oldExpert != null) {
					AppReplyExpert appReplyExpert = new AppReplyExpert(appReply.getUid(), specialexpert3);
					appReplyExpertSO.update(oldAppReplyExpert, appReplyExpert);
					User oldUser = userSO.get(oldExpert.getUseruid());
					User user = userSO.get(expert.getUseruid());
					if (user != null && oldUser != null) {
						ConfeMember confeMember = confeMemberSO.getByUseruidConfuid(oldUser.getUid(), conference.getUid());
						if (confeMember != null) {
							confeMember.setStatus(EStatus.disable);
							confeMemberSO.update(confeMember);
							BaseConferenceHP.decrExpectJoinCount(conference);//预期参加会议人数-1
						}
						if (!ReplyHP.addConfUser(user, conference, EConfemember.appReply) || !EmailHP.sendChangeSpecifyReplyExpert(oldUser.getShowname(), appReply.getName(), oldUser.getEmail())
								|| !EmailHP.sendSpecifyReplyExpertSuccess(user.getShowname(), appReply.getName(), time, address, user.getEmail())) {
							flag = false;
						}
					}
				}
			}
			if (flag) {
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 设置答辩结果
	 * 
	 * @param uid
	 * @param replyresult
	 * @param filedata
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午6:38:03
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setreplyresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setresult(String uid, String replyresult, @RequestParam MultipartFile filedata) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (StrUtil.isEmpty(replyresult)) {
				return result(MsgKeyDict.doFailed);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setDataid(appReply.getUid());
			adminLog.setData(EAdminLog.updateReplyStatus.getName());
			adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
			boolean isSecondReply = true;// 判断是否是二次答辩
			if (appReplyDetailSO.getOne(appReply.getUid(), EReplyStage.replying) == null) {
				isSecondReply = false;
			}
			if ("0".equals(replyresult)) {
				appReply.setReplystage(EReplyStage.failReply);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				if (isSecondReply) {
					appReplyDetail.setName(SysConf.SecondReplyNoPassTitle);
					adminLog.setDesc(SysConf.SecondReplyNoPassTitle);
				} else {
					appReplyDetail.setName(SysConf.ReplyNoPassTitle);
					adminLog.setDesc(SysConf.ReplyNoPassTitle);
				}
				appReplyDetail.setReplystagefrom(EReplyStage.endReply);
				appReplyDetail.setReplystageto(EReplyStage.failReply);
				so.update(appReply, appReplyDetail, adminLog);
				if (isSecondReply) {
					if (MsgHP.sendSecondReplyNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				} else {
					if (MsgHP.sendReplyNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				}
			} else {
				appReply.setReplystage(EReplyStage.passReply);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				if (isSecondReply) {
					appReplyDetail.setName(SysConf.SecondReplyPassTitle);
					adminLog.setDesc(SysConf.SecondReplyPassTitle);
				} else {
					appReplyDetail.setName(SysConf.ReplyPassTitle);
					adminLog.setDesc(SysConf.ReplyPassTitle);
				}
				appReplyDetail.setReplystagefrom(EReplyStage.endReply);
				appReplyDetail.setReplystageto(EReplyStage.passReply);
				so.update(appReply, appReplyDetail, adminLog);
				if (isSecondReply) {
					if (MsgHP.sendSecondReplyPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				} else {
					if (MsgHP.sendReplyPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
						return success();
					}
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 审核研究报告
	 * 
	 * @param uid
	 * @param reason
	 * @param checkreport
	 * @param filedata
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午6:19:00
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/setcheckreport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setcheckreport(String uid, String reason, String checkreport, @RequestParam MultipartFile filedata) {
		if (StrUtil.isNotEmpty(uid)) {
			AppReply appReply = so.get(uid);
			if (StrUtil.isEmpty(checkreport)) {
				return result(MsgKeyDict.doFailed);
			}
			String path = null;
			if (filedata.getSize() != 0) {
				path = FileUtil.uploadFile(filedata, SysConf.AppReplyFilePath);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setDataid(appReply.getUid());
			adminLog.setDesc(reason);
			adminLog.setData(EAdminLog.updateReplyStatus.getName());
			adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
			appReply.setFile2(path);
			if ("0".equals(checkreport)) {
				appReply.setReplystage(EReplyStage.failReport);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setDesc(reason);
				appReplyDetail.setName(SysConf.CheckReportNoPassTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.checkReport);
				appReplyDetail.setReplystageto(EReplyStage.failReport);
				so.update(appReply, appReplyDetail, adminLog);
				if (MsgHP.sendReportNoPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), ComplaintHP.getDegree(appReply.getCertificated()).name())) {
					return success();
				}
			} else {
				appReply.setReplystage(EReplyStage.passReport);
				AppReplyDetail appReplyDetail = new AppReplyDetail();
				appReplyDetail.setAdminuid(AdminHP.getAdminUid());
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setDesc(reason);
				appReplyDetail.setName(SysConf.CheckReportPassTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.checkReport);
				appReplyDetail.setReplystageto(EReplyStage.passReport);
				ECertificate level = ComplaintHP.getDegree(appReply.getCertificated());
				Certificate certificate = new Certificate(appReply.getUseruid(), AutoIncrHP.getCertificatesn(), appReply.getUid(), appReply.getMajor(), level);
				so.update(appReply, appReplyDetail, adminLog, certificate);
				boolean flag = MsgHP.sendReportPassMsg(AdminHP.getAdminUid(), appReply.getUseruid(), appReply.getMajor().getName(), level.getName());
				User user = userSO.get(appReply.getUseruid());
				if (level == ECertificate.SMBA) {
					if (certificateSO.getOne(appReply.getUseruid(), level) != null) {
						if (user != null) {
							if (flag && EmailHP.sendCanAppreply(user.getShowname(), appReply.getMajor().getName(), ECertificate.TMBA.getName(), WebConf.baseManagerPath + SysConf.MyReplyUrl, user.getEmail())) {
								return success();
							}
						}
					}
				} else {
					if (user != null) {
						if (level == ECertificate.RMBA) {
							if (flag && EmailHP.sendCanAppreply(user.getShowname(), appReply.getMajor().getName(), ECertificate.RMBA.getName(), WebConf.baseManagerPath + SysConf.MyReplyUrl, user.getEmail())) {
								return success();
							}
						}
					}
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或者解冻
	 * 
	 * @param status
	 * @param ids
	 * @param dopwd
	 * @param desc
	 * @param adminuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午2:32:25
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/reply/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setstatus(@PathVariable int status, String[] ids, String dopwd, String desc, String adminuid) {
		EStatus status2 = EStatus.get(status);
		if (AdminHP.isDopwd(dopwd)) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.reply.toInt());
			adminLog.setOperation(EAdminLog.updateReplyStatus.getName());
			adminLog.setData(EAdminLog.updateReplyStatus.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			AppReply appReply = so.get(ids[0]);
			if (appReply != null) {
				appReply.setStatus(status2);
			}
			so.update(appReply, adminLog);
			return success();
		}
		return result(MsgKeyDict.dopwdIsWrong);
	}
}
