package com.romejanic.shadertoy.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class IO {

	public static String getFullPath(String resource) {
		return "/res" + (resource.startsWith("/") ? "" : "/") + resource;
	}
	
	public static InputStream getResource(String resource) throws FileNotFoundException {
		String fullPath = getFullPath(resource);
		InputStream  is = IO.class.getResourceAsStream(fullPath);
		if(is == null) {
			throw new FileNotFoundException(fullPath);
		}
		return is;
	}
	
	public static String readFully(String resource) throws Exception {
		return readFully(getResource(resource));
	}
	
	public static String readFully(InputStream stream) throws Exception {
		StringBuilder s = new StringBuilder();
		
		byte[] b = new byte[1024];
		int i;
		while((i = stream.read(b)) != -1) {
			s.append(new String(b, 0, i));
		}
		
		stream.close();
		return s.toString();
	}
	
}