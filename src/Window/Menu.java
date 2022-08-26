package Window;
import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 175, 150, 125, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 175, 240, 125, 50);


    public void Menu(){

    }
    public void render(Graphics g){
        Font titleFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(titleFont);
        g.setColor(Color.white);

        Font buttonFont = new Font("Arial", Font.BOLD, 35);
        g.setFont(buttonFont);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(4f));

       // g.drawString("Printesa Eva", Game.WIDTH/2 + 150,130);
        //PLAY
        g.setColor(Color.pink);
        g.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
        g.setColor(Color.magenta);
        graphics2D.draw(playButton);
        g.setColor(Color.magenta);
        g.drawString("PLAY", playButton.x + 22, playButton.y + 37);


        //QUIT
        g.setColor(Color.pink);
        g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        g.setColor(Color.magenta);
        graphics2D.draw(quitButton);
        g.setColor(Color.magenta);
        g.drawString("QUIT", quitButton.x + 13, quitButton.y + 37);
    }
}
