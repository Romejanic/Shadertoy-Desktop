package com.romejanic.shadertoy.renderer;

import static org.lwjgl.opengl.GL11.*;

import com.romejanic.shadertoy.renderer.mesh.Quad;

public class Renderer {

	private Quad quad;
	
	public void init() throws Exception {
		glClearColor(0.4f, 0.6f, 0.9f, 1f);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glDepthFunc(GL_LEQUAL);
		glCullFace(GL_BACK);
		
		this.quad = new Quad();
	}
	
	public void render(int w, int h) {
		glViewport(0, 0, w, h);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		this.quad.bind();
		this.quad.draw();
		this.quad.unbind();
	}
	
	public void destroy() {
		this.quad.delete();
	}
	
}