package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;

import java.awt.*;
import java.util.LinkedList;
import Window.*;

public class Enemy extends GameObject {

    Texture tex = Game.getInstance();

    public Enemy(float x, float y, ObjectId id, boolean killplayer) {
        super(x,y,id);
        this.killplayer = killplayer;
    }

    public void tick(LinkedList<GameObject> object)
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(tex.enemy, (int) x, (int) y,32,32,null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}

