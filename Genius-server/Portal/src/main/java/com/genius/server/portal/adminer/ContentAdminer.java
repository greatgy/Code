package com.genius.server.portal.adminer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.constant.BaseMsgKeyDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.portal.entity.Content;
import com.genius.model.portal.enums.EContent;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.server.portal.conf.SysServerPortalConf;
import com.genius.xo.portal.service.ContentSO;

/**
 * 网站内容管理 包括 公司简介、关于我们、定制项目
 * @author liushaomin
 * 2014-6-11 下午2:19:44
 */
@Controller
public class ContentAdminer extends BaseController{
	
	@Autowired
	private ContentSO so;
	
//	@Autowired
//	private AdminSO adminSO;
	
	/**
	 * 进入公司简介、关于我们等内容的管理
	 * @param model
	 * @param type
	 * @return 
	 * @author liushaomin
	 * 2014-6-11 下午2:58:04
	 */
	@RequestMapping(value = "/content/{type:\\d+}", method = RequestMethod.GET)
	public String content(Map<String, Object> model, @PathVariable String type) {
		if (StrUtil.isNumeric(type)) {
			model.put(BaseViewKeyDict.bean, so.getOneByType(type));
			model.put(BaseViewKeyDict.title, EContent.getName(EContent.get(type), Locale.CHINA));
			model.put(BaseViewKeyDict.type, type);
		}
		return "docontent";
	}
	
	/**
	 * 提交编辑
	 * @param model
	 * @param type
	 * @param id
	 * @param title
	 * @param content
	 * @return 
	 * @author liushaomin
	 * 2014-6-11 下午3:42:24
	 */
	@RequestMapping(value = "/content/{type:\\d+}/edit", method = RequestMethod.POST)
	public String content_edit(Map<String, Object> model, @PathVariable String type, @RequestParam(required = false) MultipartFile file, int oid, String title, String content, String summary, HttpServletRequest request) {
		Map<String, String> errs = new HashMap<>();
		Map<String, String> msgs = new HashMap<>();
		Content newcontent= new Content(oid, title, content, summary);
		if (file.getSize() > 0) {
			String[] paths = FileUtil.resizeImage(file, SysServerPortalConf.imgDefaultPath_content, SysServerPortalConf.ImgDefaultSizes_content);
			newcontent.setImgs(paths);
		}
		newcontent.setAdminuid(AdminHP.getAdminUid());
		if (so.updateFields(newcontent)) {
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		} else {
			errs.put(BaseMsgKeyDict.isEmpty, "");
		}
		model.put(BaseViewKeyDict.err, errs);
		model.put(BaseViewKeyDict.msg, msgs);
		return content(model, type);
	}

}
