package br.com.etyllica.planet.view;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.button.RoundCornerButton;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.plurality.RightPanel;

public class QuizComponent implements Drawable {

	private RightPanel quizPanel;
	
	private RoundCornerButton[] options;
	
	private ImageLabel[] labels;
	
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
		
		labels = new ImageLabel[9];
		labels[0] = new ImageLabel("icon/mercury.png");
		labels[1] = new ImageLabel("icon/venus.png");
		labels[2] = new ImageLabel("icon/earth.png");
			
		options[0].setLabel(labels[0]);
		options[1].setLabel(labels[1]);
		options[2].setLabel(labels[2]);
		
		//options[0] = new Button(20, 20, 300, 300);
		
	}

	@Override
	public void draw(Graphic g) {

		quizPanel.draw(g);
				
	}
	
	public View[] getOptions() {
		return options;
	}
	
}
