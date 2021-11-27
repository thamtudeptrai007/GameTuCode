package uet.oop.bomberman.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;

public class Sound {

    public static String bombExplose = "res\\sound\\bomb_bang.wav";
    public static String startStage ="res\\sound\\startstage.wav";
    public static String bomberDie = "res\\sound\\bomber_die.wav";
    public static String item = "res\\sound\\item.wav";
    public static String mosterDie = "res\\sound\\monster_die.wav";
    public static String newbomb = "res\\sound\\newbomb.wav";
    public static String destroy ="res\\sound\\destroy.wav";

    public static void play( String filePath){
        File file = new File(filePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playDestroy(){
        Sound.play(destroy);
    }

    public static void playBombExplose(){
        Sound.play(bombExplose);
    }

    public static void playStartStage(){
        Sound.play(startStage);
    }

    public static void playBomberDie(){
        Sound.play(bomberDie);
    }

    public static void playGetNewItem(){
        Sound.play(item);
    }

    public static void playMosterDie(){
        Sound.play(mosterDie);
    }

    public static void playPlaceNewBomb(){
        Sound.play(newbomb);
    }

}
