package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.official.enums.ERecruit;

/**
 * 配置spring mvc自动接收ERecruit
 * 
 * @author LiJiacheng
 */
public class StringToERecruitConverter implements Converter<String, ERecruit> {

	@Override
	public ERecruit convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return ERecruit.get(Integer.parseInt(source));
	}

}
