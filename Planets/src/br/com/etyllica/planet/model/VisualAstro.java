package br.com.etyllica.planet.model;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import br.com.abby.linear.Point3D;
import br.com.etyllica.planet.model.data.DataAstro;
import br.com.luvia.loader.TextureLoader;

import com.jogamp.opengl.util.texture.Texture;

public class VisualAstro extends Point3D {
	
	private double radius;
	
	private Texture texture;
	
	private DataAstro data;
		
	public VisualAstro(double radius, String mapPath) {
		super();
				
		this.radius = radius;
		
		texture = TextureLoader.getInstance().loadTexture(mapPath);
		
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
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
	
public void draw(GL2 gl, GLU glu) {
		
		GLUquadric sphere = glu.gluNewQuadric();

		glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);
		glu.gluQuadricTexture(sphere, true);
		glu.gluQuadricNormals(sphere, GLU.GLU_SMOOTH);
		gl.glTexGeni(GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_SPHERE_MAP);
        gl.glTexGeni(GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_SPHERE_MAP);
        
        gl.glEnable(GL2.GL_TEXTURE_GEN_S);  // Enable Sphere Mapping
        gl.glEnable(GL2.GL_TEXTURE_GEN_T);  // Enable Sphere Mapping
				
		texture.enable(gl);
		texture.bind(gl);
		  		
		// draw a sphere
        gl.glPushMatrix();                                
            gl.glTranslated(x, y, z);
            glu.gluSphere(sphere, radius, 48, 48);            
        gl.glPopMatrix();
		
        texture.disable(gl);
		
        gl.glDisable(GL2.GL_TEXTURE_GEN_S);  // Disable Sphere Mapping
        gl.glDisable(GL2.GL_TEXTURE_GEN_T);  // Disable Sphere Mapping
	}
		
}
