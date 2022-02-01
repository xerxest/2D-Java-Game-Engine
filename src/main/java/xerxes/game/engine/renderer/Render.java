package xerxes.game.engine.renderer;

import org.joml.Vector2f;
import xerxes.game.engine.*;
import xerxes.game.engine.test.GameObjTestGen;

import java.util.ArrayList;
import java.util.Comparator;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

public class Render {

    private ArrayList<GameObject> objList;
    private final float[] texCoords = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
    };

    public Render() {


        GameObjTestGen gameObjs = new GameObjTestGen();

        objList = gameObjs.genObjs();

        objList.sort(Comparator.comparingInt(GameObject::getTextureID));

        objList.sort(Comparator.comparingInt(o -> o.layerID));

    }
    // TODO Implement batch rendering
    // TODO Remove magic numbers
    public void draw() throws Exception {

        for (GameObject gameObject : objList) {

            Vector2f leftDown  = new Vector2f(50f*gameObject.scale.x+gameObject.position.x, 50f*gameObject.scale.y+gameObject.position.y);
            Vector2f leftUp    = new Vector2f(50f*gameObject.scale.x+gameObject.position.x, 100*gameObject.scale.y+gameObject.position.y);
            Vector2f upRight   = new Vector2f(100*gameObject.scale.x+gameObject.position.x, 100*gameObject.scale.y+gameObject.position.y);
            Vector2f downRight = new Vector2f(100*gameObject.scale.x+gameObject.position.x, 50*gameObject.scale.y+gameObject.position.y);

            float[] quad ={
                    leftUp.x,    leftUp.y,
                    downRight.x, downRight.y,
                    leftDown.x,  leftDown.y,

                    leftUp.x,    leftUp.y,
                    downRight.x, downRight.y,
                    upRight.x,   upRight.y
            };

            VertexBuffer vb = new VertexBuffer(quad);

            VertexBuffer tc = new VertexBuffer(texCoords);

            VertexArray vao = new VertexArray(vb, tc);

            Texture tex = new Texture(gameObject.getFileName());

            vao.bind();

            tex.bind();

            glDrawArrays(GL_TRIANGLES, 0, 6);


        }
    }

}
