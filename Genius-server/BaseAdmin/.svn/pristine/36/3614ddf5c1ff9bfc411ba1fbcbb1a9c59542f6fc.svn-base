package com.genius.server.baseadmin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseMsgKeyDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminSO;

/**
 * @author architect.bian
 *
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class AdminpwdAdminer extends BaseController {

	@Autowired
	private AdminSO so;
	
	@RequestMapping(value = "/adminpwd", method = RequestMethod.GET)
	public String adminpwd(Map<String, Object> model, HttpServletRequest request) {
		return "doadminpwd";
	}
	
	@RequestMapping(value = "/adminpwd", method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public String adminpwd(Map<String, Object> model, String pwd, String pwd2, HttpServletRequest request) {
		Admin admin = so.getByAdminid(AdminHP.getAdminid());
		Map<String, String> errs = new HashMap<>();
		Map<String, String> msgs = new HashMap<>();
		if (StrUtil.isNotEmpty(pwd) && pwd.equals(pwd2)) {
			so.updatePwd(admin, pwd);
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		} else {
			errs.put(BaseMsgKeyDict.pwd2IsNotMatched, "");
		}
		model.put(BaseViewKeyDict.err, errs);
		model.put(BaseViewKeyDict.msg, msgs);
		return "doadminpwd";
	}
	
}
