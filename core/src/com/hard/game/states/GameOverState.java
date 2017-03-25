package com.hard.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hard.game.FlappyBirdGame;

public class GameOverState extends State {
    private Texture background;
    private Texture gameOver;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

        camera.setToOrtho(false, FlappyBirdGame.WIDTH / 2, FlappyBirdGame.HEIGHT / 2);

        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
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
        batch.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
    }
}