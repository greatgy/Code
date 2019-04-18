package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.official.enums.EBanner;

/**
 * 配置spring mvc自动接收EBanner
 * @author liushaomin
 */
public class StringToEBannerConverter implements Converter<String, EBanner> {

	@Override
	public EBanner convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EBanner.get(Integer.parseInt(source));
	}
	
}
