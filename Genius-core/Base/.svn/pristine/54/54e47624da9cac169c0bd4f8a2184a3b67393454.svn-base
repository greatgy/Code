package com.genius.core.base.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.BaseUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeUtil extends BaseUtil {

	private static final String UTF_8 = "UTF-8";

	private static Logger log = LoggerFactory.getLogger(BarCodeUtil.class);

	private static final String defaultFormat = "png";
	
	/**
	 * 将二维码生成到文件
	 * @param text
	 * @param width
	 * @param height
	 * @param path
	 * @return
	 */
	public static boolean createQRCodeToFile(String text, int width, int height, String path) {
		return createQRCodeToFile(text, width, height, path, defaultFormat);
	}
	
	/**
	 * 将二维码生成到文件并指定生成格式
	 * @param text
	 * @param width
	 * @param height
	 * @param path
	 * @param format
	 * @return
	 */
	public static boolean createQRCodeToFile(String text, int width, int height, String path, String format) {
		BitMatrix bitMatrix = createQRCode(text, width, height);
		if (bitMatrix != null) {
			try {
				MatrixToImageWriter.writeToPath(bitMatrix, format, FileSystems.getDefault().getPath(path)); // 输出图像到文件
				return true;
			} catch (IOException e) {
				logException(log, e);
			}
		}
		return false;
	}
	
	public static boolean createQRCodeToStream(String text, int width, int height, OutputStream stream) {
		return createQRCodeToStream(text, width, height, stream, defaultFormat);
	}
	
	public static boolean createQRCodeToStream(String text, int width, int height, OutputStream stream, String format) {
		BitMatrix bitMatrix = createQRCode(text, width, height);
		if (bitMatrix != null) {
			try {
				MatrixToImageWriter.writeToStream(bitMatrix, format, stream); //输出图像到流
				return true;
			} catch (IOException e) {
				logException(log, e);
			}
		}
		return false;
	}
	
	protected static BitMatrix createQRCode(String text, int width, int height) {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, UTF_8);
		hints.put(EncodeHintType.MARGIN, 0); /*default = 4*/
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			return bitMatrix;
		} catch (WriterException e) {
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 对二维码图片解码
	 * @param image ImageIO.read(filepath)
	 * @return
	 */
	public static String parseBarCodeFromStream(BufferedImage image) {
		LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, UTF_8);
        Result result;
		try {
			result = new MultiFormatReader().decode(binaryBitmap, hints); //对图像进行解码
			return result.getText();
		} catch (NotFoundException e) {
			logException(log, e);
		}
		return null;
	}
}
