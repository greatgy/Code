package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.manager.enums.EMajor;

/**
 * 配置spring mvc自动接收EMajor
 * @author XieMing
 * @date 2016-10-31 下午4:40:27
 */
public class StringToEMajorConverter implements Converter<String, EMajor> {

	@Override
	public EMajor convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EMajor.get(Integer.parseInt(source));
	}
}
