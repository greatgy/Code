package com.supergenius.web.front.life.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Complaint;
import com.supergenius.xo.life.enums.EComplaint;
import com.supergenius.xo.life.service.ComplaintSO;

/**
 * 举报
 * 
 * @author yangguang
 */
@Controller
public class ReportController extends BaseController {

	@Autowired
	ComplaintSO so;

	/**
	 * 打开举报页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/life/my/report" }, method = RequestMethod.GET)
	public String report(Map<String, Object> model, Long cid , String fromuid, String title, String url, HttpServletRequest request) {
		model.put(ViewKeyDict.fromuid, fromuid);
		model.put(ViewKeyDict.title, title);
		model.put(ViewKeyDict.url, url);
		model.put(ViewKeyDict.cid, cid);
		Catalogue catalogue = IndexHP.getOneCatalogue(cid);
		long pcid = catalogue.getPcid();
		model.put(ViewKeyDict.pcid, pcid);
		return "report";
	}
	
	/**
	 * 提交举报
	 * 
	 * @param model
	 * @param request
	 * @param topic
	 * @return
	 */
	@RequestMapping(value = { "/life/my/report" }, method = RequestMethod.POST)
	public String report(Map<String, Object> model, Complaint complaint,Long cid , int reporttype, String contributeimg, HttpServletRequest request, HttpServletResponse response) {
			complaint.setFromuseruid(BaseUserHP.getCurrUser(request).getUid());
			complaint.setType(EComplaint.get(reporttype));
			complaint.setStatus(EStatus.wait);
			complaint.setCid(cid);
			Catalogue catalogue = IndexHP.getOneCatalogue(cid);
			complaint.setCataloguename(catalogue.getName());
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				complaint.setFile(imgdata[0]);
			}
			if (complaint.getRefurl().contains("article")) {
				complaint.setKind(0);
			} else if (complaint.getRefurl().contains("video")) {
				complaint.setKind(1);
			} else if (complaint.getRefurl().contains("topic")) {
				complaint.setKind(2);
			} else if (complaint.getRefurl().contains("problem")) {
				complaint.setKind(3);
			}
			if (so.add(complaint)) {
				return "reportsuccess";
			}
		return "";
	}
}
