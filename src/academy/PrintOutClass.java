package academy;

import java.util.*;

public class PrintOutClass implements Actionable {
    /*
     * 분리 예정
     * idea 1
     * InsertMenu 클래스와 중복이 과함 출력과 네비게이터는 공유하되
     * 입출력을 구분하는 식으로 하는게 나을 듯
     *
     */

    final static int[] OPTS = new int[]{1, 2, 3, 4,5};
    final static int INSERT_STUDENT = 1;
    final static int INSERT_TEACHER = 2;
    final static int INSERT_ALL = 3;
    final static int GO_BACK = 4;
    final static int EXIT = 5;
    final static private int MOVE_EXIT = 999;
    final static private int COMPLETE = 998;
    final static private int CURRENT = 997;


    InputClass inputClass;
    Map<String,ArrayList<Person>> database = null;


    public PrintOutClass(Map<String,ArrayList<Person>> database) {
        inputClass = new InputClass();
        this.database = database;
    }



    @Override
    public void show() {
        System.out.println("=============\t서브 메뉴 설정\t=============");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=\t\t1.학생 2.선생 3.모두 보기 4.뒤로가기\t=");
        System.out.println("=\t\t\t\t\t\t\t\t\t\t\t=");
        System.out.println("=============================================");

    }


    @Override
    public int run() {
        while (true){
            show();
            int result = inputClass.getSubMenuInput(OPTS);
            int request_code = 0;

            switch (result) {
                case INSERT_STUDENT:
                    request_code= print(INSERT_STUDENT);
                    break;
                case INSERT_TEACHER:
                    request_code= print(INSERT_TEACHER);
                    break;
                case INSERT_ALL:
                    request_code= print(INSERT_ALL);
                    break;
                case GO_BACK:
                    request_code = COMPLETE;
                    break;
                case EXIT:
                    return MOVE_EXIT;
            }

            if (request_code== CURRENT){
                continue;
            }else{
                break;
            }
        }

        return COMPLETE;
    }

    public int print(int type){

        if(database.isEmpty()){
            System.out.println("등록된 정보가 없습니다.");
            return CURRENT;
        }


        StringBuilder sb = new StringBuilder();
        String unique = "전체";
        boolean student = true, teacher = true;
        if(type == INSERT_STUDENT){
            teacher = false;
            unique = "학번";
        }else if(type == INSERT_TEACHER){
            student = false;
            unique = "과목";
        }


        System.out.println("<--\t\t\t\t\t\t정보 출력\t\t\t\t\t\t-->");
        //before format
//        System.out.printf("%-5s %-2s %-8s %-22s%-11s %-32s\n","이름","나이",unique,"주소",
//                "연락처","메일 주소");
        System.out.printf("%-8s%-6s%-15s%-22s%-20s%-32s\n","이름","나이",unique,"주소",
                "연락처","메일 주소");


        for (String keyset : database.keySet()){
            for (Person info : database.get(keyset)){
                if (info instanceof Student && student){

                    //before format "%-5s %-3d %-9d %-20s %-11s %-32s"
                    sb.append(String.format("%-8s%-6d%-15s%-22s%-22s%-32s",
                            info.getName(),
                            info.getAge(),
                            ((Student) info).getStrNumber(),
                            info.getAddr(),
                            info.getTel(),
                            info.getEmail())).append("\n");
                }
                if (info instanceof Teacher && teacher){
                    sb.append(String.format("%-8s%-6d%-15s%-22s%-22s%-32s",
                            info.getName(),
                            info.getAge(),
                            ((Teacher) info).getSubject(),
                            info.getAddr(),
                            info.getTel(),
                            info.getEmail())).append("\n");
                }
            }
        }

        System.out.println(sb);

        return CURRENT;
    }



}
