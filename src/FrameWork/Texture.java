package FrameWork;

import Window.BufferedImageLoader;
import Window.ExceptiiStefana;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ps, cs, es, fs;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage coin_sheet = null;
    private BufferedImage enemy_sheet = null;
    private BufferedImage finish_sheet = null;



    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[10];
    public BufferedImage[] player_jump = new BufferedImage[2];
    public BufferedImage[] coin = new BufferedImage[4];
    public BufferedImage enemy;
    public BufferedImage finish;

    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            block_sheet = loader.loadImage("/tileset/Tileset.png");
            player_sheet = loader.loadImage("/player/sheet.png");
            coin_sheet = loader.loadImage("/coin/Coin.png");
            enemy_sheet = loader.loadImage("/enemy/floare_rea.png");
            finish_sheet = loader.loadImage("/Finish/finish.png");


        }catch (Exception e){
            e.printStackTrace();
        }


        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        cs = new SpriteSheet(coin_sheet);
        es = new SpriteSheet(enemy_sheet);
        fs = new SpriteSheet(finish_sheet);

        getTextures();
    }

    private void getTextures(){
        block[0] = bs.grabImage(2,1,32,32); //grass+dirt block
        block[1] = bs.grabImage(2,2,32,32); //dirt block
        block[2] = bs.grabImage(10,3,32,32); //water block


        player[0] = ps.grabImage(4,4,64,64);//idle1
        player[1] = ps.grabImage(5,4,64,64);//idle2
        player[2] = ps.grabImage(6,4,64,64);//idle3
        player[3] = ps.grabImage(1,1,64,64);//run left 1
        player[4] = ps.grabImage(2,1,64,64);//run left 2
        player[5] = ps.grabImage(3,1,64,64);//run left 3
        player[6] = ps.grabImage(7,1,64,64);//run right 1
        player[7] = ps.grabImage(8,1,64,64);//run right 2
        player[8] = ps.grabImage(9,1,64,64);//run right 3

        coin[0] = cs.grabImage(1,1,10,10);
        coin[1] = cs.grabImage(2,1,10,10);
        coin[2] = cs.grabImage(3,1,10,10);
        coin[3] = cs.grabImage(4,1,10,10);

        enemy = es.grabImage(1,1,64,64);

        finish = fs.grabImage(1,1,13,21);


       // player_jump[0] = ps.grabImage(3,5,64,64); //jump1 left
       // player_jump[1] = ps.grabImage(2,5,64,64); //jump2 left
    }


}
