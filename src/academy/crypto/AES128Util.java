package academy.crypto;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.Base64;

public class AES128Util {
    private String ips;
    private Key keySpec;

    public AES128Util(String key) {
        try {
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            this.ips = key.substring(0, 16);
            this.keySpec = keySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String str) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes()));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            String Str = new String(Base64.getEncoder().encode(encrypted));

            return Str;
        } catch (Exception e) {
            return null;
        }
    }

    public String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes("UTF-8")));

            byte[] byteStr = Base64.getDecoder().decode(str.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");

            return Str;
        } catch (Exception e) {
            return null;
        }
    }
    public static String getLocalMacAddress() {
        String result = "";
        InetAddress ip;

        try {
            ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            result = sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e){
            e.printStackTrace();
        }

        return result;
    }

//    public static void main(String[] args) {
//
//        String key = AES128Util.getLocalMacAddress();
//        AES128Util aes = new AES128Util(key);
//        System.out.println("key : " + key);
//
//        String txt = "Hello World";
//        String encrypt = aes.encrypt(txt);
//        String decrypt = aes.decrypt(encrypt);
//
//
//        System.out.println("평문 : " + txt);
//        System.out.println("암호화 : " + encrypt);
//        System.out.println("복호화 : " + decrypt);
//    }
}