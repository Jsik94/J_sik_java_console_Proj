package academy;

import java.util.*;

public class ThreadManage implements Subject{

    private volatile static ThreadManage uniqueInstance;

    //    HashMap<String,List<Runnable>>
    List<Runnable> myThreadSet = null;

    private ThreadManage() {
        myThreadSet = new ArrayList<>();
    }

    public void showCurrentThead() {
        System.out.println(Thread.currentThread());
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            System.out.println("Name : " + thread.getName() + ((thread.isDaemon()) ? "[Daemon]" : "[Main]"));
            System.out.println("\t" + "Group : " + thread.getThreadGroup().getName());
            System.out.println("\t\t" + "ID : " + thread.getId());
            System.out.println();


        }

        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentsGroup;


    }

    public Thread getThread(String name){
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Thread thread : map.keySet()) {
            if (!thread.getThreadGroup().getName().equals("MyThread")) continue;
            if (thread.getName().equals(name)) {

                return thread;
            }
        }

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
                System.out.println("멈췃지렁!");
                thread.interrupt();
                return true;
            }
        }
        return false;
    }


    public void runALL() {
        int i = 1111;
        for (Runnable one : myThreadSet) {
            Thread a = new Thread(new ThreadGroup("MyThread"), one, Integer.toString(i++));
            a.start();
        }
    }

    public void addAction(Runnable object) {
        myThreadSet.add(object);
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
    }

    @Override
    public void removeObserver(Observer o) {
        myThreadSet.remove(o);
    }

    @Override
    public void notifyObservers() {

    }


}
