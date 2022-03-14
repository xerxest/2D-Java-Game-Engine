package xerxes.game.engine;

import org.joml.*;
import xerxes.game.engine.shaders.Shader;

import static org.lwjgl.glfw.GLFW.*;

public class Camera implements Update{

    public static final int SCREEN_SIZE_X = 960;

    public static final int SCREEN_SIZE_Y = 540;

    private Matrix4f viewMatrix;
    private Matrix4f projectionMatrix;
    private static Vector3f position;
    private Shader shader;
    private Vector3f camFront;
    private Vector3f camUp;

    // TODO add Zoom
    public Camera(Shader shader){

        position = new Vector3f(-1.0f,0.0f,0.0f);

        this.shader = shader;

        projectionMatrix = new Matrix4f().identity();

      projectionMatrix.perspective(70.0f,14.9f,0.1f,10000.0f);

      // projectionMatrix.ortho(0.0f,SCREEN_SIZE_X,0.0f,SCREEN_SIZE_Y,-1.0f,1000f);

        this.camFront = new Vector3f(0.0f,0.0f,-1.0f);
        this.camUp = new Vector3f(0.0f,1.0f,0.0f);
        viewMatrix = new Matrix4f().identity();


        viewMatrix.lookAt(new Vector3f(position.x,position.y,position.z),
                camFront.add(position.x,position.y,position.z),camUp);

        shader.setUniform4f("proj",projectionMatrix);
        shader.setUniform4f("view",viewMatrix);

    }

    @Override
    public void update() {

        viewMatrix = new Matrix4f().identity();

        this.camFront = new Vector3f(0.0f,0.0f,-1.0f);

        viewMatrix.lookAt(new Vector3f(position.x,position.y,position.z),camFront.add(position.x,position.y,position.z),camUp);

        shader.setUniform4f("view",viewMatrix);
    }

    public static void keyInput(long window, int key, int scancode, int action, int mods){

        float move = 1f;

        if (key == GLFW_KEY_W && action == GLFW_PRESS){
            position.z-= move;
        }
        if (key == GLFW_KEY_A && action == GLFW_PRESS){
            position.x-= move;
        }
        if (key == GLFW_KEY_S && action == GLFW_PRESS){
            position.z+= move;
        }
        if (key == GLFW_KEY_D && action == GLFW_PRESS){
            position.x+= move;
        }
        if (key == GLFW_KEY_SPACE && action == GLFW_PRESS){
            position.y+= move;
        }
        if (key == GLFW_KEY_G && action == GLFW_PRESS){
            position.y-= move;
        }



    }

    public static Vector3f getposition(){
        return position;
    }
}


