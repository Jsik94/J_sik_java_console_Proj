package academy;

import java.io.IOException;
import java.util.Scanner;

public class InputClass {
    String title =null;
    Scanner sc;
    boolean filter_addr = false;
    boolean filter_email = false;
    private MusicEnterEffect effect = null;
    public static boolean onOff = true;

    /*

    인풋데이터 로그 여부를 여기서 설정
    Observer 패턴 구현하고 setting 항목할때마다 noti로 셋팅변경

     */



    public InputClass(String title) {
        this.title = title;
        effect = MusicEnterEffect.getInstance();
    }


    public int getMenuInput(int[] menuOpts) {

        MyLog.d(title,"Prepare to get User input...");
        System.out.println("원하시는 번호를 입력해 주세요 ");
        int result = 0;

        while (true) {
            try{
                result =getInteger();
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
                MyLog.e(title,"User inputs wrong Number");
                System.out.println("해당하는 번호를 골라주세요.");
                continue;
            }
            break;
        }

        //MyLog.d(title,"Return user input : " + result);
        return result;
    }


    //Only confirm character
    public String getString() {
        MyLog.d(title,"Prepare to get User input... String Type");
        sc = new Scanner(System.in);
        if(onOff) new Thread(effect).start();
        String data = null;
        while (true) {
            data = sc.nextLine().trim();
            if (MyUtil.isNumber(data)) {
                MyLog.e(title,"Not String Form... : " +data);
                System.out.println("올바른 입력이 아닙니다. 문자를 입력하십시오.");
                continue;
            }

            if(filter_email){
                if(!emailFilter(data)) {
                    MyLog.e(title,"Not Email Form... : " +data);
                    System.out.println("올바른 입력이 아닙니다. 이메일형식을 입력하십시오.");
                    continue;
                }
            }

            if(filter_addr){
                if(!addressFilter(data)) {
                    MyLog.e(title,"Not Address Form... : " +data);
                    System.out.println("올바른 입력이 아닙니다. 주소형식 입력하십시오.(필수 입력 시/구)");
                    continue;
                }
            }
            break;
        }
        MyLog.d(title,"Return user input : " + data);
        return data;
    }

    //Only confirm Integer
    public int getInteger() {
        MyLog.d(title,"Prepare to get User input... Integer Type");
        sc = new Scanner(System.in);
        int result = 0;

        while (true) {
            String data = sc.nextLine().trim();
            if(onOff) new Thread(effect).start();
            if (!MyUtil.isNumber(data)) {
                System.out.println("올바른 입력이 아닙니다. 숫자를 입력하십시오.");
                continue;
            }
            result = Integer.parseInt(data);
            break;
        }
        MyLog.d(title,"Return user input : " + result);
        return result;
    }

    public String getOrigin(){
        sc = new Scanner(System.in);
        if(onOff) new Thread(effect).start();
        String data = sc.nextLine().trim();
        /*
            나중에 로그처리 여기서 하면 될듯
         */

        return data;
    }

    public boolean getYorN() {
        MyLog.d(title,"Prepare to get User input... Yes or No");
        int data = 0;
        while(true){
            data=(int)getOrigin().charAt(0);
            if(onOff) new Thread(effect).start();
            if(Character.toUpperCase(((char)data))=='Y'){
                MyLog.d(title,"Return True");
                return true;
            }else if(Character.toUpperCase(((char)data))=='N'){
                MyLog.d(title,"Return False");
                return false;
            }


            System.out.println("다시 입력해주세요.");
        }

    }

    private boolean emailFilter(String str){

        if(str.matches("(.+)@(.+)\\.(.+)")){
            return true;
        }else{
            return false;
        }
    }

    private boolean addressFilter(String str){
        if(str.matches("(.+)시(.+)구")){
            return true;
        }else{
            return false;
        }

    }






}
