package com.flappy.game.Elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Counter {
    private String counterString;
    private Vector2 position;
    public Counter(){
        position = new Vector2(225,30);
    }

    public void update(float DTime, int c){
        position.add(100*DTime,0);
        counterString=Integer.toString(c);
    }

    public String getCounterString() {
        return counterString;
    }

    public Vector2 getPosition() {
        return position;
    }
}
