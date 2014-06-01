package br.com.etyllica.layer;

import br.com.etyllica.core.graphics.Graphic;

public class BasicImageLayer extends ImageLayer {

	@Override
	public void draw(Graphic g) {

		if(!visible) {
			return;
		}

		simpleDraw(g);

		g.dispose();
	}
	
}
