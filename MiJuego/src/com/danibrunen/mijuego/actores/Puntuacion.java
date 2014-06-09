package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Puntuacion extends Actor {

	private BitmapFont font;
	public int puntuacion;
	
	public Puntuacion(BitmapFont font) {
		this.font = font;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, "Ultras eliminados: " + Integer.toString(puntuacion), getX(), getY());
	}
}
