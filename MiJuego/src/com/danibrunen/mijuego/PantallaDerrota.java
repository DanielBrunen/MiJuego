package com.danibrunen.mijuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PantallaDerrota extends AbstractScreen {

	private Texture imagenDerrota;
	
	public PantallaDerrota(Principal juego) {
		super(juego);
	}

	@Override
	public void render(float delta) {
		juego.BATCH.begin();
		juego.BATCH.draw(imagenDerrota, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		juego.BATCH.end();
		
		if(Gdx.input.isTouched()) {
			juego.setScreen(juego.MENU);
		}
	}

	@Override
	public void show() {
		imagenDerrota = Principal.MANAGER.get("gameover.png", Texture.class);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
