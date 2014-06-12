package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Puntuacion extends Actor {

	private BitmapFont font;
	private String objetivo;
	public int puntuacion;
	
	public Puntuacion(BitmapFont font, String objetivo) {
		this.font = font;
		this.objetivo = objetivo;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, objetivo + Integer.toString(puntuacion), getX(), getY());
	}
}
