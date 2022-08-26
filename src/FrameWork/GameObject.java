package FrameWork;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x, y;
    protected float width = 32, height = 32;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected boolean killplayer = false;

    public GameObject(float x, float y, ObjectId id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float setX(float x){
        return this.x = x;
    }
    public float setY(float y){
        return this.y = y;
    }

    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public float setVelX(float velX){
        return this.velX = velX;
    }
    public float setVelY(float velY){
        return this.velY = velY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public ObjectId getId(){
        return id;
    }

    public boolean isKillplayer() {
        return this.killplayer;
    }

//    public Rectangle getBounds(){
//        return new Rectangle((int) getX(),(int) getY(),(int) width,(int) height);
//    }
//
//    public Rectangle getBoundsTop(){
//        return new Rectangle((int) getX()+10,(int) getY(),(int) width-20,(int) 5);
//    }
//
//    public Rectangle getBoundsBottom(){
//        return new Rectangle((int) getX()+10,(int) (getY()+height)-5,(int) width-20,(int) 5);
//    }
//
//    public Rectangle getBoundsLeft(){
//        return new Rectangle((int) getX(),(int) getY()+10,(int) 5,(int) height-20);
//    }
//
//    public Rectangle getBoundsRight(){
//        return new Rectangle((int) (getX()+width)-5,(int) getY()+10,(int) 5,(int) height-20);
//    }




}
