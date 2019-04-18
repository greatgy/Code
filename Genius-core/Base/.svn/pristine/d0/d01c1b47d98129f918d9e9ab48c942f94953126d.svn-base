package com.genius.core.base.image;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.ImageCommand;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.im4java.core.MontageCmd;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.utils.FilenamePatternResolver;

/**
 * @author Architect.bian
 * 
 */
public class IM4GraphicsMagickEngine implements ImageEngine {

	public IM4GraphicsMagickEngine() {
		System.setProperty("im4java.useGM", String.valueOf(true));
	}

	public static String winDirSeparator = "\\";
	public static String linuxDirSeparator = "/";
	private static final String formatRotateSuffix = "r_%s";
	private static final String formatWidthHeight = "%d_%d";
	private static final String formatCutSuffix = "%d_%d_%d_%d";
	private static final String formatTextWaterSuffix = "wt";
	private static final String formatConcatenateSuffix = "c%d";
	private static final String formatGraySuffix = "gray";
	private static final String formatCompressSuffix = "o";
	private static final String fileSuffixSperator = "_";
	public static String formatWaterSuffix = "w";
	public static double imgquality = 80;

	@Override
	public String[] resizeImages(int width, int height, String... files) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.resize(width, height);
		op.strip();
		op.quality(imgquality);
		op.addImage();
		op.addImage();

		ConvertCmd cmd = new ConvertCmd(true);
		String[] result = runCommand(op, cmd, files, String.format(formatWidthHeight, width, height));
		return result;
	}

	@Override
	public String[] resizeImages(int width, int height, double quality, String... files) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.resize(width, height);
		op.strip();
		op.quality(quality);
		op.addImage();
		op.addImage();

		ConvertCmd cmd = new ConvertCmd(true);
		String[] result = runCommand(op, cmd, files, String.format(formatWidthHeight, width, height));
		return result;
	}

	/**
	 * no create new file
	 * 
	 * @param imgnature
	 * @param imgwater
	 * @param southeast
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String waterMarks(String srcImgPath, String waterImgPath, String gravity) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.gravity(gravity);
		op.dissolve(100);
		op.addImage(waterImgPath);
		op.addImage();
		op.addImage();
		CompositeCmd cmd = new CompositeCmd(true);
		return runCommand(op, cmd, new String[] { srcImgPath }, "")[0];
	}

	/**
	 * @param waterImgPath
	 * @param gravity
	 * @param dissolve
	 * @param srcImgPath
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] waterMarks(String waterImgPath, String gravity, int dissolve, String... srcImgPath) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.gravity(gravity);
		op.dissolve(dissolve);
		op.addImage(waterImgPath);
		op.addImage();
		op.addImage();
		CompositeCmd cmd = new CompositeCmd(true);
		return runCommand(op, cmd, srcImgPath, formatWaterSuffix);
	}

	/**
	 * @param waterImgPath
	 * @param gravity
	 * @param dissolve
	 * @param srcImgPath
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] waterTextMarks(String text, int size, String color, String gravity, int dissolve, String... srcImgPath) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.font("Arial").gravity(gravity).pointsize(size).fill(color).draw("text 5,5 " + text);
		op.addImage();
		op.addImage();
		ConvertCmd cmd = new ConvertCmd(true);
		return runCommand(op, cmd, srcImgPath, formatTextWaterSuffix);
	}

	/**
	 * 图片旋转
	 * 
	 * @param srcImagePath
	 * @param destImagePath
	 * @param angle
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] rotate(double angle, String... srcImgPath) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.rotate(angle);
		op.addImage();
		op.addImage();
		ConvertCmd cmd = new ConvertCmd(true);
		return runCommand(op, cmd, srcImgPath, String.format(formatRotateSuffix, angle));
	}

	/**
	 * 图片裁剪,等比压缩
	 * 
	 * @param srcPath
	 *            源图片
	 * @param dw
	 *            处理后图片宽
	 * @param dh
	 *            处理后图片高
	 * @return
	 * @throws Exception
	 */
	public String[] cropImage(int dw, int dh, String... srcPaths) throws Exception {
		int len = srcPaths.length;
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			String img = srcPaths[i];
			int sw = getImageWidth(img);
			int sh = getImageHeight(img);
			if (dw <= 0 || dh <= 0) {
				return null;
			}

			IMOperation op = new IMOperation();
			op.strip();
			op.quality(imgquality);
			op.addImage();

			// 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
			if ((sw <= dw) && (sh <= dh)) {
				op.resize(sw, sh);
			}

			// 如果源图宽度小于目标宽度，并且源图高度大于目标高度
			if ((sw <= dw) && (sh > dh)) {
				op.resize(sw, sh); // 压缩图片
				op.append().crop(sw, dh, 0, (sh - dh) / 2); // 切割图片
			}

			// 如果源宽度大于目标宽度，并且源高度小于目标高度
			if ((sw > dw) && (sh <= dh)) {
				op.resize(sw, sh);
				op.append().crop(dw, sh, (sw - dw) / 2, 0);
			}

			// 如果源图宽、高都大于目标宽高
			if (sw > dw && sh > dh) {
				float ratiow = (float) dw / sw; // 宽度压缩比
				float ratioh = (float) dh / sh; // 高度压缩比

				// 宽度压缩比小（等）于高度压缩比（是宽小于高的图片）
				if (ratiow >= ratioh) {
					int ch = (int) (ratiow * sh); // 压缩后的图片高度
					op.resize(dw, ch); // 按目标宽度压缩图片
					op.append().crop(dw, dh, 0, (ch > dh) ? ((ch - dh) / 2) : 0); // 根据高度居中切割压缩后的图片
				} else { // （宽大于高的图片）
					int cw = (int) (ratioh * sw); // 压缩后的图片宽度
					op.resize(cw, dh); // 按计算的宽度进行压缩
					op.append().crop(dw, dh, (cw > dw) ? ((cw - dw) / 2) : 0, 0); // 根据宽度居中切割压缩后的图片
				}
			}

			op.addImage();
			ConvertCmd cmd = new ConvertCmd(true);
			result[i] = runCommand(op, cmd, new String[] { img }, String.format(formatWidthHeight, dw, dh))[0];
		}
		return result;
	}

	/**
	 * 根据坐标裁剪图片
	 * 
	 * @param srcPath
	 *            要裁剪图片的路径
	 * @param x
	 *            起始横坐标
	 * @param y
	 *            起始纵坐标
	 * @param x2
	 *            结束横坐标
	 * @param y2
	 *            结束纵坐标
	 * @return
	 */
	public String[] cutImage(int x, int y, int x2, int y2, String... srcPaths) throws Exception {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.addImage();
		/**
		 * width：裁剪的宽度 height：裁剪的高度 x：裁剪的横坐标 y：裁剪的挫坐标
		 */
		int width = x2 - x;
		int height = y2 - y;
		op.crop(width, height, x, y);

		op.addImage();

		ConvertCmd cmd = new ConvertCmd();
		return runCommand(op, cmd, srcPaths, String.format(formatCutSuffix, x, y, x2, y2));
	}

	@SuppressWarnings("unused")
	public String concatenateImage(int width, int height, String... srcPaths) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.mode("concatenate").tile(1, srcPaths.length);
		for (String src : srcPaths) {
			op.addImage(String.format("[%dx%d+0+0]", width, height));
		}
		op.addImage();

		MontageCmd cmd = new MontageCmd();
		// CompositeCmd cmd = new CompositeCmd();
		String imgdest = getDestinationImagePath(srcPaths[0], String.format(formatConcatenateSuffix, srcPaths.length));
		cmd.run(op, (Object[]) ArrayUtils.add(srcPaths, imgdest));
		return imgdest;
	}

	/**
	 * 图片信息
	 * 
	 * @param imagePath
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String showImageInfo(String img) throws IOException, InterruptedException, IM4JavaException {
		String line = null;
		IMOperation op = new IMOperation();
		op.format("Width:%w Height:%h Path:%d Name:%f Size%b %[EXIF:DateTimeOriginal]");
		op.addImage(1);
		IdentifyCmd identifyCmd = new IdentifyCmd();
		ArrayListOutputConsumer output = new ArrayListOutputConsumer();
		identifyCmd.setOutputConsumer(output);
		// log.info("==ImageMagic==/n" +
		// "Op=" + op.toString() +
		// ";Cmd=" + identifyCmd.toString());
		identifyCmd.run(op, img);
		ArrayList<String> cmdOutput = output.getOutput();
		line = cmdOutput.get(0);
		return line;
	}

	public int getImageWidth(String img) throws InfoException {
		Info info = new Info(img, true);
		return info.getImageWidth();
	}

	public int getImageHeight(String img) throws InfoException {
		Info info = new Info(img, true);
		return info.getImageHeight();
	}

	public String getGeometry(String img) throws InfoException {
		Info info = new Info(img, true);
		return info.getImageGeometry();
	}

	public String getImageFormat(String img) throws InfoException {
		Info info = new Info(img, true);
		return info.getImageFormat();
	}

	/**
	 * @param op
	 * @param cmd
	 * @param files
	 * @param destPathSuffix
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private String[] runCommand(IMOperation op, ImageCommand cmd, String[] files, String destPathSuffix) throws IOException, InterruptedException, IM4JavaException {
		int len = files.length;
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			String imgsrc = files[i];
			String imgdest = getDestinationImagePath(imgsrc, destPathSuffix);
			result[i] = imgdest;
			cmd.run(op, imgsrc, imgdest);
		}
		return result;
	}

	/**
	 * @param imgsrc
	 * @param width
	 * @param height
	 * @return
	 */
	private String getDestinationImagePath(String imgsrc, String destPathSuffix) {
		if (StringUtils.isNotEmpty(destPathSuffix)) {
			FilenamePatternResolver resolver = new FilenamePatternResolver(String.format("%%P/%%f" + fileSuffixSperator + "%1s.%%e", destPathSuffix));
			String imgdes = resolver.createName(imgsrc).replace(winDirSeparator, linuxDirSeparator);
			return imgdes;
		} else {
			return imgsrc;
		}
	}

	/**
	 * @param srcImgPath
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] gray(String... srcPaths) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.addImage();
		op.colorspace("GRAY");
		op.addImage();

		ConvertCmd cmd = new ConvertCmd();
		return runCommand(op, cmd, srcPaths, formatGraySuffix);
	}

	/**
	 * @param img
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] compress(String... srcPaths) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		op.quality(imgquality);
		op.addImage();
		op.addImage();

		ConvertCmd cmd = new ConvertCmd();
		return runCommand(op, cmd, srcPaths, formatCompressSuffix);
	}

	/**
	 * @param img
	 * @return
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String[] compress(double quality, String... srcPaths) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.strip();
		if (quality < 100) {
			op.quality(quality);
		}
		op.addImage();
		op.addImage();

		ConvertCmd cmd = new ConvertCmd();
		return runCommand(op, cmd, srcPaths, formatCompressSuffix);
	}
}
