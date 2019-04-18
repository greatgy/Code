package com.genius.captcha.component.image.backgroundgenerator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;

import com.genius.captcha.CaptchaException;

/**
 * <p>Stream reader background generator that return a random image from the ones found in the directory </p>
 * <p>You can place images in a jar file</p>
 * 
 * @author Administrator
 *
 */
public class StreamReaderRandomBackgroundGenerator extends AbstractBackgroundGenerator {

	private List<BufferedImage > images = new ArrayList<>();

	public StreamReaderRandomBackgroundGenerator(Integer width, Integer height, String resourcePath) {
        super(width, height);

        List<InputStream> streams = getAllStreams(resourcePath);
        if (streams != null) {
            for (InputStream item : streams) {
                BufferedImage out = getImage(item);
                if (out != null) {
                    images.add(images.size(), out);
                }
            }
            if (images.size() != 0) {
                for (int i = 0; i < images.size(); i++) {
                    BufferedImage bufferedImage = (BufferedImage) images.get(i);
                    images.set(i, tile(bufferedImage));
                }
            } else {
                throw new CaptchaException("resourcePath directory is valid, but " + "does not contains or read any image (jpg) files");
            }
        } else {
        	throw new CaptchaException("resourcePath directory is invalid or does not contains any image (jpg) files");
		}
    }

	/**
	 * 获取jar文件里的resourcePath下所有图片文件的流
	 * @param resourcePath
	 * @return
	 */
    private List<InputStream> getAllStreams(String resourcePath) {
    	List<InputStream> list = new ArrayList<>();
    	URL resource = this.getClass().getResource(resourcePath);
    	String path = resource.getPath();
    	if (path.contains("!")) {
			path = path.substring(0, path.indexOf("!")).replaceAll("\\\\", "/").replace("file:", "");
		} else {
			path = path.replaceAll("\\\\", "/").replace("file:", "");
		}
    	if (path.endsWith(".jar")) {
    		try {
    			JarFile jar = new JarFile(path);
    			Enumeration<JarEntry> entries = jar.entries();
    			while(entries.hasMoreElements()) {
    				String name = "/" + entries.nextElement().getName();
    				if (name.startsWith(resourcePath) && name.endsWith(".jpg")) {
    					list.add(this.getClass().getResourceAsStream(name));
    				}
    			}
    			jar.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return list;
		}
    	return null;
	}

    /**
     * 通过stream获取bufferimage，并关闭流
     * @param stream
     * @return
     */
    private static BufferedImage getImage(InputStream stream) {
        try {
            BufferedImage out =ImageIO.read(stream);
            stream.close();
            return out;
        } catch (IOException e) {
            throw new CaptchaException("Unknown error during file reading ", e);
        }
    }

    private BufferedImage tile(BufferedImage tileImage) {
        BufferedImage image = new BufferedImage(getImageWidth(), getImageHeight(), tileImage.getType());
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        int NumberX = (getImageWidth() / tileImage.getWidth());
        int NumberY = (getImageHeight() / tileImage.getHeight());
        for (int k = 0; k <= NumberY; k++) {
            for (int l = 0; l <= NumberX; l++) {
                g2.drawImage(tileImage, l * tileImage.getWidth(), k * tileImage.getHeight(), Math.min(tileImage.getWidth(), getImageWidth()), Math.min(tileImage.getHeight(), getImageHeight()), null);
            }
        }
        g2.dispose();
        return image;
    }

    /**
     * Generates a backround image on wich text will be paste. Implementations must take into account the imageHeigt and
     * imageWidth.
     *
     * @return the background image
     */
    public BufferedImage getBackground() {
        return (BufferedImage) images.get(myRandom.nextInt(images.size()));
    }

}
