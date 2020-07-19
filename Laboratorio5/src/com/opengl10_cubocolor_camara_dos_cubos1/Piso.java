package com.opengl10_cubocolor_camara_dos_cubos1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * 
 * 
 */
public class Piso {

	/**
	 *    3 ---------- 2
	 *     |          | 
	 *     |          | 
	 *     |          |
	 *     |          |
	 *    0 ---------- 1  
	 */	
	
	private float vertices[] = new float[42 * 6];
	private byte colores[] = new byte[42 * 8];
	private FloatBuffer bufVertices;
	private ByteBuffer bufColores;
	public Piso() {

		int i = 0;
		int j = 0;
		for (float x = -10; x <= 10; x++){
			vertices[i  ] = x; vertices[i+1] = -1; vertices[i+2] =  10;
			vertices[i+3] = x; vertices[i+4] = -1; vertices[i+5] = -10;
			i = i + 6;
			colores[j  ] = 0; colores[j+1] = 0; colores[j+2] = 0; colores[j+3] = 0;
			colores[j+4] = 0; colores[j+5] = 0; colores[j+6] = 0; colores[j+7] = 0;
			j = j + 8;
		}
		for (float z = 10; z >= -10; z--){
			vertices[i  ] = -10; vertices[i+1] = -1; vertices[i+2] = z;
			vertices[i+3] =  10; vertices[i+4] = -1; vertices[i+5] = z;
			i = i + 6;
			colores[j  ] = 0; colores[j+1] = 0; colores[j+2] = 0; colores[j+3] = 0;
			colores[j+4] = 0; colores[j+5] = 0; colores[j+6] = 0; colores[j+7] = 0;
			j = j + 8;
		}
		
		/* Lee los v�rtices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer
		
		/* Lee los colores */
		bufColores = ByteBuffer.allocateDirect(colores.length);
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer
	}
	
	public void dibuja(GL10 gl) {

		/* Se habilita el acceso al arreglo de v�rtices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		/* Se habilita el acceso al arreglo de colores */
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		/* Se especifica los datos del arreglo de v�rtices */
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);
		
		/* Se especifica los datos del arreglo de colores */
		gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, bufColores);
		
		/* Renderiza las primitivas desde los datos de los arreglos (vertices e indices) */
		gl.glDrawArrays(GL10.GL_LINES, 0, 84);

		/* Se deshabilita el acceso a los arreglos */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
}
