package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.user.enums.EContent;

/**
 * 将String类型转换为EContent类型
 * @author liubin
 * @date 2016-6-17 下午17:36:26
 */
public class StringToEContentConverter implements Converter<String, EContent> {

	@Override
	public EContent convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EContent.get(Integer.parseInt(source));
	}
}
