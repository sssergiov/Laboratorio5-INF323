package com.opengl10_cubocolor_camara_dos_cubos1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * 
 *
 */
public class Arbol {
	
	/**
	 *              9  
	 *             / \ 
	 *       3  7/     \ 2
	 *         / \     /|   
	 *       /     \ /  |
	 *    8   |      6  |
	 *     |  |      |  |
	 *     | 0 ------|-- 1 
	 *     | /       | /
	 *     |/        |/
	 *    4 --------- 5  
	 */
	private float vertices[] = new float[] {
		// Frente
		-2, 1.2f,  1,  // 0   0
		 2, 1.2f,  1,  // 1   1
		 0, 6.2f,-0.3f,  // 2   2
	
		
		// Atrás
		-2,  1.2f, -1.6f, // 4   3
		 0,  6.2f, -0.3f,  // 2   4
		 2,  1.2f, -1.6f, // 3   5
		
		
		// Izquierda
		-2,  1.2f, -1.6f, // 1  6
		-2,  1.2f,  1.0f, // 0  7
		 0,  6.2f,  -0.3f, // 2  8 
		
		
		// Derecha
		 2,  1.2f,  1.0f, // 1  9 
		 2,  1.2f, -1.6f, // 3  10
		 0,  6.2f,  -0.3f, // 2  11
		 
		 
		 // Abajo
		-2, 1.2f, -1.6f, // 4  12
		 2, 1.2f, -1.6f, // 3  13
		 2, 1.2f,  1.0f, // 1  14
		-2, 1.2f,  1.0f, // 0  15
		
		
		 
		
		
		
	};
	
	FloatBuffer bufVertices;

	public Arbol() {
		/* Lee los vértices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		bufVertices.put(vertices).rewind();
	}
	
	public void dibuja(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);

		/* Frente */
		gl.glColor4f(0, 128f/255, 64f/255, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,3);

		/* Atrás */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,3,3);
		
		/* Izquierda */
		//gl.glColor4f(1, 1, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,6,3);
		
		/* Derecha */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,9,3);
		
		/* Abajo */
		//gl.glColor4f(1,165/255f, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,12,4);
		
		
		
		
		
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
}
