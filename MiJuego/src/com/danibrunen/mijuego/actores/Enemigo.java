package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Enemigo extends Actor {

	private TextureRegion ultra;
	private Animation animacion;
	private float stateTime;
	public Rectangle box;
	private int velocidad;
	
	public Enemigo(int velocidad) {
		ultra = new TextureRegion(Principal.MANAGER.get("ultra.png", Texture.class), 32, 70);
		setSize(ultra.getRegionWidth(), ultra.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
		this.velocidad = velocidad;
		
		animacion = new Animation(0.25f, new TextureRegion[]{
				new Sprite(new TextureRegion(Principal.MANAGER.get("ultra.png", Texture.class), 32, 70)),
				new Sprite(new TextureRegion(Principal.MANAGER.get("ultra2.png", Texture.class), 32, 70))
		});
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
		batch.draw(ultra, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void update(float delta) {
		stateTime += delta;
		ultra = animacion.getKeyFrame(stateTime, true);
	}
}
