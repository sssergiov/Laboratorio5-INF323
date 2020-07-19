package com.opengl10_cubocolor_camara_dos_cubos1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.view.MotionEvent;

/**
 * 
 * 
 * 
 *
 */
public class Renderiza extends GLSurfaceView implements Renderer {

	/* Objeto */
	private Cubo cubo;
	private Piso piso;
	private Circulo circulo;
	private Arbol arbol;
	private Arbusto arbusto;
	private Tronco tronco;
	
	/* Inicializa ubicación de la vista del observador */
	private final float[] vectorEntrada = { 0, 0, -1, 1 };
	private static float posicion[] = { 0, 0, 0 };
	private final float[] direccion = new float[4];

	/* Tamaño de la ventana en pixeles */
	private int alto;
	private int ancho;
	
	/* Para la rotación y traslación */
	private float rotY;
	private	float antX;
	
	final float[] matriz = new float[16];
	
	/* Contexto */
	Context contexto;
	
	public Renderiza(Context contexto) {
		super(contexto);
		this.contexto = contexto;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		
		cubo = new Cubo();
		piso = new Piso();
		circulo = new Circulo(0.5f, 360, true);
		arbol = new Arbol();
		tronco = new Tronco();
		arbusto = new Arbusto();
        
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearColor(176/255f, 196/255f, 222/256f, 0);

	}
	
	@Override
	public void onDrawFrame(GL10 gl) {

		/* Borra el buffer de la ventana y del z-buffer */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		/* Botones de las opciones */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-4, 4, -6, 6, 1, 100);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		// Botón avanza
		gl.glPushMatrix();
		gl.glTranslatef(0, -4, 0);
		circulo.dibuja(gl);
		gl.glPopMatrix();
		
		// Botón retrocede
		gl.glPushMatrix();
		gl.glTranslatef(0, -5.5f, 0);
		circulo.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 67, ancho / (float)alto, 1f, 100f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotatef(-rotY, 0, 1, 0);
		gl.glTranslatef(-posicion[0], -posicion[1], -posicion[2]);
		
		// Piso
		gl.glPushMatrix();
		gl.glTranslatef(0, 0, -6);
		piso.dibuja(gl);
		gl.glPopMatrix();
		
		// Cubo 1
		//gl.glPushMatrix();
		//gl.glTranslatef(-1.5f, 0, -6);
		//gl.glScalef(0.5f, 0.5f, 0.5f);
		//cubo.dibuja(gl);
		//gl.glPopMatrix();
		
		// Cubo 2
		//gl.glPushMatrix();
		//gl.glTranslatef(1.5f, 0, -6);
		//gl.glScalef(0.5f, 0.5f, 0.5f);
		//cubo.dibuja(gl);
		//gl.glPopMatrix();
		
		
		
		// arbol 1
		gl.glPushMatrix();
		gl.glTranslatef(-3f, 0, -4);
		gl.glScalef(0.3f, 0.5f, 0.3f);
		tronco.dibuja(gl);
		arbol.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-1f, 0, -6);
		gl.glScalef(0.3f, 0.5f, 0.3f);
		tronco.dibuja(gl);
		arbol.dibuja(gl);
		gl.glPopMatrix();
		
		// arbol 2
		gl.glPushMatrix();
		gl.glTranslatef(1, 0, -6);
		gl.glScalef(0.3f, 0.5f, 0.3f);
		tronco.dibuja(gl);
		arbol.dibuja(gl);
		gl.glPopMatrix();
				
		
		
		
		//Arbol 3
		gl.glPushMatrix();
		gl.glTranslatef(3f, 0, -4);
		gl.glScalef(0.3f, 0.5f, 0.3f);
		tronco.dibuja(gl);
		arbol.dibuja(gl);
		gl.glPopMatrix();

		gl.glFlush();

	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		
		ancho = w;
		alto = h;
		
		gl.glViewport(0, 0, ancho, alto);
		
		GLU.gluLookAt(gl, 0, 0, 0, 0, 0, -1, 0, 1, 0);

	}
	
	/**
	 * Maneja los eventos del movimiento en la pantalla táctil. 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		
		/* Obtiene la coordenada de la pantalla */
		float posx = e.getX();
		float posy = e.getY();
		
		/* Se considera cuando se levanta el dedo de la pantalla. */ 
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			
			/* En coordenadas del OpenGL */
			posx = ((posx / (float) ancho) * 8) - 4;
			posy = ((1 - posy / (float) alto) * 12) - 6;

			/* Verifica área elegida */
			if (puntoEstaDentroDelCirculo(posx, posy, 0, -4f, 0.5f)) { // Avanza
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);
				
				posicion[0] = posicion[0] + direccion[0] * 0.1f;
				posicion[1] = posicion[1] + direccion[1] * 0.1f;
				posicion[2] = posicion[2] + direccion[2] * 0.1f;
				
			} else if (puntoEstaDentroDelCirculo(posx, posy, 0, -5.5f, 0.5f)) { // Retrocede
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);
				
				posicion[0] = posicion[0] - direccion[0] * 0.1f;
				posicion[1] = posicion[1] - direccion[1] * 0.1f;
				posicion[2] = posicion[2] - direccion[2] * 0.1f;
			}
			requestRender();
		} else if (e.getAction() == MotionEvent.ACTION_MOVE) {
			if(antX == -1) {
				antX = posx;
			} else {							
				rotY = rotY + (posx - antX) / 10;
				antX = posx;
			}
			
			requestRender();
		} else { 
			antX = -1;
		}	
		return true;
	}
	
	private boolean puntoEstaDentroDelCirculo(float posx, float posy, float x,
			float y, float radio) {
		return (distancia2(posx, posy, x, y) < radio * radio);
	}

	public float distancia2(float x1, float y1, float x2, float y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}
