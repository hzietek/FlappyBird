package com.flappy.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTT, positionBT;
    private Random rng;
    private Rectangle topCollision, botCollision;

    private static final int GAP = 110;
    private static final int MIN = 110;
    public Tube (float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rng = new Random();

        positionTT = new Vector2(x,rng.nextInt(130) + MIN + GAP);
        positionBT = new Vector2( x,positionTT.y - GAP - bottomTube.getHeight());

        topCollision = new Rectangle(positionTT.x, positionTT.y, topTube.getWidth(), topTube.getHeight());
        botCollision = new Rectangle(positionBT.x, positionBT.y, bottomTube.getWidth(), bottomTube.getHeight());


    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTT() {
        return positionTT;
    }

    public Vector2 getPositionBT() {
        return positionBT;
    }

//    public void reposition(float y){
//        positionTT.set(rng.nextInt(150) + MIN, y);
//        positionBT.set(positionTT.x + GAP + bottomTube.getHeight(), y);
//    }

    public boolean collision(Rectangle bird){
        return bird.overlaps(topCollision) || bird.overlaps(botCollision);
    }
}
