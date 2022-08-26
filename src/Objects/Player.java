package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import Window.Game;
import Window.Handler;
import Window.Camera;
import  Window.DataBase;


import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import Window.Animation;

import static Window.Game.c;

public class Player extends GameObject {

    private float width = 64, height = 64;

    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    public static int decese=0;
    public  static  int  omoruri=0;



    private int facing = 1;
    //1=right
    //-1=left

    private Handler handler;
    private Camera cam;

    Texture tex = Game.getInstance();

    private Animation playerWalk, playerWalkRight,playerWalkLeft;
    private static Player instance;
    public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
        super(x,y,id);
        this.handler = handler;
        this.cam = cam;

        //playerWalk = new Animation(10,tex.player[3],tex.player[4],tex.player[5]);
        playerWalkRight = new Animation(10,tex.player[6],tex.player[7],tex.player[8]);
        playerWalk = Animation.getInstance1(10,tex.player[3],tex.player[4],tex.player[5]);//singleton
        //playerWalkRight =  Animation.getInstance1(10,tex.player[6],tex.player[7],tex.player[8]);//singleton
        //playerWalkLeft =  Animation.getInstance1(10,tex.player[1],tex.player[5],tex.player[7]);//singleton
    }
    public static Player getInstance(float x, float y, Handler handler, Camera cam, ObjectId id){
        if(instance==null)
        {
            instance=new Player(x,y,handler,cam,id);
        }
        return instance;
    }


    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(velX < 0) facing = -1;
        if(velX > 0) facing = 1;

        if(falling || jumping)
        {
            velY += gravity/4;

            if(velY > MAX_SPEED)
                velY = MAX_SPEED;
        }
        Collision(object);

        playerWalk.runAnimation();
        playerWalkRight.runAnimation();
    }

    public void Collision(LinkedList<GameObject> object)
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Block)
            {
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + 2; //(height/2);
                    velY = 0;
                    //System.out.println("Am intrat aici - top");
                }

                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                    if(tempObject.isKillplayer()){
                        handler.restartLevel();
                        //System.out.println("am intrat aici apa");
                        Game.coinsF = Game.coinsA;
                        Game.coinsA = 0;
                        decese++;

                        try {
                            DataBase.insertRecord(c,Game.coinsF,decese,omoruri);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else
                    falling = true;
                //Right
                if(getBoundsRight().intersects(tempObject.getBounds())){
                   x = tempObject.getX() - width + 15;
                   //System.out.println("Am intrat aici - right");//(height/2);
                }
                //Left
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + 15; //width; //(height/2);
                }
            }else if(tempObject.getId() == ObjectId.Flag) {
                //switch level
                //System.out.println(Game.LEVEL);
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
            }
            else if(tempObject.getId() == ObjectId.Coin){
                if (getBounds().intersects(tempObject.getBounds())) {
                    Game.coinsA++;
                    handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId() == ObjectId.Enemy) {
                if (getBoundsLeft().intersects(tempObject.getBounds()) || getBoundsRight().intersects(tempObject.getBounds())) {
                    if (tempObject.isKillplayer()) {
                        handler.restartLevel();
                        Game.coinsF = Game.coinsA;
                        Game.coinsA = 0;
                        decese++;
                        try {
                            DataBase.insertRecord(c,Game.coinsF,decese,omoruri);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }else if(getBounds().intersects(tempObject.getBounds())){
                    if(tempObject.isKillplayer()){
                        handler.removeObject(tempObject);
                        omoruri++;
                        decese--;
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        Color myplayer = new Color(51,204,255);
        g.setColor(myplayer);
       /* if(jumping){
            if(facing == -1)
                g.drawImage(tex.player_jump[0],(int)x, (int)y,64,64,null );
            else if(facing == 1)
                g.drawImage(tex.player_jump[1],(int)x, (int)y,64,64,null );
        }else {*/
            if (velX != 0) {
                if (facing == -1)
                    playerWalk.drawAnimation(g, (int) x, (int) y, 64, 64);
                else
                    playerWalkRight.drawAnimation(g, (int) x, (int) y, 64, 64);
            } else
                g.drawImage(tex.player[1], (int) x, (int) y, 64, 64, null);
        //}
        //g.drawImage(tex.player[0],(int)x,(int)y, 64, 64,null);
       // g.fillRect((int)x,(int)y,(int)width,(int)height);

        /*Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.RED);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());*/
    }

    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2))+10, (int) ((int)y+(height/2))+10,(int)width/2-20,(int)height/2-9);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2))+7,(int)y+35,(int)width/2-15,(int)height/2-30);

    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width-20),(int)y+35,(int)5,(int)height-36);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x+18,(int)y+35,(int)5,(int)height-36);
    }


}
