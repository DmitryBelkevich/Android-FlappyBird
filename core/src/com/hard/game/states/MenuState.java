package com.hard.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hard.game.FlappyBirdGame;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        camera.setToOrtho(false, FlappyBirdGame.WIDTH / 2, FlappyBirdGame.HEIGHT / 2);

        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            gameStateManager.set(new PlayState(gameStateManager));
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(playButton, camera.position.x - playButton.getWidth() / 2, camera.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}