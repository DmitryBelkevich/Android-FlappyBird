package com.hard.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.hard.game.FlappyBirdGame;

public class HtmlLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(FlappyBirdGame.WIDTH, FlappyBirdGame.HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new FlappyBirdGame();
        }
}