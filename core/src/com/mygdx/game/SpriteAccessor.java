package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;


public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int ALFA=0;
    public static final int ALFASTAGE=5;
    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case ALFA:
                returnValues[0]=target.getColor().a;
                return  1;
            default:
                assert false;
                return -1;



        }


    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType){
            case ALFA:
                target.setColor(target.getColor().r,target.getColor().g,target.getColor().b,newValues[0]);
                break;
            default:
                assert false;


        }

    }
}
