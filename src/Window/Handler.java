package Window;

import FrameWork.Factory;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import Objects.*;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.LinkedList;

import static Objects.Player.decese;
import static Objects.Player.omoruri;
import static Window.Game.c;

public class Handler
{
    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    private Camera cam;
    private Factory factory = new Factory();

    private BufferedImage level2 = null, level3 = null;

    public Handler(Camera cam){
        this.cam = cam;

        BufferedImageLoader loader = new BufferedImageLoader();
        level2 = loader.loadImage("/Level2.png"); //loading the second level
        level3 = loader.loadImage("/Level3.png"); //loading the third level
    }

    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        System.out.println("width: " + w + " " + "height: " + h);

        for(int xx = 0; xx < h; xx++){
            for(int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 255 && blue == 255) addObject(factory.MakeObject(xx*32,yy*32,1,ObjectId.Block,null,null,false));//new Block(xx*32, yy*32,1, ObjectId.Block));
                if(red == 74 && green == 82 && blue == 82) addObject(factory.MakeObject(xx*32, yy*32,0, ObjectId.Block,null,null,false));//new Block(xx*32, yy*32,0, ObjectId.Block));
                if(red == 50 && green == 15 && blue == 180) addObject(factory.MakeObject(xx*32, yy*32,2, ObjectId.Block,null,null,true));//new Block(xx*32, yy*32,2, ObjectId.Block,true));
                if(red == 51 && green == 204 && blue == 255) addObject(factory.MakeObject(xx*32, yy*32,-1,ObjectId.Player,cam,this,false));//new Player(xx*32, yy*32, this, cam, ObjectId.Player));
                if(red == 228 && green == 250 && blue == 69) addObject(factory.MakeObject(xx*32, yy*32,-1,ObjectId.Flag,null,null,false));//new Flag(xx*32, yy*32, ObjectId.Flag));
                if(red == 148 && green == 19 && blue == 174) addObject(factory.MakeObject(xx*32,yy*32,-1,ObjectId.Coin,null,this,false));//new Coin(xx*32, yy*32,this, ObjectId.Coin));
                if(red == 29 && green == 214 && blue == 29) addObject(factory.MakeObject(xx*32, yy*32,-1,ObjectId.Enemy,null,null,true));//new Enemy(xx*32, yy*32, ObjectId.Enemy,true));
            }
        }
    }

    public void switchLevel(){
        clearLevel();
        cam.setX(0);
        switch(Game.LEVEL){
            case 1:
                LoadImageLevel(level2);
                break;
            case 2:
                LoadImageLevel(level3);
                break;
        }
        Game.LEVEL++;
    }

    public void restartLevel(){
        clearLevel();
        cam.setX(0);
        if(Game.LEVEL == 1){
            LoadImageLevel(Game.level);
        }else if(Game.LEVEL == 2){
            LoadImageLevel(level2);
        }else if(Game.LEVEL == 3){
            LoadImageLevel(level3);
        }
    }

    public void clearLevel(){
        object.clear();
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

//    public void createLevel()
//    {
//        for(int yy = 0; yy < Game.WIDTH+32; yy += 32)
//            addObject(new Block(0,yy, ObjectId.Block));
//
//        for(int xx = 0; xx < Game.WIDTH*2; xx += 32)
//            addObject(new Block(xx,Game.HEIGHT-32, ObjectId.Block));
//
//        for(int xx = 200; xx < 600; xx += 32)
//            addObject(new Block(xx,400, ObjectId.Block));
//    }
}
