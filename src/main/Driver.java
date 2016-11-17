package main;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import input.KeyboardInput;
import input.MouseInput;
import logic.Game;

public class Driver implements Runnable {

	private Thread thread;
	private boolean running = false;

	public long windows;
	public boolean flag;

	@SuppressWarnings("unused")
	private GLFWKeyCallback keyCallback;
	@SuppressWarnings("unused")
	private GLFWCursorPosCallback cursorCallback;

	public GameGraphic game;

	public void start(){
		thread = new Thread(this, "Game");
		thread.start();
	}

	private void init(){
		game = new GameGraphic(this);
		running = true;
		flag = false;
		if(!glfwInit()){
			System.err.println("Initialisierung fehlgeschlagen!");
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		windows = glfwCreateWindow(game.getWidth(), game.getHeight(), "Monstergame", NULL, NULL);

		if(windows == NULL){
			System.err.println("Fenster konnte nicht erstellt werden!");
		}

		glfwSetKeyCallback(windows, keyCallback = new KeyboardInput());
		glfwSetCursorPosCallback(windows, cursorCallback = new MouseInput());
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwMakeContextCurrent(windows);
		glfwShowWindow(windows);
		GL.createCapabilities();
		glViewport(0, 0, game.getWidth(), game.getHeight());

		game.init();
	}

	public void run(){
		init();
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 30.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if(delta >= 1.0){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(updates + " UPS, " + frames + " FPS");
			}
			if(glfwWindowShouldClose(windows)){
				running = false;
			}
		}
	}

	private void update(){
		glfwPollEvents();

		game.update();
	}

	private void render(){
		glfwSwapBuffers(windows);

		game.draw();
	}

	public static void main(String[] args) {
		new Driver().start();
	}
}
