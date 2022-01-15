package academy;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFileWriter implements DirectoryInfo{

    final static private String TITLE="FILE_WRITER_CLASS";
    final static private String DEBUG = DIR_LOG_EXTERNAL+ "Debug_Data_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".txt";
    final static private String ERROR = DIR_LOG_EXTERNAL+ "Error_Data_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".txt";
    private static volatile   LogFileWriter uniqueInstance = null;
    private PrintWriter pw = null;

    private LogFileWriter(){
        setData("<-------------------------------------"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+"------------------------------------------------------->",true);
        setData("<-------------------------------------"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+"------------------------------------------------------->",false);

    }

    public static LogFileWriter getInstance(){
        if(uniqueInstance == null){
            synchronized (LogFileWriter.class){
                if(uniqueInstance ==null){
                    uniqueInstance = new LogFileWriter();
                }
            }
        }
        return uniqueInstance;
    }

    private void access_file(boolean isDebug) {
        try {
            pw = new PrintWriter(new FileWriter(isDebug ? DEBUG : ERROR,true));

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }

    public void setData(String data, boolean isDebug) {
        access_file(isDebug);
        for (String line : data.split("\r\n")) {
            pw.println(line);
        }

        pw.close();
    }




}
