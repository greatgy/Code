package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.genius.model.base.enums.EStatus;

/**
 * 配置spring mvc自动接收EStatus
 * @author XieMing
 * @date 2016-11-1 下午2:15:56
 */
public class StringToEStatusConverter implements Converter<String, EStatus> {

	@Override
	public EStatus convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EStatus.get(Integer.parseInt(source));
	}
}
