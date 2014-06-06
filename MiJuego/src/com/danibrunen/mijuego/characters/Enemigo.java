package com.danibrunen.mijuego.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Enemigo extends Actor {

	private TextureRegion ultra;
	
	public Enemigo() {
		ultra = new TextureRegion(Principal.MANAGER.get("ultra.png", Texture.class), 32, 70);
		setSize(ultra.getRegionWidth(), ultra.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(-300 * delta, 0);
		if(getRight() < 0)
			remove();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(ultra, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
