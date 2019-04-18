package com.genius.core.base.utils;

import java.math.BigDecimal;

public class MathUtil extends BaseUtil {

	private static final int defaultScale = 10;
	
	/**
	 * 相加
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subtract(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}
	
	public static double muti(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 相除
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public static double div(double d1, double d2) {
		return div(d1, d2, defaultScale);
	}

	/**
	 * 相除
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	/**
	 * 截取小数N位
	 * @param money
	 * @param i
	 * @return 
	 */
	public static double scale(double val, int newScale) {
		BigDecimal b = new BigDecimal(val);
		return b.setScale(newScale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * @param money
	 * @param i
	 * @return 
	 */
	public static double scale_Halfup(double val, int newScale) {
		BigDecimal b = new BigDecimal(val);
		return b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
