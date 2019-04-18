package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.tpi.enums.EMergeNewsType;

/**
 * 配置spring mvc自动接收EArticleChannel
 * @author Liuxiaoke
 */
public class StringToEMergeNewsTypeConverter implements Converter<String, EMergeNewsType> {
	
	@Override
	public EMergeNewsType convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EMergeNewsType.get(Integer.parseInt(source));
	}
	
}
