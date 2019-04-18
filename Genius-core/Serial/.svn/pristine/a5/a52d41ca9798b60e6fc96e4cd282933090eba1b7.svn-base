package com.genius.core.serial.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

import de.javakaffee.kryoserializers.jodatime.JodaDateTimeSerializer;

/**
 * @author Architect.bian
 * 
 */
public class KryoSerialEngine implements SerialEngine {

	private static final int maxBufferSize = 1024;
//	private static final String mode_rw = "rw";
//	private static final String mode_r = "r";
	private static Kryo kryo = new Kryo();
	
	static {
		kryo.register(ISOChronology.class, new FieldSerializer<ISOChronology>(kryo, ISOChronology.class){
			public ISOChronology create(Kryo kryo, Input input, Class<ISOChronology> type) {
				return ISOChronology.getInstance();
			}
		});
		kryo.register(GregorianChronology.class, new FieldSerializer<GregorianChronology>(kryo, GregorianChronology.class){
			public GregorianChronology create(Kryo kryo, Input input, Class<GregorianChronology> type) {
				return GregorianChronology.getInstanceUTC();
			}
		});
		kryo.register(DateTime.class, new JodaDateTimeSerializer());
		kryo.register(List.class);
		kryo.register(ArrayList.class);
		kryo.register(LinkedList.class);
		
		//下面代码破坏了松散耦合规则
//		kryo.register(DataBox.class);
	}

	@Override
	public boolean serialize(Object obj, String fullpath, boolean compress) throws IOException {
		Output output = null;
		try {
//			RandomAccessFile file = new RandomAccessFile(fullpath, mode_rw);
//			OutputStream outputStream = new FileOutputStream(file.getFD());
			File file = new File(fullpath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream outputStream = new FileOutputStream(file);
//			if (compress) {
//				outputStream = new SnappyOutputStream(outputStream);
//			}
			output = new Output(outputStream, maxBufferSize);
			kryo.writeClassAndObject(output, obj);
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	@Override
	public Object deserialize(String fullpath, boolean uncompress) throws IOException {
		Input input = null;
		try {
//			RandomAccessFile file = new RandomAccessFile(fullpath, mode_r);
//			InputStream inputStream = new FileInputStream(file.getFD());
			InputStream inputStream = new FileInputStream(fullpath);
//			if (uncompress) {
//				inputStream = new SnappyInputStream(inputStream);
//			}
			input = new Input(inputStream, maxBufferSize);
			return kryo.readClassAndObject(input);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return null;
		} catch (KryoException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.core.serial.SerialEngine#getByte(java.lang.Object, boolean)
	 */
	@Override
	public byte[] serialize(Object obj, boolean compress) throws Exception {
		Output output = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			output = new Output(os);
			kryo.writeClassAndObject(output, obj);
			output.flush();
//			if (compress) {
//				return Snappy.compress(os.toByteArray());
//			} else {
			return os.toByteArray();
//			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.genius.core.serial.SerialEngine#deserialize(byte[], boolean)
	 */
	@Override
	public Object deserialize(byte[] bytes, boolean uncompress) throws Exception {
		Input input = null;
		try {
			if (bytes.length == 0) {
				return null;
			}
//			if (uncompress) {
//				bytes = Snappy.uncompress(bytes);
//			}
			ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			input = new Input(is);
			return kryo.readClassAndObject(input);
//		} catch (FileNotFoundException e) {
//			//e.printStackTrace();
//			return null;
		} catch (KryoException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

}
