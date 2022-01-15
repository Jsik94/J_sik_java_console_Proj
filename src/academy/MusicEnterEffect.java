package academy;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicEnterEffect implements MusicInterface, Runnable{


    final static private String TITLE = "MusicEnterEffect";
    final static private boolean isDaemon = true;
    private static volatile MusicEnterEffect uniqueInstance = null;
    private String music = "enter_effect.wav";
    private Clip clip;
    private static boolean status = true;


    private MusicEnterEffect (){
        MyLog.d(TITLE,"Constructed.");
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        MusicEnterEffect.status = status;
    }

    public static MusicEnterEffect getInstance(){

        if(uniqueInstance ==null){

            synchronized (MusicEnterEffect.class){
                if(uniqueInstance==null){
                    uniqueInstance = new MusicEnterEffect();
                }

            }

        }

        return uniqueInstance;
    }



    @Override
    public void musicStart() {
        MyLog.d(TITLE,"Prepare to load Opening Music....");
        try {
            File musicPath = new File(getMusicDir() + music);
            if (musicPath.exists()) {
                MyLog.d(TITLE,"Music exists");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength());
                clip.close();
            }else {
                MyLog.e(TITLE,"Music does not exist");
            }

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            MyLog.e(TITLE,"Failed : " + e.getMessage());
        } catch (InterruptedException e) {
            MyLog.e(TITLE,"Failed : " + e.getMessage());
        }
    }

    @Override
    public void musicStop() {
        if(clip!=null){
            clip.stop();
        }
        MyLog.d(TITLE,"Stop Music");
    }

    @Override
    public void run() {
        if (status){

            musicStart();
        }
    }


//    public static void main(String[] args) {
//        MyLog.setStatus(true);
//        MusicEnterEffect effect = getInstance();
//        effect.musicStart();
//        System.out.println("?");
//    }
}
