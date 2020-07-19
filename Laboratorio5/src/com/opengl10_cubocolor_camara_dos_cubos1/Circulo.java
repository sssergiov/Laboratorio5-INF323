package com.opengl10_cubocolor_camara_dos_cubos1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * 
 * 
 * 
 * 
 */
public class Circulo {
	
	/**
	 *             3      2
	 *             
	 *       4                  1
 	 *        	
	 *    5                         0
	 *    
	 *       6                  9
	 *        
	 *             7      8		
	 */
	
	private FloatBuffer bufVertices;

	private int segmentos;
	private boolean llenado;

	public Circulo(float radio, int segmentos, boolean llenado) {
		this.segmentos = segmentos;
		this.llenado = llenado;
		
		/* Reserva espacio para los v�rtices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(360 * 2 * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden del byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		
		/* Lee los v�rtices (x,y) */
		int j = 0;
	 	for (float i = 0; i < 360.0f; i = i + 360.0f/segmentos) {
	 		bufVertices.put(j++, (float) Math.cos(Math.toRadians(i)) * radio);
	 		bufVertices.put(j++, (float) Math.sin(Math.toRadians(i)) * radio);
	 	}
	 	bufVertices.rewind(); // puntero al principio del buffer
		
	}
	
	public void dibuja(GL10 gl) {
		
		/* Se habilita el acceso al arreglo de v�rtices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		/* Se especifica los datos del arreglo de v�rtices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		
		/* Se establece el color en (r,g,b,a) */
		gl.glColor4f(0, 0, 1, 0);

		/* Renderiza las primitivas desde los datos del arreglo de v�rtices */
		gl.glDrawArrays((llenado) ? GL10.GL_TRIANGLE_FAN : GL10.GL_LINE_LOOP, 0, segmentos);
		
		/* Se deshabilita el acceso al arreglo de v�rtices */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
}
