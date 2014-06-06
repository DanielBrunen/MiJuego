package com.danibrunen.mijuego.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Bala extends Actor {

	private TextureRegion avispa;
	
	public Bala() {
		avispa = new TextureRegion(Principal.MANAGER.get("avispa.png", Texture.class), 19, 15);
		setSize(avispa.getRegionWidth(), avispa.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(300 * delta, 0);
		if(getX() > getStage().getWidth()) {
			remove();
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(avispa, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
