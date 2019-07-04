package com.romejanic.shadertoy;

import com.romejanic.shadertoy.renderer.Renderer;
import com.romejanic.shadertoy.renderer.Window;

public class Shadertoy {

	private final Renderer renderer = new Renderer();
	
	private void init() throws Throwable {
		Window.create(1280, 720, "Shadertoy Desktop");
		this.renderer.init();
	}
	
	private void update() {
		this.renderer.render(Window.getWidth(), Window.getHeight());
		Window.update();
	}
	
	private void destroy() {
		this.renderer.destroy();
		Window.destroy();
	}
	
	private boolean shouldClose() {
		return Window.shouldClose();
	}
	
	public static void main(String[] args) {
		final Shadertoy app = new Shadertoy();
		try {
			app.init();
			while(!app.shouldClose()) {
				app.update();
			}
			app.destroy();
		} catch(Throwable e) {
			System.err.println("!! CRASHED !!");
			System.err.print("Caused by ");
			e.printStackTrace(System.err);
			//
			app.destroy();
		}
	}
	
}