import br.com.etyllica.planet.view.PlanetsScene;
import br.com.luvia.Luvia;
import br.com.luvia.core.context.ApplicationGL;


public class Planets extends Luvia {

	private static final long serialVersionUID = 4942162883600599757L;

	public Planets(int w, int h) {
		super(w, h);
	}

	// Main program
	public static void main(String[] args) {

		Planets engine = new Planets(1024,576);
		engine.init();
		
	}
		
	@Override
	public ApplicationGL startApplication() {
		
		initialSetup("../../");
		
		//setUndecorated(true);
		
		return new PlanetsScene(w, h);
		
	}

}
