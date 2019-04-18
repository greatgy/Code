package com.genius.core.base.image;

import java.io.IOException;

import org.im4java.core.IM4JavaException;

/**
 * @author Architect.bian
 *
 */
public interface ImageEngine {

	/**
	 * @param width
	 * @param height
	 * @param files
	 * @return 处理后的文件列表
	 * @throws IM4JavaException 
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	String[] resizeImages(int width, int height, String... files) throws Exception;

	/**
	 * @param width
	 * @param height
	 * @param quality
	 * @param files
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	String[] resizeImages(int width, int height, double quality, String... files) throws Exception;

}
