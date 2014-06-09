package com.danibrunen.mijuego;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.danibrunen.mijuego.actores.Bala;
import com.danibrunen.mijuego.actores.BarraVidaProtagonista;
import com.danibrunen.mijuego.actores.Enemigo;
import com.danibrunen.mijuego.actores.Pad;
import com.danibrunen.mijuego.actores.Porteria;
import com.danibrunen.mijuego.actores.Protagonista;
import com.danibrunen.mijuego.actores.Puntuacion;

public class Nivel1 extends AbstractScreen {
	
	private Stage stage;
	private Protagonista protagonista;
	private Porteria porteria;
	private Pad padArriba, padAbajo, padDisparo;
	private float segundosSpawn;
	private BarraVidaProtagonista vidaProtagonista, vidaPorteria;
	private List<Enemigo> enemigos;
	private List<Bala> balas;
	private Puntuacion puntuacion;
	private Sound musica;

	public Nivel1(Principal game) {
		super(game);
	}
	@Override
	public void show() {
		enemigos = new ArrayList<Enemigo>();
		balas = new ArrayList<Bala>();
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, juego.BATCH);
		Gdx.input.setInputProcessor(stage);
		
		Image imagenFondo = new Image(Principal.MANAGER.get("campo.png", Texture.class));
		imagenFondo.setFillParent(true);
		stage.addActor(imagenFondo);
		
		musica = Gdx.audio.newSound(Gdx.files.internal("fondo.ogg"));
		musica.loop();
		
		protagonista = new Protagonista();
		protagonista.setPosition(0, 250);
		protagonista.box.x = protagonista.getX();
		protagonista.box.y = protagonista.getY();
		stage.addActor(protagonista);
		
		porteria = new Porteria();
		porteria.setBounds(-5, 0, 5, stage.getHeight());
		stage.addActor(porteria);
		
		vidaProtagonista = new BarraVidaProtagonista(protagonista);
		vidaPorteria = new BarraVidaProtagonista(porteria);
		vidaProtagonista.setPosition(stage.getWidth() - 150, 20);
		vidaPorteria.setPosition(stage.getWidth() - 150, 28);
		stage.addActor(vidaProtagonista);
		stage.addActor(vidaPorteria);
		
		protagonista.setVida(1);
		porteria.setVida(1);
		
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(protagonista);
			protagonista.addListener(new ControlesPC());
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
		
		puntuacion = new Puntuacion(new BitmapFont());
		puntuacion.setPosition(vidaProtagonista.getX(), 15);
		puntuacion.puntuacion = 0;
		stage.addActor(puntuacion);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		segundosSpawn -= delta;
		if(segundosSpawn < 0) //spawn nuevo enemigo
			dispararEnemigo();
		
		comprobarListas();
		comprobarColisiones();
		
		stage.draw();
	}
	
	private void comprobarListas() {
		for(int i = 0; i < enemigos.size(); i++) {
			if(enemigos.get(i).getRight() < 0) {
				enemigos.get(i).remove();
				enemigos.remove(i);
				porteria.regenerar(-0.5f);
				if(porteria.getVida() <= 0)
					juego.setScreen(juego.FIN);
			}
		}
		for(int i = 0; i < balas.size(); i++) {
			if(balas.get(i).getX() > stage.getWidth()) {
				balas.get(i).remove();
				balas.remove(i);
			}
		}
	}
	
	private void comprobarColisiones() {
		Enemigo enemigo;
		for(int i = 0; i < enemigos.size(); i++) {
			enemigo = enemigos.get(i);
			if(enemigo.box.overlaps(protagonista.box)) {
				//colision protagonista-enemigo
				enemigos.get(i).remove();
				enemigos.remove(i);
				protagonista.regenerar(-0.5f);
				Principal.MANAGER.get("grito.ogg", Sound.class).play();
				if(protagonista.getVida() <= 0)
					juego.setScreen(juego.FIN);
			} else {
				for (int j = 0; j < balas.size(); j++) {
					if(balas.get(j).box.overlaps(enemigo.box)) {
						//colision bala-enemigo
						enemigos.get(i).remove();
						enemigos.remove(i);
						balas.get(j).remove();
						balas.remove(j);
						Principal.MANAGER.get("grito.ogg", Sound.class).play();
						puntuacion.puntuacion++;
					}
				}
			}
		}
	}
	
	private void dispararEnemigo() {
		Enemigo enemigo = new Enemigo();
		enemigo.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 0.8f * stage.getHeight() * (float) Math.random());
		enemigo.box.x = enemigo.getX();
		enemigo.box.y = enemigo.getY();
		stage.addActor(enemigo);
		enemigos.add(enemigo);
		segundosSpawn = 1 + (float) Math.random();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		musica.dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
		musica.dispose();
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
				protagonista.velocidad.y = 300;
				return true;
			case Input.Keys.DOWN:
				protagonista.velocidad.y = -300;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				protagonista.velocidad.y = 0;
				return true;
			case Input.Keys.DOWN:
				protagonista.velocidad.y = 0;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyTyped(InputEvent event, char character) {
			if(character != ' ') 
				return false;
			
			Bala bala = new Bala();
			bala.setPosition(protagonista.getWidth(), protagonista.getY() + protagonista.getHeight() / 2);
			bala.box.x = bala.getX();
			bala.box.y = bala.getY();
			stage.addActor(bala);
			balas.add(bala);
			Principal.MANAGER.get("avispa.ogg", Sound.class).play();
			
			return true;
		}
	}

	private final class ControlesAndroidDisparo extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			Bala bala = new Bala();
			bala.setPosition(protagonista.getWidth(), protagonista.getY() + protagonista.getHeight() / 2);
			bala.box.x = bala.getX();
			bala.box.y = bala.getY();
			stage.addActor(bala);
			balas.add(bala);
			Principal.MANAGER.get("avispa.ogg", Sound.class).play();
			
			return true;
		}
	}

	private final class ControlesAndroidAbajo extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			protagonista.velocidad.y = -300;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			protagonista.velocidad.y = 0;
		}
	}

	private final class ControlesAndroidArriba extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			protagonista.velocidad.y = 300;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			protagonista.velocidad.y = 0;
		}
	}

}