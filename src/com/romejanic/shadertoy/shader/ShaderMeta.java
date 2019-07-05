package com.romejanic.shadertoy.shader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ShaderMeta {

	private String id;
	private long date;
	private int views;
	private String name;
	private String author;
	private String description;
	private int likes;
	private int publishFlags;
	private int flags;
	private String[] tags;
	private boolean hasLiked;
	
	public void printFull(PrintStream out) {
		out.println("id: " + this.id);
		out.println("date: " + this.date);
		out.println("views: " + this.views);
		out.println("likes: " + this.likes);
		out.println("name: " + this.name);
		out.println("description: " + this.description);
		out.println("author: " + this.author);
		out.println("publishFlags: " + this.publishFlags);
		out.println("flags: " + this.flags);
		out.println("hasLiked: " + this.hasLiked);
		out.print("tags: ");
		for(int i = 0; i < tags.length; i++) {
			out.print(tags[i]);
			if(i < tags.length - 1) {
				out.print(", ");
			}
		}
	}

	public static ShaderMeta parse(InputStream in) {
		JsonParser parser = new JsonParser();
		JsonObject base   = ((JsonObject) parser.parse(new InputStreamReader(in))).getAsJsonObject("Shader");
		
		ShaderMeta meta = new ShaderMeta();
		
		// info
		JsonObject info = base.getAsJsonObject("info");
		meta.id = info.get("id").getAsString();
		meta.date = info.get("date").getAsLong();
		meta.views = info.get("viewed").getAsInt();
		meta.name = info.get("name").getAsString();
		meta.author = info.get("username").getAsString();
		meta.description = info.get("description").getAsString();
		meta.likes = info.get("likes").getAsInt();
		meta.publishFlags = info.get("published").getAsInt();
		meta.flags = info.get("flags").getAsInt();
		meta.tags = toStringArray(info.getAsJsonArray("tags"));
		meta.hasLiked = info.get("hasliked").getAsInt() == 1;
		
		// return object
		return meta;
	}
	
	private static String[] toStringArray(JsonArray array) {
		String[] arr = new String[array.size()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = array.get(i).getAsString();
		}
		return arr;
	}
	
}