package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Bala extends Actor {

	private TextureRegion avispa;
	public Rectangle box;
	
	public Bala() {
		avispa = new TextureRegion(Principal.MANAGER.get("avispa.png", Texture.class), 19, 15);
		setSize(avispa.getRegionWidth(), avispa.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(300 * delta, 0);
		box.x = getX();
		box.y = getY();
		box.width = getWidth();
		box.height = getHeight();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(avispa, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
