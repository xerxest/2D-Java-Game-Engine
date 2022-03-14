package xerxes.game.engine;
import static org.lwjgl.opengl.GL33.*;

public class VertexArray {
	
	private int ID;
	
	public VertexArray(VertexBuffer mesh) {
		
		ID = glGenVertexArrays();
				
		glBindVertexArray(ID);
		
		mesh.bind();

		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);

	}
	
	public void bind(){
		glBindVertexArray(ID);
	}
}
