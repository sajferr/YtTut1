package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.security.Key;
import java.util.ArrayList;

/**
 * Created by m on 2016-08-09.
 */
public class FirstMainScreen implements Screen {
    MyGdxGame game;
    Table table;
    Stage stage;
    ScrollPane panel;
    ArrayList<String>arrayList;
    List<String> list;
    Skin skin;
    TextureAtlas atlas;

    public FirstMainScreen(MyGdxGame game) {
        this.game=game;

    }

    @Override
    public void show() {

       atlas = new TextureAtlas(Gdx.files.internal("photos/skin2/butblue.atlas"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new ClickListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.ESCAPE){
                    game.setScreen(new MenuScreen(game));
                }
                return super.keyDown(event, keycode);
            }
        });
       skin = new Skin(Gdx.files.internal("photos/skin2/skin2.json"),atlas);

        table = new Table(skin)    ;
        table.setDebug(false);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        list = new List<String>(skin);
        String [] array = {"Chelsea","United","Real MAdryt,Manchester City","addada","adada","dad","ad","ad","dada","dadada","adadada","dadadadadadadadadada","ads","dada","dadadasad","dadadasdawda","wdadadada","dawdadadada","daadwdada"};
        list.setItems(array);
        panel = new ScrollPane(list,skin);


        table.add().width(table.getWidth()/3);
       table.add("Choose one").top().expandY().width(table.getWidth()/3);
        table.add().width(table.getWidth()/3).row();
      table.add(panel).expandY();



        stage.addActor(table);





    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();

    }





































    @Override
    public void resize(int width, int height) {
        table.invalidateHierarchy();

    }

    @Override
    public void pause() {
System.out.println("pauseFirstMainScreen");


    }

    @Override
    public void resume() {
        System.out.println("resumeFirstMainScreen");


    }

    @Override
    public void hide() {
        System.out.println("hideFirstMainScreen");
        dispose();


    }

    @Override
    public void dispose() {
        System.out
                .println("disposeFirstMainScreen");
    }
}
