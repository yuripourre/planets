package br.com.etyllica.planet.view;

import java.awt.Color;
import java.awt.Font;
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
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.planet.model.VisualAstro;
import br.com.etyllica.planet.model.data.Earth;
import br.com.etyllica.planet.model.data.Jupiter;
import br.com.etyllica.planet.model.data.Mars;
import br.com.etyllica.planet.model.data.Mercury;
import br.com.etyllica.planet.model.data.Neptune;
import br.com.etyllica.planet.model.data.Pluto;
import br.com.etyllica.planet.model.data.Saturn;
import br.com.etyllica.planet.model.data.Uranus;
import br.com.etyllica.planet.model.data.Venus;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.plurality.LeftPanel;
import br.com.etyllica.theme.plurality.RightPanel;
import br.com.etyllica.theme.plurality.Selection;
import br.com.etyllica.theme.plurality.TitleArrow;
import br.com.etyllica.util.SVGColor;
import br.com.luvia.core.ApplicationGL;
import br.com.luvia.linear.Point3D;
import br.com.luvia.util.CameraGL;

public class PlanetsScene extends ApplicationGL {
		
	private List<VisualAstro> planets = new ArrayList<VisualAstro>();
	
	private int currentPlanetIndex = 0;
	
	private VisualAstro currentPlanet = null;
		
	private float mx = 0;
	private float my = 0;
	
	private boolean click = false;
	
	private CameraGL camera;
	
	private double zoom = 1;
	
	private double zoomStep = 0.2;
	
	private float zoomFactor = 1;
	
	private boolean zoomChanged = false;
	
	private VisualAstro sun;
	
	//UI
	private Font orbitron;
	
	private TitleArrow titleArrow;
	
	private Selection selection;
	
	private LeftPanel dataPanel;
	
	private RightPanel quizPanel;
	
	public PlanetsScene(int w, int h) {
		super(w, h);
		
		camera = new CameraGL(7, 5, 57);
		
	}
	
	@Override
	public void load() {

		System.out.println("Load");
		
		dataPanel = new LeftPanel(10, 55, 410, 230);
		
		int panelW = 300;
		
		quizPanel = new RightPanel(w-panelW-10, 60, panelW, h-60-30);
		
		int size = 100;

		selection = new Selection(w/2-size/2, h/2-size/2, size, size);
		
		int titleW = 200;
		
		titleArrow = new TitleArrow(dataPanel.getX()+dataPanel.getW()/2-titleW/2, dataPanel.getY()+10, titleW, 60);
		
		ThemeManager.getInstance().getTheme().setPanelColor(new Color(0xff, 0xff, 0xff, 0x80));
				
		loading = 88;
		loadingPhrase = "Loading Font...";
		orbitron = FontLoader.getInstance().loadFont("Orbitron Medium.ttf");		
		
		loading = 100;

	}


	@Override
	public void init(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2();
				
		gl.glHint( GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST ); 
		gl.glHint( GL2.GL_POLYGON_SMOOTH_HINT, GL.GL_NICEST );
		gl.glEnable( GL.GL_LINE_SMOOTH ); 
		gl.glEnable( GL2.GL_POLYGON_SMOOTH ); 
		gl.glEnable( GL.GL_MULTISAMPLE );
		
		loadingPhrase = "Loading 3d stuff...";
				
		loadPlanets();
		
		currentPlanet = planets.get(currentPlanetIndex);
		
		loading = 10;

	}
	
	private void loadPlanets() {
		
		VisualAstro mercury = new VisualAstro("Mercury", 0.1516, "mercurymap.jpg"); //1,516 miles
		
		mercury.setX(3.598);//35,980,000 miles
		mercury.setData(new Mercury());
		
		VisualAstro venus = new VisualAstro("Venus", 0.3760, "venusmap.jpg"); //3,760 miles
		
		venus.setX(6.7240);//67,240,000 miles miles
		venus.setData(new Venus());
		
		VisualAstro earth = new VisualAstro("Earth", 0.3959, "earthmap1k.jpg"); //3,959 miles
		
		earth.setX(9.296);//92,960,000 miles
		earth.setData(new Earth());
		
		VisualAstro mars = new VisualAstro("Mars", 0.2106, "mars_1k_color.jpg"); //2,106 miles

		mars.setX(14.16);//141,600,000 miles
		mars.setData(new Mars());
		
		VisualAstro jupiter = new VisualAstro("Jupiter", 4.3441, "jupitermap.jpg"); //43,441 miles
		
		jupiter.setX(48.38);//483,800,000 miles
		jupiter.setData(new Jupiter());
		
		VisualAstro saturn = new VisualAstro("Saturn", 3.6184, "saturnmap.jpg"); //36,184 miles
		
		saturn.setX(89.07);//890,700,000 miles
		saturn.setData(new Saturn());
		
		VisualAstro uranus = new VisualAstro("Uranus", 1.5759, "uranusmap.jpg"); //15,759 miles
		
		uranus.setX(187.7);//1,787,000,000 miles
		uranus.setData(new Uranus());
		
		VisualAstro neptune = new VisualAstro("Netptune", 1.5299, "neptunemap.jpg");//15,299 miles
		
		neptune.setX(279.8);//2,798,000,000 miles
		neptune.setData(new Neptune());
		
		VisualAstro pluto = new VisualAstro("Pluto", 0.0715, "plutomap1k.jpg");//715 miles
		
		pluto.setX(367.0);//3,670,000,000 miles
		pluto.setData(new Pluto());
		
		sun = new VisualAstro("Sun", 43.2450, "sunmap.jpg");//432,450 miles
		
		sun.setX(-sun.getRadius());
		
		//planets.add(sun);
		
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
		camera.setTarget(model.getX(), model.getY(), model.getZ());
		
		
		glu.gluLookAt( camera.getX(), camera.getY(), camera.getZ(), model.getX(), model.getY(), model.getZ(), 0, 1, 0 );
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		
		float orbitDegrees = ((720*mx)/w)-360;
		
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0,0,0,0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
				
		lookCamera(gl);
		
		for(VisualAstro astro: planets) {
						
			astro.draw(gl, glu);
		}
		
		sun.draw(gl, glu);
		
		
		if(zoomChanged) {
			reshape(drawable, x, y, w, h);
		}

		gl.glFlush();

	}
	
	@Override
	public void draw(Graphic g) {

		g.setFont(orbitron);
		
		g.setFontSize(28f);
		g.write(currentPlanet.getName(), titleArrow);
				
		titleArrow.draw(g);
		
		selection.draw(g);
		
		dataPanel.draw(g);
		
		quizPanel.draw(g);
		
		drawData(g);
		
		g.setColor(Color.WHITE);
	
	}
	
	private void drawData(Graphic g) {
		
		g.setFontSize(22f);
		
		g.setColor(SVGColor.GHOST_WHITE);
		
		int offsetX = 20;
		
		int offsetY = dataPanel.getY()+100;
				
		g.drawString("Mass (kg): ", offsetX, 20+offsetY);
		
		int length = currentPlanet.getData().getMass().length();
		
		String mass = currentPlanet.getData().getMass().substring(0, length-2);
		String exponent = currentPlanet.getData().getMass().substring(length-2, length);
		
		g.drawStringExponent(mass, exponent, 200+offsetX, 20+offsetY);
		
		g.drawString("Radius (miles): ", offsetX, 60+offsetY);
		g.drawString(currentPlanet.getData().getRadius(), 200+offsetX, 60+offsetY);
		
		g.drawString("Distance (miles): ", offsetX, 100+offsetY);
		g.drawString(currentPlanet.getData().getDistance(), 200+offsetX, 100+offsetY);
		
	}
		
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport ((int)x, (int)y, (int)w, (int)h);

		gl.glMatrixMode(GL2.GL_PROJECTION);

		gl.glLoadIdentity();

		glu.gluPerspective(60.0*zoomFactor, (double) w / (double) h, 0.01, 6000.0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();

	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if (event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			currentPlanetIndex++;
			currentPlanetIndex%=planets.size();
						
			currentPlanet = planets.get(currentPlanetIndex);
			
			//camera.setZ(currentPlanet.getZ()+5);
			
		}
		
		else if (event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			currentPlanetIndex+=planets.size()-1;
			currentPlanetIndex%=planets.size();
			
			currentPlanet = planets.get(currentPlanetIndex);
			
			//camera.setZ(currentPlanet.getZ()+5);
		}
		
		if (event.isKeyDown(KeyEvent.TSK_D)) {
			camera.setOffsetX(1);
			System.out.println("cX: "+camera.getX());
		}
		
		if (event.isKeyDown(KeyEvent.TSK_A)) {
			camera.setOffsetX(-1);
			System.out.println("cX: "+camera.getX());
		}
		
		if (event.isKeyDown(KeyEvent.TSK_W)) {
			camera.setOffsetZ(+1);
			System.out.println("cZ: "+camera.getZ());
		}
		
		if (event.isKeyDown(KeyEvent.TSK_S)) {
			camera.setOffsetZ(-1);
			System.out.println("cZ: "+camera.getZ());
		}
		
		if (event.isKeyDown(KeyEvent.TSK_Q)) {
			camera.setOffsetY(+1);
			System.out.println("cY: "+camera.getY());
		}
		
		if (event.isKeyDown(KeyEvent.TSK_E)) {
			camera.setOffsetY(-1);
			System.out.println("cY: "+camera.getY());
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
		
		for(VisualAstro astro: planets) {
			
			astro.setRadius((astro.getRadius()/oldZoom)*zoom);
			
			astro.setX((astro.getX()/oldZoom)*zoom);
			
		}
		
	}
	
	private void zoomOut() {
		
		if(zoom>1) {
			
			double oldZoom = zoom;
			
			zoom-=zoomStep;
			
			for(VisualAstro astro: planets) {
				
				astro.setRadius((astro.getRadius()/oldZoom)*zoom);
				
				astro.setX((astro.getX()/oldZoom)*zoom);
				
			}
			
		}
		
	}
	
}