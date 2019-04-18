package com.supergenius.web.admin.official.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.official.enums.EArticleType;
import com.supergenius.xo.official.service.ArticleSO;

/**
 * 新闻和官网文章HP
 * 
 * @author liushaomin
 * @modifier lijiacheng
 */
public class OfficialArticleHP extends BaseHP {

	private static ArticleSO so;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	/**
	 * 查询组织语句
	 * 
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model, MapperDict.title);
		String[] types = new String[] { EArticleType.video.toString(), EArticleType.mobilevideo.toString() };
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString().trim());
		} else {
			map.put(MapperDict.type + MapperDict.suffix_in_key, types);
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
	 * 处理上传的图片
	 * 
	 * @param fileimg
	 * @param string
	 * @param imgshowsizes
	 * @param imgUploadBasePath
	 * @author tf
	 * @return
	 */
	public static String resizeImage(MultipartFile fileimg, String path, int[][] imgsizes, String imgUploadBasePath) {
		String[] paths = FileUtil.resizeImage(fileimg, path, imgsizes, imgUploadBasePath);
		String data = null;
		if (path.length() != 0) {
			data = paths[0] + BaseStrDict.comma + paths[1] + BaseStrDict.comma + paths[2] + BaseStrDict.comma + paths[3];
		}
		return data;
	}
}
