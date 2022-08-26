package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import Window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject
{
    Texture tex = Game.getInstance();
    private int type;


    public Block(float x, float y, int type, ObjectId id) {
        super(x,y,id);
        this.type = type;
    }
    public Block(float x, float y, int type, ObjectId id, boolean killplayer) {
        super(x,y,id);
        this.type = type;
        this.killplayer = killplayer;
    }

    public void tick(LinkedList<GameObject> object)
    {

    }

    public void render(Graphics g)
    {
//        g.setColor(Color.white);
//        g.drawRect((int)x,(int)y,32,32);
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.green);
//        g2d.draw(getBounds());
        if(type == 0) //dirt block
            g.drawImage(tex.block[1], (int) x, (int) y,null);
        if(type == 1) //grass+dirt block
            g.drawImage(tex.block[0], (int) x, (int) y,null);
        if(type == 2) //water block
            g.drawImage(tex.block[2], (int) x, (int) y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
