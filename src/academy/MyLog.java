package academy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLog {

    private static boolean status = false;
    private static LogFileWriter logfw = LogFileWriter.getInstance();

    public static void setStatus(boolean status) {
        if (status) {
            d("MyLog", "Log Turn On");
        } else {
            d("MyLog", "Log Turn OFF");
        }

        MyLog.status = status;
    }

    public static void releaseLogPrint() {
        logfw=null;
    }

    public static void setLogPrint() {
        logfw= LogFileWriter.getInstance();
    }

    public static boolean getStatus() {
        return status;
    }

    //debug
    public static String d(String tag, String message) {

        String output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | Debug | " + tag + " | " + message;
        if (status) {
            System.out.println(output);
        }

        logfw.setData(output,true);
        return output;
    }

    public static String e(String tag, String message) {
        FileWriterClass fwc_error = new FileWriterClass("src/academy/logs/DebugData_"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".txt");
        String output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | Error | " + tag + " | " + message;
        if (status) {
            System.out.println(output);
        }
        fwc_error.setData(output);
        logfw.setData(output,false);
        return output;
    }

}
