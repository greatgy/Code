package com.genius.core.base.utils;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.genius.core.base.mock.testentity.User;
/**
 * @author architect.bian
 *
 */
public class ReflectUtilTest {
	
	@Test
	public void testReflectFromObjectToUser() {
		Map<String, User> map = new HashMap<String, User>();
		User bill = new User("bill");
		map.put("bill", bill);
		User kim = new User("kim");
		map.put("kim", kim);
		String json = JsonUtil.toJson(map);
		System.out.println(json);
		assertTrue(json.contains("\"name\":\"bill\""));
		assertTrue(json.contains("\"name\":\"kim\""));
		
		@SuppressWarnings("unchecked")
		Map<String, Object> mapFromJson = JsonUtil.fromJson(json, Map.class);
		System.out.println(mapFromJson);
		for (Object v : mapFromJson.values()) {
			User user = ReflectUtil.convert(v, User.class);
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void testClassLoader(){
		assertTrue(String.class.getClassLoader() == null);
		assertTrue(User.class.getClassLoader() != null);
	}
	
	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @author Architect.bian
	 * @createtime 2014-12-26 下午5:40:49
	 */
	@Test
	public void testMethodType() throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Method method = User.class.getMethod("setNickname", String.class);
		System.out.println("setNickname:");
		printMethodType(method);
		method = User.class.getMethod("setChildren", List.class);
		System.out.println("setChildren:");
		printMethodType(method);
		method = User.class.getMethod("getChildren");
		System.out.println("getChildren:");
		printMethodType(method);
		method = User.class.getMethod("setFriends", List.class);
		System.out.println("setFriends:");
		printMethodType(method);
		method = ReflectUtil.getMethod(User.class, "setTags");
		System.out.println("setTags:");
		printMethodType(method);
		method = ReflectUtil.getMethod(User.class, "setBooks");
		System.out.println("setBooks:");
		printMethodType(method);
	}

	private void printMethodType(Method method) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		TypeVariable<Method>[] typeParameters = method.getTypeParameters();
		System.out.println(typeParameters);
		Class<?> returnType = method.getReturnType();
		System.out.println("returnType:" + returnType);
		Type genericReturnType = method.getGenericReturnType();
		System.out.println("genericReturnType:" + genericReturnType);
		Class<?>[] parameterTypes = method.getParameterTypes();
		for (Class<?> item : parameterTypes) {
			System.out.println("parameterTypes.item:" + item);
		}
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		for (Type type : genericParameterTypes) {
			if (type instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType)type;
				System.out.println("ptype:" + ptype);
				Type rawType = ptype.getRawType();
				System.out.println("rawType:" + rawType);
				System.out.println("rawType =? List:" + rawType.equals(List.class));
				Type[] actualTypeArguments = ptype.getActualTypeArguments();
				System.out.println("actualTypeArguments:" + actualTypeArguments);
				for (Type t : actualTypeArguments) {
					System.out.println("actualTypeArguments.item:" + t);
					Class<?> clz = (Class<?>) t;
					Object obj = clz.newInstance();
					System.out.println("obj:" + obj);
				}
			} else if (((Class<?>)type).isArray()) {
//				Class<?> clz = (Class) ((GenericArrayType) type).getGenericComponentType();
//				Object obj = clz.newInstance();
				Class<?> atype = ((Class<?>)type).getComponentType();
				Object arr = Array.newInstance(atype, 100); 
				System.out.println("obj:" + arr);
			} else {
				Class<?> clz = (Class<?>) type;
				Object obj = clz.newInstance();
				System.out.println("obj:" + obj);
			}
		}
		System.out.println("genericParameterTypes:" + genericParameterTypes);
		Class<?>[] exceptionTypes = method.getExceptionTypes();
		for (Class<?> item : exceptionTypes) {
			System.out.println("exceptionTypes.item:" + item);
		}
	}
}
