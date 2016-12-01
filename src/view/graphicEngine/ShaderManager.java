package view.graphicEngine;

public class ShaderManager {

	public static Shader shaderField;
	public static Shader shaderPlayer;
	public static Shader shaderMonster;

	public static void loadAll(){
		shaderField = new Shader("src/view/shaders/vertex.shader", "src/view/shaders/fragment.shader");
		shaderPlayer = new Shader("src/view/shaders/vertexPlayer.shader", "src/view/shaders/fragment.shader");
		shaderMonster = new Shader("src/view/shaders/vertexMonster.shader", "src/view/shaders/fragment.shader");
	}



}
