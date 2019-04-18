package com.genius.core.serial.engine;

import java.io.IOException;

/**
 * @author Architect.bian
 *
 */
public interface SerialEngine {

	/**
	 * @param obj
	 * @param fullpath
	 * @param compress
	 * @return
	 */
	boolean serialize(Object obj, String fullpath, boolean compress) throws IOException;

	/**
	 * @param fullpath
	 * @param uncompress
	 * @return
	 */
	Object deserialize(String fullpath, boolean uncompress) throws IOException;

	/**
	 * @param obj
	 * @param compress
	 * @return
	 */
	byte[] serialize(Object obj, boolean compress) throws Exception;

	/**
	 * @param body
	 * @param uncompress
	 * @return
	 */
	Object deserialize(byte[] body, boolean uncompress) throws Exception;

}
