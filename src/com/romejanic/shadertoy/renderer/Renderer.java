package com.romejanic.shadertoy.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.romejanic.shadertoy.renderer.mesh.Quad;
import com.romejanic.shadertoy.shader.ShaderManager;
import com.romejanic.shadertoy.utils.IO;

public class Renderer {

	private final ShaderManager shaders = new ShaderManager();
	
	private Quad quad;
	private int testProgram;
	
	public void init() throws Exception {
		glClearColor(0.4f, 0.6f, 0.9f, 1f);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glDepthFunc(GL_LEQUAL);
		glCullFace(GL_BACK);
		
		this.quad = new Quad();
		this.shaders.init();
		
		String src = IO.readFully("shaders/test_source.txt");
		this.testProgram = this.shaders.getProgramForSourceCode(src);
	}
	
	public void render(int w, int h) {
		glViewport(0, 0, w, h);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glUseProgram(this.testProgram);
		this.quad.bind();
		this.quad.draw();
		this.quad.unbind();
		glUseProgram(0);
	}
	
	public void destroy() {
		this.quad.delete();
		this.shaders.delete();
		glDeleteProgram(this.testProgram);
	}
	
}