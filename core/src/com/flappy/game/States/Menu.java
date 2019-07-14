package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.FlappyBird;

public class Menu extends State {
    private Texture bground;
    private Texture button;

    public Menu(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bground = new Texture("gameBackground.png");
        button = new Texture("startButton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new inGame(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
      batch.begin();
      batch.draw(bground,0,0);
      batch.draw(button,cam.position.x - button.getWidth() / 2, cam.position.y);
      batch.end();
    }
    @Override
    public void dispose() {
        bground.dispose();
        button.dispose();
    }
}
