package br.com.etyllica.planet.view.quiz;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.button.RoundCornerButton;
import br.com.etyllica.planet.model.data.Earth;
import br.com.etyllica.planet.model.data.Jupiter;
import br.com.etyllica.planet.model.data.Mars;
import br.com.etyllica.planet.model.data.Mercury;
import br.com.etyllica.planet.model.data.Neptune;
import br.com.etyllica.planet.model.data.Pluto;
import br.com.etyllica.planet.model.data.Saturn;
import br.com.etyllica.planet.model.data.Uranus;
import br.com.etyllica.planet.model.data.Venus;
import br.com.etyllica.planet.model.quiz.Answer;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.plurality.RightPanel;

public class QuizComponent implements Drawable {

	private RightPanel quizPanel;
	
	private RoundCornerButton[] options;
	
	private Answer[] labels;
	
	private Answer[] answers;	
	
	private String title;
	private String title2;
	
	public QuizComponent(int w, int h) {
		super();
		
		ThemeManager.getInstance().getTheme().setButtonColor(new Color(0xAA, 0xAA, 0xAA, 128));
		
		int panelW = 300;
		
		int panelX = w-panelW-10;
		
		int panelY = 60;
		
		int panelH = h-panelY-30;
						
		quizPanel = new RightPanel(panelX, 60, panelW, panelH);
		
		int buttonH = 90;
		
		options = new RoundCornerButton[3];
		
		int optionY = panelY+panelH/2-buttonH/2-20;
		
		int offset = 10;
		
		options[0] = new RoundCornerButton(panelX+offset, optionY, buttonH, buttonH);
		
		options[1] = new RoundCornerButton(panelX+offset, optionY+buttonH+offset, buttonH, buttonH);
		
		options[2] = new RoundCornerButton(panelX+offset, optionY+(buttonH+offset)*2, buttonH, buttonH);
		
		for(RoundCornerButton option: options) {
			option.setRoundness(20);
		}
		
		labels = new Answer[9];
		labels[0] = new Answer(new Mercury(), "icon/mercury.png");
		labels[1] = new Answer(new Venus(), "icon/venus.png");
		labels[2] = new Answer(new Earth(), "icon/earth.png");
		labels[3] = new Answer(new Mars(), "icon/mars.png");
		labels[4] = new Answer(new Jupiter(), "icon/jupiter.png");
		labels[5] = new Answer(new Saturn(), "icon/saturn.png");
		labels[6] = new Answer(new Uranus(), "icon/uranus.png");
		labels[7] = new Answer(new Neptune(), "icon/neptune.png");
		labels[8] = new Answer(new Pluto(), "icon/pluto.png");
		
		answers = new Answer[3];
		
		generateQuestion();		
		
	}
	
	public void generateQuestion() {
		
		title = "Which one has the";
		title2 = "biggest mass?";
		
		Random random = new Random();
		
		Set<Integer> indexes = new HashSet<Integer>();
		
		while(indexes.size()<3) {
			indexes.add(random.nextInt(labels.length));
		}
		
		int count = 0;
		
		for(Integer index: indexes) {
		
			options[count].setLabel(labels[index].getLabel());
			
			answers[count] = labels[index];
			
			count++;
			
		}
		
	}
 
	@Override
	public void draw(Graphic g) {

		quizPanel.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawStringShadow(quizPanel.getX(), quizPanel.getY(), quizPanel.getW(), 60, title, Color.BLACK);
		g.drawStringShadow(quizPanel.getX(), 60, quizPanel.getW(), 120, title2, Color.BLACK);
		
		for(int i=0;i<3;i++) {
			g.drawStringShadow(options[i].getX()+130, options[i].getY(), options[i].getW(), options[i].getH(), answers[i].getData().getName());
		}
				
	}
	
	public View[] getOptions() {
		return options;
	}
	
}