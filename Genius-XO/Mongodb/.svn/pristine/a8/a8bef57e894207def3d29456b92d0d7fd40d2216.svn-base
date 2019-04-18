package com.genius.xo.mongodb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在类上标注对应的mongodb的collection及操作所对应的strategy
 * 若不在类上标注@mongo，则采用默认name及strategy
 * 若需要指定对应的mongodb的collection名称，则指定@mongo的collection
 * 若需要保存时采用某种strategy则指定@mongo的strategy
 * 
 * @author architect.bian
 * @createtime 2015-1-8 下午5:02:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mongo {
	
	public static final String defaultStrategy = "db";
//	public static final String dbStrategy = "db";
	
	/**
	 * 对应的mongodb的collection名称
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-8 下午5:09:48
	 */
	String collection() default "";
	/**
	 * 与MapsUtil对应的strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-1-8 下午5:09:01
	 */
//	String[] strategy() default defaultStrategy;
}
