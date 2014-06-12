package com.danibrunen.mijuego.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.danibrunen.mijuego.Principal;

public class Protagonista extends Actor implements VidaProtagonista {

	private TextureRegion leon;
	public Rectangle box;
	private float vida;
	
	public Vector2 velocidad = new Vector2(0, 0);
	
	public Protagonista() {
		leon = new TextureRegion(Principal.MANAGER.get("leon.png", Texture.class), 39, 75);
		setSize(leon.getRegionWidth(), leon.getRegionHeight());
		box = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad.x * delta, velocidad.y * delta);
		box.x = getX();
		box.y = getY();
		box.width = getWidth();
		box.height = getHeight();
		
		if(getX() < 0) {
			setX(0);
			velocidad.x = 0;
		} else if(getRight() > getStage().getWidth()) {
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}
		
		if(getY() < 0) {
			setY(0);
			velocidad.y = 0;
		} else if(getTop() > getStage().getHeight()) {
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(leon, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			leon = new TextureRegion(Principal.MANAGER.get("leon2.png", Texture.class), 39, 75);
		} else {
			leon = new TextureRegion(Principal.MANAGER.get("leon.png", Texture.class), 39, 75);
		}
	}

	@Override
	public float getVida() {
		return vida;
	}

	@Override
	public void setVida(float vida) {
		this.vida = vida;
	}

	@Override
	public void regenerar(float nuevaVida) {
		vida += nuevaVida;
		comprobarVida();
	}
	
	private void comprobarVida() {
		if(vida < 0) vida = 0;
		if(vida > 1) vida = 1;
	}
}
