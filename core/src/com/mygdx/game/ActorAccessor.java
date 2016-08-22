package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by m on 2016-08-09.
 */
public class ActorAccessor implements TweenAccessor<Actor>{

    public static final int ALPHA=0;
    public static final int RGB=1;
    public static final int Y=2;
    public int howManyY=50;


    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case Y:
                returnValues[0]= Gdx.graphics.getHeight()+200;

                return 1;

            case RGB:
                returnValues[0]=target.getColor().r;
                returnValues[1]=target.getColor().g;
                returnValues[2]=target.getColor().b;
                return 3;
            case ALPHA:
                System.out.println("getAlpha");
               returnValues[0]=target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }

    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType){
            case Y:
                target.setY(newValues[0]);
                break;
            case RGB:
                target.setColor(newValues[0],newValues[1],newValues[2],target.getColor().a);

                break;
            case ALPHA:

                target.setColor(target.getColor().r,target.getColor().g,target.getColor().b,newValues[0]);
                break;
                default:
                    assert false;


        }

    }
}
