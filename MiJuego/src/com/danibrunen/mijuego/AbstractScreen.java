package com.danibrunen.mijuego;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

	protected Principal game;
	
	public AbstractScreen(Principal game) {
		this.game = game;
	}
	

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
