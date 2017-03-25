package com.hard.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hard.game.FlappyBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = FlappyBirdGame.WIDTH;
		config.height = FlappyBirdGame.HEIGHT;
		config.title = FlappyBirdGame.title;

		new LwjglApplication(new FlappyBirdGame(), config);
	}
}
