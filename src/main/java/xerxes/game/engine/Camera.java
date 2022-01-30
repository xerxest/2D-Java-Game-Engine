package xerxes.game.engine;

import org.joml.*;

public class Camera {


    // camera postion stored as a vector
    // camera's vectors of what its pointing at
    // vector pointing to right
    // vector pointing up
    // Gram–Schmidt process
    // ^^ creates a look at matrix

    public Camera(Shader shader){
        // camera postion stored as a vector
        Vector3f camPos   = new Vector3f(0.0f, 0.0f,  0.0f);
        Vector3f camFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f camUp    = new Vector3f(0.0f, 1.0f,  0.0f);

        Matrix4f lookAt = new Matrix4f();

        // fix me
        lookAt.lookAt(camPos,(camPos.add(camFront)),camUp);

        Matrix4f proj = new Matrix4f();

        proj.ortho(0.0f,960.0f,0.0f,540.0f,-1.0f,1000f);

        shader.setUniform4f("proj",proj);
        shader.setUniform4f("view",lookAt);

    }

}


