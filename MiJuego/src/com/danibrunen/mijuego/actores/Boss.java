package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Boss extends Actor {
	
	private TextureRegion boss;
	private int velocidad;
	public Rectangle box;
	public int vida;

	public Boss() {
		boss = new TextureRegion(Principal.MANAGER.get("boss.png", Texture.class), 512, 512);
		setSize(boss.getRegionWidth(), boss.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
		this.velocidad = -13;
		this.vida = 100;
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad * delta, 0);
		box.x = getX();
		box.y = getY();
		box.width = getWidth();
		box.height = getHeight();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(boss, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
