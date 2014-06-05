package com.danibrunen.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class Principal extends Game {

	public static final AssetManager MANAGER = new AssetManager();
	
	@Override
	public void create() {
		setScreen(new GameplayScreen(this));
	}
}
