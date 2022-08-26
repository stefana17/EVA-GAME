package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;

import java.awt.*;
import java.util.LinkedList;
import Window.*;

public class Flag extends GameObject {
    Texture tex = Game.getInstance();

    public Flag(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
       // g.setColor(Color.red);
//        Color myflag = new Color(228,250,69);
//        g.setColor(myflag);
//        g.fillRect((int) x, (int) y, 32, 32);
        g.drawImage(tex.finish, (int) x, (int) y,32,32,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}

