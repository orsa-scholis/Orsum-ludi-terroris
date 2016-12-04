package view.graphicEngine;

public class ShaderManager {

	private static ShaderManager instance;

	public final Shader shaderObstacle;
	public final Shader shaderPlayer;
	public final Shader shaderMonster;

	public static ShaderManager getInstance(){
		if(ShaderManager.instance == null){
			ShaderManager.instance = new ShaderManager();
		}
		return ShaderManager.instance;
	}

	private ShaderManager(){
		shaderObstacle = new Shader("src/view/shaders/vertex.shader", "src/view/shaders/fragment.shader");
		shaderPlayer = new Shader("src/view/shaders/vertexPlayer.shader", "src/view/shaders/fragment.shader");
		shaderMonster = new Shader("src/view/shaders/vertexMonster.shader", "src/view/shaders/fragment.shader");
	}



}
