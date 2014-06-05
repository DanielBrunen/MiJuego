package com.danibrunen.mijuego;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameplayScreen extends AbstractScreen {
	
	private Stage stage;

	public GameplayScreen(Principal game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
