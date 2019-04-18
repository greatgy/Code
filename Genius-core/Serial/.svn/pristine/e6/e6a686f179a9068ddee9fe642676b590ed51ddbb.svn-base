package com.genius.core.serial.thirdpart;

import java.io.IOException;

import org.junit.Test;
import org.xerial.snappy.Snappy;

/**
 * @author Architect.bian
 * 
 */
public class SnappyTest {

	@Test
	public void CompressTest() throws IOException {
		String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of Snappy, a fast compresser/decompresser.";
		System.out.println("before Compress size:" + input.getBytes("UTF-8").length);
		byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
		System.out.println("After Compressed size:" + compressed.length);
		byte[] uncompressed = Snappy.uncompress(compressed);
		System.out.println("After Uncompress size:" + uncompressed.length);
		String result = new String(uncompressed, "UTF-8");
		System.out.println(result);
	}
}
