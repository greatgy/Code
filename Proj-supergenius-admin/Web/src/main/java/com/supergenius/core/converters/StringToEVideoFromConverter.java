package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.manager.enums.EVideoFrom;

/**
 * 配置spring mvc自动接收EVideoFrom
 * @author XieMing
 * @date 2016-10-31 下午4:42:20
 */
public class StringToEVideoFromConverter implements Converter<String, EVideoFrom> {

	@Override
	public EVideoFrom convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EVideoFrom.get(Integer.parseInt(source));
	}
}
