package com.supergenius.web.admin.moral.controller;

import java.util.HashMap;
import java.util.Locale;
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
import com.genius.core.base.utils.FileUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.DocHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Doc;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.service.DocSO;

/**
 * 培训讲义管理
 * 
 * @author LiJiacheng
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class DocAdminer extends BaseController {

	@Autowired
	DocSO so;

	/**
	 * 打开页面
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/doc" }, method = RequestMethod.GET)
	public String doc(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.doc.name());
		model.put(ViewKeyDict.site, EChannel.moral.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.doc, Locale.CHINA));
		Map<String, Object> channelMap = new HashMap<String, Object>();
		for (EChapter item : EChapter.values()) {
			channelMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, channelMap);
		return "dodoc";
	}

	/**
	 * 查询时组织的数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> doc_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = DocHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加讲义
	 * @param doc
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doc_add(Doc doc ,@RequestParam MultipartFile docfile) {
		if (docfile != null && docfile.getSize() > 0) {
			doc.setFile(FileUtil.uploadFile(docfile, SysConf.MoralDocPath));
		}
		doc.setSn(DocHP.getDocSn());
		doc.setSortorder((int)(System.currentTimeMillis()/1000));
		if (so.add(doc)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑讲义
	 * @param doc
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doc_edit(Doc newdoc ,@RequestParam MultipartFile docfile) {
		if (docfile != null && docfile.getSize() > 0) {
			newdoc.setFile(FileUtil.uploadFile(docfile, SysConf.MoralDocPath, newdoc.getName()));
		}else {
			Doc doc = so.get(newdoc.getUid());
			newdoc.setFile(doc.getFile());
		}
		if (so.update(newdoc)) {
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 删除讲义
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doc_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 修改状态
	 * @param ids
	 * @param status
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doc_status(String[] ids, @PathVariable int status) {
		if (so.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 讲义上移
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/up" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doc_up(String ids) {
		if (so.doc_up(ids)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 讲义下移
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/moral/ajax/doc/down" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doc_down(String ids) {
		if (so.doc_down(ids)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 下载
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = { "/moral/doc/download/{uid:.{32}}" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doc_download(@PathVariable String uid, HttpServletRequest request, HttpServletResponse response) {
		Doc doc = so.get(uid);
		String realName = doc.getName();
		String filePath = doc.getFile();
		try {
			FileUtil.download(realName, filePath, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
