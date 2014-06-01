package br.com.etyllica.planet.model.quiz;

import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.planet.model.data.DataAstro;

public class Answer {

	private DataAstro data;
	
	private ImageLabel label;
	
	public Answer(DataAstro data, String labelPath) {
		super();
				
		this.data = data;
		
		this.label = new ImageLabel(labelPath);
		
	}

	public DataAstro getData() {
		return data;
	}

	public void setData(DataAstro data) {
		this.data = data;
	}

	public ImageLabel getLabel() {
		return label;
	}

	public void setLabel(ImageLabel label) {
		this.label = label;
	}	
		
}