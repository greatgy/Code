package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.ESysEmailType;

/**
 * 配置spring mvc自动接收ESysEmailType
 * 
 * @author LiuXiaoke
 */
public class StringToESysEmailTypeConverter implements Converter<String, ESysEmailType> {

	@Override
	public ESysEmailType convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return ESysEmailType.get(Integer.parseInt(source));
	}
}
