package com.danibrunen.mijuego;

import java.util.ArrayList;
import java.util.List;
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
import com.danibrunen.mijuego.actores.Booster;
import com.danibrunen.mijuego.actores.Enemigo;
import com.danibrunen.mijuego.actores.Porteria;
import com.danibrunen.mijuego.actores.Protagonista;
import com.danibrunen.mijuego.actores.Puntuacion;

public class Nivel2 extends AbstractScreen {

	private Stage stage;
	private Protagonista protagonista;
	private Porteria porteria;
	private float segundosSpawn;
	private BarraVidaProtagonista vidaProtagonista, vidaPorteria;
	private List<Enemigo> enemigos;
	private List<Bala> balas;
	private List<Booster> balones;
	private Puntuacion puntuacion;
	private Sound musica;

	public Nivel2(Principal game) {
		super(game);
	}
	
	@Override
	public void show() {
		enemigos = new ArrayList<Enemigo>();
		balas = new ArrayList<Bala>();
		balones = new ArrayList<Booster>();
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, juego.BATCH);
		Gdx.input.setInputProcessor(stage);
		
		Image imagenFondo = new Image(Principal.MANAGER.get("fondo2.png", Texture.class));
		imagenFondo.setFillParent(true);
		stage.addActor(imagenFondo);
		
		musica = Gdx.audio.newSound(Gdx.files.internal("fondo2.ogg"));
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
		
		stage.setKeyboardFocus(protagonista);
		protagonista.addListener(new Controles());
		
		puntuacion = new Puntuacion(new BitmapFont(), "Eliminados (de 50): ");
		puntuacion.setPosition(vidaProtagonista.getX(), 15);
		puntuacion.puntuacion = 0;
		stage.addActor(puntuacion);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		segundosSpawn -= delta;
		if(segundosSpawn < 0) {
			dispararEnemigo(-270);
			dispararBalon();
			dispararEnemigo(-320);
		}
		
		updateEnemigos(delta);
		protagonista.update();
		
		comprobarListas();
		comprobarColisiones();
		
		stage.draw();
	}
	
	private void updateEnemigos(float delta) {
		for(Enemigo enemigo: enemigos) {
			enemigo.update(delta);
		}
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
		for(int i = 0; i < balones.size(); i++) {
			if(balones.get(i).getRight() < 0) {
				balones.get(i).remove();
				balones.remove(i);
			}
		}
	}
	
	private void comprobarColisiones() {
		Enemigo enemigo;
		Booster balon;
		
		for(int i = 0; i < enemigos.size(); i++) {
			enemigo = enemigos.get(i);
			if(enemigo.box.overlaps(protagonista.box)) {
				//colision enemigo-protagonista
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
						if(puntuacion.puntuacion >= 50)
							juego.setScreen(juego.NIVEL3);
					}
				}
			}
		}
		
		for(int i = 0; i < balones.size(); i++) {
			balon = balones.get(i);
			if(balon.box.overlaps(protagonista.box)) {
				//colision balon-protagonista
				balones.get(i).remove();
				balones.remove(i);
				porteria.regenerar(0.2f);
				protagonista.regenerar(0.2f);
				Principal.MANAGER.get("booster.mp3", Sound.class).play();
			}
		}
	}
	
	private void dispararEnemigo(int velocidad) {
		Enemigo enemigo = new Enemigo(velocidad);
		enemigo.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 0.8f * stage.getHeight() * (float) Math.random());
		enemigo.box.x = enemigo.getX();
		enemigo.box.y = enemigo.getY();
		stage.addActor(enemigo);
		enemigos.add(enemigo);
		segundosSpawn = 1 + (float) Math.random();
	}
	
	private void dispararBalon() {
		Booster balon = new Booster();
		balon.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 0.8f * stage.getHeight() * (float) Math.random());
		balon.box.x = balon.getX();
		balon.box.y = balon.getY();
		stage.addActor(balon);
		balones.add(balon);
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
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}
	
	private final class Controles extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				protagonista.velocidad.y = 300;
				return true;
			case Input.Keys.DOWN:
				protagonista.velocidad.y = -300;
				return true;
			case Input.Keys.SPACE:
				Bala bala = new Bala();
				bala.setPosition(protagonista.getWidth(), protagonista.getY() + protagonista.getHeight() / 2);
				bala.box.x = bala.getX();
				bala.box.y = bala.getY();
				stage.addActor(bala);
				balas.add(bala);
				Principal.MANAGER.get("avispa.ogg", Sound.class).play();
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
	}
}
