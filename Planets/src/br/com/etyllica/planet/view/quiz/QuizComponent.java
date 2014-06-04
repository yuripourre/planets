package br.com.etyllica.planet.view.quiz;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
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
import br.com.etyllica.planet.model.quiz.QuestionTheme;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.plurality.RightPanel;

public class QuizComponent implements Drawable {

	private RightPanel quizPanel;
	
	private RoundCornerButton[] options;
	
	private Set<Integer> indexes = new HashSet<Integer>();
	
	private Answer[] labels;
	
	private Answer[] answers;	
	
	private String title;
	private String title2;
	
	private int rightAnswer = 0;
	
	private int questionsCount = 0;
	
	private int rightAnswersCount = 0;
	
	private QuestionTheme theme = QuestionTheme.BIGGEST_MASS;
		
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
		
		int count = 0;
		
		for(RoundCornerButton option: options) {
			option.setRoundness(20);
			option.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "generateQuestion", count));
			
			count++;
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
		
		generateQuestion(-1);
		
	}
		
	public void generateQuestion(int answerOption) {
				
		if(answerOption >= 0) {
			
			questionsCount++;
			
			if(answerOption == rightAnswer) {
				rightAnswersCount++;
				System.out.println("RIGHT");
			} else {
				System.out.println("WRONG!");
			}
			
		}
		
		indexes.clear();
		
		Random random = new Random();
						
		while(indexes.size()<3) {
			indexes.add(random.nextInt(labels.length));
		}

		title = "Which one has the";
		title2 = "biggest mass?";
		
		int count = 0;
				
		for(Integer index: indexes) {
		
			options[count].setLabel(labels[index].getLabel());
			
			answers[count] = labels[index];
						
			count++;
			
		}
		
		rightAnswer = massIsBigger(answers[0].getData().getMass(), answers[1].getData().getMass(), answers[2].getData().getMass());		
				
	}
	
	public static int massIsBigger(String mass1, String mass2, String mass3) {
	
		if(massIsBigger(mass1, mass2)&&massIsBigger(mass1, mass3)) {
			return 0;
		}
		
		if(massIsBigger(mass2, mass1)&&massIsBigger(mass2, mass3)) {
			return 1;
		}
				
		return 2;
		
	}
	
	public static boolean massIsBigger(String mass1, String mass2) {
		
		String[] parts1 = mass1.split(" ");
		
		float value1 = Float.parseFloat(parts1[0]);
				
		int exponent1 = Integer.parseInt(parts1[2].substring(parts1[2].length()-2));
		
		String[] parts2 = mass2.split(" ");
		
		float value2 = Float.parseFloat(parts2[0]);
				
		int exponent2 = Integer.parseInt(parts2[2].substring(parts2[2].length()-2));
		
		if(exponent2>exponent1) {
			
			return false;
			
		} else if(exponent2 == exponent1) {
			
			if(value2 > value1) {
				
				return false;
			}
			
		}
				
		return true;
				
	}
 
	@Override
	public void draw(Graphic g) {

		quizPanel.draw(g);
		
		g.setColor(Color.WHITE);
		
		g.drawShadow(500, 70, rightAnswersCount+"/"+questionsCount);
		
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
