package com.danibrunen.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Principal extends Game {

	public static final AssetManager MANAGER = new AssetManager();
	
	@Override
	public void create() {
		MANAGER.load("leon.png", Texture.class);
		MANAGER.load("avispa.png", Texture.class);
		MANAGER.load("ultra.png", Texture.class);
		MANAGER.load("pad.png", Texture.class);
		MANAGER.load("balon.png", Texture.class);
		MANAGER.load("campo.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		while(!MANAGER.update()) {
			//TODO
		}
		
		setScreen(new GameplayScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
	}
}
