package xerxes.game.engine;
import static org.lwjgl.opengl.GL33.*;
public class VertexBuffer {
	
	private final int ID;
	
	public VertexBuffer(float[] data){
		
		ID = glGenBuffers();
		
		glBindBuffer(GL_ARRAY_BUFFER,ID);
		
		glBufferData(GL_ARRAY_BUFFER,data,GL_STATIC_DRAW);
		
	}

	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER,ID);
	}

}
