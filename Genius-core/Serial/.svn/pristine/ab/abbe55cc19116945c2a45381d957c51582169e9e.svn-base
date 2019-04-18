package com.genius.core.serial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列化忽略标注，与@serialIgnore配合使用
 * @author architect.bian
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialIgnore {
	String[] value();
	String[] strategy() default "default";
}
