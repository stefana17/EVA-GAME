package Objects;

import FrameWork.GameObject;

import java.awt.*;
import java.util.LinkedList;

import FrameWork.ObjectId;
import Window.*;
import FrameWork.Texture;

public class Coin extends GameObject {
    private Animation animationcoin;
    private Handler handler;
    Texture tex = Game.getInstance();

    public Coin(float x, float y, Handler handler, ObjectId id){
        super(x,y,id);
        this.handler = handler;
        animationcoin = new Animation(6,tex.coin[0],tex.coin[1],tex.coin[2],tex.coin[3]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        animationcoin.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        animationcoin.drawAnimation(g,(int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y,32,32);
    }
}
