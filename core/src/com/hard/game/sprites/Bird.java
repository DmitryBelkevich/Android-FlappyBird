package com.hard.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;

    private Texture texture;

    private Rectangle bounds;

    private Animation birdAnimation;

    private Sound flapSound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float delta) {
        birdAnimation.update(delta);

        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);

        velocity.scl(delta);

        position.add(MOVEMENT * delta, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;

        velocity.scl(1 / delta);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getCurrentFrame();
    }

    public void jump() {
        velocity.y = 250;
        flapSound.play();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flapSound.dispose();
    }
}