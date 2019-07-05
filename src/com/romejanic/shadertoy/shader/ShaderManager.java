package com.romejanic.shadertoy.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.InputStream;
import java.util.HashMap;

import com.romejanic.shadertoy.utils.IO;

public class ShaderManager {

	private int vertexShader;
	private String fragmentShaderTemplate;

	private final HashMap<String, Integer> programs = new HashMap<String, Integer>();

	public void init() throws Exception {
		this.vertexShader = compileShader(IO.getResource("shaders/shader.vs"), GL_VERTEX_SHADER);
		this.fragmentShaderTemplate = IO.readFully("shaders/shader.fs");
	}

	public int getProgramForSourceCode(String source) {
		int program = glCreateProgram();
		glAttachShader(program, this.vertexShader);

		String samplers = "";
		String defines = "";
		String fragSource = String.format(this.fragmentShaderTemplate, defines, samplers, source);
		int fragShader;
		try {
			fragShader = compileShader(fragSource, GL_FRAGMENT_SHADER);
			glAttachShader(program, fragShader);
		} catch(Exception e) {
			System.err.println("Failed to create program for source code!");
			e.printStackTrace(System.err);
			return 0;
		}
		
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
			String infoLog = glGetProgramInfoLog(program);
			System.err.println("Failed to link program!\n" + infoLog);
		}
		
		glDetachShader(program, this.vertexShader);
		glDetachShader(program, fragShader);
		glDeleteShader(fragShader);
		
		return program;
	}

	public void delete() {
		glDeleteShader(this.vertexShader);
		for(Integer program : programs.values()) {
			glDeleteProgram(program);
		}
		programs.clear();
	}

	private int compileShader(String source, int type) throws Exception {
		int shader = glCreateShader(type);
		try {
			glShaderSource(shader, source);
			glCompileShader(shader);
			if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
				String infoLog = glGetShaderInfoLog(shader);
				throw new RuntimeException("Failed to compile shader!\n" + infoLog);
			}
			return shader;
		} catch(Exception e) {
			glDeleteShader(shader);
			throw e;
		}
	}

	private int compileShader(InputStream source, int type) throws Exception {
		return compileShader(IO.readFully(source), type);
	}

}