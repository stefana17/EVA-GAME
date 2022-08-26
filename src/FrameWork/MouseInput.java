package FrameWork;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Window.Game;
import Window.GmState;

public class MouseInput implements MouseListener {


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int MouseX = e.getX();
        int MouseY = e.getY();

//        public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 175, 150, 125, 50);
//        public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 175, 240, 125, 50);
        if (MouseX >= Game.WIDTH / 2 + 175 && MouseX <= Game.WIDTH / 2 + 175 + 125) {
            if (MouseY >= 150 && MouseY <= 150 + 50) {
                Game.LEVEL = 1;
                Game.gmState = GmState.Game;
            }else if(MouseY >= 240 && MouseY <= 240 + 50)
                System.exit(0);
        }
    }

    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

