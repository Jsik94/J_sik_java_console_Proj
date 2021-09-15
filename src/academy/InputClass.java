package academy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputClass {

    final static private int REQUEST_MAIN_MENU = 1;
    Scanner sc;

    public InputClass() {

    }

    public int getMainMenuInput(int[] menuOpts) {

        System.out.println("원하시는 번호를 입력해 주세요 ");
        int result = 0;

        while (true) {
            sc = new Scanner(System.in);
            String data = sc.nextLine().trim();
            if (!MyUtil.isNumber(data)) {
                System.out.println("올바른 입력이 아닙니다.\n해당하는 번호를 골라주세요.");
                continue;
            }

            try{
                result = Integer.parseInt(data);
            }catch (NumberFormatException e){
                continue;
            }
            boolean toggle = false;
            for (int opt : menuOpts) {
                if (opt == result) {
                    toggle = true;
                    break;
                }
            }
            if (!toggle) {
                System.out.println("해당하는 번호를 골라주세요.");
                continue;
            }
            break;
        }

        return result;
    }


    public int getSubMenuInput(int[] menuOpts) {

        System.out.println("원하시는 번호를 입력해 주세요 ");
        int result = 0;

        while (true) {
            sc = new Scanner(System.in);
            String data = sc.nextLine().trim();
            if (!MyUtil.isNumber(data)) {
                System.out.println("올바른 입력이 아닙니다.\n해당하는 번호를 골라주세요.");
                continue;
            }
            try{
                result = Integer.parseInt(data);
            }catch (NumberFormatException e){
                continue;
            }

            boolean toggle = false;
            for (int opt : menuOpts) {
                if (opt == result) {
                    toggle = true;
                    break;
                }
            }
            if (!toggle) {
                System.out.println("해당하는 번호를 골라주세요.");
                continue;
            }
            break;
        }

        return result;
    }

    //Only confirm character
    public String getString() {
        sc = new Scanner(System.in);
        String data = null;
        while (true) {
            data = sc.nextLine().trim();
            if (MyUtil.isNumber(data)) {
                System.out.println("올바른 입력이 아닙니다.");
                continue;
            }
            break;
        }

        return data;
    }

    //Only confirm Integer
    public int getInteger() {
        sc = new Scanner(System.in);
        int result = 0;
        while (true) {
            String data = sc.nextLine().trim();
            if (!MyUtil.isNumber(data)) {
                System.out.println("올바른 입력이 아닙니다.");
                continue;
            }
            result = Integer.parseInt(data);
            break;
        }

        return result;
    }

    public boolean getYorN() {
        int data = 0;
        while(true){
            try {
                data=System.in.read();
                if(Character.toUpperCase(((char)data))=='Y'){
                    return true;
                }else if(Character.toUpperCase(((char)data))=='Y'){
                    return false;
                }
                System.out.println("다시 입력해주세요.");
            } catch (IOException e) {
                System.out.println("다시 입력해주세요");
            }
        }

    }

}
