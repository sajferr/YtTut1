package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by m on 2016-08-19.
 */
public class B2d implements Screen {
    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    OrthographicCamera camera;
    float time = 1/60f;
    int velocityint = 8;
    int velocityasd = 3;
    MyGdxGame game;
    Vector2 vectorFor = new Vector2();

    Car car;
    BodyDef bodyDef;


    public B2d(MyGdxGame game) {
        this.game=game;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(car.chassis.getPosition().x,car.chassis.getPosition().y,0);
        camera.update();
        box2DDebugRenderer.render(world,camera.combined);
        world.step(time,velocityint,velocityasd);
        System.out.println(vectorFor.y);


    }
    @Override
    public void show() {
        world = new World(new Vector2(0, -09.79f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 3f;
        fixtureDef.friction = .4f;
        fixtureDef.restitution = .3f;

        FixtureDef wheelFixtureDef = new FixtureDef();
        wheelFixtureDef.density = fixtureDef.density - .5f;
     //   wheelFixtureDef.friction = 1f;
        wheelFixtureDef.friction = 5000f;


        ////body def
        car = new Car(world, fixtureDef, wheelFixtureDef, 0, 3, 3, 1.5f);


        ///shape ground

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-300, 0), (new Vector2(300, 0))});
        //fixture ground
        fixtureDef.shape = chainShape;
        fixtureDef.restitution = .5f;
        fixtureDef.density = 5f;
        Body body2;
        body2 = world.createBody(bodyDef);
        body2.createFixture(fixtureDef);
        body2.applyAngularImpulse(50f, true);


        Gdx.input.setInputProcessor(new InputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                if (keycode == Input.Keys.ESCAPE) {
                    game.setScreen(new MenuScreen(game));
                    return true;
                }
                return false;
            }


            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                camera.zoom += amount / 10f;
                return super.scrolled(amount);
            }


        },car));


    }



    @Override
    public void resize(int width, int height) {
        camera.viewportHeight=height/10;
        camera.viewportWidth=width/10;
        camera.update();


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {

    }
}
