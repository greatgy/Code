package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.moral.enums.EChapter;

/**
 * 配置spring mvc自动接收EChapter
 * @author liushaomin
 */
public class StringToEChapterConverter implements Converter<String, EChapter>{

	@Override
	public EChapter convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EChapter.get(Integer.parseInt(source));
	}
}
