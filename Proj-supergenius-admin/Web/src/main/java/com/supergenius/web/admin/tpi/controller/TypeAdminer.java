package com.supergenius.web.admin.tpi.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.TypeHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.service.TypeSO;

/**
 * 团队类别
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class TypeAdminer extends BaseController{
	
	@Autowired
	TypeSO so;
	
	/**
	 * 进入团队管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/type"}, method = RequestMethod.GET)
	public String type(Map<String, Object> model) {
		model.put(ViewKeyDict.enumstype, TypeHP.getETypeMap());
		model.put(ViewKeyDict.channel, EChannel.type.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.type, Locale.CHINA));
		return "dotype";
	}
	
	/**
	 * 得到teamtype列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"ajax/type/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> type_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = TypeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加分类
	 * @return
	 */
	@RequestMapping(value = "/ajax/type/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> type_add(Type type, String file) {
		if (file != null && type != null) {
			String[] imgs = file.split(BaseStrDict.comma);
			type.setImg(imgs[3]);
			type.setAdminuid(AdminHP.getAdminUid());
			if (so.add(type)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}
	
	
	/**
	 * 编辑type
	 * @param edittype
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/type/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> type_edit(Type edittype, String file){
		Type type = so.get(edittype.getUid());
		if (file != null) {
			String[] imgs = file.split(BaseStrDict.comma);
			edittype.setImg(imgs[0]);
		}else {
			edittype.setImg(type.getImg());
		}
		edittype.setAdminuid(AdminHP.getAdminUid());
		if (so.update(edittype)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 去掉删除，不能删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@Deprecated
	@RequestMapping(value = "/ajax/type/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> type_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

}
