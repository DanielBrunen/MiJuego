package com.danibrunen.mijuego.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Pad extends Actor {
	
	private TextureRegion boton;
	
	public Pad(int x, int y) {
		boton = new TextureRegion(Principal.MANAGER.get("pad.png", Texture.class), 32 * x, 32 * y, 32, 32);
		setSize(32, 32);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(boton, getX(), getY());
	}
}
