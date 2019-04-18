package com.supergenius.web.front.life.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.enums.EContent;

/**
 * 会员通道HP
 * 
 * @author ChenQi
 * @date 2017年9月12日16:03:16
 */
public class MemberHP extends BaseHP {

	/**
	 * 从数据库获取首页内容(HTML)，会解析freemarker标签
	 * 
	 * @param type
	 * @param request
	 * @param model 需要从Controller传入到页面的参数
	 * @return
	 */
	public static String getContent(HttpServletRequest request, Map<String, Object> model, Long cid) {
		Content content = ContentHP.getOneContent(EContent.html, cid);
		String code = content.getContent();
		Map<String, Object> map = new HashMap<String, Object>();  
		String basePath = request.getContextPath();
		map.putAll(BaseWebConf.getBasePath(basePath));
		if (model != null && model.size() != 0) {
			map.putAll(model);
		}
		if (null != code) {
			return FreemarkerUtil.processStr(code, map);
		}
		return null;
	}

}
