package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.EMergeCaseType;

/**
 * 配置spring mvc自动接收EArticleChannel
 * @author Liuxiaoke
 */
public class StringToEMergeCaseTypeConverter implements Converter<String, EMergeCaseType> {
	
	@Override
	public EMergeCaseType convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EMergeCaseType.get(Integer.parseInt(source));
	}
	
}
