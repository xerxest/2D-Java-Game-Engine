package xerxes.game.engine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import org.joml.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

	// The window handle
	private long window;

	public void run() throws Exception {
		//System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();
		
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(960, 540, "XERG", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() throws Exception {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		
		

		// Set the clear color
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		
		Scene scene = new Scene();
		
		TestGameObj obj = new TestGameObj();
		
		scene.addGameObject(obj);
		
		// Testing Shader

		// grok the opengl coordinate system

		float data[] = {
				0.0f, 540.0f, // 0
				200.0f, 100.0f, // 1
				500.0f, 200.0f, // 2
				100f, 100.0f
			};
		
		float texCoords[] = {
			    0.0f, 0.0f,  // lower-left corner  
			    1.0f, 0.0f,  // lower-right corner
			    0.5f, 1.0f   // top-center corner
			};

		
		VertexBuffer vb = new VertexBuffer(data);
		
		VertexBuffer tc = new VertexBuffer(texCoords);
		
		VertexArray vao = new VertexArray(vb,tc);
		
		Texture tex = new Texture("test.png");

		vao.bind();
		
		tex.bind();
		
		Shader shader = new Shader("BasicVertex.GLSL", "BasicFrag.GLSL");
		shader.bind();


		Matrix4f proj = new Matrix4f();

		proj.identity();

		proj.ortho(0.0f,960.0f,0.0f,540.0f,-1.0f,1.0f);


		//shader.setUniform4f("proj",proj);



		Camera cam = new Camera(shader);

		while ( !glfwWindowShouldClose(window) ) {
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			vao.bind();
			glDrawArrays(GL_TRIANGLES,0,4);

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents(); 
		}
	}

	public static void main(String[] args) throws Exception {
		
		
		new Main().run();
	}

}