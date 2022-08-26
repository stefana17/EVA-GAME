package FrameWork;
import Objects.*;
import Window.*;

public class Factory {
    public GameObject MakeObject(float x, float y,int type, ObjectId id,Camera cam, Handler handler,boolean killplayer){
        if(id == ObjectId.Player){
            //return Player.getInstance(x,y,handler,cam,id);
            return new Player(x,y,handler,cam,id);
        }else if(id == ObjectId.Block){
            return new Block(x,y,type,id,killplayer);
        }else if(id == ObjectId.Coin){
            return new Coin(x,y,handler,id);
        }else if(id == ObjectId.Flag){
            return new Flag(x,y,id);
        }else if(id == ObjectId.Enemy){
            return new Enemy(x,y,id,killplayer);
        }
        return null;
    }
}
