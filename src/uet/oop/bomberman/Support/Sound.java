package uet.oop.bomberman.Support;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;

public class Sound {

    public static String bombExplose = "res\\sound\\bomb_bang.wav";
    public static String startStage ="res\\sound\\startstage.wav";
    public static String bomberDie = "res\\sound\\bomber_die.wav";
    public static String item = "res\\sound\\item.wav";
    public static String mosterDie = "res\\sound\\monster_die.wav";
    public static String newbomb = "res\\sound\\newbomb.wav";
    public static String footsteps ="res\\sound\\buoc_chan.wav";
    public static String background ="res\\sound\\beat_thangdien.wav";
    public static String mouseRelease ="res\\sound\\mouse_release.wav";
    public static String click ="res\\sound\\select_click.wav";
    public static String win ="res\\sound\\win.wav";



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

    public static void playFootsteps(){
        Sound.play(footsteps);
    }

    public static void playBackground(){
        Sound.play(background);
    }
//
    public static void playMouseRelease(){Sound.play(mouseRelease);}

    public static void playMouseClick(){Sound.play(click);}

    public static void playGameWin(){Sound.play(win);}

}
