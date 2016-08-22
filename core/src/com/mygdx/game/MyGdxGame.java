package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.TweenManager;

public class MyGdxGame extends Game {

	
	@Override
	public void create () {setScreen(new SplashScreen(this));
System.out.println("createMyGdxGame");
	}



	@Override
	public void pause() {
		super.pause();
		System.out.println("pauseMyGdxGame");
	}

	@Override
	public void resume() {
		super.resume();
		System.out.println("resumesMyGdxGame");

	}

	@Override
	public void dispose() {
		super.dispose();
		System.out.println("disposeMyGdxGame");

	}
}
