package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.ECurrency;

/**
 * 配置spring mvc自动接收ECurrency
 * 
 * @author ShangJianguo
 */
public class StringToECurrencyConverter implements Converter<String, ECurrency> {

	@Override
	public ECurrency convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return ECurrency.get(Integer.parseInt(source));
	}
}
