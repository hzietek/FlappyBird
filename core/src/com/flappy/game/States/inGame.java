package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappy.game.Elements.Bird;
import com.flappy.game.Elements.Counter;
import com.flappy.game.Elements.Tube;
import com.flappy.game.FlappyBird;

public class inGame extends State {
    private Bird bird;
    private Counter counter;
    private Texture gameBackground;
    private Texture terrain;
    private Tube tube;
    private static final int TUBE_COUNT = 100;
    private BitmapFont font;
    private com.badlogic.gdx.utils.Array<Tube> tubes;
    private Vector2 tPos1, tPos2;
    private Integer change_counter=0;
    public inGame(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(200,150);
        cam.setToOrtho(false, FlappyBird.WIDTH /2,FlappyBird.HEIGHT/2);
        gameBackground = new Texture("gameBackground.png");
        tubes = new Array<Tube>();
        terrain = new Texture("ground.png");
        tPos1 = new Vector2(cam.position.y - cam.viewportWidth/2,-40);
        tPos2 = new Vector2((cam.position.y - cam.viewportWidth/2) + terrain.getWidth(), -40);
        font = new BitmapFont();
        counter = new Counter();
        for (int i=0; i<= TUBE_COUNT; i++){
            if(i!=0 && i!=1 && i!=2)
            tubes.add(new Tube(i * 200));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        Terrain();
        bird.update(dt);
        change_counter++;
        counter.update(dt,change_counter);
        cam.position.x = bird.getPosition().x + 80;
        for (Tube tube : tubes){
            if (tube.collision(bird.getBirdBox())){
                gsm.set(new inGame(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(gameBackground, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(),tube.getPositionTT().x,tube.getPositionTT().y);
            sb.draw(tube.getBottomTube(),tube.getPositionBT().x,tube.getPositionBT().y);
        }
        sb.draw(terrain,tPos1.x,tPos1.y);
        sb.draw(terrain,tPos2.x,tPos2.y);
        font.draw(sb,"SCORE [M]: "+counter.getCounterString(),counter.getPosition().x,counter.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    private void Terrain(){
        if(cam.position.x - (cam.viewportWidth/2)>tPos1.x + terrain.getWidth())
            tPos1.add(terrain.getWidth()*2,0);
        if(cam.position.x - (cam.viewportWidth/2)>tPos2.x + terrain.getWidth())
            tPos2.add(terrain.getWidth()*2,0);
    }
}
