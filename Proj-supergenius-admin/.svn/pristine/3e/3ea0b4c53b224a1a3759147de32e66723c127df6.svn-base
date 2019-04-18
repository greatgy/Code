package com.supergenius.web.admin.tpi.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseProjectHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.service.ProjectSO;
import com.supergenius.xo.tpi.service.TypeSO;

/**
 * 项目HP
 * @author liushaomin
 * @motiyfier liuwenhao 
 */
public class ProjectHP extends BaseProjectHP{
	
	private static ProjectSO so;
	
	private static TypeSO typeSO;
	
	private static ProjectSO getSO() {
		if (so == null) {
			so = (ProjectSO) spring.getBean(ProjectSO.class);
		}
		return so;
	}
	private static TypeSO getTypeSO() {
		if (typeSO == null) {
			typeSO = (TypeSO) spring.getBean(TypeSO.class);
		}
		return typeSO;
	}
	
	/**
	 * 组织查询数据
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.name, MapperDict.number);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, model.get(ViewKeyDict.channel).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, Boolean.parseBoolean(model.get(ViewKeyDict.istop).toString().trim()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.ischerished))) {
			map.put(MapperDict.ischerished, Boolean.parseBoolean(model.get(ViewKeyDict.ischerished).toString().trim()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.ispublic))) {
			map.put(MapperDict.ispublic, Boolean.parseBoolean(model.get(ViewKeyDict.ispublic).toString().trim()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, Boolean.parseBoolean(model.get(ViewKeyDict.isrecommend).toString().trim()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.state))) {
			map.put(MapperDict.state, model.get(ViewKeyDict.state).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString() + MapperDict.starttimeformat;
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 生成模板hp
	 * @param projectpath 文件生成路径
	 * @param pagenum     文件生成显示的数量
	 * @param data        指明读取文件
	 * @param request
	 */
	public static void SerialFile(String projectpath, int pagenum, String data, HttpServletRequest request ) {
		try {
			FileUtil.delete(SysConf.FileSiteBasePath + projectpath);
			List<Project> projects = getSO().getList(new Pager(1, pagenum));
			for (Project item : projects) {
				item.setTypeName(getTypeSO().get(item.getTypeuid()).getName());
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(BaseWebConf.getBasePath(request.getContextPath()));
			if (projectpath == SysConf.FileProjectPath) {
				model.put(ViewKeyDict.list, projects);
			} else {
	            model.put(ViewKeyDict.bean,projects);
			}
			model.put(ViewKeyDict.baseTpiPath, WebConf.baseTpiPath);
			model.put(ViewKeyDict.baseOfficialImg, WebConf.baseOfficialImg);
			File file = new File(SysConf.FileSiteBasePath + projectpath);		
			FreemarkerUtil.process(SysConf.OfficialIndexTemplatePath, data, model, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
