package academy.crypto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TestWriter {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("src/academy/crypto/myTest.txt"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < 800 ; i++){
                sb.append(i+"으갼ㅇ람ㄴㅇㅁㄴ아으우구아야ㅐ갸갸갸갸갸갸갸갸").append("\n");
            }
            System.out.println(sb);
            System.out.println("after encrypt");
            AES128Util cyper = new AES128Util(AES128Util.getLocalMacAddress());
            String encrypt = cyper.encrypt(sb.toString());
            System.out.println(encrypt);
            pw.println(encrypt);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
