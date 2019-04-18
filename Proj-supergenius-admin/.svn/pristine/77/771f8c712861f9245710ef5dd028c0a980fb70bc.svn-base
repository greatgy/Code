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
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.service.TradeDetailSO;

/**
 * 管理交易明细HP
 * 
 * @author chenminchang
 * @date 2016-5-25 上午10:16:23
 */
public class TradeHP extends BaseHP {

	private static TradeDetailSO so;

	private static TradeDetailSO getSO() {
		if (so == null) {
			so = spring.getBean(TradeDetailSO.class);
		}
		return so;
	}

	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.site))) {
			map.put(MapperDict.site, model.get(ViewKeyDict.site).toString());
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
		List<TradeDetail> list = getSO().getList(map);
		for (TradeDetail trade : list) {
			trade.setUser(BaseUserHP.getFromRedis(trade.getUseruid()));
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
	 */
	public static void exportexcel(String[] sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		List<TradeDetail> list = getSO().getList(map);
		for (TradeDetail trade : list) {
			trade.setUser(BaseUserHP.getFromRedis(trade.getUseruid()));
		}
		for (int i = 0; i < list.size(); i ++) {
			int nclo = colNum;
			row = sheet.createRow(i);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(i + 1);

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getSn());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUserName());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUsersn());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getEmail());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getSiteName());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getTypeName());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getMoney());

			cellScore = row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getAccountcurr());

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
