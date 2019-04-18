package com.genius.core.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列化忽略标注，与@Json配合使用
 * 在类上使用时必须指定value，否则无效
 * @author architect.bian
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonIgnore {
	String[] value() default {};
	String[] strategy() default "default";
}