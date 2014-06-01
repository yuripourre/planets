package br.com.etyllica.planet.model;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import br.com.etyllica.planet.model.data.DataAstro;
import br.com.luvia.linear.Point3D;
import br.com.luvia.loader.TextureLoader;

import com.jogamp.opengl.util.texture.Texture;

public class VisualAstro extends Point3D {

	private String name;
	
	private double radius;
	
	private Texture texture;
	
	private DataAstro data;
		
	public VisualAstro(String name, double radius, String mapPath) {
		super();
		
		this.name = name;
		
		this.radius = radius;
		
		texture = TextureLoader.getInstance().loadTexture(mapPath);
		
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}	

	public String getName() {
		return name;
	}
	
	public void draw(GL2 gl, GLU glu) {
		
		GLUquadric sphere = glu.gluNewQuadric();

		glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);
		glu.gluQuadricTexture(sphere, true);
		glu.gluQuadricNormals(sphere, GLU.GLU_SMOOTH);
		
		texture.enable(gl);
		texture.bind(gl);
		  		
		// draw a sphere
        gl.glPushMatrix();                                
            gl.glTranslated(x, y, z);
            gl.glRotatef(-90, 1, 0, 0);            
            glu.gluSphere(sphere, radius, 32, 32);
        gl.glPopMatrix();
		
        texture.disable(gl);
		
	}
	
	public DataAstro getData() {
		return data;
	}

	public void setData(DataAstro data) {
		this.data = data;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
		
}
