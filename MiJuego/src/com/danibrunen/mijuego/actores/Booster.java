package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Booster extends Actor {

	private TextureRegion balon;
	public Rectangle box;
	
	public Booster() {
		balon = new TextureRegion(Principal.MANAGER.get("balon.png", Texture.class), 32, 32);
		setSize(balon.getRegionWidth(), balon.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(-450 * delta, 0);
		box.x = getX();
		box.y = getY();
		box.width = getWidth();
		box.height = getHeight();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(balon, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
