package br.com.etyllica.planet.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.planet.model.Astro;
import br.com.luvia.core.ApplicationGL;
import br.com.luvia.linear.Point3D;
import br.com.luvia.util.Camera;

public class PlanetsScene extends ApplicationGL {
	
	private List<Astro> planets = new ArrayList<Astro>();
	
	private int currentPlanetIndex = 0;
	
	private Astro currentPlanet = null;
		
	private float mx = 0;
	private float my = 0;
	
	private boolean click = false;
	
	private Camera camera;
	
	private double zoom = 1;
	
	private double zoomStep = 0.2;
	
	public PlanetsScene(int w, int h) {
		super(w, h);
		
		camera = new Camera(0, 2, -5);
	}
	
	@Override
	public void load() {

		System.out.println("Load");
		
		loading = 88;
		loadingPhrase = "Loading Models...";
				
		loading = 99;		
		loadingPhrase = "Loading Textures...";
		
		loading = 100;

	}


	@Override
	public void init(GLAutoDrawable drawable) {
		
		loadingPhrase = "Loading 3d stuff...";
				
		loadPlanets();
		
		currentPlanet = planets.get(currentPlanetIndex);
		
		loading = 10;

	}
	
	private void loadPlanets() {
		
		Astro mercury = new Astro("Mercúrio", 0.1516, "mercurymap.jpg"); //1,516 miles
		
		mercury.setX(3.598);//35,980,000 miles
		
		Astro venus = new Astro("Vênus", 0.3760, "venusmap.jpg"); //3,760 miles
		
		venus.setX(6.7240);//67,240,000 miles miles
		
		Astro earth = new Astro("Terra", 0.3959, "earthmap1k.jpg"); //3,959 miles
		
		earth.setX(9.296);//92,960,000 miles
		
		Astro mars = new Astro("Marte", 0.2106, "mars_1k_color.jpg"); //2,106 miles

		mars.setX(14.16);//141,600,000 miles
		
		Astro jupiter = new Astro("Júpiter", 4.3441, "jupitermap.jpg"); //43,441 miles
		
		jupiter.setX(48.38);//483,800,000 miles
		
		Astro saturn = new Astro("Saturno", 3.6184, "saturnmap.jpg"); //36,184 miles
		
		saturn.setX(89.07);//890,700,000 miles
		
		Astro uranus = new Astro("Urano", 1.5759, "uranusmap.jpg"); //15,759 miles
		
		uranus.setX(187.7);//1,787,000,000 miles
		
		Astro neptune = new Astro("Netuno", 1.5299, "neptunemap.jpg");//15,299 miles
		
		neptune.setX(279.8);//2,798,000,000 miles
		
		Astro pluto = new Astro("Plutão", 0.0715, "plutomap1k.jpg");//715 miles
		
		pluto.setX(367.0);//3,670,000,000 miles
		
		planets.add(pluto);
		planets.add(neptune);
		planets.add(uranus);
		planets.add(saturn);
		planets.add(jupiter);
		planets.add(mars);
		planets.add(earth);
		planets.add(venus);
		planets.add(mercury);
				
	}
	
	protected void lookCamera(GL2 gl) {
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		//Point3D model = camera.getTarget();
		Point3D model = currentPlanet;
		
		glu.gluLookAt( camera.getX(), camera.getY(), camera.getZ(), model.getX(), model.getY(), model.getZ(), 0, 1, 0 );
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
				
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0,0,0,0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
				
		lookCamera(gl);
						
		GLU glu = new GLU();
		
		for(Astro astro: planets) {
						
			astro.draw(gl, glu);
		}

		gl.glFlush();

	}
	
	@Override
	public void draw(Graphic g) {

		g.setColor(Color.WHITE);
		g.writeX(50, "Press Right Arrow or Left Arrow to change the target");
		
		g.writeX(110, currentPlanet.getName());

		int size = 100;
		//g.setColor(Color.BLUE);
		g.drawRect(w/2-size/2, h/2-size/2, size, size);
	
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport ((int)x, (int)y, (int)w, (int)h);

		gl.glMatrixMode(GL2.GL_PROJECTION);

		gl.glLoadIdentity();

		glu.gluPerspective(60.0, (double) w / (double) h, 0.1, 500.0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();

	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if (event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			currentPlanetIndex++;
			currentPlanetIndex%=planets.size();
						
			currentPlanet = planets.get(currentPlanetIndex);
			
			camera.setZ(currentPlanet.getZ()+5);
			
		}
		
		else if (event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			currentPlanetIndex+=planets.size()-1;
			currentPlanetIndex%=planets.size();
			
			currentPlanet = planets.get(currentPlanetIndex);
			
			camera.setZ(currentPlanet.getZ()+5);
		}

		return GUIEvent.NONE;
	}

	public GUIEvent updateMouse(PointerEvent event) {

		mx = event.getX();
		my = event.getY();

		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){

			click = true;
		}

		if(event.onButtonUp(MouseButton.MOUSE_BUTTON_LEFT)){
			click = false;
		}
		
		if(event.onButtonDown(MouseButton.MOUSE_WHEEL_UP)) {
			
			zoomIn();			
			
		}else if(event.onButtonDown(MouseButton.MOUSE_WHEEL_DOWN)) {
			
			zoomOut();
			
		}

		return GUIEvent.NONE;
	}
	
	private void zoomIn() {
		
		double oldZoom = zoom;
		
		zoom+=zoomStep;
		
		for(Astro astro: planets) {
			
			astro.setRadius((astro.getRadius()/oldZoom)*zoom);
			
			astro.setX((astro.getX()/oldZoom)*zoom);
			
		}
		
	}
	
	private void zoomOut() {
		
		if(zoom>1) {
			
			double oldZoom = zoom;
			
			zoom-=zoomStep;
			
			for(Astro astro: planets) {
				
				astro.setRadius((astro.getRadius()/oldZoom)*zoom);
				
				astro.setX((astro.getX()/oldZoom)*zoom);
				
			}
			
		}
		
	}
	
}