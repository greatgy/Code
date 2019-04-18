package com.genius.core.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Lists;

/**
 * @author architect.bian
 * 
 */
public class ReflectUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(ReflectUtil.class);
	
	public static <T> T newInstance(Class<T> valueType) throws InstantiationException, IllegalAccessException {
		return valueType.newInstance();
	}

	public static Method getMethod(Class<?> clz, String name, Class<?>... parameterTypes) {
		try {
			return clz.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			Method[] methods = clz.getMethods();
			for (Method m : methods) {
				if (m.getName().equals(name)) {
					return m;
				}
			}
		} catch (SecurityException e) {
			logException(log, e);
		}
		return null;
	}

	/**
	 * 设置字段的值
	 * 
	 * @param obj
	 * @param field
	 * @param object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void set(Object obj, Field field, Object value) throws IllegalArgumentException, IllegalAccessException {
		ReflectionUtils.makeAccessible(field);
		field.set(obj, value);
	}

	/**
	 * 返回某个字段的值
	 * 
	 * @param obj
	 * @param field
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Object get(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException {
		ReflectionUtils.makeAccessible(field);
		return field.get(obj);
	}

	/**
	 * 调用方法
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午2:53:12
	 */
	public static Object invoke(Object obj, Method method, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(obj, args);
	}

	/**
	 * 在一个Object对象基础上创建另外一个实例，即复制对象的值到另外一个对象
	 * 
	 * @param obj
	 * @param type
	 * @return
	 * @author: Architect.bian 2014-6-14 下午2:01:00
	 */
	public static <T> T convert(Object obj, Class<T> type) {
		return JsonUtil.fromJson(JsonUtil.toJson(obj), type);
	}

	/**
	 * 判断一个类是否是java自身的类
	 * 
	 * @param clz
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午9:09:12
	 */
	public static boolean isJavaClass(Class<?> clz) {
		return clz != null && clz.getClassLoader() == null;
	}

	/**
	 * 判断对象是否是map类型
	 * @param object
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午9:16:28
	 */
	public static boolean isMap(Object object) {
		return Lists.newArrayList(object.getClass().getInterfaces()).contains(Map.class);
	}
	
	/**
	 * 判断对象是否是list类型
	 * @param object
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午9:16:39
	 */
	public static boolean isList(Object object) {
		return Lists.newArrayList(object.getClass().getInterfaces()).contains(List.class);
	}
	
	public static boolean isArry(Object object) {
		return Lists.newArrayList(object.getClass().getInterfaces()).contains(List.class);
	}

	public static boolean isArray(Object obj) {
		if (obj != null) {
			return obj.getClass().isArray();
		}
		return false;
	}

}