package uet.oop.bomberman.Support;

import uet.oop.bomberman.BombermanGame;

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


    public static void playBombExplose() {
        if (BombermanGame.soundEffects) {
            Sound.play(bombExplose);
        }
    }

    public static void playStartStage() {
        if (BombermanGame.soundEffects) {
            Sound.play(startStage);
        }
    }

    public static void playBomberDie() {
        if (BombermanGame.soundEffects) {
            Sound.play(bomberDie);
        }
    }

    public static void playGetNewItem() {
        if (BombermanGame.soundEffects) {
            Sound.play(item);
        }
    }

    public static void playMosterDie() {
        if (BombermanGame.soundEffects) {
            Sound.play(mosterDie);
        }
    }

    public static void playPlaceNewBomb() {
        if (BombermanGame.soundEffects) {
            Sound.play(newbomb);
        }
    }

    public static void playFootsteps() {
        if (BombermanGame.soundEffects) {
            Sound.play(footsteps);
        }
    }

    public static void playBackground() {
        if (BombermanGame.soundBackground) {
            Sound.play(background);
        }
    }

    public static void playMouseRelease() {
        if (BombermanGame.soundEffects) {
            Sound.play(mouseRelease);
        }
    }

    public static void playMouseClick() {
        if (BombermanGame.soundEffects) {
            Sound.play(click);
        }
    }

    public static void playGameWin() {
        if (BombermanGame.soundEffects) {
            Sound.play(win);
        }
    }
}
