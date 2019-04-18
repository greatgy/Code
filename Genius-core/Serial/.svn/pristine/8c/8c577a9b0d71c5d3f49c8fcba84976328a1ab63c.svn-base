package com.genius.core.serial.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.KryoException;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.BaseUtil;
import com.genius.core.base.utils.EnumUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.ReflectUtil;
import com.genius.core.serial.annotation.Serial;
import com.genius.core.serial.engine.KryoSerialEngine;
import com.genius.core.serial.engine.SerialEngine;
import com.genius.core.serial.engine.SpringSerialEngine;

/**
 * @author Architect.bian
 * 
 */
public class SerialUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(SerialUtil.class);
	
	public static SerialEngine engineDefault = new SpringSerialEngine();
	public static SerialEngine engineKryo = new KryoSerialEngine();
	public static SerialEngine engineSpring = new SpringSerialEngine();

	/**
	 * 默认压缩序列化
	 * @param obj
	 * @param fullpath
	 * @return
	 */
	public static boolean serialize(Object obj, String fullpath) {
		return serialize(obj, fullpath, false);
	}
	
	public static boolean serialize(Object obj, String fullpath, boolean compress) {
		return serialize(engineDefault, obj, fullpath, compress);
	}

	/**
	 * 采用engine进行序列化
	 * @param engine
	 * @param obj
	 * @param fullpath
	 * @return
	 */
	public static boolean serialize(SerialEngine engine, Object obj, String fullpath) {
		return serialize(engineDefault, obj, fullpath, false);
	}

	public static boolean serialize(SerialEngine engine, Object obj, String fullpath, boolean compress) {
		try {
			return engine.serialize(buildSerialObject(obj), fullpath, compress);
		} catch (IOException e) {
			log.error(String.format("IOException on Serialize('%s', '%s', %s)", obj.toString(), fullpath, String.valueOf(compress)));
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 默认解压返序列化
	 * @param fullpath
	 * @return
	 */
	public static Object deserialize(String fullpath) {
		return deserialize(fullpath, false);
	}
	
	/**
	 * @param uid
	 * @return
	 */
	public static Object deserialize(String fullpath, boolean uncompress) {
		return deserialize(engineDefault, fullpath, uncompress);
	}

	/**
	 * 采用engine进行反序列化
	 * @param engine
	 * @param fullpath
	 * @return
	 */
	public static Object deserialize(SerialEngine engine, String fullpath) {
		return deserialize(engineDefault, fullpath, false);
	}

	/**
	 * @param uid
	 * @return
	 */
	public static Object deserialize(SerialEngine engine, String fullpath, boolean uncompress) {
		try {
			return engine.deserialize(fullpath, uncompress);
		} catch (KryoException e) {
			log.error(String.format("KryoException Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		} catch (IOException e) {
			log.error(String.format("IOException Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		} catch (IllegalArgumentException e) {
			log.error(String.format("IllegalArgumentException Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		} catch (Exception e) {
			log.error(String.format("Exception Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		}
		return null;
	}

	/**
	 * 默认解压返序列化
	 * @param fullpath
	 * @return
	 */
	public static <T> T deserialize(String fullpath, Class<T> valueType) {
		return deserialize(fullpath, false, valueType);
	}
	
	/**
	 * 采用engine进行反序列化
	 * @param engine
	 * @param fullpath
	 * @return
	 */
	public static <T> T deserialize(SerialEngine engine, String fullpath, Class<T> valueType) {
		return deserialize(engineDefault, fullpath, false, valueType);
	}

	/**
	 * @param uid
	 * @return
	 */
	public static <T> T deserialize(String fullpath, boolean uncompress, Class<T> valueType) {
		return deserialize(engineDefault, fullpath, uncompress, valueType);
	}
	
	/**
	 * 有序列化为map对象的数据反序列化为Object
	 * @param uid
	 * @return
	 */
	public static <T> T deserialize(SerialEngine engine, String fullpath, boolean uncompress, Class<T> valueType) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) engine.deserialize(fullpath, uncompress);
			return buildDeserialObject(map, valueType);
		} catch (KryoException e) {
			log.error(String.format("KryoException Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		} catch (IOException e) {
			log.error(String.format("IOException Deserializing:%s, uncompress:%s", fullpath, uncompress));
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * @param uid
	 * @return
	 */
	public static <T> T deserializeFromMap(Map<String, Object> map, Class<T> valueType) {
		return buildDeserialObject(map, valueType);
	}

	/**
	 * 序列化为二进制字节数据
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		return serialize(obj, false);
	}
	
	/**
	 * 序列化为二进制字节数据
	 * @return
	 */
	public static byte[] serialize(Object obj, boolean uncompress) {
		return serialize(engineDefault, obj, uncompress);
	}
	
	/**
	 * 序列化为二进制字节数据
	 * @return
	 */
	public static byte[] serialize(SerialEngine engine, Object obj, boolean uncompress) {
		try {
			return engine.serialize(obj, uncompress);
		} catch (KryoException e) {
			log.error(String.format("KryoException Serialize:%s", obj.toString()));
			logException(log, e);
		} catch (Exception e) {
			log.error(String.format("Exception Serialize:%s", obj.toString()));
			logException(log, e);
		}
		return null;
	}

	/**
	 * 由字节数据反序列化为对象
	 * @param body
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		return deserialize(bytes, false);
	}
	
	/**
	 * 由字节数据反序列化为对象
	 * @param body
	 * @return
	 */
	public static Object deserialize(byte[] bytes, boolean uncompress) {
		return deserialize(engineDefault, bytes, uncompress);
	}
	
	/**
	 * 由字节数据反序列化为对象
	 * @param body
	 * @return
	 */
	public static Object deserialize(SerialEngine engine, byte[] bytes, boolean uncompress) {
		try {
			return engine.deserialize(bytes, uncompress);
		} catch (KryoException e) {
			log.error(String.format("Error Deserialize:%s", bytes.toString()));
			logException(log, e);
		} catch (Exception e) {
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 序列化成Json
	 * @param engine
	 * @param obj
	 * @param fullpath
	 * @return
	 */
	public static boolean serializeToJson(Object obj, String fullpath) {
		return serializeToJson(obj, fullpath, Json.defaultStrategy);
	}
	
	/**
	 * 序列化成Json
	 * @param engine
	 * @param obj
	 * @param fullpath
	 * @return
	 */
	public static boolean serializeToJson(Object obj, String fullpath, String strategy) {
		try {
			File file = new File(fullpath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileUtil.write(JsonUtil.toJson(obj, strategy), file);
			return true;
		} catch (IOException e) {
			log.error(String.format("IOException on Serialize('%s', '%s')", obj.toString(), fullpath));
			logException(log, e);
			return false;
		}
	}
	
	/**
	 * 由json文件反序列化成json
	 * @param valueType 
	 * @param body
	 * @return
	 */
	public static <T> T deserializeFromJson(String fullpath, Class<T> valueType) {
		return deserializeFromJson(fullpath, valueType, Json.defaultStrategy);
	}
	
	/**
	 * 由json文件反序列化成json
	 * @param valueType 
	 * @param body
	 * @return
	 */
	public static <T> T deserializeFromJson(String fullpath, Class<T> valueType, String strategy) {
		try {
			File file = new File(fullpath);
			String json = FileUtil.toString(file);
			return JsonUtil.fromJson(json, valueType, strategy);	
		} catch (KryoException e) {
			log.error(String.format("KryoException Deserializing:%s", fullpath));
			logException(log, e);
		} catch (FileNotFoundException e) {
			log.error(String.format("FileNotFoundException Deserializing:%s", fullpath));
			logException(log, e);
		} catch (IOException e) {
			log.error(String.format("IOException Deserializing:%s", fullpath));
			logException(log, e);
		}
		return null;
	}

	/**
	 * 准备所序列化的对象
	 * @param obj
	 * @return
	 */
	private static Object buildSerialObject(Object obj) {
		if (obj.getClass().isAnnotationPresent(Serial.class)) {//未完成，暂且输出所有的字段
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				Field[] fields = obj.getClass().getFields();
				for (Field field : fields) {
					map.put(field.getName(), buildSerialObject(ReflectUtil.get(obj, field)));
				}
				return map;
			} catch (IllegalArgumentException e) {
				logException(log, e);
			} catch (IllegalAccessException e) {
				logException(log, e);
			}
		}
		return obj;
	}

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T buildDeserialObject(Map<String, Object> map, Class<T> valueType) {
		try {
			T t = ReflectUtil.newInstance(valueType);
			Field[] fields = valueType.getFields();
			for (Field field : fields) {
				Object val = map.get(field.getName());
				if (field.getType().isEnum()) {
					ReflectUtil.set(t, field, EnumUtil.newInstance(field.getType(), val));
				} else if (val.getClass().getName().equals(Map.class.getName())) {//属性是对象obj.obj
					ReflectUtil.set(t, field, buildDeserialObject((Map<String, Object>)val, valueType));
				} else {
					ReflectUtil.set(t, field, val);//ReflectUtil.set(obj, field, field.getType().newInstance());
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
	
}
