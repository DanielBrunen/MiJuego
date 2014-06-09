package com.danibrunen.mijuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PantallaMenu extends AbstractScreen {
	
	private Texture fondo, btJugar, btSalir;
	private Stage stage;

	public PantallaMenu(Principal juego) {
		super(juego);
	}
	
	@Override
	public void show() {
		fondo = Principal.MANAGER.get("menu.png", Texture.class);
		btJugar = Principal.MANAGER.get("iniciar.png", Texture.class);
		btSalir = Principal.MANAGER.get("salir.png", Texture.class);
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, juego.BATCH);
		
		Image imagenFondo = new Image(fondo);
		imagenFondo.setFillParent(true);
		stage.addActor(imagenFondo);
		
		Image imagenJugar = new Image(btJugar);
		imagenJugar.setBounds(50, 10, stage.getWidth() / 3, stage.getHeight() / 4);
		imagenJugar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				juego.setScreen(juego.NIVEL1);
				return true;
			}
		});
		stage.addActor(imagenJugar);
		
		Image imagenSalir = new Image(btSalir);
		imagenSalir.setBounds(stage.getWidth() - 50 - (stage.getWidth() / 3), 10, stage.getWidth() / 3, stage.getHeight() / 4);
		imagenSalir.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});
		stage.addActor(imagenSalir);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}
	
	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

	
}
