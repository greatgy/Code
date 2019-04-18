package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.EMoneyUnit;

/**
 * 配置spring mvc自动接收EMoneyUnit
 * 
 * @author ShangJianguo
 */
public class StringToEMoneyUnitConverter implements Converter<String, EMoneyUnit> {

	@Override
	public EMoneyUnit convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EMoneyUnit.get(Integer.parseInt(source));
	}
}
