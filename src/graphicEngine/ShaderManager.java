package graphicEngine;

public class ShaderManager {

	public static Shader shaderFields;
	public static Shader shaderPlayer;
	public static Shader shaderMonster;

	public static void loadAll(){
		shaderFields = new Shader("src/shaders/vertex.shader", "src/shaders/fragment.shader");
		shaderPlayer = new Shader("src/shaders/vertexPlayer.shader", "src/shaders/fragment.shader");
		shaderMonster = new Shader("src/shaders/vertexMonster.shader", "src/shaders/fragment.shader");
	}



}
