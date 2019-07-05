package com.romejanic.shadertoy.renderer.mesh;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Quad {

	private static final float[] vertices = {
		-1f, -1f,
		 1f, -1f,
		-1f,  1f,
		
		-1f,  1f,
		 1f, -1f,
		 1f,  1f
	};
	
	private final int vao;
	private final int vbo;
	
	public Quad() {
		this.vao = glGenVertexArrays();
		this.vbo = glGenBuffers();
		
		glBindVertexArray(this.vao);
		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void draw() {
		glDrawArrays(GL_TRIANGLES, 0, vertices.length / 2);
	}
	
	public void bind() {
		glBindVertexArray(this.vao);
		glEnableVertexAttribArray(0);
	}
	
	public void unbind() {
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
	public void delete() {
		glDeleteBuffers(this.vbo);
		glDeleteVertexArrays(this.vao);
	}
	
}