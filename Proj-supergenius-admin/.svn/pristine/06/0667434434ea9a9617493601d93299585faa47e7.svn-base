package com.supergenius.web.admin.tpi.controller;


import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.ProjectHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Link;
import com.supergenius.xo.tpi.entity.MergerIndicator;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.EProjectState;
import com.supergenius.xo.tpi.enums.EType;
import com.supergenius.xo.tpi.service.ProjectSO;
import com.supergenius.xo.tpi.service.TypeSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 项目控制器
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ProjectAdminer extends BaseController {
	
	@Autowired
	private ProjectSO so;
	
	@Autowired
	private TypeSO typeSO;
	
	@Autowired
	private WishSO wishSO;
	
	/**
	 * 进入项目管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/project/{projectchannel:\\d+}"}, method = RequestMethod.GET)
	public String team(Map<String, Object> model, @PathVariable String projectchannel) {
		model.put(ViewKeyDict.type, typeSO.getListByType(EType.project));
		model.put(ViewKeyDict.projectchannel, EProjectChannel.get(projectchannel));
		model.put(ViewKeyDict.projectchannelname, EProjectChannel.getName(EProjectChannel.get(projectchannel), Locale.CHINA));
		model.put(ViewKeyDict.channel, EChannel.project.name());
		model.put(ViewKeyDict.map, ProjectHP.getEnums());
		return "doproject";
	}
	
	/**
	 * 查询时组织数据
	 * @param model
	 * @param request
	 * @param projectchannel
	 * @return
	 */
	@RequestMapping(value = {"ajax/project/list/{projectchannel:\\d+}"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> project_list(Map<String, Object> model, HttpServletRequest request, @PathVariable String projectchannel) {
		cloneParamsToModel(model, request);
		model.put(ViewKeyDict.channel, EProjectChannel.get(projectchannel));
		Map<String, Object> searchMap = ProjectHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加项目（本网推荐项目可以添加）
	 * @param project
	 * @param file
	 * @param mergerIndicators
	 * @param link
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> project_add(Project project, String file, @RequestParam MultipartFile reportpath, MergerIndicator mergerIndicators, Link report, String[] articles) {
		if (project != null && file != null && mergerIndicators != null && !reportpath.isEmpty()) {
			String path = FileUtil.uploadFile(reportpath, SysConf.TpiProjectReportPath);
			report.setPath(path);
			String[] imgs = file.split(BaseStrDict.comma);
			project.setImg(imgs[0]);
			project.setReport(report);
			project.setMergerindicator(mergerIndicators);
			project.setRelatedarticles(ProjectHP.getArticles(articles));
			project.setChannel(EProjectChannel.supergenius);
			project.setStatus(EStatus.disable);
			project.setState(EProjectState.payed);
			project.setNumber(ProjectHP.getProJSN(project));
			if (so.add(project)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 删除项目
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_delete(String[] ids, HttpServletRequest request) {
		if (so.deleteByUids(ids)) {
			wishSO.deleteByTouid(ids); 
			SerialFile(request);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 编辑项目（超天才推荐）
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> project_edit(Project newproject, String file, @RequestParam MultipartFile reportpath, String[] editarticles, HttpServletRequest request){
		Project pro = so.get(newproject.getUid());
		if (file != null) {
			String[] imgs = file.split(BaseStrDict.comma);
			newproject.setImg(imgs[0]);
		}else {
			newproject.setImg(pro.getImg());
		}
		if (reportpath.isEmpty()) {
			newproject.getReport().setPath(pro.getReport().getPath());
		}else {
			FileUtil.delete(pro.getReport().getPath());
			String path = FileUtil.uploadFile(reportpath, SysConf.TpiProjectReportPath);
			newproject.getReport().setPath(path);
		}
		newproject.setRelatedarticles(ProjectHP.getArticles(editarticles));
		newproject.set(pro);
		if (so.update(newproject)) {
			SerialFile(request);
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 特批免费（推荐机构推荐项目）
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/specialaudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> project_specialaudit(String[] ids, HttpServletRequest request) {
		if (so.update(EProjectState.payed, ids)) {
			SerialFile(request);
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 修改团队状态（发布和取消发布）
	 * TODO 操作密码暂时没有用到
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> team_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd, HttpServletRequest request) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				SerialFile(request);
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置置顶
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/project/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_top(String[] ids) {
		if (so.setTop(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消置顶
	 * @param ids
	 * @return 
	 */
	@RequestMapping(value = "/ajax/project/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_untop(String[] ids) {
		if (so.setTop(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置为推荐
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/isrecommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_isrecommend(String[] ids) {
		if (so.setRecommend(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消推荐
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/unrecommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_unrecommend(String[] ids) {
		if (so.setRecommend(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置为公开
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/ispublic", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_ispublic(String[] ids) {
		if (so.setPublic(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消公开
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/unpublic", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_unpublic(String[] ids) {
		if (so.setPublic(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置为魂牵梦绕
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/ischerished", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_ischerished(String[] ids) {
		if (so.setCherished(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消魂牵梦绕
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/project/uncherished", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> project_uncherished(String[] ids) {
		if (so.setCherished(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 生成模板
	 * @param request
	 * @author liuwenhao
	 */
	private void SerialFile(HttpServletRequest request) {
		ProjectHP.SerialFile(SysConf.FileProjectPath, WebConf.OfficialIndexProjectSize,  SysConf.HtmlProjectData, request);
		ProjectHP.SerialFile(SysConf.MobileFileProjectPath, WebConf.MobileOfficialIndexProjectSize,  SysConf.MobileHtmlProjectData, request);
	}
	
}
