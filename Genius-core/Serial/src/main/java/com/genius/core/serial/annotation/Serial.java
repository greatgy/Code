package com.genius.core.serial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列化标注，若使用自定义序列化，则在类名上加此标注，否则采用默认的序列化方案
 * @author architect.bian
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Serial {
	String[] value();
	String[] strategy() default "default";
	String key();
	String password();
}