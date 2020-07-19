package com.opengl10_cubocolor_camara_dos_cubos1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * 
 *
 */
public class Tronco {
	
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
		-1, -1.0f,  1, // 4   0
		 1, -1.0f,  1, // 5   1
		 1,  1.2f,  1, // 6   2
		-1,  1.2f,  1, // 8   3
		
		// Atrás
		-1,  1.2f, -1.6f, // 3   4
		 1,  1.2f, -1.6f, // 2   5
		 1, -1.0f, -1.6f, // 1   6
		-1, -1.0f, -1.6f, // 0   7
		
		// Izquierda
		-1, -1.0f, -1.6f, // 0  8
		-1, -1.0f,  1.0f, // 4  9
		-1,  1.2f,  1.0f, // 8  10 
		-1,  1.2f, -1.6f, // 3  11
		
		// Derecha
		 1, -1.0f,  1.0f, // 5  12 
		 1, -1.0f, -1.6f, // 1  13
		 1,  1.2f, -1.6f, // 2  14
		 1,  1.2f,  1.0f, // 6  15
		 
		 // Abajo
		-1, -1, -1.6f, // 0  16
		 1, -1, -1.6f, // 1  17
		 1, -1,  1.0f, // 5  18
		-1, -1,  1.0f, // 4  19
		
		 // Arriba
		-1,  1.2f,  1.0f, // 8  20
		 1,  1.2f,  1.0f, // 6  21
		 1,  1.2f, -1.6f, // 2  22
		-1,  1.2f, -1.6f, // 3  23
		 
		
		
		
	};
	
	FloatBuffer bufVertices;

	public Tronco() {
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
		gl.glColor4f(128f/255, 64f/255, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);

		/* Atrás */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,4,4);
		
		/* Izquierda */
		//gl.glColor4f(1, 1, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,8,4);
		
		/* Derecha */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,12,4);
		
		/* Abajo */
		//gl.glColor4f(1,165/255f, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,16,4);
		
		/* Arriba */
		//gl.glColor4f(0, 0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,20,4);
		
		
		
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
}
