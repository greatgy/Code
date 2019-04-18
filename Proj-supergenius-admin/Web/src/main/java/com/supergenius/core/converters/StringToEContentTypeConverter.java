package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.official.enums.EType;

/**
 * 配置spring mvc自动接收EContentType
 * 
 * @author ShangJianguo
 */
public class StringToEContentTypeConverter implements Converter<String, EType> {

	@Override
	public EType convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EType.get(Integer.parseInt(source));
	}
	
}
