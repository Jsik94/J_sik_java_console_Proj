package academy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLog {

    private static boolean status = false;

    public static void setStatus(boolean status) {

        if (status) {
            d("MyLog", "Log Turn On");
        } else {
            d("MyLog", "Log Turn OFF");
        }

        MyLog.status = status;
    }

    //debug
    public static String d(String tag, String message) {
        String output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | Debug | " + tag + " | " + message;
        if (status) {
            System.out.println(output);
        }
        return output;
    }

    public static String e(String tag, String message) {
        String output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | Error | " + tag + " | " + message;
        if (status) {
            System.out.println(output);
        }
        return output;
    }

}
