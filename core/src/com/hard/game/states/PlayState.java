package com.hard.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hard.game.FlappyBirdGame;
import com.hard.game.sprites.Bird;
import com.hard.game.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        camera.setToOrtho(false, FlappyBirdGame.WIDTH / 2, FlappyBirdGame.HEIGHT / 2);

        bird = new Bird(50, 300);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        bird.update(delta);
        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            if (tube.collides(bird.getBounds()))
                gameStateManager.set(new GameOverState(gameStateManager));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0/*camera.position.y - (camera.viewportHeight / 2)*/);
        batch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            batch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }

        batch.draw(ground, groundPosition1.x, groundPosition1.y);
        batch.draw(ground, groundPosition2.x, groundPosition2.y);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        ground.dispose();
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);

        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }
}
