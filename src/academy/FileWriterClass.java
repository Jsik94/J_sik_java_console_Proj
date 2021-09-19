package academy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWriterClass implements DirectoryInfo{

    final static private String DIR_DEFAULT = "src/academy/datas/Address_copied.txt";
    private boolean appendOps = false;
    private boolean showLog = true;
    PrintWriter bw = null;

    private void access_file(String dir) {


        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | FILE_WRITER_CLASS | Try to Access : " + dir);
        try {
            bw = new PrintWriter(new FileWriter(dir,false));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " | FILE_WRITER_CLASS | OPEN_SUCCESS | ");

        } catch (FileNotFoundException e) {

            System.out.println("FILE_WRITER_CLASS | OPEN_FAIL |\t" + e.getMessage());
        } catch (IOException e) {
            System.out.println("FILE_WRITER_CLASS | READ_FAIL |\t" + e.getMessage());
        }

    }

    public void setData(String data) {

        for (String line : data.split("\r\n")) {
            bw.println(line);
        }

    }


    private void closed(){
        if(bw !=null){
            bw.close();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ " | FILE_WRITER_CLASS | CLOSE_SUCCESS | ");
        }

    }

    public FileWriterClass(){
        this(DIR_DEFAULT);
    }

    public FileWriterClass(String dir) {
        this(dir,false);
    }

    public FileWriterClass(String dir ,boolean appendOps){
        access_file(dir);
        showLog = false;
    }





}
