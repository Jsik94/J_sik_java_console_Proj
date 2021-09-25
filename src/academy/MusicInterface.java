package academy;

public interface MusicInterface {

    String MUSIC_DIR = "src/academy/music/";

    default String getMusicDir(){
        return MUSIC_DIR;
    }

    void musicStart();

    void musicStop();
}
