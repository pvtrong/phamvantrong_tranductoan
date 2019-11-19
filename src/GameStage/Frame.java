package GameStage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/**Class Frame*/
public class Frame extends JFrame {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Frame();
    }
    private Frame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new JFrame();
        this.setTitle("Game");
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);

        GameStage screen = new GameStage(this);
        this.add(screen);
        this.setVisible(true);
    }
}
