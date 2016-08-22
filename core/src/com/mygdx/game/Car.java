package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

/**
 * Created by m on 2016-08-22.
 */
public class Car extends InputAdapter implements Screen{
    private Body chassis,leftWheel,rightWheel;
    private WheelJoint leftAxix,rightAxix;
    private float speedEngine = 100;
    Car(World world, FixtureDef chassisFixtureDef,FixtureDef wheelFixtureDef,float x, float y,float width,float height){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,5);
        //chassis
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(new float[]{-width/2,-height/2,width/2,-height/2,width/2*.75f,height/2,-width/2*.8f,height/2*.8f});
        chassisFixtureDef.shape=polygonShape;
        chassis = world.createBody(bodyDef);
        chassis.createFixture(chassisFixtureDef);


        //leftWheel
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(height/3f);

        wheelFixtureDef.shape=circleShape;

        leftWheel = world.createBody(bodyDef);
        leftWheel.createFixture(wheelFixtureDef);

        //rightWheel
        CircleShape circleShape2 = new CircleShape();
        circleShape2.setRadius(height/3f);
        wheelFixtureDef.shape=circleShape2;
        rightWheel = world.createBody(bodyDef);
        rightWheel.createFixture(wheelFixtureDef);



        //leftAxix

        WheelJointDef wheelJointDef = new WheelJointDef();
        wheelJointDef.bodyA=chassis;
        wheelJointDef.bodyB=leftWheel;
        wheelJointDef.localAnchorA.set(-width/2,-height/2);
        wheelJointDef.localAxisA.set(0,1);
        wheelJointDef.frequencyHz=3f;
        wheelJointDef.maxMotorTorque=100f;

        leftAxix = (WheelJoint) world.createJoint(wheelJointDef);


        //rightAxix

        WheelJointDef wheelJointDef2 = new WheelJointDef();
        wheelJointDef2.bodyA=chassis;
        wheelJointDef2.bodyB=rightWheel;
        wheelJointDef2.localAnchorA.set(width/2,-height/2);
        rightAxix = (WheelJoint)world.createJoint(wheelJointDef2);










    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                leftAxix.enableMotor(true);
                leftAxix.setMotorSpeed(-speedEngine);
                System.out.println("keydown");
                break;

            case Input.Keys.S:
                leftAxix.enableMotor(true);
                leftAxix.setMotorSpeed(speedEngine);
                System.out.println("keydowns");
                break;

        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.W:
            case Input.Keys.S:
                leftAxix.enableMotor(false);

                break;


        }

        return true;
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }










































    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
