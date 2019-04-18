package com.genius.core.base.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.google.common.collect.Lists;

/**
 * Map Strategy Util for short, MapsUtil<br />
 * 使用某个策略，将某个pojo类转化成map，基于方法，不访问私有字段及属性
 * 可转化model类，支持model参数或返回值为list/map/set/string/enum/基本类型/jodatime，不支持set重载及数组Book[]/String[]
 * @author architect.bian
 * @createtime 2014-12-23 下午4:34:51
 */
public class MapsUtil extends BaseUtil {

	private static final String regexGetMethodName = "^(get|is)";// 判断是否是get方法
	private static final String regexSetMethodName = "^(set)";// 判断是否是set方法
	private static final String regexFromMethodNameToMapKey = "^(get|is|set)";// 将方法名转成键

	private static final String CLASS = "CLASS";

	private static Logger log = LoggerFactory.getLogger(MapsUtil.class);

	private static Map<String, List<String>> cacheMethodNames = new HashMap<>();

	private enum MethodType {
		All, Get, Set;
	}

	/**
	 * 将obj转化为Map类型
	 * 
	 * @param obj
	 * @return Map<String, Obj>
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午3:56:21
	 */
	public static Map<String, Object> toMap(Object obj) {
		return toMap(obj, Maps.defaultStrategy);
	}

	/**
	 * 按某个strategy转化obj为Map
	 * 
	 * @param obj
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午3:56:54
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object obj, String strategy) {
		if (strategy == null || strategy.length() == 0) {
			strategy = Maps.defaultStrategy;
		}
		try {
			Object map = convertToMap(obj, strategy);
//			System.out.println(map);
			return (Map<String, Object>) map;
		} catch (Exception e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 将map转换成Type类型
	 * 
	 * @param map
	 *            必须是Map<String, Object>
	 * @param type
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午8:20:48
	 */
	public static <T> T fromMap(Map<String, Object> map, Class<T> type) {
		return fromMap(map, type, Maps.defaultStrategy);
	}

	/**
	 * 根据某个strategy将map转换成Type类型
	 * 
	 * @param map
	 * @param type
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-23 下午4:01:45
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromMap(Map<String, Object> map, Class<T> type, String strategy) {
		if (map == null || ReflectUtil.isJavaClass(type)) {
			return null;
		}
		if (map.size() == 0) {
			try {
				return type.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				logException(log, e);
				return null;
			}
		}
		return (T) convertToObject(map, type, strategy);
	}

	/**
	 * 返回最终要转化的对象，有@Maps标注的会返回Map，否则会直接返回对象
	 * 
	 * @param obj
	 * @return
	 */
	private static Object convertToMap(Object obj, String strategy) {
		if (obj == null) {
			return null;
		}
		if (strategy == null) {
			strategy = Maps.defaultStrategy;
		}
		String cacheKey = getCacheKey(obj.getClass(), strategy, MethodType.Get);
		List<String> finalMethods;
		if (cacheMethodNames.containsKey(cacheKey)) {// 已缓存
			finalMethods = cacheMethodNames.get(cacheKey);// 设置finalFields的值
		} else {
			finalMethods = getFinalMethodNames(obj.getClass(), strategy, MethodType.Get);
			cacheMethodNames.put(cacheKey, finalMethods);// 缓存
		}
		if (finalMethods == null || finalMethods.size() == 0) {// 根对象obj的类及字段上都没有标记@Json注解
			if (Lists.newArrayList(obj.getClass().getInterfaces()).contains(List.class)) {// 根对象obj是List类型
				return buildToListMap(obj, strategy);
			} else if (Lists.newArrayList(obj.getClass().getInterfaces()).contains(Set.class)) {// 根对象obj是Set类型
				return buildToSetMap(obj, strategy);
			} else if (Lists.newArrayList(obj.getClass().getInterfaces()).contains(Map.class)) {// 根对象obj是Map类型
				return buildToMapMap(obj, strategy);
			} else {
				return obj;
			}
		} else {
			Map<String, Method> allMethods = new HashMap<>();
			processFinalMethodNames(allMethods, obj.getClass(), finalMethods, MethodType.Get);
			Map<String, Object> result = buildToMapFromObjByMethods(obj, allMethods, strategy);
			return result;
		}
	}

	/**
	 * 通过get方法，返回由对象构造的一个map对象
	 * 
	 * @param obj
	 * @param allMethods
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午3:56:48
	 */
	private static Map<String, Object> buildToMapFromObjByMethods(Object obj, Map<String, Method> allMethods, String strategy) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (allMethods.size() > 0) {// 若在类之上指定了Json的所有字段，则先输出这些字段
			try {
				for (String key : allMethods.keySet()) {
					Method m = allMethods.get(key);
					Object val = ReflectUtil.invoke(obj, m);
					if (val == null || isRaw(m)) {
						//keep the value, do nothing 不需要做转化，保持原来的类型及值
					} else {
						if (m.getReturnType().isEnum()) {// 枚举类型
							val = val.toString();
						} else if (m.getReturnType().equals(DateTime.class)) {// joda.DateTime
							val = ((DateTime) val).getMillis();
						} else if (m.equals(LocalDate.class)) {// joda.LocalDate
							val = ((LocalDate) val).toDateTimeAtStartOfDay().toDate().getTime();
						} else if (m.equals(LocalTime.class)) {// joda.LocalTime
							val = ((LocalTime) val).getMillisOfDay();
						} else if (!val.getClass().isPrimitive()) {// 非基本类型
							val = convertToMap(val, strategy);
						}
					}
					map.put(key, val);
				}
				if (isKeepType(obj, strategy)) {// 判断是否将CLASS同时转化
					map.put(CLASS, obj.getClass().getName());
				}
			} catch (IllegalAccessException e) {
				logException(log, e);
			} catch (IllegalArgumentException e) {
				logException(log, e);
			} catch (InvocationTargetException e) {
				logException(log, e);
			}
		}
		return map;
	}

	/**
	 * Obj是List类型的，返回经过处理的可转成Json的List类型
	 * 
	 * @param obj
	 * @return
	 * @author: Architect.bian 2014-6-14 下午10:35:23
	 */
	@SuppressWarnings("unchecked")
	private static List<Object> buildToListMap(Object obj, String strategy) {
		List<Object> list = new ArrayList<>((List<Object>) obj);
		int i = 0;
		while ((i + 1) <= list.size()) {
			list.set(i, convertToMap(list.get(i), strategy));
			i++;
		}
		return list;
	}

	/**
	 * Obj是List类型的，返回经过处理的可转成Json的List类型
	 * 
	 * @param obj
	 * @return
	 * @author: Architect.bian 2014-6-14 下午10:35:23
	 */
	@SuppressWarnings("unchecked")
	private static Set<Object> buildToSetMap(Object obj, String strategy) {
		Set<Object> set = new HashSet<>((Set<Object>) obj);
		for (Object item : set) {
			item = convertToMap(item, strategy);
		}
		return set;
	}

	/**
	 * Obj是Map类型的，返回values经过处理的可转成Json的Map类型
	 * 
	 * @param obj
	 * @param strategy
	 * @return
	 * @author: Architect.bian 2014-6-14 下午10:53:39
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> buildToMapMap(Object obj, String strategy) {
		Map<Object, Object> map = new HashMap<>((Map<Object, Object>) obj);
		Map<String, Object> result = new HashMap<>();
		for (Object key : map.keySet()) {
			result.put(String.valueOf(key), convertToMap(map.get(key), strategy));
		}
		return result;
	}

	/**
	 * 将Map类型转成type类型的实例
	 * @param obj
	 * @param type 只能是某个Class，不能是Collection<?>/Map<?,?>等
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午6:47:06
	 */
	@SuppressWarnings("unchecked")
	private static Object convertToObject(Object obj, Class<?> type, String strategy) {
		if (obj == null || type == null) {
			return null;
		}
		if (strategy == null || strategy.length() == 0) {
			strategy = Maps.defaultStrategy;
		}
		String cacheKey = getCacheKey(type, strategy, MethodType.Set);
		List<String> finalMethods;
		if (cacheMethodNames.containsKey(cacheKey)) {// 已缓存
			finalMethods = cacheMethodNames.get(cacheKey);// 设置finalFields的值
		} else {
			finalMethods = getFinalMethodNames(type, strategy, MethodType.Set);
			cacheMethodNames.put(cacheKey, finalMethods);// 缓存
		}
		
		if (finalMethods != null && finalMethods.size() > 0) {
			if (!ReflectUtil.isJavaClass(type) && obj instanceof Map) {
				Map<String, Method> allMethods = new HashMap<>();
				processFinalMethodNames(allMethods, type, finalMethods, MethodType.Set);
				return buildFromMapByMethods((Map<String, Object>)obj, type, allMethods, strategy);
			}
		}
		return obj;
	}
	
	/**
	 * 将集合转化成对应的集合对象，只用在转化一个set方法泛型参数时
	 * @param obj
	 * @param type
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午6:48:26
	 */
	@SuppressWarnings("unchecked")
	private static Object convertToObjsByParameterizeType(Object obj, ParameterizedType type, String strategy) {
		if (obj == null || type == null) {
			return null;
		}
		if (strategy == null || strategy.length() == 0) {
			strategy = Maps.defaultStrategy;
		}
		Type[] actualTypeArguments = type.getActualTypeArguments();
		Type rawType = type.getRawType();
		Class<?> actualType = null;
		if (rawType == List.class && actualTypeArguments.length == 1 && !(actualTypeArguments[0] instanceof ParameterizedType)) { // 嵌套map不处理
			actualType = (Class<?>) actualTypeArguments[0];
		} else if (rawType == Set.class && actualTypeArguments.length == 1 && !(actualTypeArguments[0] instanceof ParameterizedType)) {
			actualType = (Class<?>) actualTypeArguments[0];
		} else if (rawType == Map.class && actualTypeArguments.length == 2 && !(actualTypeArguments[1] instanceof ParameterizedType)) {
			actualType = (Class<?>) actualTypeArguments[1];
		}
		if (actualType != null) {
			if (obj instanceof List) {
				return buildFromListMap((List<Object>) obj, strategy, actualType);
			} else if (obj instanceof Set) {
				return buildFromSetMap((Set<Object>) obj, strategy, actualType);
			} else if (obj instanceof Map) {
				return buildFromMapMap((Map<String, Object>) obj, strategy, actualType);
			}
		}
		return obj;
	}

	/**
	 * 反转化时执行t对象上的set方法
	 * 
	 * @param map
	 * @param type
	 * @param methods
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:42:41
	 */
	private static <T> T buildFromMapByMethods(Map<String, Object> map, Class<T> type, Map<String, Method> methods, String strategy) {
		try {
			T t = ReflectUtil.newInstance(type);
			for (String key : methods.keySet()) {
				Method setMethod;
				try {
					setMethod = methods.get(key);
					Type[] params = setMethod.getGenericParameterTypes();
					if (params.length == 1) {
						
						Object val = map.get(key);
						if (val == null) {
							if (params[0] instanceof ParameterizedType) {
								// do nothing
							} else {
								Class<?> pclz = (Class<?>) params[0];
								if (pclz.isPrimitive()) {//基本类型直接跳过
									continue;
								}
							}
						} else  if (isRaw(setMethod)) {//保持原有类型及值，不做特殊转化处理
							// do nothing
						} else {
							if (params[0] instanceof ParameterizedType) {
								val = convertToObjsByParameterizeType(val, (ParameterizedType) params[0], strategy);//集合
							} else {
								Class<?> pclz = (Class<?>) params[0];
								if (pclz.isEnum()) {
									val = EnumUtil.newInstance(pclz, val);// 创建枚举类型
								} else if (pclz.equals(DateTime.class)) {
									val = new DateTime(val, DateTimeZone.forOffsetHours(8));
								} else if (pclz.equals(LocalDate.class)) {
									val = new LocalDate(val, DateTimeZone.forOffsetHours(8));
								} else if (pclz.equals(LocalTime.class)) {
									val = new LocalTime(val, DateTimeZone.forOffsetHours(8));
//							} else if (pclz.isArray()) {// wrong ...
								} else if (!val.getClass().isPrimitive() && !val.getClass().equals(String.class)) {// 非基本类型
									val = convertToObject(val, pclz, strategy);
								}
							}
						}
						ReflectUtil.invoke(t, setMethod, val);
					}
				} catch (IllegalAccessException e) {
					logException(log, e);
				} catch (IllegalArgumentException e) {
					logException(log, e);
				} catch (InvocationTargetException e) {
					logException(log, e);
				} catch (SecurityException e) {
					logException(log, e);
				}
			}
			return t;
		} catch (InstantiationException e) {
			logException(log, e);
		} catch (IllegalAccessException e) {
			logException(log, e);
		}
		return null;
	}

	/**
	 * 将List<Map>转换成真实的List<Obj>
	 * 
	 * @param map
	 * @param strategy
	 * @return
	 * @author: Architect.bian 2014-6-15 上午2:32:37
	 * @param actualType
	 */
	@SuppressWarnings("unchecked")
	private static Object buildFromListMap(List<Object> list, String strategy, Class<?> actualType) {
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj != null && obj instanceof Map) {// item值是Map类型
				Map<String, Object> m = (Map<String, Object>) obj;
				list.set(i, convertToObject(m, chooseActualType(m, actualType), strategy));
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private static Object buildFromSetMap(Set<Object> set, String strategy, Class<?> actualType) {
		for (Object obj : set) {
			if (obj != null && obj instanceof Map) {// item值是Map类型
				Map<String, Object> m = (Map<String, Object>) obj;
				obj = convertToObject(m, chooseActualType(m, actualType), strategy);
			}
		}
		return set;
	}

	/**
	 * 将Map<String,Map>转换成真实的Map<String,Obj>
	 * 
	 * @param map
	 * @param strategy
	 * @return
	 * @author: Architect.bian 2014-6-15 上午2:33:11
	 * @param actualType
	 */
	@SuppressWarnings("unchecked")
	private static Object buildFromMapMap(Map<String, Object> map, String strategy, Class<?> actualType) {
		for (String key : map.keySet()) {
			if (map.get(key) != null && map.get(key) instanceof Map) {// item值是Map类型
				Map<String, Object> m = (Map<String, Object>) map.get(key);
				map.put(key, convertToObject(m, chooseActualType(m, actualType), strategy));
			}
		}
		return map;
	}

	/**
	 * 通过allMethodNames重新生成所有需要转化的methods，放到methods中
	 * @param methods
	 * @param type
	 * @param allMethodNames
	 * @param methodType
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午4:07:49
	 */
	private static <T> void processFinalMethodNames(Map<String, Method> methods, Class<T> type, List<String> allMethodNames, MethodType methodType) {
		Method[] ms = type.getMethods();
		for (Method m : ms) {
			String key = null;
			boolean flag = false;
			if (methodType != null) {
				if (methodType == MethodType.Get) {
					flag = isGetMethod(m);
				} else if (methodType == MethodType.Set) {
					flag = isSetMethod(m);
				} else if (methodType == MethodType.All) {
					flag = true;
				}
			} else {
				flag = true;// 不传递methodtype参数默认所有方法
			}
			if (flag) {// 是正确的get/set方法
				key = getMapKeyFromMethodName(m.getName());
				if (allMethodNames.contains(key)) {// 判断字段名是否匹配
					flag = true;
				} else if (m.isAnnotationPresent(Maps.class)) {// 判断Map.alias是否匹配
					Maps maps = m.getAnnotation(Maps.class);
					if (allMethodNames.contains(maps.alias())) {// json.key是否存在于allFieldsKey中
						key = maps.alias();
						flag = true;
					} else {
						flag = false;
					}
				} else {
					flag = false;
				}
			}
			if (flag && !methods.containsKey(key)) {// 在allFieldsKey中存在key，且未在子类中添加，且不是final类型
				methods.put(key, m);// 放入methods中
			}
		}
		if (type.getSuperclass() != Object.class) {
			processFinalMethodNames(methods, type.getSuperclass(), allMethodNames, methodType);
		}
	}

	/**
	 * 获取最终转化的所有方法名
	 * 
	 * @param <T>
	 * @param obj
	 * @param strategy
	 * @param finalFields
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> List<String> getFinalMethodNames(Class<T> type, String strategy, MethodType... methodType) {
		boolean onClass = false;
		// boolean onField = false;
		boolean onMethod = false;
		Maps mapsOnClass = type.getAnnotation(Maps.class);
		MapsIgnore mapsIgnoreOnClass = type.getAnnotation(MapsIgnore.class);
		if (isMatch(type, strategy, mapsOnClass)) {// Class上有匹配strategy的maps
			onClass = true;
		} else {
			Method[] methods = type.getDeclaredMethods();// 获取本类所有方法
			for (Method m : methods) {
				Maps maps = m.getAnnotation(Maps.class);
				if (isMatch(m, strategy, maps)) {
					onMethod = true;
					break;
				}
			}
		}
		if (onClass || onMethod) {// 需要转化，class上标有@Maps或字段上标有@Maps或方法上标有@Maps
			List<String> includeMethods = new ArrayList<>();
			List<String> excludeMethods = new ArrayList<>();
			boolean hasValueMapsOnClass = true;// Class上的Json有value值
			// 开始处理类上的json与jsonignore注解
			if (onClass) {// 类上有Json标注,获取所列的
				if (mapsOnClass.value().length == 0) {
					hasValueMapsOnClass = false;// class上的Maps注解无值
				} else if (isMatch(type, strategy, mapsOnClass)) {// Maps.values有值并匹配strategy
					includeMethods = Lists.newArrayList(mapsOnClass.value());// 有值
					hasValueMapsOnClass = true;
				}
			}
			if (isMatch(type, strategy, mapsIgnoreOnClass)) {// jsonIgnore.values有值并匹配strategy
				excludeMethods = Lists.newArrayList(mapsIgnoreOnClass.value());
			}
			// 开始循环本类及父类所有的method，判断是否转化
			iterateAllDeclaredMethods(type, strategy, includeMethods, excludeMethods, onClass, hasValueMapsOnClass, methodType);
			return (List<String>) ListUtil.removeAll(includeMethods, excludeMethods);
		} else {
			return null;
		}
	}

	/** 
	 * 开始循环本类及父类所有的method，判断是否转化
	 * @param type
	 * @param strategy
	 * @param includeMethods
	 * @param excludeMethods
	 * @param onClass
	 * @param hasValueJsonOnClass
	 * @param methodType
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:44:41
	 */
	private static <T> void iterateAllDeclaredMethods(Class<T> type, String strategy, List<String> includeMethods, List<String> excludeMethods, boolean onClass, boolean hasValueJsonOnClass, MethodType... methodType) {
		Method[] methods = type.getDeclaredMethods();// 获取所有方法
		for (Method m : methods) {
			boolean flag = false;
			if (methodType.length == 1) {
				if (methodType[0] == MethodType.Get) {
					flag = isGetMethod(m);
				} else if (methodType[0] == MethodType.Set) {
					flag = isSetMethod(m);
				} else if (methodType[0] == MethodType.All) {
					flag = true;
				}
			} else {
				flag = true;//若不指定methodtype则包含所有方法
			}
			if (flag) {
				Maps maps = m.getAnnotation(Maps.class);
				MapsIgnore mapsIgnore = m.getAnnotation(MapsIgnore.class);
				String methodKey = getMapKeyFromMethodName(m.getName());
				if (StrUtil.isNotEmpty(methodKey)) {
					if (isMatch(m, strategy, mapsIgnore)) {// 若method上存在MapsIgnore，并匹配strategy则忽略Maps
						excludeMethods.add(methodKey);
					} else if (isMatch(m, strategy, maps)) {// 若没有MapsIgnore，有Json并且strategy匹配
						includeMethods.add(maps.alias().length() == 0 ? methodKey : maps.alias());
					} else if (!hasValueJsonOnClass && onClass) {
						includeMethods.add(methodKey);
					}
				}
			}
		}
		if (type.getSuperclass() != Object.class) {
			iterateAllDeclaredMethods(type.getSuperclass(), strategy, includeMethods, excludeMethods, onClass, hasValueJsonOnClass, methodType);// 开始循环父类所有字段，判断是否可以转化
		}
	}

	/**
	 * 判断类上的json的strategy是否与strategy匹配
	 * 
	 * @param type
	 * @param strategy
	 * @param maps
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-21 下午5:11:26
	 */
	private static <T> boolean isMatch(Class<T> type, String strategy, Maps maps) {
		return type.isAnnotationPresent(Maps.class) && (Lists.newArrayList(maps.strategy()).contains(strategy) || Lists.newArrayList(maps.strategy()).contains(Maps.allStrategy));
	}

	/**
	 * 判断类上的MapsIgnore的strategy是否与strategy匹配
	 * 
	 * @param type
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-21 下午5:12:47
	 */
	private static <T> boolean isMatch(Class<T> type, String strategy, MapsIgnore mapsIgnore) {
		return type.isAnnotationPresent(MapsIgnore.class)
				&& (Lists.newArrayList(mapsIgnore.strategy()).contains(strategy) || Lists.newArrayList(mapsIgnore.strategy()).contains(MapsIgnore.allStrategy)) && mapsIgnore.value().length > 0;
	}

	/**
	 * 是否需要进行转化，判断field或method上的json的strategy是否与strategy匹配
	 * 
	 * @param fm
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:40:08
	 */
	private static boolean isMatch(AccessibleObject fm, String strategy, Maps maps) {
		return fm.isAnnotationPresent(Maps.class) && (Lists.newArrayList(maps.strategy()).contains(strategy) || Lists.newArrayList(maps.strategy()).contains(Maps.allStrategy));
	}

	/**
	 * 是否需要进行转化，判断field或method上的json的strategy是否与strategy匹配
	 * 
	 * @param fm
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:40:08
	 */
	private static boolean isMatch(AccessibleObject fm, String strategy, MapsIgnore mapsIgnore) {
		return fm.isAnnotationPresent(MapsIgnore.class) && (Lists.newArrayList(mapsIgnore.strategy()).contains(strategy) || Lists.newArrayList(mapsIgnore.strategy()).contains(MapsIgnore.allStrategy));
	}

	/**
	 * 判断方法上是否有isRaw，是否保留原来的值及类型
	 * @param m
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-9-25 下午3:00:07
	 */
	private static boolean isRaw(Method m) {
		Maps maps = m.getAnnotation(Maps.class);
		if (maps == null) {
			return false;
		} else {
			return maps.isRaw();
		}
	}
	
	/**
	 * 选择匹配的类型，若map中有CLASS则使用CLASS否则使用actualType
	 * @param m
	 * @param actualType
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午3:54:51
	 */
	private static Class<?> chooseActualType(Map<String, Object> m, Class<?> actualType) {
		if (m.containsKey(CLASS) && StrUtil.isNotEmpty(m.get(CLASS))) {// 包含CLASS需要转化成对象
			try {
				return Class.forName((String) m.get(CLASS));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logException(log, e);
			}
		}
		return actualType;
	}

	/**
	 * 判断策略为strategy时，是否需要转化CLASS
	 * 
	 * @param obj
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-2 下午2:37:46
	 */
	private static boolean isKeepType(Object obj, String strategy) {
		if (obj != null) {
			Maps maps = obj.getClass().getAnnotation(Maps.class);
			if (maps != null) {
				List<String> list = Lists.newArrayList(maps.includeTypeStrategy());
				return list.contains(strategy) || list.contains(Maps.allStrategy);
			}
		}
		return false;
	}

	/**
	 * 获取缓存的key
	 * @param type
	 * @param strategy
	 * @param methodType
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午4:08:13
	 */
	private static <T> String getCacheKey(Class<T> type, String strategy, MethodType methodType) {
		String cacheKey = type.getName() + strategy;
		if (methodType != null) {
			cacheKey += methodType.name();
		}
		return cacheKey;
	}

	/**
	 * 通过方法名获取键
	 * @param methodName
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:45:11
	 */
	private static String getMapKeyFromMethodName(String methodName) {
		return StrUtil.uncapitalize(methodName.replaceFirst(regexFromMethodNameToMapKey, ""));
	}

	/**
	 * 是set方法
	 * @param m
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:46:27
	 */
	private static boolean isSetMethod(Method m) {
		return m.getParameterTypes().length == 1 && RegexUtil.isStartWith(regexSetMethodName, m.getName());
	}

	/**
	 * 是get方法
	 * @param m
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:46:35
	 */
	private static boolean isGetMethod(Method m) {
		return m.getParameterTypes().length == 0 && RegexUtil.isStartWith(regexGetMethodName, m.getName());
	}

}
