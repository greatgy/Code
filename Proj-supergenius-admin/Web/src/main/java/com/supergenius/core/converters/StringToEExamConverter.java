package com.supergenius.core.converters;

import org.springframework.core.convert.converter.Converter;

import com.supergenius.xo.moral.enums.EExam;

/**
 * 配置spring mvc自动接收EExam
 * 
 * @author LiJiacheng
 */
public class StringToEExamConverter implements Converter<String, EExam> {

	@Override
	public EExam convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return EExam.get(Integer.parseInt(source));
	}

}
