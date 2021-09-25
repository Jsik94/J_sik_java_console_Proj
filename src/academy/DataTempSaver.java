package academy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DataTempSaver implements Observer{
    final static private int DEFAULT_TIME = 300000; //5분
    final static private String NAME = "TempSaver";
    final static private boolean isDaemon = true;

    private int time;
    Map<String, ArrayList<Person>> database;
    private ThreadManage threadManage;
    FileWriterClass fwc ;

    public DataTempSaver(ThreadManage threadManage, Map<String, ArrayList<Person>> database) {
        this(threadManage,database,DEFAULT_TIME);
    }


    public DataTempSaver(ThreadManage threadManage, Map<String, ArrayList<Person>> database,int time) {
        this.threadManage = threadManage;
        threadManage.addObserver(this);
        fwc= new FileWriterClass("src/academy/logs/input/TempData_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".txt");
        this.database = database;
        this.time = time;
    }

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public boolean getDaemon() {
        return isDaemon;
    }

    @Override
    public void update() {
        Thread thr = threadManage.getThread(NAME);
        thr.interrupt();
        thr.start();
    }


    @Override
    public void run() {

        while (true){

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fwc.setData(MyUtil.makeDataFormat(database),"src/academy/logs/input/TempData_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".txt");

        }

    }



}
