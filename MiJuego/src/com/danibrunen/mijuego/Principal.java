package com.danibrunen.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * SOS Real Zaragoza. Mi primer juego en Java usando LibGDX.
 * Controles:
 *  - Movimiento: flechas de arriba y abajo.
 *  - Disparo: espacio.
 * NPCs:
 *  - Avispas: disparalas pulsando espacio para que derroten a tus enemigos.
 *  - Ultras: intentaran traspasar tu linea de porteria. Te quitaran 50% de vida
 *    si chocan contigo, o 50% de vida a tu porteria si llegan a ella. Podras
 *    derrotarlos con una avispa.
 *  - Balones: ellos son aliados. Si los paras sumaran 20% de tu vida y 20% de la
 *    vida de la porteria. Si no los recoges no pasa nada. (Nota: la salud de tu
 *    porteria se ira recuperando lentamente.)
 *  - Boss: deberas impactarle 100 veces para derrotarlo. Si te toca estas perdido.
 * Nivel 1:
 *  - Spawn: ultras.
 *  - Objetivo: derrotar 30 ultras.
 * Nivel 2:
 *  - Spawn: ultras y balones.
 *  - Objetivo: derrotar 50 ultras.
 * Nivel 3:
 *  - Spawn: ultras, balones, y boss.
 *  - Objetivo: derrotar al boss.
 * Inicia la aplicacion y haz clic en '¡Go!' para empezar a jugar.
 * 
 * @author Daniel Bruñen
 */

public class Principal extends Game {

	public static AssetManager MANAGER;
	public SpriteBatch BATCH;
	public final AbstractScreen MENU, FIN, NIVEL1, NIVEL2, NIVEL3;
	
	public Principal() {
		MENU = new PantallaMenu(this);
		FIN = new PantallaDerrota(this);
		NIVEL1 = new Nivel1(this);
		NIVEL2 = new Nivel2(this);
		NIVEL3 = new Nivel3(this);
	}
	
	@Override
	public void create() {
		MANAGER = new AssetManager();
		BATCH = new SpriteBatch();
		
		MANAGER.load("menu.png", Texture.class);
		MANAGER.load("iniciar.png", Texture.class);
		MANAGER.load("salir.png", Texture.class);
		MANAGER.load("leon.png", Texture.class);
		MANAGER.load("leon2.png", Texture.class);
		MANAGER.load("ultra.png", Texture.class);
		MANAGER.load("ultra2.png", Texture.class);
		MANAGER.load("avispa.png", Texture.class);
		MANAGER.load("balon.png", Texture.class);
		MANAGER.load("boss.png", Texture.class);
		MANAGER.load("fondo.png", Texture.class);
		MANAGER.load("fondo2.png", Texture.class);
		MANAGER.load("fondo3.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		MANAGER.load("gameover.png", Texture.class);
		
		MANAGER.load("menu.ogg", Sound.class);
		MANAGER.load("fondo.ogg", Sound.class);
		MANAGER.load("fondo2.ogg", Sound.class);
		MANAGER.load("fondo3.ogg", Sound.class);
		MANAGER.load("avispa.ogg", Sound.class);
		MANAGER.load("grito.ogg", Sound.class);
		MANAGER.load("booster.mp3", Sound.class);
		MANAGER.load("hit.mp3", Sound.class);
		
		while(!MANAGER.update()) {
			
		}
		
		setScreen(MENU);
	}

	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
		BATCH.dispose();
	}
}
