package com.danibrunen.mijuego.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class BarraVidaProtagonista extends Actor {
	
	private Texture barra;
	private VidaProtagonista vida;
	
	public BarraVidaProtagonista(VidaProtagonista vida) {
		barra = Principal.MANAGER.get("vida.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.vida = vida;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0, (int) (getWidth() * vida.getVida()), (int) getHeight());
	}
}
