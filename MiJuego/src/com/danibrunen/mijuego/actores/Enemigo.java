package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Enemigo extends Actor {

	private TextureRegion ultra;
	public Rectangle box;
	
	public Enemigo() {
		ultra = new TextureRegion(Principal.MANAGER.get("ultra.png", Texture.class), 32, 70);
		setSize(ultra.getRegionWidth(), ultra.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(-300 * delta, 0);
		box.x = getX();
		box.y = getY();
		box.width = getWidth();
		box.height = getHeight();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(ultra, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
