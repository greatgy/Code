package com.supergenius.web.front.user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.user.helper.IndexHP;
import com.supergenius.xo.user.entity.User;

/**
 * 我的超天才
 * @author diaobisong
 */
@Controller
public class MyHomeController extends BaseController {

	/**
	 * 网站根路径跳转主页
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping(value = { "/","/index"})
    public String index(Map<String, Object> model, HttpServletRequest request) {
    	return redirectPrefix + "/my/home"; 
    }
    
    /**
     * 主页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/my/home")
    @SuppressWarnings("unchecked")
    public String myHome(Map<String, Object> model, HttpServletRequest request) {
    	User user = BaseUserHP.getCurrUser(request);
    	String fullPath = SysConf.SerialBasePath + String.format(SysConf.IndexMaskOrderPath, user.getOid()) + SysConf.Separator_Directory + SysConf.MaskOrder;
		File file = new File(fullPath);
		if (file.exists()) {
			List<String> maskorder = (List<String>) SerialUtil.deserializeFromJson(fullPath, ArrayList.class);
	    	model.put(ViewKeyDict.maskorder, maskorder);
		}
    	if (user.getIsStudent()) {//是职业经理人学员
    		model.put(MsgKeyDict.is_student, true);
    	}
    	if (user.getIsStudentMoral()) {//是职业道德学员
    		model.put(MsgKeyDict.is_studentmoral, true);
    	}
    	if (user.getIsEnterpriser()) {//是企业家培训学员
    		model.put(MsgKeyDict.is_enterpriser, true);
    	}
    	model.put(ViewKeyDict.aiarticle, IndexHP.getContent(SysConf.FileAiRecentPath));
    	model.put(ViewKeyDict.lifearticle, IndexHP.getContent(SysConf.FileLifeRecentPath));
    	model.put(ViewKeyDict.financearticle, IndexHP.getContent(SysConf.FileFinanceRecentPath));
    	model.put(ViewKeyDict.startuparticle, IndexHP.getContent(SysConf.FileStartupRecentPath));
    	model.put(ViewKeyDict.entrepreneurarticle, IndexHP.getContent(SysConf.FileEntrepreneurRecentPath));
    	model.put(ViewKeyDict.managernewsarticle, IndexHP.getContent(SysConf.FileManagernewsRecentPath));
    	return "index"; 
    }
    
    /**
     * 保存index中的模块
     * @param model
     * @param request
     * @param maskorder
     * @return
     * @author chenminchang
     * @create 2016年12月13日上午10:59:17
     */
    @RequestMapping(value = {"/ajax/my/maskorder"}, method = RequestMethod.POST)
    @ResponseBody
    public String maskorder(Map<String, Object> model, HttpServletRequest request, String[] maskorder) {
    	User user  = BaseUserHP.getCurrUser(request);
    	if (StrUtil.isNotEmpty(maskorder)) {
    		if (SerialUtil.serializeToJson(maskorder, SysConf.SerialBasePath + String.format(SysConf.IndexMaskOrderPath, user.getOid()) + SysConf.Separator_Directory + SysConf.MaskOrder))
    			return MsgKeyDict.success;
    	}
    	return MsgKeyDict.failed;
    }
}
