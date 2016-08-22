package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by m on 2016-08-08.
 */
public class SplashScreen implements Screen {
    SpriteBatch batch;
    Texture img;
    Sprite sprite ;
    MyGdxGame game;
    TweenManager manager;
    SpriteAccessor spriteAccessor;

SplashScreen(MyGdxGame myGdxGame){

    game = myGdxGame;
    batch = new SpriteBatch();
    img = new Texture("photos/splashwall.png");
 //   sprite = new Sprite(img,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,531,89);
    sprite =new Sprite(img);
    manager = new TweenManager();
    spriteAccessor = new SpriteAccessor();
    sprite.setPosition(Gdx.graphics.getWidth()/2-img.getWidth()/2,Gdx.graphics.getHeight()/2-img.getHeight()/2);
//      batch.draw(img,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),531,89);

    Tween.registerAccessor(Sprite.class,spriteAccessor);
    Tween.set(sprite,SpriteAccessor.ALFA).target(0).start(manager);
    Tween.to(sprite,0,3).target(1).delay(0.3f).start(manager);
    Tween.to(sprite,0,3).target(0).delay(0.5f).setCallback(new TweenCallback() {
        @Override
        public void onEvent(int type, BaseTween<?> source) {
            game.setScreen(new MenuScreen(game));

        }
    }).start(manager);

    manager.update(Float.MIN_VALUE);



}




    @Override
    public void show() {
        manager.update(Float.MIN_VALUE);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
     //   drawer();
        sprite.draw(batch);
        batch.end();
       manager.update(delta);
    }
    private void drawer() {

     //  batch.draw(img,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),531,89);



    }
    @Override
    public void resize(int width, int height) {
        System.out.println("resizeSplashScreen");

    }

    @Override
    public void pause() {
        System.out.println("DpauseeSplashScreen");

    }

    @Override
    public void resume() {
        System.out.println("resumeSplashScreen");

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

    }
}
