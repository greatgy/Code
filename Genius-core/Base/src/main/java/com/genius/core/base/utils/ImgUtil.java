package com.genius.core.base.utils;

import java.io.File;
import java.io.IOException;

import org.im4java.core.IM4JavaException;
import org.im4java.core.InfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.genius.core.base.image.IM4GraphicsMagickEngine;

/**
 * @author Architect.bian
 *
 */
public class ImgUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(ImgUtil.class);
	
	public static final String southeast = "southeast";

	//	private static ImageEngine engine = new IM4GraphicsMagickEngine();
	private static IM4GraphicsMagickEngine engine = new IM4GraphicsMagickEngine();
	
	public static String resizeImage(int width, int height, String file) {
		try {
			return engine.resizeImages(width, height, file)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String resizeImage(int width, int height, double quality, String file) {
		try {
			return engine.resizeImages(width, height, quality, file)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String[] resizeImages(int width, int height, String... files) {
		try {
			return engine.resizeImages(width, height, files);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String waterTextMark(String text, int size, String color, String gravity, int dissolve, String srcImgPath) {
		try {
			return engine.waterTextMarks(text, size, color, gravity, dissolve, srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 给当前图片直接加水印不创建新文件
	 * @param imgnature
	 * @param imgwater
	 * @param southeast2
	 * @return
	 */
	public static String waterMark(String imgnature, String waterImgPath, String gravity) {
		try {
			return engine.waterMarks(imgnature, waterImgPath, gravity);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 给一张图片加水印，会创建新文件
	 * @param waterImgPath
	 * @param gravity
	 * @param dissolve
	 * @param srcImgPath
	 * @return
	 */
	public static String waterMark(String waterImgPath, String gravity, int dissolve, String srcImgPath) {
		try {
			return engine.waterMarks(waterImgPath, gravity, dissolve, srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	/**
	 * 给多张图片加水印，会创建新的水印文件
	 * @param waterImgPath
	 * @param gravity
	 * @param dissolve
	 * @param srcImgPath
	 * @return
	 */
	public static String[] waterMarks(String waterImgPath, String gravity, int dissolve, String... srcImgPath) {
		try {
			return engine.waterMarks(waterImgPath, gravity, dissolve, srcImgPath);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String cropImage(int dw, int dh, String srcPaths) {
		try {
			return engine.cropImage(dw, dh, srcPaths)[0];
		} catch (Exception e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String[] cropImage(int dw, int dh, String... srcPaths) {
		try {
			return engine.cropImage(dw, dh, srcPaths);
		} catch (Exception e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String cutImage(int x, int y, int x2, int y2, String srcPath) {
		try {
			return engine.cutImage(x, y, x2, y2, srcPath)[0];
		} catch (Exception e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String rotate(double angle, String srcImgPath) {
		try {
			return engine.rotate(angle, srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}

	public static String[] rotate(double angle, String... srcImgPath) {
		try {
			return engine.rotate(angle, srcImgPath);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String concatenateImage(int width, int height, String... srcPaths) {
		try {
			return engine.concatenateImage(width, height, srcPaths);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String gray(String srcImgPath) {
		try {
			return engine.gray(srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String[] gray(String... srcImgPath) {
		try {
			return engine.gray(srcImgPath);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String showImageInfo(String img) {
		try {
			return engine.showImageInfo(img);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static int getImageWidth(String img) {
		try {
			return engine.getImageWidth(img);
		} catch (InfoException e) {
			logException(log, e);
			return -1;
		}
	}
	
	public static int getImageHeight(String img) {
		try {
			return engine.getImageHeight(img);
		} catch (InfoException e) {
			logException(log, e);
			return -1;
		}
	}
	
	public static String getGeometry(String img) {
		try {
			return engine.getGeometry(img);
		} catch (InfoException e) {
			logException(log, e);
			return null;
		}
	}
	
	public static String getImageFormat(String img) {
		try {
			return engine.getImageFormat(img);
		} catch (InfoException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * @param string
	 * @return
	 */
	public static String[] compress(String... srcImgPath) {
		try {
			return engine.compress(srcImgPath);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static String compress(String srcImgPath) {
		try {
			return engine.compress(srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static String compress(double quality, String srcImgPath) {
		try {
			return engine.compress(quality, srcImgPath)[0];
		} catch (IOException | InterruptedException | IM4JavaException e) {
			logException(log, e);
			return null;
		}
	}
	
	/**
	 * 获得iphone手机竖屏照片上传时的修正角度
	 * @param imgFile
	 * @return
	 * @author liubin
	 * @createtime 2017年3月31日下午5:55:43
	 * @return int
	 */
	public static int getRotateAngle(File imgFile) {
	    int angel = 0;
	    Metadata metadata;
	    try {
	    	metadata = JpegMetadataReader.readMetadata(imgFile);
	        Directory directory = metadata.getDirectory(ExifIFD0Directory.class);
	        if (directory != null) {// 是否包含Exif中方向信息
	        	if (directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
	        		int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);// 原图片的方向信息 
	        		switch(orientation) {
					case 3:
						angel = 180;// 3旋转180
						break;
					case 6:
						angel = 90;// 6旋转90
						break;
					case 8:
						angel = 270;// 8旋转90
						break;
					default:
						angel = 0;
						break;
					}
		        }
	        }
	    } catch (IOException e) {
		   e.printStackTrace();
	    } catch (JpegProcessingException e) {
		   e.printStackTrace();
	    } catch (MetadataException e) {
		   e.printStackTrace();
	    }
	    return angel;
	}
}