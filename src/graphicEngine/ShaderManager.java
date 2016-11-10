package graphicEngine;

public class ShaderManager {
	
	public static Shader shader1;
	
	public static void loadAll(){
		shader1 = new Shader("src/shaders/vertex.shader", "src/shaders/fragment.shader");
	}
	
	
	
}
