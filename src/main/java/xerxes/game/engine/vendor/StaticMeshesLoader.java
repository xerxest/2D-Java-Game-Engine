package xerxes.game.engine.vendor;

import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import xerxes.game.engine.Mesh;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.assimp.Assimp.*;

public class StaticMeshesLoader {

    public static Mesh[] load(String resourcePath, String texturesDir) throws Exception {
        return load(resourcePath, texturesDir, aiProcess_JoinIdenticalVertices | aiProcess_Triangulate | aiProcess_FixInfacingNormals);
    }


    public static Mesh[] load(String resourcePath, String texturesDir, int flags) throws Exception {

        AIScene aiScene = aiImportFile(resourcePath, flags);
        if (aiScene == null) {
            throw new Exception("Error loading model");
        }

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer aiMeshes = aiScene.mMeshes();
        Mesh[] meshes = new Mesh[numMeshes];
        for (int i = 0; i < numMeshes; i++) {
            AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
            Mesh mesh = processMesh(aiMesh);
            meshes[i] = mesh;
        }

        return meshes;
    }

    private static Mesh processMesh(AIMesh aiMesh) {
        List<Float> vertices = new ArrayList<>();
        List<Float> textures = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        processVertices(aiMesh, vertices);
        processIndices(aiMesh, indices);

        Mesh mesh = new Mesh(toPrimitiveFloat(vertices),
                toPrimitiveFloat(textures),
                toPrimitiveFloat(normals),
                toPrimitiveInt(indices)
        );

        return mesh;
    }

    private static void processVertices(AIMesh aiMesh, List<Float> vertices) {
        AIVector3D.Buffer aiVertices = aiMesh.mVertices();
        while (aiVertices.remaining() > 0) {
            AIVector3D aiVertex = aiVertices.get();
            vertices.add(aiVertex.x());
            vertices.add(aiVertex.y());
            vertices.add(aiVertex.z());
        }
    }

    private static void processIndices(AIMesh aiMesh, List<Integer> indices) {
        AIFace.Buffer aiIndices = aiMesh.mFaces();
        while (aiIndices.remaining() > 0) {
            AIFace aiIndex = aiIndices.get();
            indices.add(aiIndex.mIndices().get(0));
            indices.add(aiIndex.mIndices().get(1));
            indices.add(aiIndex.mIndices().get(2));
        }
    }

    private static int[] toPrimitiveInt(List<Integer> integers){
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    private static float[] toPrimitiveFloat(List<Float> floats){
        float[] ret = new float[floats.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = floats.get(i).intValue();
        }
        return ret;
    }

}
