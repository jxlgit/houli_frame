package com.houli.system.shiro.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisSerialize<T> implements RedisSerializer<T> {
	private static Logger logger = LoggerFactory.getLogger(RedisSerialize.class);
	
	@Override
	public byte[] serialize(Object object) throws SerializationException {
		byte[] result = null;

		if (object == null) {
			return new byte[0];
		}
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
			try {
				if (!(object instanceof Serializable)) {
					throw new IllegalArgumentException(
							RedisSerialize.class.getSimpleName() + " requires a Serializable payload "
									+ "but received an object of type [" + object.getClass().getName() + "]");
				}
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
				result = byteStream.toByteArray();
			} catch (Throwable ex) {
				throw new Exception("Failed to serialize", ex);
			}
		} catch (Exception ex) {
			logger.error("Failed to serialize",ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		T result = null;

		if (isEmpty(bytes)) {
			return null;
		}

		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
				try {
					result = (T) objectInputStream.readObject();
				} catch (ClassNotFoundException ex) {
					throw new Exception("Failed to deserialize object type", ex);
				}
			} catch (Throwable ex) {
				throw new Exception("Failed to deserialize", ex);
			}
		} catch (Exception e) {
			logger.error("Failed to deserialize",e);
		}
		return result;
	}
	
	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

}
