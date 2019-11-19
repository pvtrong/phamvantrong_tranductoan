package Handler;

import GameStage.GameStage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Key implements KeyListener {
    private GameStage gameStage;
    private GameStage.KeyTyped keyTyped;
    public Key(GameStage gameStage){

        this.gameStage = gameStage;
        this.keyTyped = this.gameStage.new KeyTyped();

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 10){
            this.keyTyped.keyENTER();
        }
        if(keyCode == 27){
            this.keyTyped.keyESC();
        }
        if(keyCode == 49 ){
            this.keyTyped.startMap1();
        }
        if(keyCode == 50 ){
            this.keyTyped.startMap2();
        }
        if(keyCode == 51 ){
            this.keyTyped.startMap3();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
