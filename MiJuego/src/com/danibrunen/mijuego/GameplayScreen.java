package com.danibrunen.mijuego;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.danibrunen.mijuego.characters.Bala;
import com.danibrunen.mijuego.characters.BarraVidaProtagonista;
import com.danibrunen.mijuego.characters.Enemigo;
import com.danibrunen.mijuego.characters.Porteria;
import com.danibrunen.mijuego.characters.Pad;
import com.danibrunen.mijuego.characters.Protagonista;

public class GameplayScreen extends AbstractScreen {
	
	private Stage stage;
	private Protagonista leon;
	private Porteria porteria;
	private Pad padArriba, padAbajo, padDisparo;
	private float segundosSpawn;
	private BarraVidaProtagonista vidaLeon, vidaPorteria;

	public GameplayScreen(Principal game) {
		super(game);
	}
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		leon = new Protagonista();
		leon.setPosition(0, 250);
		stage.addActor(leon);
		
		porteria = new Porteria();
		porteria.setBounds(-5, 0, 5, stage.getHeight());
		stage.addActor(porteria);
		
		vidaLeon = new BarraVidaProtagonista(leon);
		vidaPorteria = new BarraVidaProtagonista(porteria);
		vidaLeon.setPosition(stage.getWidth() - 150, 20);
		vidaPorteria.setPosition(stage.getWidth() - 150, 28);
		stage.addActor(vidaLeon);
		stage.addActor(vidaPorteria);
		
		leon.setVida(0.4f);
		
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(leon);
			leon.addListener(new ControlesPC());
		}
		else if(Gdx.app.getType() == ApplicationType.Android) {
			padArriba = new Pad(0, 0);
			padAbajo = new Pad(1, 0);
			padDisparo = new Pad(0, 1);
			
			padArriba.setPosition(10, 50);
			padAbajo.setPosition(10, 10);
			padDisparo.setPosition(stage.getWidth() - 50, 10);
			
			padArriba.addListener(new ControlesAndroidArriba());
			padAbajo.addListener(new ControlesAndroidAbajo());
			padDisparo.addListener(new ControlesAndroidDisparo());
			
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padDisparo);
		}
		
		segundosSpawn = 1 + (float) Math.random();
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		segundosSpawn -= delta;
		if(segundosSpawn < 0) { //spawn nuevo enemigo
			Enemigo ultra = new Enemigo();
			ultra.setPosition(stage.getWidth(), (float) (stage.getHeight() * Math.random()));
			stage.addActor(ultra);
			
			segundosSpawn = 1 + (float) Math.random();
		}
		
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
		if(Gdx.app.getType() == ApplicationType.Android && padDisparo != null)
			padDisparo.setPosition(stage.getWidth() - 50, 10);
	}
	
	private final class ControlesPC extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				leon.velocidad.y = 300;
				return true;
			case Input.Keys.DOWN:
				leon.velocidad.y = -300;
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

	private final class ControlesAndroidDisparo extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			Bala avispa = new Bala();
			avispa.setPosition(leon.getWidth(), leon.getY() + leon.getHeight() / 2);
			stage.addActor(avispa);
			return true;
		}
	}

	private final class ControlesAndroidAbajo extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			leon.velocidad.y = -300;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			leon.velocidad.y = 0;
		}
	}

	private final class ControlesAndroidArriba extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			leon.velocidad.y = 300;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			leon.velocidad.y = 0;
		}
	}

}