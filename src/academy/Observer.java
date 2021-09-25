package academy;

public interface Observer extends Runnable{
    void update();
    String getName();
    boolean getDaemon();
}
