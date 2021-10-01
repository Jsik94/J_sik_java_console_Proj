package academy;

import java.util.*;

public class ThreadManage implements Subject{
    private final static String TITLE = "TheadManage";
    private volatile static ThreadManage uniqueInstance;

    List<Observer> myThreadSet = null;

    private ThreadManage() {
        myThreadSet = new ArrayList<>();
    }

    public void showCurrentThead() {

        MyLog.d(TITLE,"Searching same group thread.....  Group name - MyThread");
        System.out.println(Thread.currentThread());
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            sb.append("Name : " + thread.getName() + ((thread.isDaemon()) ? "[Daemon]" : "[Main]")).append("\n");
            sb.append("\t" + "Group : " + thread.getThreadGroup().getName()).append("\n");
            sb.append("\t\t" + "ID : " + thread.getId()).append("\n");
            sb.append("\t\t\t" + "State : " + thread.getState()).append("\n");

        }
        MyLog.d(TITLE,"Thread is found.");
        MyLog.d(TITLE,sb.toString());

//        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
//        ThreadGroup parentsGroup;


    }

    public List<Observer> getMyThreadSet() {
        return myThreadSet;
    }

    public Thread getThread(String name){
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        MyLog.d(TITLE,"Searching Thead..... target thread name - " + name);
        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            if (thread.getName().equals(name)) {

                MyLog.d(TITLE,"Thread is found  : " + name);
                return thread;
            }
        }

        MyLog.d(TITLE,"No exist");
        return null;
    }

    public ArrayList<String> getThreadNames(){
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        ArrayList<String> lst = new ArrayList<>();
        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            lst.add(thread.getName());
        }

        return lst;
    }


    public boolean stopThread(String name) {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            if (thread.getName().equals(name)) {
                System.out.println(thread.getName());
                thread.interrupt();
                return true;
            }
        }
        return false;
    }

    public void runOne(String name){
        for (Observer one : myThreadSet){
            if (one.getName().equals(name)){
                Thread a = new Thread(new ThreadGroup("MyThread"), one, one.getName());
                a.setDaemon(one.getDaemon());
                a.start();
            }
        }

    }

    public void runALL() {
        MyLog.d(TITLE,"Run all Observers... Count : " + myThreadSet.size());
        for (Observer one : myThreadSet) {
            Thread a = new Thread(new ThreadGroup("MyThread"), one, one.getName());
            a.setDaemon(one.getDaemon());
            a.start();
        }
    }



    public static ThreadManage getUniqueInstance() {
        if (uniqueInstance == null) {

            synchronized (ThreadManage.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ThreadManage();
                }
            }
        }

        return uniqueInstance;
    }

    @Override
    public void addObserver(Observer o) {
        myThreadSet.add(o);
        MyLog.d(TITLE,"CREATE THREAD " + o.getName());
    }

    @Override
    public void removeObserver(Observer o) {
        myThreadSet.remove(o);
        Thread a = getThread(o.getName());
        a.interrupt();
        MyLog.d(TITLE,"DELETE THREAD " + o.getName());
    }

    @Override
    public void notifyObservers() {
    }

    public void offAll(){
        MyLog.d(TITLE,"Prepare to stop all Observers... Count : " + myThreadSet.size());
        for (Observer one : myThreadSet) {
           stopThread(one.getName());
        }
    }

}
