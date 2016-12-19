package main;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import logic.Game;
import logic.graph.Point;
import view.Renderer;
import view.controller.Movement;
import view.controller.User;
import view.graphicEngine.ShaderManager;
import view.input.KeyboardInput;
import view.input.MouseInput;

public class Driver {

	private boolean running = false;
	private long windows;
	private int height = 1000;
	private int width = 1000;

	@SuppressWarnings("unused")
	private GLFWKeyCallback keyCallback;
	@SuppressWarnings("unused")
	private GLFWCursorPosCallback cursorCallback;

	private Game game;
	private Movement move;
	private ShaderManager shaderMan;
	private Renderer rend;
	private User user;

    public static int FIELD = 0;
    public static int OBSTACLE = 1;

	private void init(){

		int[][] field = new int[][] {
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, FIELD, OBSTACLE,FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, OBSTACLE,OBSTACLE,FIELD, FIELD, OBSTACLE, FIELD, FIELD },
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD },
            { FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD, FIELD }
		};

		game = new Game(field);
		game.setMonsterPosition(new Point(0.9, 0.82));
		move = new Movement(this);
		rend = new Renderer(this);
		user = new User(this);
		running = true;

		graficInit();
	}


	private void graficInit(){

		if(!glfwInit()){
			System.err.println("Initialisierung fehlgeschlagen!");
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		windows = glfwCreateWindow(height, width, "Monstergame", NULL, NULL);

		if(windows == NULL){
			System.err.println("Fenster konnte nicht erstellt werden!");
		}

		glfwSetKeyCallback(windows, keyCallback = new KeyboardInput());
		glfwSetCursorPosCallback(windows, cursorCallback = new MouseInput());
		glfwMakeContextCurrent(windows);
		glfwShowWindow(windows);
		GL.createCapabilities();
		glViewport(0, 0, height, width);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		shaderMan = ShaderManager.getInstance();
	}


	public void run(){
		init();
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
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
				System.out.println(updates + " UPS, " + frames + " FPS");
			}
			if(glfwWindowShouldClose(windows)){
				running = false;
			}
		}
	}

	private void update(){
		glfwPollEvents();
		user.update();
	}

	private void render(){
		glfwSwapBuffers(windows);
		rend.render();
	}

	public static void main(String[] args) {
		new Driver().run();
	}

	public Game getGame() {
		return game;
	}

	public ShaderManager getShaderMan(){
		return shaderMan;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight(){
		return height;
	}

	public Movement getMove() {
		return move;
	}
}
