package com.supergenius.web.admin.account.helper;

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
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.common.constants.MapperDict;
/**
 * 充值管理
 * @author YuYingJie
 */
public class RechargeHP extends BaseHP {

	private static AccountSO so;
	
	private static AccountSO getSO(){
		if (so == null) {
			so = (AccountSO)spring.getBean(AccountSO.class);
		}
		return so;
	}
	
	/**
	 * 查询组织语句
	 * @param model
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.username))) {
			map.put(MapperDict.username, model.get(ViewKeyDict.username).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.accountsn))) {
			map.put(MapperDict.accountsn, model.get(ViewKeyDict.accountsn).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.bank))) {
			map.put(MapperDict.bank, model.get(ViewKeyDict.bank).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.site))) {
			map.put(MapperDict.site, model.get(ViewKeyDict.site).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * excel导出
	 * @param sns
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author YuYingJie
	 */
	public static void exportexcel(String []sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("resource")
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet=wb.createSheet(SysConf.ExcelName);
		//获取单元格样式对象
		HSSFCellStyle style=wb.createCellStyle();
		//设置居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置单元格内容纵向居中
		// 开始填充数据
		HSSFRow row = null;
		HSSFCell cellScore = null;
		short colNum = 1;// 列起始
		List<Account> list = getSO().getList(sns);
		for (int i = 0; i < list.size(); i++) {
			int nclo = colNum;
			row=sheet.createRow(i);
			HSSFCell cell=row.createCell(0);
			cell.setCellValue(i+1);
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getAccountsn());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUsername());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getTypeName());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getSiteName());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUseremail());
			
			cellScore=row.createCell(nclo++);    
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getMoney());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getBankName());
			
			cellScore=row.createCell(nclo++);
			cellScore.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellScore.setCellValue(list.get(i).getUpdatetimeStr());
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
