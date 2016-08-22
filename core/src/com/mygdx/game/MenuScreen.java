package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import java.sql.Time;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by m on 2016-08-09.
 */
public class MenuScreen implements Screen {
    Sprite sprite;
    TextureAtlas atlas;
    Label label;
    Stage stage;
    Skin skin;
    Table table;
    TweenManager manager;
    Action action;
    TextButton textButtonExit;
    MyGdxGame game;
    MenuScreen(MyGdxGame game){
        this.game=game;


    }

    @Override
    public void show() {


        Label.LabelStyle  style = new Label.LabelStyle();
        sprite=new Sprite();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getWidth()));
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas(Gdx.files.internal("photos/skin/buttons.atlas"));
        skin = new Skin(Gdx.files.internal("photos/skin/ss.json"),atlas);

        Action action2 =Actions.parallel(Actions.moveTo(0,-50,.5f));

        table = new Table(skin);
        table.setPosition(0,0);
        table.setFillParent(true);

        ///////////////////////
        textButtonExit = new TextButton("Exit",skin);
        textButtonExit.pad(15);




        label = new Label("Amazing Game",skin);
        label.setFontScale(3.0f);






        //label is ready
        TextButton textButtonScroll = new TextButton("Scroller",skin);
        TextButton buttonBox2d = new TextButton("Box2d",skin);
        textButtonScroll.padBottom(40f).padTop(40f);
        textButtonScroll.padRight(84f).padLeft(84f);

        ///

        buttonBox2d.pad(40f);
        buttonBox2d.padLeft(94f).padRight(94f);
        textButtonExit.padTop(40f);
        textButtonExit.padBottom(40f);
        textButtonExit.padLeft(112f).padRight(112f);
        ///
        table.add(label).center().top().spaceBottom(200).row();
        table.add(buttonBox2d).spaceBottom(10f).row();
        table.add(textButtonScroll).spaceBottom(10f).row();
        table.add(textButtonExit);





        stage.addActor(table);
        stage.addAction(action2);

        Tween.registerAccessor(Label.class,new ActorAccessor());
        manager =new TweenManager();

        Timeline.createSequence().beginSequence()
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(1,0,1))
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(0,1,0))
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(1,0,0))
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(0,1,1))
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(1,0,1))
                .push(Tween.to(label,ActorAccessor.RGB,.8f).target(1,1,1)).end().repeat(Tween.INFINITY,0).start(manager);


        ///LISTENERY

        buttonBox2d.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new B2d(game));
                return true;
            }
        });



textButtonScroll.addListener(new ClickListener(){

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Action action =Actions.sequence(Actions.moveTo(0,-stage.getHeight(),2f),Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new FirstMainScreen(game));
            }
        }));

        stage.addAction(action);
        return super.touchDown(event, x, y, pointer, button);



    }
});


        textButtonExit.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Action action =Actions.sequence(Actions.moveTo(0,-stage.getHeight(),2f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                }));

                stage.addAction(action);
                return super.touchDown(event, x, y, pointer, button);



            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawer();
        manager.update(delta);

    }
    private void drawer() {

        stage.draw();
        stage.act();




    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resizeMenu");
        table.invalidateHierarchy();

    }

    @Override
    public void pause() {
        System.out.println("pauseMenu");
    }

    @Override
    public void resume() {
        System.out.println("resumeMenu");
    }

    @Override
    public void hide() {
        System.out.println("hideMenu");
        dispose();

    }

    @Override
    public void dispose() {
        System.out.println("disposeMenu");
        stage.dispose();


    }
}
