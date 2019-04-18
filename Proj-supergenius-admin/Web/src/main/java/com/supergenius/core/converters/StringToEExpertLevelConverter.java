package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.manager.enums.EExpertLevel;

/**
 * 配置spring mvc自动接收EExpertLeve
 * @author XieMing
 * @date 2016-11-3 下午1:53:17
 */
public class StringToEExpertLevelConverter implements Converter<String, EExpertLevel> {

	@Override
	public EExpertLevel convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EExpertLevel.get(Integer.parseInt(source));
	}
}
