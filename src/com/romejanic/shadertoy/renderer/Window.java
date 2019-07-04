package com.romejanic.shadertoy.renderer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {

	private static long handle  = NULL;
	private static int width    = 1024;
	private static int height   = 640;
	private static String title = "GLFW Window";
	
	public static void create(int _width, int _height, String _title) {
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit()) {
			throw new RuntimeException("Failed to initialize GLFW!");
		}
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		handle = glfwCreateWindow(_width, _height, _title, NULL, NULL);
		if(handle == NULL) {
			throw new RuntimeException("Failed to create window!");
		}
		GLFWVidMode vm = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(handle, (vm.width()-_width)/2, (vm.height()-_height)/2);
		glfwSetFramebufferSizeCallback(handle, (win, w, h) -> {
			Window.width = w;
			Window.height = h;
		});
		glfwMakeContextCurrent(handle);
		glfwShowWindow(handle);
		GL.createCapabilities();
		
		// get current framebuffer size
		IntBuffer wb = BufferUtils.createIntBuffer(1);
		IntBuffer hb = BufferUtils.createIntBuffer(1);
		glfwGetFramebufferSize(handle, wb, hb);
		Window.width = wb.get(0);
		Window.height = hb.get(0);
		Window.title = _title;
	}
	
	public static void update() {
		if(handle != NULL) {
			glfwSwapBuffers(handle);
		}
		glfwPollEvents();
	}
	
	public static void destroy() {
		if(handle != NULL) {
			Callbacks.glfwFreeCallbacks(handle);
			glfwDestroyWindow(handle);
		}
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static boolean shouldClose() {
		return glfwWindowShouldClose(handle);
	}
	
}