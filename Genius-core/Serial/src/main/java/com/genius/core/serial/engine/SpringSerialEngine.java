package com.genius.core.serial.engine;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.SerializationUtils;

/**
 * @author Architect.bian
 * 
 */
public class SpringSerialEngine implements SerialEngine {

	/**
	 * @param f
	 * @throws IOException 
	 */
	@Override
	public boolean serialize(Object obj, String fullpath, boolean compress) throws IOException {
		byte[] data = SerializationUtils.serialize(obj);
		File file = new File(fullpath);
		FileUtils.writeByteArrayToFile(file, data);
		return true;
	}
	
	/**
	 * @param uid
	 * @return
	 * @throws IOException 
	 */
	@Override
	public Object deserialize(String fullpath, boolean uncompress) throws IOException {
		File file = new File(fullpath);
		if (file.exists()) {
			return SerializationUtils.deserialize(FileUtils.readFileToByteArray(file));
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.serial.SerialEngine#serialize(java.lang.Object, boolean)
	 */
	@Override
	public byte[] serialize(Object obj, boolean compress) throws Exception {
		return SerializationUtils.serialize(obj);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.serial.SerialEngine#deserialize(byte[], boolean)
	 */
	@Override
	public Object deserialize(byte[] bytes, boolean uncompress) throws Exception {
		return SerializationUtils.deserialize(bytes);
	}

}
