package Window;

import FrameWork.KeyInput;
import FrameWork.MouseInput;
import FrameWork.ObjectId;
import FrameWork.Texture;
import Objects.Block;
import Objects.Flag;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.*;

import static Objects.Player.decese;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    public static BufferedImage level = null;
    public BufferedImage level2 = null;
    public BufferedImage level3 = null;


//    private BufferedImage backgroundblue = null;
//    private BufferedImage tree1 = null;
//    private BufferedImage tree2 = null;
    private BufferedImage background = null;
    private BufferedImage menupic = null;

    //Object
    Handler handler;
    static Camera cam;
    static Texture tex;
    static Menu menu;
    public static int coinsA = 0;
    public static int coinsF = 0;


    public static int LEVEL = 1;
    public static GmState gmState = GmState.Menu;


    private void init()
    {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();
        menu = new Menu();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Level1.png"); //loading the first level
        level2 = loader.loadImage("/Level2.png"); //loading the second level
        level3 = loader.loadImage("/Level3.png"); //loading the third level
        //backgroundblue = loader.loadImage("background/blue_background.png"); //loading the blue background
        //tree1 = loader.loadImage("background/tree1.png"); //loading the first tree
        //tree2 = loader.loadImage("background/tree2.png");//loading the second tree
        background = loader.loadImage("/background/mm.jpg"); //loading the background
        menupic = loader.loadImage("/Menu/Printesa_Eva.jpg");


        cam = new Camera(0,0);
        handler = new Handler(cam);

        handler.LoadImageLevel(level);

        //handler.addObject(new Player(100,100, handler, ObjectId.Player));

        //handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());
    }
    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run()
    {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        if (gmState == GmState.Game) {
            handler.tick();
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ObjectId.Player) {
                    cam.tick(handler.object.get(i));
                }
            }
        }
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////


        //Draw Here
        g.setColor(Color.PINK);
        g.fillRect(0,0,getWidth(),getHeight());

        //g.drawImage(backgroundblue, 0, 0, this);

        if(gmState == GmState.Game) {
            g2d.translate(cam.getX(), cam.getY()); //begin of cam
            for (int xx = -background.getWidth(); xx < background.getWidth() * 5; xx += background.getWidth()) {
                g.drawImage(background, xx, 0, this);
            }
//        for(int xx = 0; xx < backgroundblue.getWidth() * 10; xx += backgroundblue.getWidth())
//            g.drawImage(backgroundblue, 0, 0, this);

//        for(int yy = 0; yy < tree1.getWidth() * 10; yy += tree1.getWidth())
//            g.drawImage(tree1, 0, 0, this);
//
//        for(int zz = 0; zz < tree2.getWidth() * 10; zz += tree2.getWidth())
//            g.drawImage(tree2, 0, 0, this);

            handler.render(g);


            g2d.translate(-cam.getX(), -cam.getY()); //end of cam
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.ITALIC, 40));
            g.drawString("" + coinsA, 30, 50);
        }else if(gmState == GmState.Menu){
            g2d.setPaint(new GradientPaint(0, 0, Color.WHITE, 800, 600, Color.PINK));
            g2d.fillRect(0, 0, 800, 600);
            g.drawImage(menupic,5,0,this);
            menu.render(g);
        }
        /////////////////////////////////
        g.dispose();
        bs.show();
    }

//    private void LoadImageLevel(BufferedImage image){
//        int w = image.getWidth();
//        int h = image.getHeight();
//
//        System.out.println("width: " + w + " " + "height: " + h);
//
//        for(int xx = 0; xx < h; xx++){
//            for(int yy = 0; yy < w; yy++){
//                int pixel = image.getRGB(xx, yy);
//                int red = (pixel >> 16) & 0xff;
//                int green = (pixel >> 8) & 0xff;
//                int blue = (pixel) & 0xff;
//
//                if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32,1, ObjectId.Block));
//                if(red == 74 && green == 82 && blue == 82) handler.addObject(new Block(xx*32, yy*32,0, ObjectId.Block));
//                if(red == 51 && green == 204 && blue == 255) handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
//                if(red == 228 && green == 250 && blue == 69) handler.addObject(new Flag(xx*32, yy*32, ObjectId.Flag));
//            }
//        }
//    }

    static void checkMonede(int x) throws  ExceptiiStefana {
        if (Game.coinsF>100) {
            throw new ExceptiiStefana("Sunt monede fantoma");
        }
        else {
            System.out.println("Distractie placuta");
        }
    }

    public static Texture getInstance(){
        return tex;
    }
    public static Connection c;
    public static  void main(String args[]) throws ExceptiiStefana {
        new Window(800,600,"EvaGame",new Game());
        checkMonede(Game.coinsF);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Chestie.db");
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet table = dbm.getTables(null, null, "Scor", null);
            if(!table.next())
                DataBase.createTable(c);
        } catch (Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
}
