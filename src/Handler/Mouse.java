package Handler;

import GameStage.GameStage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener {

    private GameStage gameStage;
    private GameStage.PressMouse pressMouse;
    public Mouse(GameStage gameStage){
        this.gameStage = gameStage;
        this.pressMouse = this.gameStage.new PressMouse();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            this.pressMouse.mouseDown(e);
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        this.pressMouse.mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
