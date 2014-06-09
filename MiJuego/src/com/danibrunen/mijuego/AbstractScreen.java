package com.danibrunen.mijuego;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

	protected Principal juego;
	
	public AbstractScreen(Principal juego) {
		this.juego = juego;
	}
	

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
