package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.moral.enums.ECase;

/**
 * 配置spring mvc自动接收EType
 * 
 * @author ShangJianguo
 */
public class StringToECaseConverter implements Converter<String, ECase> {

	@Override
	public ECase convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return ECase.get(Integer.parseInt(source));
	}
	
}
