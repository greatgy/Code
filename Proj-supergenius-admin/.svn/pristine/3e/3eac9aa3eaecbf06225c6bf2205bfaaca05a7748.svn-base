package com.supergenius.web.admin.enterpriser.controller;

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
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.enterpriser.hellper.LectureHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.enterpriser.service.LectureSO;


/** 
 * 讲座管理contorller
 * @author chenminchang
 * @date 2016-10-25 上午11:03:30 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LectureAdminer extends BaseController {

	@Autowired
	private LectureSO so;
	
	/**
	 * 跳转讲座管理
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-10-25下午4:06:01
	 */
	@RequestMapping(value = "/enterpriser/lecture", method = RequestMethod.GET)
	public String appstudent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lecture.name());
		model.put(ViewKeyDict.channelname, EChannel.lecture.getName());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.lecturecount, so.getLectureCount(null));
		model.put(ViewKeyDict.count, so.getCount());
		return "dolecture";
	}
	
	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/enterpriser/ajax/lecture/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> lecture_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LectureHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加讲座
	 * @return
	 * @author chenminchang
	 * @create 2016-10-25下午6:51:08
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/add", method = RequestMethod.POST)
	@ResponseBody
	@Deprecated
	public Map<String, Object>  lecture_add(Lecture lecture, String timeStr, @RequestParam MultipartFile filedata) {
		if (lecture != null) {
			lecture.setTime(DateUtil.parse(timeStr));
			lecture.setSemester(1);
			lecture.setStatus(EStatus.init);//未开始报名
			lecture.setSn(AutoIncrHP.getLecturesn());
			if (filedata != null && filedata.getSize() > 0) {
				String path = FileUtil.uploadFile(filedata, SysConf.LectuerFilePath);
				lecture.setFile(path);
			}
			if (so.add(lecture))
				return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 删除讲座
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-27下午9:02:56
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> lecture_delete(String[] ids) {
		if (so.delete(ids[0]))
			return success();
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 更新讲座
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26上午10:18:42
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lecture_update(String uid, String timeStr, String name, String username, String address, double fee, int maxcount, int registercount, String notice, @RequestParam MultipartFile filedata) {
		if (uid != null) {
			Lecture lecture = so.get(uid);
			if (lecture != null) {
				if (filedata != null && filedata.getSize() > 0) {
					String path = FileUtil.uploadFile(filedata, SysConf.LectuerFilePath);
					lecture.setFile(path);
				}
				lecture.setName(name);
				lecture.setUsername(username);
				lecture.setTime(DateUtil.parse(timeStr));
				lecture.setAddress(address);
				lecture.setFee(fee);
				lecture.setMaxcount(maxcount);
				lecture.setRegistercount(registercount);
				lecture.setNotice(notice);
				if (so.update(lecture))
					return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 更新lecture的状态
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26上午11:24:15
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lecture_status(@PathVariable int status, String uid) {
		if (LectureHP.updateStatus(uid, EStatus.get(status)))
			return success();
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 改变讲座的时间地点以及确定时间，修改状态发送邮件
	 * @param status
	 * @param ids
	 * @param adminuid
	 * @param dopwd
	 * @param desc
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午4:50:03
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/change/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lecture_change(@PathVariable int status, String uid, String reason) {
		if (LectureHP.changeLecture(uid, EStatus.get(status), reason)) 
			return success();
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 增加学期
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午6:12:50
	 */
	@RequestMapping(value = "/enterpriser/ajax/lecture/addsemester", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lecture_addsemester(String uid) {
		if (LectureHP.addSemester(uid))
			return success();
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 下载文件
	 * @return
	 * @author chenminchang
	 * @throws Exception 
	 * @create 2016-10-27上午11:54:22
	 */
	@RequestMapping(value = "/enterpriser/lecture/download", method = RequestMethod.GET)
	public String lecture_download(HttpServletResponse response, HttpServletRequest request, String uid) throws Exception {
		if (StrUtil.isNotEmpty(uid)) {
			Lecture lecture = so.get(uid);
			if (lecture != null)
				FileUtil.download(lecture.getFilename(), lecture.getFile(), request, response);
		}
		return null;
	}
}
