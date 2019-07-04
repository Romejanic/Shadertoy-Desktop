package com.romejanic.shadertoy.renderer;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

	public void init() throws Exception {
		glClearColor(0.4f, 0.6f, 0.9f, 1f);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glDepthFunc(GL_LEQUAL);
		glCullFace(GL_BACK);
	}
	
	public void render(int w, int h) {
		glViewport(0, 0, w, h);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void destroy() {
		
	}
	
}