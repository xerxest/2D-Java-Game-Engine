package xerxes.game.engine;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private float[] vertices;
    private float[] textures;
    private float[] normals;
    private int[] indices;

    public Mesh(float[] vertices, float[] textures, float[] normals, int[] indices){
        this.vertices = vertices;
        this.textures = textures;
        this.normals = normals;
        this.indices = indices;
    }

    public float[] getVertices() {
        return  vertices;
    }

    public int[] getIndices() {
        return  indices;
    }
}
