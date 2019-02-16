package com.stargazerproject.util;

import com.google.common.base.Optional;

import java.io.*;

public class CloneUtil {

	public static Object deepClone(Optional<Object> object) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(object.get());
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return oi.readObject();
		} catch (OptionalDataException e) {
			throw new NullPointerException();
		} catch (ClassNotFoundException e) {
			throw new NullPointerException();
		} catch (IOException e) {
			throw new NullPointerException();
		}
	}

	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(object);
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return oi.readObject();
		} catch (OptionalDataException e) {
			throw new NullPointerException();
		} catch (ClassNotFoundException e) {
			throw new NullPointerException();
		} catch (IOException e) {
			throw new NullPointerException(e.getMessage());
		}
	}
}
