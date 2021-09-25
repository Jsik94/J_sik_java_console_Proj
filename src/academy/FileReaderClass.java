package academy;

import academy.crypto.AES128Util;

import java.io.*;

public class FileReaderClass implements DirectoryInfo{

    static private volatile FileReaderClass uniqueInstance;
    final static private String TITLE = "FILE_READER_CLASS";
    final static private String DEFAULT = DIR_DEFUALT + "Address.txt";
    private boolean showLog = true;
    private StringBuilder sb;



    private FileReaderClass(){
        //defualt Address database
        access_file(DEFAULT);

    }


    private void access_file(String dir) {
        clearBuffer();
        BufferedReader br = null;

        MyLog.d(TITLE," Try to Access : "+dir );
        try {
            br = new BufferedReader(new FileReader(dir));
            MyLog.d(TITLE,"OPEN_SUCCESS");

//            String data;
//            while ((data = br.readLine()) != null) {
//                sb.append(data).append("\n");
//
//            }
            sb.append(br.readLine());

        } catch (FileNotFoundException e) {
            MyLog.e(TITLE,"OPEN_FAIL | \t " +e.getMessage());
        } catch (IOException e) {
            MyLog.e(TITLE,"OPEN_FAIL | \t " +e.getMessage());
        }finally {

            if(br !=null){

                try {
                    br.close();
                    MyLog.d(TITLE,"CLOSE_SUCCESS ");
                } catch (IOException e) {
                    MyLog.e(TITLE,"CLOSE_FAIL");
                }
            }
        }


    }

    private void clearBuffer(){
        sb = new StringBuilder();
    }

    public String toStringAll(){
//        AES128Util crpto = new AES128Util(AES128Util.getLocalMacAddress());
//        String output= crpto.decrypt(sb.toString());
//
//        System.out.println(output);
//        if (output==null) return sb.toString();
//        return output;

        AES128Util cyper = new AES128Util(AES128Util.getLocalMacAddress());
        String decrypt = cyper.decrypt(sb.toString());
        System.out.println(decrypt);
        return decrypt;
    }

    public void setDir(String Dir){
        access_file(Dir);
    }

    public String[] toStringLine(){

        String data ;

        if( (data=toStringAll())==null){
            return null;
        }

        return data.split("\n");
    }


    public static FileReaderClass getInstance(){
        if(uniqueInstance==null){
            synchronized (FileReaderClass.class){
                if(uniqueInstance==null){
                    uniqueInstance = new FileReaderClass();
                }
            }
        }

        return uniqueInstance;
    }





}
