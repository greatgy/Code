package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.moral.enums.EQst;

/**
 * 配置spring mvc自动接收EChapter
 * @author liushaomin
 */
public class StringToEQstConverter implements Converter<String, EQst>{

	@Override
	public EQst convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EQst.get(Integer.parseInt(source));
	}
}
