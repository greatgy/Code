package com.genius.core.base.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/**
 * @author architect.bian
 *
 */
public class JsonUtil extends BaseUtil {

//	private static final String methodSetPrefix = "set";
	private static final String regexGetMethodName = "^(get|is)";

	private static final String CLASS = "CLASS";

	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	
	private static JsonFactory factory = new JsonFactory();
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.registerModule(new JodaModule());
	}
	
	private static Map<String, List<String>> cacheFieldNames = new HashMap<>();
	
	/**
	 * 序列化为Json字符串
	 * 不支持Map<Obj, Obj>的类型，可以是Map<String, Obj>类型或List<Obj>类型
	 * @param obj
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 下午11:39:02
	 */
	public static String toJson(Object obj) {
		return toJson(obj, Json.defaultStrategy);
	}

	/**
	 * 按照strategy序列化为Json字符串
	 * 不支持Map<Obj, Obj>的类型，可以是Map<String, Obj>类型或List<Obj>类型
	 * @param obj
	 * @param strategy
	 * @return
	 * @author: Architect.bian
	 * 2014-6-15 上午1:39:44
	 */
	public static String toJson(Object obj, String strategy) {
		if (strategy == null || strategy.length() == 0) {
			strategy = Json.defaultStrategy;
		}
		return writeValueAsString(buildJsonObject(obj, strategy));
	}
	
	/**
	 * 将字符串转换成Type类型
	 * 不支持Map<Obj, Obj>的类型，可以是Map<String, Obj>类型或List<Obj>类型
	 * @param json
	 * @param type
	 * @return
	 * @author: Architect.bian
	 * 2014-6-15 上午1:00:33
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		return fromJson(json, type, Json.defaultStrategy);
	}
	
	/**
	 * 将字符串转换成Type类型，并指定strategy
	 * 不支持Map<Obj, Obj>的类型，可以是Map<String, Obj>类型或List<Obj>类型
	 * @param json
	 * @param type
	 * @param strategy
	 * @return
	 * @author: Architect.bian
	 * 2014-6-15 上午1:01:55
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Class<T> type, String strategy) {
		if (strategy == null || strategy.length() == 0) {
			strategy = Json.defaultStrategy;
		}
		if (json == null || json.equals("null")) {
			return null;
		}
		if (json.length() == 0) {
			if (type == String.class) {
				return (T) new String();
			} else {
				return null;
			}
		}
		return dejsonToObject(json, type, strategy);
	}

	/**
	 * @param object
	 * @return
	 */
	private static String writeValueAsString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		}
		return "";
	}
	
	/**
	 * @param content
	 * @param type
	 * @return
	 */
	private static <T> T readValue(String content, Class<T> type) {
		try {
			return mapper.readValue(content, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logException(log, e);
		} catch (IOException e) {
			e.printStackTrace();
			logException(log, e);
		}
		return null;
	}
	

	/**
	 * 返回最终要序列化的对象，有@json标注的会返回Map，否则会直接返回对象
	 * @param obj
	 * @return
	 */
	private static Object buildJsonObject(Object obj, String strategy) {
		if (obj == null) {
			return null;
		}
		if (strategy == null) {
			strategy = Json.defaultStrategy;
		}
		String cacheKey = getCacheKey(obj.getClass(), strategy);
		List<String> finalFields;
		if (cacheFieldNames.containsKey(cacheKey)) {//已缓存
			finalFields = cacheFieldNames.get(cacheKey);//设置finalFields的值
		} else {
			finalFields = getFinalFieldNames(obj.getClass(), strategy);
			cacheFieldNames.put(cacheKey, finalFields);//缓存
		}
		if (finalFields == null || finalFields.size() == 0) {//根对象obj的类及字段上都没有标记@Json注解
			if (Lists.newArrayList(obj.getClass().getInterfaces()).contains(List.class)) {//根对象obj是List类型
				return buildJsonObjectFromList(obj, strategy);
			} else if (Lists.newArrayList(obj.getClass().getInterfaces()).contains(Map.class)) {//根对象obj是Map类型
				return buildJsonObjectFromMap(obj, strategy);
			} else {
				return obj;
			}
		} else {
			Map<String, Field> allFields = new HashMap<>();
			Map<String, Method> allMethods = new HashMap<>();
			processFinalFieldNames(allFields, allMethods, obj.getClass(), finalFields);
			Map<String, Object> result = buildMapFromObjByFields(obj, allFields, strategy);
			result.putAll(buildMapFromObjByMethods(obj, allMethods, strategy));
			return result;
		}
	}

	/**
	 * Obj是List类型的，返回经过处理的可转成Json的List类型
	 * @param obj
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 下午10:35:23
	 */
	@SuppressWarnings("unchecked")
	private static Object buildJsonObjectFromList(Object obj, String strategy) {
		List<Object> list = (List<Object>) obj;
		int i = 0;
		while ((i + 1) <= list.size()) {
			list.set(i, buildJsonObject(list.get(i), strategy));
			i++;
		}
		return list;
	}

	/**
	 * Obj是Map类型的，返回values经过处理的可转成Json的Map类型
	 * 
	 * @param obj
	 * @param strategy
	 * @return
	 * @author: Architect.bian
	 * 2014-6-14 下午10:53:39
	 */
	@SuppressWarnings("unchecked")
	private static Object buildJsonObjectFromMap(Object obj, String strategy) {
		Map<Object, Object> map = (Map<Object, Object>)obj; 
		for (Object key : map.keySet()) {
			map.put(key, buildJsonObject(map.get(key), strategy));
		}
		return map;
	}

	/**
	 * 通过字段名，返回由对象构造的一个map对象
	 * @param obj
	 * @param strategy
	 * @param allfields
	 * @return
	 */
	private static Map<String, Object> buildMapFromObjByFields(Object obj, Map<String, Field> mapFields, String strategy) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (mapFields.size() > 0) {//若在类之上指定了Json的所有字段，则先输出这些字段
			try {
				for (String key : mapFields.keySet()) {
					Field field = mapFields.get(key);
					Object val;
					if (field.getType().isEnum()) {
						val = ReflectUtil.get(obj, mapFields.get(key));
						if (val != null) {
							val = val.toString();
						}
					} else {
						val = buildJsonObject(ReflectUtil.get(obj, mapFields.get(key)), strategy);
					}
					map.put(key, val);
				}
//				Json jsonOnClass = obj.getClass().getAnnotation(Json.class);
//				if (jsonOnClass != null && jsonOnClass.keepType()) {
//					map.put(CLASS, obj.getClass().getName());
//				}
				if (isKeepType(obj, strategy)) {//判断是否将CLASS同时序列化
					map.put(CLASS, obj.getClass().getName());
				}
			} catch (SecurityException e) {
				logException(log, e);
			} catch (IllegalArgumentException e) {
				logException(log, e);
			} catch (IllegalAccessException e) {
				logException(log, e);
			}
		}
		return map;
	}

	/**
	 * 通过方法名，返回由对象构造的一个map对象
	 * @param obj
	 * @param allMethods
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午3:56:48
	 */
	private static Map<String, Object> buildMapFromObjByMethods(Object obj, Map<String, Method> allMethods, String strategy) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (allMethods.size() > 0) {//若在类之上指定了Json的所有字段，则先输出这些字段
			try {
				for (String key : allMethods.keySet()) {
					Object val;
					if (allMethods.get(key).getReturnType().isEnum()) {
						val = ReflectUtil.invoke(obj, allMethods.get(key));
						if (val != null) {
							val = val.toString();
						}
					} else {
						val = buildJsonObject(ReflectUtil.invoke(obj, allMethods.get(key)), strategy);
					}
					map.put(getJsonKeyFromMethodKey(key), val);
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
	 * 通过type与strategy选择序列化方案
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T dejsonToObject(String json, Class<T> type, String strategy) {
		T t;
		if ((!type.isInterface() && Lists.newArrayList(type.getInterfaces()).contains(List.class)) || type == List.class) {//List
			t = readValue(json, type);
			t = (T) dejsonFromListToObject(t, strategy);
		} else if ((!type.isInterface() && Lists.newArrayList(type.getInterfaces()).contains(Map.class)) || type == Map.class) {//Map
			t = readValue(json, type);
			t = (T) dejsonFromMapToObject(t, strategy);
		} else {//实体
			Map<String, Object> map = readValue(json, Map.class);
			t = (T) dejsonToObject(map, type, strategy);
		}
		return t;
	}
	
	/**
	 * 将List<Map>转换成真实的List<Obj>
	 * @param obj
	 * @param strategy
	 * @return
	 * @author: Architect.bian
	 * 2014-6-15 上午2:32:37
	 */
	@SuppressWarnings("unchecked")
	private static Object dejsonFromListToObject(Object obj, String strategy) {
		List<Object> list = (List<Object>) obj;
		int i = 0;
		while ((i + 1) <= list.size()) {
			if ((!list.get(i).getClass().isInterface() && Lists.newArrayList(list.get(i).getClass().getInterfaces()).contains(Map.class)) || list.get(i).getClass() == Map.class) {//item值是Map类型
				Map<String, Object> map = (Map<String, Object>)list.get(i);
				if (map.containsKey(CLASS) && StrUtil.isNotEmpty(map.get(CLASS))) {//包含CLASS需要转化成对象
					try {
						list.set(i, dejsonToObject(map, Class.forName((String) map.get(CLASS)), strategy));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						logException(log, e);
					}
				}
			}
			i++;
		}
		return list;
	}
	
	/**
	 * 将Map<String,Map>转换成真实的Map<String,Obj>
	 * @param obj
	 * @param strategy
	 * @return
	 * @author: Architect.bian
	 * 2014-6-15 上午2:33:11
	 */
	@SuppressWarnings("unchecked")
	private static Object dejsonFromMapToObject(Object obj, String strategy) {
		Map<String, Object> map = (Map<String, Object>)obj;
		for (String key : map.keySet()) {
			if (map.get(key) != null && (!map.get(key).getClass().isInterface() && Lists.newArrayList(map.get(key).getClass().getInterfaces()).contains(Map.class)) || map.get(key).getClass() == Map.class) {//item值是Map类型
				Map<String, Object> m = (Map<String, Object>)map.get(key);
				if (m.containsKey(CLASS) && StrUtil.isNotEmpty(m.get(CLASS))) {//包含CLASS需要转化成对象
					try {
						map.put(key, dejsonToObject(m, Class.forName((String) m.get(CLASS)), strategy));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						logException(log, e);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 将Map类型转成T类型的实例
	 * @param map
	 * @param type
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午5:16:13
	 */
	private static <T> T dejsonToObject(Map<String, Object> map, Class<T> type, String strategy) {
		String cacheKey = getCacheKey(type, strategy);
		List<String> finalFields;
		if (cacheFieldNames.containsKey(cacheKey)) {//已缓存
			finalFields = cacheFieldNames.get(cacheKey);//设置finalFields的值
		} else {
			finalFields = getFinalFieldNames(type, strategy);
			cacheFieldNames.put(cacheKey, finalFields);//缓存
		}
		Map<String, Field> allFields = new HashMap<>();
		Map<String, Method> allMethods = new HashMap<>();
		processFinalFieldNames(allFields, allMethods, type, finalFields);
		T t = dejsonToObjectFromMapByFields(map, type, allFields, strategy);
		t = dejsonToObjectFromMapByMethods(t, map, type, allMethods, strategy);
		return t;
	}
	
	/**
	 * 创建一个实例，并对所有的fields赋值
	 * @param map
	 * @param type
	 * @param fields
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午5:40:56
	 */
	private static <T> T dejsonToObjectFromMapByFields(Map<String, Object> map, Class<T> type, Map<String, Field> fields, String strategy) {
		try {
			T t = ReflectUtil.newInstance(type);
			for (String key : fields.keySet()) {
				Object val = map.get(key);
				Field field = fields.get(key);
				if (val != null) {
					if (field.getType().isEnum()) {
						ReflectUtil.set(t, field, EnumUtil.newInstance(field.getType(), val));//实例化枚举类型
					} else if ((!val.getClass().isInterface() && Lists.newArrayList(val.getClass().getInterfaces()).contains(Map.class)) || val.getClass().getName().equals(Map.class.getName())) {//属性是对象obj.Map<obj>
						ReflectUtil.set(t, field, dejsonFromMapToObject(val, strategy));//重新将val序列化，递归反序列化
//						ReflectUtil.set(t, field, dejsonToObject(writeValueAsString(val), val.getClass(), strategy));//重新将val序列化，递归反序列化
					} else if ((!val.getClass().isInterface() && Lists.newArrayList(val.getClass().getInterfaces()).contains(List.class)) || val.getClass().getName().equals(List.class.getName())) {//属性是对象obj.List<obj>
						ReflectUtil.set(t, field, dejsonFromListToObject(val, strategy));//重新将val序列化，递归反序列化
					} else if (field.getType().getName().indexOf("joda") > 0) {//属性是对象obj.obj
						ReflectUtil.set(t, field, DateUtil.newInstance(field.getType(), val));
					} else {
						ReflectUtil.set(t, field, val);//ReflectUtil.set(obj, field, field.getType().newInstance());
					}
				} else if (field.getType() == java.lang.Integer.TYPE) {
					ReflectUtil.set(t, field, 0);//设置int类型默认值为0
				} else if (field.getType() == java.lang.Long.TYPE) {
					ReflectUtil.set(t, field, 0);//设置long类型默认值为0
				} else {
					ReflectUtil.set(t, field, null);//ReflectUtil.set(obj, field, field.getType().newInstance());
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
	 * 在t对象基础上执行所有的methods
	 * 反序列化时执行t对象上的set方法，目前反序列化时还不去调用方法
	 * @param t
	 * @param map
	 * @param type
	 * @param methods
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午5:35:58
	 */
	private static <T> T dejsonToObjectFromMapByMethods(T t, Map<String, Object> map, Class<T> type, Map<String, Method> methods, String strategy) {
//		for (String key : methods.keySet()) {
//			try {
//				Method setMethod = type.getMethod(getSetMethodNameFromJsonKey(key));
//				ReflectUtil.invoke(t, setMethod, map.get(key));
//			} catch (IllegalAccessException e) {
//				logException(log, e);
//			} catch (IllegalArgumentException e) {
//				logException(log, e);
//			} catch (InvocationTargetException e) {
//				logException(log, e);
//			}
//		}
		return t;
	}

	/**
	 * 通过allFieldNames重新生成所有需要序列化的Fields及methods
	 * @param fields 需要序列化的所有字段
	 * @param methods 需要序列化的所有方法
	 * @param type
	 * @param allFieldNames
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午5:51:42
	 */
	private static <T> void processFinalFieldNames(Map<String, Field> fields, Map<String, Method> methods, Class<T> type, List<String> allFieldNames) {
		Field[] fs = type.getDeclaredFields();
		for (Field f : fs) {
			if (!Modifier.isFinal(f.getModifiers())) {
				String key = null;
				if (allFieldNames.contains(f.getName())) {//判断字段名是否匹配
					key = f.getName();
				} else if(f.isAnnotationPresent(Json.class)) {//判断json.key是否匹配
					Json json = f.getAnnotation(Json.class);
					if (allFieldNames.contains(json.alias())) {//json.key是否存在于allFieldsKey中
						key = json.alias();
					}
				}
				if (key != null && !fields.containsKey(key)) {//在allFieldsKey中存在key，且未在子类中添加，且不是final类型
					fields.put(key, f);//放入fields中
				}
			}
		}
		Method[] ms = type.getMethods();
		for (Method m : ms) {
			String key = null;
			if (m.getParameterTypes().length == 0) {//方法没有参数,eg:getStatusName()
				if (allFieldNames.contains(m.getName())) {//判断字段名是否匹配
					key = m.getName();
				} else if(m.isAnnotationPresent(Json.class)) {//判断json.key是否匹配
					Json json = m.getAnnotation(Json.class);
					if (allFieldNames.contains(json.alias())) {//json.key是否存在于allFieldsKey中
						key = json.alias();
					}
				}
			}
			if (key != null && !methods.containsKey(key)) {//在allFieldsKey中存在key，且未在子类中添加，且不是final类型
				methods.put(key, m);//放入methods中
			}
		}
		if (type.getSuperclass() != Object.class) {
			processFinalFieldNames(fields, methods, type.getSuperclass(), allFieldNames);
		}
	}

	/**
	 * 获取最终序列化的所有字段名及方法名
	 * @param <T>
	 * @param obj
	 * @param strategy
	 * @param finalFields
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> List<String> getFinalFieldNames(Class<T> type, String strategy) {
		boolean onClass = false;
		boolean onField = false;
		boolean onMethod = false;
		Json jsonOnClass = type.getAnnotation(Json.class);
		JsonIgnore jsonIgnoreOnClass = type.getAnnotation(JsonIgnore.class);
		if (isMatch(type, strategy, jsonOnClass)) {//Class上有匹配strategy的json
			onClass = true;
		} else {
			Field[] fields = type.getDeclaredFields();//获取本类所有字段
			for (Field field : fields) {
				Json j = field.getAnnotation(Json.class);
				if (isMatch(field, strategy, j)) {//field上有匹配strategy的json
					onField = true;
					break;
				}
			}
			if (!onField) {//若onField为false则继续判断onMethod
				Method[] methods = type.getDeclaredMethods();//获取本类所有方法
				for (Method m : methods) {
					Json j = m.getAnnotation(Json.class);
					if (isMatch(m, strategy, j)) {
						onMethod = true;
						break;
					}
				}
			}
		}
		if (onClass || onField || onMethod) {//需要序列化，class上标有@json或字段上标有@json或方法上标有@json
			List<String> includeFields = new ArrayList<>();
			List<String> excludeFields = new ArrayList<>();
			boolean hasValueJsonOnClass = true;//Class上的Json有value值
			//开始处理类上的json与jsonignore注解
			if (onClass) {//类上有Json标注,获取所列的
				if (jsonOnClass.value().length == 0) {
					hasValueJsonOnClass = false;//class上的json注解无值
				} else if(isMatch(type, strategy, jsonOnClass)) {//json.values有值并匹配strategy
					includeFields = Lists.newArrayList(jsonOnClass.value());//有值
					hasValueJsonOnClass = true;
				}
			}
			if (isMatch(type, strategy, jsonIgnoreOnClass)) {//jsonIgnore.values有值并匹配strategy
				excludeFields = Lists.newArrayList(jsonIgnoreOnClass.value());
			}
			//开始循环本类及父类所有的field，判断是否序列化
			iterateAllDeclaredFields(type, strategy, includeFields, excludeFields, onClass, hasValueJsonOnClass);
			iterateAllDeclaredMethods(type, strategy, includeFields, excludeFields, onClass, hasValueJsonOnClass);
			return (List<String>) ListUtil.removeAll(includeFields, excludeFields);
		} else {
			return null;
		}
	}

	/**
	 * 开始循环本类及父类所有的field，判断是否序列化
	 * @param type
	 * @param strategy
	 * @param includeFields
	 * @param excludeFields
	 * @param onClass
	 * @param hasValueJsonOnClass
	 */
	private static <T> void iterateAllDeclaredFields(Class<T> type, String strategy, List<String> includeFields, List<String> excludeFields, boolean onClass, boolean hasValueJsonOnClass) {
		Field[] fields = type.getDeclaredFields();//获取所有字段
		for (Field field : fields) {
			Json j = field.getAnnotation(Json.class);
			JsonIgnore jIgnore = field.getAnnotation(JsonIgnore.class);
			if (isMatch(field, strategy, jIgnore)) {//若field上存在JsonIgnore，并匹配strategy则忽略Json
				excludeFields.add(field.getName());
			} else if (isMatch(field, strategy, j)) {//若没有JsonIgnore，有Json并且strategy匹配
				includeFields.add(j.alias().length() == 0 ? field.getName() : j.alias());
			} else if ((!hasValueJsonOnClass && onClass)) {//类上有Json且Json没有值，(JsonIgnore注解没有匹配的strategy)
				includeFields.add(field.getName());
			}
		}
		if (type.getSuperclass() != Object.class) {
			iterateAllDeclaredFields(type.getSuperclass(), strategy, includeFields, excludeFields, onClass, hasValueJsonOnClass);//开始循环父类所有字段，判断是否可以序列化
		}
	}
	
	/**
	 * 开始循环本类及父类所有的method，判断是否序列化
	 * @param type
	 * @param strategy
	 * @param includeFields
	 * @param excludeFields
	 * @param onClass
	 * @param hasValueJsonOnClass
	 */
	private static <T> void iterateAllDeclaredMethods(Class<T> type, String strategy, List<String> includeFields, List<String> excludeFields, boolean onClass, boolean hasValueJsonOnClass) {
		Method[] methods = type.getDeclaredMethods();//获取所有方法
		for (Method m : methods) {
			Json j = m.getAnnotation(Json.class);
			JsonIgnore jIgnore = m.getAnnotation(JsonIgnore.class);
			if (isMatch(m, strategy, jIgnore)) {//若method上存在JsonIgnore，并匹配strategy则忽略Json
				excludeFields.add(m.getName());
			} else if (isMatch(m, strategy, j)) {//若没有JsonIgnore，有Json并且strategy匹配
				includeFields.add(j.alias().length() == 0 ? m.getName() : j.alias());
			}
			//方法上没有标记@json或者类上的@json没有值时则不序列化方法
//			else if ((!hasValueJsonOnClass && onClass)) {//类上有Json且Json没有值，(JsonIgnore注解没有匹配的strategy)
//				includeFields.add(m.getName());
//			}
		}
		if (type.getSuperclass() != Object.class) {
			iterateAllDeclaredMethods(type.getSuperclass(), strategy, includeFields, excludeFields, onClass, hasValueJsonOnClass);//开始循环父类所有字段，判断是否可以序列化
		}
	}

	/**
	 * 判断类上的json的strategy是否与strategy匹配
	 * @param type
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-21 下午5:11:26
	 */
	private static <T> boolean isMatch(Class<T> type, String strategy, Json j) {
		return type.isAnnotationPresent(Json.class) && (Lists.newArrayList(j.strategy()).contains(strategy) || Lists.newArrayList(j.strategy()).contains(Json.allStrategy));
	}

	/**
	 * 判断类上的JsonIgnore的strategy是否与strategy匹配
	 * @param type
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-21 下午5:12:47
	 */
	private static <T> boolean isMatch(Class<T> type, String strategy, JsonIgnore j) {
		return type.isAnnotationPresent(JsonIgnore.class) && (Lists.newArrayList(j.strategy()).contains(strategy) || Lists.newArrayList(j.strategy()).contains(Json.allStrategy)) && j.value().length > 0;
	}
	
	/**
	 * 是否需要进行序列化，判断field或method上的json的strategy是否与strategy匹配
	 * @param fm
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:40:08
	 */
	private static boolean isMatch(AccessibleObject fm, String strategy, Json j) {
		return fm.isAnnotationPresent(Json.class) && (Lists.newArrayList(j.strategy()).contains(strategy) || Lists.newArrayList(j.strategy()).contains(Json.allStrategy));
	}
	
	/**
	 * 是否需要进行序列化，判断field或method上的json的strategy是否与strategy匹配
	 * @param fm
	 * @param strategy
	 * @param j
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:40:08
	 */
	private static boolean isMatch(AccessibleObject fm, String strategy, JsonIgnore j) {
		return fm.isAnnotationPresent(JsonIgnore.class) && (Lists.newArrayList(j.strategy()).contains(strategy) || Lists.newArrayList(j.strategy()).contains(Json.allStrategy));
	}

	/**
	 * 判断策略为strategy时，是否需要序列化CLASS
	 * @param obj
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-2 下午2:37:46
	 */
	private static boolean isKeepType(Object obj, String strategy) {
		if (obj != null) {
			Json j = obj.getClass().getAnnotation(Json.class);
			if (j != null) {
				List<String> list = Lists.newArrayList(j.ignoreTypeStrategy());
				return !list.contains(strategy) && !list.contains(Json.allStrategy);
			}
		}
		return true;
	}

	/**
	 * 获取缓存的key
	 * @param type
	 * @param strategy
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-21 下午5:05:57
	 */
	private static <T> String getCacheKey(Class<T> type, String strategy) {
		String cacheKey = type.getName() + strategy;
		return cacheKey;
	}

	private static String getJsonKeyFromMethodKey(String key) {
		return StrUtil.uncapitalize(key.replaceFirst(regexGetMethodName, ""));
	}
	
	/**
	 * 由json的key返回对应的set方法名
	 * 暂时没用
	 * @param key
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:10:12
	 */
//	private static String getSetMethodNameFromJsonKey(String key) {
//		return methodSetPrefix + StrUtil.capitalize(key);
//	}

	/**
	 * 使用UTF8编码将对象object写入到stream中
	 * @param stream
	 * @param object
	 * @throws IOException
	 * @author Architect.bian
	 * @createtime 2014-6-29 下午5:58:05
	 */
	public static void write(OutputStream stream, Object object) throws IOException {
		write(stream, object, JsonEncoding.UTF8, Json.defaultStrategy);
	}

	/**
	 * 使用encoding编码将对象object写入到stream中
	 * @param stream
	 * @param object
	 * @param encoding
	 * @throws IOException
	 * @author Architect.bian
	 * @createtime 2014-6-29 下午5:59:02
	 */
	public static void write(OutputStream stream, Object object, JsonEncoding encoding) throws IOException {
		write(stream, object, encoding, Json.defaultStrategy);
	}
	
	/**
	 * 使用encoding编码将对象object写入到stream中
	 * @param stream
	 * @param object
	 * @param encoding
	 * @throws IOException
	 * @author Architect.bian
	 * @createtime 2014-6-29 下午5:59:02
	 */
	public static void write(OutputStream stream, Object object, JsonEncoding encoding, String strategy) throws IOException {
		JsonGenerator generator = factory.createGenerator(stream, encoding);
		mapper.writeValue(generator, buildJsonObject(object, strategy));
	}

}