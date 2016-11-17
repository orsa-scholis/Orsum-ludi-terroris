package graphicEngine;

import static utils.Utilities.*;

import utils.Vector3f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Shader {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public Shader(String vertexFile, String fragmentFile) {
		vertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
		
		programID = glCreateProgram();
		glAttachShader(programID, fragmentShaderID);
		glAttachShader(programID, vertexShaderID);
		glLinkProgram(programID);
		glValidateProgram(programID);
		
	}
	
	public int getID(){
		return programID;
	}
	
	public int getUniform(String name){
		int result = glGetUniformLocation(programID, name);
		if(result == -1){
			System.err.println("Could not find uniform variable " + name + "!");
		}
		return result;
	}
	
	public void setUniform3f(String name, Vector3f position){
		glUniform3f(getUniform(name), position.x, position.y, position.z);
	}
	
	public void start(){
		glUseProgram(programID);
	}
	
	public void stop(){
		glUseProgram(0);
	}
}
