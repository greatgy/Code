package com.genius.core.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列化标注，若使用自定义序列化，则在类名上加此标注，否则采用默认的序列化方案
 * 若指定了strategy，只写了"forsave"的strategy，则对默认的strategy不起作用
 * 1.类上标有@Json，字段上没有，则序列化所有字段
 * 2.类上标有@Json(strategy="forsave")，字段上没有，则所有字段是针对strategy="forsave"的方案
 * 3.类上标有@Json("field1","field2")，字段上没有，则仅序列化"field1","field2"
 * 4.若类上标有@Json(strategy="forsave", "field1","field2")，则strategy为forsave时序列化"field1","field2"，否则不序列化
 * 5.类上标有@Json(ignoreTypeStrategy = "forsave")，则strategy为forsave时序列化的结果包含CLASS属性不记录当前对象的类型，否则记录
 * 6.方法上标有@Json(strategy="forsave", key="getStatusName")，则strategy为forsave时会调用方法序列化为别名"statusName"，否则不序列化，目前反序列化时还不去调用方法
 * @author architect.bian
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Json {
	
	public static final String defaultStrategy = "default";
	public static final String allStrategy = "*";//所有的strategy，仅可用在字段上
	public static final String webStrategy = "web";
	public static final String dataStrategy = "data";
	public static final String cacheStrategy = "cache";
	public static final String keyValueStrategy = "kv";//用在下拉框等数据
	public static final String appStrategy = "app";//用在移动端
	
	String[] value() default {};//用于类上
	String[] strategy() default defaultStrategy;
	String alias() default "";//别名
//	String password() default "";
	String[] ignoreTypeStrategy() default {};
}