package academy;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicOpening implements MusicInterface,Observer {
    final static private String TITLE = "MusicOpening";
    final static private boolean isDaemon = true;
    private String music = "title_cut.wav";
    private Clip clip;
    private Subject threadManage;

    public MusicOpening (Subject threadManage){
        this.threadManage = threadManage;
        this.threadManage.addObserver(this);
    }




    @Override
    public void musicStart()  {
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
                MyLog.d(TITLE,"Music End");
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
    public void update() {
        musicStop();
        musicStart();
    }

    @Override
    public String getName() {
        return TITLE;
    }

    @Override
    public boolean getDaemon() {
        return isDaemon;
    }

    @Override
    public void run() {

            musicStart();

    }

//    public static void main(String[] args) {
//        MyLog.setStatus(true);
//        MusicOpening musicOpening = new MusicOpening();
////        musicOpening.musicStart();
//        synchronized (musicOpening){
//            new Thread(musicOpening).start();
//        }
//        System.out.println("?");
//    }
}
