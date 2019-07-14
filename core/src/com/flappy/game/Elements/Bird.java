package com.flappy.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int FALL_RATIO = -10;
    private Vector3 position;
    private Vector3 speed;
    private Texture bird;
    private static final int MOVE = 100;
    private Rectangle birdBox;
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        speed = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        birdBox = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
    }

    public void update(float DTime){
        if(position.y >0) speed.add(0,FALL_RATIO ,0);
        speed.scl(DTime);
        position.add(MOVE * DTime, speed.y,0);

        if(position.y <70) position.y =70;
        speed.scl(1/DTime);

        birdBox.setPosition(position.x,position.y);
    }

    public void jump(){
        speed.y = 250;
    }

    public Rectangle getBirdBox(){
        return birdBox;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }
}
