import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import gameEnginge.Field;
import input.KeyboardInput;
import input.MouseInput;

public class Driver implements Runnable{

	private Thread thread;
	private boolean running = false;

	public long windows;

	private GLFWKeyCallback keyCallback;
	private GLFWCursorPosCallback cursorCallback;

	private Field field;
	private Game game;

	public void start(){
		thread = new Thread(this, "Game");
		thread.start();
	}

	private void init(){
		game = new Game();
		running = true;
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

		game.init();
	}

	public void run(){
		init();
		while(running){
			update();
			render();

			if(glfwWindowShouldClose(windows)){
				running = false;
			}
		}
	}

	private void update(){
		glfwPollEvents();
		if(KeyboardInput.isKeyDown(GLFW_KEY_SPACE)){
			System.out.println("Space is pressed!   ");

		}
	}

	private void render(){
		glfwSwapBuffers(windows);

		game.draw();
	}

	public static void main(String[] args) {
		new Driver().start();

	}

}
