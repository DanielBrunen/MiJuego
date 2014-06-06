package com.danibrunen.mijuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.danibrunen.mijuego.characters.Bala;
import com.danibrunen.mijuego.characters.Enemigo;
import com.danibrunen.mijuego.characters.Protagonista;

public class GameplayScreen extends AbstractScreen {
	
	private final class InputDesktopListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				leon.velocidad.y = 250;
				return true;
			case Input.Keys.DOWN:
				leon.velocidad.y = -250;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				leon.velocidad.y = 0;
				return true;
			case Input.Keys.DOWN:
				leon.velocidad.y = 0;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyTyped(InputEvent event, char character) {
			if(character != ' ') 
				return false;
			
			Bala avispa = new Bala();
			avispa.setPosition(leon.getWidth(), leon.getY() + leon.getHeight() / 2);
			stage.addActor(avispa);
			
			return true;
		}
	}

	private Stage stage;

	public GameplayScreen(Principal game) {
		super(game);
	}

	private Protagonista leon;
	private Bala avispa;
	private Enemigo ultra;
	
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		leon = new Protagonista();
		avispa = new Bala();
		ultra = new Enemigo();
		
		leon.setPosition(0, 250);
		stage.setKeyboardFocus(leon);
		leon.addListener(new InputDesktopListener());
		
		stage.addActor(leon);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

}
