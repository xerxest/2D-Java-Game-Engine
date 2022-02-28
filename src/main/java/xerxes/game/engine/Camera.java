package xerxes.game.engine;

import org.joml.*;
import xerxes.game.engine.shaders.Shader;

public class Camera implements Update{

    public static final int SCREEN_SIZE_X = 960;

    public static final int SCREEN_SIZE_Y = 540;

    private Matrix4f viewMatrix;
    private Matrix4f projectionMatrix;
    private Vector3f position;
    private Shader shader;
    private Vector3f camFront;
    private Vector3f camUp;

    // TODO add Zoom
    public Camera(Shader shader){

        position = new Vector3f(3.0f,3.0f,0.0f);

        this.shader = shader;

        projectionMatrix = new Matrix4f().identity();

        //projectionMatrix.perspective(90.0f,16.9f,0.1f,10000.0f);

        projectionMatrix.ortho(0.0f,SCREEN_SIZE_X,0.0f,SCREEN_SIZE_Y,-1.0f,1000f);

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
}


