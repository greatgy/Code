package com.supergenius.web.admin.user.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.service.OrderGoodsSO;

/**
 * 订单管理
 * 
 * @author XieMing
 * @date 2016-5-11 上午11:32:52
 */
public class OrderGoodsHP extends BaseHP {

	private static OrderGoodsSO so;

	private static OrderGoodsSO getSO() {
		if (so == null) {
			so = spring.getBean(OrderGoodsSO.class);
		}
		return so;
	}

	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 * @author XieMing 2016-5-11 上午11:34:49
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.sn))) {
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.sn).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.totalpricestart))) {
			map.put(MapperDict.totalprice + MapperDict.suffix_greater_key, Double.parseDouble(model.get(ViewKeyDict.totalpricestart).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.totalpriceend))) {
			map.put(MapperDict.totalprice + MapperDict.suffix_less_key, Double.parseDouble(model.get(ViewKeyDict.totalpriceend).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.useruids))) {
			map.put(MapperDict.useruids, ((String) model.get(ViewKeyDict.useruids)).split(","));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<OrderGoods> list = getSO().getList(map);
		for (OrderGoods orderGoods : list) {
			orderGoods.setUser(BaseUserHP.getFromRedis(orderGoods.getUseruid()));
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * excel导出
	 * 
	 * @param sns
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author XieMing 2016-5-12 下午3:09:14
	 */
	public static void exportExcel(String[] sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = wb.createSheet(SysConf.ExcelName);
		// 获取单元格样式对象
		HSSFCellStyle style = wb.createCellStyle();
		// 设置居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置单元格内容纵向居中
		// 开始填充数据
		HSSFRow row = null;
		HSSFCell cellScore = null;
		short colNum = 1;// 列起始
		Map<String, Object> map = new HashMap<String, Object>();
		String snsString = "";
		for (int i = 1; i < sns.length; i++) {
			snsString += "'" + sns[i] + "'" + ",";
		}
		snsString = snsString.substring(0, snsString.length() - 1);
		map.put(MapperDict.sns, snsString);
		List<OrderGoods> list = getSO().getList(map);
		for (OrderGoods orderGoods : list) {
			orderGoods.setUser(BaseUserHP.getFromRedis(orderGoods.getUseruid()));
		}
		for (int i = 0; i < list.size(); i ++) {
			int nclo = colNum;
			row = sheet.createRow(i);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(i + 1);

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getSn());

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getName());

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUser().getName());

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getType().getName());

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getTotalprice());

			cellScore = row.createCell(nclo ++);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getCreatetimeStr());
		}
		String fileName = SysConf.ExcelName;
		String myExcel = SysConf.BaseDataPath + SysConf.ExcelPath;
		String filePath = myExcel + fileName;
		FileOutputStream out = new FileOutputStream(filePath);// 创建xls文件
		wb.write(out);
		out.close();
		File file = new File(filePath);
		WebUtil.download(response, file, fileName);
	}
}
