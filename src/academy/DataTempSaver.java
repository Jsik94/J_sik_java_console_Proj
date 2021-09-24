package academy;

public class DataTempSaver implements Observer{

    private ThreadManage threadManage;

    public DataTempSaver(ThreadManage threadManage) {
        this.threadManage = threadManage;
        threadManage.addObserver(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void run() {

    }



}
