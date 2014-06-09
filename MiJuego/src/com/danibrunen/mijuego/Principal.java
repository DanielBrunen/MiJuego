package com.danibrunen.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Principal extends Game {

	public static AssetManager MANAGER;
	
	public SpriteBatch BATCH;
	
	public final AbstractScreen MENU, FIN, NIVEL1;
	
	public Principal() {
		MENU = new PantallaMenu(this);
		FIN = new PantallaDerrota(this);
		NIVEL1 = new Nivel1(this);
	}
	
	@Override
	public void create() {
		MANAGER = new AssetManager();
		BATCH = new SpriteBatch();
		
		MANAGER.load("leon.png", Texture.class);
		MANAGER.load("avispa.png", Texture.class);
		MANAGER.load("ultra.png", Texture.class);
		MANAGER.load("pad.png", Texture.class);
		MANAGER.load("balon.png", Texture.class);
		MANAGER.load("campo.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		MANAGER.load("menu.png", Texture.class);
		MANAGER.load("gameover.png", Texture.class);
		MANAGER.load("iniciar.png", Texture.class);
		MANAGER.load("salir.png", Texture.class);
		MANAGER.load("fondo.ogg", Sound.class);
		MANAGER.load("grito.ogg", Sound.class);
		MANAGER.load("avispa.ogg", Sound.class);
		
		while(!MANAGER.update()) {
			
		}
		
		setScreen(MENU);
	}

	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
		BATCH.dispose();
	}
}
