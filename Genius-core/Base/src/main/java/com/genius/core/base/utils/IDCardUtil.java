package com.genius.core.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证工具类
 * @author ChenQi
 * @date 2017年12月1日14:54:46
 * 
 */
public class IDCardUtil extends BaseUtil {

	/** 
     * 省、直辖市代码表： 
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古   
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏   
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东   
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南   
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏   
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆   
     *     71 : 台湾   
     *     81 : 香港  82 : 澳门   
     *     91 : 国外 
     */
	private static String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
			"36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
			"71", "81", "82", "91" };

	public static Map<String, String> cityCodes = new HashMap<String, String>();

	static {
		cityCodes.put("11", "北京");
		cityCodes.put("12", "天津");
		cityCodes.put("13", "河北");
		cityCodes.put("14", "山西");
		cityCodes.put("15", "内蒙古");
		cityCodes.put("21", "辽宁");
		cityCodes.put("22", "吉林");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("31", "上海");
		cityCodes.put("32", "江苏");
		cityCodes.put("33", "浙江");
		cityCodes.put("34", "安徽");
		cityCodes.put("35", "福建");
		cityCodes.put("36", "江西");
		cityCodes.put("37", "山东");
		cityCodes.put("41", "河南");
		cityCodes.put("42", "湖北");
		cityCodes.put("43", "湖南");
		cityCodes.put("44", "广东");
		cityCodes.put("45", "广西");
		cityCodes.put("46", "海南");
		cityCodes.put("50", "重庆");
		cityCodes.put("51", "四川");
		cityCodes.put("52", "贵州");
		cityCodes.put("53", "云南");
		cityCodes.put("54", "西藏");
		cityCodes.put("61", "陕西");
		cityCodes.put("62", "甘肃");
		cityCodes.put("63", "青海");
		cityCodes.put("64", "宁夏");
		cityCodes.put("65", "新疆");
		cityCodes.put("71", "台湾");
		cityCodes.put("81", "香港");
		cityCodes.put("82", "澳门");
		cityCodes.put("91", "国外");
	}
	/**
	 * 每位加权因子
	 */
	private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/**
	 * 验证所有的身份证的合法性
	 * 
	 * @param idcard
	 *            身份证
	 * @return 合法返回true，否则返回false
	 */
	public static boolean isIdcard(String idcard) {
		if (idcard == null || "".equals(idcard)) {
			return false;
		}
		if (idcard.length() == 15) {
			System.out.println("请使用二代身份证");
			return false;
		}
		return validate18Idcard(idcard);
	}

	/**
	 * 验证身份证是否合法 
	 * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
	 * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
	 * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
	 * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean validate18Idcard(String idcard) {
		if (idcard == null) {
			return false;
		}
		if (idcard.length() != 18) {
			return false;
		}

		String idcard17 = idcard.substring(0, 17); // 前17位全部为数字
		if (!isDigital(idcard17)) {
			return false;
		}

		String provinceid = idcard.substring(0, 2); // 校验省份
		if (!checkProvinceid(provinceid)) {
			return false;
		}

		String birthday = idcard.substring(6, 14); // 校验出生日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date birthDate = sdf.parse(birthday);
			if (birthDate.getTime() > new Date().getTime()) {// 日期不存在
				return false;
			}
			String tmpDate = sdf.format(birthDate);
			if (!tmpDate.equals(birthday)) {// 出生年月日不正确
				return false;
			}
		} catch (ParseException e1) {
			return false;
		}

		String idcard18Code = idcard.substring(17, 18);
		char c[] = idcard17.toCharArray();
		int bit[] = converCharToInt(c);
		int sum17 = 0;
		sum17 = getPowerSum(bit);
		String checkCode = getCheckCodeBySum(sum17); // 将和值与11取模得到余数进行校验码判断
		if (null == checkCode) {
			return false;
		}
		if (!idcard18Code.equalsIgnoreCase(checkCode)) {
			return false;
		}

		return true;
	}

	/**
	 * 校验省份
	 * 
	 * @param provinceid
	 * @return 合法返回TRUE，否则返回FALSE
	 */
	private static boolean checkProvinceid(String provinceid) {
		for (String id : cityCode) {
			if (id.equals(provinceid)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验市区县（身份证号码3-6位）
	 * 
	 * @param 
	 * @return 合法返回TRUE，否则返回FALSE
	 */
	/*private static void checkCity() {
		//TODO 由于市区县代码过多，待定方案 资料地址：http://www.knowsky.com/888908.html
	}*/

	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isDigital(String str) {
		return str.matches("^[0-9]*$");
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param bit
	 * @return
	 */
	private static int getPowerSum(int[] bit) {
		int sum = 0;
		if (power.length != bit.length) {
			return sum;
		}
		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}

	/**
	 * 将和值与11取模得到余数进行校验码判断
	 * 
	 * @param checkCode
	 * @param sum17
	 * @return 校验位
	 */
	private static String getCheckCodeBySum(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
		case 10:
			checkCode = "2";
			break;
		case 9:
			checkCode = "3";
			break;
		case 8:
			checkCode = "4";
			break;
		case 7:
			checkCode = "5";
			break;
		case 6:
			checkCode = "6";
			break;
		case 5:
			checkCode = "7";
			break;
		case 4:
			checkCode = "8";
			break;
		case 3:
			checkCode = "9";
			break;
		case 2:
			checkCode = "x";
			break;
		case 1:
			checkCode = "0";
			break;
		case 0:
			checkCode = "1";
			break;
		}
		return checkCode;
	}

	/**
	 * 将字符数组转为整型数组
	 * 
	 * @param c
	 * @return
	 * @throws NumberFormatException
	 */
	private static int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}

	/**
	 * 根据身份编号获取年龄
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 年龄(返回0为身份证不合法)
	 */
	public static int getAgeByIdCard(String idCard) {
		int iAge = 0;
		if (!isIdcard(idCard)) {
			return iAge;
		}
		String year = idCard.substring(6, 10);
		Calendar cal = Calendar.getInstance();
		int iCurrYear = cal.get(Calendar.YEAR);
		iAge = iCurrYear - Integer.valueOf(year);
		return iAge;
	}

	/**
	 * 根据身份编号获取生日
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyyMMdd) (返回null为身份证不合法)
	 */
	public static String getBirthByIdCard(String idCard) {
		if (!isIdcard(idCard)) {
			return null;
		}
		return idCard.substring(6, 14);
	}

	/**
	 * 根据身份编号获取性别
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 性别(1 : 男，0 : 女，-1 : 未知)
	 */
	public static int getGenderByIdCard(String idCard) {
		int sGender = -1;
		if (!isIdcard(idCard)) {
			return sGender;
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = 1;
		} else {
			sGender = 0;
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取户籍省份
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 省级编码。
	 */
	public static String getProvinceByIdCard(String idCard) {
		String sProvince = null;
		String sProvinNum = "";
		if (isIdcard(idCard)) {
			sProvinNum = idCard.substring(0, 2);
		}
		sProvince = cityCodes.get(sProvinNum);
		return sProvince;
	}
}