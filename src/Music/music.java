package Music;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class music {
    private String filePath;
    private Clip clip;

    public music(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        this.clip = AudioSystem.getClip();
        this.clip.open(audioInputStream);
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void play() {
        this.clip.start();
    }
}
