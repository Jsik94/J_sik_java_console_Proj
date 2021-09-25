package academy;

import academy.crypto.AES128Util;

import java.io.*;

public class FileWriterClass implements DirectoryInfo{
    final static private String TITLE="FILE_WRITER_CLASS";
    final static private String DEFAULT = DIR_DEFUALT+ "Address.txt";
    private boolean appendOps = false;
    private boolean showLog = true;
    PrintWriter bw = null;


    public FileWriterClass(){
        this(DEFAULT);
    }

    public FileWriterClass(String dir) {
        this(dir,false);
    }

    public FileWriterClass(String dir ,boolean appendOps){
        access_file(dir);
        showLog = false;
    }

    private void access_file(String dir) {
        MyLog.d(TITLE,"Try to Access : " + dir);
        try {
            bw = new PrintWriter(new FileWriter(dir,false));
            MyLog.d(TITLE,"OPEN_SUCCESS");


        } catch (FileNotFoundException e) {
            MyLog.e(TITLE,"OPEN_FAIL" + e.getMessage());
        } catch (IOException e) {
            MyLog.e(TITLE,"OPEN_FAIL" + e.getMessage());
        }

    }

    public void setData(String data) {
        access_file(DEFAULT);

        MyLog.d(TITLE,"Encrypt data on your MAC Key");
        AES128Util cyper = new AES128Util(AES128Util.getLocalMacAddress());
        String encrypt = cyper.encrypt(data);

        bw.println(encrypt);

        MyLog.d(TITLE,"Data Write Success!");
        closed();
    }
    public void setData(String data,String dir) {
        access_file(dir);
        for (String line : data.split("\r\n")) {
            bw.println(line);
        }
        MyLog.d(TITLE,"Data Write Success!");
        closed();
    }



    private void closed(){
        if(bw !=null){
            bw.close();
            MyLog.d(TITLE,"CLOSE_SUCCESS");
        }

    }






}
