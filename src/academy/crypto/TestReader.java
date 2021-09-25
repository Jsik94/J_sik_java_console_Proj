package academy.crypto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestReader {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/academy/crypto/myTest.txt"));
            StringBuilder sb = new StringBuilder();
            String a = "";
            while ((a= br.readLine())!=null){

                sb.append(a);
            }
            System.out.println(sb);
            System.out.println("<--decrypt-->");
            AES128Util cyper = new AES128Util(AES128Util.getLocalMacAddress());
            String encrypt = cyper.decrypt(sb.toString());
            System.out.println(encrypt);

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
