package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.moral.enums.ECountDetail;

/**
 * 配置spring mvc自动接收ECountDetail
 * 
 * @author LiJiacheng
 */
public class StringToECountDetailConverter implements Converter<String, ECountDetail> {

	@Override
	public ECountDetail convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return ECountDetail.get(Integer.parseInt(source));
	}

}
