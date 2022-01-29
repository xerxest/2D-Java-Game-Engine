package xerxes.game.engine;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL33.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.Scanner;

public class Shader {

    private int ID, vertexID, fragID;

    public Shader(String vertexFileName, String fragFileName) throws FileNotFoundException {

        ID = glCreateProgram();

        String vertexSrc = openShaderSourceFile(vertexFileName);

        String fragSrc = openShaderSourceFile(fragFileName);

        vertexID = compileShader(vertexSrc, GL_VERTEX_SHADER);

        fragID = compileShader(fragSrc, GL_FRAGMENT_SHADER);

        glAttachShader(ID, vertexID);
        glAttachShader(ID, fragID);
        glLinkProgram(ID);

        glValidateProgram(ID);

        glDeleteProgram(vertexID);
        glDeleteProgram(fragID);

    }

    public void bind() {
        glUseProgram(ID);
    }

    public void setUniform4f(String name, Matrix4f val){

        int loc = glGetUniformLocation(ID,name);

        System.out.println(name+" = "+loc);

        FloatBuffer valBuffer = BufferUtils.createFloatBuffer(16);

        val.get(valBuffer);

        glUniformMatrix4fv(loc,false,valBuffer);
    }

    private int compileShader(String src, int shaderType) {

        int shaderID = glCreateShader(shaderType);

        glShaderSource(shaderID, src);

        glCompileShader(shaderID);

        // Fix error checking code
		
		/*
		IntBuffer result = null;
		
		glGetShaderiv(shaderID,GL_COMPILE_STATUS,result);
		
		 if( result.get() == 0) {
			 System.out.println((glGetShaderInfoLog(shaderID)));
		 }

		 	System.out.println("Shader ERROR"+(glGetShaderInfoLog(shaderID)));
		 
		 */

        return shaderID;

    }

    private String openShaderSourceFile(String fileName) throws FileNotFoundException {

        String src = "";

        URL url = Shader.class.getClassLoader().getResource(fileName);

        File file = new File(url.getPath());

        try {

            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNext()) {

                src += fileReader.nextLine() + "\n";

            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();

            throw new FileNotFoundException();

        }

        return src;



    }


}