package xerxes.game.engine.renderer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import xerxes.game.engine.*;
import xerxes.game.engine.vendor.StaticMeshesLoader;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL33.GL_TRIANGLES;

public class Render {

    private ArrayList<Entity> objList;
    private final float[] texCoords = {

            0.0f, 1.0f, // 0
            1.0f, 0.0f, // 1
            0.0f, 0.0f, // 2

            0.0f, 1.0f, // 3
            1.0f, 0.0f, // 4
            1.0f, 1.0f, // 5
    };

    public Render() throws Exception {


    }



    public void draw() throws Exception {

        Mesh[] model = StaticMeshesLoader.load("testModel.obj","");

        VertexBuffer vb = new VertexBuffer(model[0].getVertices());

        VertexArray va = new VertexArray(vb);

        va.bind();

        IntBuffer indexBuf = BufferUtils.createIntBuffer(model[0].getIndices().length);

        indexBuf.put(model[0].getIndices());

        indexBuf.flip();

        GL33.glDrawElements(GL_TRIANGLES,indexBuf);

    }


}
