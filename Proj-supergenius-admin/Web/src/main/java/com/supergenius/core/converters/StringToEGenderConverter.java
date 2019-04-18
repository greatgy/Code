package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.user.enums.EGender;

/**
 * 配置spring mvc自动接收EGender
 * 
 * @author chenminchang
 * @createtime 2016年12月2日下午6:01:00
 */
public class StringToEGenderConverter implements Converter<String, EGender> {

	@Override
	public EGender convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EGender.get(Integer.parseInt(source));
	}
}
