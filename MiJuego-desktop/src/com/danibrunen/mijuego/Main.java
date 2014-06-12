package com.danibrunen.mijuego;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

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
 * Inicia la aplicacion y haz clic en '�Go!' para empezar a jugar.
 * 
 * @author Daniel Bru�en
 */

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SOS Real Zaragoza";
		cfg.useGL20 = false;
		cfg.width = 900;
		cfg.height = 600;
		cfg.resizable = false;
		
		new LwjglApplication(new Principal(), cfg);
	}
}
