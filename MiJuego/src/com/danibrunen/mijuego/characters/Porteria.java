package com.danibrunen.mijuego.characters;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Porteria extends Actor implements VidaProtagonista {
	
	private float vida;
	private float tiempoRegeneracion = 0;
	
	public Porteria() {
		vida = 1;
	}

	@Override
	public float getVida() {
		return vida;
	}
	
	@Override
	public void setVida(float vida) {
		this.vida = vida;
		comprobarVida();
	}
	
	@Override
	public void regenerar(float nuevaVida) {
		vida += nuevaVida;
	}
	
	private void comprobarVida() {
		if(vida < 0) vida = 0;
		if(vida > 1) vida = 1;
	}
	
	@Override
	public void act(float delta) {
		tiempoRegeneracion += delta;
		if(tiempoRegeneracion > 1 && vida < 1)
			vida += 0.01f;
		tiempoRegeneracion = 0;
	}
}
