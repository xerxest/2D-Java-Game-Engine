package xerxes.game.engine;

import org.joml.Vector2f;
import xerxes.game.engine.renderer.Texture;

public class Sprite {

    private Texture tex;
    private Vector2f pos;
    private Vector2f scale;
    public int layerID;

    public Sprite(String fileName, Vector2f pos, Vector2f scale, int layerID) throws Exception {

        tex = new Texture(fileName);;
        this.pos = pos;
        this.scale = scale;
        this.layerID = layerID;
    }

    public Sprite(String fileName) throws Exception {
        tex = new Texture(fileName);
    }

    public int getTexID(){
       return tex.currTexID;
    }
}
