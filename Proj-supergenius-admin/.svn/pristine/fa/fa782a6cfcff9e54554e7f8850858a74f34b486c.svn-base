package com.supergenius.web.admin.career.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
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
import com.supergenius.web.admin.career.helper.CareerMusicHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.career.entity.Music;
import com.supergenius.xo.career.service.MusicSO;

/**
 * 背景音乐管理
 * 
 * @author ChenQi
 * @date 2017年12月28日15:34:00
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerMusicAdminer extends BaseController {

	@Autowired
	private MusicSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 背景音乐管理页面
	 * 
	 * @author ChenQi
	 * @date 2017年12月28日15:38:10
	 * @return String
	 */
	@RequestMapping(value = "/career/careermusic", method = RequestMethod.GET)
	public String music(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careermusic.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careermusic, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(MapperDict.count, so.getCount(EStatus.enable));
		model.put(ViewKeyDict.photopath, SysConf.CareerPhotoPath);
		return "docareermusic";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017年12月28日15:43:22
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careermusic/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> music_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CareerMusicHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除背景音乐
	 * @param ids
	 * @return
	 * @author ChenQi
	 * @data 2017年12月28日15:53:05
	 */
	@RequestMapping(value = "/career/ajax/careermusic/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> music_delete(String[] ids) {
		for (String id : ids) {
			Music music = so.get(id);
			if (music != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.careermusic.toInt());
				adminLog.setOperation(EAdminLog.deleteMusic.getName());
				adminLog.setData(EAdminLog.deleteMusic.getName());
				adminLog.setDesc(EAdminLog.deleteMusic.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				music.setStatus(EStatus.disable);
				if (so.update(music)) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 添加背景音乐
	 * @param addorder 序号
	 * @param addname 歌曲名称
	 * @param file	文件内容
	 * @param temp  本地还是网络
	 * @author ChenQi
	 * @data 2017年12月28日15:54:30
	 * @return
	 */
	@RequestMapping(value = "/career/ajax/careermusic/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> music_add(String addorder, String addname, @RequestParam MultipartFile localfile,String networkfile, String temp) {
		Music music = new Music();	
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(addname)) {
			music.setName(addname);	
		}
		//表示上传的file是本地歌曲，需要上传到服务器
		if (temp.equals("0")) {
			//上传歌曲，返回上传的路径
			String localUrl = FileUtil.uploadFile(localfile, SysConf.CareerMusicPath);
			music.setUrl(localUrl);
		} else {
			music.setUrl(networkfile);
		}
		music.setTemp(Integer.parseInt(temp));
		music.setOrder(Integer.parseInt(addorder));
		music.setCreatetime(new DateTime());	
		if (StrUtil.isNotEmpty(adminUid)) {
			music.setAdminuid(adminUid);
			music.setUpdatetime(new DateTime());
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.careermusic.toInt());
		adminLog.setDataid(music.getUid());
		adminLog.setDesc(EAdminLog.addMusic.getName());
		adminLog.setData(EAdminLog.addMusic.getName());
		adminLog.setOperation(EAdminLog.addMusic.getName());
		if (so.add(music)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 音乐编辑
	 * @param uid
	 * @param title
	 * @param author
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/career/ajax/careermusic/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> music_edit(String uid, String order, String name, @RequestParam MultipartFile localfile,String networkfile, String url, String temp) {
		Music music = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (music != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careermusic.toInt());
			adminLog.setDataid(music.getUid());
			adminLog.setDesc(EAdminLog.addMusic.getName());
			adminLog.setData(EAdminLog.updateMusic.getName());
			adminLog.setOperation(EAdminLog.updateMusic.getName());	
			music.setName(name);
			//表示上传的file是本地歌曲，需要上传到服务器
			if (temp.equals("0")) {
				//文件大小超过10M
				if (localfile.getSize() > 10*1024) {
					return result(MsgKeyDict.updateFailed);
				}
				//上传歌曲，返回上传的路径
				String localUrl = FileUtil.uploadFile(localfile, SysConf.StartupMusicPath);
				music.setUrl(localUrl);
				music.setTemp(Integer.parseInt(temp));
			} else if (temp.equals("1")) {
				music.setUrl(networkfile);
				music.setTemp(Integer.parseInt(temp));
			} else {
				music.setUrl(url);
			}
			music.setOrder(Integer.parseInt(order));	
			if (StrUtil.isNotEmpty(adminUid)) {
				music.setAdminuid(adminUid);
				music.setUpdatetime(new DateTime());
			}
			if (so.update(music)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

}
