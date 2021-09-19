package academy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileReaderClass implements DirectoryInfo{

    final static private String DIR_DEFUALT = "src/academy/datas/Address.txt";
    private boolean showLog = true;
    StringBuilder sb;

    private void access_file(String dir) {
        clearBuffer();
        BufferedReader br = null;

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" | FILE_READER_CLASS | Try to Access : "+dir );
        try {
            br = new BufferedReader(new FileReader(dir));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" | FILE_READER_CLASS | OPEN_SUCCESS | ");

            String data;
            while ((data = br.readLine()) != null) {
                sb.append(data).append("\n");

            }

        } catch (FileNotFoundException e) {

            System.out.println("FILE_READER_CLASS | OPEN_FAIL |\t" + e.getMessage());
        } catch (IOException e) {
            System.out.println("FILE_READER_CLASS | READ_FAIL |\t" + e.getMessage());
        }finally {

            if(br !=null){

                try {
                    br.close();
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ " | FILE_READER_CLASS | CLOSE_SUCCESS | ");
                } catch (IOException e) {
                    System.out.println("FILE_READER_CLASS | CLOSE_FAIL |\t" + e.getMessage());
                }
            }

        }


    }

    private void clearBuffer(){
        sb = new StringBuilder();
    }

    public String toStringAll(){

        return sb.toString();
    }

    public String[] toStringLine(){
        return sb.toString().split("\r\n");
    }


    public FileReaderClass(String dir){
        access_file(dir);
        showLog =false;
    }

    public FileReaderClass(){
        //defualt Address database
        access_file(DIR_DEFUALT);

    }



}
