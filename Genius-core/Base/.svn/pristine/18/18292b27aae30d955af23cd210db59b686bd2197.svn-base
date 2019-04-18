package com.genius.core.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Map转化标注，若使用自定义转化，则在类名上加此标注，否则采用默认的转化方案
 * 若指定了strategy，只写了"forsave"的strategy，则对默认的strategy不起作用
 * 1.类上标有@Maps，方法上没有，则转化所有方法
 * 2.类上标有@Maps(strategy="forsave")，方法上没有，则所有方法是针对strategy="forsave"的方案
 * 3.类上标有@Maps("prop1","prop2")，字段上没有，则仅转化"prop1","prop2"
 * 4.若类上标有@Maps(strategy="forsave", "prop1","prop2")，则strategy为forsave时转化"prop1","prop2"，否则不转化
 * 5.类上标有@Maps(includeTypeStrategy = "forsave")，则strategy为forsave时转化的结果包含CLASS属性以记录当前对象的类型，否则不记录
 * 6.方法上标有@Maps(strategy="forsave", key="getStatusName")，则strategy为forsave时会调用方法转化为别名"statusName"，否则不转化，目前反转化时还不去调用方法
 * 7.
 * @author architect.bian
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Maps {
	
	public static final String defaultStrategy = "default";
	public static final String allStrategy = "*";//所有的strategy，仅可用在字段上
//	public static final String webStrategy = "web";
	public static final String dbStrategy = "db";
	public static final String cacheStrategy = "cache";
	public static final String searchStrategy = "search"; //搜索构建索引时用
//	public static final String keyValueStrategy = "kv";//用在下拉框等数据
	
	String[] value() default {};// 用于类、方法上，缩写形式，如name、pwd等等
	String[] strategy() default defaultStrategy;
	String alias() default "";//别名
//	String password() default "";
	String[] includeTypeStrategy() default {};
	boolean isRaw() default false;//仅作用于某个字段或方法上，是否保留原来的类型及值，还是做类型转化及值处理
}